package com.heshanthenura.shipcargomanager.Services;

import com.heshanthenura.shipcargomanager.Components.Container;
import com.heshanthenura.shipcargomanager.Controllers.MainController;
import com.heshanthenura.shipcargomanager.Database.DatabaseServices;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class Services {
    public void addToContainerHolder(VBox vbox, int slotNumber){
        vbox.getChildren().clear();
        for (Container c : DatabaseServices.retrieveContainersBySlot(slotNumber)){
           vbox.getChildren().add(c.getContainer());
        }
    }


}
