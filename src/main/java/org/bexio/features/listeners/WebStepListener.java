package org.bexio.features.listeners;

import com.google.common.base.Stopwatch;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebStepListener implements StepLifecycleListener {

  private final Stopwatch stopwatch = Stopwatch.createUnstarted();

  @Override
  public void afterStepStart(final StepResult result) {
    stopwatch.reset();
    stopwatch.start();
    log.info("Starting step: {}", result.getName());
  }

  @Override
  public void beforeStepStop(StepResult result) {
    log.info("Finishing step: " + result.getName() + " and took: " + stopwatch);
  }

}
