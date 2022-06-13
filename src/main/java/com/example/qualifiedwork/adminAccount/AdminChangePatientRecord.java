package com.example.qualifiedwork.adminAccount;

import com.example.qualifiedwork.DBHandler;
import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminChangePatientRecord {
    @FXML
    private Button changeRecordBtn;

    @FXML
    private Label addressField;

    @FXML
    private Button backToListOfPatients;

    @FXML
    private Label birthDateField;

    @FXML
    private Label fatherNameField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField medCardField;

    @FXML
    private Label nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label secondNameLabel;

    @FXML
    private TextField snils;

    private StartApp startApp;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    @FXML
    void changeRecord(MouseEvent event) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String updateMedCard = medCardField.getText();
        String updateSnilsCard = snils.getText();
        String updateLogin = loginField.getText();
        String updatePassword = passwordField.getText();

        if (updateLogin.length() == 0 && updatePassword.length() == 0 && updateMedCard.length() == 0 && updateSnilsCard.length() == 0) {
            startApp.showErrorLoginAlert("Ошибка изменения записи", "Убедитесь, что все поля заполнены.");
            return;
        }
        try {
            connection = DBHandler.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE patientDefaultData SET medCard = ?, snilsCard = ?, login = ?, password = ? WHERE secondName = ? AND name = ? AND birthDate = ?");

            preparedStatement.setString(1, updateMedCard);
            preparedStatement.setString(2, updateSnilsCard);
            preparedStatement.setString(3, updateLogin);
            preparedStatement.setString(4, updatePassword);
            preparedStatement.setString(5, secondNameLabel.getText());
            preparedStatement.setString(6, nameField.getText());
            preparedStatement.setString(7, birthDateField.getText());

            preparedStatement.executeUpdate();
            startApp.showSuccessMessage("Изменение записи", "Изменение применено успешно", "Результат изменения отображен в таблице.");

            makeFieldsIsEmpty();
            startApp.switchToListOfPatients();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void backToListOfPatients(MouseEvent event) {
        startApp.switchToListOfPatients();
    }

    public void setInfoInFields(String getPatientSecondName, String getPatientName, String getPatientFatherName, String getPatientBirthDate,
                                String getPatientMedCard, String getPatientSnilsCard, String getPatientLogin, String getPatientPassword, String getPatientAddress) {
        secondNameLabel.setText(getPatientSecondName);
        nameField.setText(getPatientName);
        fatherNameField.setText(getPatientFatherName);
        birthDateField.setText(getPatientBirthDate);
        medCardField.setText(getPatientMedCard);
        snils.setText(getPatientSnilsCard);
        loginField.setText(getPatientLogin);
        passwordField.setText(getPatientPassword);
        addressField.setText(getPatientAddress);
    }

    private void makeFieldsIsEmpty() {
        secondNameLabel.setText("");
        nameField.setText("");
        fatherNameField.setText("");
        birthDateField.setText("");
        addressField.setText("");
        loginField.setText("");
        passwordField.setText("");
        medCardField.setText("");
        snils.setText("");
    }
}
