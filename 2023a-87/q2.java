
//נגדיר gridpane ובאיתחול נוסיף את כלל הכפתורים

import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class NumberMatrixController {
    @FXML
    private GridPane gridPane;

    @FXML
    private Button finalButton;

    private Button[][] btnMatrix = new Button[5][5];
    private int x, y, z;
    private int pressedCounter;

    public void initialize() {
        int counter = 1; // start the counter from 1, not 0
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                btnMatrix[i][j] = new Button(Integer.toString(counter));
                final int value = counter; // 'counter' needs to be effectively final to use in lambda
                button.setOnAction(e -> {
                    if (pressedCounter == 0) {
                        x = value;
                    } else if (pressedCounter == 1) {
                        y = value;
                    } else {
                        z = value;
                    }
                    button.setText("");
                    button.setDisable(true);
                    pressedCounter++;
                });
                gridPane.add(btnMatrix[i][j], j, i); // Note: 'j' is the column index, 'i' is the row index
                counter++;
            }
        }

        finalButton.setOnAction(e -> {
            if (pressedCounter == 3 && x + y == z) {
                System.out.println("Correct! " + x + " + " + y + " equals " + z);
            } else {
                System.out.println("Incorrect. " + x + " + " + y + " does not equal " + z);
            }
            refreshGame();
        });
    }

    public void resetGame() {
        pressedCounter = 0;
        x = 0;
        y = 0;
        z = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                btnMatrix[i][j] = new Button(Integer.toString(counter));
                final int value = counter; // 'counter' needs to be effectively final to use in lambda
                button.setDisable(false);
            }
        }

    }

}