# SpringBootELK
Examples for Spring Boot + ELK

This repo has examples of how to connect Spring Boot App with ELK project to manage logs.

If you are using IntelliJIDEa open the project and select the options:

- Use auto-import
- Create directories for empty content roots automatically
- Create separate module per source set

and finally

- Use default gradle wrapper

## Run docker-compose

There is a docker-compose configuration to setup ELK and the configuration for TCP on logstash,
to run and install ELK execute the following command.

```
docker-compose up
```

After finish the installation you should be able to log this url `localhost:5601` which is the
interface for Kibana

Then enter into `localhost:5601` and create a new index for pattern
`logs-*` follow the steps in the UI, now you should be able to see at discovery screen your logs.

# Configure App to log to Logstash

First you need to create a configuration for your logs you can see a sample on the following file.
```
src
└── main
     └── resources
            └── log-config.xml
```

The logstash configuration inside this file means that it will publish the activity of your logs to
on `localhost:4560` also there you can configure different things like indexes or custom fields to 
identify your logs on the console.

Now start your spring boot application by running the following command

```
gradle bootRun
```

## Send requests to your app from Postman
Inside `src/main/resources` a file named **postman_collection.json** is placed, is a configuration
that can be loaded on postman and contains some examples for the requests.

## Monitor activity with Kibana

Go to Kibana UI `http://localhost:5601/` click on **Discovery** if you don't have a pattern configured 
use **logstash*** and then click again on Dicovery, now you are going to be able to see your activity, 
you can select from the left panel the fields to show.

![Kibana Screenshot](https://github.com/Joxebus/SpringBootELK/blob/master/src/main/resources/screenshots/logstash03.png?raw=true)

## Requirements

- Docker
- Gradle 8.0+
- Postman  7.0.9+

# Technologies

- Groovy 4.0+
- Java 17
- Spring 6.1.4
- Spring Boot 3.2.3
- Spock 2.4-M1-groovy-2.4
- Spock Spring 2.4-M1-groovy-2.4

## Run tests

For MacOS and Linux

`` ./gradlew test ``  

or for Windows

`` gradlew.bat test``

To see the test results you can open in your browser the **index.html** created under.

```
build
|_reports
  |_tests
    |_test
      |_index.html
```

