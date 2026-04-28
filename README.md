# 📦 DimDim - Migração para Docker (Checkpoint 2)

## 📌 Sobre o Projeto

Este projeto tem como objetivo demonstrar a migração de um ambiente de desenvolvimento para containers Docker, conforme proposto no Checkpoint 2 da disciplina de DevOps Tools & Cloud Computing.

A solução consiste em uma API desenvolvida em Java (Spring Boot) integrada a um banco de dados PostgreSQL, ambos executando em containers Docker e se comunicando através de uma rede interna.

---

## 🧱 Arquitetura da Solução

* **Container 1:** API Java (Spring Boot)
* **Container 2:** Banco de Dados PostgreSQL
* **Rede Docker:** Comunicação entre os containers
* **Volume Nomeado:** Persistência dos dados do banco

---

## ⚙️ Tecnologias Utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Docker

---

## 🚀 Funcionalidades (CRUD)

A API implementa operações básicas em uma entidade `User`:

* **Create:** Criar usuário
* **Read:** Listar usuários
* **Update:** Atualizar usuário
* **Delete:** Remover usuário

---

## 🐳 Execução com Docker

### 1. Criar rede Docker

```bash
docker network create dimdim-net
```

---

### 2. Criar volume para o banco

```bash
docker volume create dimdim-db-volume
```

---

### 3. Subir container PostgreSQL

```bash
docker run -d \
--name postgres-RMXXXXXX \
--network dimdim-net \
-e POSTGRES_PASSWORD=1234 \
-e POSTGRES_DB=dimdim \
-v dimdim-db-volume:/var/lib/postgresql/data \
-p 5432:5432 \
postgres:15
```

---

### 4. Build da API

```bash
mvn clean package
docker build -t dimdim-api .
```

---

### 5. Subir container da API

```bash
docker run -d \
--name api-RMXXXXXX \
--network dimdim-net \
-e DATABASE_URL=jdbc:postgresql://postgres-RMXXXXXX:5432/dimdim \
-e DATABASE_USERNAME=postgres \
-e DATABASE_PASSWORD=1234 \
-p 8080:8080 \
dimdim-api
```

---

## 🔌 Variáveis de Ambiente

A API utiliza variáveis de ambiente para configuração do banco:

* `DATABASE_URL`
* `DATABASE_USERNAME`
* `DATABASE_PASSWORD`

Configuradas no `application.properties` via:

```properties
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
```

---

## 🌐 Acesso à API

Após execução:

```
http://localhost:8080/users
```

---

## 🧪 Testes (CRUD)

Exemplo usando Postman:

* **POST /users** → cria usuário
* **GET /users** → lista usuários
* **PUT /users/{id}** → atualiza usuário
* **DELETE /users/{id}** → remove usuário

---

## 📊 Evidências Docker

Comandos utilizados para validação:

```bash
docker ps
docker image ls
docker volume ls
docker network ls
```

---

## 📁 Estrutura do Projeto

```
/src
/target
Dockerfile
pom.xml
README.md
```

---

## 🎥 Entrega

* Vídeo demonstrando:

  * Containers em execução
  * CRUD funcionando
  * Persistência no banco
  * Link: https://www.youtube.com/watch?v=Pff56hA-Wv8

* Arquivo PDF contendo:

  * Integrantes (nome + RM)
  * Prints dos comandos Docker
  * Link do GitHub
  * Link do vídeo

---

## ⚠️ Observações

* Os containers rodam em segundo plano (`-d`)
* Comunicação ocorre via rede Docker
* Banco possui volume para persistência
* Nome dos containers inclui o RM conforme exigido

---

## 👥 Integrantes

* Lucas Grillo Alcântara - RM561413
* Pietro Ferreira Gomes Abrahamian - RM561469
* Pedro Peres Benitez - RM561792
* Lucca Ramos Mussumecci - RM562027

---