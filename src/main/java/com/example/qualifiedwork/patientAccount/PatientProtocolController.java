package com.example.qualifiedwork.patientAccount;

import com.example.qualifiedwork.DBHandler;
import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientProtocolController {
    @FXML
    private TextField AD;

    @FXML
    private TextField CHDD;

    @FXML
    private TextField CHSS;

    @FXML
    private Button backToListOfProtocolsBtn;

    @FXML
    private Label birthDate;

    @FXML
    private TextArea commonStatus;

    @FXML
    private TextArea disease;

    @FXML
    private Label doctorFatherName;

    @FXML
    private Label doctorName;

    @FXML
    private Label doctorResponsStatus;

    @FXML
    private Label doctorSecondName;

    @FXML
    private TextField height;

    @FXML
    private TextField indexOfBody;

    @FXML
    private TextField kidneys;

    @FXML
    private TextArea mainDiagnos;

    @FXML
    private Label medCard;

    @FXML
    private Label patientFatherName;

    @FXML
    private Label patientName;

    @FXML
    private Label patientSecondName;

    @FXML
    private Label protocolDate;

    @FXML
    private TextField puls;

    @FXML
    private TextArea recommendation;

    @FXML
    private TextArea respiratorySystem;

    @FXML
    private TextArea ribCage;

    @FXML
    private TextArea skin;

    @FXML
    private Label snilsCard;

    @FXML
    private TextArea spine;

    @FXML
    private TextField t;

    @FXML
    private TextField urination;

    @FXML
    private TextField weight;

    private StartApp startApp;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    private String doctorSecName, docN, patSecondName, patN;

    public void setInfo(String doctorSecName, String docN, String patSecondName, String patN) {
        this.doctorSecName = doctorSecName;
        this.docN = docN;
        this.patSecondName = patSecondName;
        this.patN = patN;

        showProtocol();

//        System.out.println(doctorSecName + " " + docN + " " + patSecondName + " " + patN + " ");
    }

    public void showProtocol() {
        Connection connection = null;
        try {
            connection = DBHandler.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM patientProtocols WHERE docSecondName = ? AND docName = ? AND patSecondName = ? AND patName = ?");

            preparedStatement.setString(1, doctorSecName);
            preparedStatement.setString(2, docN);
            preparedStatement.setString(3, patSecondName);
            preparedStatement.setString(4, patN);

            ResultSet resultSet = preparedStatement.executeQuery();

            protocolDate.setText(resultSet.getString("protocolDate"));

            doctorSecondName.setText(resultSet.getString("docSecondName"));
            doctorName.setText(resultSet.getString("docName"));
            doctorFatherName.setText(resultSet.getString("docFatherName"));
            doctorResponsStatus.setText(resultSet.getString("docResponsStatus"));

            patientSecondName.setText(resultSet.getString("patSecondName"));
            patientName.setText(resultSet.getString("patName"));
            patientFatherName.setText(resultSet.getString("patFatherName"));
            birthDate.setText(resultSet.getString("patBirthDate"));
            medCard.setText(resultSet.getString("patMedCard"));
            snilsCard.setText(resultSet.getString("patSnilsCard"));

            height.setText(resultSet.getString("height"));
            weight.setText(resultSet.getString("weight"));
            indexOfBody.setText(resultSet.getString("indexOfBody"));
            AD.setText(resultSet.getString("AD"));
            t.setText(resultSet.getString("t"));
            CHSS.setText(resultSet.getString("CHSS"));
            CHDD.setText(resultSet.getString("CHDD"));

            commonStatus.setText(resultSet.getString("commonStatus"));
            puls.setText(resultSet.getString("puls"));
            skin.setText(resultSet.getString("skin"));
            spine.setText(resultSet.getString("spine"));

            respiratorySystem.setText(resultSet.getString("respiratorySystem"));
            ribCage.setText(resultSet.getString("ribCage"));
            urination.setText(resultSet.getString("urination"));
            kidneys.setText(resultSet.getString("kidneys"));

            mainDiagnos.setText(resultSet.getString("mainDiagnos"));
            disease.setText(resultSet.getString("disease"));
            recommendation.setText(resultSet.getString("recommendation"));

            resultSet.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void backToListOfProtocols(MouseEvent event) {
        startApp.switchToPatientListOfVisits();
    }
}
