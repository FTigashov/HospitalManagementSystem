package com.example.qualifiedwork.adminAccount;

import com.example.qualifiedwork.DBHandler;
import com.example.qualifiedwork.StartApp;
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

public class AdminListOfAllDoctorsController implements Initializable {
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
    private TableColumn<DoctorRecord, String> columnBirthDate;

    @FXML
    private TableColumn<DoctorRecord, String> columnDateEmpl;

    @FXML
    private TableColumn<DoctorRecord, String> columnFatherName;

    @FXML
    private TableColumn<DoctorRecord, String> columnLogin;

    @FXML
    private TableColumn<DoctorRecord, String> columnName;

    @FXML
    private TableColumn<DoctorRecord, String> columnPassword;

    @FXML
    private TableColumn<DoctorRecord, String> columnRespons;

    @FXML
    private TableColumn<DoctorRecord, String> columnSecondName;

    @FXML
    private Label dateEmplField;

    @FXML
    private Label fatherNameField;

    @FXML
    private TableView<DoctorRecord> listOfDoctors;

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

    @FXML
    void addNewRecordIntoTable(MouseEvent event) {

    }

    @FXML
    void changeRecord(MouseEvent event) {

    }

    @FXML
    void deleteRecord(MouseEvent event) {

    }

    @FXML
    void openMainMenu(MouseEvent event) {
        startApp.switchToAdminMainMenuScene();
    }

    private StartApp startApp;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    private ObservableList<DoctorRecord> oblist = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listOfDoctors.setRowFactory(event -> {
            TableRow<DoctorRecord> row = new TableRow<>();
            row.setOnMouseClicked(event2 -> {
                DoctorRecord clickedRow = row.getItem();
                if (clickedRow != null) {
                    secondNameField.setText(clickedRow.getSecondName());
                    nameField.setText(clickedRow.getName());
                    fatherNameField.setText(clickedRow.getFatherName());
                    birthDateField.setText(clickedRow.getBirthDate());
                    dateEmplField.setText(clickedRow.getEmployDate());
                    responsStatusChoice.setText(clickedRow.getResponsStatus());
                    loginField.setText(clickedRow.getLogin());
                    passwordField.setText(clickedRow.getPassword());
                } else {
//                    System.out.println("");
                }
            });
            return row;
        });
        refreshDataFromTable();
    }

    private void refreshDataFromTable() {
        listOfDoctors.getItems().clear();
        try {
            Connection connection = DBHandler.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM doctorDefaultData");

            while (resultSet.next()) {
                oblist.add(new DoctorRecord(resultSet.getString("secondName"),
                        resultSet.getString("name"),
                        resultSet.getString("fatherName"),
                        resultSet.getString("birthDate"),
                        resultSet.getString("employDate"),
                        resultSet.getString("responsStatus"),
                        resultSet.getString("login"),
                        resultSet.getString("password")));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        columnSecondName.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnFatherName.setCellValueFactory(new PropertyValueFactory<>("fatherName"));
        columnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        columnDateEmpl.setCellValueFactory(new PropertyValueFactory<>("employDate"));
        columnRespons.setCellValueFactory(new PropertyValueFactory<>("responsStatus"));
        columnLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        listOfDoctors.setItems(oblist);
    }

    private void makeFieldsIsEmpty() {
        secondNameField.setText("");
        nameField.setText("");
        fatherNameField.setText("");
        birthDateField.setText("");
        dateEmplField.setText("");
        responsStatusChoice.setText("");
        loginField.setText("");
        passwordField.setText("");
    }
}
