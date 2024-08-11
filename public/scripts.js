document.addEventListener('DOMContentLoaded', () => {
    const createNewFlightPlanButton = document.getElementById('createNewFlightPlan');
    const mapElement = document.getElementById('map');
    const newPlanMessage = document.getElementById('newPlanMessage');
    let points = [];
    let editorState = 'empty';

    createNewFlightPlanButton.addEventListener('click', () => {
        // Handle the creation of a new flight plan
        editorState = 'creating';
        newPlanMessage.textContent = "Choose a point near your take off position to start creating your flight plan.";
        newPlanMessage.style.display = 'block';
    });

    mapElement.addEventListener('click', (event) => {
        if (editorState === 'creating') {
            const rect = mapElement.getBoundingClientRect();
            const x = event.clientX - rect.left;
            const y = event.clientY - rect.top;

            const marker = document.createElement('div');
            marker.className = 'leaflet-marker-icon';
            marker.style.left = `${x}px`;
            marker.style.top = `${y}px`;
            mapElement.appendChild(marker);

            points.push({ x, y });

            // Simulate Selenium implicit wait
            setTimeout(() => {
                console.log('Point added on map:', { x, y });
            }, 3000);
        }
    });

    // Function to get the number of points currently displayed on the map
    window.getNumberOfPointsDisplayed = function() {
        return points.length;
    };
});