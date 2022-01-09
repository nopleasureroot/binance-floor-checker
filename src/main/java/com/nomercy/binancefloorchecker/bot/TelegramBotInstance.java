package com.nomercy.binancefloorchecker.bot;

import com.nomercy.binancefloorchecker.model.DefaultCommandModel;
import com.nomercy.binancefloorchecker.model.Message;
import com.nomercy.binancefloorchecker.model.error.ErrorMessage;
import com.nomercy.binancefloorchecker.service.CommandsFabricService;
import com.nomercy.binancefloorchecker.service.ValidateService;
import com.nomercy.binancefloorchecker.utils.Extractor;
import com.nomercy.binancefloorchecker.utils.MessageBuilder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class TelegramBotInstance extends TelegramLongPollingBot {
    private final ValidateService validateService;
    private final CommandsFabricService commandsFabricService;

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

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (validateService.validate(update)) {
            DefaultCommandModel defaultCommandModel = Extractor.extractCommandAndArgs(update.getMessage());

            Message message = commandsFabricService.getCommand(defaultCommandModel).executeCommand();
            SendMessage sendMessage = MessageBuilder.buildMessage(message, update);

            execute(sendMessage);
        } else {
            Message message = new ErrorMessage("Please try again later \uD83E\uDD72");
            SendMessage sendMessage = MessageBuilder.buildMessage(message, update);

            execute(sendMessage);
        }
    }
}
