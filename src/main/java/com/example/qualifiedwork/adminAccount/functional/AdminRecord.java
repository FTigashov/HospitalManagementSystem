package com.example.qualifiedwork.adminAccount.functional;

public class AdminRecord {
    String secondName, name, fatherName, birthDate, dateEmpl, responsStatus, login, password;

    public AdminRecord(String secondName, String name, String fatherName, String birthDate, String dateEmpl, String responsStatus, String login, String password) {
        this.secondName = secondName;
        this.name = name;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.dateEmpl = dateEmpl;
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

    public String getDateEmpl() {
        return dateEmpl;
    }

    public void setDateEmpl(String dateEmpl) {
        this.dateEmpl = dateEmpl;
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
