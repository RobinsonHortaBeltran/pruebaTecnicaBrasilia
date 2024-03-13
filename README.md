# Prueba tecnica

aplicación RESTful utilizando Java Spring Boot que gestione una 
lista de usuarios y sus tareas asociadas. Cada usuario tiene un nombre de usuario 
único y una lista de tareas. Cada tarea tiene un título, una descripción y un estado 
(pendiente o completada). La aplicación debe ser capaz de realizar las siguientes 
operaciones:
• Crear un nuevo usuario.
• Obtener la lista de todos los usuarios.
• Obtener un usuario por su ID.
• Crear una nueva tarea para un usuario específico.
• Obtener la lista de todas las tareas de un usuario.
• Marcar una tarea como completada para un usuario específico.
• Eliminar una tarea para un usuario específico.

Se desarrollo en el IDE INTELIJ
## Requisitos

- Java 21
- Maven

## Configuración

Asegúrate de tener instalado Java 21 y Maven en tu sistema.

## Instalación

1. Clona este repositorio:

```bash
git clone https://github.com/RobinsonHortaBeltran/pruebaTecnicaBrasilia.git

Navega al directorio del proyecto:
cd pruebaTecnicaBrasilia

Compila el proyecto con Maven:
```bash
mvn clean install

Ejecuta la aplicación:
```bash
mvn spring-boot:run

Accede a la aplicación en tu navegador web:
```bash
http://localhost:8000



