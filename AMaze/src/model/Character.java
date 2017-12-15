package model;

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

}
