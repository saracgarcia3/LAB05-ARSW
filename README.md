# ​ 📚 LAB05- BluePrints Parte I

**📋​ Funcionalidades**

- Buscar autores: Ingresando el nombre del autor (por ejemplo, "sara"), el sistema muestra los planos disponibles para ese autor.
- Visualización de planos: Los planos se presentan en una tabla, mostrando el nombre del plano y el número de puntos que lo componen.
- Dibujo en el canvas: Al hacer clic en Open se dibujan los planos en un canvas, permitiendo visualizar las formas que componen cada plano.
- Cálculo total de puntos: Se muestra un total de puntos al sumar los puntos de cada plano.

**👩🏿‍💻​ Tecnologías** 

- Back-End: Spring Boot para crear la aplicación.
- Maven para gestión de dependencias y construcción del proyecto.
- Front-End: HTML5 y CSS3 con Bootstrap para la interfaz de usuario.
- JavaScript (con jQuery) para la lógica y manipulación del DOM.

**📋​ ¿Como funciona?**

**Front-End:**

- El usuario ingresa un autor en el campo de texto y presiona Get blueprints.
- El sistema busca los planos correspondientes al autor, los muestra en una tabla y actualiza el total de puntos.
- Al hacer clic en Open en cualquiera de los planos, el plano se dibuja en el canvas usando los puntos que están definidos en los datos.

**Back-End:**

- La aplicación Spring Boot sirve el contenido estático desde la carpeta src/main/resources/static, donde se encuentran los archivos index.html, app.js y apimock.js.
- 

**▶️​ Ejecución y pruebas**

1. Se ejecuta el proyecto con el siguiente comando Maven:
  
```java
mvn spring-boot:run
```
2. Para probar que funciona usamos las siguientes URL:

- http://localhost:8080/: Los usuarios pueden ingresar el nombre de un autor y hacer clic en el botón "Get blueprints" para cargar y visualizar los planos del autor seleccionado. Los planos se muestran en una tabla, y al hacer clic en "Open", se dibujan en un canvas.

<p align="center">
<img width="1146" height="571" alt="image" src="https://github.com/user-attachments/assets/659db25d-0048-47d5-b5c5-f7cd18ec0cdd" />
</p>
<p align="center">
<img width="1148" height="566" alt="image" src="https://github.com/user-attachments/assets/95a49d2e-77ac-4969-9a46-e8200df28661" />
</p>
<p align="center">
<img width="1127" height="560" alt="image" src="https://github.com/user-attachments/assets/322f1583-eba0-4cdc-82fc-f62dbe8e3182" />
</p>

- http://localhost:8080/js/apimock.js: Contiene los datos simulados de los planos. Aquí se define el conjunto de datos de prueba para diferentes autores (como "sara" y "jhon"), y se proveen métodos para obtener los planos de un autor en específico.

<p align="center">
<img width="669" height="656" alt="image" src="https://github.com/user-attachments/assets/15cf6840-2570-4aef-a6b4-55dad7e53371" />
</p>

- http://localhost:8080/js/app.js: Maneja la lógica de la aplicación. Contiene el código que gestiona la interacción con el usuario, como la actualización de la tabla de planos, el cálculo del total de puntos y el dibujo de los planos en el canvas al hacer clic en el botón "Open".

<p align="center">
<img width="617" height="644" alt="image" src="https://github.com/user-attachments/assets/f4a608c6-6383-4452-8f50-c5162ac3bf27" />
</p>
  


