# microservice-samples
Microservices sample project

To run the project:

copy the appliction-config folder to c:\Users{username}\ on Windows or /Users/{username}/ on *nix. Then open a git bash terminal in application-config and run:
git init
git add .
git commit -m "First commit"
start the config server
start the discovery server
start all the other servers in any order

Fetches Service Config from Git: https://github.com/imbarunsingh/toll-platform-application-config.git

#Docker Set Up:

First run the project dependency set up:
---------------------------------------
docker pull rabbitmq:3-management
docker run -d --hostname toll-platform-rabbitmq --name spring-cloud-bus-rabbitmq -p 15672:15672 -p 5672:5672 rabbitmq:3-management

Update the windows host file entry with hostname as mentioned above and ip of docker container for RabbitMQ container.
e.g:
172.17.0.2 toll-platform-rabbit-mq

Run the Microservice in the order mentioned:
--------------------------------------------
docker run -d --hostname toll-platform-config-service --name config-service -p 9090:9090 bsingh10/config-service
docker run --hostname toll-platform-discovery-service --name discovery-service -p 9091:9091 bsingh10/discovery-service

Repeat the host file entry for config-service and discovery-service since we are using the hostname in .properties file for all the  microservices connection.e.g:
# Added for Docker communication for toll-platform microservice set upfor Windows
172.17.0.3 toll-platform-config-service
172.17.0.4 toll-platform-discovery-service
# End of section

docker run -d --hostname toll-platform-edge-service --name edge-service -p 8080:8080 bsingh10/edge-service
docker run -d --hostname toll-platform-hystrix-dashboard-service --name hystrix-dashboard-service -p 9092:9092 bsingh10/hystrix-dashboard-service
docker run -d --hostname toll-platform-hystrix-turbine-service --name hystrix-turbine-service -p 9093:9093 bsingh10/hystrix-turbine-service

docker run -d --hostname toll-platform-toll-rate-service --name toll-rate-service bsingh10/toll-rate-service
docker run -d --hostname toll-platform-web-service --name toll-platform-web bsingh10/toll-platform-web
docker run -d --hostname toll-platform-fast-pass-service --name fast-pass-service bsingh10/fast-pass-service

NOTE: In a docker set up run on windows machine, access the services listed on Eureka Dashboard by replacing the ip with localhost because that ip is resolved to localhost by docker.


#Docker Compose:
To run this application using docker compose , follow the below order:

Create a new docker network:
---------------------------
docker network create -d bridge toll-platform-network

Run The Docker Compose :
------------------------
1.docker-compose -f docker-compose-rabbitmq.yml up   -> To bring up rabbitmq instance

2. Set the Environment Variable that the compose file would need.
export SNAPSHOT_RELEASE_VERSION=1.0.0-SNAPSHOT (on GitBash)
docker-compose -f docker-compose-toll-platform-microservice.yml up  ->To run all the microservices
