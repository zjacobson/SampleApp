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
2015-07-01T07:00:00Z (Wed) to 2015-07-01T12:00:00Z should yield 1500
2015-07-04T07:00:00Z (Sat) to 2015-07-04T12:00:00Z should yield 2000
2015-07-04T07:00:00Z (Sat) to 2015-07-04T20:00:00Z should yield unavailable
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
