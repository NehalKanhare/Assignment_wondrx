package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class CommonMethods {

    public static WebElement waitForElement(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        highlightElement(driver, element);
        return element;
    }

	
    
    public static void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll to the element smoothly
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        // Small pause so it’s visually observable
        try {
            Thread.sleep(1000); // 1 second pause
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Highlight with red border
        js.executeScript("arguments[0].style.border='3px solid red'", element);

        //1 second pause after highlighting
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static List<WebElement> waitForElements(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        
        for (WebElement element : elements) {
            highlightElement(driver, element);
        }
        return elements;
    }
    public static void scrollToBottom(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        try { Thread.sleep(1500); } catch (InterruptedException e) {}
    }

    
    public static void verifyRedirection(WebDriver driver, By locator, String expectedUrl) {
        scrollToBottom(driver);
        WebElement link = waitAndHighlight(driver, locator);
        Assert.assertTrue(link.isDisplayed(), "Link should be visible");
        link.click();
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        String actualUrl = driver.getCurrentUrl().split("\\?")[0];
        Assert.assertEquals(actualUrl, expectedUrl, "Redirection mismatch");
    }

	static WebElement waitAndHighlight(WebDriver driver, By locator) {
		return null;
	}
    
    
    
}
