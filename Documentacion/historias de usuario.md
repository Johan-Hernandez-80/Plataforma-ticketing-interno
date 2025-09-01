# Historias de usuario

## User

- Como user de la app
  Quiero poder iniciar sesión
  Para realizar diferentes gestiones

  **Criterios**
  1. Se podrá iniciar sesión por medio del correo corporativo y contraseña
     - Si los campos están correctos entonces se lleva a una sección home
     - Si los campos no son validos se indicara con marco y texto rojo

- Como user de la app
  Quiero poder crear un ticket
  Para solicitar soporte de algún problema

  **Criterios**
  1. En home el user debe presionar un botón "Nuevo"
  2. Debe ir a un formulario para llenar los campos:
     - Titulo (Campo texto, limite de caracteres de 100)
     - Descripción (Text area, limite de caracteres de 1000)
     - Categoría (Combo box)
     - Prioridad (Combo box)
     - Prueba (una imagen o un `pdf`, Drop-zone)
       - Solo se puede subir una foto o un `pdf`, con tamaño máximo de `50mb`
       - Si no se cumple entonces debe aparecer un pop up que indique
         un icono de error y el mensaje "Este archivo excede el tamaño máximo
         soportado (`50mb`)"

  3. Debe presionar el botón enviar en la parte inferior
     - Si tuvo éxito entonces se le muestra un pop up que indique éxito con un icono
       y un mensaje "Ticket creado exitosamente", ademas se le deja en el home
     - Si falto algún campo entonces debe indicar cual faltó con
       un marco rojo y un texto rojo debajo que diga "Campo obligatorio"

- Como user de la app -> `Debería ser presencial, no en la app`
  Quiero poder pedir cancelar mi ticket
  Para indicar que ya no necesito el soporte

- Como user de la app
  Quiero poder ver una lista de mis tickets
  Para tener un orden y ver las solicitudes mas recientes

  **Criterios**
  1. La sección home mostrara una tabla de tickets del user con estos
     campos:
     - ID
     - Titulo
     - Descripción
     - Categoría
     - Prioridad
     - Estado
     - Fecha creación
     - Fecha cierre

  2. Debe estar en orden de creación, sera a su vez el historial
  3. Los campos que no se logren ver por completo terminaran con "..."

- Como user de la app
  Quiero ver los detalles de los tickets desde la lista
  Para poder saber toda la información del mismo y su propio proceso

  **Criterios**
  1. Cuando el user seleccione un ticket de la tabla, deberá ver estos datos:
     - ID
     - Titulo
     - Descripción
     - Categoría
     - Prioridad
     - Estado
     - Fecha creación
     - Fecha cierre

  2. Los datos aparecerán por completo
  3. También habrá un botón para ver el proceso del ticket

- Como user de la app
  Quiero estar notificado de los cambios en mis solicitudes
  Para poder atender rápidamente

  **Criterios**
  1. Aparecerá una lista de notificaciones donde se mostrara:
     - Titulo (Si no se logra mostrar todo terminara en ...)
     - Hora
     - Fecha
     - Descripción (Si no se logra mostrar todo terminara en ...)

  2. Cuando se seleccione una notificación especifica se mostrara la info completa
     bien estructurada

- Como user de la app
  Quiero poder cambiar mi contraseña y datos de contacto
  como teléfono, correo alternativo
  Para mantener mi información actualizada

  **Criterios**
  1. En una sección configuración de podrá cambiar estos datos
     desde un campo de texto, aparecerán los datos actuales y el usuario podrá
     cambiarlos

  2. El user presionará un botón actualizar
     - El correo debe tener un formato valido, si no se indica con marco rojo y
       un texto debajo que diga "correo invalido"

## Agente

- Como agente de soporte
  Quiero poder iniciar sesión
  Para realizar mis gestiones

  **Criterios**
  1. Se podrá iniciar sesión por medio del correo corporativo y contraseña
     - Si los campos están correctos entonces se lleva a una sección home
     - Si los campos no son validos se indicara con marco y texto rojo

