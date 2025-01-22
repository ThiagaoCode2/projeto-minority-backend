# Etapa 1: Build da aplicação
FROM maven:3.8.8-eclipse-temurin-17 as builder
WORKDIR /app

# Copia o código-fonte e o arquivo pom.xml
COPY pom.xml .
COPY src ./src

# Executa o build do Maven
RUN mvn clean package -DskipTests

# Etapa 2: Configuração do ambiente de execução
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copia o .jar gerado da etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Define o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]