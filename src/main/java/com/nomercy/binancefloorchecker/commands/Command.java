package com.nomercy.binancefloorchecker.commands;

import com.nomercy.binancefloorchecker.model.Message;

import java.util.LinkedList;
import java.util.List;

public interface Command {
    Message executeCommand(String command, List<String> args);
}
