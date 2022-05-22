package com.example.qualifiedwork.authenticaton;

import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PatientRegisterController {

    @FXML
    private Button patientBackToLoginBtn;

    @FXML
    private TextField patientLoginFiled;

    @FXML
    private TextField patientLoginFiled1;

    @FXML
    private PasswordField patientPasswordField;

    @FXML
    private PasswordField patientPasswordField1;

    @FXML
    private Button patientRegisterBtn;

    @FXML
    void backToLogin(MouseEvent event) {
        startApp.switchToPatientLoginScene();
    }

    private StartApp startApp;

    public void setModelApp(StartApp startApp) {
        this.startApp = startApp;
    }
}
