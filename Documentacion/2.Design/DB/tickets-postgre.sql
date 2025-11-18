-- Crear la base de datos (ejecutar fuera de la conexión a la DB)
CREATE DATABASE ticketing;

-- Conectarse a la base de datos 'ticketing' en psql:
-- \c ticketing

-- Tablas

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE permisos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE permisos_roles (
    rol_id INT NOT NULL,
    permiso_id INT NOT NULL,
    PRIMARY KEY (rol_id, permiso_id),
    CONSTRAINT fk_permisosroles_roles
        FOREIGN KEY (rol_id) REFERENCES roles(id),
    CONSTRAINT fk_permisosroles_permisos
        FOREIGN KEY (permiso_id) REFERENCES permisos(id)
);

CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    rol_id INT NOT NULL REFERENCES roles(id),
    nombre VARCHAR(255) NOT NULL,
    email_personal VARCHAR(255),
    email_corporativo VARCHAR(255) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    departamento VARCHAR(255) NOT NULL
);

CREATE TABLE categorias (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE tickets (
    id SERIAL PRIMARY KEY,
    usuario_id INT NOT NULL REFERENCES usuarios(id),
    categoria_id INT NOT NULL REFERENCES categorias(id),
    agente_id INT REFERENCES usuarios(id),
    titulo VARCHAR(255) NOT NULL,
    descripcion VARCHAR(1000) NOT NULL,
    prioridad VARCHAR(255) NOT NULL CHECK (prioridad IN ('Urgente','Importante','Programado')),
    estado VARCHAR(255) NOT NULL CHECK (estado IN ('En progreso','Pendiente','Cerrado')),
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_cierre TIMESTAMP
);

CREATE TABLE comentarios (
    id SERIAL PRIMARY KEY,
    ticket_id INT NOT NULL REFERENCES tickets(id),
    usuario_id INT NOT NULL REFERENCES usuarios(id),
    comentario VARCHAR(1000) NOT NULL,
    nombre_usuario VARCHAR(255) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE historial_tickets (
    id SERIAL PRIMARY KEY,
    ticket_id INT NOT NULL REFERENCES tickets(id),
    estado_anterior VARCHAR(255) NOT NULL CHECK (estado_anterior IN ('En progreso','Pendiente','Cerrado')),
    estado_nuevo VARCHAR(255) NOT NULL CHECK (estado_nuevo IN ('En progreso','Pendiente','Cerrado')),
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE notificaciones (
    id SERIAL PRIMARY KEY,
    usuario_id INT NOT NULL REFERENCES usuarios(id),
    mensaje VARCHAR(255) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
