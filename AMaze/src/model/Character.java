package model;

import model.Labyrinthe.direction;

/**
 * Classe abstraite des personnages
 * @see Item
 *
 */

public abstract class Character extends Item {
	
	/**
	 * Definis la position d'un personnage au
	 * lancement du jeu
	 * @param labyrinthe du jeu
	 * @param v sommet qui sera la position initiale
	 */
	abstract void startPosition(Labyrinthe labyrinthe, Vertex v);
	
	/**
	 * Permet de faire bouger le personnage
	 * @param labyrinthe du jeu
	 * @param dir de type direction
	 */
	public void move(Labyrinthe labyrinthe, direction dir) {
		Vertex vertex = this.getRealPosition(labyrinthe.getG());
		Vertex next = labyrinthe.getG().getEqualVertex(labyrinthe.getG().vertexByDir(vertex, dir));
		if (labyrinthe.getG().containsEdge(vertex, next) && (next != null)
		&& (labyrinthe.getG().getEdgeByDir(vertex, dir).getType() != Edge.Type.CLOSED_DOOR)) {
				this.setPosition(next);
		}
	}

}
