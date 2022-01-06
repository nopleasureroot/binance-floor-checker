package com.nomercy.binancefloorchecker.model.binance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class BinanceRequest {
    private final int page = 1;
    private final Params params = new Params();
    private final int size = 16;

    @Data
    @AllArgsConstructor
    static class Params {
        private final String keyword = "";
        private final String nftType = "2";
        private final String orderBy = "amount_sort";
        private final String orderType = "1";
        private final List<String> serialNo = List.of("159142601537664000");
        private final String tradeType = "0";
    }
}
