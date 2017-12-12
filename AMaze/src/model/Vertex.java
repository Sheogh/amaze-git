package model;

import model.Labyrinthe.direction;

/**
 * Classe qui definit un sommet
 * Contient des coordonnees x et y
 * ainsi qu'un numero
 *
 */
public class Vertex {
	
	private int x;
	private int y;
	private int nbr;
	
	/**
	 * Constructeur par defaut
	 * Sans numeros
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
	 * Constructeur avec numero
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
	 * Retourne la coordonnee x 
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Modifie la coordonnnee x
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Retourne la coordonnee y
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Modifie la coordonnnee y
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Retourne le numero nbr
	 * @return nbr
	 */
	public int getNbr() {
		return nbr;
	}
	
	/**
	 * Modifie le numero nbr
	 * @param nbr
	 */
	public void setNbr(int nbr) {
		this.nbr = nbr;
	}
	
	/**
	 * Affiche les coordonnees du sommet
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
	 * Verifie si la direction en parametre se trouve
	 * dans le labyrinthe ou non
	 * @param dir la direction
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
	 * Teste si deux sommets sont egaux
	 * @param obj
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		return ((x == ((Vertex) obj).x) && (y == ((Vertex) obj).y));
	}

}
