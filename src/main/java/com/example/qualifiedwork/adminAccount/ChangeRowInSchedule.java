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

public class ChangeRowInSchedule {
    @FXML
    private Button backToSheduleBtn;

    @FXML
    private TextField cabNumField;

    @FXML
    private TextField friField;

    @FXML
    private Label fullnameField;

    @FXML
    private TextField mnField;

    @FXML
    private Button saveRecord;

    @FXML
    private Label specialityField;

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
    void backToShedule(MouseEvent event) {
        startApp.switchToShedulePage();
    }

    @FXML
    void saveChangeOfRow(MouseEvent event) {
        String fullName = fullnameField.getText().trim();
        String speciality = specialityField.getText().trim();
        String cabNum = cabNumField.getText().trim();
        String monday = mnField.getText().trim();
        String tuesday = tuesField.getText().trim();
        String wednesday = wedsField.getText().trim();
        String thursday = thurField.getText().trim();
        String friday = friField.getText().trim();

        if (fullName.length() == 0 && speciality.length() == 0 && cabNum.length() == 0 && monday.length() == 0 && tuesday.length() == 0 && wednesday.length() == 0 && thursday.length() == 0 && friday.length() == 0) {
            startApp.showErrorLoginAlert("Ошибка добавления записи", "Убедитесь, что все поля заполнены.");
            return;
        } else {
            try {
                Connection connection = DBHandler.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE schedule SET cab_num = ?, monday = ?, tuesday = ?, wednesday = ?, thursday = ?, friday = ? WHERE full_name = ? AND speciality = ?");

                preparedStatement.setString(1, cabNumField.getText().trim());
                preparedStatement.setString(2, mnField.getText().trim());
                preparedStatement.setString(3, tuesField.getText().trim());
                preparedStatement.setString(4, wedsField.getText().trim());
                preparedStatement.setString(5, thurField.getText().trim());
                preparedStatement.setString(6, friField.getText().trim());
                preparedStatement.setString(7, fullnameField.getText().trim());
                preparedStatement.setString(8, specialityField.getText().trim());

                preparedStatement.executeUpdate();
                startApp.showSuccessMessage("Изменение записи", "Изменение применено успешно", "Результат изменения отображен в таблице.");
                makeFieldsIsEmpty();
                startApp.switchToShedulePage();

            } catch (SQLException e) {
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

    public void setInfoInFields(String fullName, String speciality, String cabNum, String mn, String tue, String wed, String thurs, String fri) {
        fullnameField.setText(fullName);
        specialityField.setText(speciality);
        cabNumField.setText(cabNum);
        mnField.setText(mn);
        tuesField.setText(tue);
        wedsField.setText(wed);
        thurField.setText(thurs);
        friField.setText(fri);
    }
}
