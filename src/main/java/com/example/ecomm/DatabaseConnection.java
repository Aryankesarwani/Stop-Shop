package com.example.ecomm;
import java.sql.*;

public class DatabaseConnection {

    String dburl = "jdbc:mysql://localhost:3306/ecommerce";
    String U_name = "root";
    String password = "Aryan@123";

    private Statement getStatement() {

        try{
            Connection conn = DriverManager.getConnection(dburl,U_name,password);
            return conn.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertUpdate(String query){
        Statement statement = getStatement();
        try{
            int result =  statement.executeUpdate(query);
            if(result > 0) return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }
    public boolean executeUpdate(String query){
        Statement statement = getStatement();
        try{
            int result =  statement.executeUpdate(query);
            if(result > 0) return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public ResultSet getQueryTable(String query){
        Statement statement =  getStatement();
        try{
            return statement.executeQuery(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String query = "Select * from products";
        DatabaseConnection dbcon = new DatabaseConnection();
        ResultSet rs = dbcon.getQueryTable(query);
        if(rs!=null){
            System.out.println("Connected to Database");
        }
    }

}
