package com.lidebeta.spi.service;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.lidebeta.spi.bean.Admin;
import com.lidebeta.spi.bean.Category;
import com.lidebeta.spi.bean.CoverageArea;
import com.lidebeta.spi.bean.Customer;
import com.lidebeta.spi.bean.DeliveryMan;
import com.lidebeta.spi.bean.Keywords;
import com.lidebeta.spi.bean.LatLng;
import com.lidebeta.spi.bean.Order;
import com.lidebeta.spi.bean.Product;
import com.lidebeta.spi.bean.Root;
import com.lidebeta.spi.bean.Store;

/**
 * Custom Objectify Service that this application should use.
 */
public class OfyService {
    /**
     * This static block ensure the entity registration.
     */
    static {
    	factory().register(CoverageArea.class);
    	factory().register(Store.class); 
    	factory().register(Category.class);
    	factory().register(Order.class);
    	factory().register(DeliveryMan.class);
    	factory().register(Customer.class);
    	factory().register(Admin.class);
    	factory().register(Root.class);
    	factory().register(Keywords.class);
    }

    /**
     * Use this static method for getting the Objectify service object in order to make sure the
     * above static block is executed before using Objectify.
     * @return Objectify service object.
     */
    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    /**
     * Use this static method for getting the Objectify service factory.
     * @return ObjectifyFactory.
     */
    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
