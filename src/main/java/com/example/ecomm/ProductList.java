package com.example.ecomm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductList {

    public TableView<Product> productTable;

    public Pane getAllProduct(){
//        TableColumn id = new TableColumn("ID");
//        id.setCellValueFactory(new PropertyValueFactory<>("id"));
//
//
//        TableColumn name = new TableColumn("Name");
//        name.setCellValueFactory(new PropertyValueFactory<>("name"));
//
//        TableColumn price = new TableColumn("Price");
//        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        ObservableList<Product> productlist = Product.getAllProducts();
        return createTableToView(productlist);
    }
    public Pane createTableToView(ObservableList<Product> productlist){
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));


        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

//        ObservableList<Product> data = FXCollections.observableArrayList();
//        data.addAll(new Product(123,"Laptop",(double)45000.9),
//                new Product(124,"Laptop",(double)56000.09)
//        );

        productTable = new TableView<>();
        productTable.setItems(productlist);
        productTable.getColumns().addAll(id, name, price);
        Pane tablePane = new Pane();
        //tablePane.setPrefSize(10,10);
        tablePane.getChildren().add(productTable);

        return tablePane;
    }
    public Pane productsinCart(ObservableList<Product> productlist){
        return createTableToView(productlist);
    }

    public Product getSelectedPProduct(){
        return productTable.getSelectionModel().getSelectedItem();
    }
}
