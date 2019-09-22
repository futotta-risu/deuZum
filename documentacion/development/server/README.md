# Servidor

## Secciones

1. [Organizacion del Servidor](#servidor) 
2. [Como crear comandos](#crear-comandos)
3. [Como funciona el reconocimiento de comandos](#funcionamiento-del-reconocimiento-de-comandos)
4. [Sockets y su forma de proceso](#sockets)

## Crear Comandos

Para crear nuevos comandos, lo primero que necesitaremos, es tener una clase con la funcion que queremos agregar como comando. Por ahora, todas las funciones han de tener como argumentos un array de tipo string. A continuación abriremos el archivo data/methodList y agregaremos una linea al final del documento con la siguiente estructura:

`nombreDelComando paquete1.paquete2.***.nombreDeLaClase nombreDeLaFuncion`

En la siguiente compilación que se haga del servidor, el nuevo comando será funcional.

## Funcionamiento del Reconocimiento de Comandos

El funcionamiento interno de los comandos se produce dentro de la clase serverFunctionTreeHolder(**riesgo de cambio**). Dentro de esta clase hemos creado una interfaz *Commando* con la siguiente estructura:

~~~~ 
interface Command{

	String runCommand(String[] arr);
}
~~~~ 

Esta interfaz nos permite establecer una forma de ejecutar comandos desde el Hashmap<String, Command> el cual se declara de forma estatica. Posteriormente, se recorre linea a linea el archivo methodList, añadiendo a el mapa cada uno de los identificadores de los comandos junto a el metodo asociado:

~~~~ 
while ((line = br.readLine()) != null) {
	String[] sLine = line.split(" ");
	metodos.put(sLine[0], new Command() {
        public String runCommand(String[] arr) {
			try {
				Class<?> temp = Class.forName(sLine[1]);
				Method method = temp.getMethod(sLine[2], sLine.getClass()); 
                //sLine es de tipo Array<String>, de ahi el sLine.getClass()

				return (String) method.invoke(null, new Object[]{arr});
			} catch (ClassNotFoundException e) {
				...
			}
						
		return null;
    }});
}
~~~~ 


A partir de aquí solo necesitamos invocar el metodo desde el mapa:


`ServerFunctionTreeHolder.metodos.get(command).runCommand(arr)`