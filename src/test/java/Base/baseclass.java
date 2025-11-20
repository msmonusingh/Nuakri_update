package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v115.network.Network;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.awaitility.Awaitility.await;

public class baseclass {
    public static SoftAssert softAssert= new SoftAssert();
    //public static WebDriver driver();
    public static WebDriverWait wait;
    public static ExtentReports extent;

    public static JavascriptExecutor jsExecutor;
    // public static WebDriverWait wait;
    public static Select select;
    public static Properties props;
    public static FileInputStream file;
    public static DevTools devtool;
    public static Robot rb;
    public static ChromeOptions options;
    public static SoftAssert softAssertion;
    public static ThreadLocal<WebDriver> dr= new ThreadLocal<>();
    public static ThreadLocal <ExtentTest> test= new ThreadLocal<>();

    public static ExtentTest logger() {
        return  test.get();
    }

    public static WebDriver driver() {
        return dr.get();
    }
    public static void unloadDriver() {dr.remove();}
    public static void unload_extent() {test.remove();}

    @BeforeMethod(alwaysRun = true)
    public void setup() throws IOException {
        softAssertion = new SoftAssert();
        file = new FileInputStream("src/main/Properties/Properties");
        props = new Properties();
        props.load(file);
        System.out.println(System.getProperty("browser"));
        if (extent == null) {
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter("report/page.html");
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("Automation report");
            spark.config().setReportName("shivam");
            extent.attachReporter(spark);
        }
        if (driver() == null) {
            if (System.getProperty("browser").equals("chrome")) {
                System.out.println("browser equal hai");
                System.setProperty("webdriver.manager", "false");

                System.out.println("----->"+System.getProperty("browser"));
                System.out.println("----->"+System.getProperty("envvalue"));
                WebDriverManager.chromedriver().setup();
                options = new ChromeOptions();
                options.setBrowserVersion("116");
                options.addArguments("--no-sandbox");
              options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--headless=new");
                options.addArguments("--remote-allow-origins=*");
                //options.addArguments("--headless", "--window-size=1920,1200");

                // options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36");
                dr.set(new ChromeDriver(options));
               //dr.set(new ChromeDriver());
                driver().manage().window().maximize();
                driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
                driver().manage().deleteAllCookies();
            } else {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36");

                dr.set(new EdgeDriver(options));
                //dr.set(new EdgeDriver());
                driver().manage().window().maximize();
                driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            }
        }
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() throws InterruptedException, IOException {
        if (extent != null) {
            unload_extent();
            extent.flush();

        }
        if (driver() != null) {
            try {
                driver().quit();
                unloadDriver();
                //devtool.close();
            } catch (Exception e) {
                driver().quit();
                unloadDriver();
                System.out.println("--------------->" + e.getMessage());
                System.out.println("this is new");
            }
        }
    }
    @AfterSuite
    public void getreport() throws IOException {
        //Desktop.getDesktop().browse(new File("report/page.html").toURI());
    }

    public static String screenShot2() throws IOException {
        String src = ((TakesScreenshot) driver()).getScreenshotAs(OutputType.BASE64);
        return src;
    }

    public static void flogin(WebElement element) throws InterruptedException {
        driver().get(props.getProperty("prod"));
        Thread.sleep(2000);
        clickwithborder(element);
    }

    //@BeforeMethod
    public void beforeTest() {
        driver().manage().deleteAllCookies();
    }


    public static void flogout(WebElement element) throws InterruptedException {
        clickwithborder(element);
        Thread.sleep(2000);
        clickwithborder(element);
        driver().manage().deleteAllCookies();
    }




    //
    public static void clickwithborder(WebElement element) {
        if (element.isDisplayed()) {
            jsExecutor = (JavascriptExecutor) driver();
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);
            element.click();
        }
    }

