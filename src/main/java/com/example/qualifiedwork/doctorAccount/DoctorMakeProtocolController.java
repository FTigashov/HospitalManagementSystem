package com.example.qualifiedwork.doctorAccount;

import com.example.qualifiedwork.DBHandler;
import com.example.qualifiedwork.StartApp;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DoctorMakeProtocolController {
    @FXML
    private TextField AD;

    @FXML
    private TextField CHDD;

    @FXML
    private TextField CHSS;

    @FXML
    private Label birthDate;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextArea commonStatus;

    @FXML
    private DatePicker dateOfProtocol;

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
    private TextField puls;

    @FXML
    private TextArea recommendation;

    @FXML
    private TextArea respiratorySystem;

    @FXML
    private TextArea ribCage;

    @FXML
    private Button saveBtn;

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
    private Connection connection;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    public void setInfo(String docResponsStatus, String docSecondName, String docName, String docFatherName,
                        String patSeconName, String patName, String patFatherName, String patBirthDate, String patMedCard, String patSnilsCard) {

        doctorFatherName.setText(docFatherName);
        doctorName.setText(docName);
        doctorSecondName.setText(docSecondName);
        doctorResponsStatus.setText(docResponsStatus);

        patientSecondName.setText(patSeconName);
        patientName.setText(patName);
        patientFatherName.setText(patFatherName);
        birthDate.setText(patBirthDate);
        medCard.setText(patMedCard);
        snilsCard.setText(patSnilsCard);
    }

    @FXML
    void backToListOfPatients(MouseEvent event) {
        startApp.switchToDoctorListOfPatients();
    }

    @FXML
    void createNewProtocol(MouseEvent event) {
//        String protocolDate = String.valueOf(dateOfProtocol.getValue());
        String heightStr = height.getText().trim();
        String wegihtStr = weight.getText().trim();
        String indexBody = indexOfBody.getText().trim();
        String ad = AD.getText().trim();
        String temperature = t.getText().trim();
        String chSS = CHSS.getText().trim();
        String chDD = CHDD.getText().trim();
        String comState = commonStatus.getText().trim();
        String pulsState = puls.getText().trim();
        String skinState = skin.getText().trim();
        String spinState = spine.getText().trim();
        String respiratorySystemState = respiratorySystem.getText().trim();
        String ribCageState = ribCage.getText().trim();
        String urinationState = urination.getText().trim();
        String kidneysState = kidneys.getText().trim();
        String mainDiagnosState = mainDiagnos.getText().trim();
        String diseaseState = disease.getText().trim();
        String recommendState = recommendation.getText().trim();

        if (dateOfProtocol.getValue() == null) {
            startApp.showErrorLoginAlert("Ошибка сохранения протокола", "Убедитесь, чтобы все поля были заполнены.");
            return;
        } else if (heightStr.length() == 0 &&
                wegihtStr.length() == 0 &&
                indexBody.length() == 0 &&
                ad.length() == 0 &&
                temperature.length() == 0 &&
                chSS.length() == 0 &&
                chDD.length() == 0 &&
                comState.length() == 0 &&
                pulsState.length() == 0 &&
                skinState.length() == 0 &&
                spinState.length() == 0 &&
                respiratorySystemState.length() == 0 &&
                ribCageState.length() == 0 &&
                urinationState.length() == 0 &&
                kidneysState.length() == 0 &&
                mainDiagnosState.length() == 0 &&
                diseaseState.length() == 0 &&
                recommendState.length() == 0) {
            startApp.showErrorLoginAlert("Ошибка сохранения протокола", "Убедитесь, чтобы все поля были заполнены.");
            return;
        } else {
            try {
                connection = DBHandler.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO patient_protocol (doc_second_name, doc_name, doc_father_name, doc_respons_status, " +
                        "pat_second_name, pat_name, pat_father_name, pat_birth_date, pat_polis_card, pat_snils_card, height, weight, " +
                        "index_of_body, AD, temperature, CHSS, CHDD, common_status, pulse, skin, spine, respiratory_system, rib_cage, urination, kidneys, main_diagnos, disease, recommendation, protocol_date) VALUES " +
                        "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                preparedStatement.setString(1, doctorSecondName.getText().trim());
                preparedStatement.setString(2, doctorName.getText().trim());
                preparedStatement.setString(3, doctorFatherName.getText().trim());
                preparedStatement.setString(4, doctorResponsStatus.getText().trim());
                preparedStatement.setString(5, patientSecondName.getText().trim());
                preparedStatement.setString(6, patientName.getText().trim());
                preparedStatement.setString(7, patientFatherName.getText().trim());
                preparedStatement.setString(8, birthDate.getText().trim());
                preparedStatement.setString(9, medCard.getText().trim());
                preparedStatement.setString(10, snilsCard.getText().trim());
                preparedStatement.setString(11, heightStr);
                preparedStatement.setString(12, wegihtStr);
                preparedStatement.setString(13, indexBody);
                preparedStatement.setString(14, ad);
                preparedStatement.setString(15, temperature);
                preparedStatement.setString(16, chSS);
                preparedStatement.setString(17, chDD);
                preparedStatement.setString(18, comState);
                preparedStatement.setString(19, pulsState);
                preparedStatement.setString(20, skinState);
                preparedStatement.setString(21, spinState);
                preparedStatement.setString(22, respiratorySystemState);
                preparedStatement.setString(23, ribCageState);
                preparedStatement.setString(24, urinationState);
                preparedStatement.setString(25, kidneysState);
                preparedStatement.setString(26,  mainDiagnosState);
                preparedStatement.setString(27, diseaseState);
                preparedStatement.setString(28, recommendState);
                preparedStatement.setString(29, dateOfProtocol.getValue().toString());

                preparedStatement.executeUpdate();

                startApp.showSuccessMessage("Сообщение об успехе", "Осмотр проведен", "Протокол осмотра успешно сохранен и добавлен в базу данных.");
                startApp.switchToDoctorListOfPatients();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
