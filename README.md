# LyricsFlow Backend
Este repositório contém a implementação de um sistema de aprendizado de idiomas com música, utilizando Java Spring Boot com gerenciamento de dependências via Maven.
Ele foi desenvolvido para um trabalho da disiciplina DIM0162 - Engenharia de Software, no ano de 2026.

## Estrutura do Projeto
```
lyricsflow/
 ├── client/            -> Comonicação com APIs externas
 ├── controller/        -> Recebe as requisções HTTP
 ├── service/           -> Implementa as operações do sistema e regras de negócio
 ├── repository/        -> Implementa a camada de persistência
 ├── model/             -> Tabelas do banco
 ├── dto/               -> Conversão dos dados
 └── exception/         -> Definição das excessões lançadas
```

## Arquitetura em Camadas

**[COLOCAR UMA FOTO DO DIGRAMA ATUALIZADO AQUI]**

## Como executar
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

## Testar Endpoints (Arquivos `.http`)
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

## To-do
Controle de implementação das funcionalidades do sistema:
**Ludmilla**
* [x] Gerar dados do flashcard com o Gemini.
* [x] Listar todos os flahscards de um ususário.
* [x] Consultar um flahcard isolado.
* [x] Cronograma de revisões diárias com repetição espaçada.
* [ ] Dashboard com estaísticas do progresso do usuário

**Júlia Guilhermino**
* [x] Autenticação (cadastro e login de usuários)
* [x] Listar músicas disponíveis no BD
* [x] Gerar uma atividade de preenchimento de lacunas com Gemini
* [x] Conferir gabarito da atividade
* [x] Salvar palavras aprendidas no BD como flashcads

## Autoras
* [Júlia Maria Azevedo Guilhermino](https://github.com/JuhGuilhermino) - juh.guilhermino03@gmail.com
* [Ludmilla Rorigues](https://github.com/Ludrodrigues) - ludmillarodr178@gmail.com
