package model;

import model.Labyrinthe.direction;

/**
 * Classe qui définit un sommet
 * Contient des coordonnées x et y
 * ainsi qu'un numéro
 *
 */
public class Vertex {
	
	private int x;
	private int y;
	private int nbr;
	
	/**
	 * Constructeur par défaut
	 * Sans numéros
	 * @param x
	 * @param y
	 */
	public Vertex(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.nbr = 0;
	}
	
	/**
	 * Constructeur avec numéro
	 * @param x
	 * @param y
	 * @param nbr
	 */
	public Vertex(int x, int y, int nbr) {
		super();
		this.x = x;
		this.y = y;
		this.nbr = nbr;
	}
	
	/**
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * 
	 * @return nbr
	 */
	public int getNbr() {
		return nbr;
	}
	
	/**
	 * 
	 * @param nbr
	 */
	public void setNbr(int nbr) {
		this.nbr = nbr;
	}
	
	/**
	 * return String
	 */
	public String toString() {
		return "x="+this.x+" y="+this.y;
	}
	
	/**
	 * Verifie si on se trouve
	 * dans le labyrinthe ou non
	 * @return boolean
	 */
	public boolean inBounds() {
		if ((this.getX() < Labyrinthe.LEFT_BORDER) 
		|| (this.getX() > Labyrinthe.RIGHT_BORDER) 
		|| (this.getY() < Labyrinthe.TOP_BORDER) 
		|| (this.getY() > Labyrinthe.DOWN_BORDER)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Verifie si la direction en paramètre se trouve
	 * dans le labyrinthe ou non
	 * @param dir
	 * @return boolean
	 */
	public boolean inBorders(direction dir) {
		Vertex v = new Vertex(this.getX(),this.getY());
		switch(dir) {
		case North :
			v.setY(v.getY()-1);
			break;
		case South :
			v.setY(v.getY()+1);
			break;
		case East :
			v.setX(v.getX()+1);
			break;
		case West :
			v.setX(v.getX()-1);
			break;
		}
		return v.inBounds();
	}
	
	/**
	 * Regarde si deux sommets sont égaux
	 * @param obj
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		return ((x == ((Vertex) obj).x) && (y == ((Vertex) obj).y));
	}

}
