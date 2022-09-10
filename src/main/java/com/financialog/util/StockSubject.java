package com.financialog.util;

import com.financialog.entity.Stock;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StockSubject implements EventManager<Stock> {

    private List<EventListener> listeners = new ArrayList<>();



    @Override
    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    @Override
    public void unsubscribe(EventListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void notifySubscribers(Stock stock) {
        listeners.forEach(listener -> listener.update(stock));
    }

    public void updateStockPrice(Stock stock, Float newPrice) {
//        stock.setLastUpdatedPrice(newPrice);
        notifySubscribers(stock);
    }
}
