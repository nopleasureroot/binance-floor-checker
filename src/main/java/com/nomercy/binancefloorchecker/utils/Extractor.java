package com.nomercy.binancefloorchecker.utils;

import com.nomercy.binancefloorchecker.model.DefaultCommandModel;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.LinkedList;
import java.util.List;

public class Extractor {
    public static DefaultCommandModel extractCommandAndArgs(Message message) {
        DefaultCommandModel defaultCommandModel = new DefaultCommandModel();

        defaultCommandModel.setCommand(message.getEntities().get(0).getText());
        defaultCommandModel.setArgs(new LinkedList<>(List.of(message.getText().split(" ")).subList(1, message.getText().split(" ").length)));

        return defaultCommandModel;
    }
}
