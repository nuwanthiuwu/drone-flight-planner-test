package tests;

import pages.DroneFlightPlanner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestDroneFlightPlanner extends BaseTests {
    private DroneFlightPlanner droneFlightPlanner;

    @BeforeClass
    void init() {
        this.droneFlightPlanner = new DroneFlightPlanner(getWebDriver());
    }

    @Test(priority = 1)
    public void when_the_user_clicks_on_the_create_new_flight_plan() {
        droneFlightPlanner.clickCreateANewFlightPlanButton();
        assertEquals(droneFlightPlanner.verifyNewFlightPlanMessage(), "Choose a point near your take off position to start creating your flight plan.");
    }

    @Test(priority = 2, invocationCount = 2)
    public void when_the_user_mark_points_on_the_map_viewer() {
        //sample coordinates
        int[] xAxis = {100, 100, 50, -100, -10, -80};
        int[] yAxis = {-120, -20, -60, 50, 100, 10};
        droneFlightPlanner.markPointsOnMapViewer(xAxis, yAxis);
        assertEquals(droneFlightPlanner.getNumberOfPointsDisplayed(), xAxis.length);
    }
}
