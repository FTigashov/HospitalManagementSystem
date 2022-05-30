package com.example.qualifiedwork.doctorAccount;

import com.example.qualifiedwork.StartApp;
import com.example.qualifiedwork.adminAccount.PatientRecord;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class DoctorListOfPatients {
    @FXML
    private Label addressField;

    @FXML
    private Label birthDateField;

    @FXML
    private TableColumn<PatientRecord, String> columnAddress;

    @FXML
    private TableColumn<PatientRecord, String> columnBirthDate;

    @FXML
    private TableColumn<PatientRecord, String> columnFatherName;

    @FXML
    private TableColumn<PatientRecord, String> columnMedCard;

    @FXML
    private TableColumn<PatientRecord, String> columnName;

    @FXML
    private TableColumn<PatientRecord, String> columnSecondName;

    @FXML
    private TableColumn<PatientRecord, String> columnSnils;

    @FXML
    private Button doctorMenuBtn;

    @FXML
    private Label fatherNameField;

    @FXML
    private TableView<PatientRecord> listOfPatients;

    @FXML
    private Button makeDirectionBtn;

    @FXML
    private Button makeProtocolBtn;

    @FXML
    private Button makeReceiptBtn;

    @FXML
    private Label medCardField;

    @FXML
    private Label nameField;

    @FXML
    private Label secondNameField;

    @FXML
    private Label snilsField;

    private StartApp startApp;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    @FXML
    void makeDirection(MouseEvent event) {

    }

    @FXML
    void makeProtocolBtn(MouseEvent event) {

    }

    @FXML
    void makeReceipt(MouseEvent event) {

    }

    @FXML
    void openMenu(MouseEvent event) {
        startApp.switchToDoctorMainMenuScene();
    }
}
