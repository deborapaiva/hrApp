# Sistema de Cadastro de Vagas de Emprego
![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge)

Este projeto é um **Sistema de Cadastro de Vagas de Emprego** desenvolvido com **Spring Boot** e **Thymeleaf**. 
O sistema permite que empresas registrem vagas de emprego e que candidatos visualizem, busquem e se candidatem às vagas disponíveis. 
Além disso, o sistema possui funcionalidades de **gerenciamento de usuários** com diferentes permissões: **UsuárioComum** e **UsuárioAdm**.

## Funcionalidades

- **Cadastro de Vagas**: Empresas podem registrar vagas, incluindo informações como cargo, descrição, faixa salarial e requisitos.
- **Busca de Vagas**: Candidatos podem buscar e filtrar vagas por cargo, cidade, faixa salarial e outros critérios.
- **Cadastro de Candidatos**: Candidatos podem se cadastrar no sistema e se candidatar às vagas.
- **Sistema de Login e Autenticação**: Controle de acesso utilizando login e senha, com autenticação de usuários.
- **Controle de Permissões**:
  - **UsuárioComum**: Pode se cadastrar, buscar vagas e se candidatar.
  - **UsuárioAdm**: Pode gerenciar usuários e vagas (criar, editar, excluir).
- **Validação de Dados**: Validação de campos de formulários para garantir a integridade dos dados.

## Tecnologias e Ferramentas Utilizadas

- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Thymeleaf**: Motor de template para renderização de páginas HTML.
- **Spring Data JPA**: Acesso a banco de dados com JPA (Hibernate).
- **Spring Security**: Controle de segurança e autenticação.
- **JUnit** e **Mockito**: Para testes unitários e de integração.

<div align="center">
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/192108372-f71d70ac-7ae6-4c0d-8395-51d8870c2ef0.png" alt="Git" title="Git"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/192108374-8da61ba1-99ec-41d7-80b8-fb2f7c0a4948.png" alt="GitHub" title="GitHub"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/192108890-200809d1-439c-4e23-90d3-b090cf9a4eea.png" alt="IntelliJ" title="IntelliJ"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/117201156-9a724800-adec-11eb-9a9d-3cd0f67da4bc.png" alt="Java" title="Java"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/117201470-f6d56780-adec-11eb-8f7c-e70e376cfd07.png" alt="Spring" title="Spring"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/183896128-ec99105a-ec1a-4d85-b08b-1aa1620b2046.png" alt="MySQL" title="MySQL"/></code>
</div>

## Estrutura do Projeto

- **src/main/java/com/seuempresa/vagas**:
  - **controller**: Contém os controladores responsáveis pelo gerenciamento de requisições HTTP.
  - **model**: Contém as entidades JPA para mapeamento de dados (usuários, vagas, etc.).
  - **repository**: Contém as interfaces de repositórios para interação com o banco de dados.
  - **service**: Contém as classes de serviço responsáveis pela lógica de negócios.
  - **security**: Configurações de segurança e controle de acesso.

  (EM ANDAMENTO)
- **src/main/resources**:
  - **templates**: Contém os templates Thymeleaf para as páginas HTML.
  - **static**: Contém arquivos estáticos como CSS, JavaScript e imagens.
  - **application.properties**: Arquivo de configuração do Spring Boot (banco de dados, propriedades de segurança, etc.).

## Como Rodar o Projeto

### Pré-requisitos

- **Java 17** ou superior.
- **Maven** 
- **IDE** recomendada: IntelliJ IDEA, Eclipse ou Visual Studio Code.
