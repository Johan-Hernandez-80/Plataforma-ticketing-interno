use ticketing;

-- Admin: all permissions
insert into roles (nombre) values ('admin');

insert into permisos (nombre)
select concat(op, '_', table_name_singular, '_', column_name)
from (select 'view' as op union all select 'update') ops
join (
    select table_name,
           case table_name
               when 'roles' then 'rol'
               when 'permisos' then 'permiso'
               when 'permisos_roles' then 'permiso_rol'
               when 'usuarios' then 'usuario'
               when 'categorias' then 'categoria'
               when 'tickets' then 'ticket'
               when 'asignaciones' then 'asignacion'
               when 'comentarios' then 'comentario'
               when 'historial_tickets' then 'historial_ticket'
               when 'notificaciones' then 'notificacion'
           end as table_name_singular,
           column_name
    from information_schema.columns
    where table_schema = 'ticketing'
) cols;

insert into permisos_roles (rol_id, permiso_id)
select 1, id from permisos;


-- Agente: filtered permissions
insert into roles (nombre) values ('agente');

insert into permisos_roles (rol_id, permiso_id)
select 2, p.id
from permisos p
join (
    select concat('view_usuario_', col) as permiso
    from (select 'rol_id' union all select 'nombre' union all
          select 'email_personal' union all select 'email_corporativo'
          union all select 'departamento') ucols(col)

    union all
    select 'update_usuario_email_personal'

    union all
    -- categorias: view all
    select concat('view_categoria_', col)
    from (select 'id' union all select 'nombre' union all select 'descripcion') ccols(col)

    union all
    -- tickets: view all, update except id, fecha_creacion
    select concat('view_ticket_', col)
    from (select 'id' union all select 'usuario_id' union all select 'categoria_id'
          union all select 'titulo' union all select 'descripcion'
          union all select 'prioridad' union all select 'estado'
          union all select 'fecha_creacion' union all select 'fecha_cierre') tcols(col)

    union all
    select concat('update_ticket_', col)
    from (select 'usuario_id' union all select 'categoria_id'
          union all select 'titulo' union all select 'descripcion'
          union all select 'prioridad' union all select 'estado'
          union all select 'fecha_cierre') tcols2(col)

    union all
    -- asignaciones: view all, update except id, fecha_creacion
    select concat('view_asignacion_', col)
    from (select 'id' union all select 'ticket_id' union all select 'agente_id'
          union all select 'fecha_creacion') acols(col)

    union all
    select concat('update_asignacion_', col)
    from (select 'ticket_id' union all select 'agente_id') acols2(col)

    union all
    -- comentarios: view all, update except id, fecha_creacion
    select concat('view_comentario_', col)
    from (select 'id' union all select 'ticket_id' union all select 'usuario_id'
          union all select 'comentario' union all select 'fecha_creacion') ccols2(col)

    union all
    select concat('update_comentario_', col)
    from (select 'ticket_id' union all select 'usuario_id' union all select 'comentario') ccols3(col)

    union all
    -- historial_ticket: view all, update except id, fecha_creacion
    select concat('view_historial_ticket_', col)
    from (select 'id' union all select 'ticket_id' union all select 'estado_anterior'
          union all select 'estado_nuevo' union all select 'fecha_creacion') hcols(col)

    union all
    select concat('update_historial_ticket_', col)
    from (select 'ticket_id' union all select 'estado_anterior' union all select 'estado_nuevo') hcols2(col)

    union all
    -- notificaciones: view all, update except id, fecha_creacion
    select concat('view_notificacion_', col)
    from (select 'id' union all select 'usuario_id' union all select 'mensaje'
          union all select 'fecha_creacion') ncols(col)

    union all
    select concat('update_notificacion_', col)
    from (select 'usuario_id' union all select 'mensaje') ncols2(col)
) allowed
on p.nombre = allowed.permiso;


-- Usuario: same as agente
insert into roles (nombre) values ('usuario');

insert into permisos_roles (rol_id, permiso_id)
select 3, permiso_id from permisos_roles where rol_id = 2;
