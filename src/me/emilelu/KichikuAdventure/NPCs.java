package me.emilelu.KichikuAdventure;

public class NPCs extends Characters {
	private String name;
	private int atX;
	private int atY;
	private boolean isAlive;

	public NPCs(String name, int atX, int atY, boolean isAlive) {
		super();
		this.name = name;
		this.atX = atX;
		this.atY = atY;
		this.isAlive = isAlive;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAtX() {
		return atX;
	}

	public void setAtX(int atX) {
		this.atX = atX;
	}

	public int getAtY() {
		return atY;
	}

	public void setAtY(int atY) {
		this.atY = atY;
	}

}