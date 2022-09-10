package com.financialog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financialog.dto.StockData;
import com.financialog.providers.JsonDeserializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.InvalidPropertiesFormatException;
import java.util.Optional;

@Component
@Qualifier("stockDataParser")
public class StockDataParser implements JsonDeserializer<StockData> {
    @Override
    public StockData parseObject(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(json);
            Optional<JsonNode> dataOpt = node.findValues("data").stream().findFirst();
            JsonNode data = dataOpt.orElseThrow(() -> new InvalidPropertiesFormatException("no data field in JSON"));
            StockData stockData = new StockData();
            stockData.setStockName(data.get(0).get("companyName").textValue())
                    .setCode(data.get(0).get("symbol").textValue())
                    .setCurrentPrice(data.get(0).get("lastPrice").textValue())
                    .setMetadata(data.get(0));
            return stockData;
        }  catch (JsonProcessingException | InvalidPropertiesFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
