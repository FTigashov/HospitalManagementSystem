package com.example.qualifiedwork.patientAccount.controllers;

import com.example.qualifiedwork.db_connection.DBHandler;
import com.example.qualifiedwork.StartApp;
import com.example.qualifiedwork.adminAccount.functional.DirectionTableRecord;
import com.example.qualifiedwork.adminAccount.functional.PatDirectionRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PatListOfDirectionsController implements Initializable {

    @FXML
    private Button doctorMenuBtn;

    @FXML
    private TableColumn<DirectionTableRecord, String> fatherNameCol;

    @FXML
    private TableView<PatDirectionRecord> listOfDirections;

    @FXML
    private TableColumn<DirectionTableRecord, String> nameCol;

    @FXML
    private TableColumn<DirectionTableRecord, String> openDateCol;

    @FXML
    private TableColumn<DirectionTableRecord, String> respons_col;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<DirectionTableRecord, String> secondNameCol;

    @FXML
    private Button showInfoAboutPatient;

    @FXML
    private TableColumn<DirectionTableRecord, String> typeCol;

    @FXML
    void openMenu(MouseEvent event) {
        searchField.setText("");
        startApp.switchToPatientMainMenuScene();
    }

    @FXML
    void showInfoAboutPatient(MouseEvent event) {
        PatDirectionRecord record = listOfDirections.getSelectionModel().getSelectedItem();
        if (record == null) {
            startApp.showErrorLoginAlert("Ошибка просмотра направления", "Необходимо выбрать запись в таблице.");
            return;
        } else {
            startApp.switchToShowPatDirectionScene(date, docSecondName, docName);
        }
    }

    private StartApp startApp;

    public String docSecondName, docName, patSecondName, patName, date;

    private ObservableList<PatDirectionRecord> oblist = FXCollections.observableArrayList();

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listOfDirections.setRowFactory(event -> {
            TableRow<PatDirectionRecord> row = new TableRow<>();
            row.setOnMouseClicked(event2 -> {
                PatDirectionRecord clickedRow = row.getItem();
                if (clickedRow != null) {
                    date = clickedRow.getDate_of_direction().trim();
                    docSecondName = clickedRow.getSecond_name().trim();
                    docName = clickedRow.getName().trim();
                } else {
//                   System.out.println("Не выбрано поле");
                }
            });
            return row;
        });

        refreshTable();
    }

    public void refreshTable() {
        try {
            listOfDirections.getItems().clear();
            Connection conn = DBHandler.getConnection();
            System.out.println("data pat = " + patSecondName + " " + patName);
            String query = "select date_of_direction, type_of_direction, doc_second_name, doc_name, doc_father_name, doc_respons_status FROM patient_direction WHERE pat_second_name = '" + patSecondName + "' AND pat_name = '" + patName + "'";
            ResultSet resultSet = conn.createStatement().executeQuery(query);

            while (resultSet.next()) {
                oblist.add(new PatDirectionRecord(resultSet.getString("date_of_direction"),
                        resultSet.getString("type_of_direction"),
                        resultSet.getString("doc_second_name"),
                        resultSet.getString("doc_name"),
                        resultSet.getString("doc_father_name"),
                        resultSet.getString("doc_respons_status")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        openDateCol.setCellValueFactory(new PropertyValueFactory<>("date_of_direction"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type_of_direction"));
        secondNameCol.setCellValueFactory(new PropertyValueFactory<>("second_name"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        fatherNameCol.setCellValueFactory(new PropertyValueFactory<>("father_name"));
        respons_col.setCellValueFactory(new PropertyValueFactory<>("respons_status"));

        listOfDirections.setItems(oblist);
    }
}
