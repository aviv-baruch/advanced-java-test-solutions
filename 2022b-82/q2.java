import javafx.*;

import java.io.File;
import java.net.URISyntaxException;
import java.util.*

public class ImageDisplayController {
    @FXML
    private ImageView imageView;

    @FXML
    private Text imageTitle;

    @FXML
    private Button next;

    @FXML
    private Button jump;

    private int curIndex = 0;
    private int totalImages;

    public void initialize() {
        try{
        File imageDir = new File(getClass().getResource(".").toURI());
        totalImages = (int) Arrays.stream(imageDir.listFiles())
                .filter(file -> file.getName().endsWith(".gif"))
                .count();
        }catch(Exception e){
              System.out.print(e);
              totalImages = 0;
        }
        next.setOnAction(event -> clickedNext());
        jump.setOnAction(event -> jumpToImage());
        loadAndDisplayImage(curIndex);
    }

    private void loadAndDisplayImage(int imageIndex) {
        if (imageIndex >= 0 && imageIndex < totalImages) {
            curIndex = imageIndex;
            Image image = new Image(getClass().getResourceAsStream("pic" + (imageIndex + 1) + ".gif"));
            imageView.setImage(image);
            imageTitle.setText("Image " + (imageIndex + 1));
        }
    }

    private void clickedNext() {
        curIndex++;
        if (curIndex == totalImages) {
            curIndex = 0;
        }
        loadAndDisplayImage(curIndex);
    }

    private void jumpToImage() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Jump to Image");
        dialog.setContentText("Enter image number (1-" + totalImages + "):");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(s -> {
            try {
                int imageNumber = Integer.parseInt(s);
                loadAndDisplayImage(imageNumber - 1);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid image number.");
                alert.showAndWait();
            }
        });
    }
}
