package model;

/**
 * Classe abstraite pour les differents items du labyrinthe.
 * @see Vertex
 */

public abstract class Item {
	
	Vertex position;
	
	/**
	 * Constructeur.
	 */
	public Item() {
		setPosition(new Vertex(0, 0));
	}
	
	/**
	 * Retourne le sommet correspondant a la position de l'element.
	 * @return position
	 */
	public Vertex getPosition() {
		return position;
	}
	
	/**
	 * Retourne le sommet du graphe equivalent a celui associe à l'element.
	 * @param graph du labyrinthe
	 * @return position
	 */
	public Vertex getRealPosition(GraphNOriented graph) {
		return graph.getEqualVertex(position);
	}
	
	/**
	 * Modifie la position de l'element.
	 * @param position
	 */
	public void setPosition(Vertex position) {
		this.position = position;
	}
	
	/**
	 * Definit la position d'un element au lancement du jeu.
	 * @param labyrinthe du jeu
	 * @param v sommet qui sera une position de reference
	 */
	abstract void startPosition(Labyrinthe labyrinthe, Vertex v);

}
