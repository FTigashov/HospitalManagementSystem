package com.example.qualifiedwork.doctorAccount;

import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class DoctorMenuController {
    @FXML
    private Button ArchiveOfProtocolsBtn;

    @FXML
    private Button doctorInfoBtn;

    @FXML
    private Button doctorLogOutBtn;

    @FXML
    private Button doctorMenuMainBtn;

    @FXML
    private Button doctorProfileBtn;

    @FXML
    private Button listOfPatientsBtn;

    @FXML
    private Button scheduleBtn;

    private StartApp startApp;

    public void setStartApp(StartApp startApp) { this.startApp = startApp; }

    @FXML
    void doctorLogOut(MouseEvent event) {
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
    void doctorShowInfo(MouseEvent event) {
        startApp.switchToDoctorInfoScene();
    }

    @FXML
    void openArchiveOfVisits(MouseEvent event) {
        startApp.switchToDoctorArchiveOfVisitsScene();
    }

    @FXML
    void openDoctorMainMenu(MouseEvent event) {
        startApp.switchToDoctorMainMenuScene();
    }

    @FXML
    void openDoctorProfile(MouseEvent event) {
        startApp.switchToDoctorProfileScene();
    }

    @FXML
    void openDoctorSchedule(MouseEvent event) {
        startApp.switchToDoctorScheduleScene();
    }

    @FXML
    void openListOfPatients(MouseEvent event) {
        startApp.switchToDoctorListOfPatients();
    }
}
