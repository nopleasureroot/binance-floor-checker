package com.nomercy.binancefloorchecker.utils;

import com.nomercy.binancefloorchecker.model.DefaultCommandModel;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;
import java.util.LinkedList;

public class Extractor {
    public static DefaultCommandModel extractCommandAndArgs(Message message) {
        DefaultCommandModel defaultCommandModel = new DefaultCommandModel();

        defaultCommandModel.setCommand(message.getEntities().get(0).getText());
        defaultCommandModel.setArgs(new LinkedList<>(Arrays.asList(message.getText().split(" ")).subList(1, message.getText().split(" ").length)));

        return defaultCommandModel;
    }
}
