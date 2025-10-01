-- Make sure we are using the right DB
use ticketing;

-- Insert admin user
insert into usuarios (rol_id, nombre, email_personal, email_corporativo, contrasena, departamento)
values (
    (select id from roles where nombre = 'admin'),
    'Admin Principal',
    null,
    'admin@empresa.com',
    '$2a$10$XALQrXT9eqQbU3GiK674f.zHtJ2Eusuev1vQku/Bc78jDpCOA0smO',
    'Administración'
);

-- Insert agente user
insert into usuarios (rol_id, nombre, email_personal, email_corporativo, contrasena, departamento)
values (
    (select id from roles where nombre = 'agente'),
    'Agente Soporte',
    'agente.personal@correo.com',
    'agente@empresa.com',
    '$2a$10$CAJ8XEgQlLP3J/W9pejFPeNqBGDPxru15L5TRpKp5e6bn2/cnXmWy',
    'Soporte'
);

-- Insert usuario user
insert into usuarios (rol_id, nombre, email_personal, email_corporativo, contrasena, departamento)
values (
    (select id from roles where nombre = 'usuario'),
    'Usuario Final',
    'usuario.personal@correo.com',
    'usuario@empresa.com',
    '$2a$10$mcFrZ.SKqpDX78UxCnfqQufiwPujp28e67TZ59TwyEgdJ73RBG4he',
    'General'
);
