package model;

import model.Labyrinthe.direction;

/**
 * Classe representant les mechants, herite de la classe abstraite Item.
 * @see Item
 */

public class BadGuy extends Item {
	
	static final int minDist = 16;
	
	/**
	 * Calcul et effectue le mouvement du mechant.
	 * @param labyrinthe du jeu
	 */
	public void	move(Labyrinthe labyrinthe) {
		Vertex v = futureMove(labyrinthe);
		if (v != null) {
			setPosition(v);
		}
	}
	
	/**
	 * Predit le mouvement futur du mechant. 
	 * Si la case visee est deja "reservee", le mechant reste sur place.
	 * @param labyrinthe du jeu
	 * @return sommet d'arrivee du futur mouvement
	 */
	public Vertex futureMove(Labyrinthe labyrinthe) {
		Vertex vertex = this.getRealPosition(labyrinthe.getG());
		for (direction dir : direction.values()) {
			Vertex next = labyrinthe.getG().getEqualVertex(labyrinthe.getG().vertexByDir(vertex, dir));
			if (labyrinthe.getG().containsEdge(vertex, next)
			&& (next.getNbr() == vertex.getNbr()-1) && (next != null)
			&& (labyrinthe.getG().getEdgeByDir(vertex, dir).getType() == Edge.Type.CORRIDOR)) {
				return labyrinthe.getG().getEqualVertex(labyrinthe.getG().vertexByDir(position, dir));
			}
		}
		return null;
	}

	/**
	 * Initialise la position du mechant au lancement du jeu, suffisamment loin du gentil.
	 * @param labyrinthe du jeu
	 * @param niceGuyPosition de type Vertex correspond a la position du gentil
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
