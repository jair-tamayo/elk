run: stop build-application build-docker start

build-application:
	./gradlew clean build

build-docker:
	docker-compose build

start:
	docker-compose up -d

stop:
	docker-compose down --volumes

post-data:
	while true; do \
		curl -s -X 'GET' 'http://localhost:8080/sample/auto?amount=100' -H 'accept: */*' > /dev/null; \
		curl -s -X 'GET' 'http://localhost:8080/sample/auto?amount=100' -H 'accept: */*' > /dev/null; \
		curl -s -X 'GET' 'http://localhost:8080/sample/auto?amount=100' -H 'accept: */*' > /dev/null; \
		curl -s -X 'GET' 'http://localhost:8080/sample/mortgage?amount=300' -H 'accept: */*' > /dev/null; \
		curl -s -X 'GET' 'http://localhost:8080/sample/mortgage?amount=300' -H 'accept: */*' > /dev/null; \
    	curl -s -X 'GET' 'http://localhost:8080/sample/student?amount=25' -H 'accept: */*' > /dev/null; \
    	curl -s -X 'GET' 'http://localhost:8080/sample/unknown?amount=0' -H 'accept: */*' > /dev/null; \
    	curl -s -X 'GET' 'http://localhost:8080/sample/credit-card?amount=0' -H 'accept: */*' -H 'X-Correlation: 394bdb4a1f00c4ea2174615c19b9b6d35da5d887' > /dev/null; \
    	curl -s -X 'GET' 'http://localhost:8080/sample/credit-card?amount=0' -H 'accept: */*' -H 'X-Correlation: 394bdb4a1f00c4ea2174615c19b9b6d35da5d887' > /dev/null; \
    	curl -s -X 'GET' 'http://localhost:8080/sample/credit-card?amount=0' -H 'accept: */*' -H 'X-Correlation: 394bdb4a1f00c4ea2174615c19b9b6d35da5d887' > /dev/null; \
    	curl -s -X 'GET' 'http://localhost:8080/sample/credit-card?amount=0' -H 'accept: */*' -H 'X-Correlation: 394bdb4a1f00c4ea2174615c19b9b6d35da5d887' > /dev/null; \
    	curl -s -X 'GET' 'http://localhost:8080/sample/credit-card?amount=0' -H 'accept: */*' -H 'X-Correlation: 394bdb4a1f00c4ea2174615c19b9b6d35da5d887' > /dev/null; \
    	curl -s -X 'POST' 'http://localhost:8080/sample/auto/request' -H 'accept: */*' -H 'Content-Type: application/json' -d '{}' > /dev/null; \
    	curl -s -X 'POST' 'http://localhost:8080/sample/student/request' -H 'accept: */*' -H 'Content-Type: application/json' -d '{}' > /dev/null; \
    	curl -s -X 'POST' 'http://localhost:8080/sample/mortgage/request' -H 'accept: */*' -H 'Content-Type: application/json' -d '{}' > /dev/null; \
    done;

