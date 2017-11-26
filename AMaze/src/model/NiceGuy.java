package model;

/**
 * 
 * @see Character
 *
 */

public class NiceGuy extends Character {

	/**
	 * 
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
