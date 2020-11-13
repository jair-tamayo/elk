FROM adoptopenjdk:11-jre-hotspot

ENV TZ=UTC

RUN apt-get update && apt-get install -y tzdata

RUN rm -f /etc/localtime && \
    echo $TZ | tee /etc/timezone && \
    dpkg-reconfigure --frontend noninteractive tzdata

COPY docker/entrypoint.sh /opt/application/entrypoint.sh
RUN chmod +x /opt/application/entrypoint.sh

COPY docker/logstash/logstash.conf /usr/share/logstash/pipeline/logstash.conf
COPY docker/logstash/patterns /usr/share/logstash/patterns/tomcat

COPY build/libs/elk*.jar /opt/application/application.jar

ENTRYPOINT ["/opt/application/entrypoint.sh"]
