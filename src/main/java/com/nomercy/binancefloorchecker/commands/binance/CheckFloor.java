package com.nomercy.binancefloorchecker.commands.binance;

import com.nomercy.binancefloorchecker.commands.Command;
import com.nomercy.binancefloorchecker.model.binance.BinanceMessage;
import com.nomercy.binancefloorchecker.model.Message;
import com.nomercy.binancefloorchecker.model.binance.BinanceRequest;
import com.nomercy.binancefloorchecker.utils.Constants;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class CheckFloor implements Command {
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public BinanceMessage executeCommand(String command , List<String> args) {
        return sendRequestToBinance();
    }

    private BinanceMessage sendRequestToBinance() {
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
        BinanceMessage binanceMessage = new BinanceMessage();

        try {
            JSONObject firstItem = new JSONObject(response.getBody()).getJSONObject("data").getJSONArray("data").getJSONObject(0);

            binanceMessage.setContent(firstItem.getString("amount") + " " + firstItem.getString("currency")) ;
            binanceMessage.setImage(firstItem.getString("coverUrl"));
        } catch (Exception ignored) {

        }

        return binanceMessage;
    }
}
