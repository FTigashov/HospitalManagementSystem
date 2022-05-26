package com.example.qualifiedwork.adminAccount;

import com.example.qualifiedwork.DBHandler;
import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @FXML
    void addNewRecord(MouseEvent event) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement psCheckExistsLogin = null;
        ResultSet resultSet = null;

        String secondName = secondNameField.getText().trim();
        String name = nameField.getText().trim();
        String fatherName = fatherNameField.getText().trim();
        String birthDate = birthDateField.getText().trim();
        String employDate = emplDateField.getText().trim();
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
                startApp.switchToListOfAdmins();
                connection.close();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void makeFieldsIsEmpty() {
        secondNameField.setText("");
        nameField.setText("");
        fatherNameField.setText("");
        birthDateField.setText("");
        emplDateField.setText("");
        loginField.setText("");
        passwordField.setText("");
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
        responsStatusChoice.getItems().add("Гл. бухгалгер");
        responsStatusChoice.getItems().add("Зам. по мед. части");
        responsStatusChoice.getItems().add("Зав. отделения");
        responsStatusChoice.getItems().add("Зам. Гл. отделения");
        responsStatusChoice.getItems().add("Гл. медсестра");
        responsStatusChoice.getItems().add("Ст. медсестра");
    }
}