    public static void borderwithsendkeys(WebElement element, String data) {
        if (element.isDisplayed()) {
            jsExecutor = (JavascriptExecutor) driver();
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);
            element.sendKeys(data);
        }
    }

    public static void putwaitclick(WebElement element) {
        wait = new WebDriverWait(driver(), Duration.ofMillis(10));
        wait.until(ExpectedConditions.visibilityOf(element)).click();

    }
    public static void clickwithcontains(String contains) {
        WebElement element = driver().findElement(By.xpath("//*[normalize-space()='" + contains + "']"));
        if (element.isDisplayed()) {
            jsExecutor = (JavascriptExecutor) driver();
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);
            element.click();
        }
    }

    public static void ClickwithXpath(String xpath) {
        WebElement element = driver().findElement(By.xpath(xpath));
        if (element.isDisplayed()) {
            jsExecutor = (JavascriptExecutor) driver();
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);
            element.click();

        }
    }

    public static void clickwithText(String Text) {
        WebElement element = driver().findElement(By.xpath("//a[contains(text(),'" + Text + "')]"));
        if (element.isDisplayed()) {
            jsExecutor = (JavascriptExecutor) driver();
            jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);
            element.click();

        }
    }

    public static void selectBySuggestions(String inputXpath, String inputVlalue, String suggestionsXpath, String suggestionsValue) throws InterruptedException {
        driver().findElement(By.xpath(inputXpath)).sendKeys(inputVlalue);
        Thread.sleep(5000);
        List<WebElement> Suggestion_list = driver().findElements(By.xpath(suggestionsXpath));
        Thread.sleep(3000);
        //System.out.println(Suggestion_list.size());
        //System.out.println("suggestion name is "+Company_suggestion.get(0).getText());
        for (WebElement suggestion : Suggestion_list) {
            Thread.sleep(3000);
            System.out.println("from suggestion tab:- "+suggestion.getText());
            if (suggestion.getText().contains(suggestionsValue)) {
                //putwaitclick(suggestion);
                jsExecutor = (JavascriptExecutor) driver();
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", suggestion);
                jsExecutor.executeScript("arguments[0].click();", suggestion);
                break;
            }
        }
    }

    public static void waitAndClick(WebElement element, int time) {

        //WebElement element = driver().findElement(By.xpath(Xpath));
        await().atMost(Duration.ofSeconds(time)).until(() -> {
                    return element.isDisplayed();
                }
        );
        element.click();
    }

    public static void waitandclickwithnotime(WebElement element) {

        //WebElement element = driver().findElement(By.xpath(Xpath));
        await().atMost(Duration.ofSeconds(10)).until(() -> {
                    return element.isDisplayed();
                }
        );
        element.click();
    }

    public static void waitAndDisplay(WebElement element) {
        //WebElement element = driver().findElement(By.xpath(Xpath));
        await().until(() -> {
                    return element.isDisplayed();
                }
        );

    }
    public static void waitAndGetText(WebElement element) {
        //WebElement element = driver().findElement(By.xpath(Xpath));
        await().until(() -> {
                    return element.isDisplayed();
                }
        );
        System.out.println(element.getText());
    }


    public static void getText(String Xpath) {
        WebElement element = driver().findElement(By.xpath(Xpath));
        String text = element.getText();
        System.out.println(text);
    }


    public static void checkBtnEnable(String Xpath) {
        WebElement element = driver().findElement(By.xpath(Xpath));
        if (element.isEnabled()) {
            System.out.println("Button is enabled");
        } else {
            System.out.println("Button is disabled");
        }
    }

    public static void checkBtnWithText(String Xpath, String ButtonName) {
        WebElement element = driver().findElement(By.xpath(Xpath));
        //System.out.println(element.getText()+" "+ButtonName);
        if (element.getText().equalsIgnoreCase(ButtonName)) {
            System.out.println("button text is matched");
        } else {
            System.out.println("button text is not matched");
        }

    }

    public static void clickonLinkText(String LinkText) {
        WebElement element = driver().findElement(By.linkText(LinkText));
        if (element.isDisplayed()) {
            jsExecutor = (JavascriptExecutor) driver();
            jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);
            element.click();
        }

    }

    public static void clickByjavaScript(WebElement element) {
        //WebElement element = driver().findElement(By.xpath(xpath));
        if (element.isDisplayed()) {
            jsExecutor = (JavascriptExecutor) driver();
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            jsExecutor.executeScript("arguments[0].click();", element);
        }

    }

    public static void GetTextByJavascript(WebElement element) {
        //WebElement element = driver().findElement(By.xpath(xpath));
        {
            jsExecutor = (JavascriptExecutor) driver();
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            jsExecutor.executeScript("arguments[0].text;", element);
        }

    }

    public static void SendKeysByJavaScript(WebElement element, String textvalue) {
        //WebElement element = driver().findElement(By.xpath(xpath));
        if (element.isDisplayed()) {
            jsExecutor = (JavascriptExecutor) driver();
            jsExecutor.executeScript("arguments[0].value='" + textvalue + "';", element);
        }

    }


    public static void addScreemshot() throws IOException {
        String screen = screenShot2();
        logger().log(Status.INFO,MediaEntityBuilder.createScreenCaptureFromBase64String(screen).build());
        logger().log(Status.INFO, "Screenshot of this page", MediaEntityBuilder.createScreenCaptureFromBase64String(screen).build());
        //
    }


    public static void SelectByValue(WebElement element, String value) {
        //WebElement element = driver().findElement(By.xpath(selectvaluexpath));
        if (element.isDisplayed()) {
            select = new Select(element);
            select.selectByValue(value);
        }
    }

    public static void SelectByIndex(WebElement element, int index) {
        //WebElement element = driver().findElement(By.xpath(selectvaluexpath));
        if (element.isDisplayed()) {
            select = new Select(element);
            select.selectByIndex(index);
        }
    }

    public static void SelectByVisibleText(WebElement element, String text) {
        //WebElement element = driver().findElement(By.xpath(selectvaluexpath));
        if (element.isDisplayed()) {
            select = new Select(element);
            select.selectByVisibleText(text);
        }
    }

    public static void chromeNetworkResponse() {
        devtool = ((ChromeDriver) driver()).getDevTools();
        devtool.createSession();
        devtool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devtool.addListener(Network.responseReceived(), responseReceived ->
        {
            if ((responseReceived.getResponse().getStatus().toString().startsWith("40"))) {
                System.out.println("Api is not working correctly");
                System.out.println(responseReceived.getResponse().getUrl());
                System.out.println(responseReceived.getResponse().getUrl() + "Status code is " + responseReceived.getResponse().getStatus());
            }
            //System.out.println("-------------------------------->");
            //System.out.println(responseReceived.getResponse().getUrl()+"Status code is "+responseReceived.getResponse().getStatus());
        });
//

    }

    //file path is the path of your computer
    public static void uploadFile(String filePath) throws IOException, AWTException {
        rb = new Robot();
        rb.delay(5000);
        // copying File path to Clipboard
        StringSelection str = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
    }

    public void alertAccept() {
        wait = new WebDriverWait(driver(), Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    public void matchAlertMessage(String message) {
        wait = new WebDriverWait(driver(), Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        if (alert.getText().equals(message)) {
            System.out.println("message set same as expected");
        } else {
            System.out.println("message not set same as expected");
        }
        alert.accept();
    }

    public void getAlertText() {
        wait = new WebDriverWait(driver(), Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());

    }


    public  static void delete_user(String emailid)
    {
        RestAssured.baseURI="https://test.synkdup.com/qa/api/get-otp";
        Response delete = given().queryParam("email",emailid)
                .when().delete("https://test.synkdup.com/api/delete-user-from-db");
        System.out.println("status code "+delete.statusCode());
        System.out.println(delete.prettyPrint());
    }

    public void click(WebElement element)
    {
        element.click();
    }
    public void waitElement(WebElement element)
    {
        //wait=new WebDriverWait(driver(),)
    }

}
