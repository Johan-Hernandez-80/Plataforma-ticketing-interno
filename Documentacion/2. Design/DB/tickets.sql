create database ticketing;
use ticketing;

create table roles (
	id int auto_increment primary key,
	nombre varchar(255) unique not null
);

create table permisos (
	id int auto_increment primary key,
	nombre varchar(255) unique not null
);

create table permisos_roles (
	rol_id int not null,
	permiso_id int not null,
	primary key (rol_id, permiso_id),
	
	constraint fk_permisosroles_roles
	foreign key (rol_id) references roles(id),
	
	constraint fk_permisosroles_permisos
	foreign key (permiso_id) references permisos(id)
);

create table usuarios (
    id int auto_increment primary key,
    rol_id int not null,
    nombre varchar(255) not null,
    email_personal varchar(255) unique null,
    email_corporativo varchar(255) unique not null,
    contrasena varchar(255) not null,
    departamento varchar(255) not null,
    
    constraint fk_usuarios_roles
	foreign key (rol_id) references roles(id)
);

create table categorias (
    id int auto_increment primary key,
    nombre varchar(255) not null,
    descripcion varchar(255) null
);


create table tickets (
    id int auto_increment primary key,
    usuario_id int not null,
    categoria_id int not null,
    titulo varchar(255) not null,
    descripcion varchar(1000) not null,
    prioridad varchar(255) not null,
    estado varchar(255) not null,
    fecha_creacion timestamp not null default current_timestamp,
    fecha_cierre timestamp null,

    constraint fk_tickets_usuarios
	foreign key (usuario_id) references usuarios(id),

    constraint fk_tickets_categorias
	foreign key (categoria_id) references categorias(id)
);

create table asignaciones (
    id int auto_increment primary key,
    ticket_id int not null,
    agente_id int not null,
    fecha_creacion timestamp not null default current_timestamp,

    constraint fk_asignaciones_tickets
	foreign key (ticket_id) references tickets(id),

    constraint fk_asignaciones_usuarios
	foreign key (agente_id) references usuarios(id)
);

create table comentarios (
    id int auto_increment primary key,
    ticket_id int not null,
    usuario_id int not null,
    comentario varchar(1000) not null,
    fecha_creacion timestamp not null default current_timestamp,

    constraint fk_comentarios_tickets
	foreign key (ticket_id) references tickets(id),

    constraint fk_comentarios_usuarios
	foreign key (usuario_id) references usuarios(id)
);

create table historial_tickets (
    id int auto_increment primary key,
    ticket_id int not null,
    estado_anterior varchar(255) not null,
    estado_nuevo varchar(255) not null,
    fecha_creacion timestamp not null default current_timestamp,

    constraint fk_historialtickets_tickets
	foreign key (ticket_id) references tickets(id)
);

create table notificaciones (
    id int auto_increment primary key,
    usuario_id int not null,
    mensaje varchar(255) not null,
    fecha_creacion timestamp not null default current_timestamp,
    
    constraint fk_notificaciones_usuarios
	foreign key (usuario_id) references usuarios(id)
);
