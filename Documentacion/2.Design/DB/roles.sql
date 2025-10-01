use ticketing;

-- =========================================
-- ROLES
-- =========================================
insert into roles (nombre) values 
('admin'),
('agente'),
('usuario');

-- =========================================
-- PERMISOS
-- =========================================

-- ROL
insert into permisos (nombre) values
('view_rol_id'), ('update_rol_id'),
('view_rol_nombre'), ('update_rol_nombre');

-- PERMISO
insert into permisos (nombre) values
('view_permiso_id'), ('update_permiso_id'),
('view_permiso_nombre'), ('update_permiso_nombre');

-- PERMISO_ROL
insert into permisos (nombre) values
('view_permiso_rol_rol_id'), ('update_permiso_rol_rol_id'),
('view_permiso_rol_permiso_id'), ('update_permiso_rol_permiso_id');

-- USUARIO
insert into permisos (nombre) values
('view_usuario_id'), ('update_usuario_id'),
('view_usuario_rol_id'), ('update_usuario_rol_id'),
('view_usuario_nombre'), ('update_usuario_nombre'),
('view_usuario_email_personal'), ('update_usuario_email_personal'),
('view_usuario_email_corporativo'), ('update_usuario_email_corporativo'),
('view_usuario_contrasena'), ('update_usuario_contrasena'),
('view_usuario_departamento'), ('update_usuario_departamento');

-- CATEGORIA
insert into permisos (nombre) values
('view_categoria_id'), ('update_categoria_id'),
('view_categoria_nombre'), ('update_categoria_nombre'),
('view_categoria_descripcion'), ('update_categoria_descripcion');

-- TICKET
insert into permisos (nombre) values
('view_ticket_id'), ('update_ticket_id'),
('view_ticket_usuario_id'), ('update_ticket_usuario_id'),
('view_ticket_categoria_id'), ('update_ticket_categoria_id'),
('view_ticket_titulo'), ('update_ticket_titulo'),
('view_ticket_descripcion'), ('update_ticket_descripcion'),
('view_ticket_prioridad'), ('update_ticket_prioridad'),
('view_ticket_estado'), ('update_ticket_estado'),
('view_ticket_fecha_creacion'), ('update_ticket_fecha_creacion'),
('view_ticket_fecha_cierre'), ('update_ticket_fecha_cierre');

-- ASIGNACION
insert into permisos (nombre) values
('view_asignacion_id'), ('update_asignacion_id'),
('view_asignacion_ticket_id'), ('update_asignacion_ticket_id'),
('view_asignacion_agente_id'), ('update_asignacion_agente_id'),
('view_asignacion_fecha_creacion'), ('update_asignacion_fecha_creacion');

-- COMENTARIO
insert into permisos (nombre) values
('view_comentario_id'), ('update_comentario_id'),
('view_comentario_ticket_id'), ('update_comentario_ticket_id'),
('view_comentario_usuario_id'), ('update_comentario_usuario_id'),
('view_comentario_texto'), ('update_comentario_texto'),
('view_comentario_fecha_creacion'), ('update_comentario_fecha_creacion');

-- HISTORIAL_TICKET
insert into permisos (nombre) values
('view_historial_ticket_id'), ('update_historial_ticket_id'),
('view_historial_ticket_ticket_id'), ('update_historial_ticket_ticket_id'),
('view_historial_ticket_estado_anterior'), ('update_historial_ticket_estado_anterior'),
('view_historial_ticket_estado_nuevo'), ('update_historial_ticket_estado_nuevo'),
('view_historial_ticket_fecha_creacion'), ('update_historial_ticket_fecha_creacion');

-- NOTIFICACION
insert into permisos (nombre) values
('view_notificacion_id'), ('update_notificacion_id'),
('view_notificacion_usuario_id'), ('update_notificacion_usuario_id'),
('view_notificacion_mensaje'), ('update_notificacion_mensaje'),
('view_notificacion_fecha_creacion'), ('update_notificacion_fecha_creacion');

