package model;

import model.Labyrinthe.direction;

/**
 * Classe des m�chants, h�rite de la 
 * classe abstraite des personnages
 * @see Character
 *
 */

public class BadGuy extends Character {
	
	static final int minDist = 16;
	
	/**
	 * Fait bouger un m�chant
	 * @param labyrinthe
	 */
	public void	move(Labyrinthe labyrinthe) {
		Vertex v = futureMove(labyrinthe);
		if (v != null) {
			setPosition(v);
		}
	}
	
	/**
	 * Pr�dis le mouvement futur du m�chant. 
	 * Si d�j� occup�, le m�chant reste sur place
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
	 * Initialise la position du m�chant au lancement
	 * du jeu
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
