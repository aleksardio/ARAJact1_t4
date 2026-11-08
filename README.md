# Actividad 1: Programación Web

Aplicación Maven con Spring Boot 4.1.2, compilada para Java 17 utilizando Maven. El entorno local utiliza JDK 23 y el VPS ejecuta OpenJDK 17, Spring Web MVC y Thymeleaf. Conserva la vista existente de información del servidor y añade una API con datos simulados de materias; no utiliza base de datos.

## Ejecución local

Se necesita JDK 17 y conexión para descargar las dependencias de Maven. Desde la raíz del proyecto:

```powershell
.\mvnw.cmd spring-boot:run
```

En macOS o Linux, usar `./mvnw spring-boot:run`. La aplicación escucha en `http://localhost:8082`. La vista anterior está en `/detalles_info`.

Las iniciales se pueden personalizar mediante la propiedad `proyecto.iniciales`. Por ejemplo, en PowerShell:

```powershell
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.arguments=--proyecto.iniciales=ABC"
```

Si no se configura, se muestra `XX`.

## Endpoints

| Método y ruta | Respuesta |
| --- | --- |
| `GET /api/proyecto` | Texto con la actividad y las iniciales configuradas. |
| `GET /api/materias` | Arreglo JSON de materias simuladas, con `id`, `nombre` y `docente`. |
| `GET /api/materias/total` | Número entero con la cantidad de materias del mismo listado. |

Ejemplo de una materia: `{"id":1,"nombre":"Programación Web","docente":"Ana García"}`. El total actual es `4`.

## Compilación y pruebas

En Windows, ejecutar `.\mvnw.cmd verify`; en macOS o Linux, `./mvnw verify`. Las pruebas verifican la API y la vista existente.
