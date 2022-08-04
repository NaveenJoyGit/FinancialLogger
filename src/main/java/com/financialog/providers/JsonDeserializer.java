package com.financialog.providers;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface JsonDeserializer<T> {

    public T parseObject(String json);

}
