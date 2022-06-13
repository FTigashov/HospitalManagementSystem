package com.example.qualifiedwork.patientAccount;

import com.example.qualifiedwork.DBHandler;
import com.example.qualifiedwork.StartApp;
import com.example.qualifiedwork.adminAccount.ProtocolRecord;
import com.example.qualifiedwork.adminAccount.SheduleRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientVisitsController{

    @FXML
    private Button backToPatientMenuBtn;

    @FXML
    private TableColumn<ProtocolRecord, String> columnDoctorFatherName;

    @FXML
    private TableColumn<ProtocolRecord, String> columnDoctorName;

    @FXML
    private TableColumn<ProtocolRecord, String> columnDoctorSecondName;

    @FXML
    private TableColumn<ProtocolRecord, String> columnDoctorSpeciality;

    @FXML
    private TableColumn<ProtocolRecord, String> columnMainDiagnos;

    @FXML
    private TableColumn<ProtocolRecord, String> columnVisitDate;

    @FXML
    private TableView<ProtocolRecord> listOfVisits;

    @FXML
    private Label doctorNameLabel;

    @FXML
    private Label doctorSecondNameLabel;

    @FXML
    private Label patientNameLabel;

    @FXML
    private Button openProtocolBtn;

    @FXML
    private Label patientSecondNameLabel;

    private StartApp startApp;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    @FXML
    void openPatientMenu(MouseEvent event) {
        startApp.switchToPatientMainMenuScene();
    }

    public void setInfoAboutPatient(String secondName, String name) {
        patientNameLabel.setText(name);
        patientSecondNameLabel.setText(secondName);
    }

    @FXML
    void openProtocol(MouseEvent event) {
        ProtocolRecord record = listOfVisits.getSelectionModel().getSelectedItem();
        if (record == null) {
            startApp.showErrorLoginAlert("Ошибка просмотра протокола", "Чтобы просмотреть протокол, выберите из списка\nнеобходимую запись.");
            return;
        } else {
            startApp.getInfoForShowProtocol(doctorSecondNameLabel.getText().trim(), doctorNameLabel.getText().trim(), patientSecondNameLabel.getText().trim(), patientNameLabel.getText().trim());
            startApp.switchToPatientProtocol();
        }
    }

    private ObservableList<ProtocolRecord> oblist = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        listOfVisits.setRowFactory(event -> {
            TableRow<ProtocolRecord> row = new TableRow<>();
            row.setOnMouseClicked(event2 -> {
                ProtocolRecord clickedRow = row.getItem();
                if (clickedRow != null) {
                    doctorSecondNameLabel.setText(clickedRow.getDoctorSecondName());
                    doctorNameLabel.setText(clickedRow.getDoctorName());
                } else {
//                   System.out.println("Не выбрано поле");
                }
            });
            return row;
        });
    }

    public void showTableOfVisits() {
        listOfVisits.getItems().clear();
        try {
            Connection connection = DBHandler.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT protocol_date, doc_second_name, doc_name, doc_father_name, doc_respons_status, main_diagnos FROM patient_protocol WHERE pat_second_name = ? AND pat_name = ?");
            preparedStatement.setString(1, patientSecondNameLabel.getText().trim());
            preparedStatement.setString(2, patientNameLabel.getText().trim());

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                oblist.add(new ProtocolRecord(resultSet.getString("protocol_date"),
                        resultSet.getString("doc_second_name"),
                        resultSet.getString("doc_name"),
                        resultSet.getString("doc_father_name"),
                        resultSet.getString("doc_respons_status"),
                        resultSet.getString("main_diagnos")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        columnVisitDate.setCellValueFactory(new PropertyValueFactory<>("dateOfProtocol"));
        columnDoctorSecondName.setCellValueFactory(new PropertyValueFactory<>("doctorSecondName"));
        columnDoctorName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        columnDoctorFatherName.setCellValueFactory(new PropertyValueFactory<>("doctorFatherName"));
        columnDoctorSpeciality.setCellValueFactory(new PropertyValueFactory<>("doctorResponsStatus"));
        columnMainDiagnos.setCellValueFactory(new PropertyValueFactory<>("mainDiagnos"));

        listOfVisits.setItems(oblist);
    }

}
