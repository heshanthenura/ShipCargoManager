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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class MainController implements Initializable {

    public static IntegerProperty slotNumber = new SimpleIntegerProperty(1);



    Logger logger = Logger.getLogger("info");

    @FXML
    private VBox addContainerForm;

    @FXML
    private Button addContainerFormCancelBtn;

    @FXML
    private DatePicker arrivedDatePicker;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private VBox containerVbox;

    @FXML
    private Text dataAdLbl;

    @FXML
    private Text dataIdLbl;

    @FXML
    private Text dataRDDLbl;

    @FXML
    private Text dataRDLbl;

    @FXML
    private Text dataSlotLbl;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button nextBtn;

    @FXML
    private Button previousBtn;

    @FXML
    private DatePicker releaseDueDatePicker;

    @FXML
    private DatePicker releasedDatePicker;

    @FXML
    private Text slotNumberLbl;

    @FXML
    private Spinner<Integer> slotNumberPicker;

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
        addContainerForm.setVisible(true);
        addContainerForm.setDisable(false);
    }

    @FXML
    void addContainerFormCancelBtnClick(MouseEvent event) {
        addContainerForm.setVisible(false);
        addContainerForm.setDisable(true);

    }

    @FXML
    void addContainerFormAddBtnClick(MouseEvent event) {
//        logger.info(String.valueOf(slotNumberPicker.getValue()));
//        logger.info(arrivedDatePicker.getValue().toString());
//        logger.info(releasedDatePicker.getValue().toString());
//        logger.info(releaseDueDatePicker.getValue().toString());
//        logger.info(colorPicker.getValue().toString());
        new DatabaseServices().addContainer(slotNumberPicker.getValue(),arrivedDatePicker.getValue(),releaseDueDatePicker.getValue(),null, String.valueOf(colorPicker.getValue()));
        addContainerForm.setVisible(false);
        addContainerForm.setDisable(true);
        new Services().addToContainerHolder(containerVbox, slotNumber.get());

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
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1); // Change the min, max, and initial value as needed
            slotNumberPicker.setValueFactory(valueFactory);
            new DataLables( ).initLables(dataIdLbl,dataSlotLbl,dataAdLbl,dataRDDLbl,dataRDLbl);
        });
    }

}
