<img align="right" width="180" height="180" src="/data/img/logo.png?raw=true">

# DeuZum 
<div align="center"> 
<img src="https://img.shields.io/badge/Cliente-Android%20%7C%20Windows-brightgreen?style=flat-square"
      alt="Standard" />
      <img src="https://img.shields.io/badge/Servidor-Java-informational?style=flat-square"
      alt="Standard" />
      <img src="https://img.shields.io/badge/Java-8-yellow?style=flat-square&logo=java"
      alt="Standard" />
      <img src="https://img.shields.io/badge/Android_SDK-15-green?style=flat-square&logo=android"
      alt="Standard" />
</div>


Deuzum es un sistema de transacciones online estilo bizum que permite además la gestión de gastos grupales. 

======

## Instalación

El servidor requiere de algún servidor de SQL externo para el hosteo de la base de datos. En el, importaremos la base de datos situada en `data/databases/deuzumdb` (la versión que se prefiera). Una vez tengamos la base de datos preparada, descargamos la carpeta server de src y la situamos donde queramos que se encuentre nuestro servidor.

Para configurar el servidor nos dirigimos a `data/server.propierties`. Dentro de este archivo se encuentran un conjunto de atributos que deberemos cambiar:

  

|Propiedad     | Descripción                                                  |
|------------- | -------------------------------------------------------------|
|server.port   | Número del puerto que queramos usar para el servidor.        |
|server.dbName | Nombre de la base de datos que usamos para guardar los datos.|

Una vez configurado esto, podemos lanzar el server desde la carpeta `src/deustoZumServer` mediante el comando:

    java ServerHandlerFrame



## Caracteristicas

1. Bases de Datos como plantilla
2. Archivos de Configuración/Propiedades
3. Algoritmos para Clusterizar la información

## FAQ y Documentación

La sección de FAQ se encuentra dentro de la Wiki del repositorio. Por otro lado, la documentación oficial del código todavía no esta disponible, esperamos tenerla preparada para finales de diciembre.

## TODO

- Añadir una plantilla de la base de datos vacia