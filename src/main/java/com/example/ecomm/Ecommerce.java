package com.example.ecomm;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class Ecommerce extends Application
{
    //The entry point for JavaFX applications is the Application class

    Login login = new Login();
    Signup signin = new Signup();
    private final int width =500,height = 400,headerLine = 50;
    ProductList productlist = new ProductList();

    Pane bodyPane;

    ObservableList<Product> cartItemList = FXCollections.observableArrayList();
    Label welcomeLabel = new Label("Welcome Customer");


    Customer loggedIncustomer = null;


    private void addItemToCart(Product product){
        if(cartItemList.contains(product)){
            return;
        }
        cartItemList.add(product);
        System.out.println("Product in Cart "+ (long) cartItemList.size());
    }

    private GridPane Headerbar(){

        //header.setBackground(background);
//        header.setTranslateX(10);
//        header.setHgap(11);
        TextField searchbar = new TextField();
        Button searchbutton = new Button("Search");
        Button loginBUtton = new Button("Login");
        Button cartButton = new Button("Cart");
        loginBUtton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(LoginPage());
            }
        });
        Button signupButton = new Button("SignUp");
        signupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(SigninPage());
            }
        });
        searchbutton.setOnAction(new EventHandler<ActionEvent>() {
//            Dialog<String> dialog = new Dialog<String>();
            @Override
            public void handle(ActionEvent actionEvent) {
                if(loggedIncustomer!=null){
//                    while(searchbar.hasProperties()){
//                        bodyPane.getChildren().clear();
//                        bodyPane.getChildren().add(productlist.getAllProduct(searchbar.getText()));
//                    }
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productlist.getAllProduct());
                }
                else{
//                    dialog.setContentText("Please Login First!");
//                    dialog.showAndWait();
                    welcomeLabel.setText("Please login First");
                }
            }
        });
        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productlist.productsinCart(cartItemList));
            }
        });

        GridPane header = new GridPane();
        header.setHgap(10);
        header.add(searchbar, 0, 0);
        header.add(searchbutton,1,0);
        header.add(loginBUtton,2,0);
        header.add(signupButton,3,0);
        header.add(welcomeLabel,4,0);
        header.add(cartButton,5,0);
        return header;
    }

    private GridPane SigninPage(){
        Label userlabel = new Label("User Name");
        Label emaillabel = new Label("Email");
        Label mobilelabel = new Label("Mobile");
        Label addrlabel = new Label("Address");
        Label passlabel = new Label("Password");
        TextField userName = new TextField();
        userName.setPromptText("Enter User Name");
        TextField email = new TextField();
        email.setPromptText("xyz@email.com");
        TextField mobile = new TextField();
        mobile.setPromptText("1234567890");
        TextField address = new TextField();
        address.setPromptText("Full Address");
        PasswordField password = new PasswordField();
        password.setPromptText("Enter Password");

        Button signinButton = new Button("Signin");
        Label messagelabel = new Label("Sign in - Message");

        signinButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = userName.getText();
                String mob = mobile.getText();
                String e_mail = email.getText();
                String addr = address.getText();
                String pass = password.getText();
                try{
                    if(Signup.customerSignup(username,mob,e_mail,addr,pass)){
                        messagelabel.setText("SignUp Successful");
                    }
                    else messagelabel.setText("SignUp Failed");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });



        GridPane signinPane = new GridPane();

        signinPane.setTranslateY(50);
        signinPane.setTranslateX(50);
        signinPane.setHgap(10);
        signinPane.setVgap(10);
        signinPane.add(userlabel,0,0);
        signinPane.add(userName,1,0);
        signinPane.add(mobilelabel,0,1);
        signinPane.add(mobile,1,1);
        signinPane.add(emaillabel,0,2);
        signinPane.add(email,1,2);
        signinPane.add(addrlabel,0,3);
        signinPane.add(address,1,3);
        signinPane.add(passlabel,0,4);
        signinPane.add(password,1,4);
        signinPane.add(signinButton,0,5);
        signinPane.add(messagelabel,1,5);

        return signinPane;
    }
    private GridPane LoginPage(){
        Label user = new Label("User Email");
        Label passlabel = new Label("Password");
        TextField userName = new TextField();
        userName.setPromptText("Enter User Email");
        PasswordField password = new PasswordField();
        password.setPromptText("Enter Password");
        Button loginButton = new Button("Login");
        Label messagelabel = new Label("Login - Message");
        userName.setText("18aryan2001@gmail.com");
        password.setText("Aryan@123");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String user = userName.getText();
                String pass = password.getText();
                try {
                    loggedIncustomer = Login.customerlogin(user,pass);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if(loggedIncustomer!=null){
                    messagelabel.setText("Login Successful");

                    showDialogue("Login Successful","Login Status");
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productlist.getAllProduct());
                    welcomeLabel.setText("Welcome "+loggedIncustomer.getName());
                }
                else{
                    showDialogue("Login Failed!....","Login Status");
                    messagelabel.setText("Login Failed");
                }
            }
        });





        GridPane loginPane = new GridPane();

        loginPane.setTranslateY(50);
        loginPane.setTranslateX(50);
        loginPane.setHgap(10);
        loginPane.setVgap(10);
        loginPane.add(user,0,0);
        loginPane.add(userName,1,0);
        loginPane.add(passlabel,0,1);
        loginPane.add(password,1,1);
        loginPane.add(loginButton,0,2);
        loginPane.add(messagelabel,1,2);


        return loginPane;



    }

    private void showDialogue(String message, String status){
        Dialog<String> dialog = new Dialog<String>();
        //Setting the title
        dialog.setTitle(status);
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setContentText(message);
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);
        //Setting the label

        dialog.showAndWait();
    }
    private  GridPane footerBar(){
        Button buyButton = new Button("Buy Now");
        Button addtoCart = new Button("Add To Cart");
        Button placeorderButton = new Button("Place Order");
        buyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product;
                product = productlist.getSelectedPProduct();
                boolean orderstatus = false;
                if(product!=null && loggedIncustomer!=null){
                    Order order = new Order();
                    orderstatus = order.placeOrder(loggedIncustomer,product);
                }
                if(orderstatus == true){
                    //
                    showDialogue("Order Successful","Order Status");
                }
                else{
                    showDialogue("Order Failed","Order Status");
                }
            }
        });
        addtoCart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productlist.getSelectedPProduct();
                addItemToCart(product);
            }
        });
        placeorderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                int ordercount = 0;
                if(!cartItemList.isEmpty() && loggedIncustomer!=null){
                    Order order = new Order();
                    ordercount = order.placeBulkOrder(loggedIncustomer,cartItemList);
                }
                if(ordercount>0){
                    //
                    showDialogue("Order Successful","Order Status");
                }
                else{
                    showDialogue("Order Failed","Order Status");
                }
            }
        });
        GridPane footer = new GridPane();
        footer.setHgap(10);
        footer.setTranslateY(headerLine+height);
        footer.add(buyButton,0,0);
        footer.add(addtoCart,1,0);
        footer.add(placeorderButton,2,0);
        return footer;
    }
    private Pane createContent(){
         Pane root = new Pane();
         root.setPrefSize(width ,height +2*headerLine);

         bodyPane = new Pane();
         bodyPane.setPrefSize(width,height);
         bodyPane.setTranslateY(headerLine);
         bodyPane.setTranslateX(10);

         bodyPane.getChildren().add(LoginPage());

        root.getChildren().addAll(Headerbar()
         //, LoginPage(),
        // productlist.getAllProduct()
                ,bodyPane
                ,footerBar()
        );
         return root;
    }

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(Ecommerce.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());//fxmlLoader.load(), 320, 240);
        stage.setTitle("Ecommerce");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {

        launch();
    }
}