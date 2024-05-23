package net.conology.wbcicamundalogging.aspect;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

  /**
   * A common format for log entries.
   * <li>First placeholder should be populated by the unique business key of the process.
   * <li>Second placeholder should be populated by the intended log statement.
   * <li>Third placeholder should be populated by the process instance key.
   * <li>Fourth placeholder should be populated by the activity name.
   */
  public static final String LOG_PREFIX = "{} : {} ||  processInstanceID = {} || Activity Name = {}";

  @Pointcut("execution(* net.conology..*(..))")
  public void conologyPackages() {
  }

  @Pointcut("@annotation(io.camunda.zeebe.spring.client.annotation.JobWorker)")
  public void jobWorkerMethods() {
  }

  /**
   * The method is called before each method in the Camunda ServiceTask class.
   *
   * @param joinPoint metadata container at any point during program execution
   */
  @Before("conologyPackages() && jobWorkerMethods()")
  public void beforeActivatedJob(JoinPoint joinPoint) {
    final ActivatedJob activatedJob = (ActivatedJob) joinPoint.getArgs()[0];
    log.info(LOG_PREFIX, activatedJob.getBpmnProcessId(), "Started", activatedJob.getProcessInstanceKey(),
        activatedJob.getElementId());
  }

  /**
   * The method is called after a method in the Camunda ServiceTask class if no error is thrown.
   *
   * @param joinPoint metadata container at any point during program execution
   */
  @AfterReturning("conologyPackages() && jobWorkerMethods()")
  public void afterActivatedJobSuccessful(JoinPoint joinPoint) {
    final ActivatedJob activatedJob = (ActivatedJob) joinPoint.getArgs()[0];
    log.info(LOG_PREFIX, activatedJob.getBpmnProcessId(), "Completed", activatedJob.getProcessInstanceKey(),
        activatedJob.getElementId());
  }

  /**
   * The method is called after a method in the Camunda ServiceTask class if an error is thrown.
   *
   * @param joinPoint metadata container at any point during program execution
   */
  @AfterThrowing("conologyPackages() && jobWorkerMethods()")
  public void afterActivatedJobFailed(JoinPoint joinPoint) {
    final ActivatedJob activatedJob = (ActivatedJob) joinPoint.getArgs()[0];
    log.error(LOG_PREFIX, activatedJob.getBpmnProcessId(), "Failed", activatedJob.getProcessInstanceKey(),
        activatedJob.getElementId());
  }

}
