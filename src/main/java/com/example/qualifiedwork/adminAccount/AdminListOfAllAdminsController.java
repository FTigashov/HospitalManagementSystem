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

public class AdminListOfAllAdminsController implements Initializable {

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
    private TableColumn<AdminRecord, String> columnBirthDate;

    @FXML
    private TableColumn<AdminRecord, String> columnDateEmpl;

    @FXML
    private TableColumn<AdminRecord, String> columnFatherName;

    @FXML
    private TableColumn<AdminRecord, String> columnLogin;

    @FXML
    private TableColumn<AdminRecord, String> columnName;

    @FXML
    private TableColumn<AdminRecord, String> columnPassword;

    @FXML
    private TableColumn<AdminRecord, String> columnRespons;

    @FXML
    private TableColumn<AdminRecord, String> columnSecondName;

    @FXML
    private TableView<AdminRecord> listOfAdmins;

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

    private DBHandler dbHandler;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    public void openMainMenu(MouseEvent mouseEvent) {
        startApp.switchToAdminMainMenuScene();
    }

    private ObservableList<AdminRecord> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection connection = DBHandler.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM adminDefaultData");

            while (resultSet.next()) {
                oblist.add(new AdminRecord(resultSet.getString("secondName"),
                        resultSet.getString("name"),
                        resultSet.getString("fatherName"),
                        resultSet.getString("birthDate"),
                        resultSet.getString("emplDate"),
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
        columnDateEmpl.setCellValueFactory(new PropertyValueFactory<>("dateEmpl"));
        columnRespons.setCellValueFactory(new PropertyValueFactory<>("responsStatus"));
        columnLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        listOfAdmins.setItems(oblist);
    }
}
