package com.nomercy.binancefloorchecker.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultCommandModel {
    private String command;
    private List<String> args;
}
