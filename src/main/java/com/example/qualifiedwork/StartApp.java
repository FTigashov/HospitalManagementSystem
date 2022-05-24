package com.example.qualifiedwork;

import com.example.qualifiedwork.authenticaton.*;
import com.example.qualifiedwork.patientAccount.PatientMenuController;
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
    private Scene patientMenuScene;

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

        stage.setScene(choiceViewScene);
        stage.show();

    }

    private Scene createPatientMenuScene() throws IOException {
        URL fxmLocation = getClass().getResource("/patientAccount/mainMenu.fxml");
        FXMLLoader loader = new FXMLLoader(fxmLocation);
        patientMenuScene = new Scene(loader.load());
        patientMenuController = loader.getController();
        patientMenuController.setStartApp(this);

        return patientMenuScene;
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
}