package com.example.qualifiedwork.doctorAccount;

import com.example.qualifiedwork.DBHandler;
import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MakeDirectionController {
    @FXML
    private Button backToArchiveOfDirections;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField diagnosField;

    @FXML
    private Label docFullNameLabel;

    @FXML
    private TextField explain1;

    @FXML
    private Label fullNameLabel;

    @FXML
    private Label birthDateLabel;

    @FXML
    private TextField nameOfMedField;

    @FXML
    private Label polisNumLabel;

    @FXML
    private Label responsibility_status;

    @FXML
    private Button saveBtn;

    @FXML
    private Label checkLabel;

    @FXML
    private ChoiceBox<String> typeOfDirection;

    @FXML
    private TextField work_place;
    private Connection connection = DBHandler.getConnection();

    public MakeDirectionController() throws SQLException {
    }

    @FXML
    void addNewDirection(MouseEvent event) {
        if (dateField.getValue() == null ||
        nameOfMedField.getText().trim().length() == 0 ||
        work_place.getText().trim().length() == 0 ||
        diagnosField.getText().trim().length() == 0 ||
        explain1.getText().trim().length() == 0) {
            startApp.showErrorLoginAlert("Ошибка создания направления", "Убедитесь, что все поля были заполенны.");
            return;
        } else {
            try {
                String[] fullNameData = fullNameLabel.getText().trim().split(" ");
                String[] docFullNameData = docFullNameLabel.getText().trim().split(" ");

                PreparedStatement preparedStatement = connection.prepareStatement("insert into patient_direction (type_of_direction, hospital_direction, polis_num, pat_second_name, pat_name, pat_father_name, birth_date, pat_work_place, diagnos, explaining, doc_respons_status, doc_second_name, doc_name, doc_father_name, date_of_direction) VALUES " +
                        "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, typeOfDirection.getValue().trim());
                preparedStatement.setString(2, nameOfMedField.getText().trim());
                preparedStatement.setString(3, polisNumLabel.getText().trim());
                preparedStatement.setString(4, fullNameData[0]);
                preparedStatement.setString(5, fullNameData[1]);
                preparedStatement.setString(6, fullNameData[2]);
                preparedStatement.setString(7, birthDateLabel.getText().trim());
                preparedStatement.setString(8, work_place.getText().trim());
                preparedStatement.setString(9, diagnosField.getText().trim());
                preparedStatement.setString(10, explain1.getText().trim());
                preparedStatement.setString(11, responsibility_status.getText().trim());
                preparedStatement.setString(12, docFullNameData[0]);
                preparedStatement.setString(13, docFullNameData[1]);
                preparedStatement.setString(14, docFullNameData[2]);
                preparedStatement.setString(15, dateField.getValue().toString());

                preparedStatement.executeUpdate();
                makeFieldEmpty();
                startApp.showSuccessMessage("Уведомление об успехе", "Направление успешно добавлено", "Протокол направления создан\nи добавлен в базу данных.");
                startApp.switchToDoctorListOfPatients();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void backToListOfDirections(MouseEvent event) {
        makeFieldEmpty();
        startApp.switchToDoctorListOfPatients();
    }

    public void setInfo() {
        PreparedStatement pr = null;
        PreparedStatement pr2 = null;
        try {
            pr = connection.prepareStatement("SELECT father_name, birth_date, med_card FROM patient_default_data WHERE second_name = ? AND name = ?");
            pr.setString(1, patSecondName);
            pr.setString(2, patName);
            ResultSet rs = pr.executeQuery();

            pr2 = connection.prepareStatement("SELECT father_name, responsibility_status FROM doc_default_data WHERE second_name = ? AND name = ?");
            pr2.setString(1, docSecondName);
            pr2.setString(2, docName);
            ResultSet rs2 = pr2.executeQuery();

            if (rs.next()) {
                polisNumLabel.setText(rs.getString("med_card"));
                fullNameLabel.setText(patSecondName + " " + patName + " " + rs.getString("father_name"));
                birthDateLabel.setText(rs.getString("birth_date"));
            }
            if (rs2.next()) {
                responsibility_status.setText(rs2.getString("responsibility_status"));
                docFullNameLabel.setText(docSecondName + " " + docName + " " + rs2.getString("father_name"));
                String getCheck = checkLabel.getText().trim();
//                System.out.println(getCheck);
                String nameF = docName.split("")[0];
                String fatherNameF = rs2.getString("father_name").trim().split("")[0];
                String docCheck = getCheck + " (" + docSecondName + " " + nameF + "." + fatherNameF + ".)";
                checkLabel.setText(docCheck);

//                System.out.println("Данные установлены!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void makeFieldEmpty() {
        polisNumLabel.setText("");
        fullNameLabel.setText("");
        birthDateLabel.setText("");
        responsibility_status.setText("");
        docFullNameLabel.setText("");
        checkLabel.setText("Электронная подпись");
    }

    @FXML
    void initialize() {
        typeOfDirection.setValue("консультацию");
        typeOfDirection.getItems().add("госпитализацию");
        typeOfDirection.getItems().add("восстановительное лечение");
        typeOfDirection.getItems().add("обследование");
        typeOfDirection.getItems().add("консультацию");
    }

    public String docSecondName, docName, patSecondName, patName;
    private StartApp startApp;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }
}
