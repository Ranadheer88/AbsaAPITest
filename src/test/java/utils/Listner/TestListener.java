package utils.Listner;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentReports.ExtentManager;
import utils.ExtentReports.ExtentTestManager;

public class TestListener implements ITestListener {

    public void onStart(ITestContext iTestContext) {
    }

    public void onFinish(ITestContext iTestContext) {
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }

    public void onTestStart(ITestResult iTestResult) {
        ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(),"");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
    }

    public void onTestFailure(ITestResult iTestResult) {
        ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed");
    }

    public void onTestSkipped(ITestResult iTestResult) {
        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

}
