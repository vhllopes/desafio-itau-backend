# Desafio de Programação Backend - Itaú Unibanco

![Java](https://img.shields.io/badge/Java-17%2B-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)
![Status](https://img.shields.io/badge/Status-Concluído-success.svg)

API RESTful desenvolvida como solução para o Desafio de Programação Backend do Itaú. O projeto consiste em um serviço para receber transações financeiras e retornar estatísticas em tempo real, com todos os dados sendo processados e armazenados em memória.

---

## Objetivo do Desafio

A missão é construir uma API REST que lida com transações financeiras, seguindo um conjunto de regras e restrições técnicas específicas. O foco está na qualidade do código, organização do projeto, e na correta implementação dos endpoints definidos.

### Restrições Técnicas

Este projeto foi desenvolvido seguindo estritamente as restrições do desafio:
* **Armazenamento em Memória:** Nenhum sistema de banco de dados (como H2, MySQL) ou cache externo (como Redis) foi utilizado. Todos os dados das transações são mantidos em uma estrutura de dados na memória da aplicação.
* **Endpoints Específicos:** A API implementa os endpoints `POST /transacao`, `DELETE /transacao` e `GET /estatistica` exatamente como especificado.
* **Formato JSON:** A API aceita e responde exclusivamente com objetos no formato JSON.
* **Histórico de Commits:** O desenvolvimento seguiu as boas práticas de versionamento, com commits lógicos para cada etapa do desenvolvimento.

## Tecnologias Utilizadas

* **Java 17+**: Linguagem de programação principal.
* **Spring Boot**: Framework para a construção da API.
* **Spring Web**: Para a criação dos endpoints RESTful.
* **Maven**: Gerenciador de dependências e build do projeto.
* **Lombok**: Para reduzir o código boilerplate (getters, setters, etc.).
* **ConcurrentLinkedQueue**: Estrutura de dados thread-safe para armazenar as transações em memória de forma eficiente e segura.

## Como Executar o Projeto

Siga os passos abaixo para executar a aplicação localmente.

### Pré-requisitos
* **Java JDK 17** ou superior instalado.
* **Maven** instalado e configurado no seu sistema.

### Passos
1.  **Clone o repositório:**
    ```bash
    git clone URL_DO_REPOSITORIO.git
    cd desafio-itau-backend
    ```

2.  **Execute a aplicação:**
    ```bash
    mvn spring-boot:run
    ```

A API estará disponível em `http://localhost:8080`.

## Endpoints da API

A seguir estão detalhados os endpoints disponíveis e como interagir com eles.

### 1. Adicionar uma Transação

Este endpoint recebe e valida uma nova transação.

* **URL:** `/transacao`
* **Método:** `POST`
* **Corpo da Requisição (Body - JSON):**
    ```json
    {
        "valor": 123.45,
        "dataHora": "2025-09-04T10:30:00.123-03:00"
    }
    ```
* **Critérios de Aceite:**
    * `valor` deve ser um número não negativo (>= 0).
    * `dataHora` deve ser uma data no passado (não pode ser no futuro).

* **Respostas Possíveis:**
    * `201 Created`: A transação é válida e foi registrada com sucesso (sem corpo na resposta).
    * `422 Unprocessable Entity`: A transação é inválida (ex: valor negativo ou data no futuro) (sem corpo na resposta).
    * `400 Bad Request`: O JSON enviado é inválido ou mal formatado.

### 2. Obter Estatísticas

Este endpoint retorna as estatísticas das transações ocorridas nos últimos 60 segundos.

* **URL:** `/estatistica`
* **Método:** `GET`

* **Resposta de Sucesso (200 OK):**
    ```json
    {
        "count": 10,
        "sum": 1234.56,
        "avg": 123.456,
        "min": 12.34,
        "max": 123.56
    }
    ```
    * **Atenção:** Se não houver transações nos últimos 60 segundos, todos os valores serão retornados como `0`.

### 3. Limpar Todas as Transações

Este endpoint apaga todas as transações armazenadas em memória.

* **URL:** `/transacao`
* **Método:** `DELETE`

* **Resposta de Sucesso:**
    * `200 OK`: Todas as transações foram removidas com sucesso (sem corpo na resposta).

---

## Autor

Feito por **Vítor Lopes**.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/vhllopes)
[![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/vhllopes)
