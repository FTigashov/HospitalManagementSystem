package com.example.qualifiedwork.adminAccount;

public class ProtocolRecord {
    String dateOfProtocol, doctorSecondName, doctorName, doctorFatherName, doctorResponsStatus, mainDiagnos;

    public ProtocolRecord(String dateOfProtocol, String doctorSecondName, String doctorName, String doctorFatherName, String doctorResponsStatus, String mainDiagnos) {
        this.dateOfProtocol = dateOfProtocol;
        this.doctorSecondName = doctorSecondName;
        this.doctorName = doctorName;
        this.doctorFatherName = doctorFatherName;
        this.mainDiagnos = mainDiagnos;
        this.doctorResponsStatus = doctorResponsStatus;
    }

    public String getDoctorResponsStatus() {
        return doctorResponsStatus;
    }

    public void setDoctorResponsStatus(String doctorResponsStatus) {
        this.doctorResponsStatus = doctorResponsStatus;
    }

    public String getDateOfProtocol() {
        return dateOfProtocol;
    }

    public void setDateOfProtocol(String dateOfProtocol) {
        this.dateOfProtocol = dateOfProtocol;
    }

    public String getDoctorSecondName() {
        return doctorSecondName;
    }

    public void setDoctorSecondName(String doctorSecondName) {
        this.doctorSecondName = doctorSecondName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorFatherName() {
        return doctorFatherName;
    }

    public void setDoctorFatherName(String doctorFatherName) {
        this.doctorFatherName = doctorFatherName;
    }

    public String getMainDiagnos() {
        return mainDiagnos;
    }

    public void setMainDiagnos(String mainDiagnos) {
        this.mainDiagnos = mainDiagnos;
    }
}
