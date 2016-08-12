package com.app.mvp.retrofit;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import java.util.List;

public class CustomerList {


    @ElementList(entry = "CUSTOMER", inline = true)
    List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public static class Customer {
        @Text
        int value;
        @Attribute(name = "href")
        String attribute;

        public int getValue() {
            return value;
        }

        public String getAttribute() {
            return attribute;
        }

        @Override
        public String toString() {
            return "attribute: "+getAttribute()+" value: "+getValue();
        }
    }
}