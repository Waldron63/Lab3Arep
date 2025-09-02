<img width="510" height="165" alt="image" src="https://github.com/user-attachments/assets/eac5f902-73a0-4eb4-a48c-c8c73b8e24f4" /># Taller de Arquitecturas de Servidores de Aplicaciones, Meta protocolos de objetos, Patrón IoC, Reflexión

La intención de este taller, es mostrar el desarrollo para la implementacion de un servidor Web en java (tipo APACHE) el cual entrega una página completa HTML con imagenes y diseño, al igual que aplicaciones web a partir de POJOS

## Contenidos iniciales

El servidor escanea y registra dinámicamente los controladores y rutas utilizando anotaciones personalizadas como:

- @RestController: Marca una clase como un controlador que manejará solicitudes HTTP.
- @GreetingController:Genera una ruta base para utilizar el controlador (tipo Endpoint).
- @GetMapping: Asocia métodos de un controlador con solicitudes GET.
- @RequestParam: Permite extraer parámetros de la URL en los métodos del controlador.

### Prerrequisitos

Para el correcto uso del servidor, es necesario tener las siguientes aplicaciones instaladas:
- JAVA
     ```sh
   java -version
   ```
- MAVEN
     ```sh
   mvn -v
   ```
- GIT
   ```sh
   git --version
   ```
(NOTA: si alguna de estas aplicaciones no fue instalada, ir a la página oficial de cada una e instalar la versión recomendada).

### Instalación
1. clonar el repositorio con el siguiente comando y ingresar a la carpeta en donde esta incluido el *pom.xml*:

   ```sh
   git clone https://github.com/Waldron63/Lab3Arep.git
   cd Lab3Arep
   ```

2. Construir el proyecto:

   ```sh
   mvn clean package
   ```
  La consola mostrará información parecida a:
  
  <img width="1448" height="278" alt="image" src="https://github.com/user-attachments/assets/44cf6f4f-3b2a-487b-8e7d-dd760367048f" />
  
3. Correr la aplicación:

   ```sh
   java -cp target/classes com.edu.escuelaing.httpserver.MicroSpringBoot com.edu.escuelaing.httpserver.anotations.GreetingController Starting MicroSpringBoot
   ```

   La consola debería mostrar el siguiente mensaje:

   ```sh
   Listo para recibir ...
   ```

   - Página principal:
     Una vez iniciado, en el buscador ingresar: "http://localhost:35000/" o "http://localhost:35000/index.html" y lo llevará a la página inicial del proyecto:

     <img width="1919" height="961" alt="image" src="https://github.com/user-attachments/assets/5c085ab6-c79b-49d6-9c27-8ddfd415b5d4" />

   - Archivos estáticos:
     Ingresar alguno de los siguientes comandos para mirar cada archivo estático
     ```bash
       http://localhost:35000/imgage.png
       http://localhost:35000/styles.css
       http://localhost:35000/script.js
       http://localhost:35000/form.html
       http://localhost:35000/index.html
     ```
   - servicios Rest:
     Ingresar el siguiente comando para mirar el servicio Greeting:
     ```bash
       http://localhost:35000/app/greeting?name=Santiago
       http://localhost:35000/app/greeting
     ```

## Arquitectura

<img width="824" height="665" alt="image" src="https://github.com/user-attachments/assets/215cb119-e341-448f-bed0-3db6a98b8894" />

La estructura del directorio del proyecto es:

<img width="943" height="759" alt="image" src="https://github.com/user-attachments/assets/54a6f47c-1698-4e6f-a26f-f0431d3a34d7" />

donde:

- MicroSpringBoot.java: programa de ejecución base.
- HttpServer.java: programa del servidor principal.
- HttpRequest, HttpResponse, Server: programas para el correcto funcionamiento de los servicios Rest.
- Resources/*: aplicación web(html, js, css y png).
- HttpServerTest.java: pruebas test del servidor para un funcionamiento correcto.

## Reporte de pruebas

**NOTA:** Si se desean correr las pruebas unitarias, es recomentable tener el servidor apagado y el puerto 35000 libre, de lo contrario puede generar error

### fecha

Fecha: 01/09/2025

### Pruebas unitarias:

<img width="1284" height="305" alt="image" src="https://github.com/user-attachments/assets/db1c30a1-5d42-46fc-986d-817621fb75ec" />

donde cada prueba unitaria (del archivo mainTest, las pruebas de HttpServerTest no cambiaron de la anterior versión) sirve para:
- shouldLoadStaticFileHtml(): comprobar que funciona el estándar o index principal de la aplicación web.
- shouldLoadStaticFileForm(): comprueba que el servidor acepta formato html.
- shouldLoadStaticFileCss(): comprueba que el servidor acepta formato css.
- shouldLoadStaticFileJs(): comprueba que el servidor acepta formato js.
- shouldLoadStaticImagePNG(): comprueba que el servidor acepta formato png y jpg.
- shouldLoadGreetingControllerWithQuery(): Comprueba que genera la petición Http con servicio RestController de forma adecuada, obteniendo el nombre de la persona y devolviendo la respuesta.
- shouldLoadGreetingControllerWithoutQuery(): Comprueba que genera la petición Http con servicio RestController de forma adecuada, sin el nombre de la persona y devolviendo la respuesta estandar.

### Pruebas de aceptación

- index.html:
  
  <img width="1560" height="950" alt="image" src="https://github.com/user-attachments/assets/deace7f1-80e0-456c-badd-4a6c64049f51" />

- servicio rest Greeting?name=Santiago:

  <img width="591" height="103" alt="image" src="https://github.com/user-attachments/assets/e8200e40-22d3-4d46-bd4c-f8726625090a" />

- servicio rest Greeting:

  <img width="510" height="165" alt="image" src="https://github.com/user-attachments/assets/0946cd18-6810-4b02-b28f-59b65cf42223" />
  
## Construido con

[Maven](https://maven.apache.org/index.html) - Dependency Management

[Git](https://git-scm.com) - Version Control System

## Autor

Santiago Gualdron Rincon - [Waldron63](https://github.com/Waldron63)
