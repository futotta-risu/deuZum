package io.github.fatsquirrels.deuzum.IA.bots;

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
