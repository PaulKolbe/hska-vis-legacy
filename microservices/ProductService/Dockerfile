#FROM maven:3.8.5-openjdk-17-slim as builder
#
#COPY ./pom.xml ./pom.xml
#COPY ./src ./src
#RUN mvn clean package
#
#FROM eclipse-temurin:17-jdk-alpine
#COPY --from=builder /target/product-0.0.1-SNAPSHOT.jar /usr/local/
#
#ENTRYPOINT ["java","-jar","/usr/local/product-0.0.1-SNAPSHOT.jar"]
#EXPOSE 8080

FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod +x mvnw
RUN dos2unix mvnw
RUN ./mvnw --version
RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.hka.vslab.product.ProductApplication"]