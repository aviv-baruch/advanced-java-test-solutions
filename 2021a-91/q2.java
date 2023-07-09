/*
 * <VBox>
 * <HBox fx:id="textButtonsBox">
 * </HBox>
 * <HBox>
 * <TextField fx:id="text">
 * <Button fx:id="endBtn" text="endBtn"  onAction="#handleClick">
 * </HBox>
 * </VBox>
 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;

public class Controller {
    private Button[] btnArr;
    private char[] charArr;
    private int score;
    private Dictionary dic;

    @FXML
    private HBox textButtonsBox;

    @FXML
    private TextField text;

    @FXML
    private Button endBtn;

    public void initialize() {
        dic = new Dictionary();
        btnArr = new Button[7];
        charArr = new char[7];
        for (int i = 0; i < 7; i++) {
            charArr[i] = dic.getRandomLetter();
            btnArr[i] = new Button(String.valueOf(charArr[i])); // You need to convert char to String to create a button
            final int finalI = i;
            btnArr[i].setOnAction(event -> clickedOnWord(charArr[finalI]));
            textButtonsBox.getChildren().add(btnArr[i]); // You need to add the button to the HBox
        }
    }

    public void clickedOnWord(char ch) {
        text.setText(text.getText() + ch);
    }

    public void handleClick() {
        checkAnswer(text.getText());
    }

    public void checkAnswer(String word) {
        if (isLegal(word)) {
            score += word.length();
            showMessage(true);
            restartWords();
        } else {
            score--;
            showMessage(false);
            restartWords();
        }
    }

    public void showMessage(boolean isCorrect) {
        Alert alert;
        if (isCorrect) {
            alert = new Alert(Alert.AlertType.INFORMATION, "Well done!\n Current score is: " + score);
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "Wrong! word doesn't exists.\n Current score is: " + score);
        }
        alert.showAndWait();
    }

    public void restartWords() {
        for (int i = 0; i < 7; i++) {
            charArr[i] = dic.getRandomLetter();
            btnArr[i].setText(String.valueOf(charArr[i])); // You need to convert char to String to set button text
        }
        text.setText("");
    }

}
