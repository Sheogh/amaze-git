package view;

import java.io.File;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Labyrinthe;
import model.Vertex;

/**
 * 
 * @see ViewElement
 *
 */
public class ViewItem extends ViewElement {
	
	private ImageView viewSprite;
	
	public ViewItem() {
		viewSprite = new ImageView();
	}
	
	public void setPosition(int x, int y) {
		viewSprite.setX(x*((WALL+CELL)*SPAN)+WALL*SPAN);
		viewSprite.setY(y*((WALL+CELL)*SPAN)+WALL*SPAN);
	}
	
	public void drawSprite(Stage stage, String fileName) {
		pane = ViewGame.getPane();
		String imagePath = new File("Assets/"+fileName).toURI().toString();
		Image sprite = new Image(imagePath, 36, 36, false, false);
		viewSprite = new ImageView(sprite);
		pane.getChildren().add(viewSprite);
	}
	
	public void start(Stage primaryStage, Labyrinthe laby, String fileName) {
		drawSprite(primaryStage, fileName);
	}
}
