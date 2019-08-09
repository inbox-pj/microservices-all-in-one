# Spring Boot Microservices
Springboot Microservices along with Admin Server, Config Service, Discovery Service, Hystrix, Zuul Gateway, Zipkin, Kafka, Redis with mysql

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

# Components!
 * Spring boot Microservices for Shopping Cart applications enabling Swagger UI
 * Configuration Management with Spring Cloud Config
 * Service registration and discovery
 -- Client Side Load Balancing
 -- Replication DS
 * Client Resiliency
 -- Cercuit Breaker
 -- Fallbacks
 -- Bulkheads
 * Service Routing
 -- Juul Gateway
 * Distributed Caching using Redis
 * Distributed Tracing and Log collector using Sluth and Zipkin
 * Exception Handing
 * Admin Server
 * Custom/Standard Zipkin Server
 * Hystrix Dashboard

### URL to monitor
* [Swagger UI] -		http://<host>:<port>/swagger-ui.html
* [Hystrix Dashboard] -	http://<host>:<port>/hystrix
* [Hystrix Monitor] -	http://<host>:<port>/actuator/hystrix.stream
* [Health] -			http://<host>:<port>/actuator/health
* [Refresh] -			http://<host>:<port>/actuator/refresh
* [Info] -				http://<host>:<port>/actuator/info
* [Metrics] -			http://<host>:<port>/actuator/metrics
* [Beans] -				http://<host>:<port>/actuator/beans
* [Trace] -				http://<host>:<port>/actuator/trace
* [Zuul Route] -		http://<host>:<port>/actuator/routes

### Configurations
* Ribbon Timeout
```sh
ribbonTimeout = (ribbon.ConnectTimeout + ribbon.ReadTimeout) * (ribbon.MaxAutoRetries + 1) * (ribbon.MaxAutoRetriesNextServer + 1);
if(hystrixTimeout < ribbonTimeout) {
    LOGGER.warn("The Hystrix timeout of " + hystrixTimeout + "ms for the command " + commandKey +
        " is set lower than the combination of the Ribbon read and connect timeout, " + ribbonTimeout + "ms.");
}

MaxAutoRetries = default:0
MaxAutoRetriesNextServer = default:1
ribbon.ConnectTimeout – The connection timeout in milliseconds. Defaults to 1000,
zuul.host.connect-timeout-millis – The connection timeout in milliseconds. Defaults to 2000,
zuul.host.socket-timeout-millis – The socket timeout in millis. Defaults to 10000.
zuul.host.max-per-route-connections – The maximum number of connections that can be used by a single route. Default: 20
zuul.host.max-total-connections – The maximum number of total connections the proxy can hold open to backends. Default: 200.
zuul.host.time-to-live – The lifetime for the connection pool. Default: -1.
```

# To Register with Eureka (spring-cloud-starter-netflix-eureka-client)
```sh
#eureka:
#  instance:
#    prefer-ip-address: true
#  client:
#    fetch-registry: true
#    register-with-eureka: true
#    serviceUrl: 
#      defaultZone: ${DISCOVERY_SERVER_URL:http://localhost:8200}/eureka
```
# To Register with Admin Server (spring-boot-admin-starter-client)
```sh
#  boot:
#    admin:
#      client:
#        url: ${ADMIN_SERVER_URL:http://localhost:8300}
#        instance:
#          prefer-ip: true
```
### MYSQL Server
https://www.digitalocean.com/community/tutorials/how-to-create-a-new-user-and-grant-permissions-in-mysql
```sh
mysql -u [username] -p
```

# Kafka/Zookeeper
```sh
cd /opt/Kafka-zkServer/kafka_2.11/bin
sh zookeeper-server-start.sh ../config/zookeeper.properties
sh kafka-server-start.sh ../config/server.properties
```

### Redis Server
```sh
/opt/redis-5.0.5/src
redis-server
```

### Redis Server (external software only)
```sh
https://github.com/openzipkin/zipkin/tree/master/zipkin-server
```

### Port Configurations
* Config Service - [8100]
* Discovery Service - [8200] - <http://localhost:8200/>
* Admin Server - [8300] - <http://localhost:8300/#/wallboard>
* Hystrix Dashboard - [8400] - <http://localhost:8400/hystrix/>
* Zuul Gateway - [8500]
-- Centralized Swagger UI - <http://localhost:8500/swagger-ui.html>
* Histrix Stream Monitor - <http://localhost:9100/actuator/hystrix.stream>
* Zipkin Server - [9411] - <http://localhost:9411/zipkin>
* Pricing Service - [8600]
* Catelog Service - [8700]
* Payment Validation - [8800]
* Payment Processingg - [8900]
* Cart Processing - [9100]

### Todos
The repository doesn't include below topics related to Microservices. Will be part of seperate repository:
 - CQRS
 - Spring Security
 - Transaction Management
 - Docker

### Reference

https://spring.io/guides/gs/service-registration-and-discovery/
https://spring.io/guides/gs/accessing-data-jpa/
https://spring.io/guides/gs/accessing-data-mysql/
https://spring.io/guides/gs/routing-and-filtering/
https://spring.io/guides/gs/client-side-load-balancing/
https://spring.io/guides/gs/multi-module/
https://spring.io/guides/gs/gateway/
https://spring.io/guides/gs/integration/
https://spring.io/guides/gs/messaging-redis/
https://spring.io/guides/gs/messaging-rabbitmq/
https://spring.io/guides/gs/messaging-jms/
https://spring.io/guides/gs/actuator-service/
https://spring.io/guides/gs/managing-transactions/
https://spring.io/guides/gs/async-method/
https://spring.io/guides/gs/spring-boot-docker/
https://spring.io/guides/gs/centralized-configuration/
https://spring.io/guides/gs/circuit-breaker/
https://spring.io/projects/spring-cloud-dataflow
https://spring.io/projects/spring-cloud
https://spring.io/projects/spring-security
https://spring.io/projects/spring-ldap
https://spring.io/projects/spring-cloud-sleuth


**Free Software, Enjoy!**

