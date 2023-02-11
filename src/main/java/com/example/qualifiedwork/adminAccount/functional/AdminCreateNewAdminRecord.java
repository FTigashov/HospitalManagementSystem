package com.example.qualifiedwork.adminAccount.functional;

import com.example.qualifiedwork.db_connection.DBHandler;
import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminCreateNewAdminRecord implements Initializable {
    @FXML
    private Button addNewRecordBtn;

    @FXML
    private Button adminMenuMainBtn;

    @FXML
    private DatePicker birthDateField;

    @FXML
    private DatePicker emplDateField;

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

        if (nameField.getText().trim().length() == 0 ||
                nameField.getText().trim().length() == 0 ||
                fatherNameField.getText().trim().length() == 0 ||
                birthDateField.getValue() == null ||
                emplDateField.getValue() == null ||
                loginField.getText().trim().length() == 0 ||
                passwordField.getText().trim().length() == 0) {
            startApp.showErrorLoginAlert("Ошибка добавления записи", "Убедитесь, что все поля заполнены.");
            return;
        } else {
            try {
                String secondName = secondNameField.getText().trim();
                String name = nameField.getText().trim();
                String fatherName = fatherNameField.getText().trim();
                String birthDate = birthDateField.getValue().toString();
                String employDate = emplDateField.getValue().toString();
                String responsStatus = responsStatusChoice.getValue();
                String login = loginField.getText().trim();
                String password = passwordField.getText().trim();

                connection = DBHandler.getConnection();
                psCheckExistsLogin = connection.prepareStatement("SELECT * FROM doc_default_data WHERE login = ?");
                psCheckExistsLogin.setString(1, login);

                resultSet = psCheckExistsLogin.executeQuery();

                if (resultSet.isBeforeFirst()) {
                    startApp.showErrorLoginAlert("Ошибка добавления записи", "Пользователь с данным логином уже есть в системе.");
                    return;
                } else {
                    preparedStatement = connection.prepareStatement("INSERT INTO doc_default_data (second_name, name, father_name, birth_date, employee_date, responsibility_status, login, password, type_of_account) VALUES" +
                            " (?, ?, ?, ?, ?, ?, ?, ?, 'admin') ");
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void makeFieldsIsEmpty() {
        secondNameField.setText("");
        nameField.setText("");
        fatherNameField.setText("");
        birthDateField.getEditor().clear();
        emplDateField.getEditor().clear();
        loginField.setText("");
        passwordField.setText("");
    }

    @FXML
    void backToListOfAdmins(MouseEvent event) {
        makeFieldsIsEmpty();
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
