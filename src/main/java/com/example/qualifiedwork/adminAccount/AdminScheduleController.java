package com.example.qualifiedwork.adminAccount;

import com.example.qualifiedwork.DBHandler;
import com.example.qualifiedwork.StartApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminScheduleController implements Initializable {
    @FXML
    private Button DeleteRecordBtn;

    @FXML
    private Button addNewRecordBtn;

    @FXML
    private Button adminMenuMainBtn;

    @FXML
    private Button changeRecordBtn;

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

    @FXML
    private Label cabNumLabel;

    @FXML
    private Label fridayLabel;

    @FXML
    private Label fullnameLabel;

    @FXML
    private Label mondayLabel;

    @FXML
    private Label specialityLabel;

    @FXML
    private Label thursdayLabel;

    @FXML
    private Label tuesdayLabel;

    @FXML
    private Label wednesdayLabel;

    private StartApp startApp;

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    @FXML
    void addNewRecordIntoTable(MouseEvent event) {
        startApp.switchToAddRowInShedule();
    }

    @FXML
    void changeRecord(MouseEvent event) {
        SheduleRecord record = shedule.getSelectionModel().getSelectedItem();
        if (record == null) {
            startApp.showErrorLoginAlert("Ошибка выбора записи", "Для выполнения изменения,\nнеобходимо выбрать запись в таблице.");
        } else {
            startApp.getInfoAboutScheduleRow(fullnameLabel.getText(), specialityLabel.getText(), cabNumLabel.getText(), mondayLabel.getText(), tuesdayLabel.getText(), wednesdayLabel.getText(), thursdayLabel.getText(), fridayLabel.getText());
            clearFields();
            startApp.swichToChanScheduleRow();
        }
    }

    private void clearFields() {
        fullnameLabel.setText("");
        specialityLabel.setText("");
        cabNumLabel.setText("");
        mondayLabel.setText("");
        tuesdayLabel.setText("");
        wednesdayLabel.setText("");
        thursdayLabel.setText("");
        fridayLabel.setText("");
    }

    @FXML
    void deleteRecord(MouseEvent event) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        SheduleRecord record = shedule.getSelectionModel().getSelectedItem();
        if (record == null) {
            startApp.showErrorLoginAlert("Ошибка удаления", "Необходимо выбрать запись в таблице.");
            return;
        } else {
            String speciality = record.getSpeciality();
            try {
                connection = DBHandler.getConnection();
                preparedStatement = connection.prepareStatement("DELETE FROM schedule WHERE speciality = ?");
                preparedStatement.setString(1, speciality);

                preparedStatement.executeUpdate();

                makeFieldsIsEmpty();

                startApp.showSuccessMessage("Уведомление об удалении  записи", "Запись успешно удалена", "Таблица обновлена.");
                refreshDataFromTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void makeFieldsIsEmpty() {
        fullnameLabel.setText("");
        specialityLabel.setText("");
        cabNumLabel.setText("");
        mondayLabel.setText("");
        tuesdayLabel.setText("");
        wednesdayLabel.setText("");
        thursdayLabel.setText("");
        fullnameLabel.setText("");
    }

    @FXML
    void openMainMenu(MouseEvent event) {
        startApp.switchToAdminMainMenuScene();
    }

    private ObservableList<SheduleRecord> oblist = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        shedule.setRowFactory(event -> {
            TableRow<SheduleRecord> row = new TableRow<>();
            row.setOnMouseClicked(event2 -> {
                SheduleRecord clickedRow = row.getItem();
                if (clickedRow != null) {
                    fullnameLabel.setText(clickedRow.getFullName());
                    specialityLabel.setText(clickedRow.getSpeciality());
                    cabNumLabel.setText(clickedRow.getCabNum());
                    mondayLabel.setText(clickedRow.getMonday());
                    tuesdayLabel.setText(clickedRow.getTuesday());
                    wednesdayLabel.setText(clickedRow.getWednesday());
                    thursdayLabel.setText(clickedRow.getThursday());
                    fridayLabel.setText(clickedRow.getFriday());
                } else {
//                   System.out.println("Не выбрано поле");
                }
            });
            return row;
        });
        refreshDataFromTable();
    }

    public void refreshDataFromTable() {
        shedule.getItems().clear();
        try {
            Connection connection = DBHandler.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM schedule");

            while (resultSet.next()) {
                oblist.add(new SheduleRecord(resultSet.getString("full_name"),
                        resultSet.getString("speciality"),
                        resultSet.getString("cab_num"),
                        resultSet.getString("monday"),
                        resultSet.getString("tuesday"),
                        resultSet.getString("wednesday"),
                        resultSet.getString("thursday"),
                        resultSet.getString("friday")));
            }
        } catch (SQLException e) {
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
