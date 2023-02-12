FROM openjdk:17
ADD ./target/kameleoon.jar kameleoon.jar
ENTRYPOINT ["java", "-jar", "kameleoon.jar"]