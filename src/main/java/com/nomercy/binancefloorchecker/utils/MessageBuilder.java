package com.nomercy.binancefloorchecker.utils;

import com.nomercy.binancefloorchecker.model.Message;
import com.nomercy.binancefloorchecker.model.binance.BinanceMessage;
import com.nomercy.binancefloorchecker.model.error.ErrorMessage;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class MessageBuilder {
    public static SendMessage buildMessage(Message message, Update update) {
        SendMessage sendMessage = null;

        if (message instanceof BinanceMessage) sendMessage = binanceMessageBuilder((BinanceMessage) message, update);
        if (message instanceof ErrorMessage) sendMessage = errorMessageBuilder((ErrorMessage) message, update);

        return sendMessage;
    }

    public static SendMessage binanceMessageBuilder(BinanceMessage binanceMessage, Update update) {
        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText("PRICE: " + binanceMessage.getContent());


        return sendMessage;
    }

    public static SendMessage errorMessageBuilder(ErrorMessage errorMessage, Update update) {
        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText(errorMessage.getContent());

        return sendMessage;
    }
}
