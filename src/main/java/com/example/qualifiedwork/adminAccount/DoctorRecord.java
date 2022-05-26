package com.example.qualifiedwork.adminAccount;

public class DoctorRecord {
    String secondName, name, fatherName, birthDate, employDate, responsStatus, login, password;

    public DoctorRecord(String secondName, String name, String fatherName, String birthDate, String employDate, String responsStatus, String login, String password) {
        this.secondName = secondName;
        this.name = name;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.employDate = employDate;
        this.responsStatus = responsStatus;
        this.login = login;
        this.password = password;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmployDate() {
        return employDate;
    }

    public void setEmployDate(String employDate) {
        this.employDate = employDate;
    }

    public String getResponsStatus() {
        return responsStatus;
    }

    public void setResponsStatus(String responsStatus) {
        this.responsStatus = responsStatus;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
