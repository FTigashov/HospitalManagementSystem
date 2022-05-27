package com.example.qualifiedwork.adminAccount;

import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AdminCreateNewPatientRecord {
    @FXML
    private Button addNewRecordBtn;

    @FXML
    private TextField addressField;

    @FXML
    private Button backToListOfPatients;

    @FXML
    private TextField birthDateField;

    @FXML
    private TextField fatherNameField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField medCardField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField secondNameField;

    @FXML
    private TextField snils;

    private StartApp startApp;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    @FXML
    void addNewRecord(MouseEvent event) {

    }

    @FXML
    void backToListOfPatients(MouseEvent event) {
        startApp.switchToListOfPatients();
    }
}
