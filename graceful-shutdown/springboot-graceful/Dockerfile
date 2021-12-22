FROM openjdk:8-jre
WORKDIR /app
ADD test-graceful.jar app.jar
EXPOSE 3333
ENTRYPOINT ["java","-jar"]
CMD ["app.jar"]


