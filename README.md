[![Build Status](https://app.travis-ci.com/Luisfelipeqt/projeto-imma.svg?token=KAvJ8RQqgqYFscRoFUQb&branch=main)](https://app.travis-ci.com/Luisfelipeqt/projeto-imma)

# IMMA Clinic - Aplicativo para Marcação de Consultas e Exames

[Descrição breve do projeto]

Este projeto é um aplicativo para marcação de consultas e exames, bem como para o recebimento de laudos dentro do aplicativo. Foi desenvolvido usando o framework Spring Boot e está contêinerizado com Docker. O pipeline de integração contínua e entrega contínua (CI/CD) é configurado com o Travis CI.

## Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em seu ambiente de desenvolvimento:

- Java Development Kit (JDK) 11
- Docker
- Travis CI (configurado com seu repositório)

## Configuração do Ambiente de Desenvolvimento

1. Clone este repositório:

   ```shell
   git clone https://github.com/seu-usuario/seu-repositorio.git
   
   
2. Acesse o diretório do projeto:

cd app

3. Construa a imagem Docker do projeto:

docker-compose build


4. Inicie o ambiente de desenvolvimento:

docker-compose up


5. O aplicativo estará disponível em http://localhost:8080.

## Pipeline de Integração Contínua e Entrega Contínua (CI/CD)
O pipeline de CI/CD é configurado com o Travis CI. Sempre que um novo commit é enviado para o repositório, o pipeline é acionado para realizar a integração contínua, construção da imagem Docker e implantação em um servidor Heroku.

## Contribuindo
Sinta-se à vontade para contribuir para este projeto. Você pode fazer fork deste repositório, criar uma branch com sua implementação e enviar um pull request.

## Licença
Este projeto é licenciado sob a MIT License.
   
   
   
   
   
Lembre-se de substituir `seu-usuario` e `seu-repositorio` pelos seus dados reais de usuário e repositório do GitHub.


