package ro.robertpirvanus.telegrambot.newbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

/**
 * @author Robert Pirvanus
 * <p>
 * 5/8/2024
 */
public class TelegramBot extends TelegramLongPollingBot {
	private static final String BOT_TOKEN = "botname";
	private static final String BOT_NAME = "botkey";

	public TelegramBot() {
		super(BOT_TOKEN);
	}

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasCallbackQuery()) {
			CallbackQuery callbackQuery = update.getCallbackQuery();
			String data = callbackQuery.getData();

			// Respond to the callback query
			try {
				execute(AnswerCallbackQuery.builder()
						.callbackQueryId(callbackQuery.getId())
						.text("Cool")
						.build());
				return;
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
		if (update.hasMessage() && update.getMessage().hasText()) {
			String msgText = update.getMessage().getText();
			long chatId = update.getMessage().getChatId();

			System.out.printf("Received: (chatId: %s) %s\n", chatId, msgText);

			SendMessage message = new SendMessage();
			message.setChatId(chatId);
			message.setText("Ai scris: %s".formatted(msgText));


			InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
			inlineKeyboardButton.setText("Cool");
			inlineKeyboardButton.setCallbackData("/cool");

			InlineKeyboardButton inlineKeyboardButtonText = new InlineKeyboardButton();
			inlineKeyboardButtonText.setText(msgText);
			inlineKeyboardButtonText.setCallbackData("/test");

			KeyboardRow keyboardRow = new KeyboardRow();
			keyboardRow.add("Cool");
			keyboardRow.add(msgText);

			InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(List.of(List.of(inlineKeyboardButton, inlineKeyboardButtonText)));
			ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(List.of(keyboardRow));

			try {
//				message.setReplyMarkup(replyKeyboardMarkup);
				message.setReplyMarkup(inlineKeyboardMarkup);
				execute(message);
			} catch (TelegramApiException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public String getBotUsername() {
		return BOT_NAME;
	}

}
