//https://github.com/Alex2492595/Lab07_B

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 *
 * @author Alexander Nikopoulos
 * Lab 07_B
 * 29/10/2025
 */
public class Lab07_B extends Application {
    private boolean status = false;
    private Button animationBtn;
    private SequentialTransition cycleImage;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(0, 0, 40, 0));
        
        HBox imageBox = new HBox(30);
        imageBox.setAlignment(Pos.CENTER);
        
        HBox buttonsBox = new HBox(30);
        buttonsBox.setAlignment(Pos.BOTTOM_CENTER);
        
        Label lblImage = new Label();
        
        animationBtn = new Button("Pause");
        Button speedUpBtn = new Button("Speed+");
        Button speedDownBtn = new Button("Speed-");
        
        Image[] imgs = new Image[20];
        
        for (int i = 101; i <= 120; i++) {
            String path = "file:images/" + i + ".jpg";
            Image img = new Image(path);
            imgs[i - 101] = img;
        }
        
        lblImage.setGraphic(new ImageView(imgs[0]));
        
        cycleImage = new SequentialTransition();
        
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
        
        animationBtn.setOnAction(e -> {
            changeAnimation();
        });
        
        speedUpBtn.setOnAction(e -> {
            cycleImage.setRate(cycleImage.getCurrentRate() * 1.25);
        });
        
        speedDownBtn.setOnAction(e -> {
            cycleImage.setRate(cycleImage.getCurrentRate() * 0.75);
        });
        
        imageBox.getChildren().addAll(lblImage);
        buttonsBox.getChildren().addAll(animationBtn, speedUpBtn, speedDownBtn);
        
        root.setBottom(buttonsBox);
        root.setCenter(imageBox);
        
        Scene scene = new Scene(root, 350, 450);
        
        primaryStage.setTitle("Java Games");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void changeAnimation() {
        if (status == false) {
            animationBtn.setText("Play");
            cycleImage.pause();
            status = true;
        } else {
            animationBtn.setText("Pause");
            cycleImage.play();
            status = false;
        }
    }
}
