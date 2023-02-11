package com.example.qualifiedwork.authenticaton.controllers;

import com.example.qualifiedwork.db_connection.DBHandler;
import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientLoginController {

    @FXML
    private Button patientAuthBtn;

    @FXML
    private Button patientBackToChoiceBtn;

    @FXML
    private TextField patientLoginFiled;

    @FXML
    private PasswordField patientPasswordField;

    @FXML
    private Hyperlink patientRegisterBtn;
    private Connection connection;

    @FXML
    void backToChoiceView(MouseEvent event) {
        clearFields();
        startApp.switchToChoiceScene();
    }

    @FXML
    void openRegisterView(MouseEvent event) {
        clearFields();
        startApp.switchToPatientRegisterScene();
    }

    @FXML
    void patientAuth(MouseEvent event) {
        String login = patientLoginFiled.getText().trim();
        String password = patientPasswordField.getText().trim();

        String name = null;
        String secondName = null;
        String passwordDB = null;

        if (login.length() == 0 || password.length() == 0) {
            startApp.showErrorLoginAlert("Ошибка авторизации", "Необходимо, чтобы все поля были заполнены!");
            return;
        } else {
            try {
                connection = DBHandler.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM patient_default_data WHERE login = ?");
                preparedStatement.setString(1, login);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    secondName = resultSet.getString("second_name");
                    name = resultSet.getString("name");
                    passwordDB = resultSet.getString("password");

                    if (passwordDB != null && passwordDB.equals(password)) {
                        startApp.getPatSecondName = secondName;
                        startApp.getPatName = name;
                        startApp.getInfoAboutAccountFromController(secondName, name);
                        startApp.showSuccessMessage("Уведомление об авторизации", "Авторизация произошла успешно", "Вы вошли в учетную запись в роли пациента.");
                        clearFields();
                        connection.close();
                        startApp.switchToPatientMainMenuScene();
                    } else {
                        startApp.showErrorLoginAlert("Ошибка авторизации", "Неккоретный ввод пароля.");
                        return;
                    }
                } else {
                    startApp.showErrorLoginAlert("Ошибка авторизации", "Аккаунт по введенным данным не был найден.");
                    return;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void clearFields() {
        patientLoginFiled.setText("");
        patientPasswordField.setText("");
    }

    private StartApp startApp;

    public void setModelApp(StartApp startApp) {
        this.startApp = startApp;
    }
}
