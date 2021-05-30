package com.empPayroll;

import java.sql.Connection;

public class EmployeePayroll {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql//:localhost:3306/payroll_service?useSSL=false";
        String userName = "root";
        String password = "nikhil123";

        Connection connection;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException e){
            throw new IllegalStateException("Class not found", e);
        }
    }



}
