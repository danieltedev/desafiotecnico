
# Aplicação Spring Boot com Docker, MySQL e WireMock

---

## Descrição do Projeto

Este projeto é uma aplicação construída com **Spring Boot**, que consome dados de um endpoint simulado por **WireMock** e armazena logs de consulta em um banco de dados **MySQL**. Toda a aplicação é configurada para rodar em containers **Docker**, facilitando o processo de deploy e gerenciamento.

## Fluxo de Dados

1. O cliente faz uma requisição para o endpoint exposto pela aplicação Spring Boot.
2. A aplicação Spring Boot faz uma chamada para o serviço WireMock, que simula uma API de CEP.
3. Os dados retornados pelo WireMock são exibidos ao usuário.
4. Um log da consulta é salvo no banco de dados MySQL.

O diagrama do fluxo de dados pode ser visualizado no link:  
[Diagrama de Fluxo - draw.io](https://viewer.diagrams.net/?tags=%7B%7D&lightbox=1&highlight=0000ff&edit=_blank&layers=1&nav=1&title=Diagrama%20sem%20nome.drawio#Uhttps%3A%2F%2Fraw.githubusercontent.com%2Fdanieltedev%2Fdesafiotecnico%2Fmain%2FDiagrama%2520sem%2520nome.drawio)

---

## Tecnologias Utilizadas

- **Spring Boot**: Framework para construção da aplicação.
- **WireMock**: Simulação da API externa de CEP.
- **MySQL**: Banco de dados para armazenamento de logs.
- **Docker**: Contêineres para simplificar a execução da aplicação.
- **Docker Compose**: Orquestração dos contêineres.

---

## Pré-requisitos

- Docker e Docker Compose instalados na máquina.
- JDK 17 ou superior para desenvolvimento local (opcional).

---

## Como Executar o Projeto

1. **Clone o repositório**:
   ```bash
   git clone [desafiotecnico](https://github.com/danieltedev/desafiotecnico.git)
   cd desafiotecnico
   ```

2. **Crie o arquivo `docker-compose.yml` como o exmeplo baixo**:
   ```yaml
   services:
    db:
      image: mysql:8.0
      environment:
        MYSQL_ROOT_PASSWORD: senha123
        MYSQL_DATABASE: dtedatabase
      ports:
        - "3306:3306"
      volumes:
        - db_data:/var/lib/mysql
    wiremock:
      image: wiremock/wiremock:latest
      ports:
        - "8080:8080"
      volumes:
        - ./mappings:/home/wiremock/mappings
    myapp:
      build: .
      ports:
        - "8081:8081"
      depends_on:
        - db
        - wiremock
      environment:
        SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/dtedatabase
        SPRING_DATASOURCE_USERNAME: root
        SPRING_DATASOURCE_PASSWORD: senha123
        SPRING_JPA_HIBERNATE_DDL-AUTO: update
        SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL: true
        APP_API_CEP_URL: http://wiremock:8080
        SERVER_PORT: 8081

    volumes:
      db_data:
   ```

3. **Monte os arquivos necessários**:
   - **Dockerfile** para a aplicação Spring Boot:
     ```dockerfile
     FROM openjdk:17-jdk-alpine
     FROM eclipse-temurin:17-jdk-alpine
     RUN apk add --no-cache maven
     WORKDIR /app
 
     COPY pom.xml pom.xml
     RUN mvn dependency:go-offline -X
 
     COPY src/ src/
     RUN mvn package -DskipTests
 
     EXPOSE 8081
 
     CMD ["java", "-jar", "target/busca-cep-0.0.1-SNAPSHOT.jar"]
     ```

4. **Inicie os contêineres**:
   ```bash
   docker-compose up --build
   ```

5. **Acesse a aplicação**:
   - A aplicação Spring Boot estará disponível em: `http://localhost:8081`
   - O WireMock responderá em: `http://localhost:8080`

---

## Endpoints da Aplicação

- **GET /consulta/{cep}**  
  Faz a consulta ao WireMock e retorna os dados do CEP.

---

## Estrutura do Projeto

```plaintext
src/
├── main/
│   ├── java/
│   │   └── com.example
│   │       ├── client/             # Client da API CEP
│   │       ├── config/             # Configuração do RestTemplate
│   │       ├── controller/         # Controladores da API
│   │       ├── service/            # Regras de negócio
│   │       └── repository/         # Persistência de dados
│   └── resources/
│       ├── application.properties  # Configurações da aplicação
├── test/                           # Testes automatizados
docker-compose.yml
Dockerfile
```

---

## Personalizações

- **WireMock Stubs**: Arquivos de configuração do WireMock podem ser adicionados em `./mappings`.
- **Configurações do MySQL**: Ajuste as credenciais no `docker-compose.yml` e no `application.properties`.

---

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
