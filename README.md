# MusicService
Music Service


There are two ways to run the application.

1. Using Maven:

Assuming that mvn is installed on local machine..Please execute following command.

mvn clean package spring-boot:run

2. Using Docker

assuming docker-compose and docker is installed on local machine.

docker-compose up

docker-compose down

Swagger UI
http://localhost:8080/music-service/swagger-ui/index.html?configUrl=/music-service/v3/api-docs/swagger-config

In Memory DB console:
http://localhost:8080/music-service/console/login.jsp?jsessionid=65a56a62f6803d12ca4fd2350de9fa33
Click on connect without modifying any of the UI information.