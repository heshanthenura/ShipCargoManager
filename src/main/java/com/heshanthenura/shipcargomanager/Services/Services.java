package com.heshanthenura.shipcargomanager.Services;

import com.heshanthenura.shipcargomanager.Components.Container;
import com.heshanthenura.shipcargomanager.Database.DatabaseServices;
import javafx.scene.layout.VBox;

import java.util.List;

public class Services {
    public void addToContainerHolder(VBox vbox, int slotNumber) {
        vbox.getChildren().clear();
        List<Container> containers = DatabaseServices.retrieveContainersBySlot(slotNumber);
        for (int i = containers.size() - 1; i >= 0; i--) {
            vbox.getChildren().add(containers.get(i).getContainer());
        }
    }
}
