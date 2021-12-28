FROM maven:3.6.3-jdk-11

ADD out/artifacts/currency_alpha_jar/*.jar alpha_task.jar

EXPOSE 8000

CMD ["java", "-jar", "currency_alpha.jar"]