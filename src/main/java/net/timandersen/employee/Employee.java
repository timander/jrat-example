package net.timandersen.employee;

public class Employee {

    private int employeeId = -1;

    private String name;

    private int age;

    private int employeeNumber;

    private String type;

    public Employee() {

    }

    public Employee(String name, int employeeNumber, int age, String type) {
        this.name = name;
        this.age = age;
        this.employeeNumber = employeeNumber;
        this.type = type;

    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Employee Details - ");
        sb.append("Name:" + getName());
        sb.append(", ");
        sb.append("Type:" + getType());
        sb.append(", ");
        sb.append("Id:" + getEmployeeNumber());
        sb.append(", ");
        sb.append("Age:" + getAge());
        sb.append(".");

        return sb.toString();
    }
}
