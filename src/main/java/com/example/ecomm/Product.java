package com.example.ecomm;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Product {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;

    public int getId(){
        return id.get();
    }
    public String getName(){
        return name.get();
    }
    public double getPrice(){
        return price.get();
    }

    public Product(int id, String name, Double price){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);

    }
    public static ObservableList<Product> getAllProducts(){
        String allProductList = "Select * from products";
        //String allProductList = "Select * from products where ";
        return getProducts(allProductList);
    }
    public static ObservableList<Product> getProducts(String query){
        DatabaseConnection dbcon = new DatabaseConnection();
        ObservableList<Product> result = FXCollections.observableArrayList();
        ResultSet rs = dbcon.getQueryTable(query);
        try{
            if(rs!=null){
                while(rs.next()){
                    result.add(new Product(rs.getInt("pid"),
                            rs.getString("name"),
                            rs.getDouble("price")
                            )
                    );
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
