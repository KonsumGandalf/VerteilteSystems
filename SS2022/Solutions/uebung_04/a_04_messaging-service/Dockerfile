FROM openjdk:10-jre
MAINTAINER daniel.jobst@oth-regensburg.de
RUN mkdir /var/opt/messaging
WORKDIR /var/opt/messaging
ADD messaging-service-1.0-jar-with-dependencies.jar /var/opt/responder/messaging-service.jar
EXPOSE 1213
CMD ["java", "-jar", "messaging-service.jar"]


#docker build --tag=messaging-service --rm=true .
#docker run -itd --restart=unless-stopped -p 1213:1213 --name=messaging-service messaging-service