-- =========================================
-- PERMISOS_ROLES
-- =========================================

-- ADMIN: all permissions
insert into permisos_roles (rol_id, permiso_id)
select 1, id from permisos;

-- AGENTE (view first, then update, one per line)
insert into permisos_roles (rol_id, permiso_id)
select 2, id from permisos where nombre in (
  -- ===== ROL =====
--  'view_rol_id',
--  'view_rol_nombre',
--  'update_rol_id',
--  'update_rol_nombre',

  -- ===== PERMISO =====
--  'view_permiso_id',
--  'view_permiso_nombre',
--  'update_permiso_id',
--  'update_permiso_nombre',

  -- ===== PERMISO_ROL =====
--  'view_permiso_rol_rol_id',
--  'view_permiso_rol_permiso_id',
--  'update_permiso_rol_rol_id',
--  'update_permiso_rol_permiso_id',

  -- ===== USUARIO =====
  'view_usuario_id',
--  'view_usuario_rol_id',
  'view_usuario_nombre',
  'view_usuario_email_personal',
  'view_usuario_email_corporativo',
--  'view_usuario_contrasena',
  'view_usuario_departamento',
--  'update_usuario_id',
--  'update_usuario_rol_id',
  'update_usuario_nombre',
  'update_usuario_email_personal',
  'update_usuario_email_corporativo',
  'update_usuario_contrasena',
--  'update_usuario_departamento',

  -- ===== CATEGORIA =====
  'view_categoria_id',
  'view_categoria_nombre',
  'view_categoria_descripcion',
--  'update_categoria_id',
--  'update_categoria_nombre',
--  'update_categoria_descripcion',

  -- ===== TICKET =====
  'view_ticket_id',
  'view_ticket_usuario_id',
  'view_ticket_categoria_id',
  'view_ticket_titulo',
  'view_ticket_descripcion',
  'view_ticket_prioridad',
  'view_ticket_estado',
  'view_ticket_fecha_creacion',
  'view_ticket_fecha_cierre',
  'update_ticket_id',
  'update_ticket_usuario_id',
  'update_ticket_categoria_id',
  'update_ticket_titulo',
  'update_ticket_descripcion',
  'update_ticket_prioridad',
  'update_ticket_estado',
  'update_ticket_fecha_creacion',
  'update_ticket_fecha_cierre',

  -- ===== ASIGNACION =====
  'view_asignacion_id',
  'view_asignacion_ticket_id',
  'view_asignacion_agente_id',
  'view_asignacion_fecha_creacion',
  'update_asignacion_id',
  'update_asignacion_ticket_id',
  'update_asignacion_agente_id',
  'update_asignacion_fecha_creacion',

  -- ===== COMENTARIO =====
  'view_comentario_id',
  'view_comentario_ticket_id',
  'view_comentario_usuario_id',
  'view_comentario_texto',
  'view_comentario_fecha_creacion',
  'update_comentario_id',
  'update_comentario_ticket_id',
  'update_comentario_usuario_id',
  'update_comentario_texto',
  'update_comentario_fecha_creacion',

  -- ===== HISTORIAL_TICKET =====
  'view_historial_ticket_id',
  'view_historial_ticket_ticket_id',
  'view_historial_ticket_estado_anterior',
  'view_historial_ticket_estado_nuevo',
  'view_historial_ticket_fecha_creacion',
  'update_historial_ticket_id',
  'update_historial_ticket_ticket_id',
  'update_historial_ticket_estado_anterior',
  'update_historial_ticket_estado_nuevo',
  'update_historial_ticket_fecha_creacion',

  -- ===== NOTIFICACION =====
  'view_notificacion_id',
  'view_notificacion_usuario_id',
  'view_notificacion_mensaje',
  'view_notificacion_fecha_creacion',
  'update_notificacion_id',
  'update_notificacion_usuario_id',
  'update_notificacion_mensaje',
  'update_notificacion_fecha_creacion'
);

