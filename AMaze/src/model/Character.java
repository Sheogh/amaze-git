package model;

import model.Labyrinthe.direction;

/**
 * 
 * @see Item
 *
 */

public abstract class Character extends Item {
	
	abstract void startPosition(Labyrinthe labyrinthe, Vertex v);
	
	public void move(Labyrinthe labyrinthe, direction dir) {
		Vertex vertex = this.getPosition(labyrinthe.getG());
		Vertex next = labyrinthe.getG().getEqualVertex(labyrinthe.getG().vertexByDir(vertex, dir));
		if (labyrinthe.getG().containsEdge(vertex, next) && next != null) {
			this.setPosition(next);
		}
	}

}