- Como agente de soporte
  Quiero poder ver una lista de tickets asignados
  Para poder atenderlos

  **Criterios**
  1. El agente verá una lista de tickets en el home, con estos datos:
     - ID
     - user(ID)
     - Titulo
     - Descripción
     - Categoría
     - Prioridad
     - Estado
     - Fecha creación

  2. Se mostrará en el orden de urgencia

- Como agente de soporte
  Quiero poder ver la información del ticket
  Para atenderlo en base a la necesidad

  **Criterios**
  1. Cuando el agente seleccione un ticket de la tabla, deberá ver estos datos:
     - ID
     - user(ID)
     - Titulo
     - Descripción
     - Categoría
     - Prioridad
     - Estado
     - Fecha creación

  2. Los datos aparecerán por completo
  3. También habrá un botón para ver el proceso del ticket

- Como agente de soporte
  Quiero poder ver un historial de tickets atendidos
  Para ver mi productividad

  **Criterios**
  1. Será la misma lista del home, pues podrá ver los tickets ya cerrados

- Como agente de soporte
  Quiero poder escribir comentarios en los tickets
  Para poder informar sobre el estado del mimo

  **Criterios**
  1. Desde la sección de proceso de un ticket tendrá un botón para
     comentar, entonces podrá escribir una actualización sobre la solicitud,
     este quedará como el mas reciente de otros comentarios, como un historial

- Como agente de soporte
  Quiero estar notificado sobre tickets asignados
  Para poder atenderlos lo mas rápido posible

  **Criterios**
  - Se vera igual que en el caso del user

- Como agente de soporte
  Quiero poder cambiar el estado de urgencia de los tickets
  Para que sean atendidos según la verdadera importancia

  **Criterios**
  1. Se hará desde la sección de detalles de un ticket, como un combo box
  2. Se presionará un botón "Actualizar"

- Como agente de soporte
  Quiero poder cerrar los tickets
  Para finalizar el seguimiento de una solicitud

  **Criterios**
  1. Se hará desde la sección de proceso de un ticket
  2. Se podrá presionar un botón "Cerrar"
  3. Aparecerá un pop up para advertir con las opciones "Aceptar" y "cancelar"
     - Si acepta aparecerá en el proceso "Cerrado" en la ultima posición del historial
     - Si cancela entonces el pop up desaparece

- Como agente de soporte
  Quiero poder cambiar mi contraseña y datos de contacto
  como teléfono, correo alternativo
  Para mantener mi información actualizada

  **Criterios**
  - Se hará igual que el user

## Admin

- Como admin del sistema
  Quiero poder iniciar sesión con una cuenta defecto
  Para administrar el sistema

- Como admin del sistema
  Quiero poder asignar y reasignar tickets a los agentes
  Para que estos puedan atender las solicitudes

- Como admin del sistema
  Quiero poder crear categorías para los tickets
  Para clasificar mejor las solicitudes

- Como admin del sistema
  Quiero poder crear cuentas de users y agentes
  Para que realicen las gestiones necesarias

- Como admin del sistema
  Quiero ver una lista de todos los tickets filtrable por estado, prioridad,
  agente o fecha.
  Para llevar una gestión adecuada y asignarlos a los agentes

- Como admin del sistema
  Quiero poder ver la información completa de cada ticket
  Para poder estar informado de todo lo que ha pasado en el sistema

- Como admin del sistema
  Quiero poder atender tickets especiales que solo yo puedo resolver
  Para resolver las necesidades mas importantes de primera mano

- Como admin del sistema
  Quiero poder exportar informes del sistema en `PDF`
  Para poder ver los datos relevantes en general

- Como admin del sistema
  Quiero poder ver un dashboard con métricas
  Para poder ver estadística del sistema

## Reglas de negocio

-
