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
	 * retourne la position du sommet
	 * @return position
	 */
	public Vertex getPosition() {
		return position;
	}
	
	/**
	 * Retourne la position dans le graphe
	 * du sommet en parametre
	 * @param graph
	 * @return position
	 */
	public Vertex getRealPosition(GraphNOriented graph) {
		return graph.getEqualVertex(position);
	}
	
	/**
	 * Modifie la position 
	 * @param position
	 */
	public void setPosition(Vertex position) {
		this.position = position;
	}

}
