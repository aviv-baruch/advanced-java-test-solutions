/*
 * <VBox>
 * <GridPane fx:id="pane">
 * <Button fx:id="finishBtn" text="finish"  onAction="#gameOver">
 * </VBox>
 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;

public class Controller {
    @FXML
    private GridPane pane;

    @FXML
    private Button finishBtn;

    private final int SIZE = 5;
    private int x, y, z;

    private Button[][] btnMatrix = new Button[SIZE][SIZE];
    private int[][] valuesMatrix = new int[SIZE][SIZE];
    private int[][] clickedOnItems = new int[3][2];
    private int guessedCorrectly = 0;
    private int pressedCounter = 0;

    public void initialize() {
        x = y = z = pressedCounter = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                btnMatrix[i][j] = new Button(Integer.toString(i + j + 1));
                valuesMatrix[i][j] = i + j + 1;

                btnMatrix[i][j].setOnAction(e -> {
                    handleButtonClick(i, j);
                });
                pane.add(btnMatrix[i][j], j, i); // Note: 'j' is the column index, 'i' is the row index
            }
        }
    }

    public void handleButtonClick(int i, int j) {
        pressedCounter++;
        switch (pressedCounter) {
            case 1:
                x = valuesMatrix[i][j];
                clickedOnItems[0][0] = i;
                clickedOnItems[0][1] = j;
                break;
            case 2:
                y = valuesMatrix[i][j];
                clickedOnItems[1][0] = i;
                clickedOnItems[1][1] = j;
                break;
            case 3:
                z = valuesMatrix[i][j];
                clickedOnItems[2][0] = i;
                clickedOnItems[2][1] = j;
                break;
        }
        btnMatrix[i][j].setDisable(true);

        if (pressedCounter == 3) {
            if (x + y == z) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Correct!");
                for (int k = 0; k < 3; k++) {
                    btnMatrix[clickedOnItems[k][0]][clickedOnItems[k][1]].setDisable(false);
                    btnMatrix[clickedOnItems[k][0]][clickedOnItems[k][1]].setText("");
                }
                alert.show();
                guessedCorrectly++;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong!");
                for (int k = 0; k < 3; k++) {
                    btnMatrix[clickedOnItems[k][0]][clickedOnItems[k][1]].setDisable(true);
                }
                alert.show();
            }

            pressedCounter = 0;
        }
    }

    public void gameOver() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Game over!\n You were correct in: " + guessedCorrectly + " selections.");
        alert.showAndWait();
    }
}
