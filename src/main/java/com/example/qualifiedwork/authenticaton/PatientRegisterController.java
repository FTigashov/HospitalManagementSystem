package com.example.qualifiedwork.authenticaton;

import com.example.qualifiedwork.DBHandler;
import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.*;

public class PatientRegisterController {

    @FXML
    private Button patientBackToLoginBtn;

    @FXML
    private TextField patientFatherNameField;

    @FXML
    private TextField patientLoginFiled;

    @FXML
    private TextField patientNameField;

    @FXML
    private PasswordField patientPasswordField;

    @FXML
    private PasswordField patientConfirmPasswordField;

    @FXML
    private Button patientRegisterBtn;

    @FXML
    private TextField medCardField;

    @FXML
    private TextField snilsField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField birthDateField;

    @FXML
    private TextField patientSecondNameField;

    @FXML
    void backToLogin(MouseEvent event) {
        startApp.switchToPatientLoginScene();
    }

    @FXML
    void startRegistration(MouseEvent event) {

        String secondName = patientSecondNameField.getText().trim();
        String name = patientNameField.getText().trim();
        String fatherName = patientFatherNameField.getText().trim();
        String birthDate = birthDateField.getText().trim();
        String address = addressField.getText().trim();
        String medCard = medCardField.getText().trim();
        String snils = snilsField.getText().trim();
        String login = patientLoginFiled.getText().trim();
        String password = patientPasswordField.getText().trim();
        String confirmPassword = patientConfirmPasswordField.getText().trim();

        if (secondName.length() == 0 && name.length() == 0 && birthDate.length() == 0 && address.length() == 0 && fatherName.length() == 0 && medCard.length() == 0 && snils.length() == 0 && login.length() == 0 && password.length() == 0 && confirmPassword.length() == 0) {
            startApp.showErrorLoginAlert("Ошибка регистрации", "Необходимо, чтобы все поля были заполнены!");
            return;
        }
        if (password.equals(confirmPassword)) {
            checkLoginForExistsAndRegister(secondName, name, fatherName, birthDate, address, medCard, snils, login, password);
        } else { startApp.showErrorLoginAlert("Ошибка регистрации", "Необходимо, чтобы введенные пароли совпадали."); }

    }



    private void checkLoginForExistsAndRegister(String secondName, String name, String fatherName, String birthDate, String address, String medCard, String snils, String login, String password) {
        Connection connection = null;
        PreparedStatement psCheckUser = null;
        PreparedStatement psRegisterNewUser = null;
        PreparedStatement psRegisterNewUserData = null;
        ResultSet resultSet = null;

        try {
            connection = DBHandler.getConnection();
            psCheckUser = connection.prepareStatement("SELECT * FROM patientDefaultData WHERE login = ?");
            psCheckUser.setString(1, login);

            resultSet = psCheckUser.executeQuery();

            if (resultSet.isBeforeFirst()) {
                startApp.showErrorLoginAlert("Ошибка регистрации", "Пользователь с данным логином уже есть в системе.\nПопробуйте использовать другой.");
                connection.close();
            } else {
                psRegisterNewUserData = connection.prepareStatement("INSERT INTO patientDefaultData (secondName, name, fatherName, login, password, birthDate, medCard, snilsCard, address)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                psRegisterNewUserData.setString(1, secondName);
                psRegisterNewUserData.setString(2, name);
                psRegisterNewUserData.setString(3, fatherName);
                psRegisterNewUserData.setString(4, login);
                psRegisterNewUserData.setString(5, password);
                psRegisterNewUserData.setString(6, birthDate);
                psRegisterNewUserData.setString(7, medCard);
                psRegisterNewUserData.setString(8, snils);
                psRegisterNewUserData.setString(9, address);

                psRegisterNewUserData.executeUpdate();
                connection.close();

                patientSecondNameField.setText("");
                patientNameField.setText("");
                patientFatherNameField.setText("");
                patientLoginFiled.setText("");
                patientPasswordField.setText("");
                patientConfirmPasswordField.setText("");

                startApp.showSuccessMessage("Уведомление о регистрации", "Регистрация прошла успешно!", "Пользователь был добавлен в систему.\nТеперь вы можете выполнить авторизацию.");
                startApp.switchToPatientLoginScene();
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
