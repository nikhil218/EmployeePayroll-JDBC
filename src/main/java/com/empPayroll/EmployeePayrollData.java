package com.empPayroll;

import java.util.Objects;
import java.time.LocalDate;

public class EmployeePayrollData {
    public int id;
    public String name;
    public double salary;
    public LocalDate date;

    public EmployeePayrollData(int id, String name, double salary, LocalDate date) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.date = date;
    }

    @Override
    public String toString() {
        return "EmployeePayrollData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeePayrollData that = (EmployeePayrollData) o;
        return id == that.id && Double.compare(that.salary, salary) == 0 && Objects.equals(name, that.name);
    }
}
