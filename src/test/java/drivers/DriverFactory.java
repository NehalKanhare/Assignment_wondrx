package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {
    public static WebDriver initDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "firefox": return new FirefoxDriver();
            case "edge": return new EdgeDriver();
            default: return new ChromeDriver();
        }
    }
}


