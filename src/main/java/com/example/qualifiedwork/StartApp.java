package com.example.qualifiedwork;

import com.example.qualifiedwork.adminAccount.*;
import com.example.qualifiedwork.authenticaton.*;
import com.example.qualifiedwork.patientAccount.PatientInfoController;
import com.example.qualifiedwork.patientAccount.PatientMenuController;
import com.example.qualifiedwork.patientAccount.PatientProfileController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class StartApp extends Application {
    private Stage stage;

    //authentication
    private ChoiceViewController choiceViewController;
    private AdminLoginController adminLoginController;
    private DoctorLoginController doctorLoginController;
    private PatientLoginController patientLoginController;
    private PatientRegisterController patientRegisterController;

    private Scene choiceViewScene;
    private Scene adminScene;
    private Scene doctorScene;
    private Scene patientLoginScene;
    private Scene patientRegisterScene;

    //patientAccount
    private PatientMenuController patientMenuController;
    private PatientInfoController patientInfoController;
    private PatientProfileController patientProfileController;

    private Scene patientMenuScene;
    private Scene patientProfileScene;
    private Scene patientInfoScene;

    //adminAccount
    private AdminProfileController adminProfileController;
    private AdminMenuController adminMenuController;
    private AdminInfoController adminInfoController;

    private AdminListOfAllAdminsController adminListOfAllAdminsController;
    private AdminCreateNewAdminRecord adminCreateNewAdminRecord;
    private AdminChangeRecord adminChangeRecord;

    private AdminListOfAllDoctorsController adminListOfAllDoctorsController;
    private AdminCreateNewDoctorRecordController adminCreateNewDoctorRecordController;
    private AdminChangeDoctorRecord adminChangeDoctorRecord;

    private AdminListOfAllPatientsController adminListOfAllPatientsController;
    private AdminCreateNewPatientRecord adminCreateNewPatientRecord;
    private AdminChangePatientRecord adminChangePatientRecord;

    private AdminSheduleController adminSheduleController;
    private AddNewRowToShedule addNewRowToShedule;

    private Scene adminProfileScene;
    private Scene adminMenuScene;
    private Scene adminInfoScene;

    private Scene listOfAllAdminsScene;
    private Scene createNewAdminRecordScene;
    private Scene changeAdminRecordScene;

    private Scene listOfAllDoctorsScene;
    private Scene createNewDoctorRecordScene;
    private Scene changeDoctorRecordScene;

    private Scene listOfAllPatientsScene;
    private Scene createNewPatientRecordScene;
    private Scene changePatientRecordScene;

    private Scene sheduleScene;
    private Scene addNewRowIntoSheduleScene;
    private Scene changeTheRowInSheduleScene;

    private String getSecondName;
    private String getName;

    private String getAdminSecondName, getAdminName, getAdminfatherName, getAdminBirthDate, getAdminEmplDate, getAdminResponsStatus, getAdminLogin, getAdminPassword;
    private String getPatientSecondName, getPatientName, getPatientFatherName, getPatientBirthDate, getPatientMedCard, getPatientSnilsCard, getPatientLogin, getPatientPassword, getPatientAddress;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        stage.setTitle("Hospital management system");
        stage.setResizable(false);
        stage.setFullScreen(false);

        choiceViewScene = createChoiceViewScene();

        adminScene = createAdminScene();
        doctorScene = createDoctorScene();
        patientLoginScene = createPatientLoginScene();
        patientRegisterScene = createPatientRegisterScene();

        patientMenuScene = createPatientMenuScene();
        patientProfileScene = createPatientProfileScene();
        patientInfoScene = createPatientInfoScene();

        adminProfileScene = createAdminProfileScene();
        adminInfoScene = createAdminInfoScene();
        adminMenuScene = createAdminMenuScene();

        listOfAllAdminsScene = createListOfAllAdmins();
        createNewAdminRecordScene = createNewAdminRecordScene();
        changeAdminRecordScene = createNewChangeAdminRecordScene();

        listOfAllDoctorsScene = createListOfAllDoctors();
        createNewDoctorRecordScene = createNewDoctorScene();
        changeDoctorRecordScene = createChangeDoctorRecordScene();

        listOfAllPatientsScene = createListOfAllPatientsScene();
        createNewPatientRecordScene = createNewPatientScene();
        changePatientRecordScene = createChangePatientRecordScene();

        sheduleScene = createSheduleScene();
        addNewRowIntoSheduleScene = createAddNewRowIntoShedule();

        stage.setScene(choiceViewScene);
        stage.show();

    }

    private Scene createAddNewRowIntoShedule() throws IOException {
        URL fxmLocation = getClass().getResource("/adminAccount/addNewRowToShedule.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        addNewRowIntoSheduleScene = new Scene(loader.load());
        addNewRowToShedule = loader.getController();
        addNewRowToShedule.setStartApp(this);

        return addNewRowIntoSheduleScene;
    }

    private Scene createSheduleScene() throws IOException {
        URL fxmLocation = getClass().getResource("/adminAccount/shedule.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        sheduleScene = new Scene(loader.load());
        adminSheduleController = loader.getController();
        adminSheduleController.setStartApp(this);

        return sheduleScene;
    }

    private Scene createListOfAllPatientsScene() throws IOException {
        URL fxmLocation = getClass().getResource("/adminAccount/adminListOfAllPatients.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        listOfAllPatientsScene = new Scene(loader.load());
        adminListOfAllPatientsController = loader.getController();
        adminListOfAllPatientsController.setStartApp(this);

        return listOfAllPatientsScene;
    }

    private Scene createNewPatientScene() throws IOException {
        URL fxmLocation = getClass().getResource("/adminAccount/adminCreateNewPatientRecord.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        createNewPatientRecordScene = new Scene(loader.load());
        adminCreateNewPatientRecord = loader.getController();
        adminCreateNewPatientRecord.setStartApp(this);

        return createNewPatientRecordScene;
    }

    private Scene createChangePatientRecordScene() throws IOException {
        URL fxmLocation = getClass().getResource("/adminAccount/adminChangePatientRecord.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        changePatientRecordScene = new Scene(loader.load());
        adminChangePatientRecord = loader.getController();
        adminChangePatientRecord.setStartApp(this);

        return changePatientRecordScene;
    }

    private Scene createChangeDoctorRecordScene() throws IOException {
        URL fxmLocation = getClass().getResource("/adminAccount/adminChangeDoctorRecord.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        changeDoctorRecordScene = new Scene(loader.load());
        adminChangeDoctorRecord = loader.getController();
        adminChangeDoctorRecord.setStartApp(this);

        return changeDoctorRecordScene;
    }

    private Scene createNewDoctorScene() throws IOException {
        URL fxmLocation = getClass().getResource("/adminAccount/adminCreateNewDoctorRecord.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        createNewDoctorRecordScene = new Scene(loader.load());
        adminCreateNewDoctorRecordController = loader.getController();
        adminCreateNewDoctorRecordController.setStartApp(this);

        return createNewDoctorRecordScene;
    }

    private Scene createListOfAllDoctors() throws IOException {
        URL fxmLocation = getClass().getResource("/adminAccount/adminListOfAllDoctors.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        listOfAllDoctorsScene = new Scene(loader.load());
        adminListOfAllDoctorsController = loader.getController();
        adminListOfAllDoctorsController.setStartApp(this);

        return listOfAllDoctorsScene;
    }

    private Scene createNewChangeAdminRecordScene() throws IOException {
        URL fxmLocation = getClass().getResource("/adminAccount/adminChangeRecord.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        changeAdminRecordScene = new Scene(loader.load());
        adminChangeRecord = loader.getController();
        adminChangeRecord.setStartApp(this);

        return changeAdminRecordScene;
    }

    private Scene createNewAdminRecordScene() throws IOException {
        URL fxmLocation = getClass().getResource("/adminAccount/adminCreateNewRecord.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        createNewAdminRecordScene = new Scene(loader.load());
        adminCreateNewAdminRecord = loader.getController();
        adminCreateNewAdminRecord.setStartApp(this);

        return createNewAdminRecordScene;
    }

    private Scene createListOfAllAdmins() throws IOException {
        URL fxmLocation = getClass().getResource("/adminAccount/adminListOfAllAdmins.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        listOfAllAdminsScene = new Scene(loader.load());
        adminListOfAllAdminsController = loader.getController();
        adminListOfAllAdminsController.setStartApp(this);

        return listOfAllAdminsScene;
    }

    private Scene createAdminMenuScene() throws IOException {
        URL fxmLocation = getClass().getResource("/adminAccount/adminMenu.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        adminMenuScene = new Scene(loader.load());
        adminMenuController = loader.getController();
        adminMenuController.setStartApp(this);

        return adminMenuScene;
    }

    private Scene createAdminProfileScene() throws IOException {
        URL fxmLocation = getClass().getResource("/adminAccount/adminProfile.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        adminProfileScene = new Scene(loader.load());
        adminProfileController = loader.getController();
        adminProfileController.setStartApp(this);

        return adminProfileScene;
    }

    private Scene createAdminInfoScene() throws IOException {
        URL fxmLocation = getClass().getResource("/adminAccount/adminInfo.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        adminInfoScene = new Scene(loader.load());
        adminInfoController = loader.getController();
        adminInfoController.setStartApp(this);

        return adminInfoScene;
    }

    private Scene createPatientMenuScene() throws IOException {
        URL fxmLocation = getClass().getResource("/patientAccount/mainMenu.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        patientMenuScene = new Scene(loader.load());
        patientMenuController = loader.getController();
        patientMenuController.setStartApp(this);

        return patientMenuScene;
    }

    private Scene createPatientProfileScene() throws IOException {
        URL fxmLocation = getClass().getResource("/patientAccount/patientProfile.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        patientProfileScene = new Scene(loader.load());
        patientProfileController = loader.getController();
        patientProfileController.setStartApp(this);

        return patientProfileScene;
    }

    private Scene createPatientInfoScene() throws IOException {
        URL fxmLocation = getClass().getResource("/patientAccount/patientInfo.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        patientInfoScene = new Scene(loader.load());
        patientInfoController = loader.getController();
        patientInfoController.setStartApp(this);

        return patientInfoScene;
    }

    private Scene createChoiceViewScene() throws IOException {
        URL fxmLocation = getClass().getResource("/authentication/choiceView.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        choiceViewScene = new Scene(loader.load());
        choiceViewController = loader.getController();
        choiceViewController.setModelApp(this);

        return choiceViewScene;
    }

    private Scene createAdminScene() throws IOException {
        URL fxmLocation = getClass().getResource("/authentication/adminLogin.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        adminScene = new Scene(loader.load());
        adminLoginController = loader.getController();
        adminLoginController.setModelApp(this);

        return adminScene;
    }

    private Scene createDoctorScene() throws IOException {
        URL fxmLocation = getClass().getResource("/authentication/doctorLogin.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        doctorScene = new Scene(loader.load());
        doctorLoginController = loader.getController();
        doctorLoginController.setModelApp(this);

        return doctorScene;
    }

    private Scene createPatientLoginScene() throws IOException {
        URL fxmLocation = getClass().getResource("/authentication/patientLogin.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        patientLoginScene = new Scene(loader.load());
        patientLoginController = loader.getController();
        patientLoginController.setModelApp(this);

        return patientLoginScene;
    }

    private Scene createPatientRegisterScene() throws IOException {
        URL fxmLocation = getClass().getResource("/authentication/patientRegister.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        patientRegisterScene = new Scene(loader.load());
        patientRegisterController = loader.getController();
        patientRegisterController.setModelApp(this);

        return patientRegisterScene;
    }

    public void switchToChoiceScene() {
        stage.setScene(choiceViewScene);
        stage.centerOnScreen();
    }

    public void switchToAdminLoginScene() {
        stage.setScene(adminScene);
        stage.centerOnScreen();
    }

    public void switchToDoctorLoginScene() {
        stage.setScene(doctorScene);
        stage.centerOnScreen();
    }

    public void switchToPatientLoginScene() {
        stage.setScene(patientLoginScene);
        stage.centerOnScreen();
    }

    public void switchToPatientRegisterScene() {
        stage.setScene(patientRegisterScene);
        stage.centerOnScreen();
    }

    public void switchToPatientMainMenuScene() {
        stage.setScene(patientMenuScene);
        stage.centerOnScreen();
    }

    public void switchToPatientProfileScene() {
        stage.setScene(patientProfileScene);
        patientProfileController.getInfoAboutAccount(getSecondName, getName);

        stage.centerOnScreen();
    }

    public void switchToPatientInfoScene() {
        stage.setScene(patientInfoScene);
        stage.centerOnScreen();
    }

    public void switchToAdminMainMenuScene() {
        stage.setScene(adminMenuScene);
        stage.centerOnScreen();
    }

    public void switchToAdminProfileScene() {
        stage.setScene(adminProfileScene);
        adminProfileController.getInfoAboutAccount(getSecondName, getName);

        stage.centerOnScreen();
    }

    //общий метод для получения информации об аккаунте
    public void getInfoAboutAccountFromController(String secondName, String name) {
        getSecondName = secondName;
        getName = name;
    }

    public void getInfoAboutDoctorAccount(String secondNameField, String nameField, String fatherNameField, String birthDateField,
                                          String dateEmplField, String responsStatusChoice, String loginField, String passwordField) {
        getAdminSecondName = secondNameField;
        getAdminName = nameField;
        getAdminfatherName = fatherNameField;
        getAdminBirthDate = birthDateField;
        getAdminEmplDate = dateEmplField;
        getAdminResponsStatus = responsStatusChoice;
        getAdminLogin = loginField;
        getAdminPassword = passwordField;

        adminChangeDoctorRecord.setDataInFields(getAdminSecondName, getAdminName, getAdminfatherName, getAdminBirthDate, getAdminEmplDate, getAdminResponsStatus, getAdminLogin, getAdminPassword);

    }

    public void getInfoAboutAdminAccount(String secondNameField, String nameField, String fatherNameField, String birthDateField,
                    String dateEmplField, String responsStatusChoice, String loginField, String passwordField) {
        getAdminSecondName = secondNameField;
        getAdminName = nameField;
        getAdminfatherName = fatherNameField;
        getAdminBirthDate = birthDateField;
        getAdminEmplDate = dateEmplField;
        getAdminResponsStatus = responsStatusChoice;
        getAdminLogin = loginField;
        getAdminPassword = passwordField;

        adminChangeRecord.setDataInFields(getAdminSecondName, getAdminName, getAdminfatherName, getAdminBirthDate, getAdminEmplDate, getAdminResponsStatus, getAdminLogin, getAdminPassword);
    }

    public void switchToAdminInfoScene() {
        stage.setScene(adminInfoScene);
        stage.centerOnScreen();
    }

    public void switchToListOfAdmins() {
        stage.setScene(listOfAllAdminsScene);
        adminListOfAllAdminsController.refreshDataFromTable();
        stage.centerOnScreen();
    }

    public void showErrorLoginAlert(String errorName, String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Уведомление об ошибке");
        alert.setHeaderText(errorName);
        alert.setContentText(errorMessage);
        alert.show();
    }

    public void showSuccessMessage(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void switchToCreateNewRecordForm() {
        stage.setScene(createNewAdminRecordScene);
        stage.centerOnScreen();
    }

    public void switchToChangeAdminRecordScene() {
        stage.setScene(changeAdminRecordScene);
        stage.centerOnScreen();
    }

    public void switchToListOfDoctors() {
        stage.setScene(listOfAllDoctorsScene);
        adminListOfAllDoctorsController.refreshDataFromTable();
        stage.centerOnScreen();
    }

    public void switchToCreateNewDoctorRecordForm() {
        stage.setScene(createNewDoctorRecordScene);
        stage.centerOnScreen();
    }

    public void switchToChangeDoctorRecordScene() {
        stage.setScene(changeDoctorRecordScene);
        stage.centerOnScreen();
    }

    public void switchToListOfPatients() {
        stage.setScene(listOfAllPatientsScene);
        adminListOfAllPatientsController.refreshDataFromTable();
        stage.centerOnScreen();
    }

    public void switchToAddNewPatientRecordScene() {
        stage.setScene(createNewPatientRecordScene);
        stage.centerOnScreen();
    }

    public void switchToChangePatientRecordScene() {
        stage.setScene(changePatientRecordScene);
        stage.centerOnScreen();
    }

    public void getInfoAboutPatientAccount(String getPatientSecondName, String getPatientName, String getPatientFatherName, String getPatientBirthDate, String getPatientMedCard,
                                           String getPatientSnilsCard, String getPatientLogin, String getPatientPassword, String getPatientAddress) {
        this.getAdminSecondName = getPatientSecondName;
        this.getPatientName = getPatientName;
        this.getPatientFatherName = getPatientFatherName;
        this.getPatientBirthDate = getPatientBirthDate;
        this.getPatientMedCard = getPatientMedCard;
        this.getPatientSnilsCard = getPatientSnilsCard;
        this.getPatientLogin = getPatientLogin;
        this.getAdminPassword = getPatientPassword;
        this.getPatientAddress = getPatientAddress;

        adminChangePatientRecord.setInfoInFields(getPatientSecondName, getPatientName, getPatientFatherName,
                getPatientBirthDate, getPatientMedCard, getPatientSnilsCard, getPatientLogin, getPatientPassword, getPatientAddress);
    }

    public void switchToShedulePage() {
        stage.setScene(sheduleScene);
        adminSheduleController.refreshDataFromTable();
        stage.centerOnScreen();
    }

    public void switchToAddRowInShedule() {
        stage.setScene(addNewRowIntoSheduleScene);
        stage.centerOnScreen();
    }


}