package com.example.qualifiedwork.adminAccount;

import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AdminListOfAllAdminsController {

    @FXML
    private Button DeleteRecordBtn;

    @FXML
    private Button addNewRecordBtn;

    @FXML
    private Button adminMenuMainBtn;

    @FXML
    private TextField birthDateField;

    @FXML
    private Button changeRecordBtn;

    @FXML
    private TableColumn<?, ?> columnBirthDate;

    @FXML
    private TableColumn<?, ?> columnDateEmpl;

    @FXML
    private TableColumn<?, ?> columnFatherName;

    @FXML
    private TableColumn<?, ?> columnLogin;

    @FXML
    private TableColumn<?, ?> columnName;

    @FXML
    private TableColumn<?, ?> columnPassword;

    @FXML
    private TableColumn<?, ?> columnRespons;

    @FXML
    private TableColumn<?, ?> columnSecondName;

    @FXML
    private TextField dateEmplField;

    @FXML
    private TextField fatherNameField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private ChoiceBox<?> responsStatusChoice;

    @FXML
    private TextField secondNameField;

    private StartApp startApp;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    public void openMainMenu(MouseEvent mouseEvent) {
        startApp.switchToAdminMainMenuScene();
    }
}
