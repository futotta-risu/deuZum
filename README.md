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


## Instalación

El servidor requiere de algún servidor de SQL externo para el hosteo de la base de datos. En el, importaremos la base de datos situada en `data/databases/pantilla/deuzumdbv2.2`. Una vez tengamos la base de datos preparada podemos lanzar el servidor.

Una vez configurado esto, podemos lanzar el server desde la carpeta `src/deustoZumServer` mediante el comando:

    java ServerLauncher

El resto de la configuración se puede realizar desde la propia pestaña de "configuración" del programa.


