zhGloss
=======

[![Build Status](https://travis-ci.org/ageery/zhgloss.svg?branch=master)](https://travis-ci.org/ageery/zhgloss)

The [zhGloss][1] application provides both Chinese [dictionary][2] functionality, as well as batch text [gloss][3] functionality.

The Chinese word data is from [CEDICT][4].

Building
--------

In order to build the application, both the [Java 8 JDK][5] and [Maven][6] 3+ must be installed.

The application is a standard, single-module Maven project.  To build it use:

`mvn clean install`

Running
-------

In order to run the application, a UTF-8 encoded, PostgreSQL 9.4+ database must be installed.  

The connection information, including username and password, must be provided on the command-line in the `DATABASE_URL` variable using a "Heroku-style" URL (`postgres://<USERNAME>:<PASSWORD>@<SERVER>:<PORT>/<NAME>`).

The database user must be the owner of the database.

The application is built on [Spring Boot][7].  To run it use (substituting the proper database connection info):

`mvn spring-boot:run -DDATABASE_URL=postgres://postgres:password@127.0.0.1:5432/zhgloss`

The application can be accessed at the URL `http://127.0.0.1:8080/`.

The application uses the [Flyway][8] library to create and keep the the database structures up-to-date.  It does this when the application is started.

The application will initially have no word data.  The application downloads and refreshes the word data once a day.  

To force it to download and refresh the data at application startup time, add `-Dzhgloss.refreshCedictDataAtStartup=true` to the command-line.  For example:

`mvn spring-boot:run -DDATABASE_URL=postgres://postgres:password@127.0.0.1:5432/zhgloss -Dzhgloss.refreshCedictDataAtStartup=true`

[1]: http://www.zhgloss.com
[2]: http://www.zhgloss.com/dictionary
[3]: http://www.zhgloss.com/gloss
[4]: http://cc-cedict.org/wiki/
[5]: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[6]: https://maven.apache.org/
[7]: http://projects.spring.io/spring-boot/
[8]: http://flywaydb.org/