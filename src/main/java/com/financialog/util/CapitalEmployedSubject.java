package com.financialog.util;

import com.financialog.entity.TradeDetails;
import com.financialog.repository.TradeDetailsRepository;
import com.financialog.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CapitalEmployedSubject implements EventManager<Float>{

    @Autowired
    TradeDetailsRepository tradeDetailsRepository;

    @Autowired
    AuthenticationFacade authenticationFacade;

    List<EventListener<Float>> listeners = new ArrayList<>();

    @Override
    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    @Override
    public void unsubscribe(EventListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void notifySubscribers(Float newCapital) {
        listeners.forEach(listener -> listener.update(newCapital));
    }

    public void updateCapital(TradeDetails tradeDetails, Float tradeValue) {
        Optional<Float> totalTradeCapitalOpt = tradeDetailsRepository.getTotalTradeCapital(authenticationFacade.getLoggedInUser());
        tradeDetails.setTradeValue(tradeValue);
        Float percentageOfCapital = totalTradeCapitalOpt.map(tradeCap -> tradeValue * 100 / (tradeCap + tradeValue)).orElse(100f);
        tradeDetails.setPercentageOfCapital(percentageOfCapital);
        notifySubscribers(totalTradeCapitalOpt.map(tradeCap -> tradeCap + tradeValue).orElse(tradeValue));
    }
}
