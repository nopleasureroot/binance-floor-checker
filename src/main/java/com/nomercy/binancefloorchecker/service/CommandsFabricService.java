package com.nomercy.binancefloorchecker.service;

import com.nomercy.binancefloorchecker.commands.DefaultCommand;
import com.nomercy.binancefloorchecker.commands.binance.CheckFloor;
import com.nomercy.binancefloorchecker.model.DefaultCommandModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class CommandsFabricService {
    private CheckFloor checkFloor = null;

    public DefaultCommand getCommand(DefaultCommandModel defaultCommandModel) {
        DefaultCommand defaultCommand = null;

        switch (defaultCommandModel.getCommand()) {
            case "/binance" :
                if (checkFloor == null || checkFloor.getCommand() == null || checkFloor.getArgs() == null) {
                    checkFloor = new CheckFloor(defaultCommandModel.getCommand(), defaultCommandModel.getArgs());
                }

                defaultCommand = checkFloor;
                break;
        }

        return defaultCommand;
    }
}
