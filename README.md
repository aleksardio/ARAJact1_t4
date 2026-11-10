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

## Despliegue en DigitalOcean

La aplicación está desplegada en un VPS Ubuntu de DigitalOcean a partir del [repositorio del proyecto](https://github.com/aleksardio/ARAJact1_t4).

| Configuración | Valor |
| --- | --- |
| IP pública | `204.48.17.197` |
| Puerto de la aplicación | `8082` |
| Java | `17` |
| Servicio systemd | `spring-act1-araj` |
| Ubicación del JAR | `/opt/spring-act1/act1-0.0.1-SNAPSHOT.jar` |

### Acceso público

- [Endpoint principal](http://204.48.17.197:8082/api/proyecto)
- [Lista de materias](http://204.48.17.197:8082/api/materias)
- [Total de materias](http://204.48.17.197:8082/api/materias/total)
- [Vista de ejemplo](http://204.48.17.197:8082/detalles_info)

Para desplegar una aplicación Spring Boot en Ubuntu, se compila como un JAR ejecutable, se transfiere al VPS con Java compatible y se configura un servicio systemd para ejecutarlo y administrarlo. El archivo JAR permite ejecutar la aplicación también en otro equipo que tenga una versión de Java compatible, por ejemplo, Java 17:

```bash
java -jar act1-0.0.1-SNAPSHOT.jar
```

### Administración y comprobación

Desde una terminal local, sustituye `<usuario>` por el usuario autorizado del VPS:

```bash
ssh <usuario>@204.48.17.197
```

Una vez conectado, consulta el servicio y sus registros:

```bash
sudo systemctl status spring-act1-araj
sudo journalctl -u spring-act1-araj -n 100 --no-pager
```

Para comprobar los endpoints desde una terminal con `curl`:

```bash
curl http://204.48.17.197:8082/api/proyecto
curl http://204.48.17.197:8082/api/materias
curl http://204.48.17.197:8082/api/materias/total
curl http://204.48.17.197:8082/detalles_info
```

### Actualización de la aplicación

1. En Windows, compila y ejecuta las pruebas desde la raíz del proyecto:

   ```powershell
   .\mvnw.cmd verify
   ```

2. Desde PowerShell, transfiere el nuevo JAR al directorio temporal del VPS, sustituyendo `<usuario>` por el usuario autorizado:

   ```powershell
   scp .\target\act1-0.0.1-SNAPSHOT.jar <usuario>@204.48.17.197:/tmp/act1-0.0.1-SNAPSHOT.jar
   ```

3. Conéctate por SSH y respalda el JAR actual. Detén únicamente el servicio de la Actividad 1, sustituye el archivo y vuelve a iniciarlo:

   ```bash
   sudo cp /opt/spring-act1/act1-0.0.1-SNAPSHOT.jar /opt/spring-act1/act1-0.0.1-SNAPSHOT.jar.bak
   sudo systemctl stop spring-act1-araj
   sudo cp /tmp/act1-0.0.1-SNAPSHOT.jar /opt/spring-act1/act1-0.0.1-SNAPSHOT.jar
   sudo systemctl start spring-act1-araj
   ```

4. Verifica el estado y los registros del servicio y comprueba los endpoints con los comandos anteriores.

La Actividad 2 funciona de forma independiente en el puerto `8084`. No debe modificarse ni interrumpirse durante el despliegue o la actualización de la Actividad 1.
