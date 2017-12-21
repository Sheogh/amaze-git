package view;

//import java.io.File;

import App.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Labyrinthe;
import model.Vertex;

/**
 * Classe qui affiche un item du jeu
 * @see ViewElement
 */
public class ViewItem extends ViewElement {
	
	private ImageView viewSprite;
	
	/**
	 * 	Constructeur.
	 */
	public ViewItem() {
		viewSprite = new ImageView();
	}
	
	/**
	 * @return viewSprite
	 */
	public ImageView getViewSprite() {
		return viewSprite;
	}
	
	/**
	 * Positionne un item dans une cellule du labyrinthe.
	 * @param x coordonnee x
	 * @param y coordonnee y
	 */
	public void setPosition(int x, int y) {
		viewSprite.setX(x*((WALL+CELL)*SPAN)+WALL*SPAN);
		viewSprite.setY(y*((WALL+CELL)*SPAN)+WALL*SPAN);
	}
	
	/**
	 * Positionne un item par rapport a un sommet.
	 * @param v sera le sommet en question
	 */
	public void setPosition(Vertex v) {
		setPosition(v.getX(), v.getY());
	}
	
	/**
	 * Affiche un item.
	 * @param stage
	 * @param fileName nom du fichier
	 */
	public void drawSprite(Stage stage, String fileName, int wh) {
		pane = ViewGame.getPane();
		String imagePath = Main.class.getResource("/"+fileName).toString();
		Image sprite = new Image(imagePath, wh, wh, false, false);
		viewSprite = new ImageView(sprite);
		pane.getChildren().add(viewSprite);
	}
	
	/**
	 * Change l'image affichee par l'item.
	 * @param fileName
	 * @param wh
	 */
	public void changeImage(String fileName, int wh) {
		String imagePath = Main.class.getResource("/"+fileName).toString();
		viewSprite.setImage(new Image(imagePath, wh, wh, false, false));
	}
	
	/**
	 * Cache la vue associee.
	 */
	public void hideImage() {
		viewSprite.setVisible(false);
	}
	
	/**
	 * Lance l'affichage de l'item.
	 * @param primaryStage
	 * @param laby labyrinthe du jeu
	 * @param fileName nom du fichier
	 */
	public void start(Stage primaryStage, Labyrinthe laby, String fileName, int wh) {
		drawSprite(primaryStage, fileName, wh);
	}
}
