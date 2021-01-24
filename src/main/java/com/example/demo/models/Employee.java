package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private int age;

    private int salary;

    private int workYears;

    private String title;

    public Employee() {}

    public Employee(String name, String surname, int age, int salary, int workYears, String title) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.salary = salary;
        this.workYears = workYears;
        this.title = title;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public int getSalary() { return salary; }

    public void setSalary(int salary) { this.salary = salary; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public int getWorkYears() { return workYears; }

    public void setWorkYears(int workYears) { this.workYears = workYears; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append(id);
        builder.append(", ");
        builder.append(name);
        builder.append(", ");
        builder.append(surname);
        builder.append(", ");
        builder.append(age);
        builder.append(", ");
        builder.append(salary);
        builder.append(", ");
        builder.append(workYears);
        builder.append(", ");
        builder.append(title);

        return builder.toString();
    }
}
