package com.example.qualifiedwork.doctorAccount.controllers;

import com.example.qualifiedwork.db_connection.DBHandler;
import com.example.qualifiedwork.StartApp;
import com.example.qualifiedwork.adminAccount.functional.DocProtocolView;
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

public class ArchiveOfPatients {
    @FXML
    private TableColumn<DocProtocolView, String> columnBirthDate;

    @FXML
    private TableColumn<DocProtocolView, String> columnFatherName;

    @FXML
    private TableColumn<DocProtocolView, String> columnMedCard;

    @FXML
    private TableColumn<DocProtocolView, String> columnName;

    @FXML
    private TableColumn<DocProtocolView, String> columnSecondName;

    @FXML
    private TableColumn<DocProtocolView, String> date_col;

    @FXML
    private TableColumn<DocProtocolView, String> diagnos_col;

    @FXML
    private Button doctorMenuBtn;

    @FXML
    private TableView<DocProtocolView> listOfPatProtocols;

    @FXML
    private Label patNameLabel;

    @FXML
    private Label patSecondNameLabel;

    @FXML
    private Label dateOfProtocolLabel;

    @FXML
    private TextField searchField;

    @FXML
    private Button showInfoAboutPatient;

    public ArchiveOfPatients() throws SQLException {
    }


    @FXML
    void openMenu(MouseEvent event) {
        startApp.switchToDoctorMainMenuScene();
    }

    @FXML
    void openRecord(MouseEvent event) {

    }

    @FXML
    private void initialize() {
        listOfPatProtocols.setRowFactory(event -> {
            TableRow<DocProtocolView> row = new TableRow<>();
            row.setOnMouseClicked(event2 -> {
                DocProtocolView clickedRow = row.getItem();
                if (clickedRow != null) {
                    startApp.getPatSecondName = clickedRow.getPatSecondName();
                    startApp.getPatName = clickedRow.getPatName();
                } else {
//                   System.out.println("Не выбрано поле");
                }
            });
            return row;
        });
        refreshTable();
    }

    private ObservableList<DocProtocolView> oblist = FXCollections.observableArrayList();

    public String docSecondName, docName;


    public void refreshTable() {
        listOfPatProtocols.getItems().clear();
        try {
            PreparedStatement pr = connection.prepareStatement("SELECT * FROM patient_protocol WHERE doc_second_name = ? AND doc_name = ?");
            pr.setString(1, docSecondName);
            pr.setString(2, docName);
            ResultSet resultSet = pr.executeQuery();

            while (resultSet.next()) {
                oblist.add(new DocProtocolView(resultSet.getString("protocol_date"),
                        resultSet.getString("pat_second_name"),
                        resultSet.getString("pat_name"),
                        resultSet.getString("pat_father_name"),
                        resultSet.getString("pat_birth_date"),
                        resultSet.getString("pat_snils_card"),
                        resultSet.getString("main_diagnos")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnSecondName.setCellValueFactory(new PropertyValueFactory<>("patSecondName"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("patName"));
        columnFatherName.setCellValueFactory(new PropertyValueFactory<>("patFatherName"));
        columnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        columnMedCard.setCellValueFactory(new PropertyValueFactory<>("polisCard"));
        diagnos_col.setCellValueFactory(new PropertyValueFactory<>("diagnos"));

        listOfPatProtocols.setItems(oblist);
    }

    public void makeFieldsEmpty() {
        searchField.setText("");
        patSecondNameLabel.setText("");
        patNameLabel.setText("");
    }

    private Connection connection = DBHandler.getConnection();

    private StartApp startApp;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }
}
