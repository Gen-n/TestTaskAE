package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.io.PrintWriter;

public class RetryAnalyzerImpl implements IRetryAnalyzer {

    private int retryCounter = 0;
    private int maxRetryCount;

    public RetryAnalyzerImpl(int maxRetryCount){
        this.maxRetryCount = maxRetryCount;
    }

    public boolean retry(ITestResult result){
        if(retryCounter < maxRetryCount){
            retryCounter++;
        } else {
            if(Boolean.parseBoolean(result.getMethod().getXmlTest().getLocalParameters().get("resultToLogFile"))){
                if(result.getStatus() != 1){
                    try(PrintWriter printWriter = new PrintWriter("prodTestLog.txt", "UTF-8")){
                        printWriter.println("Result: fail");
                    } catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        }
        return false;
    }
}
