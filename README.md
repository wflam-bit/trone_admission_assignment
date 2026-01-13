# 🍽️ Dish Management App
Assignment  for applying to Trone Software Company.

A full-stack web application for managing dishes, built with **Vue 3** (frontend), **Spring Boot** (backend), and **PostgreSQL** (database), running via **Docker Compose**. 🐳

---

## 📋 Table of Contents

* [✨ Features](#features)
* [🛠️ Tech Stack](#tech-stack)
* [📂 Project Structure](#project-structure)
* [⚙️ Prerequisites](#prerequisites)
* [🚀 Getting Started](#getting-started)
* [👨‍💻 Usage](#usage)
* [🔗 API Endpoints](#api-endpoints)
* [✅ Form Validation](#form-validation)
* [🌐 CORS Setup](#cors-setup)
* [📝 Lessons Learned](#lessons-learned)
* [🛠 Troubleshooting](#troubleshooting)

---

## ✨ Features

* Create, read, update, delete (CRUD) dishes 🍲
* Dish attributes: name, country, kind (WARM/COLD/NORMAL), prep time, vegetarian 🥗
* Reactive form validation ✅
* Automatic timestamp handling (`creation` and `update`) ⏱️
* Dockerized development and production setup 🐳

---

## 🛠️ Tech Stack

* **Frontend:** Vue 3, Tailwind CSS, Vite 🌐
* **Backend:** Spring Boot, Spring Data JPA, Hibernate ⚡
* **Database:** PostgreSQL 🐘
* **Containerization:** Docker, Docker Compose 🐳

---

## 📂 Project Structure

```
project-root/
│
├─ frontend/        # Vue 3 frontend 🌐
│  ├─ src/
│  │  ├─ components/ # DishForm.vue && DishOverview.vue
│  │  └─ main.js
│  └─ package.json
│
├─ backend/         # Spring Boot backend ⚡
│  ├─ src/main/java/nl/hva/wf/be/
│  │  ├─ rest/       # WebConfig, controllers
│  │  ├─ model/      # Dish entity
│  │  └─ repository/
│  └─ build.gradle
│
├─ docker-compose.yml 🐳
└─ README.md 📄
```

---

## ⚙️ Prerequisites

* Docker & Docker Compose installed 🐳
* Java 17 (for local backend development) ☕
* Node.js 20+ (for local frontend development) 🟢

---

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone <repo-url>
cd project-root
```

### 2. Build and run with Docker Compose

```bash
docker-compose up --build
```

This will start three containers:

* **Frontend:** accessible at `http://localhost` 🌐
* **Backend:** accessible at `http://localhost:8080` ⚡
* **PostgreSQL:** accessible at `localhost:5432` 🐘

---

### 3. Environment Variables

Backend uses the following environment variables:

```yaml
SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/dish
SPRING_DATASOURCE_USERNAME: postgress
SPRING_DATASOURCE_PASSWORD: admin
SPRING_JPA_HIBERNATE_DDL_AUTO: update
```

Frontend can switch between development (`http://localhost:5173`) and production (`http://localhost`) URLs using environment variables if needed. 🌐

---

## 👨‍💻 Usage

* Open the frontend in your browser at `http://localhost` 🌐
* Add, edit, delete dishes using the form ✏️
* Errors are shown inline if validation fails: ⚠️

    * **Name:** required
    * **Kind:** required
    * **Prep time:** required, must be ≥ 0

---

## 🔗 API Endpoints

| Method | Endpoint              | Description             |
| ------ | --------------------- | ----------------------- |
| GET    | `/api/v1/dishes`      | Get all dishes 🍲       |
| GET    | `/api/v1/dishes/{id}` | Get dish by ID 🔍       |
| POST   | `/api/v1/dishes`      | Create new dish ➕       |
| PUT    | `/api/v1/dishes/{id}` | Update existing dish ✏️ |
| DELETE | `/api/v1/dishes/{id}` | Delete a dish ❌         |

---

## ✅ Form Validation

* Reactive validation using Vue 3 `reactive` + `watch`
* Errors displayed below each input ⚠️
* Prevents submission if any field is invalid 🚫

---

## 🌐 CORS Setup

Backend configures CORS to allow requests from the frontend:

```java
registry.addMapping("/**")
        .allowedOrigins(frontendUrl)
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        .allowedHeaders("*")
        .allowCredentials(true);
```

* The frontend URL is passed via environment variable `FRONTEND_URL`
* Default fallback: `http://localhost:5173` 🌐

---

## 📝 Lessons Learned

* Originally, the preferred tech stack was **ASP.NET** or **PHP** with VueJS. ⏳

    * ASP.NET (C#) was unfamiliar in syntax, and the IDE was difficult to use with a different frontend framework than Spring Boot.
    * PHP experience was outdated (last used v7.1), so I avoided it.
* Back-end exception handling to the front-end was not implemented due to misreading requirements ❌.
* Back-end bonus feature for **Reviews**: the class entity exists but the endpoints weren't implemented. Ideally, it would be a `OneToMany` relation with dishes.
* Tailwind CSS was new for me; the implementation is basic, but it was a personal challenge to avoid Bootstrap 🎨.
* Using **Docker** ensures PostgreSQL works across environments and prevents "It works on my machine" issues 🐳.
* Learned how to configure CORS properly in Spring Boot 🌐
* Handling timestamp columns in PostgreSQL required manual `USING` casting ⏱️
* Docker networking between frontend and backend can be tricky 🐳
* Form validation in Vue 3 with reactive state is powerful but needs careful error handling ✅
* Managing environment variables for dev vs prod requires careful planning 🔧
* Learned to use `reactive` + `watch` in Vue to sync props with local state 🔄

---

## 🛠 Troubleshooting

* **Port conflicts:** Ensure `8080` and `80` are free or adjust Docker ports ⚠️
* **Database errors:** Check PostgreSQL container logs, ensure `POSTGRES_USER` and `POSTGRES_PASSWORD` match 🐘
* **CORS errors:** Make sure `FRONTEND_URL` matches the origin of your frontend requests 🌐

---

## 📝 License

MIT License © lamwf
