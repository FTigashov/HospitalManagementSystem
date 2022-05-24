module com.example.qualifiedwork {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.qualifiedwork to javafx.fxml;
    opens com.example.qualifiedwork.authenticaton;
    opens com.example.qualifiedwork.patientAccount;
    exports com.example.qualifiedwork;
    exports com.example.qualifiedwork.patientAccount;
    exports com.example.qualifiedwork.authenticaton;
}