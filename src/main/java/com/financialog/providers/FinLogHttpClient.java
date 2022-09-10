package com.financialog.providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.function.Function;

@Component
public class FinLogHttpClient {


    Logger logger = LoggerFactory.getLogger(FinLogHttpClient.class);

    /**
     * Can accept any Function interface which returns an HttpResponse and accpets ApiParameter.
     * By calling apply method, the corresponding function provided at method call gets invoked
     * @param httpMethodResolver
     * @param apiParams
     * @return
     * @param <T>
     */
    public <T> HttpResponse<T> sendRequest(Function<List<ApiParameter>, HttpResponse<T>> httpMethodResolver, List<ApiParameter> apiParams) {
        return httpMethodResolver.apply(apiParams);
    }



}
