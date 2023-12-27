package com.heshanthenura.shipcargomanager.Controllers;

import com.heshanthenura.shipcargomanager.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    static class SplashScreen extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 600, 400);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage =new Stage();
            scene.setFill(Color.TRANSPARENT);
            stage.setMaximized(true);
            stage.setTitle("CMan");
            stage.setScene(scene);
            stage.show();
        }
    }
}
