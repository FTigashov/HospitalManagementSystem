package com.example.qualifiedwork.adminAccount;

import com.example.qualifiedwork.DBHandler;
import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddNewRowToShedule {
    @FXML
    private Button addNewRowBtn;

    @FXML
    private Button backToSheduleBtn;

    @FXML
    private TextField cabNumField;

    @FXML
    private TextField friField;

    @FXML
    private TextField fullnameField;

    @FXML
    private TextField mnField;

    @FXML
    private TextField specialityField;

    @FXML
    private TextField thurField;

    @FXML
    private TextField tuesField;

    @FXML
    private TextField wedsField;

    private StartApp startApp;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    @FXML
    void addNewRow(MouseEvent event) {
        String fullName, speciality, cabNum, mn, tue, wed, thur, fri;
        fullName = fullnameField.getText().trim();
        speciality = fullnameField.getText().trim();
        cabNum = fullnameField.getText().trim();
        mn = fullnameField.getText().trim();
        tue = fullnameField.getText().trim();
        wed = fullnameField.getText().trim();
        thur = fullnameField.getText().trim();
        fri = fullnameField.getText().trim();

        if (fullName.length() == 0 && speciality.length() == 0 && cabNum.length() == 0 && mn.length() == 0 && tue.length() == 0 && wed.length() == 0 && thur.length() == 0 && fri.length() == 0) {
            startApp.showErrorLoginAlert("Ошибка добавления записи", "Убедитесь, что все поля заполнены.");
            return;
        } else {
            try {
                Connection connection = DBHandler.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO shedule (fullName, speciality, cabNum, monday, tuesday, wednesday, thursday, friday) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, fullnameField.getText().trim());
                preparedStatement.setString(2, specialityField.getText().trim());
                preparedStatement.setString(3, cabNumField.getText().trim());
                preparedStatement.setString(4, mnField.getText().trim());
                preparedStatement.setString(5, tuesField.getText().trim());
                preparedStatement.setString(6, wedsField.getText().trim());
                preparedStatement.setString(7, thurField.getText().trim());
                preparedStatement.setString(8, friField.getText().trim());

                preparedStatement.executeUpdate();

                makeFieldsIsEmpty();

                startApp.showSuccessMessage("Уведомление о создании записи", "Запись успешно создана", "Новая запись отобразится в таблице");
                startApp.switchToShedulePage();
                connection.close();

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void makeFieldsIsEmpty() {
        fullnameField.setText("");
        specialityField.setText("");
        cabNumField.setText("");
        mnField.setText("");
        tuesField.setText("");
        wedsField.setText("");
        thurField.setText("");
        friField.setText("");
    }

    @FXML
    void backToShedule(MouseEvent event) {
        startApp.switchToShedulePage();
    }

}
