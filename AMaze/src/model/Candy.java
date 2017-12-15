package model;

/**
 * Classe du personnage principal
 * Herite de la classe abstraite Character
 * @see Character
 *
 */

public class Candy extends Character {
	
	static final int minDist = 10;
	
	/**
	 * Positionne le bonbon au lancement
	 * du jeu, a un moins 10 cases du gentil
	 * @param labyrinthe du jeu
	 * @param exitPosition position de la porte de sortie
	 */
	@Override
	public void startPosition(Labyrinthe labyrinthe, Vertex niceGuyPosition) {
		Vertex v;
		do {
			v = labyrinthe.getG().getEqualVertex(labyrinthe.getG().randomVertex());
			labyrinthe.launchManhattan(v, niceGuyPosition);
		} while ((v.getNbr() < minDist) 
		&& (!v.equals(labyrinthe.getExit().getPosition()))
		&& (!v.equals(labyrinthe.getGuy().getPosition())));
		setPosition(v);
	}

}
