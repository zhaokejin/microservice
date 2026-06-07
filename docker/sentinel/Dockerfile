FROM eclipse-temurin:8-jre-alpine

LABEL maintainer="cicoding"

# Sentinel Dashboard 版本
ENV SENTINEL_VERSION=1.8.8
ENV SERVER_PORT=8858
ENV JAVA_OPTS="-Xms256m -Xmx256m"

COPY sentinel-dashboard.jar /app/sentinel-dashboard.jar

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -Dserver.port=${SERVER_PORT} -jar /app/sentinel-dashboard.jar"]
