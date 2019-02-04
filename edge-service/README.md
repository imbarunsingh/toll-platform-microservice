#Edge Service
Zuul acts as an API gateway or Edge service. 
It receives all the requests coming from the UI and then delegates the requests to internal microservices. So, we have to create a brand new microservice which is Zuul-enabled, and this service sits on top of all other microservices.

#DOCKER
Run the docker image:
docker run --hostname toll-platform-edge-service --name edge-service -p 8080:8080 <Container ID>
SSH to the container:
docker exec -it <Container ID> /bin/bash