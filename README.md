# 🌤️ Previsão do Tempo - Spring Boot + Redis + Frontend

Este projeto é uma aplicação simples de previsão do tempo, que permite ao usuário pesquisar a temperatura de uma cidade usando uma interface web amigável. A aplicação utiliza **Spring Boot** no backend com **Redis** para cache, e uma interface frontend com **HTML**, **CSS**, **Bootstrap** e **JavaScript**.

---

## 🚀 Tecnologias Utilizadas

### Backend

- [Spring Boot](https://spring.io/projects/spring-boot) - framework principal da aplicação
- [Redis](https://redis.io/) - cache para otimizar chamadas de API externas

### Frontend

- HTML5
- CSS3
- [Bootstrap](https://getbootstrap.com/) - para estilização rápida e responsiva
- JavaScript - requisições e interações com o backend

---

## 📚 O que eu aprendi

- Como integrar o **Redis** ao Spring Boot para melhorar a performance com cache
- Como desenvolver uma interface **frontend responsiva** com Bootstrap
- Requisições assíncronas com JavaScript (fetch/AJAX)
- Consumo de APIs REST de forma eficiente

---

## 🛠️ Como usar

### 🔁 Clonando o repositório

Você pode clonar o repositório com o Git:

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
```
Ou, se preferir:

- Acesse o repositório no GitHub
- Clique em `Code > Download ZIP`
- Extraia o conteúdo no seu computador

---

### 🧹 Requisitos

- JDK **21** ou superior
- Redis instalado e em execução (`localhost:6379`)
- Maven (ou uma IDE com suporte a projetos Spring Boot)
- Navegador web moderno (Chrome, Edge, Firefox, etc.)

**🔧 Configurações padrão:**

- Backend: porta `8080`
- Frontend: acessado diretamente via navegador (porta local padrão do arquivo HTML)

---

### ▶️ Executando a aplicação

#### 1. **Inicie o backend (Spring Boot)**

No terminal, dentro da pasta do projeto:

```bash
./mvnw spring-boot:run
```
Ou execute diretamente pela sua IDE (como IntelliJ, Eclipse ou VS Code).

#### 2. **Abra o frontend (`index.html`)**

Abra o `index.html` localizado na pasta do frontend com um navegador. Pode abrir diretamente com duplo clique ou usar uma extensão como Live Server no VS Code.

---

### 🔍 Como funciona

1. No frontend, você digita o nome de uma cidade.
2. Um request é feito para o backend Spring Boot.
3. O backend consulta a API de clima (OpenWeather).
4. Se a cidade já estiver em cache (Redis), ele retorna os dados rapidamente.
5. A cidade armazenada no Redis fica cacheada por 2h.
6. O resultado da previsão aparece na tela.

