package com.example.ecomm;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {


    private static byte[] getSha(String input){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        }catch(Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    public static  String getEncryptedPassword(String password){
        try{
            BigInteger num = new BigInteger(1,getSha(password));
            StringBuilder hexString = new StringBuilder(num.toString(16));
            return hexString.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static Customer customerlogin(String UEmail, String Password) throws SQLException {
        String encryptedPass = getEncryptedPassword(Password);
        String query = "Select * from customers where email = '" +UEmail+ "' and password = '"+ encryptedPass +"'";
        DatabaseConnection dbcon;
        dbcon = new DatabaseConnection();
        try{
            ResultSet rs = dbcon.getQueryTable(query);
            if((rs != null) && rs.next()){
                return new Customer(rs.getInt("cid"),
                        rs.getString("name"),
                        rs.getString("email"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
//        System.out.println(customerlogin("18aryan2001@gmail.com", "Aryan@123"));
        System.out.println(getEncryptedPassword("Aryan@123"));
    }

}
