package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

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

        Actions actions = new Actions(driver);
        actions.moveToElement((element), 0, 0);
        //Mark all the points on the map viewer
        for (int i = 0; i < xAxis.length; i++) {
            actions.moveByOffset(xAxis[i], yAxis[i]).click().build().perform();
            //Wait time
            driver.manage().timeouts().implicitlyWait(SECONDS_WAIT_AFTER_POINT_ADDED, TimeUnit.SECONDS);
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
