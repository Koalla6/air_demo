FROM java:11
LABEL maintainer="allaverh@ukr.net"
VOLUME /tmp
EXPOSE 8081
ADD target/air_demo-0.0.1-SNAPSHOT.jar air_demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","air_demo-0.0.1-SNAPSHOT.jar"]
#jar?