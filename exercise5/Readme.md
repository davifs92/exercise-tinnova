<h1 align="center">:file_cabinet: Veiculos API</h1>

## :memo: Descrição
Esta é uma API que permite o a gestão de veículos.

## :books: Funcionalidades
* <b>Cadastro de Veículo</b>:Permite cadastrar um veículo
* <b>Buscar um véiculo</b>: Permite buscar um veículo por id
* <b>Buscar todos os veiculos</b>: Retorna todos os veículos
* <b>Buscar os veículos baseados em parãmetros</b>: Busca veículos baseados em parâmetros como ano, marca, etc
* <b>Permite atualizar veículos</b>: Permite atualizar parcialmente ou completamente um veículo
* <b>Exclui um veículo</b>: Permite deletar um veículo da base de dados

## :wrench: Tecnologias utilizadas
- Java 17;
- Spring Boot 3.1
- H2 Database
- JUnit
- Lombok
- Swagger
- Model Mapper
- Docker
- Maven

## :wrench: Pré requisitos

É necessário que tenha instalado e configurado na máquina:

- Docker
- Java 17

## :rocket: Rodando o projeto
Para rodar o repositório é necessário clonar o mesmo,  e o no diretório raiz dar o seguinte comando para iniciar o projeto:
```
./mvnw clean package spring-boot:repackage
```
Depois:
```
docker build -t vehicles/api .
```
```
docker build -t vehicles/api .
```

Apos isso a aplicação estará disponível no seguinte endereço: http://localhost:8080

## :hourglass:	Testando o projeto

A aplicação conta com o swagger-ui e todo o detalhamento dos endpoints podem ser testados e visualizados no seguinte endereço: 
http://localhost:8080/swagger-ui/index.html

<img src="https://i.ibb.co/JvK2qB5/print-swagger.jpg" alt="print-swagger" border="0">
