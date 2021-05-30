package com.empPayroll;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class EmployeePayroll {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
        String userName = "root";
        String password = "nikhil123";

        Connection connection;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded");
        }
        catch(ClassNotFoundException e){
            throw new IllegalStateException("Class not found", e);
        }
        listdrivers();
        try{
            System.out.println("Connecting to database : " + jdbcURL);
            connection = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection Successful : " + connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void listdrivers(){
        Enumeration<Driver> driverlist = DriverManager.getDrivers();
        while(driverlist.hasMoreElements()){
            Driver driverClass = (Driver) driverlist.nextElement();
            System.out.println(" " + driverClass.getClass().getName());
        }
    }
}
