#Git Based config Server
#Asymmetric Encryption/decrption
Generate a keystore:
keytool -genkeypair -alias config-server-key -keyalg RSA -keysize 4096 -sigalg SHA512withRSA -dname "CN=Config Server,OU=Spring Cloud,O=Toll System" -keypass my-k34-s3cr3t -keystore config-server.jks -storepass my-k34-s3cr3t -validity 365

Run Jar with command line arguments:
java -jar -Xmx1024M -Xms1024M  config-service.jar --SPRING_PROFILES_ACTIVE=production --KEYSTORE_PASSWORD=my-k34-s3cr3t --KEYSTORE_SECRET=my-k34-s3cr3t --RABBITMQ_HOST=192.168.99.100 --RABBITMQ_PORT=5672 --RABBITMQ_USERNAME=guest --RABBITMQ_PASSWORD=guest --EUREKA_DEFAULT_ZONE=http://localhost:8761/eureka/


#NOTE : Prefer to use bootstrap.yml for spring cloud project
bootstrap.yml is loaded before application.yml.

It is typically used for the following:

when using Spring Cloud Config Server, you should specify spring.application.name and spring.cloud.config.server.git.uri inside bootstrap.yml
Technically, bootstrap.yml is loaded by a parent Spring ApplicationContext. That parent ApplicationContext is loaded before the one that uses application.yml.

#Refresh services with latest property changes
curl http://configServerUser:GbVx74Gp6cmEQnUcyNQKcCVu5NUfpx5K@localhost:9090/config-server/monitor -d path="*"
This would refresh all the config client services to see the updated value of the message property.
 
 #NOTE: More Scalable solution is : Push notification by registering a git web hook, where we would not even need to explicitly call /refresh endpoint
 
#DOCKER
Run the docker image by assigning static IP:
docker run --hostname toll-platform-config-service -p 9090:9090 <image-id>
SSH to the container:
docker exec -it <Container ID> /bin/bash
 