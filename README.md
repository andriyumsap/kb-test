# Technologies Used

1. Spring Boot - 2.1.8.RELEASE
2. Togglz - 2.6.1.Final
3. h2 database
4. JDK 12
5. Maven

## Installation

Make sure the laptop / PC has been installed [maven](https://maven.apache.org/install.html) & [docker](https://docs.docker.com/get-docker/)

Go to the project folder and run the command

```bash
# build / generate jar with running test
mvn clean install

# build image
docker build -t kb-test:latest .

# create container
docker container create --name kb-test -p 8080:8080 kb-test

# start container
docker container start kb-test

# stop container
docker container stop kb-test
```

# Swagger
```bash
http://localhost:8080/swagger-ui.html
```

# Vendor Notification
There are two vendors

default vendor one is active

To make changes notification vendor configuration, please access the following link
```bash
http://localhost:8080/togglz-console
```
