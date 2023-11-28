package com.example.model.entity;

import java.util.Date;

public class Student {
    private int studentId;
    private String studentName;
    private String email;
    private String address;
    private Date birthday;

    public Student() {
    }

    public Student(int studentId, String studentName, String email, String address, Date birthday) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
