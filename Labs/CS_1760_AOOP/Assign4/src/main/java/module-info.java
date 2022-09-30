module com.daymon.github.assign4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.daymon.github.assign4 to javafx.fxml;
    exports com.daymon.github.assign4;
}