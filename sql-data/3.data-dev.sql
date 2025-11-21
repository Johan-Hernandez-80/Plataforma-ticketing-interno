USE ticketing;

-- =========================================
-- 1. USUARIOS
-- =========================================
INSERT INTO usuarios (id, rol_id, nombre, email_personal, email_corporativo, contrasena, departamento) VALUES
(1, 1, 'Jaime Martínez', NULL, 'admin@empresa.com', '$2a$10$ig5jc/KAPqnbsQgs729ssu3cj2grVoIAclZEAYyQBWhUA7cbzU6sa', 'Administración'),
(2, 2, 'José González', 'jose.gonzalez@personal.com', 'agente@empresa.com', '$2a$10$D8/.pV32nAGkfOSUYK9g4uzlX/IKkWVIfjAA1jxuYy9Rit848zs5u', 'Soporte Técnico'),
(3, 3, 'María Zambrano', 'maria.zambrano@personal.com', 'usuario@empresa.com', '$2a$10$sUPVkeyjnG3e10m2Nejk7efrf4.8GfMU//X5ZoGy0p.p9ol4ZMk3O', 'Recursos Humanos'),
(4, 2, 'Ana López', 'ana.lopez@personal.com', 'ana.lopez@empresa.com', '$2a$10$i9n/Yy43i04hIepYm/AxSuPJnFQHosKzdJjE5/cQwdZfNLoNJJULq', 'Soporte Técnico'),
(5, 2, 'Carlos Ruiz', 'carlos.ruiz@gmail.com', 'carlos.ruiz@empresa.com', '$2a$10$fw3VNzGx5bgb7ngBX5v3AOn3zmBmLngbdplwrfZs0IdNMQm31SABC', 'Soporte Técnico'),
(6, 3, 'Laura Fernández', 'laura.fernandez@outlook.com', 'laura.fernandez@empresa.com', '$2a$10$cPRfNbo26gQVk6r9orXx5.KaCiQOqSZB6asYHZlyJsXQVbhADdd..', 'Finanzas'),
(7, 3, 'Pedro Sánchez', 'pedro.sanchez@yahoo.com', 'pedro.sanchez@empresa.com', '$2a$10$KMTe1fbRZ7zK1ErGsH1C7eIXSFgBrnLNITrRFJRfBIG/WbKu//YOO', 'Ventas'),
(8, 3, 'Sofía Torres', 'sofia.torres@personal.com', 'sofia.torres@empresa.com', '$2a$10$5WRfCb2JDrtrkLA2w9xBnu/ZKR0Q0ZUX5dkSyX2a1QmpbmrWubtbu', 'Marketing'),
(9, 3, 'Diego Morales', NULL, 'diego.morales@empresa.com', '$2a$10$3aczDXxQod17kVFX5KVQA.T9pOZnEeAnakMgVTvtE1DeE1yl.sI6a', 'Operaciones'),
(10, 3, 'Valeria Castro', 'valeria.castro@personal.com', 'valeria.castro@empresa.com', '$2a$10$ehnSknGaTMAjD3Nimic.HuCWKPODFUnrEzYNn1DAdA.JiJhCSXFDm', 'Logística'),
(11, 3, 'Miguel Ángel Herrera', 'miguel.herrera@personal.com', 'miguel.herrera@empresa.com', '$2a$10$nDkUpvZtuLRRx3SGiHc6euBLyTQ.QIr/19s16vrrslet3wkB/P5rK', 'Producción'),
(12, 3, 'Camila Ortiz', 'camila.ortiz@personal.com', 'camila.ortiz@empresa.com', '$2a$10$rH810/TH8tRRdQR62/gLW.uFjlp9WkJOKhzHxFfISHan5pK.ur1We', 'Compras'),
(13, 3, 'Andrés Vargas', 'andres.vargas@personal.com', 'andres.vargas@empresa.com', '$2a$10$KM68f7PAb/iUAJRjxDC8Q.BRNQ7UIMkpejGu4Mwk6SlVDTZNAbSaW', 'Calidad')
ON DUPLICATE KEY UPDATE id = VALUES(id);

