package model;

/**
 * Definit la porte de sortie
 * H�rite de la classe abstraite Item
 * @see Item
 *
 */

public class Exit extends Item {
	
	/**
	 *  Constructeur vide
	 */
	public Exit() {
		setPosition(new Vertex(0, 0, 0));
	}

	/**
	 *  D�finis la position de la porte 
	 *  lancement du jeu. Par d�faut, elle 
	 *	se trouve sur le premier sommet du
	 *	chemin
	 */
	public void startPosition() {
		int n1 = (int) (Math.random() * Labyrinthe.RIGHT_BORDER);
		int n2 = (int) (Math.random() * Labyrinthe.DOWN_BORDER);
		Vertex v = new Vertex(n1, n2);
		setPosition(v);
	}

}
