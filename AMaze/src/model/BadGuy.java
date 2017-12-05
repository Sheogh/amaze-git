package model;

import model.Labyrinthe.direction;

/**
 * 
 * @see Character
 *
 */

public class BadGuy extends Character {
	
	static final int minDist = 16;
	
	/**
	 * 
	 * @param labyrinthe
	 */
	public void	move(Labyrinthe labyrinthe) {
		/*Vertex vertex = this.getRealPosition(labyrinthe.getG());
		for (direction dir : direction.values()) {
			Vertex next = labyrinthe.getG().getEqualVertex(labyrinthe.getG().vertexByDir(vertex, dir));
			if (labyrinthe.getG().containsEdge(vertex, next)
			&& (next.getNbr() == vertex.getNbr()-1) && next != null) {
				this.move(labyrinthe, dir);
				return;
			}
		}*/
		Vertex v = futureMove(labyrinthe);
		if (v != null) {
			setPosition(v);
		}
	}
	
	/**
	 * 
	 * @param labyrinthe
	 */
	public Vertex futureMove(Labyrinthe labyrinthe) {
		Vertex vertex = this.getRealPosition(labyrinthe.getG());
		for (direction dir : direction.values()) {
			Vertex next = labyrinthe.getG().getEqualVertex(labyrinthe.getG().vertexByDir(vertex, dir));
			if (labyrinthe.getG().containsEdge(vertex, next)
			&& (next.getNbr() == vertex.getNbr()-1) && next != null) {
				return labyrinthe.getG().getEqualVertex(labyrinthe.getG().vertexByDir(position, dir));
			}
		}
		return null;
	}

	/**
	 * 
	 * @param labyrinthe
	 * @param niceGuyPosition
	 */
	@Override
	public void startPosition(Labyrinthe labyrinthe, Vertex niceGuyPosition) {
		Vertex v;
		do {
			v = labyrinthe.getG().getEqualVertex(labyrinthe.getG().randomVertex());
			labyrinthe.launchManhattan(v, niceGuyPosition);
		} while (v.getNbr() < minDist);
		setPosition(v);
	}

}
