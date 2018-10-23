# **Solution**
This repo contains a JAX-RS compliant API in Java that solves the Sample Problem (outlined [below]((#sample-problem))).

## Documentation
The API is documented via [Swagger](./swagger.yaml) and [Postman](https://www.getpostman.com/collections/7992fe51d76d7e73ce92).
* The Postman collection includes `Accept` headers to illustrate the endpoint generating JSON and XML. 
* The Gradle build script will generate the client code from swagger.yaml when the project is built.

### curl commands
In case the postman link doesn't work, these two commands illustrate the api:

     curl -X GET \
      'http://localhost:8080/pricing/park?start=2018-10-20T10:00:00Z&end=2018-10-20T13:00:00Z' \
      -H 'Accepts: application/xml,application/json' ```
      
    curl -X POST \
      'http://localhost:8080/pricing/park?start=2018-10-20T10:00:00Z&end=2018-10-20T13:00:00Z' \
      -H 'Accept: application/xml,application/xml' \
      -H 'Content-Type: application/json' \      
      -d '{
        "rates": [
            {
                "days": "mon,tues,thurs",
                "times": "0900-2100",
                "price": 1500
            },
            {
                "days": "fri,sat,sun",
                "times": "0900-2100",
                "price": 2500
            },
            {
                "days": "wed",
                "times": "0600-1800",
                "price": 1750
            },
            {
                "days": "mon,wed,sat",
                "times": "0100-0500",
                "price": 1000
            },
            {
                "days": "sun,tues",
                "times": "0100-0700",
                "price": 925
            }
        ]
    }'

## Design
The API has a single endpoint, `park` that handles both `GET` and a `POST`.  The `GET` flavor will use the sample rates 
included below.  The `POST` flavor takes in the rates to use in calulating the rate to be charged.  My thinking behind 
this design (this API could not be publicly accessible since anyone could pass in rates of $0) is 
this would be a stateless microservice. There would be a client service that would retrieve the rates (based on the city, for example)
and pass those along to this service with the start & end times to calculate the rate. 
 
## Testing Strategy
Unit tests cover model classes (which are Lombok data classes with a bit of business logic to convert strings to enums or
filter collections) and the class that calculates the parking rate (`ParkingComputer`).  There is an integration test 
that tests how the rate calculation work with all the classes working together (`ParkingRateIntegrationTest`).
Finally, there is `ApiIntegrationTest` with launches an in-memory app instance and exercises the API (leveraging `JerseyTest`)


## Running the API
The following command will launch the server:
```./gradlew run```
The service will be listen on port 8080 - this is not configurable, but could be by supporting a command line
argument to `Main`.

## Deploying the API to a cloud service
The app can be deployed in a jax-rs compliant containter.  I ran in to [an issue](https://stackoverflow.com/questions/46604406/when-jodatime-v-2-5-library-included-into-war-file-deployment-fails) with deploying to
a standalone Glassfish 5 container and then a [different issue](https://bugs.eclipse.org/bugs/show_bug.cgi?id=463169) when deploying to Glassfish 4 and then 
ran out of waking hours.

# **Sample Problem**


------------

#### Build an API that allows a user to enter a date time range and get back the rate at which they would be charged to park for that time span.

## Requirements

* Publish a repo on Github that i can clone and run on my local machine
* Use Java, Scala, Kotlin or Clojure to complete this.
   - Don’t use Spring Boot, Dropwizard or Scalatra to do this, but feel free to use the libraries employed by those frameworks (eg JAX-RS, Jersey, Metrics, Jackson, Akka, Jetty, etc)
* API will need documentation and a contract published.  
* It should support JSON & XML over HTTP
* I should be able to curl against an API that computes a price for a specified datetime range given a JSON file of rates. 
* Rates will not span multiple days
* User input can span more than one day, but it just won't return a valid rate
* The user specifies input date/times as ISO-8601, but there is no corresponding time zone information for the configured
rates.  Assume that the rates are for a location with a timezone offset of 0, the rates configuration model need not specify a timezone offset.

Staff Engineer requirments
* Bonus points for Protocol Buffers
* Integration tests need to be in place. 
* I should also be able to publish that app to AWS/Azure/Google Cloud very simply.  
* Metrics for endpoint(s) captured and available to be queried via an endpoint  (eg average response time)



Sample file: 
```json
    {
      "rates": [
        {
          "days": "mon,tues,wed,thurs,fri",
          "times": "0600-1800",
          "price": 1500
        },
        {
          "days": "sat,sun",
          "times": "0600-2000",
          "price": 2000
        }
      ]
    }
```
     
 

Sample result:
Datetime ranges should be specified in isoformat.  A rate must completely encapsulate a datetime range for it to be available.

Rates will never overlap.
```
2015-07-01T07:00:00Z to 2015-07-01T12:00:00Z should yield 1500
2015-07-04T07:00:00Z to 2015-07-04T12:00:00Z should yield 2000
2015-07-04T07:00:00Z to 2015-07-04T20:00:00Z should yield unavailable
```
 
Sample JSON for testing
```json
{
    "rates": [
        {
            "days": "mon,tues,thurs",
            "times": "0900-2100",
            "price": 1500
        },
        {
            "days": "fri,sat,sun",
            "times": "0900-2100",
            "price": 2000
        },
        {
            "days": "wed",
            "times": "0600-1800",
            "price": 1750
        },
        {
            "days": "mon,wed,sat",
            "times": "0100-0500",
            "price": 1000
        },
        {
            "days": "sun,tues",
            "times": "0100-0700",
            "price": 925
        }
    ]
}

```
