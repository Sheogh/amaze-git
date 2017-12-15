package model;

/**
 * Classe du personnage principal
 * Herite de la classe abstraite Character
 * @see Character
 *
 */

public class NiceGuy extends Character {
	
	/**
	 * Positionne le personnage au lancement
	 * du jeu, en fonction de la position de la porte de sortie
	 * @param labyrinthe du jeu
	 * @param exitPosition position de la porte de sortie
	 */
	@Override
	public void startPosition(Labyrinthe labyrinthe, Vertex exitPosition) {
		Vertex farthest = new Vertex(0, 0);
		Vertex v;
		int greaterDist = 0;
		Object[] tab = labyrinthe.getG().vertexSet();
		for (Object b : tab) {
			v = (Vertex) b;
			labyrinthe.launchManhattan(v, exitPosition);
			if (v.getNbr() > greaterDist) {
				greaterDist = v.getNbr();
				farthest = v;
			}
		}
		this.setPosition(farthest);
	}

}
