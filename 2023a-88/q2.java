<VBox>
    <Canvas fx:id="canvas">
</VBox>


import java.util.*;
public class ClickOnRectsController {
    public Canvas canvas;
    private LinkedList<Rectangle> rects;
    private final int STARTING_RECTS = 10;
    private static int errCounter = 0;
    private GraphicsContext gc;

    public void initialize(){
        gc = canvas.getGraphicsContext2D();
        rects = new LinkedList<>();
        for(int i = 0; i < STARTING_RECTS; i++){
            double size = Math.random()*90 +10;
            rects.add(new Rectangle(Math.random()*400, Math.random()*400, size, size));
        }
        drawRects();
    }

    public void drawRects(){
        gc.clearRect(0,0,400,400);
        for(Rectangle r: rects){
            gc.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
        }
    }

    public void pressed(MouseEvent clk){
        Rectangle rect = getSmallest();
        if(rect.contains(clk.getX(), clk.getY())){
            rects.remove(rect);
            drawRects();
        } else {
            errCounter++;
            Alert alert = new Alert(AlertType.ERROR, "Wrong click!");
            alert.showAndWait();
        }
        if(rects.isEmpty()){
            endGame();
        }
    }

    private void endGame(){
        Alert alert = new Alert(AlertType.INFORMATION, "You won with " + errCounter + " errors\n Confirm to start a new game.");
        alert.showAndWait();
        Platform.runLater(this::initialize);
    }

    private Rectangle getSmallest(){
        if(rects.isEmpty())
            return null;
        Rectangle smallest = rects.get(0);
        for(Rectangle r: rects){
            if(r.getWidth()*r.getHeight() < smallest.getWidth()*smallest.getHeight())
                smallest = r;
        }
        return smallest;
    }
}