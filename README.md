<img align="right" width="200" height="80" src="/data/img/logo.png?raw=true">

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

El servidor requiere de algún servidor de SQL externo para el hosteo de la base de datos. En el, importaremos la base de datos situada en `data/databases/pantilla/deuzumdb`. Una vez tengamos la base de datos preparada, descargamos la carpeta server de src y la situamos donde queramos que se encuentre nuestro servidor.

Para configurar el servidor nos dirigimos a `data/server.propierties`. Dentro de este archivo se encuentran un conjunto de atributos que deberemos cambiar:

  

|Propiedad     | Descripción                                                  |
|------------- | -------------------------------------------------------------|
|server.port   | Número del puerto que queramos usar para el servidor.        |
|server.dbName | Nombre de la base de datos que usamos para guardar los datos.|

Una vez configurado esto, podemos lanzar el server desde la carpeta `src/deustoZumServer` mediante el comando:

    java ServerHandlerFrame

Para la instalación de la parte del cliente para desktop no se necesita nada más que el programa de eclipse para poder lanzar el programa. Mientras que para la app de android, en nuestro caso hemos usado android studio. Dentro del programa va a haver que instalarse un virtual device, para ello tenemos que pulsar el spinner que esta a la izquierda del boton de lanzar el programa  y selleccionar la opcion de `Open AVD Manager`. Despues para evitar tener promblemas se aconseja crear una nueva maquina virtualcon el nombre de Pixel 3 API 27. Despues de esos sencillos pasos el programa va ha estar preparado para klanzar el programa de Dezum.

## Caracteristicas

1. Bases de Datos como plantilla
2. Archivos de Configuración/Propiedades
3. Algoritmos para Clusterizar la información

## FAQ y Documentación

La sección de FAQ se encuentra dentro de la Wiki del repositorio. Por otro lado, la documentación oficial del código todavía no esta disponible, esperamos tenerla preparada para finales de diciembre.

## Bugs conocidos

- Al pulsar sobre "Activar bot" estando el servidor apagado, este se mantendrá en azul aun habiando saltado el error. Si luego se ejecuta el server y se vuelve a pulsar, la transicion se buggea no dejando ver los botones.
