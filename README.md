# Parcial II de Programación II

Estudiante: Cistyan Josue Catalán Maldonado

Carné: 0904 25 27550

El proyecto contiene el backend `parcial2b` de Spring Boot, el frontend `parcial2b-frontend` de React con TypeScript, el SQL original y una colección de Postman. Las clases Paciente, PacienteDTO y MessageResponse conservan exactamente el contenido entregado por el catedrático.

## Requisitos

- Java JDK 21.
- MySQL y MySQL Workbench, con tu usuario y contraseña.
- Node.js 22.12 o posterior y npm.
- Maven 3.9 si ejecutas o recompilas el código fuente. Si hay un JAR compilado en `parcial2b/target`, el script de inicio usa Java directamente.
- Postman para tomar las cuatro capturas exigidas.
- Git y tu cuenta de GitHub para publicar tu repositorio.

## 1. Extraer y abrir

Extrae todo el ZIP. Abre la carpeta `parcial2b-completo` en VS Code. Abre una terminal PowerShell en esa carpeta. No ejecutes el proyecto dentro del ZIP.

## 2. Preparar MySQL

Abre tu conexión en Workbench. Usa File > Open SQL Script y selecciona `base-de-datos/paciente.sql`. Ejecuta el script completo con el botón del rayo.

ATENCIÓN: el SQL original contiene `DROP DATABASE IF EXISTS parcial2b`; ejecutarlo de nuevo elimina los datos previos de esa base. Úsalo para preparar la base de demostración, no para reiniciar la aplicación cada vez.

Verifica en Workbench:

```sql
USE parcial2b;
SELECT * FROM paciente ORDER BY ID_PACIENTE DESC;
SELECT * FROM paciente WHERE ESTADO = TRUE ORDER BY ID_PACIENTE DESC;
```

Al importar el SQL hay cinco registros: cuatro activos y uno anulado.

Alternativa desde el cliente `mysql`, si está instalado y disponible:

```text
mysql -u root -p
```

Dentro del cliente, sustituye la ruta por la carpeta donde extrajiste el proyecto:

```sql
SOURCE C:/ruta/parcial2b-completo/base-de-datos/paciente.sql;
```

## 3. Iniciar backend

Desde la raíz del proyecto, ejecuta:

```powershell
powershell -ExecutionPolicy Bypass -File .\INICIAR-BACKEND.ps1
```

El script te pide la contraseña sin mostrarla en pantalla. Si MySQL usa el puerto 3307:

```powershell
powershell -ExecutionPolicy Bypass -File .\INICIAR-BACKEND.ps1 -PuertoMySQL 3307
```

Si usas otro usuario:

```powershell
powershell -ExecutionPolicy Bypass -File .\INICIAR-BACKEND.ps1 -Usuario tu_usuario
```

Deja esa terminal abierta. Espera el mensaje `Started Parcial2bApplication`. La API escucha en `http://localhost:8080`.

Alternativa manual para ejecutar desde el código fuente:

```powershell
cd .\parcial2b
$env:DB_USER = 'root'
$env:DB_PASSWORD = 'TU_CONTRASEÑA'
$env:DB_URL = 'jdbc:mysql://localhost:3306/parcial2b'
mvn spring-boot:run
```

Para compilar:

```powershell
mvn clean package
java -jar .\target\parcial2b-0.0.1-SNAPSHOT.jar
```

## 4. Iniciar frontend

Abre una SEGUNDA terminal en `parcial2b-completo`:

```powershell
powershell -ExecutionPolicy Bypass -File .\INICIAR-FRONTEND.ps1
```

Alternativa manual:

```powershell
cd .\parcial2b-frontend
npm.cmd install
npm.cmd run dev
```

Abre `http://localhost:5173`. Deja las dos terminales abiertas mientras haces la demostración. Para detener un proceso usa Ctrl+C en su terminal.

El archivo `parcial2b-frontend/.env` configura la API, el nombre y el carné. Reinicia Vite si lo cambias.

Para verificar el frontend:

```powershell
npm.cmd run build
```

## 5. Postman

Importa `postman/Pacientes.postman_collection.json`. La colección tiene las cuatro peticiones. `baseUrl` vale `http://localhost:8080`.

1. Ejecuta **Mostrar activos**. Debe responder una lista ordenada por ID descendente.
2. Ejecuta **Guardar**. Debe responder `Paciente creado con exito`.
3. Ejecuta Mostrar activos otra vez y busca el ID recién generado. Cambia la variable `idPaciente` de la colección por ese ID.
4. Ejecuta **Modificar**. Debe responder `Paciente actualizado con exito`.
5. Ejecuta **Anular**. Debe responder `Paciente anulado con exito`.
6. Ejecuta Mostrar activos de nuevo: ese ID ya no aparece.

Las respuestas exitosas de escritura son HTTP 200 con `{ "mensaje": "..." }`. Un ID inexistente en modificar/anular devuelve HTTP 400. El backend ignora el estado enviado al crear y conserva el estado original al modificar.

Toma cuatro capturas reales de Postman: una por cada procedimiento. Incluye método, URL, estado HTTP y respuesta visible. Toma también una captura del navegador con tu nombre y carné visibles. Las capturas deben obtenerse con tu aplicación funcionando.

## 6. Demostración personal

Lee `GUIA-DE-DEMOSTRACION.md` para el orden y las explicaciones de cada parte.

## 7. Subir a GitHub

Crea un repositorio vacío en tu cuenta de GitHub. Desde la raíz `parcial2b-completo`, ejecuta estos comandos y sustituye la URL por la de tu repositorio:

```powershell
git init
git add .
git commit -m "Parcial II: gestión de pacientes"
git branch -M main
git remote add origin https://github.com/TU_USUARIO/parcial2b.git
git push -u origin main
```

Abre el enlace en el navegador y verifica que aparecen el backend, frontend, SQL y README. El `.gitignore` excluye las dependencias y archivos compilados. No publiques contraseñas. No se ha creado ni publicado un repositorio desde este proyecto.

## Problemas frecuentes

- `Access denied for user`: verifica usuario y contraseña de MySQL.
- `Communications link failure`: verifica que MySQL esté iniciado y que el puerto sea correcto.
- `Unknown database parcial2b`: importa el SQL primero.
- Puerto 8080 o 5173 ocupado: detén el proceso que ya lo usa antes de iniciar otra copia.
- `mvn` no se reconoce: instala Maven y añádelo al PATH, o usa el JAR si está incluido.
- La pantalla muestra error de conexión: confirma que el backend arrancó y que `.env` tiene la dirección correcta.

La base se entrega como script de creación e inserción. Debes importarlo en tu servidor MySQL; la conexión requiere tus credenciales locales.
