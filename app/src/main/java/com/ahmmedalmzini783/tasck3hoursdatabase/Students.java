package com.ahmmedalmzini783.tasck3hoursdatabase;

import java.io.Serializable;

public class Students implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String parthDay;

    public static final String TABLE_NAME = "students";
    public static final String COL_ID = "id";
    public static final String COL_FIRST_NAME = "firstName";
    public static final String COL_LAST_NAME = "lastName";
    public static final String COL_AGE = "age";
    public static final String COL_PARTH_DAY = "parthDay";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_FIRST_NAME + " TEXT, "
            + COL_LAST_NAME + " TEXT, "
            + COL_PARTH_DAY + " TEXT, "
            + COL_AGE + " INTEGER)";


    public Students(int id, String firstName, String lastName, int age, String parthDay) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.parthDay = parthDay;
    }



    public String getParthDay() {
        return parthDay;
    }

    public void setParthDay(String parthDay) {
        this.parthDay = parthDay;
    }

    public Students() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
