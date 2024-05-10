package ro.robertpirvanus.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ro.robertpirvanus.telegrambot.newbot.TelegramBot;

/**
 * @author Robert Pirvanus
 * <p>
 * 5/8/2024
 */
public class Main {
	public static void main(String[] args) throws TelegramApiException {
		// Initialize Api Context
//		ApiContextInitializer.init();

		// Instantiate Telegram Bots API
		TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

		// Register our bot
		try {
			botsApi.registerBot(new TelegramBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
