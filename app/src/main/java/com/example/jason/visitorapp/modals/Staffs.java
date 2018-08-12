package com.example.jason.visitorapp.modals;

public class Staffs {

    private String Id;
    private String name;
    private String number;
    private String department;
    private String email;

    public Staffs(String id, String name, String number, String department, String email) {
        Id = id;
        this.name = name;
        this.number = number;
        this.department = department;
        this.email = email;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
