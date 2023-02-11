package com.example.qualifiedwork.authenticaton.controllers;

import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ChoiceViewController {
    @FXML
    private Button adminBtn;

    @FXML
    private Button doctorBtn;

    @FXML
    private Button patientBtn;

    @FXML
    public void switchToSceneAdminLogin(MouseEvent event) {
        startApp.switchToAdminLoginScene();
    }

    @FXML
    public void switchToSceneDoctorLogin(MouseEvent event) {
        startApp.switchToDoctorLoginScene();
    }

    @FXML
    public void switchToScenePatientLogin(MouseEvent event) {
        startApp.switchToPatientLoginScene();
    }

    public StartApp startApp;

    public void setModelApp(StartApp startApp) {
        this.startApp = startApp;
    }
}
