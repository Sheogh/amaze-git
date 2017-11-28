package model;

import model.Labyrinthe.direction;

/**
 * 
 * @see Character
 *
 */

public class BadGuy extends Character {
	
	/**
	 * 
	 * @param labyrinthe
	 */
	public void	move(Labyrinthe labyrinthe) {
		Vertex vertex = this.getRealPosition(labyrinthe.getG());
		for (direction dir : direction.values()) {
			Vertex next = labyrinthe.getG().vertexByDir(vertex, dir);
			if (labyrinthe.getG().containsEdge(vertex, next)
			&& (next.getNbr() == vertex.getNbr()-1) && next != null) {
				this.move(labyrinthe, dir);
			}
		}
	}

	/**
	 * 
	 */
	@Override
	void startPosition(Labyrinthe labyrinthe, Vertex niceGuyPosition) {
		// TODO Auto-generated method stub
		
	}

}
