package app;

import model.Manager;
import view.BotView;
import view.discord.bot.DiscordBotView;
import view.telegram.bot.TelegramBotView;
import view.vk.bot.VkBotView;

import java.security.NoSuchAlgorithmException;

public class Application {

    private BotView[] view = {
            new DiscordBotView(),
            new TelegramBotView(),
            VkBotView.getInstance()
    };

    private Manager manager;

    public Application() throws NoSuchAlgorithmException {
        manager = new Manager();
    }

    public static void main(String[] args) {
        //kk
    }
}