FROM java:8
VOLUME /toll-platform-app-store
RUN mkdir /usr/local/toll-platform-app
WORKDIR /usr/local/toll-platform-app
COPY edge-service.jar .
ENV JAVA_OPTS="-Xmx512M -Xss256K -Xmx512m"
CMD java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar edge-service.jar