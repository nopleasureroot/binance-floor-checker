package com.nomercy.binancefloorchecker.model.binance;

import com.nomercy.binancefloorchecker.model.Message;
import lombok.Setter;

@Setter
public class BinanceMessage extends Message {
    private String image;

    public BinanceMessage(String content, String image) {
        super(content);
        this.image = image;
    }
}
