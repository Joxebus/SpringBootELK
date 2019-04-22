# SpringBootELK
Examples for Spring Boot + ELK

This repo has examples of how to connect Spring Boot App with ELK project to manage logs.

If you are using IntelliJIDEa open the project and select the options:

- Use auto-import
- Create directories for empty content roots automatically
- Create separate module per source set

and finally

- Use default gradle wrapper


Inside `src/main/resources` a file named **postman_collection.json** is placed, is a configuration
that can be loaded on postman and contains some examples for the requests.

To see the test results you can open in your browser the **index.html** created under.

```
build
|_reports
  |_tests
    |_test
      |_index.html
```

## Run docker-compose

There is a docker-compose configuration to setup ELK and the configuration for TCP on logstash,
to run and install ELK execute the following command.

```
docker-compose up
```

After finish the installation you should be able to log this url `localhost:5601` which is the
interface for Kibana

Start your spring boot application, then enter into `localhost:5601` and create a new index for pattern
`logstash-*` follow the steps in the UI, now you should be able to see at discovery screen your logs.

## Requirements

- Java 8
- Gradle 3.3
- Postman

## Run tests

For MacOS and Linux

`` ./gradlew test ``  

or for Windows

`` gradlew.bat test``
