/*
 * <VBox>
 * >Cavnas fx:id canvas onAction="handleClick">
 * </VBox> 
 */

import java.util.*;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;


public class ImageDisplayController extends Thread {
    private final int SQUARE_SIDE = 10;
    private final int CANVAS_SIDE = 400;
    private final int NAP = 1000;
    private final int X_POINT = 0;
    private final int Y_POINT = 0;
    private int n = 10; // num of squares
    private int hit = 0;
    private int miss = 0;
    private boolean gameOn;
    private double sqrData[] = new double[2]; // [x,y];
    private GraphicsContext gc;


    @FXML
    private Canvas canvas;

    @FXML
    private void handleClick(MouseEvent clk){
        if(gameOn && (clk.getX() >= sqrData[X_POINT] && clk.getX() <= sqrData[X_POINT]+SQUARE_SIDE) && 
        (clk.getY() >= sqrData[Y_POINT] && clk.getY() <= sqrData[Y_POINT]+SQUARE_SIDE)){
            hit++;
        }else{
            miss++;
        }
    }
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        this.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < n; i++) {
            gameOn = true;
            sqrData[0] = (double) Math.random() * (CANVAS_SIDE - SQUARE_SIDE);
            sqrData[1] = (double) Math.random() * (CANVAS_SIDE - SQUARE_SIDE);
            gc.fillRect(sqrData[0],sqrData[1],SQUARE_SIDE,SQUARE_SIDE);
            try {
            Thread.sleep(NAP);
            } catch(InterruptedException ex) {
                 Thread.currentThread().interrupt();
            }
            gameOn = false;
        gc.clearRect(0, 0, CANVAS_SIDE, CANVAS_SIDE);
        }
        endgame();
    }


    public void endgame(){
    Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Game over!\n You score is: " + ((hit*2)-miss));
        alert.show();
    });
}
    }

}
