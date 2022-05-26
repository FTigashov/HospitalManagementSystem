package com.example.qualifiedwork.adminAccount;

import com.example.qualifiedwork.DBHandler;
import com.example.qualifiedwork.StartApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AdminListOfAllAdminsController implements Initializable {

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
    private Label dateEmplField;

    @FXML
    private Label fatherNameField;

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

        listOfAdmins.setRowFactory(event -> {
           TableRow<AdminRecord> row = new TableRow<>();
           row.setOnMouseClicked(event2 -> {
               AdminRecord clickedRow = row.getItem();
               if (clickedRow != null) {
                   secondNameField.setText(clickedRow.getSecondName());
                   nameField.setText(clickedRow.getName());
                   fatherNameField.setText(clickedRow.getFatherName());
                   birthDateField.setText(clickedRow.getBirthDate());
                   dateEmplField.setText(clickedRow.getDateEmpl());
                   responsStatusChoice.setText(clickedRow.getResponsStatus());
                   loginField.setText(clickedRow.getLogin());
                   passwordField.setText(clickedRow.getPassword());
               } else {
//                   System.out.println("Не выбрано поле");
               }
           });
            return row;
        });
        showDataFromTable();
    }

    @FXML
    void addNewRecordIntoTable(MouseEvent event) {
        startApp.switchToCreateNewRecordForm();

        /*Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement psCheckExistsLogin = null;
        ResultSet resultSet = null;

        String secondName = secondNameField.getText().trim();
        String name = nameField.getText().trim();
        String fatherName = fatherNameField.getText().trim();
        String birthDate = birthDateField.getText().trim();
        String employDate = dateEmplField.getText().trim();
        String responsStatus = responsStatusChoice.getValue();
        String login = loginField.getText().trim();
        String password = passwordField.getText().trim();

        if (secondName.length() == 0 &&
                name.length() == 0 &&
                fatherName.length() == 0 &&
                birthDate.length() == 0 &&
                employDate.length() == 0 &&
                login.length() == 0 &&
                password.length() == 0) {
            startApp.showErrorLoginAlert("Ошибка добавления записи", "Убедитесь, что все поля заполнены.");
            return;
        }
        try {
            connection = DBHandler.getConnection();
            psCheckExistsLogin = connection.prepareStatement("SELECT * FROM adminDefaultData WHERE login = ?");
            psCheckExistsLogin.setString(1, login);

            resultSet = psCheckExistsLogin.executeQuery();

            if (resultSet.isBeforeFirst()) {
                startApp.showErrorLoginAlert("Ошибка добавления записи", "Пользователь с данным логином уже есть в системе.");
                return;
            } else {
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

                makeFieldsIsEmpty();

                startApp.showSuccessMessage("Уведомление о создании записи", "Запись успешно создана", "Новая запись отобразится в таблице");

                showDataFromTable();
                connection.close();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/
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

    @FXML
    void deleteRecord(MouseEvent event) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        AdminRecord record = listOfAdmins.getSelectionModel().getSelectedItem();
        if (record == null) {
            startApp.showErrorLoginAlert("Ошибка удаления", "Необходимо выбрать запись в таблице.");
            return;
        } else {
            String loginRecord = record.getLogin();
            try {
                connection = DBHandler.getConnection();
                preparedStatement = connection.prepareStatement("DELETE FROM adminDefaultData WHERE login = ?");
                preparedStatement.setString(1, loginRecord);

                preparedStatement.executeUpdate();

                makeFieldsIsEmpty();

                startApp.showSuccessMessage("Уведомление об удалении  записи", "Запись успешно удалена", "Таблица обновлена");
                showDataFromTable();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void changeRecord(MouseEvent event) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement psCheckExistsLogin = null;
        ResultSet resultSet = null;

        String secondName = secondNameField.getText().trim();
        String name = nameField.getText().trim();
        String fatherName = fatherNameField.getText().trim();
        String birthDate = birthDateField.getText().trim();
        String employDate = dateEmplField.getText().trim();
        String responsStatus = responsStatusChoice.getText().trim();
        String login = loginField.getText().trim();
        String password = passwordField.getText().trim();

        if (secondName.length() == 0 &&
                name.length() == 0 &&
                fatherName.length() == 0 &&
                birthDate.length() == 0 &&
                employDate.length() == 0 &&
                login.length() == 0 &&
                password.length() == 0) {
            startApp.showErrorLoginAlert("Ошибка изменения записи", "Убедитесь, что все поля заполнены.");
            return;
        }

        try {
            connection = DBHandler.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE adminDefaultData SET emplDate = ?, responsStatus = ?, login = ?, password = ? WHERE secondName = ? AND name = ? AND birthDate = ?");

            preparedStatement.setString(1, employDate);
            preparedStatement.setString(2, responsStatus);
            preparedStatement.setString(3, login);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, secondName);
            preparedStatement.setString(6, name);
            preparedStatement.setString(7, birthDate);

            preparedStatement.executeUpdate();
            startApp.showSuccessMessage("Изменение записи", "Изменение применено успешно", "Результат изменения отображен в таблице.");

            makeFieldsIsEmpty();

            showDataFromTable();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void makeFieldsIsEmpty() {
        secondNameField.setText("");
        nameField.setText("");
        fatherNameField.setText("");
        birthDateField.setText("");
        dateEmplField.setText("");
        loginField.setText("");
        passwordField.setText("");
    }
}
