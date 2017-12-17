package model;

/**
 * Classe du personnage principal
 * Herite de la classe abstraite Character
 * @see Character
 *
 */

public class DoorSwitch extends Item {
	
	static final int minDist = 3;
	static final int maxDist = 5;
	
	/**
	 * Initialise la position de l'interrupteur au lancement
	 * du jeu
	 * @param labyrinthe du jeu
	 * @param niceGuyPosition de type Vertex correspond a la position du gentil
	 */
	@Override
	public void startPosition(Labyrinthe labyrinthe, Vertex doorEdge) {
		Vertex v;
		do {
			v = labyrinthe.getG().getEqualVertex(labyrinthe.getG().randomVertex());
			labyrinthe.launchManhattan(v, doorEdge);
		} while ((v.getNbr() > maxDist) || (v.getNbr() < minDist)
		&& (!v.equals(labyrinthe.getExit()))
		&& (!v.equals(labyrinthe.getGuy().getPosition())));
		setPosition(v);
	}

}
