import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HardCodedTests {

    @Test(description = "Verify 'Trainings' search works properly with searching in 'Skills'")
    public void verifyTrainingsSearchWorksProperlyForSkills() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe"); //DOWNLOAD DRIVER !
        WebDriver driver =new ChromeDriver();
        WebDriverWait wait=new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://training.by/#/Home");

        WebElement signInButton = driver.findElement(By.xpath("//p[@class='heatytder-auth__signin']//span"));
        signInButton.click();
        WebElement mailInput = driver.findElement(By.id("signInEmail"));
        mailInput.sendKeys("ivanhorintest@gmail.com");
        WebElement passwordInput = driver.findElement(By.id("signInPassword"));
        passwordInput.sendKeys("ivanhorintestPassword");
        WebElement signIn = driver.findElement(By.className("popup-reg-sign-in-form__sign-in"));
        signIn.click();
        WebElement expandSkillsArrow = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.className("filter-toggle__arrow-icon")));
        expandSkillsArrow.click();

        WebElement bySkillsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'By skills')]")));
        bySkillsButton.click();

        WebElement skillsSearchInput = driver
                .findElement(By.xpath("//input[@name='training-filter-input']"));
        skillsSearchInput.sendKeys("Java");

        WebElement javaCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(.,'Java')]//span")));
        javaCheckbox.click();

        WebElement collapseSkillsArrow = driver.findElement(By.xpath("//div[@class='filter-toggle__arrow-icon arrow-icon-rotate']"));
        collapseSkillsArrow.click();

        List<WebElement> skillsSearchResultsList = driver.
                findElements(By.xpath("//div[@class='training-list__container training-list__desktop']//a"));
        skillsSearchResultsList.forEach(element-> Assert.assertTrue(element.getText().contains("JAVA"),
                String.format("Element %s does not contain 'Java' word.",element)));
        driver.quit();

        //ADD OTHER STEPS
    }
}
