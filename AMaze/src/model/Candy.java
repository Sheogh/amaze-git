package model;

/**
 * Classe representant le personnage principal, herite de la classe abstraite Item.
 * @see Item
 */

public class Candy extends Item {
	
	static final int minDist = 10;
	
	/**
	 * Positionne le bonbon au lancement du jeu, suffisamment loin du gentil
	 * @param labyrinthe du jeu
	 * @param niceGuyPosition (position du gentil)
	 */
	@Override
	public void startPosition(Labyrinthe labyrinthe, Vertex niceGuyPosition) {
		Vertex v;
		do {
			v = labyrinthe.getG().getEqualVertex(labyrinthe.getG().randomVertex());
			labyrinthe.launchManhattan(v, niceGuyPosition);
		} while ((v.getNbr() < minDist) 
		|| (v.equals(labyrinthe.getExit()))
		|| (v.equals(niceGuyPosition)));
		setPosition(v);
	}

}
