package com.empPayroll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class EmployeePayrollServiceTest {
    @Test
    public void givenEmployeePayrollCount_ShouldMatchDatabaseCount() throws SQLException {
        EmployeePayroll employeePayroll = new EmployeePayroll();
        List<EmployeePayrollData> employeePayrollData = employeePayroll.readData();
        Assertions.assertEquals(3, employeePayrollData.size());
    }

    @Test
    public void givenNewSalaryShouldMatchUpdatedSalary() {
        EmployeePayroll employeePayroll = new EmployeePayroll();
        int result = employeePayroll.updateEmployeeSalary("bill", 500000);
        Assertions.assertEquals(1, result);
    }

    @Test
    public void givenNewSalaryShouldMatchUpdatedSalary_UsingPreparedStatement() {
        EmployeePayroll employeePayroll = new EmployeePayroll();
        int result = employeePayroll.updateEmployeeSalaryPrepared("Terisa", 50000);
        Assertions.assertEquals(1, result);
    }

    @Test
    public void givenStatement_shouldReturn_employeeDetails_between_givenDateRange() {
        EmployeePayroll employeePayroll = new EmployeePayroll();
        List<EmployeePayrollData> employeePayrollDataList = employeePayroll.retrieveEmployeeBetweenDateRange("2017-01-01", "2019-06-06");
        Assertions.assertEquals(2, employeePayrollDataList.size());
    }

    @Test
    public void get_sum_from_employeePayroll_table() {
        EmployeePayroll employeePayroll = new EmployeePayroll();
        List<String> list = employeePayroll.dataManipulation();
        Assertions.assertEquals(12, list.size());
    }
}
