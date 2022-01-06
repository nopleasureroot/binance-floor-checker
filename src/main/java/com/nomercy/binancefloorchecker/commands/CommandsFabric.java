package com.nomercy.binancefloorchecker.commands;

import com.nomercy.binancefloorchecker.commands.binance.CheckFloor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class CommandsFabric {
    private CheckFloor checkFloor = new CheckFloor();

    public Command getCommand(String stringifyCommand) {
        Command command = null;

        switch (stringifyCommand.replace("/", "")) {
            case "binance" :
                command = checkFloor;
                break;
        }

        return command;
    }
}
