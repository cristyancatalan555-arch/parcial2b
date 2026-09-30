# Guía para presentar el parcial

## Antes de presentarlo

Importa el SQL una sola vez, inicia el backend y el frontend, importa la colección en Postman y prueba todo. Ten abiertas las dos terminales, Workbench, Postman, VS Code y el navegador. Guarda las cuatro capturas de Postman y la captura de la pantalla. Publica tu repositorio y comprueba el enlace.

## 1. Mostrar la base

En Workbench ejecuta:

```sql
USE parcial2b;
SELECT * FROM paciente ORDER BY ID_PACIENTE DESC;
```

Explica: «La llave primaria es ID_PACIENTE y se genera automáticamente. ESTADO permite anular sin borrar el registro. La base tiene los campos nombre, DPI, teléfono y dirección».

## 2. Explicar el backend

Abre estas carpetas en VS Code:

- `entity`: Paciente representa la tabla.
- `dto`: PacienteDTO transporta los datos y MessageResponse devuelve el mensaje.
- `repository`: JpaRepository accede a MySQL. `findByEstadoTrueOrderByIdPacienteDesc` filtra los activos y los ordena.
- `service`: crea, modifica y anula pacientes. Crear fuerza estado verdadero; modificar conserva el estado; anular lo cambia a falso.
- `controller`: declara las rutas HTTP y devuelve las respuestas.

Explica: «El controlador recibe la petición, el servicio aplica la operación y el repositorio guarda o consulta la base».

## 3. Mostrar las cuatro rutas en Postman

Ejecuta la colección en el orden indicado en README. Usa un paciente de prueba nuevo para no alterar los registros iniciales. Después de crearlo, consulta el listado y coloca su ID en la variable `idPaciente`.

| Operación | Método | Ruta |
|---|---|---|
| Mostrar activos | GET | /pacientes/mostrarActivos |
| Guardar | POST | /pacientes |
| Modificar | PUT | /pacientes/{idPaciente} |
| Anular | PUT | /pacientes/anular/{idPaciente} |

Muestra la respuesta de cada llamada y toma las cuatro capturas. Puedes demostrar el manejo de error usando un ID que comprobaste que no existe: devuelve 400.

## 4. Mostrar el frontend

Abre `http://localhost:5173` y realiza estas acciones:

1. Señala el encabezado con tu nombre y carné.
2. Muestra el listado de pacientes activos.
3. Crea un paciente llamado «Paciente demostración» y señala el mensaje de éxito y el nuevo registro.
4. Pulsa Modificar, cambia el teléfono y guarda. Señala que se actualiza el mismo ID.
5. Pulsa Anular. Primero cancela para mostrar la confirmación; después vuelve a pulsar y acepta.
6. Señala que el paciente desaparece del listado.

Explica: «useState controla los campos y el listado. useEffect carga los pacientes al entrar. Los servicios de Axios consumen la API configurada en VITE_API_URL. Después de cada operación se recarga la lista. Los errores se muestran con try/catch».

## 5. Comprobar el borrado lógico

En Workbench ejecuta, sustituyendo 6 por el ID que acabas de anular:

```sql
SELECT * FROM parcial2b.paciente WHERE ID_PACIENTE = 6;
```

Explica: «El registro permanece en la tabla con ESTADO = 0. La consulta de activos ya no lo incluye».

## 6. Mostrar GitHub y las evidencias

Abre tu repositorio y muestra las carpetas del backend, frontend y base de datos. Enseña el README con los comandos y las cinco capturas reales.

## Preguntas que debes poder responder

- ¿Por qué PUT para modificar y anular? Porque cambian un registro existente.
- ¿Por qué no DELETE? El requisito pide conservar el paciente mediante borrado lógico.
- ¿Cómo se conserva el estado al modificar? El servicio solo asigna nombre, DPI, teléfono y dirección.
- ¿Qué ocurre si no existe el ID? El servicio lanza una excepción y el controlador responde 400 con MessageResponse.
- ¿Por qué una interface en TypeScript? Define los campos y tipos esperados de la API.
- ¿Para qué sirve `.env`? Configura la URL del backend y la identificación del estudiante.
