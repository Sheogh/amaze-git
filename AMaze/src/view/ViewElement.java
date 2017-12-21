package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Classe abstraite posant les constantes partagees par les vues et definissant des attributs partages.
 */
public abstract class ViewElement {
	
	static final int SPAN =  4 ; //  Pixels  for  a  unit
	static final int WALL =  2 ; //  thickness  of  the  walls  ( in  units )
	static final int CELL =  9 ; //  size of the cell	 (in units)
	static final Paint WALLCOLOR = Color.BURLYWOOD;
	static final Paint CLOSEDDOORCOLOR = Color.RED;
	static final Paint OPENEDDOORCOLOR = Color.LIMEGREEN;
	static final Paint SCENECOLOR = Color.WHITE;
	
	protected static Scene scene;
	protected static Pane pane;

}
