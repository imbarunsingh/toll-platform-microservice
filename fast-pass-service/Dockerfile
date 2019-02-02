FROM java:8
VOLUME /toll-platform-app-store
RUN mkdir /usr/local/toll-platform-app
WORKDIR /usr/local/toll-platform-app
COPY fast-pass-service-1.0.0-SNAPSHOT.jar .
ENV JAVA_OPTS="-Xmx512M -Xss256K -Xmx512m"
CMD java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar fast-pass-service-1.0.0-SNAPSHOT.jar 