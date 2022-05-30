package com.example.qualifiedwork.adminAccount;

public class PatientRecord {
    String secondName, name, fatherName, birthDate, address, login, password, medCard, snilsCard;

    public PatientRecord(String secondName, String name, String fatherName, String birthDate, String address, String login, String password, String medCard, String snilsCard) {
        this.secondName = secondName;
        this.name = name;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.address = address;
        this.login = login;
        this.password = password;
        this.medCard = medCard;
        this.snilsCard = snilsCard;
    }

    public PatientRecord(String secondName, String name, String fatherName, String birthDate, String address, String medCard, String snilsCard) {
        this.secondName = secondName;
        this.name = name;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.address = address;
        this.medCard = medCard;
        this.snilsCard = snilsCard;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getMedCard() {
        return medCard;
    }

    public void setMedCard(String medCard) {
        this.medCard = medCard;
    }

    public String getSnilsCard() {
        return snilsCard;
    }

    public void setSnilsCard(String snilsCard) {
        this.snilsCard = snilsCard;
    }
}
