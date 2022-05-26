package com.example.qualifiedwork.authenticaton;

import com.example.qualifiedwork.DBHandler;
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

    @FXML
    void backToChoiceView(MouseEvent event) {
        startApp.switchToChoiceScene();
    }

    @FXML
    void openRegisterView(MouseEvent event) {
        startApp.switchToPatientRegisterScene();
    }

    @FXML
    void patientAuth(MouseEvent event) {
        String login = patientLoginFiled.getText().trim();
        String password = patientPasswordField.getText().trim();

        String passwordDB = null;

        if (login.length() == 0 || password.length() == 0) {
            startApp.showErrorLoginAlert("Ошибка авторизации", "Необходимо, чтобы все поля были заполнены!");
            return;
        }
        try {
            Connection connection = DBHandler.getConnection();
            ResultSet resultSet;
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM patientDefaultData WHERE login = ?");
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            passwordDB = resultSet.getString("password");

            if (passwordDB != null && passwordDB.equals(password)) {
                if (resultSet.isBeforeFirst()) {
                    String secondName = resultSet.getString("secondName");
                    String name = resultSet.getString("name");
                    startApp.getInfoAboutAccountFromController(secondName, name);

                    patientLoginFiled.setText("");
                    patientPasswordField.setText("");
                    connection.close();
                    startApp.showSuccessMessage("Уведомление об авторизации", "Авторизация произошла успешно", "Вы вошли в учетную запись в роли пациента.");
                    startApp.switchToPatientMainMenuScene();
                } else {
                    startApp.showErrorLoginAlert("Ошибка авторизации", "По введенным вами данным\nаккаунт не был найден.");
                    return;
                }
            } else {
                startApp.showErrorLoginAlert("Ошибка авторизации", "Некорректный логин или пароль.\nПроверьте правильность введенных данных.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private StartApp startApp;

    public void setModelApp(StartApp startApp) {
        this.startApp = startApp;
    }
}
