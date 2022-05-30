package com.example.qualifiedwork.doctorAccount;

import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class DoctorMakeProtocolController {
    @FXML
    private TextField AD;

    @FXML
    private TextField CHDD;

    @FXML
    private TextField CHSS;

    @FXML
    private Label birthDate;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextArea commonStatus;

    @FXML
    private DatePicker dateOfProtocol;

    @FXML
    private TextArea disease;

    @FXML
    private Label doctorFatherName;

    @FXML
    private Label doctorName;

    @FXML
    private Label doctorResponsStatus;

    @FXML
    private Label doctorSecondName;

    @FXML
    private TextField height;

    @FXML
    private TextField indexOfBody;

    @FXML
    private TextField kidneys;

    @FXML
    private TextArea mainDiagnos;

    @FXML
    private Label medCard;

    @FXML
    private Label patientFatherName;

    @FXML
    private Label patientName;

    @FXML
    private Label patientSecondName;

    @FXML
    private TextField puls;

    @FXML
    private TextArea recommendation;

    @FXML
    private TextArea respiratorySystem;

    @FXML
    private TextArea ribCage;

    @FXML
    private Button saveBtn;

    @FXML
    private TextArea skin;

    @FXML
    private Label snilsCard;

    @FXML
    private TextArea spine;

    @FXML
    private TextField t;

    @FXML
    private TextField urination;

    @FXML
    private TextField weight;

    private StartApp startApp;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    public void setInfo(String docResponsStatus, String docSecondName, String docName, String docFatherName,
                        String patSeconName, String patName, String patFatherName, String patBirthDate, String patMedCard, String patSnilsCard) {

        doctorFatherName.setText(docFatherName);
        doctorName.setText(docName);
        doctorSecondName.setText(docSecondName);
        doctorResponsStatus.setText(docResponsStatus);

        patientSecondName.setText(patSeconName);
        patientName.setText(patName);
        patientFatherName.setText(patFatherName);
        birthDate.setText(patBirthDate);
        medCard.setText(patMedCard);
        snilsCard.setText(patSnilsCard);
    }

    @FXML
    void backToListOfPatients(MouseEvent event) {

    }

    @FXML
    void createNewProtocol(MouseEvent event) {

    }
}
