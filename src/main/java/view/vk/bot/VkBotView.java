package view.vk.bot;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;
import view.BotView;
import view.vk.refac.MockObject;
import view.vk.refac.RefacVk;

import java.util.Arrays;

public class VkBotView implements BotView {

    private static Group group;
    private static int chatId = 171052185;

    private String[][] timetable;

    private static VkBotView view;
//    private static Thread thread;

    public static VkBotView getInstance() {
        if(view == null){
            view = new VkBotView();
        }
        return view;
    }

    private VkBotView() {
        group = new Group(7240343, RefacVk.ACCESS_TOKEN);
        group.onSimpleTextMessage(VkBotView::onTextMessage);
    }

    private static void onTextMessage(Message message) {
        String[] text = message.getText().split("\\s+");

        chatId = message.authorId();
        System.out.println(chatId);

        if (text[0].equalsIgnoreCase("/getLesson")) {
            view.sendTimetableForSubs(view.timetable);
        }
    }

    @Override
    public void sendTimetableForSubs(String[][] timetable) {
        new Message().from(group)
                .to(chatId)
                .text(timetableToString(timetable))
                .send();
//        System.out.println("Vk bod v dele "); //TODO remove this shit
//        Arrays.stream(timetable)
//                .flatMap(Arrays::stream)
//                .forEach(System.out::println);
    }

    private String timetableToString(String[][] timetable) {
        StringBuilder res = new StringBuilder();

        for(int i = 0; i < timetable.length; i++){
            res.append(String.format(RefacVk.template[i], timetable[1][i]));
            System.out.println(timetable[1][i]);
            if(i == 0)
                res.append("\n");
        }

        return new String(res);
    }

    public void setTimetable(String[][] timetable) {
        this.timetable = timetable;
    }

    public String[][] getTimetable() {
        return timetable;
    }
}
