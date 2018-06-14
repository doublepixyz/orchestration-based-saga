FROM java:8
LABEL maintainer=“pizhigang@huawei.com”
VOLUME /tmp
EXPOSE 8080
ADD target/booking-car-0.0.1-SNAPSHOT.jar booking-car-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","booking-car-0.0.1-SNAPSHOT.jar"]