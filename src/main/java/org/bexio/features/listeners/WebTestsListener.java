package org.bexio.features.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Component
@Slf4j
public class WebTestsListener implements ITestListener {

  @Override
  public void onTestSuccess(ITestResult result) {
    log.info("^^^^^^^^^^^^^^^^^ Test " + result.getName() + " is PASSED");
  }

  @Override
  public void onTestFailure(ITestResult result) {
    log.info("^^^^^^^^^^^^^^^^^ Test " + result.getName() + " is FAILED");
  }

  @Override
  public void onTestStart(final ITestResult result) {
    log.info("=================" + " Test: {} is starting", result.getName());
  }
}
