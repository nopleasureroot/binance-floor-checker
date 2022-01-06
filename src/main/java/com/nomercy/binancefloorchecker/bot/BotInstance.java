package com.nomercy.binancefloorchecker.bot;

import com.nomercy.binancefloorchecker.commands.Command;
import com.nomercy.binancefloorchecker.commands.CommandsFabric;
import com.nomercy.binancefloorchecker.model.Message;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class BotInstance extends TelegramLongPollingBot {
    private final CommandsFabric commandsFabric = new CommandsFabric();

    @Value("${bot.name}")
    private String name;

    @Value("${bot.token}")
    private String token;

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            List<String> args = null;
            String command = null;

            if (update.getMessage() != null
                    && !update.getMessage().getText().isEmpty()
                    && update.getMessage().getText() != null)
            {
                if (update.getMessage().getEntities() != null) {
                    List<String> splitText = List.of(update.getMessage().getText().split(" "));

                    if (splitText.size() > 1) {
                        command = splitText.get(0);
                        args = splitText.subList(1, splitText.size());
                    }

                    assert command != null;
                    Message messageModel = commandsFabric.getCommand(command.replace("/", "")).executeCommand(command, args);

                    SendMessage message = new SendMessage();

                    message.setText("PRICE"
                            + "\n" +
                            messageModel.getContent()
                            + "\n" +
                            messageModel.getImage());
                    message.setChatId(update.getMessage().getChat().getId().toString());

                    execute(message);
                } else if (Objects.requireNonNull(update.getMessage()).getText().equals("Привет")
                    && update.getMessage().getFrom().getId() == 1140233050L
                ) {
                    SendMessage message = new SendMessage();
                    message.setText("Привет, Сонечка! ❤️ \uD83D\uDC8B");
                    message.setChatId(update.getMessage().getChat().getId().toString());

                    execute(message);
                }
            } else if (Objects.requireNonNull(update.getMessage()).getText().equals("Привет")
            ) {
                String chatId = Objects.requireNonNull(update.getMessage()).getChat().getId().toString();
                SendMessage message = new SendMessage();

                message.setText("Привет сонечка");
                message.setChatId(chatId);
                message.setReplyToMessageId(update.getMessage().getMessageId());

                execute(message);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
