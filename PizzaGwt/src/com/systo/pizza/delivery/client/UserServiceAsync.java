package com.systo.pizza.delivery.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

import com.systo.pizza.delivery.shared.Order;
import com.systo.pizza.delivery.shared.Pizza;

public interface UserServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);

    void getOffers(AsyncCallback<List<Pizza>> async);

    void submitOrder(Order order, AsyncCallback<Void> async);
}
