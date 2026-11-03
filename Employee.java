package com.employee.model;

public class Employee {

    private int id;
    private String name;
    private double salary;
    private int deptid;
    private int age;
    private String city;

    public Employee() {
    }

    public Employee(int id, String name, double salary,
                    int deptid, int age, String city) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.deptid = deptid;
        this.age = age;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDeptid() {
        return deptid;
    }

    public void setDeptid(int deptid) {
        this.deptid = deptid;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
