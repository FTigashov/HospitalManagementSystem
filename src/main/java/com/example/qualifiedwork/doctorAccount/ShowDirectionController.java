package com.example.qualifiedwork.doctorAccount;

import com.example.qualifiedwork.DBHandler;
import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowDirectionController {
    @FXML
    private Button backToListOfDirectionsBtn;

    @FXML
    private Label birthDateLabel;

    @FXML
    private Label checkLabel;

    @FXML
    private Label dateField;

    @FXML
    private Label diagnosField;

    @FXML
    private Label docFullNameLabel;

    @FXML
    private Label explain1;

    @FXML
    private Label fullNameLabel;

    @FXML
    private Label nameOfMedField;

    @FXML
    private Label polisNumLabel;

    @FXML
    private Label responsibility_status;

    @FXML
    private Label typeOfDirection;

    @FXML
    private Label work_place;

    public String getDocSecondName, getDocName, getDateOfDirection, getPatSecondName, getPatName, typeOfAccount;

    @FXML
    void backToListOfDirections(MouseEvent event) {
        if (typeOfAccount.equals("doctor")) {startApp.switchToListOfDirections();}
        else if (typeOfAccount.equals("patient")) {startApp.switchToPatListOfDirections();}
    }

    private StartApp startApp;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    public void setInfo() {
        System.out.println(getDocSecondName + " " + getDocName);
        System.out.println(getPatSecondName + " " + getPatName);
        try {
            Connection conn = DBHandler.getConnection();
            PreparedStatement pr = conn.prepareStatement("select * from patient_direction where doc_second_name = ? and doc_name = ? and pat_second_name = ? and pat_name = ? and date_of_direction = ?");
            pr.setString(1, getDocSecondName);
            pr.setString(2, getDocName);
            pr.setString(3, getPatSecondName);
            pr.setString(4, getPatName);
            pr.setString(5, getDateOfDirection);

            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                dateField.setText(rs.getString("date_of_direction"));
                typeOfDirection.setText(rs.getString("type_of_direction"));
                nameOfMedField.setText(rs.getString("hospital_direction"));
                polisNumLabel.setText(rs.getString("polis_num"));
                fullNameLabel.setText(rs.getString("pat_second_name") + " " + rs.getString("pat_name") + " " + rs.getString("pat_father_name"));
                birthDateLabel.setText(rs.getString("birth_date"));
                work_place.setText(rs.getString("pat_work_place"));
                diagnosField.setText(rs.getString("diagnos"));
                explain1.setText(rs.getString("explaining"));
                responsibility_status.setText(rs.getString("doc_respons_status"));
                docFullNameLabel.setText(rs.getString("doc_second_name") + " " + rs.getString("doc_name") + " " + rs.getString("doc_father_name"));
                String[] secondF = rs.getString("doc_second_name").trim().split("");
                String[] nameF = rs.getString("doc_name").trim().split("");
                String[] fatherF = rs.getString("doc_father_name").trim().split("");
                checkLabel.setText("Электронная подпись (" + rs.getString("doc_second_name") + " " + nameF[0] + "." + fatherF[0] + ".)");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
