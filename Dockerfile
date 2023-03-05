FROM openjdk:16
ADD target/pop-starter-0.0.1-SNAPSHOT.jar pop-starter-0.0.1-SNAPSHOT.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=local-docker" ,"pop-starter-0.0.1-SNAPSHOT.jar"]