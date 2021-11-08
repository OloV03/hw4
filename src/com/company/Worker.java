package com.company;

public class Worker {
    // личное
    private String name;
    private String surname;
    private int age;
    private char sex;
    public Cat cat; // public чтобы все знали, как крут этот кот!

    // работа
    private String nameOfDepartment;
    private String position;
    private int salary;
    private int prime; // если заслужил
    private int yearsInCompany;

    public Worker(String name, String surname, int age, char sex, String nameOfDepartment, String position, int salary, int yearsInCompany) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
        this.nameOfDepartment = nameOfDepartment;
        this.position = position;
        this.salary = salary;
        this.yearsInCompany = yearsInCompany;
    }

    public String getName() { return name; }

    public String getSurname() { return surname; }

    public int getAge() { return age; }

    public char getSex() { return sex; }

    public String getCatName() { return cat.getName(); }

    public String getNameOfDepartment() { return nameOfDepartment; }

    public String getPosition() { return position; }

    public int getSalary() { return salary; }

    public int getPrime() { return prime; }

    public int getYearsInCompany() { return yearsInCompany; }

    public void setCat(Cat cat) { this.cat = cat; }

    public void setPrime(int prime) { this.prime = prime; }

    public Cat getCat() { return cat; }
}

