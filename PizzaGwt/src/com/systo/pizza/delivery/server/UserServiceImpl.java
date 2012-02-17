package com.systo.pizza.delivery.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.systo.pizza.delivery.client.UserService;
import java.util.List;
import java.util.ArrayList;

import com.systo.pizza.delivery.shared.Order;
import com.systo.pizza.delivery.shared.Pizza;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;





public class UserServiceImpl extends RemoteServiceServlet implements UserService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }

    DB db;

    public UserServiceImpl() throws Exception {
        Mongo m = new Mongo( "localhost" , 27017 );

        db = m.getDB( "pizza-delivery-db" );
    }


    public List<Pizza> getOffers() {
        DBCollection coll = db.getCollection("offers");
        DBCursor cur = coll.find();

        List<Pizza> returnList = new ArrayList<Pizza>();

        while(cur.hasNext()) {
            DBObject object = cur.next();
            Pizza pizza = new Pizza();
            pizza.name = (String)object.get("name");
            pizza.price = (String)object.get("price");
            returnList.add(pizza);
        }


        return returnList;
    }

    @Override
    public void submitOrder(Order order) {
        DBObject object = new BasicDBObject();
        object.put("order person", order.name);
        object.put("order delivery address", getAddress(order));
        object.put("order pizza", order.orderedPizza.name);
        db.getCollection("orders").save(object);
    }

    private Object getAddress(Order order) {
        DBObject object = new BasicDBObject();
        object.put("street", order.deliveryAddress.street);
        object.put("houseNumber", order.deliveryAddress.houseNumber);
        return object;
    }


}