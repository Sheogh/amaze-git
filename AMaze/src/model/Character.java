package model;

import model.Labyrinthe.direction;

/**
 * Classe abstraite des personnages
 * @see Item
 *
 */

public abstract class Character extends Item {
	
	/**
	 * Définis la position d'un personnage au
	 * lancement du jeu
	 * @param labyrinthe
	 * @param v
	 */
	abstract void startPosition(Labyrinthe labyrinthe, Vertex v);
	
	/**
	 * Permet de faire bouger les personnage
	 * @param labyrinthe
	 * @param dir
	 */
	public void move(Labyrinthe labyrinthe, direction dir) {
		Vertex vertex = this.getRealPosition(labyrinthe.getG());
		Vertex next = labyrinthe.getG().getEqualVertex(labyrinthe.getG().vertexByDir(vertex, dir));
		if (labyrinthe.getG().containsEdge(vertex, next) && next != null) {
			this.setPosition(next);
		}
	}

}
