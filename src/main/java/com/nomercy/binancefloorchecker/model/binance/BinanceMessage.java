package com.nomercy.binancefloorchecker.model.binance;

import com.nomercy.binancefloorchecker.model.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BinanceMessage extends Message {
    private String image;
}
