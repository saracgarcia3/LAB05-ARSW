# ​ 📚 Construción de un cliente 'grueso' con un API REST, HTML5, Javascript y CSS3. Parte I.

1. Empezamos clonando y comprobando el anterior laboratorio 4: 

  
```java
git clone https://github.com/saracgarcia3/LAB04-ARSW
cd LAB04-ARSW
mvn clean package -DskipTests
mvn spring-boot:run
```

2. Agregamos las dependencias que se nos dice en el laboratorio: 

```java
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>webjars-locator</artifactId>
</dependency>

<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>bootstrap</artifactId>
    <version>3.3.7</version>
</dependency>

<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>jquery</artifactId>
    <version>3.1.0</version>
</dependency>          
```