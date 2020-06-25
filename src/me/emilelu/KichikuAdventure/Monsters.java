package me.emilelu.KichikuAdventure;

public class Monsters extends Characters {
	private String name;
	private int maxHP;
	private int hp;
	private boolean isAlive;
	private int attack;
	private int defense;
	private int atX;
	private int atY;

	public Monsters(String name, int maxHP, int hp, boolean isAlive, int attack, int defense, int atX, int atY) {
		super();
		this.name = name;
		this.maxHP = maxHP;
		this.hp = hp;
		this.isAlive = isAlive;
		this.attack = attack;
		this.defense = defense;
		this.atX = atX;
		this.atY = atY;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

}
