package io.github.fatsquirrels.deuzum.IA.Bots;

public class BotGenerator {

	public static BotBase generateBot(BotType botType, String name) {
		// Set the Specific Class of the Bot
		BotBase bot = botType.getBotClass();
		
		// Set the properties of the bot
		bot.setName(name);
		
		return bot;
	}
	
}
