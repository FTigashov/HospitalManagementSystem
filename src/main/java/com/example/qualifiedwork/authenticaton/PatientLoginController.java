package com.example.qualifiedwork.authenticaton;

import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PatientLoginController {

    @FXML
    private Button patientAuthBtn;

    @FXML
    private Button patientBackToChoiceBtn;

    @FXML
    private TextField patientLoginFiled;

    @FXML
    private PasswordField patientPasswordField;

    @FXML
    private Hyperlink patientRegisterBtn;

    @FXML
    void backToChoiceView(MouseEvent event) {
        startApp.switchToChoiceScene();
    }

    @FXML
    void openRegisterView(MouseEvent event) {
        startApp.switchToPatientRegisterScene();
    }

    private StartApp startApp;

    public void setModelApp(StartApp startApp) {
        this.startApp = startApp;
    }
}
