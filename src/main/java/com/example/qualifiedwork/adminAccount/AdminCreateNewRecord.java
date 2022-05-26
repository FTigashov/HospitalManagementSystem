package com.example.qualifiedwork.adminAccount;

import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminCreateNewRecord implements Initializable {
    @FXML
    private Button addNewRecordBtn;

    @FXML
    private Button adminMenuMainBtn;

    @FXML
    private TextField birthDateField;

    @FXML
    private TextField emplDateField;

    @FXML
    private TextField fatherNameFIeld;

    @FXML
    private TextField loginField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private ChoiceBox<String> responsStatusChoice;

    @FXML
    private TextField secondNameField;

    @FXML
    void addNewRecord(MouseEvent event) {

    }

    @FXML
    void backToListOfAdmins(MouseEvent event) {
        startApp.switchToListOfAdmins();
    }

    private StartApp startApp;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        responsStatusChoice.setValue("Зав. отделения");
        responsStatusChoice.getItems().add("Зам. Гл. врача");
        responsStatusChoice.getItems().add("Зав. отделения");
        responsStatusChoice.getItems().add("Зам. Гл. отделения");
        responsStatusChoice.getItems().add("Гл. медсестра");
        responsStatusChoice.getItems().add("Ст. медсестра");
    }
}
