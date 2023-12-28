package com.heshanthenura.shipcargomanager.Components;

import com.heshanthenura.shipcargomanager.Controllers.MainController;
import com.heshanthenura.shipcargomanager.Database.DatabaseServices;
import com.heshanthenura.shipcargomanager.Services.DataLables;
import com.heshanthenura.shipcargomanager.Services.Services;
import javafx.application.Platform;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.time.LocalDate;
import java.util.logging.Logger;

public class Container {

    Logger logger = Logger.getLogger("info");

    private final Rectangle container = new Rectangle();

    private int id;
    private int slot;
    private LocalDate arrivedDate;
    private LocalDate releaseDueDate;
    private LocalDate releasedDate;
    private Color color;

    public Container(int id, int slot, LocalDate arrivedDate, LocalDate releaseDueDate, LocalDate releasedDate,Color color) {
        this.id = id;
        this.slot = slot;
        this.arrivedDate = arrivedDate;
        this.releaseDueDate = releaseDueDate;
        this.releasedDate = releasedDate;
        this.color = color;

        container.setOnMouseClicked(e -> {
            String releasedDateString = (releasedDate != null) ? releasedDate.toString() : "N/A";
            new DataLables().setLables(id, slot, arrivedDate.toString(), releaseDueDate.toString(), releasedDateString);
            if (e.getButton() == MouseButton.SECONDARY) {
                new DatabaseServices().deleteContainerById(id);
                new Services().addToContainerHolder((VBox) container.getParent(), slot);
            }
        });




    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public LocalDate getArrivedDate() {
        return arrivedDate;
    }

    public void setArrivedDate(LocalDate arrivedDate) {
        this.arrivedDate = arrivedDate;
    }

    public LocalDate getReleaseDueDate() {
        return releaseDueDate;
    }

    public void setReleaseDueDate(LocalDate releaseDueDate) {
        this.releaseDueDate = releaseDueDate;
    }

    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(LocalDate releasedDate) {
        this.releasedDate = releasedDate;
    }

    public Rectangle getContainer() {
        container.setWidth(400);
        container.setHeight(200);
        container.setFill(Color.GREEN);
        container.setStroke(Color.BLACK);
        container.setStrokeWidth(1); // Change the stroke width as needed
        container.setFill(color);
        return container;
    }

    @Override
    public String toString() {
        return "Container{" +
                "id=" + id +
                ", slot=" + slot +
                ", arrivedDate=" + arrivedDate +
                ", releaseDueDate=" + releaseDueDate +
                ", releasedDate=" + releasedDate +
                '}';
    }
}
