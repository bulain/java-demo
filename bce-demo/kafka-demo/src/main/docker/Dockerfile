FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD kafka-demo.jar app.jar
ADD client.keystore.jks client.keystore.jks
ADD client.truststore.jks client.truststore.jks
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]