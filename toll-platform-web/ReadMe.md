The toll-platoform-web console which consumes the downstream services(fast-pass-service & toll-rate-service) to display online toll details.

#NOTE : Prefer to use bootstrap.yml for spring cloud project
bootstrap.yml is loaded before application.yml.

It is typically used for the following:

when using Spring Cloud Config Server, you should specify spring.application.name and spring.cloud.config.server.git.uri inside bootstrap.yml
some encryption/decryption information
Technically, bootstrap.yml is loaded by a parent Spring ApplicationContext. That parent ApplicationContext is loaded before the one that uses application.yml.

#DOCKER
Run the docker image:
docker run -d --hostname toll-platform-web --name toll-platform-web  8eea9b0a87ad<Container ID>
SSH to the container:
docker exec -it <Container ID> /bin/bash