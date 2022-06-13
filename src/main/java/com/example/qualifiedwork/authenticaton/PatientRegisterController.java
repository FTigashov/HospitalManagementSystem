package com.example.qualifiedwork.authenticaton;

import com.example.qualifiedwork.DBHandler;
import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
    private DatePicker birthDateField;

    @FXML
    private TextField patientSecondNameField;
    private Connection connection;

    @FXML
    void backToLogin(MouseEvent event) {
        clearFields();
        startApp.switchToPatientLoginScene();
    }

    @FXML
    void startRegistration(MouseEvent event) {

        String secondName = patientSecondNameField.getText().trim();
        String name = patientNameField.getText().trim();
        String fatherName = patientFatherNameField.getText().trim();
//        String birthDate = birthDateField.getValue().toString();
        String address = addressField.getText().trim();
        String medCard = medCardField.getText().trim();
        String snils = snilsField.getText().trim();
        String login = patientLoginFiled.getText().trim();
        String password = patientPasswordField.getText().trim();
        String confirmPassword = patientConfirmPasswordField.getText().trim();

        if (secondName.length() == 0 || name.length() == 0 || birthDateField.getValue() == null || address.length() == 0 || fatherName.length() == 0 || medCard.length() == 0 || snils.length() == 0 || login.length() == 0 || password.length() == 0 || confirmPassword.length() == 0) {
            startApp.showErrorLoginAlert("Ошибка регистрации", "Необходимо, чтобы все поля были заполнены!");
            return;
        } else if (password.equals(confirmPassword)) {
            checkLoginForExistsAndRegister(secondName, name, fatherName, birthDateField.getValue().toString(), address, medCard, snils, login, password);
        } else { startApp.showErrorLoginAlert("Ошибка регистрации", "Необходимо, чтобы введенные пароли совпадали."); }
    }



    private void checkLoginForExistsAndRegister(String secondName, String name, String fatherName, String birthDate, String address, String medCard, String snils, String login, String password) {
        PreparedStatement psCheckUser = null;
        PreparedStatement psRegisterNewUserData = null;
        ResultSet resultSet = null;

        try {
            connection = DBHandler.getConnection();
            psCheckUser = connection.prepareStatement("SELECT * FROM patient_default_data WHERE login = ?");
            psCheckUser.setString(1, login);

            resultSet = psCheckUser.executeQuery();
            if (!resultSet.next()) {
                psRegisterNewUserData = connection.prepareStatement("INSERT INTO patient_default_data (second_name, name, father_name, login, password, birth_date, med_card, snils_card, address)" +
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

                clearFields();

                startApp.showSuccessMessage("Уведомление о регистрации", "Регистрация прошла успешно!", "Пользователь был добавлен в систему.\nТеперь вы можете выполнить авторизацию.");
                startApp.switchToPatientLoginScene();
            } else {
                startApp.showErrorLoginAlert("Ошибка регистрации", "Пользователь с данным логином уже есть в системе.\nПопробуйте использовать другой.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void clearFields() {
        patientSecondNameField.setText("");
        patientNameField.setText("");
        patientFatherNameField.setText("");
        birthDateField.getEditor().clear();
        patientLoginFiled.setText("");
        patientPasswordField.setText("");
        patientConfirmPasswordField.setText("");
    }

    private StartApp startApp;

    public void setModelApp(StartApp startApp) {
        this.startApp = startApp;
    }
}
