package com.kupreychik.notificationservice.config;

import com.kupreychik.notificationservice.conts.Constants;
import com.kupreychik.notificationservice.handler.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.telegram.abilitybots.api.objects.Locality.USER;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

@Component
public class OnlineShopBot extends AbilityBot {
    private final ResponseHandler responseHandler;

    @Autowired
    public OnlineShopBot(Environment env) {
        super(env.getProperty("botToken"), "zulubot");
        responseHandler = new ResponseHandler(silent, db);
    }

    @Override
    public long creatorId() {
        return 1L;
    }

    public void sendCustomMessage(Long chatId, String text) {
        responseHandler.customNotification(chatId, text);
    }

    public Ability startBot() {
        return Ability
                .builder()
                .name("start")
                .info(Constants.START_DESCRIPTION)
                .locality(USER)
                .privacy(PUBLIC)
                .action(ctx -> responseHandler.replyToStart(ctx.chatId()))
                .build();
    }

}