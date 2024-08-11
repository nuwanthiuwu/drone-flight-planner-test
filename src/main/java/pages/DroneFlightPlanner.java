package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DroneFlightPlanner {
    private final WebDriver driver;

    private static final By CREATE_NEW_FLIGHT_PLANNER = By.xpath("//button[@aria-label='Create a new Flight Planadd']");
    private static final By MAP_ELEMENT = By.xpath("//div[@class='leaflet-overlay-pane']//*[name()='svg']");
    private static final By POINT_ELEMENTS = By.className("leaflet-marker-icon");
    private static final By NEW_PLAN_MESSAGE = By.xpath("//p[@ng-show=\"editor.state==='empty'\"]");

    private static final long SECONDS_WAIT_AFTER_POINT_ADDED = 3;

    public DroneFlightPlanner(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCreateANewFlightPlanButton() {
        WebElement createNewFlightPlanButton = driver.findElement(CREATE_NEW_FLIGHT_PLANNER);
        createNewFlightPlanButton.click();
    }

    public void markPointsOnMapViewer(int[] xAxis, int[] yAxis) {
        WebElement element = driver.findElement(MAP_ELEMENT);

        // Get the size of the map element
        int mapWidth = element.getSize().getWidth();
        int mapHeight = element.getSize().getHeight();

        Actions actions = new Actions(driver);
        actions.moveToElement(element, 0, 0);

        // Mark all the points on the map viewer
        for (int i = 0; i < xAxis.length; i++) {
            int xOffset = Math.min(Math.max(xAxis[i], -mapWidth / 2), mapWidth / 2);
            int yOffset = Math.min(Math.max(yAxis[i], -mapHeight / 2), mapHeight / 2);

            actions.moveByOffset(xOffset, yOffset).click().build().perform();

            // Simulate implicit wait (can also use explicit wait here)
           /* driver.manage().timeouts().implicitlyWait(SECONDS_WAIT_AFTER_POINT_ADDED, TimeUnit.SECONDS);*/

            // Optional: Add a short sleep to prevent any rapid sequential actions
            try {
                Thread.sleep(SECONDS_WAIT_AFTER_POINT_ADDED * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Move back to the center of the element
            actions.moveToElement(element, 0, 0);
        }
    }

    public int getNumberOfPointsDisplayed() {
        return driver.findElements(POINT_ELEMENTS).size();
    }

    public String verifyNewFlightPlanMessage() {
        WebElement newFlightPlanMessage = driver.findElement(NEW_PLAN_MESSAGE);
        return newFlightPlanMessage.getText();
    }
}
