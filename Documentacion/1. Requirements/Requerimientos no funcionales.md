## Requerimientos no funcionales para el sistema de tickets



#### 1\. Rendimiento

\- Las tablas de tickets deben mostrarse en menos de 3 segundos incluso con 500 registros.  

\- El sistema debe poder enviar notificaciones en tiempo real con un retraso máximo de 2 segundos.  



#### 2\. Confiabilidad

\- Si ocurre un error durante la creación o actualización de un ticket, el sistema debe evitar pérdida de datos y mostrar un mensaje claro al usuario.  

\- La información de tickets y comentarios debe guardarse siempre de manera íntegra y accesible.  



#### 3\. Disponibilidad

\- El sistema debe estar disponible el 99% del tiempo en horario laboral.  

\- En caso de caída, la recuperación debe ser en menos de 30 minutos.  



#### 4\. Seguridad

\- El acceso debe realizarse únicamente mediante credenciales válidas.  

\- Las contraseñas deben almacenarse de forma segura y nunca mostrarse en texto claro.  

\- Cada rol (empleado, agente, admin) solo puede acceder a la información que le corresponde.  

\- Archivos adjuntos (imágenes o PDFs hasta 50MB) deben ser accesibles únicamente desde el sistema.  



#### 5\. Usabilidad

\- La interfaz debe ser clara y consistente, con mensajes de error fáciles de entender (ejemplo: “Campo obligatorio” o “Correo inválido”).  

\- Los usuarios deben poder aprender a usar el sistema sin necesidad de capacitación extensa.  



#### 6\. Mantenibilidad

\- El sistema debe permitir que se apliquen cambios en validaciones, formularios o reportes sin interrumpir el uso normal.  

\- El código y la documentación deben estar organizados para que otros desarrolladores puedan dar soporte fácilmente.  



#### 7\. Escalabilidad

\- El sistema debe soportar al menos 500 usuarios activos concurrentes sin degradar la experiencia.  

\- Debe permitir agregar nuevas categorías.

