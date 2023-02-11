package com.example.qualifiedwork.adminAccount.functional;

public class DocProtocolView {
    String date, patSecondName, patName, patFatherName, birthDate, polisCard, diagnos;

    public DocProtocolView(String date, String patSecondName, String patName, String patFatherName, String birthDate, String polisCard, String diagnos) {
        this.date = date;
        this.patSecondName = patSecondName;
        this.patName = patName;
        this.patFatherName = patFatherName;
        this.birthDate = birthDate;
        this.polisCard = polisCard;
        this.diagnos = diagnos;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPatSecondName() {
        return patSecondName;
    }

    public void setPatSecondName(String patSecondName) {
        this.patSecondName = patSecondName;
    }

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public String getPatFatherName() {
        return patFatherName;
    }

    public void setPatFatherName(String patFatherName) {
        this.patFatherName = patFatherName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPolisCard() {
        return polisCard;
    }

    public void setPolisCard(String polisCard) {
        this.polisCard = polisCard;
    }

    public String getDiagnos() {
        return diagnos;
    }

    public void setDiagnos(String diagnos) {
        this.diagnos = diagnos;
    }
}
