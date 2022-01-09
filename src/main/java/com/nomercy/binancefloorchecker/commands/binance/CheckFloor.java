package com.nomercy.binancefloorchecker.commands.binance;

import com.nomercy.binancefloorchecker.commands.DefaultCommand;
import com.nomercy.binancefloorchecker.model.DefaultCommandModel;
import com.nomercy.binancefloorchecker.model.binance.BinanceMessage;
import com.nomercy.binancefloorchecker.model.binance.BinanceRequest;
import com.nomercy.binancefloorchecker.utils.Constants;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

public class CheckFloor extends DefaultCommandModel implements DefaultCommand {
    private final RestTemplate restTemplate = new RestTemplate();

    public CheckFloor(String command, List<String> args) {
        super(command, args);
    }

    @Override
    public BinanceMessage executeCommand() {
        return sendRequestToBinance(this.getArgs());
    }

    private BinanceMessage sendRequestToBinance(List<String> args) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        BinanceRequest binanceRequest = new BinanceRequest();
        String url = Constants.BINANCE_POST_URL;

        HttpEntity<BinanceRequest> binanceRequestHttpEntity = new HttpEntity<>(binanceRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, binanceRequestHttpEntity, String.class);

        return processResponse(response);
    }

    private BinanceMessage processResponse(ResponseEntity<String> response) {
        BinanceMessage binanceMessage = null;

        try {
            JSONObject firstItem = new JSONObject(response.getBody()).getJSONObject("data").getJSONArray("data").getJSONObject(0);

            binanceMessage = new BinanceMessage(firstItem.getString("amount") + " " + firstItem.getString("currency"), firstItem.getString("coverUrl"));
        } catch (Exception ignored) {

        }

        return binanceMessage;
    }
}
