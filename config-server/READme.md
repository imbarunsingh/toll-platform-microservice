#Git Based config Server

#NOTE : Prefer to use bootstrap.yml for spring cloud project
bootstrap.yml is loaded before application.yml.

It is typically used for the following:

when using Spring Cloud Config Server, you should specify spring.application.name and spring.cloud.config.server.git.uri inside bootstrap.yml
Technically, bootstrap.yml is loaded by a parent Spring ApplicationContext. That parent ApplicationContext is loaded before the one that uses application.yml.

#Refresh services with latest property changes
curl http://admin:password@localhost:9090/config-server/monitor -d path="*" This would refresh all the config client services to see the updated value of the message property.
 
 #NOTE: More Scalable solution is : Push notification by registering a git web hook, where we would not even need to explicitly call /refresh endpoint