package com.example.qualifiedwork;

import com.example.qualifiedwork.adminAccount.*;
import com.example.qualifiedwork.authenticaton.*;
import com.example.qualifiedwork.patientAccount.PatientInfoController;
import com.example.qualifiedwork.patientAccount.PatientMenuController;
import com.example.qualifiedwork.patientAccount.PatientProfileController;
import javafx.application.Application;
import javafx.fxml.FXML;
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
    private AdminCreateNewRecord adminCreateNewRecord;

    private Scene adminProfileScene;
    private Scene adminMenuScene;
    private Scene adminInfoScene;
    private Scene listOfAllAdminsScene;
    private Scene createNewAdminRecordScene;

    private String getSecondName;
    private String getName;

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

        stage.setScene(choiceViewScene);
        stage.show();

    }

    private Scene createNewAdminRecordScene() throws IOException {
        URL fxmLocation = getClass().getResource("/adminAccount/adminCreateNewRecord.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        createNewAdminRecordScene = new Scene(loader.load());
        adminCreateNewRecord = loader.getController();
        adminCreateNewRecord.setStartApp(this);

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
}