package net.conology.wbcicamundatestapp;

import io.camunda.zeebe.spring.client.annotation.Deployment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@Deployment(resources = "classpath*:*.bpmn")
public class StartCamundaProcessApplication {

  public static void main(String[] args) {
    SpringApplication.run(StartCamundaProcessApplication.class, args);
    log.info("\n\nStartCamundaProcessApplication is up and running!\n");
  }

}
