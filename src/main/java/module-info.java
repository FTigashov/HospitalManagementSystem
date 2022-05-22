module com.example.qualifiedwork {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.qualifiedwork to javafx.fxml;
    exports com.example.qualifiedwork;
}