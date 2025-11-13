USE ticketing;

-- =========================================
-- 1. USUARIOS (explicit IDs, exact enum values)
-- =========================================
INSERT INTO usuarios (id, rol_id, nombre, email_personal, email_corporativo, contrasena, departamento) VALUES
(1, 1, 'Jaime Martínez', NULL, 'admin@empresa.com', '$2a$10$XALQrXT9eqQbU3GiK674f.zHtJ2Eusuev1vQku/Bc78jDpCOA0smO', 'Administración'),
(2, 2, 'José González', 'jose.gonzalez@personal.com', 'jose.gonzalez@empresa.com', '$2a$10$CAJ8XEgQlLP3J/W9pejFPeNqBGDPxru15L5TRpKp5e6bn2/cnXmWy', 'Soporte Técnico'),
(3, 3, 'María Zambrano', 'maria.zambrano@personal.com', 'maria.zambrano@empresa.com', '$2a$10$mcFrZ.SKqpDX78UxCnfqQufiwPujp28e67TZ59TwyEgdJ73RBG4he', 'Recursos Humanos')
ON DUPLICATE KEY UPDATE id = VALUES(id);

-- =========================================
-- 2. CATEGORÍAS
-- =========================================
INSERT INTO categorias (id, nombre, descripcion) VALUES
(1, 'Soporte Técnico', 'Problemas con hardware, software, red o acceso'),
(2, 'Recursos Humanos', 'Nómina, vacaciones, contratación, políticas internas')
ON DUPLICATE KEY UPDATE id = VALUES(id);

-- =========================================
-- 3. TICKETS
-- =========================================
INSERT INTO tickets (id, usuario_id, categoria_id, agente_id, titulo, descripcion, prioridad, estado, fecha_creacion) VALUES
(1, 3, 1, 2, 'Error 403 al acceder al portal de empleados', 'Desde ayer no puedo ingresar al portal. Muestra "Acceso denegado" aunque uso las credenciales correctas.', 'Urgente', 'En progreso', '2025-04-01 09:15:00'),
(2, 3, 2, NULL, 'Solicitud de vacaciones del 15 al 20 de mayo', 'Necesito programar vacaciones. Ya hablé con mi supervisor y está aprobado.', 'Programado', 'Pendiente', '2025-04-02 14:30:00')
ON DUPLICATE KEY UPDATE id = VALUES(id);

-- =========================================
-- 4. COMENTARIOS
-- =========================================
INSERT INTO comentarios (ticket_id, usuario_id, comentario, fecha_creacion) VALUES
(1, 3, 'Ya intenté limpiar caché y usar otro navegador. Sigo con el mismo error.', '2025-04-01 09:30:00'),
(1, 2, 'Revisaré los permisos en Active Directory. Te contacto en 30 min.', '2025-04-01 10:00:00'),
(2, 3, 'Adjunto correo de aprobación de mi jefe.', '2025-04-02 14:35:00'),
(2, 1, 'Aprobado. Se registrará en el sistema de RRHH.', '2025-04-02 16:00:00')
ON DUPLICATE KEY UPDATE ticket_id = ticket_id;

-- =========================================
-- 5. HISTORIAL_TICKETS
-- =========================================
INSERT INTO historial_tickets (ticket_id, estado_anterior, estado_nuevo, fecha_creacion) VALUES
(1, 'Pendiente', 'En progreso', '2025-04-01 09:45:00'),
(2, 'Pendiente', 'Cerrado', '2025-04-02 16:05:00')
ON DUPLICATE KEY UPDATE ticket_id = ticket_id;

-- =========================================
-- 6. NOTIFICACIONES
-- =========================================
INSERT INTO notificaciones (usuario_id, mensaje, fecha_creacion) VALUES
(3, 'Tu ticket #1 ha sido tomado por el agente José González.', '2025-04-01 09:46:00'),
(3, 'Tu solicitud de vacaciones ha sido aprobada.', '2025-04-02 16:06:00'),
(2, 'Nuevo ticket asignado: "Error 403 al acceder al portal"', '2025-04-01 09:20:00'),
(1, 'Se ha cerrado el ticket de solicitud de vacaciones.', '2025-04-02 16:07:00')
ON DUPLICATE KEY UPDATE usuario_id = usuario_id;