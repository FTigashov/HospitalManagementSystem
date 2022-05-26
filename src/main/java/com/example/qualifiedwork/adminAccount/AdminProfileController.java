package com.example.qualifiedwork.adminAccount;

import com.example.qualifiedwork.DBHandler;
import com.example.qualifiedwork.StartApp;
import com.example.qualifiedwork.authenticaton.AdminLoginController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AdminProfileController {
    @FXML
    private Button adminMenuMainBtn;

    @FXML
    private Button adminInfoBtn;

    @FXML
    private Button adminLogOutBtn;

    @FXML
    private Label birthDateLabel;

    @FXML
    private Label emplDateLabel;

    @FXML
    private Label fatherNameLabel;

    @FXML
    private Label loginLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label responsStatusLabel;

    @FXML
    private Label secondNameLabel;

    @FXML
    private Button patientProfileBtn;
    private AdminLoginController adminLoginController;

    @FXML
    void adminLogOut(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение действия");
        alert.setHeaderText("Выход из учетной записи");
        alert.setContentText("Вы уверены, что хотите выйти?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            startApp.switchToChoiceScene();
        } else { return; }
    }

    @FXML
    void adminOpenInfoPage(MouseEvent event) {
        startApp.switchToAdminInfoScene();
    }

    @FXML
    void adminOpenMainPage(MouseEvent event) {
        startApp.switchToAdminMainMenuScene();
    }

    @FXML
    void adminOpenProfile(MouseEvent event) {
        startApp.switchToAdminProfileScene();
    }

    private StartApp startApp;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    public void getInfoAboutAccount(String sName, String nm) {
        try {
            Connection connection = DBHandler.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM adminDefaultData WHERE secondName = ? AND name = ?");
            ResultSet resultSet;
            preparedStatement.setString(1, sName);
            preparedStatement.setString(2, nm);
            resultSet = preparedStatement.executeQuery();

            String secondNameDb = resultSet.getString("secondName");
            String nameDB = resultSet.getString("name");

            secondNameLabel.setText(secondNameDb);
            nameLabel.setText(nameDB);
            fatherNameLabel.setText(resultSet.getString("fatherName"));
            birthDateLabel.setText(resultSet.getString("birthDate"));
            emplDateLabel.setText(resultSet.getString("emplDate"));
            responsStatusLabel.setText(resultSet.getString("responsStatus"));
            loginLabel.setText(resultSet.getString("login"));
            passwordLabel.setText(resultSet.getString("password"));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
