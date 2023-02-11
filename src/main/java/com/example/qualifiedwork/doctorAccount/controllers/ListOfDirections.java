package com.example.qualifiedwork.doctorAccount.controllers;

import com.example.qualifiedwork.db_connection.DBHandler;
import com.example.qualifiedwork.StartApp;
import com.example.qualifiedwork.adminAccount.functional.DirectionTableRecord;
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

public class ListOfDirections  implements Initializable {
    @FXML
    private TableColumn<?, ?> birthDateCol;

    @FXML
    private Button doctorMenuBtn;

    @FXML
    private TableColumn<DirectionTableRecord, String> fatherNameCol;

    @FXML
    private TableView<DirectionTableRecord> listOfDirections;

    @FXML
    private TableColumn<DirectionTableRecord, String> nameCol;

    @FXML
    private TableColumn<DirectionTableRecord, String> openDateCol;

    @FXML
    private TableColumn<DirectionTableRecord, String> polisCol;

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
        startApp.switchToDoctorMainMenuScene();
    }

    @FXML
    void showInfoAboutPatient(MouseEvent event) {
        DirectionTableRecord record = listOfDirections.getSelectionModel().getSelectedItem();
        if (record == null) {
            startApp.showErrorLoginAlert("Ошибка просмотра направления", "Необходимо выбрать запись в таблице.");
            return;
        } else {
            startApp.switchToShowDirectionScene(date, patSecondName, patName);
        }
    }

    private StartApp startApp;

    public String docSecondName, docName, patSecondName, patName, date;

    private ObservableList<DirectionTableRecord> oblist = FXCollections.observableArrayList();

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listOfDirections.setRowFactory(event -> {
            TableRow<DirectionTableRecord> row = new TableRow<>();
            row.setOnMouseClicked(event2 -> {
                DirectionTableRecord clickedRow = row.getItem();
                if (clickedRow != null) {
                    date = clickedRow.getDate_of_direction().trim();
                    patSecondName = clickedRow.getSecond_name().trim();
                    patName = clickedRow.getName().trim();
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
            String query = "select date_of_direction, type_of_direction, pat_second_name, pat_name, pat_father_name, birth_date, polis_num FROM patient_direction WHERE doc_second_name = '" + docSecondName + "' AND doc_name = '" + docName + "'";
            ResultSet resultSet = conn.createStatement().executeQuery(query);

            while (resultSet.next()) {
                oblist.add(new DirectionTableRecord(resultSet.getString("date_of_direction"),
                        resultSet.getString("type_of_direction"),
                        resultSet.getString("pat_second_name"),
                        resultSet.getString("pat_name"),
                        resultSet.getString("pat_father_name"),
                        resultSet.getString("birth_date"),
                        resultSet.getString("polis_num")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        openDateCol.setCellValueFactory(new PropertyValueFactory<>("date_of_direction"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type_of_direction"));
        secondNameCol.setCellValueFactory(new PropertyValueFactory<>("second_name"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        fatherNameCol.setCellValueFactory(new PropertyValueFactory<>("father_name"));
        birthDateCol.setCellValueFactory(new PropertyValueFactory<>("birth_date"));
        polisCol.setCellValueFactory(new PropertyValueFactory<>("polis_card"));

        listOfDirections.setItems(oblist);
    }
}
