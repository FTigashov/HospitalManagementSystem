package com.example.qualifiedwork.adminAccount.controllers;

import com.example.qualifiedwork.db_connection.DBHandler;
import com.example.qualifiedwork.StartApp;
import com.example.qualifiedwork.adminAccount.functional.AdminRecord;
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

public class AdminListOfAllAdminsController implements Initializable {

    @FXML
    private Button DeleteRecordBtn;

    @FXML
    private Button addNewRecordBtn;

    @FXML
    private Button adminMenuMainBtn;

    @FXML
    private Label birthDateField;

    @FXML
    private Button changeRecordBtn;

    @FXML
    private TableColumn<AdminRecord, String> columnBirthDate;

    @FXML
    private TableColumn<AdminRecord, String> columnDateEmpl;

    @FXML
    private TableColumn<AdminRecord, String> columnFatherName;

    @FXML
    private TableColumn<AdminRecord, String> columnLogin;

    @FXML
    private TableColumn<AdminRecord, String> columnName;

    @FXML
    private TableColumn<AdminRecord, String> columnPassword;

    @FXML
    private TableColumn<AdminRecord, String> columnRespons;

    @FXML
    private TableColumn<AdminRecord, String> columnSecondName;

    @FXML
    private TableView<AdminRecord> listOfAdmins;

    @FXML
    private Label dateEmplField;

    @FXML
    private Label fatherNameField;

    @FXML
    private Label loginField;

    @FXML
    private Label nameField;

    @FXML
    private TextField searchField;

    @FXML
    private Label passwordField;

    @FXML
    private Label responsStatusChoice;

    @FXML
    private Label secondNameField;

    private StartApp startApp;

    private DBHandler dbHandler;
    private Connection connection = DBHandler.getConnection();;

    public AdminListOfAllAdminsController() throws SQLException {
    }

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    public void openMainMenu(MouseEvent mouseEvent) {
        makeFieldsIsEmpty();
        startApp.switchToAdminMainMenuScene();
    }

    private ObservableList<AdminRecord> oblist = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listOfAdmins.setRowFactory(event -> {
           TableRow<AdminRecord> row = new TableRow<>();
           row.setOnMouseClicked(event2 -> {
               AdminRecord clickedRow = row.getItem();
               if (clickedRow != null) {
                   secondNameField.setText(clickedRow.getSecondName());
                   nameField.setText(clickedRow.getName());
                   fatherNameField.setText(clickedRow.getFatherName());
                   birthDateField.setText(clickedRow.getBirthDate());
                   dateEmplField.setText(clickedRow.getDateEmpl());
                   responsStatusChoice.setText(clickedRow.getResponsStatus());
                   loginField.setText(clickedRow.getLogin());
                   passwordField.setText(clickedRow.getPassword());
               } else {
//                   System.out.println("Не выбрано поле");
               }
           });
            return row;
        });

        refreshDataFromTable();
        searchMethod();
    }

    private void searchMethod() {
        oblist.clear();
        searchField.setOnKeyReleased(e->{
            if (searchField.getText().equals("")) {
                refreshDataFromTable();
            } else {
                try {
                    String searchText = searchField.getText().trim();
                    PreparedStatement pr = connection.prepareStatement("select * from doc_default_data where type_of_account = 'admin' and " +
                            "(second_name like '%" + searchText + "%'\n" +
                            "    or name like '%" + searchText + "%'\n" +
                            "    or father_name like '%" + searchText + "%'\n" +
                            "    or birth_date like '%" + searchText + "%'\n" +
                            "    or employee_date like '%" + searchText + "%'\n" +
                            "    or responsibility_status like '%" + searchText + "%'\n" +
                            "    or login like '%" + searchText +"%'\n" +
                            "    or password like '%" + searchText + "%')");
                    ResultSet rs = pr.executeQuery();
                    oblist.clear();
                    while (rs.next()) {
                        oblist.add(new AdminRecord(rs.getString("second_name"),
                                rs.getString("name"),
                                rs.getString("father_name"),
                                rs.getString("birth_date"),
                                rs.getString("employee_date"),
                                rs.getString("responsibility_status"),
                                rs.getString("login"),
                                rs.getString("password")));
                        listOfAdmins.setItems(oblist);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    @FXML
    void addNewRecordIntoTable(MouseEvent event) {
        makeFieldsIsEmpty();
        startApp.switchToCreateNewRecordForm();
    }

    public void refreshDataFromTable() {
        try {
            listOfAdmins.getItems().clear();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM doc_default_data WHERE type_of_account = 'admin'");

            while (resultSet.next()) {
                oblist.add(new AdminRecord(resultSet.getString("second_name"),
                        resultSet.getString("name"),
                        resultSet.getString("father_name"),
                        resultSet.getString("birth_date"),
                        resultSet.getString("employee_date"),
                        resultSet.getString("responsibility_status"),
                        resultSet.getString("login"),
                        resultSet.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        columnSecondName.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnFatherName.setCellValueFactory(new PropertyValueFactory<>("fatherName"));
        columnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        columnDateEmpl.setCellValueFactory(new PropertyValueFactory<>("dateEmpl"));
        columnRespons.setCellValueFactory(new PropertyValueFactory<>("responsStatus"));
        columnLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        listOfAdmins.setItems(oblist);
    }

    @FXML
    void deleteRecord(MouseEvent event) {
        PreparedStatement preparedStatement;
        AdminRecord record = listOfAdmins.getSelectionModel().getSelectedItem();
        if (record == null) {
            startApp.showErrorLoginAlert("Ошибка удаления", "Необходимо выбрать запись в таблице.");
            return;
        } else {
            String loginRecord = record.getLogin();
            try {
                preparedStatement = connection.prepareStatement("DELETE FROM doc_default_data WHERE login = ?");
                preparedStatement.setString(1, loginRecord);

                preparedStatement.executeUpdate();

                makeFieldsIsEmpty();

                startApp.showSuccessMessage("Уведомление об удалении  записи", "Запись успешно удалена", "Таблица обновлена.");
                refreshDataFromTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void changeRecord(MouseEvent event) {
        AdminRecord record = listOfAdmins.getSelectionModel().getSelectedItem();
        if (record == null) {
            startApp.showErrorLoginAlert("Ошибка выбора записи", "Для проведения изменения записи,\nнеобходимо выбрать нужную в таблице.");
            return;
        } else {
            startApp.getInfoAboutAdminAccount(secondNameField.getText().trim(), nameField.getText().trim());
//            System.out.println("send data: " + secondNameField.getText().trim() + " " + nameField.getText().trim());
            makeFieldsIsEmpty();
            startApp.switchToChangeAdminRecordScene();
        }
    }

    private void makeFieldsIsEmpty() {
        searchField.setText("");
        secondNameField.setText("");
        nameField.setText("");
        fatherNameField.setText("");
        birthDateField.setText("");
        dateEmplField.setText("");
        responsStatusChoice.setText("");
        loginField.setText("");
        passwordField.setText("");
    }
}
