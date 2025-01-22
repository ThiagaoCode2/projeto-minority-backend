# Etapa 1: Build da aplicação
FROM maven:3.8.8 as builder
WORKDIR /app

# Instala o JDK 23
RUN apt-get update && \
    apt-get install -y wget && \
    wget -O /tmp/jdk23.tar.gz https://download.java.net/java/early_access/jdk23/23_linux-x64_bin.tar.gz && \
    mkdir /usr/lib/jvm && \
    tar -xvf /tmp/jdk23.tar.gz -C /usr/lib/jvm && \
    update-alternatives --install /usr/bin/java java /usr/lib/jvm/jdk-23/bin/java 1 && \
    update-alternatives --set java /usr/lib/jvm/jdk-23/bin/java && \
    rm -rf /tmp/jdk23.tar.gz

# Copia o código-fonte e o arquivo pom.xml
COPY pom.xml .
COPY src ./src

# Executa o build do Maven
RUN mvn clean package -DskipTests

# Etapa 2: Configuração do ambiente de execução
FROM debian:bullseye-slim
WORKDIR /app

# Instala o JDK 23
RUN apt-get update && \
    apt-get install -y wget && \
    wget -O /tmp/jdk23.tar.gz https://download.java.net/java/early_access/jdk23/23_linux-x64_bin.tar.gz && \
    mkdir /usr/lib/jvm && \
    tar -xvf /tmp/jdk23.tar.gz -C /usr/lib/jvm && \
    update-alternatives --install /usr/bin/java java /usr/lib/jvm/jdk-23/bin/java 1 && \
    update-alternatives --set java /usr/lib/jvm/jdk-23/bin/java && \
    rm -rf /tmp/jdk23.tar.gz

# Copia o .jar gerado da etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Define o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
