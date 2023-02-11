package com.example.qualifiedwork.adminAccount;

public class DirectionRecord {
    String dateOfDirection, typeOfDirection, hospitalDirection, polisNum, patSecondName, patName, patFatherName, birthDate, patWorkPlace, diagnos, explain, docResponsStatus, docSecondName, docName, docFatherName;

    public DirectionRecord(String typeOfDirection, String hospitalDirection, String polisNum, String patSecondName, String patName, String patFatherName, String birthDate, String patWorkPlace, String diagnos, String explain, String docResponsStatus, String docSecondName, String docName, String docFatherName) {
        this.typeOfDirection = typeOfDirection;
        this.hospitalDirection = hospitalDirection;
        this.polisNum = polisNum;
        this.patSecondName = patSecondName;
        this.patName = patName;
        this.patFatherName = patFatherName;
        this.birthDate = birthDate;
        this.patWorkPlace = patWorkPlace;
        this.diagnos = diagnos;
        this.explain = explain;
        this.docResponsStatus = docResponsStatus;
        this.docSecondName = docSecondName;
        this.docName = docName;
        this.docFatherName = docFatherName;
    }

    public String getTypeOfDirection() {
        return typeOfDirection;
    }

    public void setTypeOfDirection(String typeOfDirection) {
        this.typeOfDirection = typeOfDirection;
    }

    public String getHospitalDirection() {
        return hospitalDirection;
    }

    public void setHospitalDirection(String hospitalDirection) {
        this.hospitalDirection = hospitalDirection;
    }

    public String getPolisNum() {
        return polisNum;
    }

    public void setPolisNum(String polisNum) {
        this.polisNum = polisNum;
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

    public String getPatWorkPlace() {
        return patWorkPlace;
    }

    public void setPatWorkPlace(String patWorkPlace) {
        this.patWorkPlace = patWorkPlace;
    }

    public String getDiagnos() {
        return diagnos;
    }

    public void setDiagnos(String diagnos) {
        this.diagnos = diagnos;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getDocResponsStatus() {
        return docResponsStatus;
    }

    public void setDocResponsStatus(String docResponsStatus) {
        this.docResponsStatus = docResponsStatus;
    }

    public String getDocSecondName() {
        return docSecondName;
    }

    public void setDocSecondName(String docSecondName) {
        this.docSecondName = docSecondName;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocFatherName() {
        return docFatherName;
    }

    public void setDocFatherName(String docFatherName) {
        this.docFatherName = docFatherName;
    }
}
