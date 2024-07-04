package com.kupreychik.notificationservice.handler;

import com.kupreychik.notificationservice.conts.Constants;
import com.kupreychik.notificationservice.model.telegram.UserState;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Map;

import static com.kupreychik.notificationservice.conts.Constants.START_TEXT;
import static com.kupreychik.notificationservice.model.telegram.UserState.AWAITING_NAME;

public class ResponseHandler {
    private final SilentSender sender;
    private final Map<Long, UserState> chatStates;

    public ResponseHandler(SilentSender sender, DBContext db) {
        this.sender = sender;
        chatStates = db.getMap(Constants.CHAT_STATES);
    }

    public void replyToStart(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(START_TEXT);
        sender.execute(message);
        chatStates.put(chatId, AWAITING_NAME);
    }

    public void customNotification(Long chatId, String customMessage) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(customMessage);
        sender.execute(message);
        chatStates.put(chatId, AWAITING_NAME);
    }
}