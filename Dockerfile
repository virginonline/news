ARG JAVA_VERSION=21
ARG GRADLE_VERSION=8.5.0

FROM gradle:${GRADLE_VERSION}-jdk${JAVA_VERSION}-alpine AS GRADLE_BUILD
COPY --chown=gradle:gradle build.gradle /app/
COPY --chown=gradle:gradle settings.gradle /app/
COPY --chown=gradle:gradle src /app/src
WORKDIR /app
RUN gradle clean bootJar --no-daemon --stacktrace

FROM bellsoft/liberica-openjre-alpine-musl:${JAVA_VERSION} AS RUNNER
WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY --from=GRADLE_BUILD app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]