package com.example.ecomm;

import javafx.collections.ObservableList;

public class Order {


    public static boolean placeOrder(Customer customer, Product product){
        try{
            String query = "Insert into orders(customer_id,product_id,status) values("+customer.getId()+","+product.getId()+",'Ordered')";
            DatabaseConnection db = new DatabaseConnection();
            return db.insertUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static int placeBulkOrder(Customer customer, ObservableList<Product> product){
        int count=0;
        for(Product p : product){
            if(placeOrder(customer,p)) count++;
        }
        return  count;
    }
}
