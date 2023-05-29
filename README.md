[![Build Status](https://app.travis-ci.com/Luisfelipeqt/projeto-imma.svg?token=KAvJ8RQqgqYFscRoFUQb&branch=main)](https://app.travis-ci.com/Luisfelipeqt/projeto-imma)


# Projeto Imma - Aplicativo para Marcação de Consultas e Exames

Tecnologias utilizadas:

- ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
- ![Typescript](https://img.shields.io/badge/TypeScript-007ACC?style=for-the-badge&logo=typescript&logoColor=white)
- ![React Native](https://img.shields.io/badge/React_Native-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)
- ![Mysql](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
- ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
- ![Travis CI/CD](https://img.shields.io/badge/Travis-E4D766?style=for-the-badge&logo=travis&logoColor=white)
- ![AWS EC2](https://img.shields.io/badge/Amazon_AWS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white)
- ![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
- ![Linux](https://img.shields.io/badge/Linux-E34F26?style=for-the-badge&logo=linux&logoColor=black)


### [Visão do projeto abaixo]

<img src="app/src/main/resources/templates/imagens/Imagem do WhatsApp de 2023-05-29 à(s) 20.00.08.jpg" width="400px;" heightalt="Foto do Mark Zuckerberg"/><br>


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

5. O aplicativo estará disponível em http://localhost:8080/swagger-ui/index.html#/

## Pipeline de Integração Contínua e Entrega Contínua (CI/CD)
O pipeline de CI/CD é configurado com o Travis CI. Sempre que um novo commit é enviado para o repositório, o pipeline é acionado para realizar a integração contínua, construção da imagem Docker e implantação em um servidor EC2 da AWS.

## Contribuindo
Projeto privado e construido pela a equipe da Kanagawa Inc sendo todos os direitos do APP reservado.
## Licença
- Projeto não é licensiado já que é um projeto PRIVADO e reservado da empresa Kanagawa Inc




Lembre-se de substituir `seu-usuario` e `seu-repositorio` pelos seus dados reais de usuário e repositório do GitHub.

## 🤝 Colaboradores

Agradecemos às seguintes pessoas que contribuíram para este projeto:

<table>
  <tr>
    <td align="center">
      <a href="#">
        <img src="app/src/main/resources/templates/imagens/1669141836631.jpeg" width="100;" alt="Foto do Iuri Silva no GitHub"/><br>
        <sub>
          <b>Luis Felipe Rodrigues</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="#">
        <img src="app/src/main/resources/templates/imagens/Marcos.jpg" width="100px;" alt="Foto do Mark Zuckerberg"/><br>
        <sub>
          <b>Marcos Kanagawa</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="#">
        <img src="app/src/main/resources/templates/imagens/Victor.jpg" width="100px;" alt="Foto do Steve Jobs"/><br>
        <sub>
          <b>Victor Gabriel</b>
        </sub>
      </a>
    </td>
<td align="center">
      <a href="#">
        <img src="app/src/main/resources/templates/imagens/Marcelo.jpg" width="100px;" alt="Foto do Steve Jobs"/><br>
        <sub>
          <b>Marcelo</b>
        </sub>
      </a>
    </td>
  </tr>
</table>


