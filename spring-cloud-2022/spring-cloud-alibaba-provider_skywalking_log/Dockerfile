FROM openjdk:8-jre-alpine
# 作者
MAINTAINER zhaokejin

ENV TZ Asia/Shanghai
ENV BOOT_OPTIONS=""
#ENV SKYWALKING_CMM="-javaagent:/skywalking/agent/skywalking-agent.jar"
ENV SKYWALKING_CMM=""
ENV BOOT_PROFILES = ""
#COPY agent /skywalking/agent

VOLUME /home/docker/mapping

ADD target/spring-cloud-alibaba-provider_skywalking_log-*.jar spring-cloud-alibaba-provider-skywalking-log.jar
# 解决时间差
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime
RUN echo $TZ > /etc/timezone

ENTRYPOINT ["sh","-c","java ${SKYWALKING_CMM} -jar -Djava.security.egd=file:/dev/./urandom /spring-cloud-alibaba-provider-skywalking-log.jar ${BOOT_OPTIONS} ${BOOT_PROFILES}"]
