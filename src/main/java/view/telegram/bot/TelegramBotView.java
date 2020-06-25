package view.telegram.bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import view.BotView;
import view.telegram.commands.TelegramListener;

public class TelegramBotView implements BotView {

    TelegramListener listener;

    public TelegramBotView() {
        ApiContextInitializer.init();
        TelegramBotsApi bot = new TelegramBotsApi();
        listener = new TelegramListener();
        try {
            bot.registerBot(listener);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendTimetableForSubs(String[][] timetable) {
        listener.sendTimetableForSubs(timetable);
    }
}