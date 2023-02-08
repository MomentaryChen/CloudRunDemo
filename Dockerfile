FROM maven:3.6.3-openjdk-8 as builder

COPY . /usr/src/app

RUN echo \
    "<settings xmlns='http://maven.apache.org/SETTINGS/1.0.0\' \
    xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' \
    xsi:schemaLocation='http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd'> \
        <localRepository>/home/a0909210396/.m2/repository</localRepository> \
        <interactiveMode>true</interactiveMode> \
        <usePluginRegistry>false</usePluginRegistry> \
        <offline>false</offline> \
    </settings>" \
    > /usr/share/maven/conf/settings.xml;

WORKDIR /usr/src/app

# no re-download

VOLUME /home/a0909210396/.m2/repository /home/a0909210396/.m2/repository
RUN mvn clean package -Dmaven.repo.local=./.m2 -DskipTests


FROM openjdk:8-jdk-alpine

COPY --from=builder /usr/src/app/target/CloudRunDemo-0.0.1-SNAPSHOT.jar /app/CloudRunDemo.jar
COPY ~/.postgresql/client-cert.pem ~/.postgresql/client-key.pk8 /usr/.postgresql/

EXPOSE 8080

CMD ["java", "-jar", "/app/CloudRunDemo.jar"]
