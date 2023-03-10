package com.example.qualifiedwork.adminAccount.functional;

import com.example.qualifiedwork.db_connection.DBHandler;
import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminCreateNewPatientRecord {
    @FXML
    private Button addNewRecordBtn;

    @FXML
    private TextField addressField;

    @FXML
    private Button backToListOfPatients;

    @FXML
    private DatePicker birthDateField;

    @FXML
    private TextField fatherNameField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField medCardField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField secondNameField;

    @FXML
    private TextField snils;

    private StartApp startApp;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    @FXML
    void addNewRecord(MouseEvent event) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String secondName = secondNameField.getText().trim();
        String name = nameField.getText().trim();
        String fatherName = fatherNameField.getText().trim();
        String medCard = medCardField.getText().trim();
        String snilsCard = snils.getText().trim();
        String login = loginField.getText().trim();
        String password = passwordField.getText().trim();
        String address = addressField.getText().trim();

        if (secondName.length() == 0 ||
        name.length() == 0 ||
        fatherName.length() == 0 ||
        birthDateField.getValue() == null ||
        medCard.length() == 0 ||
        snilsCard.length() == 0 ||
        login.length() == 0 ||
        password.length() == 0 ||
        address.length() == 0) {
            startApp.showErrorLoginAlert("???????????? ???????????????????? ????????????", "??????????????????, ?????? ?????? ???????? ??????????????????.");
            return;
        }
        try {
            connection = DBHandler.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO patient_default_data (second_name, name, father_name, login, password, birth_date, med_card, snils_card, address) VALUES" +
                    " (?, ?, ?, ?, ?, ?, ?, ?, ?) ");
            preparedStatement.setString(1, secondName);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, fatherName);
            preparedStatement.setString(6, birthDateField.getValue().toString());
            preparedStatement.setString(4, login);
            preparedStatement.setString(5, password);
            preparedStatement.setString(7, medCard);
            preparedStatement.setString(8, snilsCard);
            preparedStatement.setString(9, address);

            preparedStatement.executeUpdate();

            makeFieldsIsEmpty();

            startApp.showSuccessMessage("?????????????????????? ?? ???????????????? ????????????", "???????????? ?????????????? ??????????????", "?????????? ???????????? ?????????????????????? ?? ??????????????");
            startApp.switchToListOfPatients();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void backToListOfPatients(MouseEvent event) {
        startApp.switchToListOfPatients();
    }

    private void makeFieldsIsEmpty() {
        secondNameField.setText("");
        nameField.setText("");
        fatherNameField.setText("");
        birthDateField.getEditor().clear();
        addressField.setText("");
        loginField.setText("");
        passwordField.setText("");
        medCardField.setText("");
        snils.setText("");
    }
}
