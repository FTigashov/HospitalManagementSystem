package com.example.qualifiedwork.authenticaton;

import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AdminLoginController {
    @FXML
    private Button adminAuthBtn;

    @FXML
    private Button adminBackToChoiceBtn;

    @FXML
    private TextField adminLoginFiled;

    @FXML
    private PasswordField adminPasswordField;

    @FXML
    void backToChoiceView(MouseEvent event) {
        startApp.switchToChoiceScene();
    }

    private StartApp startApp;

    public void setModelApp(StartApp startApp) {
        this.startApp = startApp;
    }
}
