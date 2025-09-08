# Plan de Pruebas de Aceptación Basado en Historias de Usuario

## Introducción

Este documento define el plan de pruebas de aceptación del sistema de ticketing interno
basado en los requerimientos funcionales representados mediante historias de usuario.
El objetivo es validar que el sistema cumpla con las necesidades del usuario final.

## Alcance del Plan de Pruebas

Se validarán las funcionalidades para cada uno de los roles del sistema, asegurando
de esta manera un funcionamiento completo y apropiado de lo que es requerido por
el cliente

## Criterios de Aceptación

Los criterios de aceptación para cada historia de usuario están documentados en el archivo criterios_de_aceptacion.md dentro de la sección de requerimientos.

```text
Historia 1: Como usuario, quiero iniciar sesión para acceder al sistema.
Criterios de aceptación:
- Se requiere correo y contraseña válidos.
- El botón "Iniciar sesión" debe estar deshabilitado hasta que se completen los campos.
- Si las credenciales son válidas, se accede al panel del usuario.
- Si no, se muestra un mensaje de error.
```

---

## Casos de Prueba

Una tabla estructurada por caso:

| ID     | Historia relacionada     | Caso de Prueba                  | Precondición                 | Pasos a Seguir                                                           | Resultado Esperado          |
| ------ | ------------------------ | ------------------------------- | ---------------------------- | ------------------------------------------------------------------------ | --------------------------- |
| TC-001 | HU-01 (Inicio de sesión) | Ingreso válido                  | Usuario en pantalla de login | 1. Ingresar correo y contraseña válidos <br> 2. Clic en "Iniciar sesión" | Acceso al panel de usuario  |
| TC-002 | HU-01 (Inicio de sesión) | Error por contraseña incorrecta | Usuario en pantalla de login | 1. Ingresar correo válido <br> 2. Contraseña incorrecta <br> 3. Clic     | Se muestra mensaje de error |

> 🔹 Repite esto para las historias clave. Puedes agrupar por módulo o por rol (Empleado, Agente, Admin).
