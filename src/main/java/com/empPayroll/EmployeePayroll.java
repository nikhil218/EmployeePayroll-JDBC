package com.empPayroll;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayroll {

    private Connection getConnection() throws SQLException{
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
        String userName = "root";
        String password = "nikhil123";

        Connection con;

        System.out.println("Connecting to database : " + jdbcURL);
        con = DriverManager.getConnection(jdbcURL, userName, password);
        System.out.println("Connection Successful : " + con);

        return con;
    }

    public List<EmployeePayrollData> readData() {
        String sql = "SELECT * FROM employee_payroll; ";
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                LocalDate date = resultSet.getDate("start").toLocalDate();
                employeePayrollList.add(new EmployeePayrollData(id, name, salary, date));
            }
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return employeePayrollList;
    }

    public void printData(ResultSet resultSet) {
        try {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                LocalDate date = resultSet.getDate("start").toLocalDate();
                System.out.println("\n");
                System.out.println("Id : " + id);
                System.out.println("Name : " + name);
                System.out.println("Start Date : " + date);
                System.out.println("Salary : " + salary);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int updateEmployeeSalary(String name, double salary) {
        String sql = String.format("UPDATE employee_payroll set salary = %.2f where name = '%s';", salary, name);
        try(Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sql);
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public int updateEmployeeSalaryPrepared(String name, double salary) {
        String sql = "UPDATE employee_payroll set salary = ? where name = ?;";
        try(Connection connection = this.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, salary);
            statement.setString(2, name);
            int result = statement.executeUpdate();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public List<EmployeePayrollData> retrieveEmployeeBetweenDateRange(String startDate, String endDate) {
        String sql = "SELECT * FROM employee_payroll WHERE start BETWEEN ? AND ? ";
        List<EmployeePayrollData> employeePayrollDataList = new ArrayList<>();
        try(Connection connection = this.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, java.sql.Date.valueOf(startDate));
            preparedStatement.setDate(2, java.sql.Date.valueOf(endDate));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                LocalDate date = resultSet.getDate("start").toLocalDate();
                employeePayrollDataList.add(new EmployeePayrollData(id, name, salary, date));
            }
            this.printData(resultSet);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employeePayrollDataList;
    }
}
