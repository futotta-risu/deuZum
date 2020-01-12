Una vez descargado el proyecto será necesario iniciar el servidor de xampp con los servicios de apache y MySQL en los puestos por defecto.

Una vez iniciados los servicios le damos al botón de config para acceder al localHost.

Nos dirigimos a la sección de phpMyAdmin y creamos una nueva base de datos que se llame como en el archivo server.properties, en este caso “deuzumdb” y a continuación le daremos a importar y seleccionaremos la base de datos: data/databases/plantilla/deuzumdbv2.2.sql

En el caso de que de esta manera no funcione, podemos copiar el codigo de la plantilla y pegarlo en la sección de sql del localhost. 

Una vez tengamos en marcha el servidor con la base de datos, importamos el proyecto entero en eclipse mediante la opción file, import, Existing Maven Projects y seleccionamos la carpeta /deuZum/server/deuzum.

Una vez importado, podremos iniciar el servidor mediante el botón play para acceder a todas las funcionalidades. 

·Podemos utilizar los bots que aparecen en la parte derecha, pulsando activar en cualquiera de ellos y añadiéndolos pulsando sobre el icono del bot.

En el caso del MailBot solicitara 2 archivos .txt:
	1º Contiene una dirección de correo electrónico destino por cada línea
    2º Contiene la cabecera del correo en la primera línea y el resto se incluyen en el cuerpo del mensaje.

Para el resto, excepto CleaningBot, se solicitara un numero de tuplas a introducir en la base de datos automáticamente por los bots.
Las claves externas de la base de datos nos crean una dependencia por lo que será necesario introducir ciertos datos antes que otros, podríamos considerar la siguiente jerarquía
		Usuario
		Cuenta
		Transaccion			
        Grupo
		Proyecto
		ProyectoTrasnaccion
·Podemos acceder al panel de Configuración para cambiar la configuración del servidor.
·Si vamos a la sección de Funcionalidades podemos encontrar opciones como:
		1º Visualizar graficos relacionados con la base de datos.
    2º Clusterizar la información de la Base datos y guardar los resultados en un archivo .bin .
·El resto de botones del menú nos muestran paneles con tablas que contienen información de la base de datos, podemos usar los botones de añadir, editar y eliminar para modificar la información de estas.