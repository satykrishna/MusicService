# * set up and configure docker image for backend server
FROM maven:latest AS backend
WORKDIR /build
COPY . .
RUN mvn -f pom.xml clean package

# * set up and configure docker image to combine front- and backend images
FROM openjdk:11-jre 
VOLUME /tmp

RUN groupadd --system appuser
RUN useradd -g appuser -s /sbin/nologin appuser
RUN mkdir /opt/application
RUN chown -R appuser:appuser /opt/application


# Run as non-root
USER appuser

WORKDIR /opt/application
COPY --from=backend /build/target/MusicService-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080/tcp

ENV APP_HOME="/opt/application"
ENV APP_JAR="${APP_HOME}/app.jar"
RUN printf "#!/bin/bash\nexec java -jar ${APP_JAR} 2>&1" > ./start.sh

RUN chmod +x ./start.sh
RUN printenv

ENTRYPOINT ["/opt/application/start.sh"]
