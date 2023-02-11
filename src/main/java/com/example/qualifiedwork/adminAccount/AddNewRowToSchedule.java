package com.example.qualifiedwork.adminAccount;

import com.example.qualifiedwork.DBHandler;
import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.*;

public class AddNewRowToSchedule {
    @FXML
    private Button addNewRowBtn;

    @FXML
    private Button backToSheduleBtn;

    @FXML
    private TextField cabNumField;

    @FXML
    private TextField friField;

    @FXML
    private ChoiceBox<String> fullnameField;

    @FXML
    private TextField specialityField;

    @FXML
    private TextField mnField;

    @FXML
    private TextField thurField;

    @FXML
    private TextField tuesField;

    @FXML
    private TextField wedsField;

    private StartApp startApp;
    private Connection connection = DBHandler.getConnection();;

    public AddNewRowToSchedule() throws SQLException {
    }

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    @FXML
    void initialize() {
        fullnameField.setValue("ФИО");

        try {
            connection = DBHandler.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT second_name, name, father_name from doc_default_data");

            while (rs.next()) {
                String fullName = rs.getString("second_name") + " " + rs.getString("name") + " " + rs.getString("father_name");
                fullnameField.getItems().add(fullName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void addNewRow(MouseEvent event) throws SQLException {

        if (
        cabNumField.getText().trim().length() == 0 ||
        mnField.getText().trim().length() == 0 ||
        tuesField.getText().trim().length() == 0 ||
        wedsField.getText().trim().length() == 0 ||
        thurField.getText().trim().length() == 0 ||
        friField.getText().trim().length() == 0)  {
            startApp.showErrorLoginAlert("Ошибка добавления новой записи", "Убедитесь, что все поля были заполнены.");
            return;
        } else if (fullnameField.getValue().trim().equals("ФИО")) {
            startApp.showErrorLoginAlert("Ошибка добавления новой записи", "Выберите сотрудника в поле ФИО.");
            return;
        } else {
            PreparedStatement pr = connection.prepareStatement("SELECT * FROM schedule WHERE full_name = ?");
            pr.setString(1, fullnameField.getValue().trim());
            ResultSet resultSet = pr.executeQuery();

            if (!resultSet.next()) {
                pr = null;
                String fullName, cabNum, mn, tue, wed, thur, fri;
                fullName = fullnameField.getValue().trim();
                String[] data = fullName.split(" ");

                pr = connection.prepareStatement("select responsibility_status from doc_default_data where second_name = ? AND name = ?");
                pr.setString(1, data[0]);
                pr.setString(2, data[1]);
                ResultSet rs = pr.executeQuery();

                if (rs.next()) {
                    specialityField.setText(rs.getString("responsibility_status"));
                }

                try {
                    pr = null;
                    pr = connection.prepareStatement("INSERT INTO schedule (full_name, speciality, cab_num, monday, tuesday, wednesday, thursday, friday) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                    pr.setString(1, fullnameField.getValue().trim());
                    pr.setString(2, specialityField.getText().trim());
                    pr.setString(3, cabNumField.getText().trim());
                    pr.setString(4, mnField.getText().trim());
                    pr.setString(5, tuesField.getText().trim());
                    pr.setString(6, wedsField.getText().trim());
                    pr.setString(7, thurField.getText().trim());
                    pr.setString(8, friField.getText().trim());

                    pr.executeUpdate();

                    makeFieldsIsEmpty();

                    startApp.showSuccessMessage("Уведомление о создании записи", "Запись успешно создана", "Новая запись отобразится в таблице");
                    startApp.switchToShedulePage();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                startApp.showErrorLoginAlert("Ошибка добавления записи", "Сотрудник с данным расписанием уже есть в системе.");
                return;
            }


        }
    }

    private void makeFieldsIsEmpty() {
        fullnameField.setValue("");
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
        makeFieldsIsEmpty();
        startApp.switchToShedulePage();
    }

}
