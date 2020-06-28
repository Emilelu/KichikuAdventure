package me.emilelu.KichikuAdventure;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class WeaponsU {
	String name;
	String description;
}

class gfsStick extends WeaponsU {
	static String name = "爷爷的拐杖";
	static String description = "可以击飞敌人 1~3 米，并造成 1~5 点伤害。";
}

class miHotel extends WeaponsU {
	static String name = "㊙Hotel";
	static String description = "一个外观酷似猕猴桃的武器？可以使方圆 ?? 米内产生爆炸，并对敌人造成 ?? 点伤害。";
}

abstract class PlayerU {
	static String name = "狗蛋";
	static int normalAttack = 2;
	static int hp = 10;
}

class evolvedPlayerU extends PlayerU implements GodMode {
	static String protagonistAura() {
		return "你拥有了主角光环！";
	}

	static int normalAttack = Boss.hp;
	static String hp = "???";
}

interface GodMode {
	static String protagonistAura() {
		return null;
	}
}

abstract class MonstersU {
	String name;
	int hp;
}

class Caixukun extends MonstersU {
	static String name = "蔡徐坤";
	static int hp = 5;
}

class DYGG extends MonstersU {
	static String name = "冬泳怪鸽";
	static int hp = 233;
}

class guolaoShi extends MonstersU {
	static String name = "迷人的郭老师";
	static int hp = 666;
}

class Boss extends MonstersU {
	static String name = "影 流 之 主";
	static int hp = 2147483647;
}

