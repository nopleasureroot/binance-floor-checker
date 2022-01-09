package com.nomercy.binancefloorchecker.commands;

import com.nomercy.binancefloorchecker.model.Message;


public interface DefaultCommand {
    Message executeCommand();
}
