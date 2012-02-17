package com.systo.pizza.delivery.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;
import com.systo.pizza.delivery.shared.Pizza;
import com.systo.pizza.delivery.shared.Order;


@RemoteServiceRelativePath("rpc/user")
public interface UserService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

    List<Pizza> getOffers();

    void submitOrder(Order order);

    /**
     * Utility/Convenience class.
     * Use UserService.App.getInstance() to access static instance of UserServiceAsync
     */
    public static class App {
        private static UserServiceAsync ourInstance = GWT.create(UserService.class);

        public static synchronized UserServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