-- =========================================
-- 2. CATEGORÍAS
-- =========================================
INSERT INTO categorias (id, nombre, descripcion) VALUES
(1, 'Soporte Técnico', 'Problemas con hardware, software, red o acceso'),
(2, 'Recursos Humanos', 'Nómina, vacaciones, contratación, políticas internas'),
(3, 'Accesos y Permisos', 'Solicitudes de acceso a sistemas, VPN, carpetas compartidas'),
(4, 'Hardware', 'Fallas en equipos, monitores, impresoras, laptops'),
(5, 'Software', 'Instalación, actualización o errores de aplicaciones'),
(6, 'Red e Internet', 'Problemas de conectividad, WiFi corporativo, lentitud'),
(7, 'Correo Corporativo', 'Outlook, envío/recepción, espacio, configuración'),
(8, 'ERP / Sistema Interno', 'Errores o solicitudes relacionadas con el ERP'),
(9, 'Telefonía', 'Líneas fijas, móviles corporativos, centralita'),
(10, 'Seguridad Informática', 'Phishing, alertas, bloqueos de cuenta'),
(11, 'Mantenimiento de Oficina', 'Aire acondicionado, iluminación, mobiliario'),
(12, 'Capacitación', 'Solicitudes de cursos internos o externos')
ON DUPLICATE KEY UPDATE id = VALUES(id);

-- =========================================
-- 3. TICKETS
-- =========================================
INSERT INTO tickets (id, usuario_id, categoria_id, agente_id, titulo, descripcion, prioridad, estado, fecha_creacion, fecha_cierre) VALUES
(1, 3, 1, 2, 'Error 403 al acceder al portal de empleados', 'Desde ayer no puedo ingresar al portal. Muestra "Acceso denegado" aunque uso las credenciales correctas.', 'Urgente', 'En progreso', '2025-04-01 09:15:00', NULL),
(2, 3, 2, NULL, 'Solicitud de vacaciones del 15 al 20 de mayo', 'Necesito programar vacaciones. Ya hablé con mi supervisor y está aprobado.', 'Programado', 'Cerrado', '2025-04-02 14:30:00', '2025-04-02 16:05:00'),
(3, 6, 4, 4, 'Monitor parpadea constantemente', 'El monitor de mi estación parpadea cada pocos segundos, ya reinicié el equipo.', 'Importante', 'En progreso', '2025-04-03 11:20:00', NULL),
(4, 7, 5, 5, 'No abre el programa de facturación', 'Al intentar abrir el módulo de facturación sale error 0x80070057.', 'Urgente', 'Pendiente', '2025-04-04 08:45:00', NULL),
(5, 8, 6, NULL, 'WiFi muy lento en sala de reuniones', 'Imposible hacer videollamadas, la velocidad es menor a 2 Mbps.', 'Importante', 'Pendiente', '2025-04-05 15:10:00', NULL),
(6, 9, 3, 2, 'Solicitud de acceso a carpeta compartida Contabilidad', 'Necesito leer y escribir en la carpeta \\server\Contabilidad2025.', 'Programado', 'En progreso', '2025-04-06 10:30:00', NULL),
(7, 10, 7, 4, 'No recibo correos externos', 'Desde ayer no llegan mensajes de clientes externos.', 'Urgente', 'En progreso', '2025-04-07 09:00:00', NULL),
(8, 11, 8, 5, 'Error al generar reporte mensual en ERP', 'El reporte de producción marca "Data source error".', 'Importante', 'Pendiente', '2025-04-08 13:15:00', NULL),
(9, 12, 11, NULL, 'Aire acondicionado gotea en oficina 203', 'Hay agua en el suelo debajo del equipo split.', 'Importante', 'Pendiente', '2025-04-09 16:40:00', NULL),
(10, 13, 12, NULL, 'Solicitud curso Power BI avanzado', 'Quiero inscribirme al curso programado para junio.', 'Programado', 'Cerrado', '2025-04-10 11:55:00', '2025-04-11 14:20:00'),
(11, 6, 10, 2, 'Alerta de posible phishing recibido', 'Recibí correo sospechoso pidiendo cambiar contraseña.', 'Urgente', 'Cerrado', '2025-04-11 08:20:00', '2025-04-11 09:30:00'),
(12, 7, 9, 4, 'Teléfono fijo no tiene tono', 'Línea 412 no marca ni recibe llamadas.', 'Importante', 'En progreso', '2025-04-12 14:00:00', NULL)
ON DUPLICATE KEY UPDATE id = VALUES(id);

