FROM gradle:7.5.0-jdk17-alpine AS build
COPY --chown=gradle:gradle . /src
WORKDIR /src
RUN gradle distTar

FROM eclipse-temurin:17-jdk-alpine AS run
RUN mkdir /app
WORKDIR /app
COPY --from=build /src/build/distributions/layla.tar /app/layla.tar
RUN tar xfv /app/layla.tar
ENTRYPOINT ["sh", "/app/layla/bin/layla"]