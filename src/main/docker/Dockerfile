FROM openjdk:8-jre-alpine

EXPOSE 8080

COPY assets/ /

CMD ["/usr/bin/java", \
     "-jar", \
     "/usr/issue-manager/issue-manager.jar", \
     "--spring.config.location=/etc/issue-manager/config/application.properties"]