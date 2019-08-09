Springboot Microservices along with Admin Server, Config Service, Discovery Service, Hystrix, Zuul Gateway, Zipkin, Kafka, Redis with mysql

Swagger UI: 		http://<host>:<port>/swagger-ui.html
Hystrix Dashboard:	http://<host>:<port>/hystrix
Hystrix Monitor:	http://<host>:<port>/actuator/hystrix.stream
Health:				http://<host>:<port>/actuator/health
Refresh:			http://<host>:<port>/actuator/refresh
Info:				http://<host>:<port>/actuator/info
Metrics:			http://<host>:<port>/actuator/metrics
Beans:				http://<host>:<port>/actuator/beans
Trace:				http://<host>:<port>/actuator/trace
Zuul Route:			http://<host>:<port>/actuator/routes

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



To Register with Eureka (spring-cloud-starter-netflix-eureka-client)
--------------------------------------------------------------------
#eureka:
#  instance:
#    prefer-ip-address: true
#  client:
#    fetch-registry: true
#    register-with-eureka: true
#    serviceUrl: 
#      defaultZone: ${DISCOVERY_SERVER_URL:http://localhost:8200}/eureka

To Register with Admin Server (spring-boot-admin-starter-client)
----------------------------------------------------------------
#  boot:
#    admin:
#      client:
#        url: ${ADMIN_SERVER_URL:http://localhost:8300}
#        instance:
#          prefer-ip: true


MYSQL Server:
-----------
mysql -u [username] -p

Kafka/Zookeeper
---------------
cd /opt/Kafka-zkServer/kafka_2.11/bin
sh zookeeper-server-start.sh ../config/zookeeper.properties
sh kafka-server-start.sh ../config/server.properties

Redis Server
-------------
/opt/redis-5.0.5/src
redis-server


Zipkin
---------
https://github.com/openzipkin/zipkin/tree/master/zipkin-server