-- =========================================
-- 4. COMENTARIOS
-- =========================================
INSERT INTO comentarios (ticket_id, usuario_id, comentario, nombre_usuario, fecha_creacion) VALUES
(1, 3, 'Ya intenté limpiar caché y usar otro navegador. Sigo con el mismo error.', 'María Zambrano', '2025-04-01 09:30:00'),
(1, 2, 'Revisaré los permisos en Active Directory. Te contacto en 30 min.', 'José González', '2025-04-01 10:00:00'),
(2, 3, 'Adjunto correo de aprobación de mi jefe.', 'María Zambrano', '2025-04-02 14:35:00'),
(2, 1, 'Aprobado. Se registrará en el sistema de RRHH.', 'Jaime Martínez', '2025-04-02 16:00:00'),
(3, 6, 'Ya probé con otro cable HDMI y sigue igual.', 'Laura Fernández', '2025-04-03 11:35:00'),
(3, 4, 'Voy a pasar por tu puesto con un monitor de reemplazo.', 'Ana López', '2025-04-03 11:50:00'),
(4, 7, 'Necesito el programa para cerrar facturas de hoy.', 'Pedro Sánchez', '2025-04-04 09:00:00'),
(5, 5, 'Revisaré el access point de la sala. Gracias por reportar.', 'Carlos Ruiz', '2025-04-05 15:30:00'),
(6, 2, 'Acceso concedido. Ya deberías poder entrar.', 'José González', '2025-04-06 10:45:00'),
(7, 4, 'Regla de firewall actualizada. Ya deberían llegar los correos.', 'Ana López', '2025-04-07 09:30:00'),
(11, 6, 'Gracias por reportarlo rápido.', 'Laura Fernández', '2025-04-11 08:40:00'),
(11, 2, 'Correo bloqueado y marcado como phishing. Todo ok.', 'José González', '2025-04-11 09:00:00')
ON DUPLICATE KEY UPDATE ticket_id = ticket_id;

-- =========================================
-- 5. HISTORIAL_TICKETS
-- =========================================
INSERT INTO historial_tickets (ticket_id, estado_anterior, estado_nuevo, fecha_creacion) VALUES
(1, 'Pendiente', 'En progreso', '2025-04-01 09:45:00'),
(2, 'Pendiente', 'Cerrado', '2025-04-02 16:05:00'),
(3, 'Pendiente', 'En progreso', '2025-04-03 11:25:00'),
(4, 'Pendiente', 'En progreso', '2025-04-04 09:10:00'),
(6, 'Pendiente', 'En progreso', '2025-04-06 10:35:00'),
(7, 'Pendiente', 'En progreso', '2025-04-07 09:05:00'),
(10, 'Pendiente', 'Cerrado', '2025-04-11 14:20:00'),
(11, 'Pendiente', 'En progreso', '2025-04-11 08:25:00'),
(11, 'En progreso', 'Cerrado', '2025-04-11 09:30:00'),
(12, 'Pendiente', 'En progreso', '2025-04-12 14:15:00')
ON DUPLICATE KEY UPDATE ticket_id = ticket_id;

-- =========================================
-- 6. NOTIFICACIONES
-- =========================================
INSERT INTO notificaciones (usuario_id, mensaje, fecha_creacion) VALUES
(3, 'Tu ticket #1 ha sido tomado por el agente José González.', '2025-04-01 09:46:00'),
(3, 'Tu solicitud de vacaciones ha sido aprobada.', '2025-04-02 16:06:00'),
(2, 'Nuevo ticket asignado: "Error 403 al acceder al portal"', '2025-04-01 09:20:00'),
(1, 'Se ha cerrado el ticket de solicitud de vacaciones.', '2025-04-02 16:07:00'),
(6, 'Tu ticket #3 (monitor) está siendo atendido por Ana López.', '2025-04-03 11:26:00'),
(4, 'Nuevo ticket asignado: #3 - Monitor parpadea', '2025-04-03 11:22:00'),
(7, 'Tu ticket #4 ha sido escalado como Urgente.', '2025-04-04 09:15:00'),
(5, 'Nuevo ticket asignado: #4 - Error facturación', '2025-04-04 09:12:00'),
(9, 'Acceso a carpeta Contabilidad concedido (ticket #6).', '2025-04-06 10:46:00'),
(10, 'Tu problema de correo ha sido solucionado (ticket #7).', '2025-04-07 09:31:00'),
(13, 'Tu solicitud de curso Power BI ha sido aprobada.', '2025-04-11 14:21:00'),
(6, 'El correo phishing fue bloqueado. Tu cuenta está segura.', '2025-04-11 09:31:00')
ON DUPLICATE KEY UPDATE usuario_id = usuario_id;