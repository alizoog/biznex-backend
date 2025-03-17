package com.biznex.utils.tglog.messages;

import com.biznex.utils.tglog.MyTelegramBot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RequiredArgsConstructor
@Component
@Slf4j
public class MessageSender {

    private final MyTelegramBot bot;

    public void info(String message, Object... args) {
        log.info(message, args);
        SendMessage sendMessage = new SendMessage("7076510029", "Arrays.toString(args)");
        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
