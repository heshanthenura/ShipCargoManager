module com.heshanthenura.shipcargomanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;


    opens com.heshanthenura.shipcargomanager to javafx.fxml;
    exports com.heshanthenura.shipcargomanager;
    exports com.heshanthenura.shipcargomanager.Controllers;
    opens com.heshanthenura.shipcargomanager.Controllers to javafx.fxml;
}