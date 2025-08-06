# Etapa 1: Construcción de la app con Maven
FROM eclipse-temurin:21-jdk-alpine AS build

# Instalar Maven
RUN apk update && apk add --no-cache maven

# Directorio de trabajo
WORKDIR /app

# Copiar el código fuente
COPY . .

# Compilar el proyecto sin ejecutar tests
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final para ejecución
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copiar el JAR compilado desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
