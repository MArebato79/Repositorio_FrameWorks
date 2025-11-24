
[README.md](https://github.com/user-attachments/files/23712683/README.md)
# 📚 Proyecto de Gestión de Reservas de Aulas
_API RESTful con Spring Boot para centros educativos_

Este proyecto permite gestionar la reserva de aulas en un centro educativo, administrando **usuarios**, **aulas**, **horarios** y **reservas**, garantizando control de **solapamientos**, **aforo** y **roles de usuario**.  
Incluye un sistema de autenticación seguro basado en **JWT**.

## 🚀 Tecnologías Utilizadas

- **Java:** 21  
- **Framework:** Spring Boot 3.5.6  
- **Gestor de Dependencias:** Maven  
- **Base de Datos:** MySQL  
- **Seguridad:** Spring Security + JWT (jjwt 0.12.6)  
- **Persistencia:** JPA / Hibernate  
- **Otros:** Lombok, Jakarta Validation  

## ⚙️ Configuración y Requisitos

### 1. Requisitos Previos
- JDK 21  
- MySQL Server  
- Maven (opcional por mvnw)  

### 2. Base de Datos

```
spring.datasource.url=jdbc:mysql://localhost:3306/reservasAula
spring.datasource.username=alumno
spring.datasource.password=alumno
```

Pasos:
1. Crear base de datos `reservasAula`
2. (Opcional) Importar dump SQL
3. Cambiar credenciales si es necesario

### 3. Ejecución

Windows:
```
./mvnw.cmd spring-boot:run
```

Linux/Mac:
```
./mvnw spring-boot:run
```

## 🔐 Seguridad

Autenticación mediante **JWT**.  
Enviar token con:
```
Authorization: Bearer <token>
```

Roles:
- **ROLE_PROFE:** Crear reservas  
- **ROLE_ADMIN:** Gestionar aulas y horarios  
- **Autenticado:** Consultas  

## 📡 Endpoints

### Autenticación
| Método | Endpoint | Descripción |
|-------|----------|-------------|
| POST | /auth/register | Registrar profesor |
| POST | /auth/register/admin | Registrar admin |
| POST | /auth/login | Login |

### Aulas
| Método | Endpoint | Rol |
|--------|----------|-----|
| GET | /aulas | Autenticado |
| GET | /aulas/{id} | Autenticado |
| POST | /aulas | ADMIN |
| PUT | /aulas/{id} | ADMIN |
| DELETE | /aulas/{id} | ADMIN |
| GET | /aulas/{id}/reservas | Autenticado |

### Horarios
| Método | Endpoint | Rol |
|--------|----------|-----|
| GET | /horarios | Autenticado |
| POST | /horarios | ADMIN |
| DELETE | /horarios/{id} | ADMIN |

### Reservas
| Método | Endpoint | Rol |
|--------|----------|-----|
| GET | /reservas | Autenticado |
| GET | /reservas/{id} | Autenticado |
| POST | /reservas | PROFE/ADMIN |
| DELETE | /reservas/{id} | Autenticado |

## 🧪 Pruebas

Se incluyen:
- `interfaz_segura.html` (con JWT y pruebas completas)
- `interfaz.html` (versión simple)

## 📝 Ejemplos JSON

### Aula
```json
{
  "nombre": "Laboratorio 3 (Robots)",
  "capacidad": 24,
  "esDeOrdenadores": true,
  "numeroOrdenadores": 24
}
```

### Horario
```json
{
  "dia": "JUEVES",
  "sesionDia": 3,
  "horaInicio": "13:30:00",
  "horaFim": "14:30:00",
  "tipo": "MEDIO_DIA"
}
```

### Reserva
```json
{
  "fechaReserva": "2026-03-13",
  "motivo": "Prácticas de Edición de Video",
  "numeroAsistentes": 15,
  "aulaId": 1,
  "horarioId": 1
}
```

## 📂 Estructura del Proyecto

```
controllers/     -> Controladores REST
services/        -> Lógica de negocio
entities/        -> Entidades JPA
DTOS/            -> Transferencia de datos
security/        -> JWT y filtros
```
