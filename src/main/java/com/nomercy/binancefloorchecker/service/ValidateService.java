package com.nomercy.binancefloorchecker.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class ValidateService {
    public Boolean validate(Update update) {
        if (update != null) {
            if (messageNoEmpty(update)) {
                return checkMessageContent(update.getMessage());
            }

            return false;
        }

        return false;
    }

    private Boolean messageNoEmpty(Update update) {
        if (update.getMessage() != null) {
            return update.getMessage().getText() != null
                    && !update.getMessage().getText().isEmpty();
        }

        return false;
    }

    private Boolean checkMessageContent(Message message) {
        if (message.getText().startsWith("/")) {
            return message.getEntities() != null
                    && message.getEntities().get(0).getText() != null
                    && !message.getEntities().get(0).getText().isEmpty();
        }

        return false;
    }
}
