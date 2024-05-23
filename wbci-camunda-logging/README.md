# WBCI Camunda Logging

> A library to enable logging for Camunda job-worker methods of WBCI microservices

## Usage

Please add this dependency in the WBCI microservice.

```xml

<dependency>
  <groupId>net.conology</groupId>
  <artifactId>wbci-camunda-logging</artifactId>
  <version>${latestVersion}</version>
</dependency>
```

## How to define Camunda Job Workers

To enable logging for your job-workers, make sure you are following
the [WBCI Coding Guidelines](https://conology.atlassian.net/wiki/spaces/WBCI/pages/2585395239/Coding+Guidelines)
to create your Camunda job-workers.
Ideally, they should be residing inside `net.conology..*.camunda` package structure.

Additionally, each job-worker must follow the below method signature:

```java

@JobWorker(type = "workerName")
public <return_type> methodName(ActivatedJob job) {
  // method body
}
```

Any return type can be used, but it must accept a single `ActivatedJob` param.
