run: stop build-application build-docker start

build-application:
	./gradlew clean build

build-docker:
	docker-compose build

start:
	docker-compose up -d

stop:
	docker-compose down --volume
