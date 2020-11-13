# Application logging and monitoring

Sample project to demonstrate the ability to analyze application logs by using the searching/aggregation/visualization capabilities bundled into [elastic stack](https://www.elastic.co).

### Requirements

- Docker
- Docker compose
- Java 11
- Make utility (optional)

### Building

If you have make utility installed, run:

```
$ make
```

If you don't, run:

```
$ ./gradlew clean build
$ docker-compose up -d --build
```

This will bring up the elastic stack as well as the sample application.

In order to entirely shutdown the stack (and remove all persisted data), you can either run:

```
$ make stop
```

Or,

```
$ docker-compose down --volume
```

### Host setup

By default, the services expose the following ports:

- 8080: Sample application
- 9200: Elasticsearch
- 9600: Logstash
- 5601: Kibana

### Initial setup

Kibana will take about a minute to initialize. You may need to configure the index pattern before being able to use its tools.

Refer to [Create an index pattern](https://www.elastic.co/guide/en/kibana/7.x/index-patterns.html#index-patterns) for detailed instructions about the index pattern configuration.

### Enjoy!

- Refer to [API docs](http://localhost:8080/swagger-ui/) for details about how to make api calls  
- Access the kibana web UI by opening [http://localhost:5601](http://localhost:5601) in a web browser
