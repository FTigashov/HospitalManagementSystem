package com.example.qualifiedwork.adminAccount;

import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class AdminListOfAllPatientsController {

    @FXML
    private Button DeleteRecordBtn;

    @FXML
    private Button addNewRecordBtn;

    @FXML
    private Button adminMenuMainBtn;

    @FXML
    private Label birthDateField;

    @FXML
    private Button changeRecordBtn;

    @FXML
    private TableColumn<?, ?> columnAddress;

    @FXML
    private TableColumn<?, ?> columnBirthDate;

    @FXML
    private TableColumn<?, ?> columnFatherName;

    @FXML
    private TableColumn<?, ?> columnLogin;

    @FXML
    private TableColumn<?, ?> columnMedCard;

    @FXML
    private TableColumn<?, ?> columnName;

    @FXML
    private TableColumn<?, ?> columnPassword;

    @FXML
    private TableColumn<?, ?> columnSecondName;

    @FXML
    private TableColumn<?, ?> columnSnils;

    @FXML
    private Label dateEmplField;

    @FXML
    private Label fatherNameField;

    @FXML
    private TableView<?> listOfPatients;

    @FXML
    private Label loginField;

    @FXML
    private Label nameField;

    @FXML
    private Label passwordField;

    @FXML
    private Label responsStatusChoice;

    @FXML
    private Label secondNameField;

    private StartApp startApp;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    @FXML
    void addNewRecordIntoTable(MouseEvent event) {
        startApp.switchToAddNewPatientRecordScene();
    }

    @FXML
    void changeRecord(MouseEvent event) {
        startApp.switchToChangePatientRecordScene();
    }

    @FXML
    void deleteRecord(MouseEvent event) {

    }

    @FXML
    void openMainMenu(MouseEvent event) {
        startApp.switchToAdminMainMenuScene();
    }
}
