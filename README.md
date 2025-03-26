# Powerlifting Records API

Esta API permite o registro e gerenciamento de **lifters** e seus **personal records (PRs)**. A aplicação é construída com **Spring Boot** e permite as seguintes operações:

- **Cadastrar lifters**
- **Listar todos os lifters**
- **Cadastrar PRs para um lifter**
- **Listar PRs de um lifter**
- **Deletar PRs de um lifter**

## Endpoints

### 1. Lifter

#### **POST** /lifter
Cria um novo lifter.

- **Requisição**:
  - Método HTTP: **POST**
  - URL: `/lifter`
  - Corpo da requisição (JSON):
    ```json
    {
      "name": "John Doe",
      "age": 25
    }
    ```
- **Resposta**:
  - Status: `200 OK`
  - Corpo:
    ```json
    {
      "id": 1,
      "name": "John Doe",
      "age": 25
    }
    ```

#### **GET** /lifter
Lista todos os lifters.

- **Requisição**:
  - Método HTTP: **GET**
  - URL: `/lifter`
- **Resposta**:
  - Status: `200 OK`
  - Corpo:
    ```json
    [
      {
        "id": 1,
        "name": "John Doe",
        "age": 25
      },
      {
        "id": 2,
        "name": "Jane Doe",
        "age": 28
      }
    ]
    ```

#### **GET** /lifter/{id}/prs
Lista todos os PRs de um lifter.

- **Requisição**:
  - Método HTTP: **GET**
  - URL: `/lifter/{id}/prs` (substitua `{id}` pelo ID do lifter)
- **Resposta**:
  - Status: `200 OK`
  - Corpo:
    ```json
    [
      {
        "id": 1,
        "lift": "Squat",
        "weight": 150
      },
      {
        "id": 2,
        "lift": "Deadlift",
        "weight": 180
      }
    ]
    ```

### 2. PR (Personal Record)

#### **POST** /lifter/{id}/prs
Adiciona um novo PR para um lifter.

- **Requisição**:
  - Método HTTP: **POST**
  - URL: `/lifter/{id}/prs` (substitua `{id}` pelo ID do lifter)
  - Corpo da requisição (JSON):
    ```json
    {
      "lift": "Squat",
      "weight": 160
    }
    ```
- **Resposta**:
  - Status: `200 OK`
  - Corpo:
    ```json
    {
      "id": 3,
      "lift": "Squat",
      "weight": 160
    }
    ```

#### **DELETE** /lifter/{id}/pr/{prId}
Deleta um PR de um lifter.

- **Requisição**:
  - Método HTTP: **DELETE**
  - URL: `/lifter/{id}/pr/{prId}` (substitua `{id}` pelo ID do lifter e `{prId}` pelo ID do PR a ser deletado)
- **Resposta**:
  - Status: `204 No Content`
  - Corpo: Nenhum conteúdo

---

## Como testar a API usando Postman

### 1. Criar um Lifter
- Método: **POST**
- URL: `http://localhost:8080/lifter`
- Corpo (JSON):
  ```json
  {
    "name": "John Doe",
    "age": 25
  }
