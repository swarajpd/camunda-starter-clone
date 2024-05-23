package net.conology.wbcicamundatestapp.camunda;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProcessServiceTask {

  @JobWorker(type = "performDummyTask")
  public void executeDummyTask(ActivatedJob job) {
    log.debug("Worker: {}", job.getWorker());
  }

}
