package com.financialog.providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

import static com.financialog.constants.HttpUrls.*;

@Service
public class NseHttpClient {

    Logger logger = LoggerFactory.getLogger(NseHttpClient.class);

    /**
     * Retrieves stock quote data from NSE website
     * @param params
     * @return
     */
    public HttpResponse<String> getStockQuoteData(List<ApiParameter> params) {
        String urlParams = getParams(params);
        String symbol = params.stream().map(param -> param.getValue()).collect(Collectors.joining());

        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("accept", "application/json")
                    .header("Referer", NSE_GET_QUOTES_REFERER + urlParams)
                    .uri(URI.create(NSE_GET_QUOTES_BASE_URL + urlParams))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info(response.body());
            return response;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getParams(List<ApiParameter> params) {
        StringBuilder paramBuilder = new StringBuilder();
        params.forEach(param -> paramBuilder.append("&")
                .append(param.getKey())
                .append("=")
                .append(param.getValue()));
        return paramBuilder.toString();
    }


}
