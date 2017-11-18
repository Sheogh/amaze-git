package model;

public class Vertex {
	
	private int x;
	private int y;
	
	public Vertex(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public String toString() {
		return "x="+this.x+"y="+this.y;
	}
	
	public boolean compareTo(Vertex sommet) {
		
		return true;
	}

}
