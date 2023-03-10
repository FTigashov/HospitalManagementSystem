package com.example.qualifiedwork;

import com.example.qualifiedwork.adminAccount.controllers.*;
import com.example.qualifiedwork.adminAccount.functional.*;
import com.example.qualifiedwork.authenticaton.controllers.*;
import com.example.qualifiedwork.doctorAccount.controllers.*;
import com.example.qualifiedwork.patientAccount.controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

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

    // doctor
    private DoctorMenuController doctorMenuController;
    private DoctorProfileController doctorProfileController;
    private DoctorInfoController doctorInfoController;
    private DoctorListOfPatients doctorListOfPatients;
    private DoctorMakeProtocolController doctorMakeProtocolController;
    private DoctorSchedule doctorScheduleController;

    private ArchiveOfPatients archiveOfPatientsController;

    private ListOfDirections listOfDirectionsController;

    private MakeDirectionController makeDirectionController;

    private ShowDirectionController showDirectionController;


    private Scene doctorMenuScene;
    private Scene doctorProfileScene;
    private Scene doctorInfoScene;

    private Scene doctorListOfPatientsScene;
    private Scene doctorArchiveOfVisitsScene;
    private Scene doctorScheduleScene;

    private Scene doctorMakeProtocolScene;

    private Scene listOfDirectionsScene;

    private Scene makeDirectionScene;

    private Scene showDirectionScene;

    //patientAccount
    private PatientMenuController patientMenuController;
    private PatientInfoController patientInfoController;
    private PatientProfileController patientProfileController;

    private PatientVisitsController patientVisitsController;
    private PatientProtocolController patientProtocolController;

    private PatListOfDirectionsController patListOfDirectionsController;


    private Scene patientMenuScene;
    private Scene patientProfileScene;
    private Scene patientInfoScene;

    private Scene patientListOfProtocolsScene;
    private Scene patientProtocolScene;

    private Scene patListOfDirectionsScene;

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

    private AdminScheduleController adminScheduleController;
    private AddNewRowToSchedule addNewRowToSchedule;
    private ChangeRowInSchedule changeRowInSchedule;

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

    public String getDocSecondName, getDocName;

    private String getAdminSecondName, getAdminName, getAdminfatherName, getAdminBirthDate, getAdminEmplDate, getAdminResponsStatus, getAdminLogin, getAdminPassword;
    private String getPatientSecondName, getPatientName, getPatientFatherName, getPatientBirthDate, getPatientMedCard, getPatientSnilsCard, getPatientLogin, getPatientPassword, getPatientAddress;
    public String getPatSecondName, getPatName, getPatFatherName, getPatBirthDate, getPatMedCard, getPatSnilsCard;

    private String getFullName;
    private String getSpeciality;
    private String getCabNum;
    private String getMonday;
    private String getTuesday;
    private String getWednesday;
    private String getThursday;
    private String getFriday;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        stage.setTitle("Hospital management system");
        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.getIcons().add(new Image("D:/JavaProjects/QualifiedWork/src/main/resources/main_icon.png"));

        choiceViewScene = createChoiceViewScene();

        adminScene = createAdminScene();
        doctorScene = createDoctorScene();
        patientLoginScene = createPatientLoginScene();
        patientRegisterScene = createPatientRegisterScene();

        doctorMenuScene = createDoctorMenuScene();
        doctorListOfPatientsScene = createDoctorListOfPatientsScene();
        doctorArchiveOfVisitsScene = createDoctorArchiveOfVisitsScene();
        doctorScheduleScene = createDoctorScheduleScene();

        doctorProfileScene = createDoctorProfileScene();
        doctorInfoScene = createDoctorInfoScene();

        doctorMakeProtocolScene = createDoctorMakeProtocolScene();
        listOfDirectionsScene = createListOfDirectionsScene();
        makeDirectionScene = createMakeDirectionScene();

        patientMenuScene = createPatientMenuScene();
        patientProfileScene = createPatientProfileScene();
        patientInfoScene = createPatientInfoScene();

        patientListOfProtocolsScene = createPatientListOfVisits();
        patientProtocolScene = createPatientProtocolScene();

        patListOfDirectionsScene = createPatListOfDirectionsScene();

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
        changeTheRowInSheduleScene = createChangeScheduleRowScene();

        showDirectionScene = createShowDirectionScene();

        stage.setScene(choiceViewScene);
        stage.show();

    }

    private Scene createPatListOfDirectionsScene() throws IOException {
        URL fxmLocation = getClass().getResource("/patientAccount/patListOfDirections.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        patListOfDirectionsScene = new Scene(loader.load());
        patListOfDirectionsController = loader.getController();
        patListOfDirectionsController.setStartApp(this);

        return patListOfDirectionsScene;

    }

    private Scene createShowDirectionScene() throws IOException {
        URL fxmLocation = getClass().getResource("/doctorAccount/showDirection.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        showDirectionScene = new Scene(loader.load());
        showDirectionController = loader.getController();
        showDirectionController.setStartApp(this);

        return showDirectionScene;
    }

    private Scene createPatientProtocolScene() throws IOException {
        URL fxmLocation = getClass().getResource("/patientAccount/showProtocol.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        patientProtocolScene = new Scene(loader.load());
        patientProtocolController = loader.getController();
        patientProtocolController.setStartApp(this);

        return patientProtocolScene;
    }

    private Scene createPatientListOfVisits() throws IOException {
        URL fxmLocation = getClass().getResource("/patientAccount/patientProtocolVisits.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        patientListOfProtocolsScene = new Scene(loader.load());
        patientVisitsController = loader.getController();
        patientVisitsController.setStartApp(this);

        return patientListOfProtocolsScene;
    }

    private Scene createDoctorMakeProtocolScene() throws IOException {
        URL fxmLocation = getClass().getResource("/doctorAccount/makeProtocol.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        doctorMakeProtocolScene = new Scene(loader.load());
        doctorMakeProtocolController = loader.getController();
        doctorMakeProtocolController.setStartApp(this);

        return doctorMakeProtocolScene;
    }

    private Scene createDoctorInfoScene() throws IOException {
        URL fxmLocation = getClass().getResource("/doctorAccount/doctorInfo.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        doctorInfoScene = new Scene(loader.load());
        doctorInfoController = loader.getController();
        doctorInfoController.setStartApp(this);

        return doctorInfoScene;
    }

    private Scene createDoctorMenuScene() throws IOException {
        URL fxmLocation = getClass().getResource("/doctorAccount/doctorMainMenu.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        doctorMenuScene = new Scene(loader.load());
        doctorMenuController = loader.getController();
        doctorMenuController.setStartApp(this);

        return doctorMenuScene;
    }

    private Scene createDoctorListOfPatientsScene() throws IOException {
        URL fxmLocation = getClass().getResource("/doctorAccount/doctorListOfPatients.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        doctorListOfPatientsScene = new Scene(loader.load());
        doctorListOfPatients = loader.getController();
        doctorListOfPatients.setStartApp(this);

        return doctorListOfPatientsScene;
    }

    private Scene createDoctorProfileScene() throws IOException {
        URL fxmLocation = getClass().getResource("/doctorAccount/doctorProfile.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        doctorProfileScene = new Scene(loader.load());
        doctorProfileController = loader.getController();
        doctorProfileController.setStartApp(this);

        return doctorProfileScene;
    }

    private Scene createDoctorArchiveOfVisitsScene() throws IOException {
        URL fxmLocation = getClass().getResource("/doctorAccount/archiveOfPatients.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        doctorArchiveOfVisitsScene = new Scene(loader.load());
        archiveOfPatientsController = loader.getController();
        archiveOfPatientsController.setStartApp(this);

        return doctorArchiveOfVisitsScene;
    }

    private Scene createListOfDirectionsScene() throws IOException {
        URL fxmLocation = getClass().getResource("/doctorAccount/listOfDirections.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        listOfDirectionsScene = new Scene(loader.load());
        listOfDirectionsController = loader.getController();
        listOfDirectionsController.setStartApp(this);

        return listOfDirectionsScene;
    }

    private Scene createMakeDirectionScene() throws IOException {
        URL fxmLocation = getClass().getResource("/doctorAccount/makeDirection.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        makeDirectionScene = new Scene(loader.load());
        makeDirectionController = loader.getController();
        makeDirectionController.setStartApp(this);

        return makeDirectionScene;
    }

    private Scene createDoctorScheduleScene() throws IOException {
        URL fxmLocation = getClass().getResource("/doctorAccount/doctorSchedule.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        doctorScheduleScene = new Scene(loader.load());
        doctorScheduleController = loader.getController();
        doctorScheduleController.setStartApp(this);

        return doctorScheduleScene;
    }

    private Scene createChangeScheduleRowScene() throws IOException {
        URL fxmLocation = getClass().getResource("/adminAccount/changeRowInSchedule.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        changeTheRowInSheduleScene = new Scene(loader.load());
        changeRowInSchedule = loader.getController();
        changeRowInSchedule.setStartApp(this);

        return changeTheRowInSheduleScene;
    }

    private Scene createAddNewRowIntoShedule() throws IOException {
        URL fxmLocation = getClass().getResource("/adminAccount/addNewRowToShedule.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        addNewRowIntoSheduleScene = new Scene(loader.load());
        addNewRowToSchedule = loader.getController();
        addNewRowToSchedule.setStartApp(this);

        return addNewRowIntoSheduleScene;
    }

    private Scene createSheduleScene() throws IOException {
        URL fxmLocation = getClass().getResource("/adminAccount/schedule.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        sheduleScene = new Scene(loader.load());
        adminScheduleController = loader.getController();
        adminScheduleController.setStartApp(this);

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
        patListOfDirectionsController.patSecondName = getPatSecondName;
        patListOfDirectionsController.patName = getPatName;
        showDirectionController.typeOfAccount = "patient";
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

    //?????????? ?????????? ?????? ?????????????????? ???????????????????? ???? ????????????????
    public void getInfoAboutAccountFromController(String secondName, String name) {
        getSecondName = secondName;
        getName = name;
    }

    public void getInfoForShowProtocol(String doctorSecondName, String doctorName, String patientSecondName, String patientName) {
        getDocSecondName = doctorSecondName;
        getDocName = doctorName;
        getSecondName = patientSecondName;
        getName = patientName;
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

    public void getInfoAboutScheduleRow(String fullName, String speciality, String cabNum, String mn, String tue, String wed, String thurs, String fri) {
        getFullName = fullName;
        getSpeciality = speciality;
        getCabNum = cabNum;
        getMonday = mn;
        getTuesday = tue;
        getWednesday = wed;
        getThursday = thurs;
        getFriday = fri;

    }
    public void getInfoAboutAdminAccount(String secondNameField, String nameField) {
        getAdminSecondName = secondNameField;
        getAdminName = nameField;
//        System.out.println("Get data: " + getAdminSecondName + " " + getAdminName);
        try {
            adminChangeRecord.setDataInFields(getAdminSecondName, getAdminName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public void getInfoAboutPatientAccountWithoutAuth(String getPatientSecondName, String getPatientName, String getPatientFatherName, String getPatientBirthDate, String getPatientMedCard,
                                           String getPatientSnilsCard) {
        getPatSecondName = getPatientSecondName;
        getPatName = getPatientName;
        getPatFatherName = getPatientFatherName;
        getPatBirthDate = getPatientBirthDate;
        getPatMedCard = getPatientMedCard;
        getPatSnilsCard = getPatientSnilsCard;

        System.out.println(getPatientSecondName + " " + getPatientName);
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
        alert.setTitle("?????????????????????? ???? ????????????");
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



    public void switchToShedulePage() {
        stage.setScene(sheduleScene);
        adminScheduleController.refreshDataFromTable();
        stage.centerOnScreen();
    }

    public void switchToAddRowInShedule() {
        stage.setScene(addNewRowIntoSheduleScene);
        stage.centerOnScreen();
    }


    public void swichToChanScheduleRow() {
        stage.setScene(changeTheRowInSheduleScene);
        changeRowInSchedule.setInfoInFields(getFullName, getSpeciality, getCabNum, getMonday, getTuesday, getWednesday, getThursday, getFriday);
        stage.centerOnScreen();
    }

    public void switchToDoctorMainMenuScene() {
        makeDirectionController.docSecondName = getDocSecondName;
        makeDirectionController.docName = getDocName;
        listOfDirectionsController.docSecondName = getDocSecondName;
        listOfDirectionsController.docName = getDocName;
        showDirectionController.getDocSecondName = getDocSecondName;
        showDirectionController.getDocName = getDocName;
        showDirectionController.typeOfAccount = "doctor";
        stage.setScene(doctorMenuScene);
        stage.centerOnScreen();
    }

    public void switchToDoctorInfoScene() {
        stage.setScene(doctorInfoScene);
        stage.centerOnScreen();
    }

    public void switchToDoctorArchiveOfVisitsScene() {
//        System.out.println(getDocSecondName + " " + getDocName);

        archiveOfPatientsController.docSecondName = getDocSecondName;
        archiveOfPatientsController.docName = getDocName;
        archiveOfPatientsController.refreshTable();
        stage.setScene(doctorArchiveOfVisitsScene);
        stage.centerOnScreen();
    }

    public void switchToDoctorProfileScene() {
        stage.setScene(doctorProfileScene);
        doctorProfileController.setDoctorInfo(getAdminSecondName, getAdminName, getAdminfatherName, getAdminBirthDate, getAdminEmplDate, getAdminResponsStatus, getAdminLogin, getAdminPassword);
        stage.centerOnScreen();
    }

    public void switchToDoctorScheduleScene() {
        stage.setScene(doctorScheduleScene);
        stage.centerOnScreen();
    }

    public void switchToDoctorListOfPatients() {
        stage.setScene(doctorListOfPatientsScene);
        doctorListOfPatients.refreshDataFromTable();
        stage.centerOnScreen();
    }

    public void switchToMakeProtocolForm() {
        stage.setScene(doctorMakeProtocolScene);
        doctorMakeProtocolController.setInfo(getAdminResponsStatus, getAdminSecondName, getAdminName, getAdminfatherName, getPatSecondName, getPatName, getPatFatherName,
                getPatBirthDate, getPatMedCard, getPatSnilsCard);
        stage.centerOnScreen();
    }

    public void switchToPatientListOfVisits() {
        stage.setScene(patientListOfProtocolsScene);
        patientVisitsController.setInfoAboutPatient(getSecondName, getName);
        patientVisitsController.showTableOfVisits();
        stage.centerOnScreen();
    }

    public void switchToPatientProtocol() {
        stage.setScene(patientProtocolScene);
        patientProtocolController.setInfo(getDocSecondName, getDocName, getSecondName, getName);
        stage.centerOnScreen();
    }

    public void switchToListOfDirections() {
        listOfDirectionsController.refreshTable();
        stage.setScene(listOfDirectionsScene);
        stage.centerOnScreen();
    }

    public void switchToMakeDirectionScene() {
        makeDirectionController.patSecondName = getPatSecondName;
        makeDirectionController.patName = getPatName;
        makeDirectionController.setInfo();
        stage.setScene(makeDirectionScene);
        stage.centerOnScreen();
    }

    public void switchToShowDirectionScene(String date, String patSecondName, String patName) {
        System.out.println(date + " " + patSecondName + " " + patName);
        showDirectionController.getDateOfDirection = date;
        showDirectionController.getPatSecondName = patSecondName;
        showDirectionController.getPatName = patName;
        showDirectionController.setInfo();
        stage.setScene(showDirectionScene);
        stage.centerOnScreen();
    }

    public void switchToPatListOfDirections() {
        showDirectionController.getPatSecondName = getPatSecondName;
        showDirectionController.getPatName = getPatName;
        patListOfDirectionsController.refreshTable();
        stage.setScene(patListOfDirectionsScene);
        stage.centerOnScreen();
    }

    public void switchToShowPatDirectionScene(String date, String docSecondName, String docName) {
        showDirectionController.getDateOfDirection = date;
        showDirectionController.getDocSecondName = docSecondName;
        showDirectionController.getDocName = docName;
        showDirectionController.setInfo();
        stage.setScene(showDirectionScene);
        stage.centerOnScreen();
    }
}