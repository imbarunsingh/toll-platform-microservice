#Edge Service
Zuul acts as an API gateway or Edge service. 
It receives all the requests coming from the UI and then delegates the requests to internal microservices. So, we have to create a brand new microservice which is Zuul-enabled, and this service sits on top of all other microservices.