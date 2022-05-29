package com.example.qualifiedwork.doctorAccount;

import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

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
        startApp.switchToChoiceScene();
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
