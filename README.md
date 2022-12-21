# Spring Boot Application With PostGress SQL

This is a sample blog application that I created to use as an example of how to take a Spring Boot application to production.
It's not enough to understand how to create applications, you need to understand how to take them to production. This document
will walk you through what this application is, how to run it and how to execute the tests associated with it.

## About the Application

This is a simple web application that exposes a REST API. This application uses Maven as the build tool and the current
LTS version of Java, 11. I hope to add more functionality to this application in the future but
for now this project uses the following dependencies:

- Spring Web
- Spring Data JDBC
- PostgresSQL Database

### Local Development

When working on this application locally you will need to download and install postgres and set user and password as per your wish. 
You will need to use pgadmin4 and create a DB with a name you wish. 
Once you have done all of that you need to update src/main/resources/application-dev.yaml according to your configs 

### Railway (Deploy on Web)

Bring your code, we'll handle the rest. Made for any language, for projects big and small. [Railway](https://railway.app/)
is the cloud that takes the complexity out of shipping software.

Create a new empty project in Railway and start by creating a PostgreSQL database. Once you have that created you can create
a new project from GitHub. You can use the following environment variables based on the database you just created.

```properties
spring_profiles_active=prod
PROD_DB_HOST=HOST_HERE
PROD_DB_PORT=POST_HERE
PROD_DB_NAME=railway
PROD_DB_PASSWORD=PASSWORD_HERE
PROD_DB_USERNAME=postgres
```

You don't need GitHub Actions or any type of pipeline for this setup because Railway handles this for you. Simply push your code to GitHub
and a new build and deploy will be triggered. If you want to disable this functionality you can from the settings of your project
on Railway. 
