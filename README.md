# LyricsFlow
Este repositório contém a implementação de um sistema de aprendizado de idiomas com música, utilizando Java Spring Boot com gerenciamento de dependências via Maven.
Ele foi desenvolvido para um trabalho da disiciplina DIM0162 - Engenharia de Software, no ano de 2026.

## Backend
### Estrutura do Projeto
```
src/main/java/com/example/application1
 ├── client/            -> Comonicação com APIs externas
 ├── controller/        -> Recebe as requisções HTTP
 ├── service/           -> Implementa as operações do sistema e regras de negócio
 ├── repository/        -> Implementa a camada de persistência
 ├── model/             -> Tabelas do banco
 ├── dto/               -> Conversão dos dados
 └── exception/         -> Definição das excessões lançadas
```
### Diagrama de Classe UML

### Como executar
As instruções abaixo são para o sistema operacional Windows.
#### Pré-requsitos
* Java JDK 17 ou superior.
* PostgreSQL ativo (com o banco de dados do projeto criado)
* Uma chave de API do Gemini configurada nas suas variáveis de ambiente.
#### Compilação e Execução
Apra o terminal na raiz do projeto (que contém o arquivo `pom.xml`) e utize os seguintes comandos:
```powershell
# Limpar cache antigo e compilar o código do zero
.\mvnw clean compile

# Informar a chave da API do Gemini antes de rodar a aplicação
$env:GENAI_API_KEY="SUA-CHAVE”

# Rodar aplicação: http://localhost:8080
.\mvnw spring-boot:run
```

### Testar Endpoints (Arquivos `.http`)
Instale a extensão REST Client(da Huachao Mao) no VS Code.
A pasta `tests`, na raiz do projeto, contém os arquivos `.http` no formato baixo:
```h
@baseUrl = http://localhost:8080/auth

### CADASTRO: sucesso
POST {{baseUrl}}/register
Content-Type: application/json

{
  "username": "dev_musica",
  "email": "teste@lyricsflow.com",
  "password": "SenhaSegura123",
  "level": "BEGINNER"
}
```
Uma linha escrita Send Request aparecerá logo acima de cada método (POST, GET, etc.).Basta clicar em Send Request para disparar a requisição. O retorno do servidor (Status Code, JSON de resposta e tempo de execução) será exibido em uma janela dividida logo ao lado no seu editor.

## Frontend
### Estrutura do Projeto
### Diagrama de Classe UML
### Como executar
#### Pré-requisitos
#### Compilação e execução

## To-do
* [ ] Dashboard com estaísticas do progresso do usuário
* [ ] Buscar músicas que não estão no banco em uma API externa.

## Autoras
* [Júlia Maria Azevedo Guilhermino](https://github.com/JuhGuilhermino) - juh.guilhermino03@gmail.com
* [Ludmilla Rorigues](https://github.com/Ludrodrigues) - ludmillarodr178@gmail.com
