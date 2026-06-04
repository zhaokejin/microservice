FROM openjdk:8-jre-alpine
# 作者
MAINTAINER zhaokejin

ENV TZ Asia/Shanghai
ENV BOOT_OPTIONS=""
ENV SKYWALKING_CMM=""
ENV BOOT_PROFILES = ""

VOLUME /home/docker/mapping

ADD target/spring-cloud-alibaba-provider_dockerfile_deploy-*.jar spring-cloud-alibaba-provider.jar
# 解决时间差
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime
RUN echo $TZ > /etc/timezone

ENTRYPOINT ["sh","-c","java ${SKYWALKING_CMM} -jar -Djava.security.egd=file:/dev/./urandom /spring-cloud-alibaba-provider.jar ${BOOT_OPTIONS} ${BOOT_PROFILES}"]
