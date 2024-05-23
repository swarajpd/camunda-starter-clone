package net.conology.wbcicamundalogging.autoconfigure;

import lombok.RequiredArgsConstructor;
import net.conology.wbcicamundalogging.aspect.LoggingAspect;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
@EnableConfigurationProperties(WbciCamundaProperties.class)
@AutoConfiguration
public class WbciCamundaAutoConfiguration {

  private final WbciCamundaProperties wbciCamundaProperties;

  @Bean
  public LoggingAspect loggingAspect() {
    return new LoggingAspect();
  }
}
