package com.systo.pizza.delivery.shared;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: stefandjurasic
 * Date: 17.02.12
 * Time: 20:06
 * To change this template use File | Settings | File Templates.
 */
public class Order implements Serializable{
    
    public Address deliveryAddress;
    public Pizza orderedPizza;
    public String name;
}
