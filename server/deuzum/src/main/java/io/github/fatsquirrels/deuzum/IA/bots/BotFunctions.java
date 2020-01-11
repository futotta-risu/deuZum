package io.github.fatsquirrels.deuzum.IA.bots;

/**
 * Interfaz que contiene las funciones relacionadas con los Bots
 */
public interface BotFunctions {
	
	/**
	 * Ejecuta el bot.
	 */
	public void execute();
	
	/**
	 * Pausa el bot durante un tiempo introducido.
	 * @param tiempo Tiempo a pausar el bot (segundos)
	 */
	public void stop(long tiempo);
	
	/**
	 * Elimina el bot.
	 */
	public void kill();
	
}
