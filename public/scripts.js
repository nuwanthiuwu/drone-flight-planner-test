document.addEventListener('DOMContentLoaded', function () {
    const createFlightPlanButton = document.getElementById('create-flight-plan');
    const saveMapButton = document.getElementById('save-map');
    const flightPlanMessage = document.getElementById('flight-plan-message');
    const savedMapsContainer = document.getElementById('saved-maps-container');
    let flightPlanActive = false;
    let markers = [];
    let polyline = null;
    let editingMap = false;
    let currentMapButton = null;

    // Initialize the map
    const map = L.map('map').setView([51.505, -0.09], 13);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© OpenStreetMap contributors'
    }).addTo(map);

    // Handle creating a new flight plan
    createFlightPlanButton.addEventListener('click', function () {
        flightPlanMessage.classList.remove('hidden');
        saveMapButton.classList.remove('hidden');
        flightPlanActive = true;
        createFlightPlanButton.disabled = true;
    });

    // Mark points on the map and connect them with lines
    map.on('click', function (e) {
        if (!flightPlanActive) return;

        const marker = L.marker(e.latlng).addTo(map);
        markers.push(marker);

        if (markers.length > 1) {
            const latlngs = markers.map(marker => marker.getLatLng());
            if (polyline) {
                map.removeLayer(polyline);
            }
            polyline = L.polyline(latlngs, { color: 'red' }).addTo(map);
        }
    });

    // Save or update the map with a timestamp and coordinates
    saveMapButton.addEventListener('click', function () {
        if (markers.length === 0) return;

        const markerCoordinates = markers.map(marker => marker.getLatLng());

        if (editingMap && currentMapButton) {
            // Update existing map
            currentMapButton.dataset.coordinates = JSON.stringify(markerCoordinates);
            currentMapButton.textContent = currentMapButton.textContent.replace(' (edited)', '') + ' (edited)';
        } else {
            // Save as a new map
            const timestamp = new Date().toISOString().replace(/[-:T]/g, '').slice(0, 14);
            const mapName = `map_${timestamp}`;

            const savedMapDiv = document.createElement('div');
            savedMapDiv.classList.add('saved-map-entry');
            savedMapDiv.style.display = 'flex'; // Align items in the same row

            const mapTitle = document.createElement('button');
            mapTitle.textContent = mapName;
            mapTitle.className = "save-ele";
            mapTitle.dataset.coordinates = JSON.stringify(markerCoordinates);
            mapTitle.style.flexGrow = '1'; // Make the map title take up available space

            const deleteButton = document.createElement('button');
            deleteButton.textContent = 'X';
            deleteButton.className = 'delete-map';
            deleteButton.style.backgroundColor = 'red'; // Change color to distinguish
            deleteButton.style.color = 'white';
            deleteButton.style.border = 'none';
            deleteButton.style.marginLeft = '10px';
            deleteButton.style.cursor = 'pointer';

            savedMapDiv.appendChild(mapTitle);
            savedMapDiv.appendChild(deleteButton);

            savedMapsContainer.insertBefore(savedMapDiv, savedMapsContainer.firstChild);
        }

        resetFlightPlan();
    });

    // Handle clicking on saved maps and delete buttons
    savedMapsContainer.addEventListener('click', function (e) {
        if (e.target.classList.contains('save-ele')) {
            resetFlightPlan();

            currentMapButton = e.target;
            editingMap = true;

            const coordinates = JSON.parse(e.target.dataset.coordinates);

            coordinates.forEach(latlng => {
                const marker = L.marker(latlng).addTo(map);
                markers.push(marker);
            });

            if (markers.length > 1) {
                const latlngs = markers.map(marker => marker.getLatLng());
                polyline = L.polyline(latlngs, { color: 'red' }).addTo(map);
            }

            flightPlanActive = true;
            saveMapButton.textContent = 'Update Flight Plan';
            createFlightPlanButton.disabled = true;
            saveMapButton.classList.remove('hidden');
            flightPlanMessage.classList.remove('hidden');
        } else if (e.target.classList.contains('delete-map')) {
            e.target.parentElement.remove(); // Remove the saved map entry
        }
    });

    // Reset the flight plan
    function resetFlightPlan() {
        if (polyline) {
            map.removeLayer(polyline);
        }
        markers.forEach(marker => map.removeLayer(marker));
        markers = [];
        polyline = null;
        flightPlanMessage.classList.add('hidden');
        createFlightPlanButton.disabled = false;
        saveMapButton.classList.add('hidden');
        flightPlanActive = false;
        editingMap = false;
        saveMapButton.textContent = 'Save Map';
        currentMapButton = null;
    }
});