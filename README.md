# Powerlifting Records API

Esta API permite o registro e gerenciamento de **lifters** e seus **personal records (PRs)**. A aplicação é construída com **Spring Boot** e permite as seguintes operações:
Para adicionar as informações é necessário PostgreeSQL

- **Cadastrar lifters**
- **Listar todos os lifters**
- **Atualizar o weight**
- **Cadastrar PRs para um lifter**
- **Listar PRs de um lifter**
- **Deletar PRs de um lifter**

## Endpoints

### 1. Lifter

#### **POST** /lifter
Cria um novo lifter.

- **Requisição**:
  - Método HTTP: **POST**
  - URL: `/auth/register`
  - Corpo da requisição (JSON):
    ```json
    {
      "name": "João",
      "password": "1234"
    }
    ```

#### **POST** /lifter
Login de lifter.

- **Requisição**:
  - Método HTTP: **POST**
  - URL: `/auth/login`
  - Corpo da requisição (JSON):
    ```json
    {
      "name": "João",
      "password": "1234"
    }
    ```

#### **GET** /lifter
Lista todos os lifters.

- **Requisição**:
  - Método HTTP: **GET**
  - URL: `/lifter`
- **Resposta**:
  - Corpo:
    ```json
    [
        {
            "id": 1,
            "name": "Vitor",
            "password": "16"
        },
        {
            "id": 2,
            "name": "João",
            "password": "1234"
        },
    
    ]
    ```

#### **PATCH** /lifter/{idLifter}
Altera o weight do lifter

- **Requisição**:
  - Método HTTP: **PATCH**
  - URL: `/lifter`
- **Resposta**:
  - Corpo:
    ```json
    68.4
    ```

#### **GET** /lifter/{id}/prs
Lista todos os PRs de um lifter.

- **Requisição**:
  - Método HTTP: **GET**
  - URL: `/lifter/{lifterId}/prs` (substitua `{lifterId}` pelo ID do lifter)
- **Resposta**:
  - Corpo:
    ```json
    [
      {
          "id": 2,
          "exercise": "BENCHPRESS",
          "kg": 130.0
      },
      {
          "id": 3,
          "exercise": "BENCHPRESS",
          "kg": 130.0
      }
    ]
    ```

### 2. PR (Personal Record)

#### **POST** /lifter/{id}/prs
Adiciona um novo PR para um lifter.

- **Requisição**:
  - Método HTTP: **POST**
  - URL: `/lifter/{lifterId}/prs` (substitua `{lifterId}` pelo ID do lifter)
  - Corpo da requisição (JSON):
    ```json
    {
      "exercise": "BENCHPRESS",
      "kg": 130.0
    }
    ```
- **Resposta**:
  PR adicionado com sucesso.

#### **PUT** /lifter/{lifterId}/prs/{prId}
Atualizar um PR

- **Requisição**:
  - Método HTTP: **PUT**
  - URL: `/lifter/{lifterId}/prs/{prId}` 
  - Corpo da req (JSON):
  ```json
    {
      "exercise": "BENCHPRESS",
      "kg": 180
    }
  ```
- **Resposta**:
  - Status: `204 No Content`

---

#### **DELETE** /lifter/{id}/pr/{prId}
Deleta um PR de um lifter.

- **Requisição**:
  - Método HTTP: **DELETE**
  - URL: `/lifter/{id}/pr/{prId}` (substitua `{id}` pelo ID do lifter e `{prId}` pelo ID do PR a ser deletado)
- **Resposta**:
  - Status: `204 No Content`

---
