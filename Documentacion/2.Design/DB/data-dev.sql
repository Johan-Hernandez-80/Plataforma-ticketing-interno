use ticketing;

-- Usuarios
insert into usuarios (rol_id, nombre, email_personal, email_corporativo, contrasena, departamento) values
((select id from roles where nombre='admin'), 'Jaime Martinez', null, 'admin@empresa.com', '$2a$10$Env5ZVj0B85eaUfb87ox3utFuofSnOzPb0eiBPZLcmkncVIyXrcX2', 'Administración'),
((select id from roles where nombre='agente'), 'José González', 'agente.personal@correo.com', 'agente@empresa.com', '$2a$10$Kbyl6uYEU9CscEiJ8RPYI.bvy5d3P3hV7aKtlw4fn7zKctI/7TEyG', 'Soporte'),
((select id from roles where nombre='usuario'), 'María Zambrano', 'usuario.personal@correo.com', 'usuario@empresa.com', '$2a$10$WPtSUPCTZpX.8FF7AVoSrO/ahIJCx/GA9lZtv3ZNqY0R073G691AO', 'General');

-- Categorías
insert into categorias (nombre, descripcion) values
('Soporte Técnico', 'Tickets relacionados con problemas técnicos'),
('Recursos Humanos', 'Consultas o solicitudes de empleados');

-- Tickets
insert into tickets (usuario_id, categoria_id, agente_id, titulo, descripcion, prioridad, estado)
values
((select id from usuarios where nombre='María Zambrano'),
 (select id from categorias where nombre='Soporte Técnico'),
 (select id from usuarios where nombre='José González'),
 'No puedo iniciar sesión',
 'El usuario no puede acceder al sistema desde su cuenta.',
 'Urgente',
 'Pendiente'
);

-- Comentarios
insert into comentarios (ticket_id, usuario_id, comentario)
values
((select id from tickets where titulo='No puedo iniciar sesión'),
 (select id from usuarios where nombre='María Zambrano'),
 'Intenté restablecer mi contraseña, pero sigue sin funcionar.');

-- History
insert into historial_tickets (ticket_id, estado_anterior, estado_nuevo)
values
((select id from tickets where titulo='No puedo iniciar sesión'),
 'Pendiente',
 'En progreso');

-- Notifications
insert into notificaciones (usuario_id, mensaje)
values
((select id from usuarios where nombre='María Zambrano'),
 'Su ticket ha sido actualizado a "En progreso".');
