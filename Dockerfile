FROM openjdk:8
ADD target/maven-simplest-3.5.1-fat.jar maven-simplest-3.5.1-fat.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","maven-simplest-3.5.1-fat.jar"]