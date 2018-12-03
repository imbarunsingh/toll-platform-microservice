#Hystrix Dashboard
To run locally:

mvn install
java -jar target/hystrix-dashboard-service-0.0.1-SNAPSHOT.jar
In your browser, go to http://localhost:<port>/hystrix # port configurable in application.yml

On the home page is a form where you can enter the URL for an event stream to monitor, for example (the toll-system-web running locally): http://localhost:<port>/hystrix.stream. Any app that uses @EnableCircuitBreaker will expose the stream.

To aggregate many streams together you can use the Turbine Stream.