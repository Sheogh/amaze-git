package model;

/**
 * Classe representant les interrupteurs des portes, herite de la classe abstraite Item.
 * @see Item
 */

public class DoorSwitch extends Item {
	
	static final int minDist = 3;
	static final int maxDist = 5;
	
	/**
	 * Initialise la position de l'interrupteur au lancement du jeu, a distance raisonnable de la porte.
	 * @param labyrinthe du jeu
	 * @param doorEdge (position de la porte associee a l'interrupteur)
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
