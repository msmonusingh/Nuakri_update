import Base.baseclass;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class naukari extends baseclass {
    @Test
    public void update() throws IOException, InterruptedException {

        driver().manage().window().maximize();
        driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver().get("https://www.naukri.com/");
        logger().log(Status.INFO,"site get open");
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
                    .sendKeys("msmonusingh8@gmail.com");
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
                driver().findElement(By.xpath("//*[@class='view-profile-wrapper']/a")).click();
                logger().log(Status.INFO,"click on My profile ");
                logger().log(Status.INFO,driver().getCurrentUrl());
//                if (driver.findElement(By.xpath("//*[@class='chatbot_Nav']/div")).isDisplayed()) {
//                    driver.findElement(By.xpath("//*[@class='chatbot_Nav']/div")).click();
//                } else {
//                    System.out.println("nothing");
//                }

                //clickwithborder("//*[@id='attachCV']");
                String path = System.getProperty("user.dir")+"src/main/Resume/My_Resume _removed.pdf";

                Thread.sleep(5000);
                driver().findElement(By.xpath("//*[@id='attachCV']"))
                        .sendKeys("C:/Users/mrshi/IdeaProjects/Page_object/src/main/Resume/My_Resume _removed.pdf");
                logger().log(Status.INFO,driver().getCurrentUrl());
                logger().log(Status.INFO,"resume updated");
                Thread.sleep(3000);
                addScreemshot();
                String msg = driver().findElement(By.xpath("//p[@class='msg']")).getText();
                logger().log(Status.INFO,msg);
                //clickByjavaScript("//*[@id='attachCV']");
                //uploadFile("Resume/My_Resume _removed.pdf");

            }
        } else {
            System.out.println("check your username and password");
        }
    }
    @Test
    public void update2() throws IOException, InterruptedException {

        driver().manage().window().maximize();
        driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver().get("https://www.naukri.com/");
        logger().log(Status.INFO,"site get open");
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
                driver().findElement(By.xpath("//*[@class='view-profile-wrapper']/a")).click();
                logger().log(Status.INFO,"click on My profile ");
                logger().log(Status.INFO,driver().getCurrentUrl());
//                if (driver.findElement(By.xpath("//*[@class='chatbot_Nav']/div")).isDisplayed()) {
//                    driver.findElement(By.xpath("//*[@class='chatbot_Nav']/div")).click();
//                } else {
//                    System.out.println("nothing");
//                }

                //clickwithborder("//*[@id='attachCV']");
                String path = System.getProperty("user.dir")+"src/main/Resume/shivam_singh_QA .pdf";

                Thread.sleep(5000);
                driver().findElement(By.xpath("//*[@id='attachCV']"))
                        .sendKeys("C:/Users/mrshi/IdeaProjects/Page_object/src/main/Resume/My_Resume _removed.pdf");
                logger().log(Status.INFO,driver().getCurrentUrl());
                logger().log(Status.INFO,"resume updated");
                Thread.sleep(3000);
                addScreemshot();
                String msg = driver().findElement(By.xpath("//p[@class='msg']")).getText();
                logger().log(Status.INFO,msg);
                //clickByjavaScript("//*[@id='attachCV']");
                //uploadFile("Resume/My_Resume _removed.pdf");

            }
        } else {
            System.out.println("check your username and password");
        }
    }
}
