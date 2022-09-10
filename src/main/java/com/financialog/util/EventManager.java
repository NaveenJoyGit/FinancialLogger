package com.financialog.util;

import java.util.ArrayList;
import java.util.List;

public interface EventManager<T> {

    public void subscribe(EventListener listener);

    public void unsubscribe(EventListener listener);

    public void notifySubscribers(T t);

}
