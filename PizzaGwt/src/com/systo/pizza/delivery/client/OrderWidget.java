package com.systo.pizza.delivery.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ValuePicker;
import com.google.gwt.user.client.ui.Widget;
import com.systo.pizza.delivery.shared.Address;
import com.systo.pizza.delivery.shared.Pizza;
import com.systo.pizza.delivery.shared.Order;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: stefandjurasic
 * Date: 17.02.12
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
public class OrderWidget extends Composite {

    interface MyUiBinder extends UiBinder<Widget, OrderWidget> {
    }

    private static MyUiBinder myUiBinder = GWT.create(MyUiBinder.class);

    @UiField (provided = true)
    ValuePicker<Pizza> offersPicker;

    public OrderWidget() {

        offersPicker = new ValuePicker<Pizza>(new Renderer<Pizza>() {
            @Override
            public String render(Pizza object) {
                return object.name;
            }

            @Override
            public void render(Pizza object, Appendable appendable) throws IOException {
                appendable.append(object.name);
            }
        });


        offersPicker.addValueChangeHandler(new ValueChangeHandler<Pizza>() {
            @Override
            public void onValueChange(ValueChangeEvent<Pizza> pizzaValueChangeEvent) {

                Order order = new Order();
                order.name = "Auftraggeber";
                order.orderedPizza = pizzaValueChangeEvent.getValue();
                Address address = new Address();
                address.houseNumber = "5";
                address.street = "Mozartrsraße";
                order.deliveryAddress = address;
                UserService.App.getInstance().submitOrder(order, new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(Void result) {
                        Window.alert("Pizza ordered");
                    }
                });
            }
        });

        initWidget(myUiBinder.createAndBindUi(this));


        UserService.App.getInstance().getOffers(new AsyncCallback<List<Pizza>>() {
            @Override
            public void onFailure(Throwable caught) {
                //
            }

            @Override
            public void onSuccess(List<Pizza> result) {
                offersPicker.setAcceptableValues(result);

            }
        });

    }

}