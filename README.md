# LyricsFlow Backend
Este projeto foi desenvolvido para um trabalho da disiciplina DIM0162 - Engenharia de Software, no ano de 2026. O obejtivo é csontruir um sistema de aprendizado de idiomas com música.

#### Tecnologias
* Nome - Versão

## Arquitetura em Camadas
O projeto está organizado da seguinte forma:
* `controller/` -> requisições http, convete DTO e chama `bussiness`.
* `business/` -> implementa as regras de negócio e chama `service`.
* `service` -> integração com serviços externos, repositórios, coordenação das operções so sistema e chama `repository`.
* `repository` -> genrencia a persistência dos dados através da comunicação direta com o banco.

**[COLOCAR UMA FOTO DO DIGRAMA ATUALIZADO AQUI]**

## Estrutura do Projeto
```
lyricsflow/
 ├── controller/        -> Recebe as requisções HTTP
 ├── business/          -> Implementa as regras de negócio
 ├── service/           -> Implementa as operações do sistema
 ├── repository/        -> Implementa a camada de persistência
 ├── model/             -> Tabelas do banco
 ├── dto/               -> Conversão dos dados
 └── exception/         -> Definição das excessões lançadas em business
```

## To-do
**Ludmilla**
* [ ] Implementar *Flashcards*
    * [ ] Consulta isolada a um flashcard
    * [ ] Sistema de repetição espaçada
* [ ] Implementar *Deshboard do Usuário*

**Júlia Guilhermino**
* [x] Integração com banco de dados
* [x] População do banco de dados
* [x] Implementar *Cadastro*
* [x] Implementar *Login*
* [x] Criar tarefas do usuário
* [ ] Implementar *Buscar uma atividade*
* [ ] Implementar *Tarefa*
* [ ] Implementar *Tarefa de Diagnóstico*

## Autoras
* [Júlia Maria Azevedo Guilhermino]() (matrícula)
* [Ludmilla Rorigues]() (matrícula)
* [Raquel Cavalcante]() (matrícula)
