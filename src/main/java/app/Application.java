package app;

import model.Manager;
import view.BotView;
import view.discord.bot.DiscordBotView;
import view.vk.bot.VkBotView;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.function.Consumer;

public class Application {

    private static BotView[] view = {
            new DiscordBotView(),
//            new TelegramBotView(),
            VkBotView.getInstance()
    };

    private Manager manager;

    public Application(Consumer<String[][]> consumer) throws NoSuchAlgorithmException {
        manager = new Manager(consumer);
        manager.start();
    }

    public static void main(String[] args) {
        try {
            Application app = new Application(Application::start);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static void start(String[][] schedule) {
        Arrays.stream(view)
                .forEach((s) -> s.sendTimetableForSubs(schedule));
    }
}