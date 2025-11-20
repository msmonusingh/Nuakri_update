import Base.baseclass;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class naukari extends baseclass {
    @Test
    public void update2() throws IOException, InterruptedException {

        driver().manage().window().maximize();
        driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver().get("https://www.naukri.com/");
        logger().log(Status.INFO,driver().getTitle());
        addScreemshot();
        Thread.sleep(2000);
        WebElement aa = driver().findElement(By.xpath("//*[@id='login_Layer']"));
        putwaitclick(aa);
        logger().log(Status.INFO,"click on login button by wait");
        addScreemshot();
        logger().log(Status.INFO,"click on login layer");
        if (true) {
            Thread.sleep(5000);
            driver().findElement(By.xpath("//*[@placeholder='Enter your active Email ID / Username']"))
                    .sendKeys("mr.shivamsingh2610@gmail.com");
            addScreemshot();
            Thread.sleep(5000);
            logger().log(Status.INFO,"Enter user name");
            driver().findElement(By.xpath("//*[@placeholder='Enter your password']"))
                    .sendKeys("Abcd@1995");
            addScreemshot();
            logger().log(Status.INFO,"Enter password");
            Thread.sleep(5000);
            driver().findElement(By.xpath("//button[text()='Login']")).click();
            addScreemshot();
            logger().log(Status.INFO,"click on login button");
            Thread.sleep(3000);
            if (driver().getCurrentUrl().equalsIgnoreCase("https://www.naukri.com/mnjuser/homepage")) {
                logger().log(Status.INFO,"user logged in ");
                logger().log(Status.INFO,driver().getCurrentUrl());

                try {
                    if(driver().findElement(By.className("crossIcon chatBot chatBot-ic-cross")).isDisplayed())
                    {
                        driver().findElement(By.className("crossIcon chatBot chatBot-ic-cross")).click();
                        logger().log(Status.INFO,"click on the cross icon");

                    }
                } catch (InvalidSelectorException e) {
                    System.out.println(e.getMessage());
                }
                driver().findElement(By.xpath("//*[@class='view-profile-wrapper']/a")).click();
                logger().log(Status.INFO,"click on My profile ");
                logger().log(Status.INFO,driver().getCurrentUrl());
                Thread.sleep(5000);

                DayOfWeek today = LocalDate.now().getDayOfWeek();
                String day = today.toString();   // Example: MONDAY

                System.out.println("Today is: " + day);

                // Check and print Even or Odd
                if(day.equals("MONDAY") || day.equals("WEDNESDAY") || day.equals("FRIDAY")) {
                    System.out.println("Even");
                    String filePath = System.getProperty("user.dir") + "/resume/4_year_automation_tester.pdf";

                    driver().findElement(By.xpath("//*[@id='attachCV']"))
                            .sendKeys(filePath);
                    logger().log(Status.INFO,driver().getCurrentUrl());
                    logger().log(Status.INFO,"resume updated");
                } else {
                    String filePath = System.getProperty("user.dir") + "/resume/automation_tester_4year.pdf";

                    driver().findElement(By.xpath("//*[@id='attachCV']"))
                            .sendKeys(filePath);
                    logger().log(Status.INFO,driver().getCurrentUrl());
                    logger().log(Status.INFO,"resume updated");
                }




                Thread.sleep(3000);
                addScreemshot();
                String msg = driver().findElement(By.xpath("//p[@class='msg']")).getText();
                logger().log(Status.INFO,msg);
                //clickByjavaScript("//*[@id='attachCV']");
                //uploadFile("Resume/My_Resume _removed.pdf");

                try {
                    if(driver().findElement(By.xpath("//div[@class=\"widgetHead\"]/span")).isDisplayed())
                    {
                        List<WebElement>data_list = driver().findElements(By.xpath("//div[@class=\"widgetHead\"]/span"));
                        for (  WebElement data:data_list)
                        {
                         if(data.getText().equalsIgnoreCase("editOneTheme"))
                         {
                             data.click();
                         }
                        }
                        String resume_data="QA Automation Engineer with 4+ years of experience in software testing,manual testing,Functional Testing,Selenium Webdriver,Automation Testing,Postman Api,Quality Analysis,Selenium Webdriver,api automation,regression testing,load testing.";
                        driver().findElement(By.id("resumeHeadlineTxt")).clear();
                        driver().findElement(By.id("resumeHeadlineTxt")).sendKeys(resume_data);
                        Thread.sleep(20000);
                        By xpath = By.xpath("//button[@type='submit']");
                        if (driver().findElement(xpath).isDisplayed()) {
                            baseclass.putwaitclick(driver().findElement(xpath));
                        }


                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        } else {
            System.out.println("check your username and password");
        }
    }
}
