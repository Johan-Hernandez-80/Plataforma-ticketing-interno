-- =========================================
-- ROLES
-- =========================================
INSERT INTO roles (nombre) VALUES 
('admin'),
('agente'),
('usuario');

-- =========================================
-- PERMISOS
-- =========================================

-- ROL
INSERT INTO permisos (nombre) VALUES
('view_rol_id'), ('update_rol_id'),
('view_rol_nombre'), ('update_rol_nombre');

-- PERMISO
INSERT INTO permisos (nombre) VALUES
('view_permiso_id'), ('update_permiso_id'),
('view_permiso_nombre'), ('update_permiso_nombre');

-- PERMISO_ROL
INSERT INTO permisos (nombre) VALUES
('view_permiso_rol_rol_id'), ('update_permiso_rol_rol_id'),
('view_permiso_rol_permiso_id'), ('update_permiso_rol_permiso_id');

-- USUARIO
INSERT INTO permisos (nombre) VALUES
('view_usuario_id'), ('update_usuario_id'),
('view_usuario_rol_id'), ('update_usuario_rol_id'),
('view_usuario_nombre'), ('update_usuario_nombre'),
('view_usuario_email_personal'), ('update_usuario_email_personal'),
('view_usuario_email_corporativo'), ('update_usuario_email_corporativo'),
('view_usuario_contrasena'), ('update_usuario_contrasena'),
('view_usuario_departamento'), ('update_usuario_departamento');

-- CATEGORIA
INSERT INTO permisos (nombre) VALUES
('view_categoria_id'), ('update_categoria_id'),
('view_categoria_nombre'), ('update_categoria_nombre'),
('view_categoria_descripcion'), ('update_categoria_descripcion');

-- TICKET
INSERT INTO permisos (nombre) VALUES
('view_ticket_id'), ('update_ticket_id'),
('view_ticket_usuario_id'), ('update_ticket_usuario_id'),
('view_ticket_categoria_id'), ('update_ticket_categoria_id'),
('view_ticket_agente_id'), ('update_ticket_agente_id'),
('view_ticket_titulo'), ('update_ticket_titulo'),
('view_ticket_descripcion'), ('update_ticket_descripcion'),
('view_ticket_prioridad'), ('update_ticket_prioridad'),
('view_ticket_estado'), ('update_ticket_estado'),
('view_ticket_fecha_creacion'), ('update_ticket_fecha_creacion'),
('view_ticket_fecha_cierre'), ('update_ticket_fecha_cierre');

-- COMENTARIO
INSERT INTO permisos (nombre) VALUES
('view_comentario_id'), ('update_comentario_id'),
('view_comentario_ticket_id'), ('update_comentario_ticket_id'),
('view_comentario_usuario_id'), ('update_comentario_usuario_id'),
('view_comentario_texto'), ('update_comentario_texto'),
('view_comentario_fecha_creacion'), ('update_comentario_fecha_creacion');

-- HISTORIAL_TICKET
INSERT INTO permisos (nombre) VALUES
('view_historial_ticket_id'), ('update_historial_ticket_id'),
('view_historial_ticket_ticket_id'), ('update_historial_ticket_ticket_id'),
('view_historial_ticket_estado_anterior'), ('update_historial_ticket_estado_anterior'),
('view_historial_ticket_estado_nuevo'), ('update_historial_ticket_estado_nuevo'),
('view_historial_ticket_fecha_creacion'), ('update_historial_ticket_fecha_creacion');

-- NOTIFICACION
INSERT INTO permisos (nombre) VALUES
('view_notificacion_id'), ('update_notificacion_id'),
('view_notificacion_usuario_id'), ('update_notificacion_usuario_id'),
('view_notificacion_mensaje'), ('update_notificacion_mensaje'),
('view_notificacion_fecha_creacion'), ('update_notificacion_fecha_creacion');

-- =========================================
-- PERMISOS_ROLES
-- =========================================

-- ADMIN: all permissions
INSERT INTO permisos_roles (rol_id, permiso_id)
SELECT 1, id FROM permisos;

-- AGENTE
INSERT INTO permisos_roles (rol_id, permiso_id)
SELECT 2, id FROM permisos WHERE nombre IN (
  'view_usuario_id',
  'view_usuario_nombre',
  'view_usuario_email_personal',
  'view_usuario_email_corporativo',
  'view_usuario_departamento',
  'update_usuario_nombre',
  'update_usuario_email_personal',
  'update_usuario_email_corporativo',
  'update_usuario_contrasena',
  'view_categoria_id',
  'view_categoria_nombre',
  'view_categoria_descripcion',
  'view_ticket_id',
  'view_ticket_usuario_id',
  'view_ticket_categoria_id',
  'view_ticket_agente_id',
  'view_ticket_titulo',
  'view_ticket_descripcion',
  'view_ticket_prioridad',
  'view_ticket_estado',
  'view_ticket_fecha_creacion',
  'view_ticket_fecha_cierre',
  'update_ticket_id',
  'update_ticket_usuario_id',
  'update_ticket_categoria_id',
  'update_ticket_agente_id',
  'update_ticket_titulo',
  'update_ticket_descripcion',
  'update_ticket_prioridad',
  'update_ticket_estado',
  'update_ticket_fecha_creacion',
  'update_ticket_fecha_cierre',
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
  'view_notificacion_id',
  'view_notificacion_usuario_id',
  'view_notificacion_mensaje',
  'view_notificacion_fecha_creacion',
  'update_notificacion_id',
  'update_notificacion_usuario_id',
  'update_notificacion_mensaje',
  'update_notificacion_fecha_creacion'
);

-- USUARIO
INSERT INTO permisos_roles (rol_id, permiso_id)
SELECT 3, id FROM permisos WHERE nombre IN (
  'view_usuario_id',
  'view_usuario_nombre',
  'view_usuario_email_personal',
  'view_usuario_email_corporativo',
  'view_usuario_departamento',
  'update_usuario_nombre',
  'update_usuario_email_personal',
  'update_usuario_email_corporativo',
  'update_usuario_contrasena',
  'view_categoria_id',
  'view_categoria_nombre',
  'view_categoria_descripcion',
  'update_categoria_id',
  'update_categoria_nombre',
  'update_categoria_descripcion',
  'view_ticket_id',
  'view_ticket_usuario_id',
  'view_ticket_categoria_id',
  'view_ticket_agente_id',
  'view_ticket_titulo',
  'view_ticket_descripcion',
  'view_ticket_prioridad',
  'view_ticket_estado',
  'view_ticket_fecha_creacion',
  'view_ticket_fecha_cierre',
  'update_ticket_id',
  'update_ticket_usuario_id',
  'update_ticket_categoria_id',
  'update_ticket_agente_id',
  'update_ticket_titulo',
  'update_ticket_descripcion',
  'update_ticket_prioridad',
  'update_ticket_estado',
  'update_ticket_fecha_creacion',
  'update_ticket_fecha_cierre',
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
  'view_notificacion_id',
  'view_notificacion_usuario_id',
  'view_notificacion_mensaje',
  'view_notificacion_fecha_creacion',
  'update_notificacion_id',
  'update_notificacion_usuario_id',
  'update_notificacion_mensaje',
  'update_notificacion_fecha_creacion'
);
