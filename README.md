Projeto Spring Boot com Swagger - API Documentada

Este projeto é uma aplicação Spring Boot configurada para expor uma API documentada com Swagger utilizando Springdoc OpenAPI.
Requisitos

Certifique-se de ter os seguintes requisitos instalados para rodar o projeto:


    Java 11 ou superior
    Maven 3.6+
    Git (opcional, mas recomendado)

Configurações
1. Clonar o repositório

Clone o repositório Git para sua máquina local:

bash

git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio

2. Configuração do banco de dados

(Opcional) Se o projeto utiliza banco de dados, configure o arquivo application.properties ou application.yml com as credenciais corretas do seu ambiente.

Exemplo de application.properties para banco de dados H2:

properties

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update

3. Construir o projeto

Utilize o Maven para baixar as dependências e construir o projeto:

bash

mvn clean install

4. Executar a aplicação

Agora você pode executar o projeto diretamente com o Maven:

bash

mvn spring-boot:run

Ou, caso prefira, execute o JAR gerado:

bash

java -jar target/seu-projeto-0.0.1-SNAPSHOT.jar

5. Acessar a API

A aplicação estará disponível no endereço: http://localhost:8080
6. Acessar a documentação Swagger

Com a aplicação em execução, você pode acessar a documentação da API via Swagger UI:

    Swagger UI: http://localhost:8080/swagger-ui.html
    Documentação OpenAPI JSON: http://localhost:8080/v3/api-docs

7. Executar Testes (Opcional)

Para rodar os testes unitários e de integração, execute o seguinte comando Maven:

bash

mvn test

Dependências principais

Este projeto inclui as seguintes dependências principais:

    Spring Boot 2.6.1
    Springdoc OpenAPI 1.6.15 para integração com Swagger
    Hibernate para ORM (Object-Relational Mapping)
    H2 para banco de dados em memória (para desenvolvimento)

Pom.xml

Aqui estão as dependências incluídas no pom.xml:

`

<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Springdoc OpenAPI para Swagger -->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-ui</artifactId>
        <version>1.6.15</version>
    </dependency>

    <!-- H2 Database (para testes locais) -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
    </dependency>

    <!-- Spring Data JPA e Hibernate -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Testes com JUnit e Spring Boot -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies> 


Dúvidas?

Se encontrar algum problema ou tiver dúvidas, fique à vontade para abrir uma issue no repositório.
