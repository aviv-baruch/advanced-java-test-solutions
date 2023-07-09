import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Controller {
    private static final int GRID_SIZE = 10;
    private Button[][] grid = new Button[GRID_SIZE][GRID_SIZE];

    @FXML
    private GridPane gridPane;

    @FXML
    private VBox vbox;

    @FXML
    private Button clearBtn;

    @FXML
    public void initialize() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Button btn = new Button();
                btn.setPrefSize(30, 30);
                btn.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

                int finalI = i;
                int finalJ = j;
                btn.setOnAction(e -> {
                    if (btn.getBackground().getFills().get(0).getFill().equals(Color.WHITE))
                        btn.setBackground(
                                new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                    else
                        btn.setBackground(
                                new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

                    grid[finalI][finalJ] = btn;
                });

                gridPane.add(btn, j, i);
                grid[i][j] = btn;
            }
        }

        clearBtn.setOnAction(e -> clearGrid());
    }

    private void clearGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j].setBackground(
                        new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
    }
}
