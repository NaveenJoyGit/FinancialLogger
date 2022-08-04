package com.financialog.providers;

import com.financialog.providers.ApiParameter;

import java.util.List;

public class QuoteApiParameter implements ApiParameter {

    private List<String> symbols;

    public QuoteApiParameter(List<String> symbols) {
        this.symbols = symbols;
    }
    @Override
    public String getKey() {
        return "symbol";
    }

    @Override
    public String getValue() {
        return String.join(",", symbols);
    }
}
