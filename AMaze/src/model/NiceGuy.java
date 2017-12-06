package model;

/**
 * Classe du personnage principal
 * Herite de la classe abstraite Character
 * @see Character
 *
 */

public class NiceGuy extends Character {
	
	/**
	 * Constructeur vide
	 */
	public NiceGuy() {
		setPosition(new Vertex(0, 0));
	}
	
	/**
	 * Positionne le personnage au lancement
	 * du jeu
	 * @param labyrinthe
	 * @param exitPosition
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
