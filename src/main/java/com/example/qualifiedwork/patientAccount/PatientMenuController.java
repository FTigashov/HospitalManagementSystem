package com.example.qualifiedwork.patientAccount;

import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class PatientMenuController {
    @FXML
    private Button archiveOfRecords;

    @FXML
    private Button doctorsDirection;

    @FXML
    private Button listOfReceipts;

    @FXML
    private Button makeRecordToVisitDoctor;

    @FXML
    private Button patientMenuMainBtn;

    @FXML
    private Button patientProfileBtn;

    @FXML
    private Button patientInfoBtn;

    private StartApp startApp;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    @FXML
    private Button patientLogOutBtn;

    @FXML
    void patientLogOut(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение действия");
        alert.setHeaderText("Выход из учетной записи");
        alert.setContentText("Вы уверены, что хотите выйти?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            startApp.switchToChoiceScene();
        } else { return; }
    }

    @FXML
    void openPatientInfo(MouseEvent event) {
        startApp.switchToPatientInfoScene();
    }

    @FXML
    void openPatientProfile(MouseEvent event) {
        startApp.switchToPatientProfileScene();
    }

    @FXML
    void openListOfVisits(MouseEvent event) {
        startApp.switchToPatientListOfVisits();
    }

    @FXML
    void openMyDirections(MouseEvent event) {
        startApp.switchToPatListOfDirections();
    }
}
