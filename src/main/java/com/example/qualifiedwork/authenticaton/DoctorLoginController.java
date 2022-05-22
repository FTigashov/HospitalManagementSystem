package com.example.qualifiedwork.authenticaton;

import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class DoctorLoginController {

    @FXML
    private Button doctorAuthBtn;

    @FXML
    private Button doctorBackToChoiceBtn;

    @FXML
    private TextField doctorLoginFiled;

    @FXML
    private PasswordField doctorPasswordField;

    @FXML
    void backToChoiceView(MouseEvent event) {
        startApp.switchToChoiceScene();
    }

    private StartApp startApp;

    public void setModelApp(StartApp startApp) {
        this.startApp = startApp;
    }
}
