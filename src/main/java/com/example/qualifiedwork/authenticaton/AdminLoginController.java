package com.example.qualifiedwork.authenticaton;

import com.example.qualifiedwork.DBHandler;
import com.example.qualifiedwork.StartApp;
import com.example.qualifiedwork.adminAccount.AdminInfoController;
import com.example.qualifiedwork.adminAccount.AdminProfileController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminLoginController{
    @FXML
    private Button adminAuthBtn;

    @FXML
    private Button adminBackToChoiceBtn;

    @FXML
    private TextField adminLoginFiled;

    @FXML
    private PasswordField adminPasswordField;

    @FXML
    void backToChoiceView(MouseEvent event) {
        startApp.switchToChoiceScene();
    }

    @FXML
    void adminAuth(MouseEvent event) {
        String login = adminLoginFiled.getText().trim();
        String password = adminPasswordField.getText().trim();

        String passwordDB = null;

        if (login.length() == 0 || password.length() == 0) {
            startApp.showErrorLoginAlert("Ошибка авторизации", "Необходимо, чтобы все поля были заполнены!");
            return;
        }

        try {
            Connection connection = DBHandler.getConnection();
            ResultSet resultSet;
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM adminDefaultData WHERE login = ?");
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            passwordDB = resultSet.getString("password");
            if (passwordDB != null && passwordDB.equals(password)) {
                adminLoginFiled.setText("");
                adminPasswordField.setText("");

                String secondName = resultSet.getString("secondName");
                String name = resultSet.getString("name");
                String fatherName = resultSet.getString("fatherName");
                String birthDate = resultSet.getString("birthDate");
                String emplDate = resultSet.getString("emplDate");
                String responsStatus = resultSet.getString("responsStatus");
                String log = resultSet.getString("login");
                String pwd = resultSet.getString("password");

                System.out.print(secondName + " " + name + " " + fatherName + " " + login);

                connection.close();

                startApp.showSuccessMessage("Уведомление об авторизации", "Авторизация произошла успешно", "Вы вошли в учетную запись в роли администратора");
                startApp.getInfoAboutAccountFromController(secondName, name);
                startApp.switchToAdminMainMenuScene();
            } else {
                connection.close();
                startApp.showErrorLoginAlert("Ошибка авторизации. Некорректный логин или пароль", "Проверьте правильность введенных данных");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private StartApp startApp;
    public void setModelApp(StartApp startApp) {
        this.startApp = startApp;
    }

}
