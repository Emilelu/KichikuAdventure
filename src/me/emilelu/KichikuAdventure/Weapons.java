package me.emilelu.KichikuAdventure;

public class Weapons extends Items {
	private String name;
	private int attack;
	private String introduction;

	public Weapons(String name, int attack, String introduction) {
		super();
		this.name = name;
		this.attack = attack;
		this.introduction = introduction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

}
