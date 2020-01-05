package io.github.fatsquirrels.deuzum.IA.bots;

import io.github.fatsquirrels.deuzum.IA.bots.types.CleaningBot;
import io.github.fatsquirrels.deuzum.IA.bots.types.MailBot;

@Deprecated
public enum BotType {
	CleaningBot(new CleaningBot()),
	MailBot(new MailBot());
	
	private final BotBase botClass;
	
	private BotType(BotBase botFunctionality) {
		this.botClass = botFunctionality;
	}
	public BotBase getBotClass() {
		return this.botClass;
	}
}
