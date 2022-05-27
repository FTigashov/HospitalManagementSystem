package com.example.qualifiedwork.adminAccount;

import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AdminChangePatientRecord {
    @FXML
    private Button changeRecordBtn;

    @FXML
    private Label addressField;

    @FXML
    private Button backToListOfPatients;

    @FXML
    private Label birthDateField;

    @FXML
    private Label fatherNameField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField medCardField;

    @FXML
    private Label nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label secondNameLabel;

    @FXML
    private TextField snils;

    private StartApp startApp;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    @FXML
    void changeRecord(MouseEvent event) {

    }

    @FXML
    void backToListOfPatients(MouseEvent event) {
        startApp.switchToListOfPatients();
    }
}
