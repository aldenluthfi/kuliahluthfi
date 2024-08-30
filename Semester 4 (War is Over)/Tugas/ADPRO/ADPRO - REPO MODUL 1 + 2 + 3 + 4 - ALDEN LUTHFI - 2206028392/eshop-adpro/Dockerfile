FROM docker.io/library/eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /src/advpro
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew clean bootJar

FROM docker.io/library/eclipse-temurin:21-jre-alpine AS runner

ARG USERNAME=aldenluthfi
ARG USER_UID=1000
ARG USER_GID=${USER_UID}

RUN addgroup -g ${USER_GID} ${USERNAME} && \
    adduser -S -u ${USER_UID} -G ${USERNAME} ${USERNAME}

USER ${USERNAME}
WORKDIR /opt/advpro
EXPOSE 8000
COPY --from=builder --chown=${USER_UID}:${USER_GID} /src/advpro/build/libs/*.jar app.jar


ENTRYPOINT [ "java" ]
CMD [ "-jar", "app.jar" ]