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
    }

    public void showProtocol() {
        Connection connection = null;
        try {
            connection = DBHandler.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM patient_protocol WHERE doc_father_name = ? AND doc_name = ? AND pat_second_name = ? AND pat_name = ?");

            preparedStatement.setString(1, doctorSecName);
            preparedStatement.setString(2, docN);
            preparedStatement.setString(3, patSecondName);
            preparedStatement.setString(4, patN);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                protocolDate.setText(resultSet.getString("protocol_date"));

                doctorSecondName.setText(resultSet.getString("doc_second_name"));
                doctorName.setText(resultSet.getString("doc_name"));
                doctorFatherName.setText(resultSet.getString("doc_father_name"));
                doctorResponsStatus.setText(resultSet.getString("doc_respons_status"));

                patientSecondName.setText(resultSet.getString("pat_second_name"));
                patientName.setText(resultSet.getString("pat_name"));
                patientFatherName.setText(resultSet.getString("pat_father_name"));
                birthDate.setText(resultSet.getString("pat_birth_date"));
                medCard.setText(resultSet.getString("pat_polis_card"));
                snilsCard.setText(resultSet.getString("pat_snils_card"));

                height.setText(resultSet.getString("height"));
                weight.setText(resultSet.getString("weight"));
                indexOfBody.setText(resultSet.getString("index_of_body"));
                AD.setText(resultSet.getString("AD"));
                t.setText(resultSet.getString("temperature"));
                CHSS.setText(resultSet.getString("CHSS"));
                CHDD.setText(resultSet.getString("CHDD"));

                commonStatus.setText(resultSet.getString("common_status"));
                puls.setText(resultSet.getString("pulse"));
                skin.setText(resultSet.getString("skin"));
                spine.setText(resultSet.getString("spine"));

                respiratorySystem.setText(resultSet.getString("respiratory_system"));
                ribCage.setText(resultSet.getString("rib_cage"));
                urination.setText(resultSet.getString("urination"));
                kidneys.setText(resultSet.getString("kidneys"));

                mainDiagnos.setText(resultSet.getString("main_diagnos"));
                disease.setText(resultSet.getString("disease"));
                recommendation.setText(resultSet.getString("recommendation"));

                resultSet.close();
                connection.close();
            } else {
                startApp.showErrorLoginAlert("Ошибка открытия протокола", "Данные не были найдены.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void backToListOfProtocols(MouseEvent event) {
        startApp.switchToPatientListOfVisits();
    }
}
