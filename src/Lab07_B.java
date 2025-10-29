//https://github.com/Alex2492595/Lab07_B

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.util.Duration;

/**
 *
 * @author Alexander Nikopoulos
 * Lab 07_B
 * 29/10/2025
 */
public class Lab07_B extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        StackPane middle = new StackPane();
        Label lblImage = new Label();
        
        Image[] imgs = new Image[20];
        
        for (int i = 101; i <= 120; i++) {
            String path = "file:images/" + i + ".jpg";
            Image img = new Image(path);
            imgs[i - 101] = img;
        }
        
        SequentialTransition cycleImage = new SequentialTransition();
        
        for (int i = 0; i < imgs.length; i++) {
            int nextIdx = (i++) % imgs.length;
            
            FadeTransition nextImage = new FadeTransition(Duration.millis(2000), lblImage);
            
            nextImage.setOnFinished(e -> {
                lblImage.setGraphic(new ImageView(imgs[nextIdx]));
            });
            
            cycleImage.getChildren().addAll(nextImage);
        }
        
        cycleImage.setCycleCount(Animation.INDEFINITE);
        cycleImage.play();
        
        middle.getChildren().add(lblImage);
        
        root.setCenter(middle);
        
        Scene scene = new Scene(root, 250, 300);
        
        primaryStage.setTitle("Java Games");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
