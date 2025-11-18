# 🎟️ Plataforma de Ticketing Interno

**Una solución integral de soporte empresarial**, desarrollada con todo el compromiso, pasión y profesionalismo de tres ingenieros de software que creen en el poder del código bien hecho.
Este proyecto permite gestionar solicitudes internas mediante un sistema de tickets que conecta empleados, agentes de soporte y administradores, garantizando eficiencia, trazabilidad y comunicación.

## 💡 Descripción general

La **Plataforma de Ticketing Interno** facilita la gestión de incidencias y solicitudes dentro de una organización.
Cada usuario tiene un rol con permisos y vistas específicas:

* **Empleados:** crean y consultan tickets.
* **Agentes:** gestionan y responden solicitudes.
* **Administradores:** supervisan todo el sistema y generan reportes.

El sistema está diseñado con un enfoque **seguro, escalable y moderno**, basado en buenas prácticas de arquitectura y desarrollo ágil.

---

## 🏗️ Arquitectura del sistema

```plaintext
📦 Plataforma-ticketing-interno/
├── 📁 backend/          # API REST - Spring Boot + Gradle + JWT
├── 📁 frontend/         # Interfaz - Angular
├── 📁 documentacion/    # Manuales, diagramas y recursos técnicos
├── .env.example         # Variables de entorno de ejemplo
└── README.md
```

### Flujo general

```
[Usuario] ⇄ [Frontend Angular] ⇄ [API REST Spring Boot] ⇄ [Base de datos]
```

---

## ⚙️ Tecnologías principales

### 🔹 Frontend

* **Angular** (TypeScript, HTML, CSS)
* **Bootstrap** para diseño responsivo
* **Gestión de estados y servicios** con RxJS
* **Consumo de API REST**

### 🔹 Backend

* **Spring Boot** (Java)
* **Gradle** para la gestión de dependencias
* **JWT (JSON Web Tokens)** para autenticación y autorización
* **JPA / Hibernate** para persistencia de datos
* **Base de datos relacional** (por ejemplo, PostgreSQL o MySQL)

### 🔹 Extras

* **Variables de entorno:** `.env` y `.env.example`
* **Exportación de reportes:** PDF
* **Dashboard de métricas:** para visualización administrativa

---

## 🗂️ Estructura del proyecto

El repositorio contiene tres carpetas principales:

| Carpeta            | Descripción                                                                                   |
| ------------------ | --------------------------------------------------------------------------------------------- |
| **backend/**       | Contiene la API desarrollada en **Spring Boot** con autenticación JWT y servicios REST.       |
| **frontend/**      | Contiene la aplicación **Angular**, encargada de la interfaz visual y experiencia de usuario. |
| **documentacion/** | Incluye diagramas, manuales técnicos y documentación funcional del sistema.                   |

---

## 🧰 Instalación y ejecución

### 🔧 Requisitos previos

Asegúrate de tener instaladas las siguientes herramientas:

* [Node.js](https://nodejs.org/) (v18+)
* [Angular CLI](https://angular.io/cli)
* [Java JDK 17+](https://adoptium.net/)
* [Gradle](https://gradle.org/)
* [Git](https://git-scm.com/)
* Una base de datos (PostgreSQL o MySQL)

---

### 🚀 Pasos generales

#### 1️⃣ Clonar el repositorio

```bash
git clone https://github.com/Johan-Hernandez-80/Plataforma-ticketing-interno.git
cd Plataforma-ticketing-interno
```

#### 2️⃣ Configurar variables de entorno

Cada módulo (backend y frontend) tiene su propio archivo de entorno.
Copia los archivos de ejemplo y completa tus credenciales:

```bash
cp backend/.env.example backend/.env
cp frontend/.env.example frontend/.env
```

Configura las variables según tu entorno local (base de datos, puertos, URLs, etc.).

---

### 🧩 Backend (Spring Boot)

1. Entrar a la carpeta:

   ```bash
   cd backend
   ```
2. Ejecutar el proyecto:

   ```bash
   ./gradlew bootRun
   ```
3. El backend se ejecutará en:
   🔗 `http://localhost:8080`

---

### 💻 Frontend (Angular)

1. Entrar a la carpeta:

   ```bash
   cd frontend
   ```
2. Instalar dependencias:

   ```bash
   npm install
   ```
3. Ejecutar la aplicación:

   ```bash
   ng serve
   ```
4. El frontend estará disponible en:
   🔗 `http://localhost:4200`

---

## 👥 Funcionalidades por rol

### 👔 Empleado

* Iniciar sesión y cerrar sesión
* Crear y visualizar tickets propios
* Consultar el estado de cada ticket
* Ver comentarios y actualizaciones
* Editar perfil y cambiar contraseña
* Recibir notificaciones de cambios

### 🧑‍💻 Agente

* Ver tickets asignados
* Consultar detalles y comentarios
* Escribir respuestas o avances
* Cambiar estado o prioridad
* Revisar historial de tickets atendidos
* Modificar perfil y contraseña

### 🛠️ Administrador

* Gestionar usuarios y categorías
* Reasignar tickets
* Filtrar tickets por estado, prioridad o agente
* Exportar informes en PDF
* Ver métricas en un dashboard visual
* Participar en la conversación de tickets
* Cerrar tickets y sesiones

---

## 🌟 Desarrolladores

Este proyecto fue construido con dedicación, aprendizaje y visión por tres desarrolladores que buscan crecer como ingenieros de software y aportar soluciones reales al mundo tecnológico:

| 👨‍💻 Nombre                      | Rol / Enfoque                        |
| --------------------------------- | ------------------------------------ |
| **Juan José Molano Franco**       | Desarrollo Backend / Integración API |
| **Simón David Cruz Suazo**        | Desarrollo Frontend / UX & UI        |
| **Johan Steven Hernández Torres** | Arquitectura / Coordinación Técnica  |

💬 *“Más que un proyecto, es una muestra de lo que podemos construir con pasión, disciplina y trabajo en equipo.”*

---

## 📄 Licencia

Este proyecto está bajo la licencia **MIT**.
Eres libre de usarlo, modificarlo o compartirlo, siempre dando el crédito correspondiente a los autores.

---

## 🧭 Repositorio oficial

🔗 [Plataforma Ticketing Interno – GitHub](https://github.com/Johan-Hernandez-80/Plataforma-ticketing-interno)
