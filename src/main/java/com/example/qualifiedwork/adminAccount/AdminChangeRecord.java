package com.example.qualifiedwork.adminAccount;

import com.example.qualifiedwork.DBHandler;
import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminChangeRecord {

    @FXML
    private Button adminMenuMainBtn;

    @FXML
    private Label birthDateLabel;

    @FXML
    private Label emplDateLabel;

    @FXML
    private Label fatherNameLabel;

    @FXML
    private TextField loginField;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField passwordField;

    @FXML
    private ChoiceBox<String> responsStatusChoice;

    @FXML
    private Button saveChangeRecordBtn;

    @FXML
    private Label secondLabel;

    private StartApp startApp;

    @FXML
    void backToListOfAdmins(MouseEvent event) {
        startApp.switchToListOfAdmins();
    }

    @FXML
    void changeRecord(MouseEvent event) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement psCheckExistsLogin = null;
        ResultSet resultSet = null;

        String updateResponsibleStatus = responsStatusChoice.getValue();
        String updateLogin = loginField.getText();
        String updatePassword = passwordField.getText();

        if (updateLogin.length() == 0 && updatePassword.length() == 0) {
            startApp.showErrorLoginAlert("Ошибка изменения записи", "Убедитесь, что все поля заполнены.");
            return;
        }
        try {
            connection = DBHandler.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE adminDefaultData SET responsStatus = ?, login = ?, password = ? WHERE secondName = ? AND name = ? AND birthDate = ?");

            preparedStatement.setString(1, updateResponsibleStatus);
            preparedStatement.setString(2, updateLogin);
            preparedStatement.setString(3, updatePassword);
            preparedStatement.setString(4, secondLabel.getText());
            preparedStatement.setString(5, nameLabel.getText());
            preparedStatement.setString(6, birthDateLabel.getText());

            preparedStatement.executeUpdate();
            startApp.showSuccessMessage("Изменение записи", "Изменение применено успешно", "Результат изменения отображен в таблице.");

            makeFieldsIsEmpty();
            startApp.switchToListOfAdmins();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void makeFieldsIsEmpty() {
        loginField.setText("");
        passwordField.setText("");
    }

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    @FXML
    public void initialize() {
        responsStatusChoice.getItems().add("Зам. Гл. врача");
        responsStatusChoice.getItems().add("Зав. отделения");
        responsStatusChoice.getItems().add("Зам. Гл. отделения");
        responsStatusChoice.getItems().add("Гл. медсестра");
        responsStatusChoice.getItems().add("Ст. медсестра");
    }

    public void setDataInFields(String getAdminSecondName, String getAdminName, String getAdminfatherName,
                                String getAdminBirthDate, String getAdminEmplDate, String getAdminResponsStatus, String getAdminLogin, String getAdminPassword) {
        secondLabel.setText(getAdminSecondName);
        nameLabel.setText(getAdminName);
        fatherNameLabel.setText(getAdminfatherName);
        birthDateLabel.setText(getAdminBirthDate);
        emplDateLabel.setText(getAdminEmplDate);
        responsStatusChoice.setValue(getAdminResponsStatus);
        loginField.setText(getAdminLogin);
        passwordField.setText(getAdminPassword);
    }


}
