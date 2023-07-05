
import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class NumberMatrixController {
    @FXML
    private GridPane gridPane;

    @FXML
    private Button finalButton;

    private final int SIZE = 5;
    private final int TOTAL_CARDS = 25;
    private final int[] SCORES = { 0, 100, 200, 300, 400 };

    private Button[][] btnMatrix = new Button[SIZE][SIZE];
    private int[][] valuesMatrix = new int[SIZE][SIZE];
    private int sum;
    private int pressedCounter;

    public void initialize() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                btnMatrix[i][j] = new Button(Integer.toString(""));
                valuesMatrix[i][j] = SCORES[(int) Math.random() * SIZE + 1];

                button.setOnAction(e -> {
                    handleButtonClick(i, j);
                });
                gridPane.add(btnMatrix[i][j], j, i); // Note: 'j' is the column index, 'i' is the row index
            }
        }

        finalButton.setOnAction(e -> {
            resetGame();
        });
    }

    public void handleButtonClick(int i, int j) {
        btnMatrix[i][j].setText(Integer.toString(valuesMatrix[i][j]));
        btnMatrix[i][j].setDisable(true);
        sum += valuesMatrix[i][j];
        pressedCounter++;

        if (pressedCounter == TOTAL_CARDS || valuesMatrix[i][j] == 0) {
            System.out.println("Game over, " + sum + " points have been collected");
            resetGame();
        }
    }

    public void resetGame() {
        pressedCounter = 0;
        sum = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                btnMatrix[i][j] = new Button(Integer.toString(""));
                valuesMatrix[i][j] = SCORES[(int) Math.random() * SIZE + 1];
                btnMatrix[i][j].setDisable(false);
            }
        }

    }

}