public class EasterEgg {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		List<String> weaponList = new ArrayList<String>();
		weaponList.add("赤手空拳");
		int hasWeapon = 1;
		System.err.println("This game is abandoned. But you was teleported to here by SOME UNKNOWN REASONS.");
		System.out.println("在遥远的鬼畜大陆，每一个鬼畜素材对象都在这里快乐的生活着。");
		System.out.println("但是有一天，谜之森林外出现了令人叹为观止的一幕——天空正在逐渐被黑暗所侵蚀着！");
		System.out.println("就在这时，有一名年轻勇敢的冒险家，他决定只身一人，拯救鬼畜大陆，他的名字叫——");
		System.out.print("请输入你的名字：");
		Scanner s = new Scanner(System.in);
		while (s.hasNext()) {
			String RIP = s.next();
			if (!RIP.equals("/kill--SetName")) { // 开 幕 雷 击
				System.out.println("他的名字叫 " + RIP + "，不幸的是，他刚迈出去谜之森林冒险的第一步时，就被天上掉下来的石头砸死了。");
				System.err.println("GAME OVER");
				System.exit(0);
			} else { // 主内容

				System.out.println("Module 'SetName' is Skipped.");
				System.out.println("[警告] 由于玩家名未定义，因此将使用缺省值：" + PlayerU.name);
				System.out.println(
						PlayerU.name + " 踏上了去谜之森林冒险的旅途，刚进到森林里，他就遇到了第一个怪物 " + Caixukun.name + "，它的血量是 " + Caixukun.hp);
				System.out.println("你当前的血量是 " + PlayerU.hp);
				gethasWeapon(weaponList, hasWeapon);
				Scanner s1 = new Scanner(System.in);
				String attackWay1 = s1.next();
				if (attackWay1.equals("1")) {
					int hurtCXK = (int) (Math.random() * (Caixukun.hp) - 2);
					System.out.println(PlayerU.name + " 用尽全力发起了攻击，对 " + Caixukun.name + " 造成了 "
							+ (hurtCXK + PlayerU.normalAttack) + " 点伤害！");
					System.out.println(PlayerU.name + " 被 " + Caixukun.name + " 秒杀了，造成了 " + PlayerU.hp + " 点伤害");
					System.err.println("GAME OVER");
					System.exit(0);
				} else if (attackWay1.equals("/kill--Caixukun")) {

					System.out.println(Caixukun.name + " 被 System 杀死了！");
					System.out.println(Caixukun.name + " 掉落了 " + gfsStick.name + "x1，描述：" + gfsStick.description);
					weaponList.add(gfsStick.name);
					System.out.println("你把它添加到了背包，继续往前走。");

					System.out.println("你在地上发现了一张满是灰尘的纸条，要打开吗？(y/n)");
					Scanner isOpen = new Scanner(System.in);
					String openNote = isOpen.next();
					if (openNote.equals("y")) {
						System.out.println("'勇敢的冒险者啊，若想抵达胜利，牢记以下秘籍：↓↓↑↑→←→←ABAB(Reversed)'");
						System.out.println("你把它刻进了DNA里，继续向前走，接下来该怎么走呢？(上w、下s、左a、右d)");
						Scanner go1 = new Scanner(System.in);
						String getGo1 = go1.next();
						if (getGo1.equals("w")) {
							System.out.println("你遇到了一个怪物！它是 " + DYGG.name + "，血量是 " + DYGG.hp);
							System.out.println("你当前的血量是 " + PlayerU.hp);
							gethasWeapon(weaponList, hasWeapon);
							Scanner attackToDYGG = new Scanner(System.in);
							String chosenofDYGG = attackToDYGG.next();
							if (chosenofDYGG.equals("2")) {
								System.out.println(DYGG.name + " 先发制人！对你造成了 3 点伤害！并说：'我们遇到什么困难也不要怕，微笑着面对它！'");
								PlayerU.hp = 7;
								System.out.println("你对 " + DYGG.name + " 造成了 " + PlayerU.normalAttack + " 点伤害！");
								System.out.println(DYGG.name + " 被击飞了！掉下悬崖前喊了一声 奥利给！！！然后摔死了！");
								System.out.println("本次战斗没有掉落任何物品，接下来该怎么走呢？");
								Scanner go2 = new Scanner(System.in);
								String getGo2 = go2.next();
								if (getGo2.equals("w")) {
									System.out.println("随着进一步深入，可见度越来越低，接下来该怎么走？");
									Scanner go3 = new Scanner(System.in);
									String getGo3 = go3.next();
									if (getGo3.equals("s")) {
										System.out.println("你感到情况越来越不对劲，接下来怎么走？");
										Scanner go4 = new Scanner(System.in);
										String getGo4 = go4.next();
										if (getGo4.equals("s")) {
											System.out
													.println("你遇到了一个怪物！它是 " + guolaoShi.name + "，血量是 " + guolaoShi.hp);
											System.out.println("你当前的血量是 " + PlayerU.hp);
											System.out.println("怪物对你说：'我现在就杀了你！啊，还没杀呢，还没呢~'");
											gethasWeapon(weaponList, hasWeapon);
											Scanner attackToguolaoShi = new Scanner(System.in);
											String chosenofguolaoShi = attackToguolaoShi.next();
											if (chosenofguolaoShi.equals("2")) {
												System.out.println("你拿着 " + gfsStick.name + " 向它挥去，击飞 3 米，造成 5 点伤害。");
												System.out.println("可是这并没有什么效果，它被激怒了，向你冲了过来——");
												System.out.println("难道我就要死在这了吗！！！！！！！！！！！！！");
												System.out.println("就在此时，它被一道闪电击中，随后灰飞烟灭。");
												System.out.println("？？？？？？？？？");
												System.out.println(guolaoShi.name + " 被 Lightning 杀死了！");
												System.out.println(guolaoShi.name + " 掉落了 " + miHotel.name + "x1，描述："
														+ miHotel.description);
												weaponList.add(miHotel.name);
												System.out.println("这令你感到十分不可思议，但你还是把它添加到了背包，继续往前走。");
												System.out.println("前面是一个个方向不同的石柱，下面是深不见底的悬崖，对面才是接下来的路！接下来该怎么走？");
												Scanner go5 = new Scanner(System.in);
												String getGo5 = go5.next();
												if (getGo5.equals("adad")) {
													System.out.println("你凭借着 250 的智商，成功抵达对岸。");
													System.out.println("你继续往前走了不知道多久，发现了一块凸起来的土堆。");
													System.out.println("好奇心促使你去刨开，你发现了一个宝箱，但是不知道怎么打开。");
													System.out.println("于是你读取了DNA，发现最后四个元素是——ABAB，你别无他法，只好决定对着天空大喊——");
													Scanner shout = new Scanner(System.in);
													String shoutWhat = shout.next();
													if (shoutWhat.equals("BABA")) {
														System.out.println("你对着天空大喊了一声——BABA！！！！！！！！！！！！");
														System.out.println("随后，天旋地转，你什么都看不见了。");
														System.out.println("过了一会，你发现，眼前有三个...人？");
														System.out.println("他们正在以奇怪的姿势扭动着，向你走...来？");
														System.out.println("突然，你的眼前闪了一片金光！脑海里闪过了一句话——"
																+ evolvedPlayerU.protagonistAura());
														System.out.println("你的直觉告诉你他们非常危险！你没有时间犹豫了！");
														System.out.println(
																"你遇到了一个(?)怪物！它(们)是 " + Boss.name + "，血量是 " + Boss.hp);
														System.out.println("你当前的血量是  " + evolvedPlayerU.hp);
														System.out.println(
																"我和你荡秋千~荡到那天外天~看着那牛郎织女相会在那银河边~我和你心相连~幻想在蔚蓝海边~坐着那小船游啊游啊游到岸的彼岸~");
//														gethasWeapon(weaponList, hasWeapon); REMOVE BY BOSS
														System.out.println("[System] weaponList has been cleared.");
														System.out.println("什么！我的背包竟然被清空了？？？");
														System.out.println("影流之主：哈哈哈哈哈哈哈哈哈哈哈哈哈，你以为你能打败我？？做梦！");
														System.out.println(
																"难道我就只能坐以待毙了吗？？？VXNlJTIwc29tZSUyMGluc3RydWN0aW9ucyUyMHRvJTIwc2F2ZSUyMHlvdS4=/fromgod");
														Scanner attackToBoss = new Scanner(System.in);
														String chosenofBoss = attackToBoss.next();
														if (chosenofBoss.equals("/give--@p--赤手空拳")) {
															System.out.println("你 " + weaponList.get(0) + "，对 "
																	+ Boss.name + " 造成了 " + Boss.hp + " 点伤害！");
															System.out.println("转眼间，世界恢复了光明！你凯旋而归，成为了鬼畜大陆永远传世的神话！");
															System.out.println("恭喜获得成就：赤手空拳打败BOSS的男人");
															System.out.println("游戏的内容到此结束，感谢您的游玩！");
														} else if (chosenofBoss.equals("/give--@p--爷爷的拐杖")) {
															System.out.println(
																	"你使用了 " + weaponList.get(1) + "，" + Boss.name
																			+ " 被打飞到∞光年外！并造成了 " + Boss.hp + " 点伤害！");
															System.out.println("转眼间，世界恢复了光明！你凯旋而归，成为了鬼畜大陆永远传世的神话！");
															System.out.println("恭喜获得成就：用一根爷爷祖传的的拐杖打败BOSS的男人");
															System.out.println("游戏的内容到此结束，感谢您的游玩！");
														} else if (chosenofBoss.equals("/give--@p--㊙Hotel")) {
															System.out.println("你使用了 " + weaponList.get(2) + "，"
																	+ Boss.name + " 爆炸了！并对它造成了 " + Boss.hp + " 点伤害！");
															System.out.println("转眼间，世界恢复了光明！你凯旋而归，成为了鬼畜大陆永远传世的神话！");
															System.out.println("恭喜获得成就：用㊙hotel打败BOSS的男人");
															System.out.println("游戏的内容到此结束，感谢您的游玩！");
														} else {
															System.out.println("你犹豫不决，被毁尸灭迹了。");
															System.err.println("GAME OVER");
															System.exit(0);
														}

														/* Horrible Atmosphere Start */
														System.out.println(
																"\n.............................................");

														Music.Stop();
														Music.Stop();

														try {
															Thread.currentThread();
															Thread.sleep(2000);
														} catch (InterruptedException e) {
															e.printStackTrace();
														}

														for (int i = 0; i < 50; i++) {
															System.out.println();
														}

														try {
															Thread.currentThread();
															Thread.sleep(500);
														} catch (InterruptedException e1) {
															e1.printStackTrace();
														}

														System.err.print("但是，");
														try {
															Thread.currentThread();
															Thread.sleep(1500);
														} catch (InterruptedException e1) {
															e1.printStackTrace();
														}
														System.err.print("你所在的游戏，");
														try {
															Thread.currentThread();
															Thread.sleep(1600);
														} catch (InterruptedException e1) {
															e1.printStackTrace();
														}
														System.err.print("真的是你一开始要玩的游戏");
														try {
															Thread.currentThread();
															Thread.sleep(2000);
														} catch (InterruptedException e1) {
															e1.printStackTrace();
														}
														System.err.print("...吗？\n");

														try {
															Thread.currentThread();
															Thread.sleep(2000);
														} catch (InterruptedException e) {
															e.printStackTrace();
														}

														System.out.println("好像没啥发生(*^_^*)");
														System.out.println("接下来该怎么走？");

														String end = s.next();
														System.out.println("[System] An exception has been occurred.");
														System.out.println("[System] Restoring default config...");
														System.out.println("[System] Detected input content: " + end
																+ " , continue...");
														System.out.println("你正打算前进的时候，被 %^%&62*^*&^@&* 杀死了。");
														System.err.println("E  N  D");
														System.exit(0);
														/* Horrible Atmosphere End */

													} else {
														System.out.println("你似乎是激怒了什么，你被碎尸万段。");
														System.err.println("GAME OVER");
														System.exit(0);
													}
												} else {
													System.out.println("石柱在你踩上去的一瞬间分崩离析，你坠入了深渊。");
													System.err.println("GAME OVER");
													System.exit(0);
												}
											} else {
												System.out.println(
														"随着 " + guolaoShi.name + " 的一声尖叫，惊天动地。你瞬间化为缕缕骨灰，向远方飘去。");
												System.err.println("GAME OVER");
												System.exit(0);
											}
										} else {
											System.out.println("FIRE EVERYWHERE!");
											System.err.println("GAME OVER");
											System.exit(0);
										}
									} else {
										System.out.println("过了不知道多久，鬼畜大陆的人们仍然没有见到那位勇敢的冒险家凯旋。");
										System.out.println("鬼畜大陆被黑暗完全吞噬了。");
										System.err.println("GAME OVER");
										System.exit(0);
									}
								} else {
									System.out.println("天空一声巨响，你的骨灰被扬。");
									System.err.println("GAME OVER");
									System.exit(0);
								}
							} else {
								System.out.println("你对 " + DYGG.name + " 造成了 " + PlayerU.normalAttack + " 点伤害！");
								System.out.println(DYGG.name + " 对你造成了 " + PlayerU.hp + " 点伤害！");
								System.err.println("GAME OVER");
								System.exit(0);
							}
						} else {
							System.out.println("你失足摔下悬崖，死。");
							System.err.println("GAME OVER");
							System.exit(0);
						}
					} else {
						System.out.println("你无视了它，继续前进，但转眼间，你的眼前血红一片，之后再也没有醒来。");
						System.err.println("GAME OVER");
						System.exit(0);
					}

				} else {
					System.out.println(PlayerU.name + "犹豫不决，被 " + Caixukun.name + " 秒杀了，享年 17 岁。");
					System.err.println("GAME OVER");
					System.exit(0);
				}

			}
		}

	}

	public static void gethasWeapon(List<String> weaponList, int hasWeapon) {
		System.out.print("请选择武器：");
		for (String ws : weaponList) {
			System.out.print(hasWeapon + "." + ws + " ");
			hasWeapon++;
		}
		System.out.println();
	}
}