-- USUARIO (view first, then update, one per line)
insert into permisos_roles (rol_id, permiso_id)
select 3, id from permisos where nombre in (
  -- ===== ROL =====
--  'view_rol_id',
--  'view_rol_nombre',
--  'update_rol_id',
--  'update_rol_nombre',

  -- ===== PERMISO =====
--  'view_permiso_id',
--  'view_permiso_nombre',
--  'update_permiso_id',
--  'update_permiso_nombre',

  -- ===== PERMISO_ROL =====
--  'view_permiso_rol_rol_id',
--  'view_permiso_rol_permiso_id',
--  'update_permiso_rol_rol_id',
--  'update_permiso_rol_permiso_id',

  -- ===== USUARIO =====
  'view_usuario_id',
--  'view_usuario_rol_id',
  'view_usuario_nombre',
  'view_usuario_email_personal',
  'view_usuario_email_corporativo',
--  'view_usuario_contrasena',
  'view_usuario_departamento',
--  'update_usuario_id',
--  'update_usuario_rol_id',
  'update_usuario_nombre',
  'update_usuario_email_personal',
  'update_usuario_email_corporativo',
  'update_usuario_contrasena',
--  'update_usuario_departamento',

  -- ===== CATEGORIA =====
  'view_categoria_id',
  'view_categoria_nombre',
  'view_categoria_descripcion',
  'update_categoria_id',
  'update_categoria_nombre',
  'update_categoria_descripcion',

  -- ===== TICKET =====
  'view_ticket_id',
  'view_ticket_usuario_id',
  'view_ticket_categoria_id',
  'view_ticket_titulo',
  'view_ticket_descripcion',
  'view_ticket_prioridad',
  'view_ticket_estado',
  'view_ticket_fecha_creacion',
  'view_ticket_fecha_cierre',
  'update_ticket_id',
  'update_ticket_usuario_id',
  'update_ticket_categoria_id',
  'update_ticket_titulo',
  'update_ticket_descripcion',
  'update_ticket_prioridad',
  'update_ticket_estado',
  'update_ticket_fecha_creacion',
  'update_ticket_fecha_cierre',

  -- ===== ASIGNACION =====
  'view_asignacion_id',
  'view_asignacion_ticket_id',
  'view_asignacion_agente_id',
  'view_asignacion_fecha_creacion',
  'update_asignacion_id',
  'update_asignacion_ticket_id',
  'update_asignacion_agente_id',
  'update_asignacion_fecha_creacion',

  -- ===== COMENTARIO =====
  'view_comentario_id',
  'view_comentario_ticket_id',
  'view_comentario_usuario_id',
  'view_comentario_texto',
  'view_comentario_fecha_creacion',
  'update_comentario_id',
  'update_comentario_ticket_id',
  'update_comentario_usuario_id',
  'update_comentario_texto',
  'update_comentario_fecha_creacion',

  -- ===== HISTORIAL_TICKET =====
  'view_historial_ticket_id',
  'view_historial_ticket_ticket_id',
  'view_historial_ticket_estado_anterior',
  'view_historial_ticket_estado_nuevo',
  'view_historial_ticket_fecha_creacion',
  'update_historial_ticket_id',
  'update_historial_ticket_ticket_id',
  'update_historial_ticket_estado_anterior',
  'update_historial_ticket_estado_nuevo',
  'update_historial_ticket_fecha_creacion',

  -- ===== NOTIFICACION =====
  'view_notificacion_id',
  'view_notificacion_usuario_id',
  'view_notificacion_mensaje',
  'view_notificacion_fecha_creacion',
  'update_notificacion_id',
  'update_notificacion_usuario_id',
  'update_notificacion_mensaje',
  'update_notificacion_fecha_creacion'
);
