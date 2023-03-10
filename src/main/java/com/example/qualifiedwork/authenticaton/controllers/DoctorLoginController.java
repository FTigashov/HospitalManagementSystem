package com.example.qualifiedwork.authenticaton.controllers;

import com.example.qualifiedwork.db_connection.DBHandler;
import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorLoginController {

    @FXML
    private Button doctorAuthBtn;

    @FXML
    private Button doctorBackToChoiceBtn;

    @FXML
    private TextField doctorLoginFiled;

    @FXML
    private PasswordField doctorPasswordField;

    @FXML
    void backToChoiceView(MouseEvent event) {
        startApp.switchToChoiceScene();
    }

    @FXML
    void doctorAuth(MouseEvent event) {
        String login = doctorLoginFiled.getText().trim();
        String password = doctorPasswordField.getText().trim();

        String passwordDB = null;

        if (login.length() == 0 || password.length() == 0) {
            startApp.showErrorLoginAlert("Ошибка авторизации", "Необходимо, чтобы все поля были заполнены!");
            return;
        }

        try {
            Connection connection = DBHandler.getConnection();
            ResultSet resultSet;
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM doc_default_data WHERE login = ?");
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                passwordDB = resultSet.getString("password");
                if (passwordDB != null && passwordDB.equals(password)) {
                    doctorLoginFiled.setText("");
                    doctorPasswordField.setText("");

                    startApp.getInfoAboutDoctorAccount(resultSet.getString("second_name"),
                            resultSet.getString("name"),
                            resultSet.getString("father_name"),
                            resultSet.getString("birth_date"),
                            resultSet.getString("employee_date"),
                            resultSet.getString("responsibility_status"),
                            resultSet.getString("login"),
                            resultSet.getString("password"));

                    startApp.getDocSecondName = resultSet.getString("second_name");
                    startApp.getDocName = resultSet.getString("name");
                    connection.close();
                    startApp.showSuccessMessage("Уведомление об авторизации", "Авторизация произошла успешно", "Вы вошли в учетную запись в роли врача");
//                    System.out.println(doc_id);
                    startApp.switchToDoctorMainMenuScene();
                    cleanFields();
                } else {
                    startApp.showErrorLoginAlert("Ошибка авторизации. Некорректный логин или пароль", "Проверьте правильность введенных данных");
                    connection.close();
                }
            } else {
                startApp.showErrorLoginAlert("Ошибка авторизации", "Аккаунт по введенным данным не был найден.");
                return;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cleanFields() {
        doctorLoginFiled.setText("");
        doctorPasswordField.setText("");
    }

    private StartApp startApp;

    public void setModelApp(StartApp startApp) {
        this.startApp = startApp;
    }
}
