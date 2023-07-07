/*
 * <VBox>
 * <ImageView fx:id="image">
 * <TextField fx:id="text">
 * <HBox>
 * <Button text="check" onAction="handleCheck">
 * <Button text="stop" onAction="handleStop">
 * </HBox>
 * </VBox> 
 */

import java.util.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class ImageDisplayController {
    private int totalImages;
    private int guessedCorrectly;
    private Picture pic;

    @FXML
    private ImageView image;

    @FXML
    private TextField text;

    public void initialize() {
        totalImages = 0;
        guessedCorrectly = 0;
        newImage();
    }

    public void handleCheck() {
        if (text.getText().equals(pic.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Correct!");
            alert.show();
            guessedCorrectly++;
            newImage();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong!");
            alert.show();
        }
    }

    public void handleStop() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Game has been stopped!\nGussed Correctly " + (double) guessedCorrectly / totalImages * 100 + "%");
        alert.show();
    }

    private void newImage() {
        totalImages++;
        pic = new Picture();
        image.setImage(new Image(pic.getImage()));
    }
}
