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

    private Connection connection = DBHandler.getConnection();

    public AdminChangeRecord() throws SQLException {
    }

    @FXML
    void backToListOfAdmins(MouseEvent event) {
        startApp.switchToListOfAdmins();
    }

    @FXML
    void changeRecord(MouseEvent event) {
        PreparedStatement preparedStatement = null;

        String updateResponsibleStatus = responsStatusChoice.getValue();
        String updateLogin = loginField.getText();
        String updatePassword = passwordField.getText();

        if (updateLogin.length() == 0 || updatePassword.length() == 0) {
            startApp.showErrorLoginAlert("Ошибка изменения записи", "Убедитесь, что все поля заполнены.");
            return;
        } else {
            try {
                preparedStatement = connection.prepareStatement("UPDATE doc_default_data SET responsibility_status = ?, login = ?, password = ? WHERE second_name = ? AND name = ? AND birth_date = ?");

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
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

    public void setDataInFields(String getAdminSecondName, String getAdminName) throws SQLException {
//        System.out.println("controller data: " + getAdminSecondName + " " + getAdminName);

        PreparedStatement pr = connection.prepareStatement("SELECT * FROM doc_default_data WHERE second_name = ? AND name = ? AND type_of_account = 'admin'");
        pr.setString(1, getAdminSecondName);
        pr.setString(2, getAdminName);
        ResultSet rs = pr.executeQuery();

        if (rs.next()) {
            secondLabel.setText(rs.getString("second_name"));
            nameLabel.setText(rs.getString("name"));
            fatherNameLabel.setText(rs.getString("father_name"));
            birthDateLabel.setText(rs.getString("birth_date"));
            emplDateLabel.setText(rs.getString("employee_date"));
            responsStatusChoice.setValue(rs.getString("responsibility_status"));
            loginField.setText(rs.getString("login"));
            passwordField.setText(rs.getString("password"));

            System.out.println("Информация установлена!");
        }
    }


}
