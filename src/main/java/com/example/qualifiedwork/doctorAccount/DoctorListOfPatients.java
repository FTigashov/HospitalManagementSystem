package com.example.qualifiedwork.doctorAccount;

import com.example.qualifiedwork.DBHandler;
import com.example.qualifiedwork.StartApp;
import com.example.qualifiedwork.adminAccount.DoctorRecord;
import com.example.qualifiedwork.adminAccount.PatientRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DoctorListOfPatients implements Initializable {

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
        makeFieldsIsEmpty();
    }

    @FXML
    void makeProtocolBtn(MouseEvent event) {
        PatientRecord record = listOfPatients.getSelectionModel().getSelectedItem();
        if (record == null) {
            startApp.showErrorLoginAlert("Ошибка открытия протокола", "Для выполнения осмотра,\nнеобходимо выбрать пациента в таблице.");
            return;
        } else {
            startApp.getInfoAboutPatientAccountWithoutAuth(secondNameField.getText().trim(), nameField.getText().trim(), fatherNameField.getText().trim(), birthDateField.getText().trim(), medCardField.getText().trim(), snilsField.getText().trim());
            makeFieldsIsEmpty();
            startApp.switchToMakeProtocolForm();
        }
    }

    @FXML
    void makeReceipt(MouseEvent event) {
        makeFieldsIsEmpty();
    }

    private ObservableList<PatientRecord> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listOfPatients.setRowFactory(event -> {
            TableRow<PatientRecord> row = new TableRow<>();
            row.setOnMouseClicked(event2 -> {
                PatientRecord clickedRow = row.getItem();
                if (clickedRow != null) {
                    secondNameField.setText(clickedRow.getSecondName());
                    nameField.setText(clickedRow.getName());
                    fatherNameField.setText(clickedRow.getFatherName());
                    birthDateField.setText(clickedRow.getBirthDate());
                    medCardField.setText(clickedRow.getMedCard());
                    snilsField.setText(clickedRow.getSnilsCard());
                    addressField.setText(clickedRow.getAddress());
                } else {
//                    System.out.println("");
                }
            });
            return row;
        });
        refreshDataFromTable();
    }

    public void refreshDataFromTable() {
        listOfPatients.getItems().clear();
        try {
            Connection connection = DBHandler.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM patient_default_data");

            while (resultSet.next()) {
                oblist.add(new PatientRecord(resultSet.getString("second_name"),
                        resultSet.getString("name"),
                        resultSet.getString("father_name"),
                        resultSet.getString("birth_date"),
                        resultSet.getString("address"),
                        resultSet.getString("med_card"),
                        resultSet.getString("snils_card")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        columnSecondName.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnFatherName.setCellValueFactory(new PropertyValueFactory<>("fatherName"));
        columnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        columnMedCard.setCellValueFactory(new PropertyValueFactory<>("medCard"));
        columnSnils.setCellValueFactory(new PropertyValueFactory<>("snilsCard"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        listOfPatients.setItems(oblist);
    }

    @FXML
    void openMenu(MouseEvent event) {
        makeFieldsIsEmpty();
        startApp.switchToDoctorMainMenuScene();
    }

    private void makeFieldsIsEmpty() {
        secondNameField.setText("");
        nameField.setText("");
        fatherNameField.setText("");
        birthDateField.setText("");
        addressField.setText("");
        medCardField.setText("");
        snilsField.setText("");
    }
}
