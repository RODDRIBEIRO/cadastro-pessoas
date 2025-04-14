# 🧑‍💼 Cadastro de Pessoas

Aplicação Java completa com API REST para cadastro, consulta, atualização e remoção de pessoas. Inclui autenticação HTTP Basic, documentação Swagger, versões da API, validação de CPF, e deploy via Docker.

---

## 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot 3.4.x
- Spring Web
- Spring Security
- Spring Validation
- Lombok
- H2 Database
- Swagger (Springdoc OpenAPI 3)
- Docker

---

## 📦 Como executar o projeto

### 1. Clonar e entrar no projeto

```bash
git clone https://github.com/roddribeiro/cadastro-pessoas.git
cd cadastro-pessoas
```

### 2. Rodar com Maven

```bash
./mvnw spring-boot:run
```

> A aplicação estará disponível em:  
> [http://localhost:8080](http://localhost:8080)

---

## 🔐 Autenticação

Toda a aplicação é protegida por **HTTP Basic Auth**.

**Usuário padrão:**

- `admin`
- `senha123`

Ao acessar `localhost:8080`, será exibido um popup de autenticação do navegador. Após autenticar, a tela de cadastro será carregada automaticamente.

---

## 🧾 Documentação Swagger

Disponível em:

```
http://localhost:8080/swagger-ui/index.html
```

OpenAPI JSON:

```
http://localhost:8080/v3/api-docs
```

---

## 🔁 API - Versões

- **v1**: Dados pessoais básicos
- **v2**: Inclui campo de endereço (obrigatório)

**Exemplos de endpoints:**

- `GET /api/v1/pessoas`
- `POST /api/v1/pessoas`
- `GET /api/v2/pessoas`
- `POST /api/v2/pessoas`

---

## 🧪 Testes

O projeto possui testes unitários.

---

## 🐳 Docker

### 1. Build da imagem Docker

```bash
docker build -t cadastro-pessoas .
```

### 2. Executar o container

```bash
docker run -p 8080:8080 cadastro-pessoas
```

> O sistema estará acessível em `http://localhost:8080`

Imagem disponível em: https://hub.docker.com/r/waterplz/cadastro-pessoas

---

## 🌐 Endpoint público `/source`

Endpoint sem autenticação que retorna a URL do repositório no GitHub com o código-fonte:

```
GET /source
```

```json
{
  "url": "https://github.com/roddribeiro/cadastro-pessoas"
}
```

---

## 📂 Estrutura de diretórios

```
src/
├── main/
│   ├── java/
│   │   └── com.example.cadastro/
│   │       ├── controller/
│   │       ├── dto/
│   │       ├── entity/
│   │       ├── mapper/
│   │       ├── repository/
│   │       ├── service/
│   │       ├── config/
│   │       └── CadastroPessoasApplication.java
│   └── resources/
│       ├── templates/        # tela HTML de cadastro (via Thymeleaf)
│       ├── application.properties
│       └── static/
└── test/
```

---

## 📄 Licença

Este projeto é open source e está disponível sob a licença MIT.
