# CQRS demo with Spring Boot, Spring Cloud Stream, Kafka, Redis, Mysql
by Zhigang Pi

---

### Overview

```
mvn clean package

sudo docker-compose build

sudo docker-compose up
```

-- trigger App Downloaded Event
curl -X GET -i 'http://localhost:8090/api/v3/event/download?userId=1024&appId=1'

-- get the result from cache
curl -X GET -i 'http://localhost:8092/api/v3/cache/downloads/count/app/1'


-- get the result from storage
curl -X GET -i 'http://localhost:8091/api/v3/downloads/'
curl -X GET -i 'http://localhost:8091/api/v3/downloads/app/1'
curl -X GET -i 'http://localhost:8091/api/v3/downloads/user/1024'
curl -X GET -i 'http://localhost:8091/api/v3/downloads/count/app/1'
curl -X GET -i 'http://localhost:8091/api/v3/downloads/count/user/1024'



-- test
--- rest client 
http://localhost:8083/api/v2/downloads

{
  "appId": 1,
  "downloadCount": 1
}

http://localhost:8083/api/v2/downloads/count/1


--- curl
curl -X POST -H 'Content-Type: application/json' -i http://localhost:8083/api/v2/downloads --data '{
  "appId": 1,
  "downloadCount": 1
}'

curl -X GET -i http://localhost:8083/api/v2/downloads/count/1 --data '{
  "appId": 1
}'