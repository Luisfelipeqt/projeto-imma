[![Build Status](https://app.travis-ci.com/Luisfelipeqt/projeto-imma.svg?token=KAvJ8RQqgqYFscRoFUQb&branch=main)](https://app.travis-ci.com/Luisfelipeqt/projeto-imma)

# IMMA Clinic - Aplicativo para Marcação de Consultas e Exames

[Descrição breve do projeto]

Este projeto é um aplicativo para marcação de consultas e exames, bem como para o recebimento de laudos dentro do aplicativo. Foi desenvolvido usando o framework Spring Boot e está contêinerizado com Docker. O pipeline de integração contínua e entrega contínua (CI/CD) é configurado com o Travis CI.

## Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em seu ambiente de desenvolvimento:

- Java Development Kit (JDK) 17
- Docker
- Travis CI (configurado com seu repositório) 


## Configuração do Ambiente de Desenvolvimento

1. Clone este repositório:

   ```shell
   git clone git@github.com:Luisfelipeqt/projeto-imma.git


2. Acesse o diretório do projeto:
   ```shell
   - cd app

   
3. Construa a imagem Docker do projeto:
   ```shell
   docker-compose build

4. Inicie o ambiente de desenvolvimento:
   ```shell
   docker-compose up

5. O aplicativo estará disponível em http://localhost:8080.

## Pipeline de Integração Contínua e Entrega Contínua (CI/CD)
O pipeline de CI/CD é configurado com o Travis CI. Sempre que um novo commit é enviado para o repositório, o pipeline é acionado para realizar a integração contínua, construção da imagem Docker e implantação em um servidor EC2 da AWS.

## Contribuindo
Projeto privado e construido pela a equipe da Kanagawa Inc sendo todos os direitos do APP reservado.
## Licença
- Projeto não é licensiado já que é um projeto PRIVADO e reservado da empresa Kanagawa Inc




Lembre-se de substituir `seu-usuario` e `seu-repositorio` pelos seus dados reais de usuário e repositório do GitHub.


