FROM java:8
VOLUME /toll-platform-app-store
RUN mkdir /usr/local/toll-platform-app
COPY discovery-service-1.0.0-SNAPSHOT.jar wait-for-it.sh /usr/local/toll-platform-app/
RUN ["chmod", "+x", "/usr/local/toll-platform-app/wait-for-it.sh"]
ENV JAVA_OPTS="-Xmx512M -Xss256K -Xmx512m"
WORKDIR /usr/local/toll-platform-app
CMD java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar discovery-service-1.0.0-SNAPSHOT.jar