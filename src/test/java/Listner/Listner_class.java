package Listner;

import Base.baseclass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static Base.baseclass.logger;
import static Base.baseclass.props;

public class Listner_class extends baseclass implements ITestListener {
    public void onTestStart(ITestResult result) {
        if(props.getProperty("browser").equalsIgnoreCase("chrome")) {
            System.out.println("browser name is "+"------->"+props.getProperty("browser"));
            //chromeNetworkResponse();
        }
        ExtentTest lgg = extent.createTest(result.getMethod().getMethodName()).
                assignCategory(result.getMethod().getGroups());
        System.out.println("Code running");
        test.set(lgg);
        System.out.println("logger set");
        logger().log(Status.INFO,result.getMethod().getMethodName() + "test started");
        System.out.println("test started");
    }
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        logger().log(Status.INFO,result.getMethod().getMethodName() + "pass");
        System.out.println(result.getMethod().getMethodName()+"is passed");
        logger().log(Status.PASS, result.getMethod().getDescription());
    }
    @Override
    public void onTestFailure(ITestResult result) {
        try {
            ITestListener.super.onTestFailure(result);
            System.out.println(result.getThrowable().toString());
            logger().log(Status.INFO,result.getMethod().getMethodName() + "is failed");
            //logger().log(Status.FAIL, result.getMethod().getDescription());
        } catch (Exception e) {
            logger().log(Status.INFO,e.toString());
        }
        finally {
            logger().log(Status.INFO,"final runned");
            logger().log(Status.INFO,"finally run ");
            logger().log(Status.FAIL,result.getMethod().getMethodName() + "is failed");
            logger().log(Status.INFO,result.getThrowable().getMessage());
        }
    }

}
