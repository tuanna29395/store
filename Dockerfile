From openjdk:8
copy ./target/app-web-0.0.1-SNAPSHOT.jar app-web-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","app-web-0.0.1-SNAPSHOT.jar"]