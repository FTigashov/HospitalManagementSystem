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
import java.sql.PreparedStatement;
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
    private ChoiceBox<String> responsStatusChoice;

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
        responsStatusChoice.setValue("Гл. врач");
        responsStatusChoice.getItems().add("Гл. врач");
        responsStatusChoice.getItems().add("Зав. отделения");
        responsStatusChoice.getItems().add("Гл. медсестра");

        showDataFromTable();
    }

    @FXML
    void addNewRecordIntoTable(MouseEvent event) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String secondName = secondNameField.getText().trim();
        String name = nameField.getText().trim();
        String fatherName = fatherNameField.getText().trim();
        String birthDate = birthDateField.getText().trim();
        String employDate = dateEmplField.getText().trim();
        String responsStatus = responsStatusChoice.getValue();
        String login = loginField.getText().trim();
        String password = passwordField.getText().trim();

        try {
            connection = DBHandler.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO adminDefaultData (secondName, name, fatherName, birthDate, emplDate, responsStatus, login, password) VALUES" +
                    " (?, ?, ?, ?, ?, ?, ?, ?) ");
            preparedStatement.setString(1, secondName);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, fatherName);
            preparedStatement.setString(4, birthDate);
            preparedStatement.setString(5, employDate);
            preparedStatement.setString(6, responsStatus);
            preparedStatement.setString(7, login);
            preparedStatement.setString(8, password);

            preparedStatement.executeUpdate();
            showDataFromTable();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
//        Connection connection = null;
//        PreparedStatement psCheckUser = null;
//        PreparedStatement psRegisterNewUser = null;
//        PreparedStatement psRegisterNewUserData = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = DBHandler.getConnection();
//            psCheckUser = connection.prepareStatement("SELECT * FROM patientsAuth WHERE login = ?");
//            psCheckUser.setString(1, login);
//
//            resultSet = psCheckUser.executeQuery();
//
//            if (resultSet.isBeforeFirst()) {
//                startApp.showErrorLoginAlert("Ошибка регистрации", "Пользователь с данным логином уже есть в системе.\nПопробуйте использовать другой.");
//                connection.close();
//            } else {
//                psRegisterNewUser = connection.prepareStatement("INSERT INTO patientsAuth (login, password) VALUES (?, ?)");
//                psRegisterNewUser.setString(1, login);
//                psRegisterNewUser.setString(2, password);
//
//                psRegisterNewUser.executeUpdate();
//
//                psRegisterNewUserData = connection.prepareStatement("INSERT INTO patientDefaultData (secondName, name, fatherName, login) VALUES (?, ?, ?, ?)");
//                psRegisterNewUserData.setString(1, secondName);
//                psRegisterNewUserData.setString(2, name);
//                psRegisterNewUserData.setString(3, fatherName);
//                psRegisterNewUserData.setString(4, login);
//
//                psRegisterNewUserData.executeUpdate();
//                connection.close();
//
//
//                startApp.showSuccessMessage("Уведомление о регистрации", "Регистрация прошла успешно!", "Пользователь был добавлен в систему.\nТеперь вы можете выполнить авторизацию.");
//                startApp.switchToPatientLoginScene();
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
    }

    private void showDataFromTable() {
        listOfAdmins.getItems().clear();
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
