package com.heshanthenura.shipcargomanager.Controllers;

import com.heshanthenura.shipcargomanager.Database.DatabaseServices;
import com.heshanthenura.shipcargomanager.Services.DataLables;
import com.heshanthenura.shipcargomanager.Services.Services;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public static IntegerProperty slotNumber = new SimpleIntegerProperty(1);

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button nextBtn;

    @FXML
    private Button previousBtn;

    @FXML
    private Text slotNumberLbl;

    @FXML
    private VBox containerVbox;

    @FXML
    public Text dataAdLbl;

    @FXML
    public Text dataIdLbl;

    @FXML
    public Text dataRDDLbl;

    @FXML
    public Text dataRDLbl;

    @FXML
    public Text dataSlotLbl;

    @FXML
    void nextBtnClick(MouseEvent event) {
        slotNumber.set(slotNumber.getValue() + 1);
        new Services().addToContainerHolder(containerVbox, slotNumber.get());

    }

    @FXML
    void previousBtnClick(MouseEvent event) {
        slotNumber.set(slotNumber.getValue() - 1);
        new Services().addToContainerHolder(containerVbox, slotNumber.get());

    }

    @FXML
    void addContainerBtnClick(MouseEvent event) {
        Rectangle container = new Rectangle();
        container.setWidth(400);
        container.setHeight(200);
        ObservableList<Node> children = containerVbox.getChildren();
        int indexToAdd = 0;
        children.add(indexToAdd, container);
    }

    @FXML
    void addSlotBtnClick(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        slotNumberLbl.textProperty().bind(slotNumber.asString());
        new DatabaseServices().initTables();
        new Services().addToContainerHolder(containerVbox, slotNumber.get());
        Platform.runLater(() -> {

            new DataLables( ).initLables(dataIdLbl,dataSlotLbl,dataAdLbl,dataRDDLbl,dataRDLbl);
        });
    }

}
