package com.example.ecomm;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Signup {
    Login login = new Login();


    public static boolean customerSignup(String user, String mobile, String email, String addr, String password) throws SQLException {
        String encryptedPass = Login.getEncryptedPassword(password);
        int mob = Integer.parseInt(mobile);
        String query = "Insert into customers (name,mobile,email,address,password) VALUES ('"+user+"','"+mob+"','"+email+"','"+addr+"','"+encryptedPass+"')";
        DatabaseConnection dbcon;
        dbcon = new DatabaseConnection();
        return dbcon.executeUpdate(query);
    }
    public static void main(String[] args){
        try {
            System.out.println(customerSignup("Ashish","123456789","Ashish@gmail.com","Varanasi","Ashish@123"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
