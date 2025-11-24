📚 Proyecto de Gestión de Reservas de Aulas

Este proyecto es una API RESTful desarrollada con Spring Boot para gestionar la reserva de aulas en un centro educativo. Permite administrar usuarios (profesores y administradores), aulas, horarios y realizar reservas controlando solapamientos y capacidades.

El sistema incluye seguridad mediante Spring Security y JWT (JSON Web Tokens).

🚀 Tecnologías Utilizadas

Java: 21

Framework: Spring Boot 3.5.6

Gestor de Dependencias: Maven

Base de Datos: MySQL

Seguridad: Spring Security + JWT (jjwt 0.12.6)

Persistencia: JPA / Hibernate

Herramientas: Lombok, Jakarta Validation

⚙️ Configuración y Requisitos

1. Requisitos Previos

JDK 21 instalado.

MySQL Server en ejecución.

Maven (opcional, ya que el proyecto incluye el wrapper mvnw).

2. Base de Datos

El proyecto está configurado para conectarse a una base de datos MySQL local.

Configuración por defecto (src/main/resources/application.properties):

URL: jdbc:mysql://localhost:3306/reservasAula

Usuario: alumno

Contraseña: alumno

Pasos para inicializar:

Crea una base de datos llamada reservasAula en tu MySQL.

(Opcional) Importa el archivo dump-reservasAula-202510241912.sql incluido en la raíz para tener datos de prueba iniciales.

Si deseas usar otras credenciales, modifica el archivo application.properties.

3. Ejecución

En la raíz del proyecto, abre una terminal y ejecuta:

# En Windows
./mvnw.cmd spring-boot:run

# En Linux/Mac
./mvnw spring-boot:run


La aplicación iniciará en http://localhost:8080.

🔐 Seguridad y Roles

El sistema utiliza autenticación vía Token Bearer (JWT).

Sin Autenticación: Endpoints de Login y Registro.

Autenticado (Cualquier Rol): Ver aulas, ver horarios, ver reservas.

Rol ROLE_PROFE: Puede crear reservas.

Rol ROLE_ADMIN: Puede crear, modificar y eliminar Aulas y Horarios.

📡 Endpoints de la API

👤 Autenticación (/auth)

Método

Endpoint

Descripción

Body Requerido

POST

/auth/register

Registrar un Profesor

{ "email": "...", "password": "..." }

POST

/auth/register/admin

Registrar un Admin

{ "email": "...", "password": "..." }

POST

/auth/login

Iniciar sesión

{ "email": "...", "password": "..." }

El login devuelve un token. Debes enviar este token en el Header Authorization: Bearer <token> para las peticiones protegidas.

📘 Aulas (/aulas)

Método

Endpoint

Rol Requerido

Descripción

GET

/aulas

Autenticado

Listar todas las aulas

GET

/aulas/{id}

Autenticado

Ver detalle de un aula

POST

/aulas

ADMIN

Crear nueva aula

PUT

/aulas/{id}

ADMIN

Actualizar aula

DELETE

/aulas/{id}

ADMIN

Eliminar aula

GET

/aulas/{id}/reservas

Autenticado

Ver reservas de un aula específica

🕒 Horarios (/horarios)

Método

Endpoint

Rol Requerido

Descripción

GET

/horarios

Autenticado

Listar horarios disponibles

POST

/horarios

ADMIN

Crear nuevo tramo horario

DELETE

/horarios/{id}

ADMIN

Eliminar horario

📅 Reservas (/reservas)

Método

Endpoint

Rol Requerido

Descripción

GET

/reservas

Autenticado

Listar todas las reservas

GET

/reservas/{id}

Autenticado

Ver detalle reserva

POST

/reservas

PROFE / ADMIN

Crear una reserva (Valida aforo y solapamiento)

DELETE

/reservas/{id}

Autenticado

Cancelar reserva

🧪 Pruebas (Front-end incluido)

El proyecto incluye dos archivos HTML en la raíz para probar la API fácilmente sin necesidad de Postman:

interfaz_segura.html (Recomendado):

Gestiona el flujo completo de Login.

Guarda el Token JWT automáticamente.

Permite probar todos los endpoints (Crear Aulas, Horarios, Reservas) enviando el token en las cabeceras.

interfaz.html: Versión básica (para pruebas sin seguridad habilitada o versiones anteriores).

📝 Ejemplos de JSON (Body)

Crear Aula

{
    "nombre": "Laboratorio 3 (Robots)",
    "capacidad": 24,
    "esDeOrdenadores": true,
    "numeroOrdenadores": 24
}


Crear Horario

{
    "dia": "JUEVES",
    "sesionDia": 3,
    "horaInicio": "13:30:00",
    "horaFim": "14:30:00",
    "tipo": "MEDIO_DIA"
}


Tipos permitidos: LECTIVO, RECREO, MEDIO_DIA.

Crear Reserva

{
    "fechaReserva": "2026-03-13",
    "motivo": "Prácticas de Edición de Video",
    "numeroAsistentes": 15,
    "aulaId": 1,
    "horarioId": 1
}


Nota: El usuario se extrae automáticamente del Token JWT.

📂 Estructura del Proyecto

src/main/java/com/example/ProyectoReservas/controllers: Controladores REST.

src/main/java/com/example/ProyectoReservas/services: Lógica de negocio (validaciones de solapamiento, mapeo DTOs).

src/main/java/com/example/ProyectoReservas/entities: Modelos de BD (Aula, Reserva, Horario, Usuario).

src/main/java/com/example/ProyectoReservas/DTOS: Objetos de transferencia de datos.

src/main/java/com/example/ProyectoReservas/security: Configuración de filtros y JWT.
