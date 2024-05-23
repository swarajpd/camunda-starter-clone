package net.conology.wbcicamundatestapp;

import static io.camunda.zeebe.process.test.assertions.BpmnAssert.assertThat;
import static io.camunda.zeebe.spring.test.ZeebeTestThreadSupport.waitForProcessInstanceCompleted;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import io.camunda.zeebe.spring.test.ZeebeSpringTest;
import java.util.List;
import net.conology.wbcicamundalogging.aspect.LoggingAspect;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ZeebeSpringTest
class ProcessServiceTaskTest {

  private static final String BPMN_PROCESS_ID = "LoggingAspectTestProcess";
  @Autowired
  private ZeebeClient client;

  @Test
  void testLoggingAspectTestProcess() {
    Logger loggingAspectLogger = (Logger) LoggerFactory.getLogger(LoggingAspect.class);
    ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
    listAppender.start();
    loggingAspectLogger.addAppender(listAppender);

    ProcessInstanceEvent processInstance = client
        .newCreateInstanceCommand()
        .bpmnProcessId(BPMN_PROCESS_ID)
        .latestVersion()
        .send()
        .join();
    waitForProcessInstanceCompleted(processInstance.getProcessInstanceKey());

    assertThat(processInstance).hasPassedElement("AspectTest_PerformDummyTask").isCompleted();
    List<ILoggingEvent> logsList = listAppender.list;
    assertThat("There are not 2 log entries for a service task method", logsList.size(), equalTo(2));
    ILoggingEvent firstLogEntry = logsList.getFirst();
    assertThat("Logging Level is not INFO", firstLogEntry.getLevel(), equalTo(Level.INFO));
    assertThat("Log message doesn't contain business key of the process", firstLogEntry.getFormattedMessage(),
        containsString(BPMN_PROCESS_ID));
    assertThat("Log message doesn't contain process instance key", firstLogEntry.getFormattedMessage(),
        containsString(String.valueOf(processInstance.getProcessInstanceKey())));
  }

}
