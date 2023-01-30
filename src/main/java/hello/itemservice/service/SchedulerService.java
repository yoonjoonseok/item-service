package hello.itemservice.service;

import hello.itemservice.domain.Item;
import hello.itemservice.login.domain.member.MemberRepository;
import hello.itemservice.repository.ItemSearchCond;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final ItemService itemService;
    private final MemberRepository memberRepository;

    @Value("${telegram-bot-key}")
    private String telegram_bot_key;

    @Scheduled(fixedDelay = 300000) // 5분마다 실행
    public void run() {
        List<Item> items = itemService.findItems(new ItemSearchCond());
        for (Item item : items) {
            Integer result = itemService.update(item.getId());
            if (-1<result) {
                getChatId(1, result, item);
            }
        }
    }

    public void getChatId(Integer preQuantity, Integer postQuantity, Item item) {
        String msg;
        List<String> chatIdList = memberRepository.findChatId(item.getId());
        for (String chatId : chatIdList) {
            if (preQuantity == 0)
                funcTelegram(item.getItemName() + "이 재입고 되었습니다", chatId);
            funcTelegram(item.getItemName() + " - 남은 재고: " + postQuantity, chatId);
        }
    }

    public void funcTelegram(String msg, String chatId) {

        String chat_id = chatId;
        String text = msg;

        BufferedReader in = null;

        try {
            URL obj = new URL("https://api.telegram.org/bot" + telegram_bot_key + "/sendmessage?chat_id=" + chat_id + "&text=" + text); // 호출할 url

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String line;

            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
