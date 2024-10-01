package hemangs.own.project.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;


@Service
public class WebsitesScraping {

    public String scrapingLeetCode(){
        WebDriverManager.chromedriver().driverVersion("129.0.6668.59").setup();
        WebDriver driver = new ChromeDriver();

        try{
            driver.get("https://leetcode.com/u/Em_hemang/");
            String websiteText = ".text-label-1.dark\\:text-dark-label-1.break-all.text-base.font-semibold";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(websiteText)));
            //WebElement webElement = driver.findElement(By.cssSelector(websiteText));
            String extractedText = webElement.getText();
            System.out.println("leetcodes scraped Text:" + extractedText);
            return extractedText;

        }finally {
            driver.quit();
        }
    }
}