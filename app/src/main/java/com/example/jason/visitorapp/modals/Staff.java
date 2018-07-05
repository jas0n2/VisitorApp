package com.example.jason.visitorapp.modals;

public class Staff {
    private String employeeId;
    private  String employeeName;
    private String Department;

    public Staff(String employeeId, String employeeName,String Department) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.Department = Department;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
