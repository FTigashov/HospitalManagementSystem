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

public class AdminListOfAllPatientsController implements Initializable{

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
    private TableColumn<PatientRecord, String> columnAddress;

    @FXML
    private TableColumn<PatientRecord, String> columnBirthDate;

    @FXML
    private TableColumn<PatientRecord, String> columnFatherName;

    @FXML
    private TableColumn<PatientRecord, String> columnLogin;

    @FXML
    private TableColumn<PatientRecord, String> columnMedCard;

    @FXML
    private TableColumn<PatientRecord, String> columnName;

    @FXML
    private TableColumn<PatientRecord, String> columnPassword;

    @FXML
    private TableColumn<PatientRecord, String> columnSecondName;

    @FXML
    private TableColumn<PatientRecord, String> columnSnils;

    @FXML
    private Label dateEmplField;

    @FXML
    private Label fatherNameField;

    @FXML
    private TableView<PatientRecord> listOfPatients;

    @FXML
    private Label loginField;

    @FXML
    private Label medCardField;

    @FXML
    private Label nameField;

    @FXML
    private Label passwordField;

    @FXML
    private Label secondNameField;

    @FXML
    private Label snilsField;

    @FXML
    private Label addressField;

    @FXML
    private TextField searchField;


    private StartApp startApp;
    private Connection connection = DBHandler.getConnection();;

    public AdminListOfAllPatientsController() throws SQLException {
    }

    public void setStartApp(StartApp startApp) {
        this.startApp = startApp;
    }

    @FXML
    void addNewRecordIntoTable(MouseEvent event) {
        makeFieldsIsEmpty();
        startApp.switchToAddNewPatientRecordScene();
    }

    @FXML
    void changeRecord(MouseEvent event) {
        PatientRecord record = listOfPatients.getSelectionModel().getSelectedItem();
        if (record == null)
        {
            startApp.showErrorLoginAlert("Ошибка выбора записи", "Для проведения изменения записи,\nнеобходимо выбрать нужную в таблице.");
            return;
        } else {
            startApp.getInfoAboutPatientAccount(secondNameField.getText(),
                    nameField.getText(),
                    fatherNameField.getText(),
                    birthDateField.getText(),
                    medCardField.getText(),
                    snilsField.getText(),
                    loginField.getText(),
                    passwordField.getText(),
                    addressField.getText());

            makeFieldsIsEmpty();

            startApp.switchToChangePatientRecordScene();
        }
    }

    @FXML
    void deleteRecord(MouseEvent event) {
        PreparedStatement preparedStatement = null;
        PatientRecord record = listOfPatients.getSelectionModel().getSelectedItem();
        if (record == null) {
            startApp.showErrorLoginAlert("Ошибка удаления", "Необходимо выбрать запись в таблице.");
            return;
        } else {
            String loginRecord = record.getLogin();
            try {
                preparedStatement = connection.prepareStatement("DELETE FROM patient_default_data WHERE login = ?");
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

    private void searchMethod() {
        oblist.clear();
        searchField.setOnKeyReleased(e->{
            if (searchField.getText().equals("")) {
                refreshDataFromTable();
                return;
            } else {
                try {
                    String searchText = searchField.getText().trim();
                    String query = "select * from patient_default_data where father_name like '%" + searchText + "%'" +
                            "or name like '%" + searchText + "%'" +
                            "or father_name like '%" + searchText + "%'" +
                            "or birth_date like '%" + searchText + "%'" +
                            "or address like '%" + searchText + "%'" +
                            "or login like '%" + searchText + "%'" +
                            "or password like '%" + searchText + "%'" +
                            "or med_card like '%" + searchText + "%'" +
                            "or snils_card like '%" + searchText + "%'";

                    PreparedStatement pr = connection.prepareStatement(query);
                    ResultSet rs = pr.executeQuery();
                    oblist.clear();
                    while (rs.next()) {
                        oblist.add(new PatientRecord(rs.getString("second_name"),
                                rs.getString("name"),
                                rs.getString("father_name"),
                                rs.getString("birth_date"),
                                rs.getString("address"),
                                rs.getString("login"),
                                rs.getString("password"),
                                rs.getString("med_card"),
                                rs.getString("snils_card")));
                        listOfPatients.setItems(oblist);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    @FXML
    void openMainMenu(MouseEvent event) {
        makeFieldsIsEmpty();
        startApp.switchToAdminMainMenuScene();
    }

    private ObservableList<PatientRecord> oblist = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listOfPatients.setRowFactory(event -> {
            TableRow<PatientRecord> row = new TableRow<>();
            row.setOnMouseClicked(event2 -> {
                PatientRecord clickedRow = row.getItem();
                if (clickedRow != null) {
                    secondNameField.setText(clickedRow.getSecondName());
                    nameField.setText(clickedRow.getName());
                    fatherNameField.setText(clickedRow.getFatherName());
                    birthDateField.setText(clickedRow.getBirthDate());
                    medCardField.setText(clickedRow.getMedCard());
                    snilsField.setText(clickedRow.getSnilsCard());
                    loginField.setText(clickedRow.getLogin());
                    passwordField.setText(clickedRow.getPassword());
                    addressField.setText(clickedRow.getAddress());
                } else {
//                    System.out.println("");
                }
            });
            return row;
        });
        refreshDataFromTable();
        searchMethod();
    }

    public void refreshDataFromTable() {
        listOfPatients.getItems().clear();
        try {
            Connection connection = DBHandler.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM patient_default_data");

            while (resultSet.next()) {
                oblist.add(new PatientRecord(resultSet.getString("second_name"),
                        resultSet.getString("name"),
                        resultSet.getString("father_name"),
                        resultSet.getString("birth_date"),
                        resultSet.getString("address"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("med_card"),
                        resultSet.getString("snils_card")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        columnSecondName.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnFatherName.setCellValueFactory(new PropertyValueFactory<>("fatherName"));
        columnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        columnMedCard.setCellValueFactory(new PropertyValueFactory<>("medCard"));
        columnSnils.setCellValueFactory(new PropertyValueFactory<>("snilsCard"));
        columnLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        listOfPatients.setItems(oblist);
    }

    private void makeFieldsIsEmpty() {
        searchField.setText("");
        secondNameField.setText("");
        nameField.setText("");
        fatherNameField.setText("");
        birthDateField.setText("");
        addressField.setText("");
        loginField.setText("");
        passwordField.setText("");
        medCardField.setText("");
        snilsField.setText("");
    }
}
