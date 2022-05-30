package com.example.qualifiedwork.doctorAccount;

import com.example.qualifiedwork.DBHandler;
import com.example.qualifiedwork.StartApp;
import com.example.qualifiedwork.adminAccount.SheduleRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorSchedule {

    @FXML
    private Button backToDoctorMenuBtn;

    @FXML
    private TableColumn<SheduleRecord, String> columnCabNum;

    @FXML
    private TableColumn<SheduleRecord, String> columnFriday;

    @FXML
    private TableColumn<SheduleRecord, String> columnFullname;

    @FXML
    private TableColumn<SheduleRecord, String> columnMonday;

    @FXML
    private TableColumn<SheduleRecord, String> columnSpeciality;

    @FXML
    private TableColumn<SheduleRecord, String> columnThurs;

    @FXML
    private TableColumn<SheduleRecord, String> columnTues;

    @FXML
    private TableColumn<SheduleRecord, String> columnWed;

    @FXML
    private TableView<SheduleRecord> shedule;

    private StartApp startApp;

    public void setStartApp(StartApp startApp) { this.startApp = startApp; }

    @FXML
    void openDoctorMenu(MouseEvent event) {
        startApp.switchToDoctorMainMenuScene();
    }

    private ObservableList<SheduleRecord> oblist = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        refreshDataFromTable();
    }

    public void refreshDataFromTable() {
        try {
            Connection connection = DBHandler.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM shedule");

            while (resultSet.next()) {
                oblist.add(new SheduleRecord(resultSet.getString("fullName"),
                        resultSet.getString("speciality"),
                        resultSet.getString("cabNum"),
                        resultSet.getString("monday"),
                        resultSet.getString("tuesday"),
                        resultSet.getString("wednesday"),
                        resultSet.getString("thursday"),
                        resultSet.getString("friday")));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        columnFullname.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        columnSpeciality.setCellValueFactory(new PropertyValueFactory<>("speciality"));
        columnCabNum.setCellValueFactory(new PropertyValueFactory<>("cabNum"));
        columnMonday.setCellValueFactory(new PropertyValueFactory<>("monday"));
        columnTues.setCellValueFactory(new PropertyValueFactory<>("tuesday"));
        columnWed.setCellValueFactory(new PropertyValueFactory<>("wednesday"));
        columnThurs.setCellValueFactory(new PropertyValueFactory<>("thursday"));
        columnFriday.setCellValueFactory(new PropertyValueFactory<>("friday"));

        shedule.setItems(oblist);
    }

}
