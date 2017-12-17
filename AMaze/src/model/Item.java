package model;

/**
 * Classe abstraite pour les differents
 * items du labyrinthe
 * @see Vertex
 *
 */

public abstract class Item {
	
	Vertex position;
	
	/**
	 * Constructeur par defaut
	 */
	public Item() {
		setPosition(new Vertex(0, 0));
	}
	
	/**
	 * retourne la position du sommet
	 * @return position
	 */
	public Vertex getPosition() {
		return position;
	}
	
	/**
	 * Retourne la position dans le graphe
	 * du sommet en parametre
	 * @param graph du labyrinthe
	 * @return position
	 */
	public Vertex getRealPosition(GraphNOriented graph) {
		return graph.getEqualVertex(position);
	}
	
	/**
	 * Modifie la position 
	 * @param position de l'element
	 */
	public void setPosition(Vertex position) {
		this.position = position;
	}
	
	/**
	 * Definis la position d'un element au
	 * lancement du jeu
	 * @param labyrinthe du jeu
	 * @param v sommet qui sera une position de reference
	 */
	abstract void startPosition(Labyrinthe labyrinthe, Vertex v);

}
