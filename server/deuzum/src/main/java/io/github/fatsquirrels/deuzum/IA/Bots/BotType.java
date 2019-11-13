package io.github.fatsquirrels.deuzum.IA.Bots;

public enum BotType {
	CleaningBot(new CleaningBot());
	
	private final BotBase botClass;
	
	private BotType(BotBase botFunctionality) {
		this.botClass = botFunctionality;
	}
	public BotBase getBotClass() {
		return this.botClass;
	}
}
