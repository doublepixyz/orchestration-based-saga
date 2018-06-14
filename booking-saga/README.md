# CQRS demo with Spring Boot, Spring Cloud Stream, Kafka, Redis, Mysql
by Zhigang Pi

---

### Overview

```
mvn clean package

sudo docker-compose build

sudo docker-compose up
```

-- trigger booked Event
curl -X POST -H 'Content-Type: application/json' -i 'http://localhost:8090/api/v1/booking/pizhigang/1/2'
