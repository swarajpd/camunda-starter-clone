# WBCI Camunda Spring Boot Starter

> A Camunda - Spring Boot Starter project for WBCI microservices

## Installing / Getting started

This project defines starter libraries for using Camunda in a Spring-Boot
setup for WBCI. The objective of this project is to provide easy auto-configuration,
standardization & reusability across WBCI microservices using Camunda.

### List of libraries:

- [**wbi-camunda-logging**](./wbci-camunda-logging/README.md)

## Developing

### Built With

- Java 21
- Maven
- Camunda

### Prerequisites

These are the things you would need to install:

- [Java 21](https://www.oracle.com/java/technologies/downloads/#java21)
  and [Maven](https://maven.apache.org)
- [Camunda](https://camunda.com) for working with .bpmn files

### Setting up Dev

Executing the following should work well:

```shell
git clone https://github.com/conology/wbci-camunda-spring-boot-starter.git
cd wbci-camunda-spring-boot-starter
mvn clean install
```

## Versioning

The versioning is manged
via [![semantic-release: angular](https://img.shields.io/badge/semantic--release-angular-e10079?logo=semantic-release)](https://github.com/semantic-release/semantic-release)
using [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/).

## Tests

You can run tests by:

```shell
mvn clean verify
```

## Style guide

We use Google style scheme along with SonarLint for code quality check.
