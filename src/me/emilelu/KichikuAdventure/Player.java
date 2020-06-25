package me.emilelu.KichikuAdventure;

public class Player extends Characters {
	private String name;
	private String gender;
	private int maxHP;
	private int hp;
	private boolean isAlive;
	private int attack;
	private int defense;
	private String introduction;

	public Player(String name, String gender) {
		super();
		this.name = name;
		this.gender = gender;
		System.out.println("【系统】角色创建成功。你的名字是 " + name + "，性别是：" + gender);
		maxHP = 100;
		hp = maxHP;
		isAlive = true;
		attack = 10;
		defense = 10;
		System.out.println(
				"最大血量：" + maxHP + "，当前血量：" + hp + "，是否存活：" + isAlive + "，攻击力：" + attack + "，防御力：" + defense + "，背包：空");
		introduction = "个人简介：你叫 " + name + "，你是一个" + gender + "人。";
		System.out.println(introduction + "\n");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
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

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

}
