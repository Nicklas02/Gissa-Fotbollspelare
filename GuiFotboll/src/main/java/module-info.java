module com.example.guifotboll {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.guifotboll to javafx.fxml;
    exports com.example.guifotboll;
}