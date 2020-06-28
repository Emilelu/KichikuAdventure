package me.emilelu.KichikuAdventure;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class playMusic extends Thread {
	private String[] args;

	@Override
	public void run() {
		Music.main(args);
	}
}

public class Game {
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		/* Configure Player Information Start */
		System.out.println("(｡･∀･)ﾉﾞ嗨！欢迎游玩《鬼畜大冒险》，在开始游戏前，我们需要初始化一些必要的东西。");
		System.out.print("冒险者，请输入你的名字：");
		Scanner s = new Scanner(System.in); // Create a Scanner for Initialization
		String inputName = s.next();
		System.out.print("冒险者，请输入你的性别（男/女）：");
		String inputGender = s.next();
		if (inputGender.equals("男") || inputGender.equals("女")) {
			// Nothing happened, continue the rest of things~
		} else {
			System.out.println("输入的性别有误，请重新再来。");
			System.exit(0);
		}
		Player you = new Player(inputName, inputGender); // Generate a player
		List<String> bag = new ArrayList<String>(); // Generate the bag of the player
		int hasCount = 1; // Set Default Counter
		/* Configure Player Information End */

		System.out.println(you.getName() + "，欢迎来到《鬼畜大冒险》！");
		System.out.println("游戏方法：输入 w(上) s(下) a(左) d(右) 在地图上移动。");
		System.out.println("小朋友你是否有很多？？走走或许就知道了呢(～￣▽￣)～");

		/* Configure Map Start */
		// Generate Default Player Point
		int x = 0;
		int y = 0;

		// Generate the NPCs on the map
		NPCs guideAngel1 = new NPCs("导航天使①", 1, 0, true);
		NPCs guideAngel2 = new NPCs("导航天使②", -1, 0, true);
		NPCs guideAngel3 = new NPCs("导航天使③", 0, 1, true);
		NPCs guideAngel4 = new NPCs("导航天使④", 0, -1, true);
		NPCs author = new NPCs("作者", -2, 4, true);

		// Generate the Monsters on the map
		Monsters caixukun = new Monsters("蔡徐坤", 20, 20, true, 5, 0, 4, 2);
		Monsters guolaoshi = new Monsters("郭老师", 60, 60, true, 8, 10, 3, -3);
		Monsters dygg = new Monsters("冬泳怪鸽", 233, 233, true, 12, 20, -4, -4);
		Monsters ylzz = new Monsters("影流之主", 2333, 2333, true, 2500, 233, -5, 5);

		// Generate Items
		Weapons dagger = new Weapons("匕首", 10, "一把普普通通的匕首，可以让攻击力提升 10！");
		Weapons basketball = new Weapons("篮球", 30, "看起来是一个普通的篮球，但是可以让攻击力提升 30！");
		Weapons miHotel = new Weapons("迷吼tiao", 100, "外观酷似猕猴桃的炸弹，可以多次使用，可以让攻击力提升 50！");
		Weapons bigSword = new Weapons("张牙舞爪大宝剑", 123, "外形像狼牙的大宝剑！可以让攻击力提升 123！");
		Weapons gfsStick = new Weapons("爷爷的拐杖", 188, "爷爷祖传下来的拐杖！可以击退敌人 ? 米，可以让攻击力提升 188！");
		/* Configure Map End */

		/* BGM Configure Start */
		System.out.println("\n[Music] Loading midi 'GodKnows.mid','black_rose.mid' from " + Music.f);
		playMusic play = new playMusic();
		play.start();
		System.out.println("[Music] Loaded 2 midis. Playing now.\n现在，你可以开始移动了。\n");
		/* BGM Configure End */

		/* Move Detection Start */
		Scanner move = new Scanner(System.in);
		while (move.hasNext()) {
			String moved = move.next();
			if (moved.equals("exit")) {
				System.out.println("[System] Received a force into exiting instruction.");
				System.err.println("Game exited without any saving.");
				System.exit(0);
			} else if (x <= 5 && x >= -5 && y <= 5 && y >= -5) {
				if (moved.equals("w")) {
					y++;
					printDialogue(x, y, you, guideAngel1, guideAngel2, guideAngel3, guideAngel4, author, bag, hasCount,
							caixukun, guolaoshi, dygg, ylzz, dagger, basketball, miHotel, bigSword, gfsStick);
					System.out.println("你现在的坐标是 " + "(" + x + "," + y + ")");
					System.out.println("接下来该怎么走？");
				} else if (moved.equals("s")) {
					y--;
					printDialogue(x, y, you, guideAngel1, guideAngel2, guideAngel3, guideAngel4, author, bag, hasCount,
							caixukun, guolaoshi, dygg, ylzz, dagger, basketball, miHotel, bigSword, gfsStick);
					System.out.println("你现在的坐标是 " + "(" + x + "," + y + ")");
					System.out.println("接下来该怎么走？");
				} else if (moved.equals("a")) {
					x--;
					printDialogue(x, y, you, guideAngel1, guideAngel2, guideAngel3, guideAngel4, author, bag, hasCount,
							caixukun, guolaoshi, dygg, ylzz, dagger, basketball, miHotel, bigSword, gfsStick);
					System.out.println("你现在的坐标是 " + "(" + x + "," + y + ")");
					System.out.println("接下来该怎么走？");
				} else if (moved.equals("d")) {
					x++;
					printDialogue(x, y, you, guideAngel1, guideAngel2, guideAngel3, guideAngel4, author, bag, hasCount,
							caixukun, guolaoshi, dygg, ylzz, dagger, basketball, miHotel, bigSword, gfsStick);
					System.out.println("你现在的坐标是 " + "(" + x + "," + y + ")");
					System.out.println("接下来该怎么走？");
				} else {
					System.out.println("输入的内容不合法！");
					System.out.println("因此你并没有移动。");
				}
			} else {
				if (y > 7) { // Teleport to EasterEgg Game
					for (int i = 0; i < 5; i++) {
						System.err.println("你掉入了时空裂缝！！！！！！！！");
					}
					EasterEgg.main(args);
				}
				if (y < -7) {
					System.out.println("你前行的时候，脚下突然一空——");
					System.out.println("你以光速往下掉落！");
					System.err.println("现场血肉模糊。**散落满地，引来了这个区域所生存的生物前来啃食。");
					System.err.println(you.getName() + " 掉下了悬崖，摔死了！！！！！！");
					System.exit(0);
				}
				if (x > 7) {
					System.err.println("你掉入了神秘法阵，传送到了地图的 (??,??)...无法获取...");
					x = -5;
					y = 3;
				}
				if (x < -7) {
					System.out.println("你毫无征兆的失去了意识，当你再次醒来的时候...");
					System.out.println(
							"@O@@@@@@@@@@@@@@@@@@OOOOO@@@@@@@@@@@@@@O@@O@@@@@@O@@@@@@@@@@@@@@@@@@@@O@@@@@@@O@@@@@@O@@@@@@@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
									+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
									+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOO@@OOOOOOOOOOOOOOOOOOOOOOOOOO@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooOOOOOOooOOOOOOOOOOOOOOOOOOOOOO@OOOOOOOOOOOOOOOOOO@@OOOOO@@@O@@@OOOOOOOO@OOOOOOOOOOOOOOOOOOOOOoo^=OOOOOOOOOOOOOOOOOOOOOOOO@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOO@OOOOOOOOOOOOOOOooOOooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooooooooOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoOOOoOOOOOOOOOOOOOOOOO@@@OOOoOOOOOOOOOOOOO@@@OOOO@@@@O@@@OOOOOooOOOOOOOOooOO@OoooOOOOOOO,*,OOOOOOOOOOOOOOOOOOOOOOOOO@@@@OOOOOOOOOOOOOOOO@OOOOOOOOoOOOOOOOOOOOOOOOooooooooOOOOOOOOOOOOOOooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooooooooOOOOOoOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOoooOOOoooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoOOoOoooOOOOOOOOOOOOOOOOOOOOOOOOoOOOOOOooOOoOO@@@OOOOO@@OOOOO@OOOooooOOOOOOOo*`OOOO^ooOOOOOOO^..\\OOOOOOOOOOOOOoOOoooOOOOOO@@@OOOOOOOOOOOOOOOO@OOOOO@OOoooO@OOOOOOOOOOOoooooooooOOOOOOOOOOOOOoOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooooooooOOOOOooooOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOoooOOooOOooooooOooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoooooOOOOOOOOOOOOOO@OOOOO@@OOOOoO@@OOOoOooOO@@@OOOO@@@@/\\OOOO@OOOoOOOOOOOOO^.=OOOO^=OOOOOOOOO`.=OOOOOOOOO[o*\\OoO@@OOOOOOO@@@@OOOOOOOOOOOOOOO@@OOOO@@O@OooO@OOOOOOOOOOOoooooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooOOOOooooooooooOOoooooOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOoOOOoOoOooooooooooOOOoOOOoooOOooOOOOOOOOOOOOOOOOOOOOOOOooOOOOOOOOOOoOO@OOO@OOOO@@OOOOOOO@OOOOoOoOO@@@@OOOO@@@O..OOOO@OOOOOOOOOOOOOO^oOOOO\\=OOOOOOoOOOoOOOOOOOOOOO\\o/OOOOOO@@OOOOO@@@OOOOOOOOOOOOOOO@@OOOOO@OO@OooOOOOOOOOOOOOoooooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooOOooooOOOOOooooooooOooO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOoOOOoOoooooooooooOoooooOoooooooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@OO@@OOOO@OOOO@OO@OOOOOOOO@@@@@@OOO@@@O. .\\OOOO@OOOOOOOOOOOOOOoOOOOOOOOOOOO.=OOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@OOOOOOOOOOOOOOO@@OOOOO@OOO@OooO@OOOOOOOOOOooooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoooooOOoOOOooooOoOoOoooo\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOoooOOoooooooooooooooooooooooooooo/oOOOOOOOOOOOOOOOOOOOOOooOOOOOOOOOO@@@OO@@OOOO@OOOOO@@@@OOOOOOO@O@@@@OOO@@@/.   ,OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO..=OOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@OOOOOOOOOOOOOOO@@OOOO@OOOO@OooO@OOOOOOOOOoooooooOOoOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooOOOOoooooO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOooooOOOooooooooooooooooooooooooooooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@OOOO@@OOOOOOOOOO@@O@OOOOOO@OOO@@@OO@@@/.     =OOOOOOOOOOOOOOOOOOOoOOOOOOOOOO^   =OOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@OOOOOOOOOOOOOOO@@OOOOO@OOOOOOo,OOOOOOOOOOOooooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoOOOOOOooOooO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOooOoooooooooooooooooooooooooooooooooOOOOOOOOOOOOOOOOOOOOOOOOOOOO/OOOOOOO@@@OOO@OOO@OO@OOOOOOOOOOOOOO@OO@@@/.       =OOOOOOOOoOOOOOOOOOOOOOOOOOOOO^    =OOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@OOOOOOOOOOOOOOO@@@OOOO@OOOOOOo^=OOOOOOOOOOooooooOOOOoOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooOOOOOOOOooO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOoooOooooooooooooooooooooooooooooooooOOOOOOOOOOoOOoOOOOOOOOOOOOOo/oOOOOOO@@OOOOOOOO@OO@OOOOOOOOOOOOO@@O@@@O.         ,OOOOOOoooOOooOOOoOOOOOOOOOOO^     ,OOOOOOO^ [OOOOOOOOOOOOOOOOO@@@OOOOOOOOOOOOOOO@@@OOOO@@OOOO@OO`\\OOOOOOOOOoooOoooooooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOoooooooooooooooooooooooooooooooooooOOOOOOOOOOoOOOOOOOOOOOOOOO`ooOOOOOO@@OOOOOOOOO@OO@OOOOOOOOOOOO@@@@@OO.            OOOOOooooOOoooooooOOOOOOOOO\\OO].   \\OOOOOO`   ,OOOOOOOOOOOOOO@@@OOOOOOOOOOOOOOO@@@OOOO@@OOOOO@OO=OOOOOOOOOooooooooooooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOoooooooooooooooooOOOOOooooooooooOOOOOOOOOOOOOoooOOOOOOOOOOOOoooOOOOOOO@@OOOOOOOOO@OO@OOOOOOOOOOOO@@@@O/.              \\ooOOOOoOOoooooooooOOOOOOO  ,\\OOO\\oOOOOOOO`     .[OOOOOOOOOO@@@OOOOOOOOOOOOOOOOO@OOOOO@OOOOO@@O`OOOOOOOOOooooooooOoooooOOoOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOoooooooooOooOOooOOOOOOoooo*\\ooo]//oOoOOOOOOOOOOOOOOOOOOOOOO/oooOOOOOOO@OOOOOOOOOO@O@@@@OOOOOOOOOO@@OOO.                =`/OOOOooooooooooooOOOOOO         .OOOOOOOO\\o\\]`. . ,[OOO@@@/OOOOOOOOOOOOOOOOOOOOOOOO@OOOOOO@O^\\OOOOOOOOoooooooOOOOOOOOOOoOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOooooooooOoooooooooooooOooooo=ooo\\`\\ooOOOOOOOOOOOOOOooooooOOOO=ooOOOOOOOO@O@OOOOOOOO@OO@OOOOOOOOOOOOOOOO.               .*]/O/OOOoooooooooooooOOOO^           =OOOOOO\\     .[[o]`..,OO.*OooOOOO/OOOOOOOOO@@OOOO@@OOOOO@@\\=OOOOOOOOoooOOooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOoooooOoooOOooooooooooOOoooOOooo]ooOOOOOOOOOOOOOOOooooooOOOoooOOOOOOOO@@@@OOOOOOO@OO@@OOOOOOOOOOOOOO.         ..*]]oo[`     =OoooOoooooooooOOOO^             \\OOOOoo         ..`..\\^.Ooooooo.  .oOOoOO@@OOOO@@OOOOO@@o=OOOOOOOOoo/oooOooooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOoooOOoooooOOooooooooooooooo`oooOOOOOOOOOOOOOOOoooooooOOO/\\ooOOOOOOO@@@OO@OOOOOO@OO@OOOOOOOOOOOOOO^   ........              =OoooOoooooooooOOO^               ,\\OOOO         .  ..OOOOOOoooo...=ooOO@OOOOOO@@OOOOO@@^=OOOOOOOO^o\\`=^/OoooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOoOOOOoooooooooooooooo/\\o`[ooo^=ooOOOOOOOOOOOOOOOooooOOOO^*=oOOOOOOO@@@OO@@@OOOO@OO@@OOOOOOOOOOOO/`                          ,OOOOOoooOOoOOOOO^                    [\\             .,OOOOOO@OO]oOOO@OO@@OOOO@@OOOOO@@^=OOOOOOoo^*,*=^\\ooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooooooooooooooooo\\,\\oo`=OOoOOOoOOOOOOooooooooOOO^*/oOOOooOO@@OO@@@@OOOOOOOO@OOOOOOOOOOOO                              \\OOOOOOOOOOOOOO^                   ].              .=OOOOOOOOOOOOOOOOO@@OOOO@@OOOOO@O^=OOOOOooo*=`*=o*ooo[ooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoooooooooooooOOOooOOOOoOoOOoOOOOOooooooooooOOOOooOOOOooOO@@OO@@O@OOOOOOOO@OOOOOOOOOOO^                               ,OOOOOOOOOOOOO^               .*]O@@@@@O\\]`       .=OOOOOOOOOOOOOOOOO@@OOOO@@OOOO@@O\\oOOOOOoo^*=o`*/**\\o],/OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOoOOoOOOOOOOOOOOoOoooooooOooooOOOOOOOOOOOOOOOOoOOOoOOoooooooOOOOOOOOO@@OOOOO@@OO@@OOOOOOOOOOOOOOOOOOOOOO.                                 ,OOOOOOOOOOOO.      .=O\\/OOO@@@@@@@@@@@@@@OO]   .OOOOOOOOOOOOOOOOOO@@OOOO@@OOOO@@OoOOOOOooo^*,o/*\\`*/oo/oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@\r\n"
									+ "OOOOOOOOOOOOOOOOooOoOoOOOOOOoooooooooooooooooOOOOOOOOOOOOoOOooOooooooooooooOOOOOOOOO@@OOOOO@@O@@@OOOOOOOOOOOOOOOOOOOOO^       .O\\`.....    .               \\OOOOOOOOOO^    ..,oO@@@@@@@@@/[O@@@@@@@OOOOO],O@OOOOOOOOOOOOOOO@@@@@OO@@OOOO@@OOOOOOOOOo^*ooo*/`ooooooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@OO@@\r\n"
									+ "OOOOOOOOOOOOOOOooOooOoOOoooooooooooooooooooooooooOOOOOOoOOOOOOOooOooooooOOOOOOOOOOOO@@OooOOO@@O@@OOOOOOOOOOOOOOOOOOOOO^  ,OOOOO@@@@@@@@@@@O\\......           .\\OOOOOOOO^  ,OOOOO@@@OO\\OO\\...,OOOOO@@O@OOOOOOOOOOOOOOOOOOOOO@@@@@@O@@OOOO@@OoOOOOOOOo^ooOo`o*\\oo^*oooOOooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@OO@@\r\n"
									+ "OOOOOOOOOOOOOOoooOooOooOooooooooOOoooooooOoooooooooooOOOOOOOOoOoooooooooOOOOOOOOOOO@@@OooOOOOOO@@@@OOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@OOOOO@@@O`               ,OOOOOOO` ./OOo/.  .,OOO.\\@OO`,OOOO..`=O//OOOOOOOOOOOOOOOOO@@@@@@O@@OOO@@OoOOOOOOOOoooO@@Oo`*ooo\\,OOO@OO@@OOOOOOOOOOOOOOOOOOO@@O@OOOOOOOOOOOO@@OOOOOOOOOOO@@OOO@@\r\n"
									+ "OOOOOOOOOOOOOOOOoOooooooooooooooOOOOoooooooooOoooooo/\\OOOOOOOOOooooOOooooooooOOOOOOO@@OOOOOO@O@@@@@OOOOOOOOOOOOoOOOOOOOOOOOO@OOOO[\\OOOOoooo^  ,O\\..               ,OO@@@\\ */.=.    .`.[@^         ` ..o/\\oOOOOOOOOOOOOOOOOO@@@@@@O@@OOO@OOoOOOOOOoooOO@OOOOoo/ooo/oOO@@OO@OOOOOOOOO@OOO@@OOO@@OO@@OOOO@OOOOO@@@@@OOOOOOOO@@@OOO@@\r\n"
									+ "OOOOOOOOOOOOooooooooooooooooooooooOOOOoo^*,/ooooooo``*=OOoOOOoOOOoooooOOoOOOOOOOOOOOO@@OoOOOOO@@@@@OOOOOOOOOOOOOOOOOOOOOOO/[.,,o. ..=O@OOOOO   ./\\                  ,\\@@O^    =^....,.           .  /`^*.,OOOOOOOOOOOOOOOOO@@@@@@@@@OOO@OoOOOOOOOOOO@@OOO@oOOOoOOOOO@O@OOO@OOOOOOOO@@O@@@OOO@@@@@@@OO@@@OOOO@@@OOOOOOOOOOO@@@OO@@\r\n"
									+ "OOOOOOOOOOOOOOOooooooooooooooOOOOOOOoooOoooooooooOO@OOOOoOOoooOooOOoooOOOOOooOOOOOOOO@@@OoOOOO@@O@@OOOOOOOOOOOOOOOOooo/[[.   ,OOO^=Oo. \\`  [   ..*                     ,O^....*\\o`*.....      .. .*/[`. .=OOOOOOOOOOOOOOOOO@@@@@@@@@@O@OOOOOOOOOOOOO@@@OOOO@@OOO@@@O@OO@@OOOOOOOOOO@@@@O@@O@O@@@@@@@@O@@@OOO@@@OOOOOOOOOO@@@@OO@@\r\n"
									+ "OOOOOOooOOOOOOooooooOOooOoooooOooooooooooOOOOOOOoOO@OOOoooOOOooooOoooooOOOOOOOOooOOooO@@@OOOOO@@OO@OOOOOOOOOoOOOO,^ ,\\O.     ..[O\\.            ....                       ......                   .     =OOOOOOOOOOOOOOOOO@@@OO@@@@@OOOOOOOOOOOOOOO@@@OOOOO@@@@@OOOOOOOOOOOOOOOOOOOOOOOO@@@OO@@OO@@OOOOOOOO@@OOOOOOOOOOO@@@OOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOoOOOooOOOOooooooooooooOOoOOOOOOO@OOOOOOOOOOOOOOOOOooOooOOOOOOOOOOOoO@@@OOOO@@OO@OOOOOOOOOOoOoO^=.  .\\`                     ,..                                                        =OOOOOOOOOOOOOOOOO@@@@O@@@@OOoOOOOOOOOOOOoO@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOoOOOOOOOOOOOOOOOOOOOOooOO@OOOOOOOOOOOOOOOOOOOOoooooooOOOoooooO@@@OOOO@OO@@OOOOOOooOooooOo^        ,\\^                                                                          .OOOOOOOOOOOOOOOOO@@@@@@@@@OOOOOOOOOOOOOOoOO@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOoOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@OOOOOOO@OOOOOOOOOOOOOoooooooooooooooOO@@OOO@OOO@OOOOOOooooooooOO         ,^                                                                          .OOO@OOOOOOOOOOOO@@@@@@@@@@OOOOOOOOOOOOOooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@OOOooOO@@OOOOOO@@@@OOoOoOooooooooOooooOOOOOOOOOO@@OOOOOo,ooo^=oOO^         =                                                                  ...,`   .OOOOOOOOOOOOOOOO@@@@OOO@@@OOOOOOOOOOOOooOooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOO@OOOOOO@@OOOOOOOOOOOOOOOOOOOOO@@OOOOOOOOoOoO@O@OOooooOO@@@@OOOO@@@@OOOOoOOOOOooooOoooooOOOOOOOOOOOOOOOO`*[o,*,oOo.                                                                             ,\\.   .OOO@OOOOOOOOOOOO@@@@OOOO@@OOOOOOOOOOOOoooOoooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "oOOO@OoOOO@@OOoOOOOOO@OOOOO@OOOO@OO@@OOOOOO@OOO@OO@OOOOOOOO@@OOOOOO@@@@OoOOoOOOOOOoooooooOooOOOOOOOOOOOOOOO\\*******oO^                                                                            .. ..  .=OOOOOOOOOOOOOOO@@@@OOOOO@OOOOOOOOOOOOooooo/[..         ,[\\OOOOOOOOOOOOOOOOOOOOO@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OoOOO@OO@OO@OOO\\,\\OOO@@OO@@@@@@@@O@O@@OOO@OOO@@OOOOOOOOOOOOO@OOOOOOOOOOOoOOoOOOOOOooOOoooOooOOOOOOOOOOOOOOOo****/**=Oo.       ,.                                                                 .   =o. .=OOOOOOOOOOOOOOO@@@@OOOOO@OOOOOOOOOOOOo\\`      ..............,\\OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "@OOOOOOO@@O@@OOOo/`,\\O@OO@@@@@O@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoOOoOOOOOOOOOOoooOoooOOOOOOOOOOOOOoO\\*******oO^       =^                                                                    =o`. .=OOOOOOOOOOOOOOO@@@@OOOOOOOOoOOOOOoooo.     ..     ....             .[oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^\r\n"
									+ "OOOOOOoOO@@@@OooooooooO@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoOOOOOOOOOOOoOOOOOOOOOOOooooooOOOOOOOOOOOOOooO^*..***,Oo       ,`                                                                   *O.   ./OOOOOOOOOOOOOOO@@O@OOoOOO@OoOOooOoo/     .  .           .........     \\OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO//\r\n"
									+ "OOOOOOOoOOOOooooOoooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoOOOoOOOOOOOOOOoooOO@OOOOOOOOOOoooo*...*=*\\O`                                                                          ...   ./OOOOOOOOOOOOOOOO@@OOOOo\\OOOO^\\ooooo^    ..                       ...... .,\\OOOOOOOOOOOOOOOOOOOOOOOooOOOOOOOOOOOOOOOOO..\r\n"
									+ "OOOOOOOoOoOOOOooooOOoOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooOOOOOOOOOOooooooooO@OOOOOOOOOOOooo\\*...*`=O^                                                                         .     .OO@@OOOOOOOOOOOOOO@OOOOO^*\\OOO^,=o=o`.                                   .... \\OOOOOOOOOOOOOO@@OOOOO`OOOOO@@@@@@@OOOO//OO\r\n"
									+ "OOOOOOOoOoOOooooooOOooOoOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoOOOoOOOOOOOOOoooO@OOOOoOOOoOOOOOOooOoooO@OOOOOOOOOOOOooo\\*..*o*OO                                                                              .oOO@OOOOOOOOOooOOOO@@OOOo^**oOO^*****.                                      *.   \\OOOOOOOOOO@@OOOOOOOOOO@@@@@OO@@@OOO\\OO@@\r\n"
									+ "OOOOOOooooOooOOooOOOOooooOOOOOOOOOOOOOOOOOOOOo\\oOOOOOOOOOOOoOOOOOOOOooOO@OOoOooOooOOOOOOooooooOOOOOOOOOOOoOOoooo\\***=\\oO^                                                    ..                      .oOOO@OOOOOOOOOooOOO@@@OOOo^..,OO\\.***   .                                     *    ,OOOOOOO@@OOOOOOO@@@O.=O@@OOO@@@@O@@@OO@\r\n"
									+ "OOOOOOoooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoOOOOOOOOOO@@@OOoOoOOooOOOOooooooooOOOOOOOOOOO^oOOooo^\\*,oooOO^                                                                          ./OOOO@OOOOoOOOOOoOOOO@@OOOo^...=Oo...               ,O@@@`                      .     OOOOO@@OOOOOOO@@@@OO.O@@@@@OO@@@@O@@OO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@OOOOOOOOOooOOOOOO@@@@@OOoooOOoooOooOoooooooOOOOOOOOOOOo=oOOooo^\\]oooOOO\\.                                                                       ./OOOOOOOOOOooOOOOooOOO@@OOOo^....=^..                 [@@@@@`                    .      OOOO@@OOOOOOO@@@OOOOOO@OOOOOO@OOOOOOO\r\n"
									+ "OOOOOOOoOOOOOO@@OOOOO@@@OOOOOOOOOOOOOOOOOOOOO@@OOO@OO@@@OOOOOOoOOO@@OO@@@@OoooOOooOOOoOoooooooOOOOOOOOOOOO`oOOOooo`oooOOOOOO`                                     . ... .=                        .oOOOOOOOOOOOooOOOOOOOOO@@@OOo^.....`...            .    ,O@OO@\\                    .     .OOOOO@@@@@@@@@OOOOOOOOOOoOOO//OOOOOO\r\n"
									+ "O@@@@@OOoooOO@OO@OOO@@@@OOOOOO@OO@OOOOOOOOO@@OOOO@@@@@O@@OOOO@OOOO@@O@@O@@OooooOoooOOOOooooooOOOOOOOOOOOOO^,oOOOoooooOOOOOOOO^                           .//o\\....**.....o                       *oOOOOOOOOOOOOoooOOOOOOOO@@@OO^`.  .....            .``   .\\OoOO/.                   .      =OOOOOOOOOOOOOOOOo\\/\\O/,oOO//OOOOOOO\r\n"
									+ "@@OOOOO@OOOO@@O@@OOO@@OOO@@@@@OOOOOOOOOOOOOOOO@@OOOOOOOOOoooOOOOOO@@@OOO@@OooooOoooOOOoooooooOOOOOOOOOOOOO\\*\\ooOOooooOOoOOOOOO\\.                          ,OO^.. ...  ...O.                    .,OOOOOOOOOOOOOOooooOOOOOOO@@@OOo^                    .`,.  ]O@@O@^                     .     .OOOOOOOOOOOOOOOOOOOO``,OOOOOOOOOOOO\r\n"
									+ "O@OOOOO@OOO@@@@@OOOO@@OOO@O@@OOOOOOOOOOOOOOOOOOOO`.*]]],ooooooOOoOOOooOOOOOOOooOooOOOoooo\\o/oOOOOOOOOOOOOOO*=ooOOooo`\\ooOOOOOOOO`                          ,o.          .[                    *oOOOOOOOOOOOOOOOooooOOOOOOOO@@@OO.                   ./ooooOO@@@@^..                    .     .oOOOOOOOOOOOOOOOOOOo*.=OOOOOOOOOOOO\r\n"
									+ "OO@@@@O@OOO@@OOOOOOOO@@@@OOOOOOOOOOOOOOOOOOOOOOOooo*]`,[OOooOoOOOooOoooooOOOoooOoooOooooo\\o*=OOOOOOOOOOOOOO^,ooooOoooooooOOOOOOOOo.                                     .                   .=OOOOOOOOOOOOOOOOOOooooOOOOOOO@@@OO`                   /OOOOO@@@@/.                       .     .oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "ooOOO@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoOOOoooOooooooOoO\\,\\OooooOoo[[oooo`=o*=OOOOOOOOOOOOOOo*o*/oOOoooooOOOOOOOOOOOo`                                                     ./OOOOOOOOOOOOOOOOOOOOooooOOOOOOO@@@OOO`                 .O@@@@@@O[.              .                .oOOOOOOOOOOOOOOOOOO[=OOOOOOOOOOOOOO\r\n"
									+ "ooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooOOOOOOOoooOOOOoo[oo[oooOo^/**ooo^=/./OOOOOOOOOOOOOOo`o^\\=oOOooooOOOOOOOOOOOOOO\\.                          .   .                 .=oOOOOOOOOOOOOOOOOOOOOOOooOoOOOOOOO@@@OOo\\.               .O@@@@@/                   .              .oOOOOOOOOOOOOOOOO/,``./\\OOOOOOO@OOO\r\n"
									+ "ooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooOOOOoO^,oo\\ooOo/o\\**,o^,`,OOOOOooOOOOOOOOO^=^,*=oOOooOOOOOOOOOOOOOOOOOO].                    .  .  .                .*oOOOOOOOOOOOOOOOOOOOOOOOOooOOOOOOOOO@@@@OO`,..             *O@@@@^.                     .       .    =ooOOOOOOOOOOOOOO\\O*  ,\\OOOOOOO@@OOO\r\n"
									+ "ooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoOOOOOOOOOOOOOOO@OOooooOoOoOo..**.\\Oo/,o*.*/o=oOOOOOO*oO@OO@@OOO\\/o*`*oOOOoOOOOOOOOOOOOOOOOOOOO\\.                                      ....*OOOOOOOOOOOOOOOOOOOOOOOOOOooOOOOOOOOO@@@OOO.....           .O@@[`                         .         .o\\ooOOOOOOOOOOOOOOO..,/=OOOOOOO@@@OO\r\n"
									+ "oooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@OOoOOOOOO@OOo]*,oo\\,[*`*..oooOOOOOO`*oOOOO@@OOOooo***=OOOOOOOOOOOOOOOOOOOOOOOOOO^...                               ....  .=OOOOOOOOOOOOOOOOOOOOOOOOOOooOOOOOO@OoO@@@OO\\.......          .                              .      .=/o*ooOOOOOOOOOOOoo.. .,\\ooOOOOO@@@OO\r\n"
									+ "OOooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@OoOOOoO@@@Oooooo\\`=`*.   .,.=OOOOO^.*OOOO@@@@OOo/o**.=oOoOOOOOOOOOOOOOOOOOOOOOOOo.   ..                         ....     .=OOOOOOOOOOOOOOOOOOOO@@OOOOOooOOOO@@OoO@@@OOO^..../^..                                         .    .,/oooOOOOOOO`.\\OOoo`^`*,\\OOOOOOOO@@@@\r\n"
									+ "oOOooOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@OOOOO@OOOOOOOOOOOOOOOO@OO@OOOO@OO@@OooooooOooOo],`**]]/OOOOo*.=O@OO@@@@OOooo`*.=oOOOOOOOOOOOOOOOOOOOOOOOO`..       ..                   ...        .,OOOOOOOOOOOOOOOOO@@@@@OOOOOOOOOOO@@OoOO@@@OOO`...oo^.                                          ..  .***[[[oOOo^*[[.*[,/OOOoooOOOOOOOOOOOO\r\n"
									+ "OOOoooOoOOOOOOOOOOOOOOOO@@OOO@@O@@@OOOOO@OOOOOOOOOOOOOOO@OOO@@OOOOO@@Oooooooooooooo[*`=ooO@O/=`..=OOOO@@@@@Oooo^^.=OOoOOOOOOOOOOOOOOOOOOO^. ...            ..           ...           .  ..=OOOOOOOOOO@@@@@@@@OOOOOOOOOOO@@OOOO@@@@OOO..*ooo*^....                                      .. ..***... ,]*[^  ..,OOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOooOOoOOOOOOOOOOOOOOOO@@OOOO@@@@@@OOOO@@OOOOOOO@@OOOOO@OOO@OooOooO@@OOoooooooooo\\oo^=oOO@O.*`.,OOOO@@@@Ooo*.`.*.*OOOOOOOOOOOOOOOOOOOOO*......               .......                  ......\\OOOOOO@@@@@@@@@@@OOOOOOOOOO@@OOOO@@@@@OO\\..oOo^=o..                                         .........,**.... .]]\\OOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOO@@OOOO@@OO@@OOOOOO@@@@OOOO@OOOO@@@@O/o*=oooOO@@@OoooOooOOo/ooooOOO/..../O@OOOOO@@@Oo/^...*oOOOOOOOOOOOOOOOOOOo*.. ....                                       ..*......,OOOOOOO@@@@@@@@OOOOOOOOOO@@OOOO@@@@@@OO`.oOO^.OO... .                                        ........***.....\\oO`\\OOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOO@@OOOOOOOOO@@OOOOOO@O@@OOO@@OOOOOOoo***`**/,oo**[oo*oooOOOoo\\ooOOOO.**,O@OOOOOO@@@O,***`^=ooooOOooOOOOOOOOOO/...  .*...                                     ..**........\\O@@@OO@@@@@@@OOOOOOOOOO@@OOOOO@@@@@@OO*oOO/.,OO.....                                         ...............\\OO^\\OOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@OO@@OOOOOOOOOOOoo\\]`o*****]*\\OOo/o^,O/ooo[[\\oOOOoo=/OOOOOOOO@@Oo/ooo\\^=OOOOOOoOOOOOOoOOOo....   .*..                                   ................O@@OOOOO@OO@OOOOOOOOOO@@OOOOO@@@@@@OO^=OO^..oO^......                                         .............,Ooo*,OOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@OOOOOOOOOOOOoooo^**,**\\/****[//*/**o^*,*..,O@OooooOOOOOOOOOOOOOOoooo^oOOOOOOoOOOOOOOO/=`...    .....                                 ...    ........**=O@OOOOOOOOOOOOOOOOOOO@@OOOOOO@@@@@OOO]OOO`.=OO`......                                         ........ ....=Oo* =OOOOOOOOOOOO\r\n"
									+ "OOOOOOOOoOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooOooo]o`//o/**,****o^*o/*****=O@OooooOOOOOOOOOOOoooooo^^oOOOOOooOOOOOOO`*...           .                              ....      ........o*\\@OOOOOOOOOOoOOOOOOOOO@OOOOOOO@@@@@OOOOOO\\.*OOO*....                                          ..... ..  ...]o/. ,oOOOOOOOOOOO\r\n"
									+ "OOOOOOOooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoOOOOOoOOoooooo[***[*****o`*/**,*..*\\OOooooOOOOOOOOOO^[oOooo=^oOOOOOOOOOOOOO\\`..             ..                           ...        .........**=O@OOOOOOO@OoOOOOOOOOO@@OOOOOOO@@@@@OOOOOO`.=OO^..**..                                      ..  ......    .o/.* .oOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOoOoOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooo^*****oooooo**o*..*..*=OOoooOOOOOOOOOOOO*\\ooo^/^oOOOOOooOOOOO^...                *.                        ...       ....... ....*.O@OOOOOOO@@oooOOOOOOOO@OOOOOoOOOOOOOOOOOOO^,oOO`**. ...     ..                            ...... ...      .[.  .=OOoOOOOOOOO\r\n"
									+ "OOOOOOOOooOoooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoooooo^**********\\`***..**..,oooOOOOOOO^.**\\Oo=oooo*ooOOOOOOooOOOOO`                    *.                     ...        ...... . ......=O@OOOOOOO@Oo^,oOOOOOO@OOOOOoOOO\\,OOOOOOoOOOo=OO*...  ..  ... ....                    ......... .         ./^  .oOoOOOOOOOOO\r\n"
									+ "OOOOOOOOoOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooooo/[***********=o[**..***/^,OOOOOO/......*oO^=/^o\\=\\oOOOOOOoOOOOOO^                     ,.                 ....      .........   .......=O@OOOOOOO@Ooo**\\/=oOO@@OOOOooOOO^=OOOOOOooOO`=/   .   ...... .  ..    ..                 ..  ...   ...   .\\*\\^,O*oOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoOOo\\ooo^************,********.*****/O\\OOOoo...*...=OO**=oOo^ooOOOOOooOOOOOO^.                     ..              ...      .........     ...  ...=O@OOOOOOO@@Oo^*]]/oOOO@OOOO/ooOOO*OOOOOOOOOO/ .. .. ...............                       .. ......***...,\\,`..**/OOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooooo*********,\\]***********\\***/o`*....,OO\\......=O^oooOOoooOOOOOOoOOOOOOO^.                      .^.          ....     ........             ...=O@OOOOOOOO@@OoooOOOOOO@OOOO^ooOO@O=OOoo[`....,[o\\]`  ...............                       ........... ..,.,/.,ooooOooOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoooo\\***********oo/`**`*,[[*oo/`*..........=OO.....oOoooOOOoOOOOOOOOOOOOOOOOO.                       .\\.        ....   ........                ...=O@OOOOOOOO@@@OOOOOOOOOO@OOOo=ooO/\\`\\O/OO`..........[O\\.......**....., .                   ..  ...      ....``=\\/OOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooOooo^***********=o^**,`*,o[................*oOO`..=OOooOOOOOOOOOOOOOOOOOOOOOO^.                    ...,\\..     ..*. .......                    ...=@@OOOOOOOOO@@OOOOOOOOOO@OOOo^OoOOO/,/OOO`.......... .,OO\\]........*..,\\                    .         .....  .*oooOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoOOoooo\\***********,`*.,o/..    ..............=OOO..oOooOOOOOOOOOOOOOOOOOOOOOOO^.                    .=`..o.    ..*. ..**.                      ....o@@OOOOOOOOO@@OOOOOOOOOOO@OOo/oo[\\\\OOOO/].......  ..  ..==OO`......=^..=o`                         .  .....]oo/o/OOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooOOOOOooOooooo^*,]*********oo`..      ..............,OOO^,OOooOOOOOOOOOOOOOOOOOOOOOOOO.                  .***,\\..\\.. ..*\\..******.                   ....*O@@OOOOOOOOOO@@OOOOOOOOOO@@O...,]]`OOOoO^.......  ..  ...,OOO^******\\..^.,\\.                     .....*...=/ooooooOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoOOoOOOOooo]ooooooo******,oo*.            ............\\OOOoOOoOOOOOOOOOOOOOOOOOOOOOOOOO.                .,o*...,o..*....=o`*,`=\\***..                 ....=O@OOOOOOOOOOOO@OOOOOOOOO@OO\\`*o/OOoOOoOOo^......  ... ....=OOo=o`***=^.*.  ,                    ....***...*o^.,\\`OOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooOoooOoOOOOoooooooooooooOO`.                 .........=oOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@O.               .=OO^...**\\*`....=o*,oo*\\OO\\*...               .***O@@OOOOOoooOOOO@OO@OOO@OO@OOO^ooooOO*OOOOOo`......  ... ...=OOOooo\\*`*o.,.                     , .. ..*....=OO`.\\\\`\\OOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoOoooooooOOooooooooooooooO^.       ...........    ......=oOOOOOOOOOOOOOOOOO@OOOOOOoOOOO@OO`             .**OOO.....=\\`...*=`,oo`,OOOOO\\....            ..`*/O@OOOOOOooooOOOOOO@OOO@@@@OOO\\,\\oOOO^,OOOOO\\*...... .......=OOO^*ooo***..                   . .O^ ****.\\`]ooOOOO\\=OOOOO=OOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoooooooO*\\oooooooooooooo,..    ...            ..    .....*oOOOOOOOOOOOOOOO@@OOOOOOoOOOOO@OO^            .*.=OOO`...=o^....o^/OooO@OOO^[oO]`....       ..**/OO@OOOOOoooooOOOO@O@OOO@@@@O@@OO.\\OOOO`O@@OOO\\*... .........,OOOOoooooo`..                    =oO^ =OOOOOooOOOOOOOO/OOOO\\\\OO\\O\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoooooooooO^=oo`...,[\\o/..........                 ... .****.oOOOOOOOOOOOOOOO@@OO@OOOOOOOOO@O`=`.         .....=OOO^.**o^....\\oOOOOOOO@/.....[\\O\\`..    ...**oO@@@OOOOoooooOOOO@OOOOO@@@@O@@OOO\\`,OOoOO@@OOO^*.............O@OOo^*=oo`..                    =OO` ,OOOOOOOOOOOOOOOO\\,.\\O^\\oOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooooooo^^*.  ...   ....            .                .../oO^.oOOOOOOOoOOOOOOO@@OO@OOoOOOOOO@@^..\\]....    .... .,OOOO`*\\o^...=ooOOOoOOO`..   ....,\\O]`....*^=OO@@OOOOoooo]oOOOO@@@OOO@@@@@@OOOOOoO\\,OoO@@@OOo*.............o@Oo**`=ooo..                    =OO.  OOOOOOOOOOOOOOOOO\\=oO...=o\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooooooo`.,             ..            ..              ..OOO*=OOOOOOOOooOOOOOO@@OO@OoOOOOOOOO@O^.....`*......     *\\oOO\\*OO\\]]/OOOOOOO^...       ...**[o\\o/o\\OO@@@OOOOooooooOOOO@@@@@O@@@@@@@O,\\OO/oOO`=O@@@OO^.............=O@OoooooOo^..             .    .o^\\.  =OOOOOOOOOOOOOOOOO^OOO^*O^\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoo[`....               ..            .             ..=OO,oOOOOOOOoooooOOOO@@O@OOooooOOOOOO@O*........ ...       ..\\oooOOOOOOOoOOoOO]....      ....*****,OO@@@OOOOOOOooooOOO@O@@@OO@@@@@OOO\\OOOOOOOOOO@@@@OO.............=O@Oo\\`[[oo`..            .    .]`  *. =OOOOOOOOOOOOOOOOOOOOOO\\,O\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/`         ...             .             ...         ..\\/\\/OOOOOOOoooooOOOOO@@@@OOOOoOOOOOOOOOO`....        .,/OOOOOOOoooOOOOOO[OOOOOOOOOOOOO\\]..  ..***,OOO@@OOOOOOOOOOOoOO@O@@@@OO@@O@@@OOOOOOOOOOOOOOO@@OO`............/O@@OOO/\\/]*O`           .     ./    * .OOOOOOOOOOOOOOOOOOOOOOOO=\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO[.               *                             ..         ..oOoOOOOOOoooOoOOOOO@@@OOooooOOOOOOO@O=O\\....   .,/OOOooOOOO\\o/*//o/\\OO^*OOooOO^.*[OOOOOOOOO\\]ooOOO@@OOOOOOOOOOooOO@OO@@@OOOO@@@@@@OOOOOO@OOOOOO@O@@O^...........*OOOO@OOooooOOo`               ./      * =OOOOOOOOOOOOOOOOOOOOOOO\\\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^                   ..             .          .   ..         ,oOOOO@OOooooooOOOO@@@@OoOoooooOOOOO@O**OO`.,ooOOOooo/``..*ooo.oo/oooOOO^,OOooOO^..***,ooOOOOOOOOOOO@OOOOOOOOOOOOOOOO@@@@^/OOO@@@@@OOOOOOO@OOOOOOOO@OO*.........*/OOOO@OOOooooOOO^             .     .   ..OOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^,.                    ..             .          .  . .       .,oOOO@OOOooOOooOOOO@@@OOOO\\`**=oOOOO@O`]oOOOOO[[ooO`......=OOOOoOooooOOOO^=OoooO^   ...*o/*/oOOOOOOOOOOOOOooooooOOOO@@OOOOOO@@@@@@@@OOOOOOO@OOOOO@OOOO^.........=OOOOOO@OOoooooOOO].                . .. . =OOOOOOOOOOOOOOOOOOOO[OO\r\n"
									+ "OooOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^                                     .          . .  ....  .,oOOO@OOOOooOOOOOOO@@@@OOOo*.**=ooOOOOOOOO@OOoooOO`.   ....oOOOOOOo^*OOOOOO\\OOooOO.  ..*,/oOOOOOOOOOOOOOOOOo/*`]*\\OOO@@@@@@@@@O@@@@@O@OOOOOOO@OOOO@OO@O^........*oOOO^OOOOOo=oooo/OO`               .   .  ..OOOOOOOOOOOOOOOOOOOO=OO\r\n"
									+ "OOoOOOOOoOOOOOOOOOOOOOOOOOOOOOOOO      ....                           .           ...   .,]*/\\OOOOOOOO/.  ,oOOOO@@@@OOo*..***\\oOOOOOOOOoOOOOOOo`.    ../OOOOOOOO\\/OOO@OoOOOoooO^ ..*/oOOO@OOOOOO@OOOOOOo`*****oOO@@@@@@@@@O@@@@@@O@@OOOOOOOOOOOO@OOOO........,OOOo**OOOOo/oooo^,OO.                  ..**.OOOOOOOOOOOOOOOOOOOO=OO\r\n"
									+ "OOOOOOOooOOOOOoOOOOOOOOOOOOOOOOOO     ..*..                           .           ....../OOOOoOOOOOOO^     .^oOO@@@OOo^*..***ooOOOOOOOOOOOO[o,OO\\*. ..=OOOOOOOOOOOOOOOOOooOOooOO,ooOOOOOOOOOOOOO@OoOOOooo\\`*,\\oOO@@@@@@@OO@@@@@@@OO@@OOOOOOOOOOOO@OOO.......*oOOO`..,OOOOooooo^=^,O.                 =` ..\\OOOOOOOOOOOOOOOOO/. =O\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO      ..      .                      ..           ....oOOOOOOOOOOOO/       **OO@@OOo`*..****oOOOOOOOOOO^****,/oOO^..,O@OOO@OO`*...OOoOOooOOOOOOOOOOOOOoooO@OOOO@OooOOooooo[[ooO@@@@@@@OO@@@@@@@@OO@@@OOOOOOOOOOO@OOO^......=OOO^....,OOOOooooo=. ,o             ...  . .*oOOOOOOOOOOOOOOOooO^\\.=\r\n"
									+ "OOOOOOooooOOOOOOOOOOOOOOOOOOOOOOO               ..                     .           ...=oOOOOOOOOOOO/.       .*OO@OO/**...***=OOOOOOOOOOOO\\`,oOOoooO^,OOOOOOOOOOO\\..=/\\*,OOOOOOO@OOOOooooOO@@OOOO@OooOOoooooooooO@@@@@@@@O@@@@@@@@OO@@@OOOOOOOOOOOO@OO^.....,OOO/......=oOOo,ooo..   ,`                .. *=OOOOOOOOOOOOOOOOOO.O\\=\r\n"
									+ "OOOOOOOooOOOOOoOoOOOOOOOOOOOOOOOO.                 .. .     .                      ...=OOO@O@OOOOOO.        **OOOo`*.....***OOOOOOOOOOOO@OooOO`\\ooooO@@OOOo*OO@@OOoo^****oOOOOO@OoooOOOOO@OOOOOO@OooOOoooooooo/oO@@@@@@@@O@@@@@@@OOO@@@OOOOOOOOOOO@@O\\*...]oOOO........=oOO`=o*.     .,                , ..,OOOOOOOOOOOOOo/\\O*/\\O\r\n"
									+ "OOOOoooOOOOooooOoOOOooOOOOOOoOooO`                           .                     ..,oOO@@OOOOOOO^         ===O/**......**=OOOOOOOOOOOOoO@OO/***[oOOOOOOO^/OOOOOoo/**,/OOOOOOO@Oo`=oOO@OOOOOOOO@oooOOooooooooooO@@@@@@@@OO@@@@@@OOOO@@@@OOOOOOOOOO@OOo*,oOOOO`.........=/OO^o`..     .*.             .*  ,\\OOOOOOOOOOOOO\\ooO=O`O\r\n"
									+ "OOOOOOOoOOooOOOOOOOOOOOOOOOOOOOOO^                             .  .     .    .     ..oOOO@OOOOOOO^         ..,oo*.......***oOOoOOOOOO@OOooO@@Oo`*/OOOOoOOO\\oooooOO^=OOOOOOOOOOOO@oooOOOOOOOOOOO@OoooOOooooooooooO@@@@@@@@O@@@@@@@OOoO@@@@OOOOOOOOOOO@OOoOOOOO^............oOO**..\\.                   .``.,\\OOOOOOOOOOOOOOOOo^  =\r\n"
									+ "OOOOOOOOooOOOOoOOOOOOOOOOOOOOOooO^                               .      .    .     ..oOOOOOOOOo/o..        ...`*.........**oOOoOOOOOOOOOOOoOOOOooOOOOOooOOO\\*,*,oOOOOOOooOOO@OOO@OOOooOOOOOOOOO@OooOOOoooooooooooO@@@@@@@@@@@@@@@OOoOO@@@@OOOOOOOOOO@@OOOOOO^.............,OO^,..oO.                    . ,=oOOOOOOOOOOOOO/OOOOOO\r\n"
									+ "OOOOOOOOooOOoOOOOOOOOOOoOOOOOoooo^                                       ..  .     ...\\/=OO@OO\\/* ..      ...*..........***oOooOOOOOOO@OOOoOooOO@OO@OOoOOooO@OOOOOOOoooooOO@@OO@@OO\\/oOOOOOOOOO@OOOOOOOoooooooooooO@@@@@@@@@@@@@@OoooO@@@@@OOO@OOOOOOO@OOOOO*..............,OO..  ...                    *.`oOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo`                                        .       . .***=OOOo`           ...............**=OO*oOOOOOOO@OOOOOOOOOOO@OooOoooOOOOOooooo`*\\ooOO@OOO@OoOOOOOOOOOOOOOOOOOOOooooooooooo,O@@@@@@@@@@@@@@OOooO@@@@OOOO@OOOOOOOO@@@Oo................=O^.  ,\\/                    *o=oOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOoOOOOOOOOOOOOOOOOOOOOOOOOOO/^                                                 .*,\\]=/o/.            *............*****\\Oo/OOOOOOO@OOOOO@OOOO@OooOOoOOOO/\\OOO/o^*,,ooOO@OOO@OoOOOO@OOOOOOOOoOOOOoooooooooooo*=O@@@OO@@@@@@@@OOOooO@@@@OOO@OOOOOOOO@@@O^............  ...O\\.  .,O\\                  .*/,ooOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo`                                                       .              *`............******oOoooOOOOOOOOOOOOOOOOOo/\\OOOOO`=``**\\Oo]oo]ooOO@OOO@OooO@@OOOOOOOOOOOOOoooooooooooo*,^,OOOOO@@@@@@@@@OOOoO@@@@OOO@OOOOOOOO@@OO............    ..=O`  . .\\^                .**`^o=OOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/=OO^                                                                     ..\\^..........********\\OOooOOOOOOOOOOOOOOOOo,OOOOOOOooooooOOOOOOOOOOOOOO@OoOOOOOOOOOOOOOOOOooooooooooo\\oo\\/^=OOO@@@@@@@@@@OOOooO@@@OOO@OOOOOOOO@@@O...........     ...\\\\  ..  ,\\              ... *^=.=OOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOO\\oO/[.                                                                    .*]*O^.........**********OOOoOO@O@@OOoo`OOO^*=OO^*[\\OOOOOOOOOO[\\OOoOOOOOO@OOOOOOOOOOOOOOOoooooo\\ooooooooo`****\\OOO@@@@@@@@@OOOOoO@@@OOOOOOOOOOO@@OOo...........    .. ..O^  ,.  .^            ..    `.*.OOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOO`O/O^...                                                                   .=OO\\,O\\*........*********,oOOOOoOOOOOOO*oOO`,oOO*/]*oOoOO/`=oOoOOooOOOOooO@OOOOoooOOOOoo/**/oooooooooooo\\/****,oO@O@@@@O@@@OOOOOO@@@@@O@OOOOOOOO@OO^..........    .  ...*O  .\\\\   .                 . *.=OOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOO/,oO/..  .                                                                  *oOOOO`=OO`.......*********,[oOOOOOOOO@OOoOOooooo*^*,`oOOO`***\\OOO/^=OOO@OoO@OOOO/***[[[********,[\\/\\oo[`****,/ooooO@@@@OOOOOO@OooO@@@@@OOOOOOOoO@@OO^..........    .. ....=. =/oO`                     ...OOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOO//OO..   ..                                                                .,OOOOOO^.oOO^.......***********[[oOOOOOOOOOOOOOOo^****=oOO^****/OO/,*\\oOO@ooO@OOOo*******************^******,oOOOOooooOO@@OOOOOOOOoO@@@@@OO^`*=*=O@OOO^ .........       ....=^ ,^. ,\\                    ...=OOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOO//O^.......*                                                               ./O@@OOOOO*,OOO\\.......*************`[\\oOOOO@OOOoOOo\\ooooOOO\\`oo[oO@O,,=oo@@ooO@OOo\\************************,oOOOOo/**[oooOOOOOOOOOOOO@@@@@O,\\]/OO/OOOOO*.. ........  . .   ...\\  *,^ .,*.              .*. ...OOOOOOOOOOOOOOOO\r\n"
									+ "OOOOoOOOOOOOOOOOOOOOOOOOO`........*                                                              .oO@@OO@OOO^.=OOO\\.....*****************=OOOO@OOOooOOooOOO`*\\OooooOOOOo\\ooOOOOooOOOo`..*********************/OO@OO/*******,\\oOOOooooo/O@@@@@O\\OOOOOOOOOO/...  ......   ....   . =.  *o\\  .*...           .** ...\\OOOOOOOOOOOOOOO\r\n"
									+ "OOOOoOOOOOOOOOOOOOOOOOOO*.........*.                                                          ..,OO@@OO@@@O@O..\\OOOO**.******************,oOO@OOOOooOOoOOOo**[OOOoOOo=OOoOOOOOOoooOO``,,\\*\\ooooo**********oOOOOOOO/***********,\\^......=O@@@@@OOOOOOOOOOO^.. ..        . ....  . ,^  .\\OO.   ....         .*  ,*.=OOOOOOOOOOOOOOO\r\n"
									+ "OOoOOOOOOOOOOOOOOOOOOO`...........*                                                          .=OO@@@OOO@@@OOO*..\\OO@O]*.*********,`******,OO@OOOOOOOoOOOO/****\\OOOOOooOOOOOOOO@o]oO^.,`,oooooooo********,oOOOOOoo`**************.......=O@@@@@@@@@OOOOOOO`.......     . ....   . .o  ..*\\O.                * .=/*=OOOOOOOOOOOOOOO\r\n"
									+ "OO,/OOOOOOOOOOOOOOOO/.............*.                                                     ..,\\OO@@@@@@@@@@@@OO^...=OO@Oo\\******,`ooo[\\**\\/OO@OOOOOOOOOOOOOo/=`/ooOO@OOOoOOOOOO@@OooO\\`=\\oooOOooo/`*****/OOOOOOo/`****************.......=O@@@@@@@@OOOOOOOOo......    .......     . =^ .  =\\^.                 ./O^,OOOOOOOOOOOOOOO\r\n"
									+ "OOOoOOOOOOOOOOOOOOO*................                                                 ..]/ooOO@@@@@@@@@@@@@@@@O....=OO@Ooo`**********\\^*oOOOOOOOOOOOOOOO@Oooo/`,ooO@OOoooO@OOOO@OOoOO`oooOOOooo^****]/OOOOOOoo`******************........O@@@@@OOOO@OOOOOOO*.........  ...    .. ..,\\ .  .,,`.                ,oO..=OOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOoOOO`...............,/o                                              .**,oOO@@@@@@@@@@@@@@@@@@@@O^....=OO@@OO\\******o]/o//OOOOOOOOOO@OOOO@OOOOoooooO@OOOOooOOOO@OO@OoOOOoOOOOoooo/oooOOOOOOOOo^********************........O@@@@@OOO@@OOO@OOO^..............    .. ...o..      ...          .  .=oO`..\\OOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOO`................/OOO`                                          ./,o\\]oO@@@@@@@@@@@@@@@@@@@@@@@O*....*OO@@OOo\\,`**\\**^/OOOOOOOoOOOO@OO@OOOO@OoooOOOOOOOooOOO@@OO@OOO@OOOooooooooOOOOOOOOOo/`*********************........O@@@@@@@@@@OO@@OOO*..............       ...,^. . .                 ..=O/[`. OOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOoOooO`. ..........***/OOO@O\\.                                       .*=OOO@OooO@@@@@@@@@@@@@@@@@OOO@@@O^.*****\\OO@@Ooo]*=oo/oOOOOOOOoooOOO@OO@OOOOO@OOOOOOOOOOOOOOO@OOOOooO@Oooo[,ooOOOOOOOOOOoo`***********************.......=O@@@@@@@@@@@@@@OOo...............      ..`,**` .  .                .*oOo  .  OOOOOOOOOOOO\r\n"
									+ "OOOOOOOOooO/,^............,OOO@@O`oO.                                 ..,]/`*/O@@@@OoOO@@@@@@@@@@@@@@@@OOOO@@@OO`******,OOO@Oooo*/oOOOOOOOOo/=ooOO@@O@OoooOOOOOOOOOOOOOOOO@OO/oooOO@@^....=OOOOOOOOOo/**************************.......oO@@@@@@@@@@@@@@OOo*..............     ...o`..*. .  ..              .*o/,^  . ,OOOOOOOOOOO\r\n"
									+ "OOOOOOOOoO`/`............,OOOOO/.*O^.                                .,o[\\]`,/O@@@@OoO@@@@@@@@@@@@@@@OOOOO@@@@@Oo*******ooOOOOOo\\oOOOOOOOooo/,ooOOO@@@Oooo\\oOO@OOOOOOOO@OOOO//oOOOOOOO../OOOOOOOOooo****************************......*OO@@@@@@@@@@@@@@@Oo*.............. ......o=..,..  .    .*.          *=o**^  .. =OOOOOOOOOO\r\n"
									+ "oOOOOOoOO,O............*/OOOOOO. =o.                                .,O\\]].\\ooO@@@@OoO@@@OO@@@@@@@@@@OOO@@@@@@@OO\\******,ooOOOOOOOOOOOoOooo`*=oooOO@@O@OoOOoOOOOOOOOOO@@O@OoOOOOoO`,/OOO@OOOOOooo^******************************.....*=O@@@@@@@@@@@@@@@@OO^*.......***.....`*,.=`...=].  .                .,**.=^  =^. =OOOOOOOOO\r\n"
									+ "OOOOoooO//............=OOOOoOOO. *^.                              ../OO@@@@\\=OO@@@@@OO@@@OOO@OOO@@@@@@OO@@@@@@@OoO^******,oooOOO@OOoooOoOooo`\\oooOOO@@O@OOOOOOOOOOOOOO@@@OOOOO`,.,OOOO@OOOO/ooo/********************************.....*=O@@@OO@@@@@@@@@@@@Oo*......****..,o*.,.*o. . ./^`.*.             .  **  ,=` .oo\\  \\OOOOOOO\r\n"
									+ "OOOOOO`/`...........*OOOOooooOO` ,^.                           .....=O@@@@@@\\=OO@@@@OO@@OOOO@OoOO@@@O@O@@@@@@@@@Ooo^*******oooOOOOOOooooOOOooooooOOOO@OO@OO@OOOOO@@OOO@@OOOOo.. ./OO@OOOOOo*oo\\`^*******************************.....*oO@@@@@@@@@@@@@@@@@OO^.....*,ooo/o\\*..^.=^. ...=]*[o\\***`...     .  .*    ,o. ,OO`  \\OOOOOO\r\n"
									+ "oooOO\\/.......... .=O//..**oooOO  *\\                        .,`.  ...O@@@@@@OooO@@@@OO@@O/OOOOoOOO@@@O@@@@@@@@@@OoOo*******,oooOOOO@@OooooOOOooooOOOO@@OOOOOO@OOOO@@O@@OOOO/./`=,OOOOOOOOo^*oooo\\]/*****************************.....*OOO@@@@@@@@@@@@@@@@OOO*....*ooOOO/.`.=*.=^. ...*.. ..          .    .   .  ,   oO.   \\OOOOO\r\n"
									+ "oOOo\\/........  .,o^......**\\ooOO`  ..                    .**..`...../OO@@@@O^\\O@@@@@@@@OOOOOOooO@@@@O@@@@@@@@@@OoOOo********oooOOOOO@OOooooOOOOOOOOO@@@OOOOO@O@OO@OO@OOO^,=o/,OO@OOOOOooooooooooo`*****************************... .*OOO@@@@@@@@@@@@@@@@@OO^....=oOOO^.=.*^..**.. ..,*.  .                  .   ,. .o@^    =OOOO\r\n"
									+ "oO`/`. ./...  .]o/. .......***=oOO` ...*.               .,/,../..   .O@@@@@@O`=O@@@@@@@@@O@@Oo\\OOO@@@@@@@@@@@@@@OOOOoo*******,oooOOOOO@OOoOOOOOOOOOOOO@@@@O@@OOOOOOoOO/Oo..o`OO@OOOOOOOOOOo\\ooo*********************************. ..,oO@@@@@@@@@@@@@@@@@@@@OO`..*oOOOO*.^*=^..=*.....*..                    .       *OO^  ,..,OOO\r\n"
									+ "^./...=\\*. .,oo`... .........***\\OOO`   ....         ..,^.`..*....../O@@@@@O^,*oO@@@@@@@@@@@OOoOOOO@@@@@@@@@@@@@@OOooo^********\\oooOOOO@@OOOOOOOOOOOOO@@@@OO@OOOO@@O@@OO/   oOOOoOOOOOOOOoooo\\/ooo^*****************************...=oOO@@@@@@@@@@@@@@@@@@@@@@O..oOOOOo.*o*=^../O.....**                   ..        =O/.  .^.,`=O\r\n"
									+ ",^]]oO/..]oOo`......  ...........*\\OOO\\      ......../[..**........oOOOOO@OO^*=oO@@@@@@@@@@OOOOOOOOO@@@@@@@@@@@@@OOOooo******/oooooOOOOOOO@@OOOOOOOOOOO@@@OOO@@OOOOO@OOO   =@OOOOO\\/OOOOoooooooo^/o^**********,]**o\\**,/**/o/\\]\\*.*ooOO@@@@@@@@@@@@@@@@@@@@@@@\\*oOOOOo.*o*=^.=OO`....**..                *         ./O.    ,`.`.=\r\n"
									+ "\\OOO/,/o`*`..........  ............ .[\\OOOO\\`.     .`. ... ....../OOOO/OOOO/*oOOO@@@@@@@@@@OOOOOOOOoO@@@@@@O@@@@@OOOooo\\**ooooOOOOOo/[[\\^***,OOOOOOOOOOOO@@OOOO@@@OOOOO`..,]o\\..**`*oOOOOOOOOOOOoooooooooooooooooooooooooooooooo^.*=oOO@@@@@@@@@@@@@@@@@@@@@@@O^/OOOO^.*o`=*.=OO`........              ..          *O\\     .**``.\r\n"
									+ "OOOOOOO[.............    ...............\\OOO@OOOO]]]].    .=]]OOO@@OOOOOOO^,oooOO@@@@@@@@@OOOOOOOOOOOO@@@@@@@@@@@OOOOoooooOOOOOOOOOOOooOOOOOOOO@@OOOOOOO@@OO@OOOO@OOO]. .oOOOO.......**`**[[ooooOOOOOOOOOOOOOOoooooooooooooooooo^**,oOO@@@@@@@@@@@@@@@@@@@@@@OOOoOOOOo..o^*^.oOOO`........            .           ./o*\\   ...*oO`\r\n"
									+ "OOOOo`*..............     .............*..\\o\\OOOOOOOOOOOOOO@@@OOOOOOOO@OOooOOOoOOO@@@@@@@@@OOOOOOOOOOO@@@@@@@@@@@OOooooooOOOOOOOOOOOOOOOOOOo\\OOOO@@@OOOO@@@@@OOOOOOOOO\\oOOOOO\\ .    .**[o^******]/oOOOOOOOOOOooooooooooooooooooo^***=O@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOO/^.=Ooo*=OOOo*.......                       ...`**.  ...*oOO\r\n"
									+ "OOo/*..............      ..  ...........**...=oooooooOOOOOOOOOOoooOO@OOOOOOOOOOOOO@@@@@@@@@@OOOOOOOO@OO@@@@OOO@@@@OooooooooooooOOOOoo^********,ooOO@@OOOO@@O@@OO@OOOOOOOO@@OOOO\\  =OOOoooooo\\[[[[[[\\oOOOOOOOOOOooooooooooooooooo\\*]oOO@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOoo*\\OO\\oOO@OO........                 .   ..    ,^  ....oOO\r\n"
									+ "oo^`**.............         ..................*[*****[\\oooooooooOOO@OOOOOOOOOOOOOO@@@@@@@@@@@OOOOOOOO@OO@@@@@@@@O@Ooo^*......*=oooooooo]]***,]oooOOO@OOOO@@OO@@OOOOOOOOO@@@OOOOo[\\OOOOOOOOOOOOoo`****,=oOOOOooooooooooooooooooOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOo\\o\\,OOOOOOOOOO\\..*^..                            *......=OO\r\n"
									+ "ooooo]`*.............      ...............................*...,OO@@OOOOOOOOOOOOOOOO@OO@@@@@@OOOOOOOOOOOoO@@O@O@@@OOoo\\`******/ooooooOOOOOOoooOOOOOOOOO@@@@@OOOOOOoO@OOOO@OOOOOOOo]****[ooOOOOoOOOOOOoOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOO`=OoOOOOOOOOOoo^..*...                          .`.....=oO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOO]*,OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@OOOOOOOOOOOOOOOOOOOOOOO@@@@OOO@@O@@@@@OOO@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@OO@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]/OOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "@@@@@@@@@@@OO@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "");
					for (int i = 0; i < 5; i++) {
						System.err.println("E  N  D");
					}
					System.exit(0);
				}
				System.out.println("警告：你正在试图越界！继续越界可能会发生无法预估的情况！");
				System.out.println("你现在的坐标是 " + "(" + x + "," + y + ")");
				System.out.println("由于越界，现在显示的坐标不一定在地图范围内！");
				Scanner whattodo = new Scanner(System.in);
				String whattodoR = whattodo.next();
				if (whattodoR.equals("w")) {
					y++;
					System.out.println("已尝试向上位移一个单位。");
					System.out.println("你现在的坐标是 " + "(" + x + "," + y + ")");
					System.out.println("请继续输入。");
				} else if (whattodoR.equals("s")) {
					y--;
					System.out.println("已尝试向下位移一个单位。");
					System.out.println("你现在的坐标是 " + "(" + x + "," + y + ")");
					System.out.println("请继续输入。");
				} else if (whattodoR.equals("a")) {
					x--;
					System.out.println("已尝试向左位移一个单位。");
					System.out.println("你现在的坐标是 " + "(" + x + "," + y + ")");
					System.out.println("请继续输入。");
				} else {
					x++;
					System.out.println("已尝试向右位移一个单位。");
					System.out.println("你现在的坐标是 " + "(" + x + "," + y + ")");
					System.out.println("请继续输入。");
				}
			}
		}
		/* Move Detection End */
	}

	public static void getBag(List<String> bag, int hasCount) {
		System.out.print("请选择武器：");
		for (String ws : bag) {
			System.out.print(hasCount + "." + ws + " ");
			hasCount++;
		}
		System.out.println();
	}

	// The Core Content of this game! DO NOT REMOVE!
	@SuppressWarnings("resource")
	public static void printDialogue(int x, int y, Player you, NPCs guideAngel1, NPCs guideAngel2, NPCs guideAngel3,
			NPCs guideAngel4, NPCs author, List<String> bag, int hasCount, Monsters caixukun, Monsters guolaoshi,
			Monsters dygg, Monsters ylzz, Weapons dagger, Weapons basketball, Weapons miHotel, Weapons bigSword,
			Weapons gfsStick) {
		if (x == author.getAtX() && y == author.getAtY() && author.isAlive() && !caixukun.isAlive()
				&& !guolaoshi.isAlive() && !dygg.isAlive()) {
			System.out.println(
					"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@[[`..                                                                                      ..[[[@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OooooooooooooooooooOO@@@@OO\\[@@..   .                                                                                                  .,[[[O@@@@@@@@@@@@@@@@@@@[`*.********,[[[[[[\\OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ooooooooo^*oooo[o**ooooooooo\\O@.                                                                                                                     \\O,*`,[[[****.****.............***..***O@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Oooooooooo^***************`*\\\\@^                                                                                                                       =\\.*****.............................*,@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ooo=ooooooo`o,*********o*****o@.                                                                                                                        \\^..*................................*O@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ooo=@O/ooooooo*********o****\\\\@.                                                                                                                        .@***........................./O....**O@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Ooo\\O@@O\\oooooo`*\\*****=o***o==O.                                                                                                                         =^.*......................*,O/*.....*=@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Ooooo@@@^oooooooooooooooo***o`O^.                                                        .`                                                               .@`*.....................*/O`*......*O@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OooooO@@@o\\oooooooooooooo**oo\\O^                                                      ./@O`.,]]]]]]]]]]@\\.                                                 =^*...................*,O`.........*O@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Oooooo@@@@o,\\oooooooooooo**oooO^.                                                 .,@@@@@@@@@@@@@@@@@@@@@@`                                                ,O**.................,O/.*.........*OOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@oooooO@@@Ooooooooooooooooooo==^.                                             .]@@@@@@@@@@@@@@@@@@@@@@@@@@@@].                                             .@**..............*,OO*..*.........*@OOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@OoooooO@@Oooooooooooooooooooo=@..                                    ...,]@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\\`                                            O^*.*.........*.,OO`*.............=@OOOOOOOOOOOOOooooOOOOOOOOO\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@OooooooO@Oo/oooooooooooooooo^o@.                            . ...,]/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@].                                         =^**.*......*.,/O**...............=@OOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@OoooooooOOooooooooooooooooooo/O^.            .  .. ....,]]]O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@].                                      =^**.*......*O/.***..............*O@OOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "@@@@@@@@@@@@@@@@@@@O@@OOOOOO@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@oooooooooooooooooooooooooooo=/@`..       .=O@@@@@OOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@].                                   =^.***...........................=@OOOOOOOOOOOOOOOOOOoOOOOOOOO\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@ooooooooooooooooooooooooooo/o\\O\\. .        .[OOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^                                 /^*..*.........................**=@OOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@OO@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@OoooooooooooOoo]/oo/[=ooooooooo@^.        ...,/OOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\\                                O^.*.....*...................*.**@OOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@OO@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@OooooooooooooOO@O]ooooooooo/ooo=@......,]/OOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@`                             ,@*.**....*.....................*=@OOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@O\\ooooooooooooooooOOOO@@OOOOO@@@@@@@@@OOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O@\\.                           =^****....*...*................**OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@OOooooooooooooooooooooooooooooO@@@OOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\\.                        ,O****.....*...*...............*.*@OOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@OooooooooooooooooooooooooooooooO@..,[[OOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\\.                      O`.***........**.................OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@Ooooooooooooooooooooooooooooo^O@.........,OOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\\.                   =^.****........**................*O.                     ........\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O@OOOOOOOOOOOOO@@OOOOOOOOOOOOOOOOOO@@OOooooooooooooooooooooooooooo=@....]OOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO]`               //******.........................*=^            .]]]]]]]]]]]]]]]]]\r\n"
							+ "**......................[[[[[[[[[\\OOOOOOO/[[[[[[[[[[[[[[[..\\OOOOOOOOOOOOOOO@@ooooooooooooooooooooooooooo\\/@OOOOOOOOOOOOOOOOOO@@@@@@@@@@@@OOO\\@@@@OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOO\\]]]]]`....,/O]]/Oo***......................**,/                               \r\n"
							+ "............................................................OO@OOOOOOOOOOOOO@@o\\oooooooooooooooooooooooo\\\\O@..,\\OOOOOOOOOOOO@@@@@@@@@@@/****=@OOO`*@@@@@@OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOO/`..\\O`*******....**.*.........**...**O.                               \r\n"
							+ "............................................................=OOOOOOOOOOOOOOOO@@Ooooooooooooooooooooooooo]oo@^....,OOOOOOOO@@@@@@@@@@@@@*....../*.**\\@@@@O^*O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOO/[.     .@\\*************.*.........**.*../^                            ,@OO\r\n"
							+ ".............................................................OO@OOOOOOOOOOOO@O@@OooooooooooooooooooooooOOOO@/\\OO]]..OOOOOOOOOO@@@@@@@@^*..........*=@@@O*..=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOO^               =@^*.*********..*.........*.***=/                            .@OOO\r\n"
							+ ".............................................................,OO@OOOOOOOOOOOOOO@@Ooooooooooooooo\\oo]ooooooO^.......,\\OOOOOOOOOOOO@@@@@**...*]OOO[\\OO@O[*...*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOO^                =\\`*************.........*.*.,O                            .OOOOO\r\n"
							+ "..............................................................=O@@OOOOOOOOOOOOO@@OooOOOOOOOOOOOOOOooooooo^O@............[OOOOOOOOO@@@O*`]O/[*..............*O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOO.                 \\^*********************..*.,O.                            /OOOOO\r\n"
							+ "...............................................................\\@OOOOOOOOOOOOOO@OOooooooooooooooooooooooooO@..........  ...,OOOOOOOO@//`**..**.............*=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO^                  ,O`************************=\\.                           =OOOOOO\r\n"
							+ "................................................................O@@@OOOOOOOO@O@@Ooooooooooooooooooooooooooo@..................OOOOOO@^*`./@/[**............*=@@@@@@@@@@@@@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^                    @^****************`]/******O^                          =OOOOOOO\r\n"
							+ ".............................................................,]OOOOOOOOOOOOOOO@@Ooooooooooooooooooooooooooo@............   ....=OOOO@\\**,O@@@@@O^..........*/@O@@@@@@@@@@O/=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`                     @^**************]O/**.*****=@                         =OOOOOOOO\r\n"
							+ "........................................................../O@@@@OOOOOO@OOOOOOO@@Ooooooooooooooooooooooooooo@....................\\OOO@@**,[@@@...............O@*@@@@@@@@@@@@`\\@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O.                      @*********`*,/O/************O^                       ,@OOOOOOOO\r\n"
							+ "...................................................................*OOOOOOOO@O@@OOooooooooooooooooooooooooO@............. ..\\OOOOOOO@@^***=@@/*..............**O@@@@@@@@@@@\\*O@@@@@@@@@@@@@@@@@@@@@@@@@@@O`                       @^*****,/OO/`***************=\\                      .OOOOOOOOO`\r\n"
							+ "..................................................................=OOOOOOOOO@O@@oOooooooooooooooooooooooooO@..................,OOOOO@@********...............**O@@@@@@@@@OOO*O@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOO]]..                @OOOO//`*********************@.                    .OOOOOOOOO^ \r\n"
							+ "................................................................../O@OOOOOOO@@O@OoooooooooooooooooooooooooO@.............  .....,OOO@O**...*..................*\\@@@@@@@O@@OO,@@@@@@@@@@@@@@@@@@@@@@@@@@OOO...[OOOOOO\\]..         .@***********.*************.**O^                    /OOOOOOOO/  \r\n"
							+ "..................................................................=@OOOOOOOO@O@@OOooooooooooooooooooooooooO^.....................=O@O*.*.....................*.=@@@@@@O@OO/O@@@@@@@@@@@@@@@@@@@@@O@@@@@OO^.    ..,\\OOOOOO\\..      @^***************************=@                   /OOOOOOOOO  *\r\n"
							+ "..................................................................,OOOOOOOOO@@@OOooooooooooooooooooooooooo@^.....................,@^***.......................*=@@@@@`\\OO\\@@@@@@@@@@@@@@@@@@@@@OOO@@@@OO^           .,\\OOOOOO`.   =O************************.***@^                 =@OOOOOOOO.   \r\n"
							+ "...................................................................=@O@OOOOOO@@OOooooooooooooooooooooooooO@^....................//`*****.......................*@@@@/*]]/@@@@@@@@@@@@@@@@@@@@@OOO@@@@OO`.               .,\\OOOO\\`..@^***********************.***O^                =@OOOOOOOO.    \r\n"
							+ "....................................................................O@@OOOOOO@@OOooooooooooooooooooooooooO@....................\\O`******.......................*O@@^O@@O@OO@@@@@@@@@@@@@@@@@OOOOO@OOO/.                      .,\\OO`=O***************************=^               ,@OOOOOOOO`     \r\n"
							+ ".....................................................................OOO@OOO@@OOOOooooooooooooooooooooooOO@......................\\O`*******.....................=@O\\@@@@OOOOOO@@@@@@@@@@@@@@@@OOOOOOO^                           ..,@`**************************O^              ,@OOOOOOOO^      \r\n"
							+ ".....................................................................,O@@@O@@@OOOOoooooooooooooooooooooooOO........................@@`*****......*..............***O@@@OOOOOO@@@@@@@@@@@@@@@@@@OOOOOOO.                             @^**************************=^             .OOOOOOOOO^       \r\n"
							+ "......................................................................,O@@@@@@OOOOOooooOOoooooooooooooOooOO.......................=OO@^********..................*/@@@O@OO@@O@@@@@@@@@@@@@@@@@@@OOOOOOo.                            =O**************************=^             OOOOOOOOO/        \r\n"
							+ ".......................................................................,O@@@@@OOOOOOoooOOoooooooooooooOooO@.......................OOO@\\*******.................**/O@@@OOOO@@@@@@@@@@@@@@@@@@@OOOOOOOOOO^.                           =O**************************/^            =@@OOOOOOO         \r\n"
							+ ".........................................................................O@O@@OOOOOOOOOOOOOOooooooooooOoOO@.....................]/OOO@@\\\\]/OO]****...........***O@@@@@@@OOOOOOO@@@@@@@@@@@@@@@@OOOOOOOOO^.                          =O******,]]/OOOOO@@@@@@OOO],/^             =@@@@@OO.         \r\n"
							+ "..........................................................................\\@@@@OOOOOOOooooooooooooooooOOOO@^........]]/OOOOOOOOOOOOOOOOO@@O/[[*****.........**,O@@@@@@@@OOOO@O@@@O/`.. . ..  . ,OOOOOOOOOOOOOO]]]]`......          .OO]OOO@@@@@@@@@@@@@@@@@@@@@OO^              .[@@@OO@O].      \r\n"
							+ ".....................,]]]O@@@@@@@@@@@@^....................................=O@@OOOOOOOOOOOOOOOOOOOOOOOOO@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOO@@`***************.*,O@@@@@@@O@@@@@@/`.........      ...=@OOOOOOOOOOOOOOOOOOOOOOOOOOOO\\]]]]/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.                 ..\\O@@@OO@\\]. \r\n"
							+ "....................O@@@@@@@@@@@@@@@@@@^.....................................O@@@@@@@@@@@@@O@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOO@@O`*************/O@@@@@@@@@@@O[......,]]]/OO[[`..........[[O@OOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/                       .[OO@@@O@\r\n"
							+ "..............**`,]]O@@@@@@@@@@@@@@@@@@^......................................@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O@OOOOOOOOOOOOOOOOOOOOOOOOOO@O*********,/O@@@@@@@@@@@@OO[[....                           ,[O@@OOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^                           .,[O@\r\n"
							+ "...........,[[[[OO@@@@@@@@@@@@@@@@@@@@@^.........................................[[[O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O@OOOOOOOOOOOOOOOOOOO@@@@@@`*****]O@@@@@@OOO@@@[.... .  .   .                              .,\\@@OOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O[[                                  \r\n"
							+ "...................*O@@@@@@@@@@@@@@@@@@^........................................................[O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@[.......[\\OO@@@@@OOO@@@@/`.........OO/[[..                                   .,[@@OOOOOOOOOOOOOOOOOOOOOOOO`.                                                \r\n"
							+ "...................*=@@@@@@@@@@@@@@@@@@^.............................................................[\\OOO@@O@@@@@OOOOO@@OOO@@O@@@`... .. ...  ......,[[OOOO]]].,]]]]/OOOOOOOOOO\\]]`...                                .,\\@OOOOOOOOOOOOOOOO/`.                                                   \r\n"
							+ "...................*=@@@@@@@@@@@@@@@@@@\\...................................................................*[\\OOOOOOOOOOOOOOOO@/....                    ..........[[[[[[[[[[[[[[[[[[[[[[[[[.                                ,@OOOOOOO/[[..                                                       \r\n"
							+ "...................*=@@@@@@@@@@@@@@@@@@@^...........................................................................[[OOOOOO@@`....                       ..    .                                                             ,@OO. .                                                            \r\n"
							+ "...................*=@@@@@@@@@O@@@@@@@@@@@OO*...........................................`................................./@@.......            ..                                                                              =@O.                                                             \r\n"
							+ "...................*=@@@@@@OO[[[\\O@@@@@@O`...............................................\\\\`............................=O@@........            ..                                                                               ,@\\.                                                            \r\n"
							+ "*..................*=@@@@@         @@@@@O*..................................................\\\\`......................../O@@....... .                                                                                              =@^                                                            \r\n"
							+ "*..................*=@@@@@         O@@@@O.....................................................,\\@\\`...................=@@@`.........                                                                                               =@.                                                           \r\n"
							+ "....*...............,@@@@@\\]`    ]]@@@@@`.........................................................,O@@@OO]]..........,O@@^..../`......                  .      ..                                                              ,    \\^                                                           \r\n"
							+ "**..*..............**@@@@@@@@@@@@@@@@@@@^............................................................,@@@@@O@@OO\\]]]]O@@@...//........                  .      ..                                                              \\.   .O                                                           \r\n"
							+ "*****.......*..*.*]OO@@@@@@@@@@@@@@@@@@@^................................................................[@@@@@@@@@@@@O@^.,O`...........                                                                                       =^    =^                                                          \r\n"
							+ "*****........*[[`****\\@@@@@@@@@@@@@@@@@@^*....................................................................[O@@@@@@@@^//................                                                          /`                        =^    .\\                                                          \r\n"
							+ "*******.****........****[O@@@@@@@@OOO`**............................................................................=@@@O`......................                                                     O^                        O`     O.                                                         \r\n"
							+ "************..............**.*...*..................................................................................=@@/........................                        .                           .O^                       .O.     ,^                                                         \r\n"
							+ "*************.......................................................................................................=@^..........................                                                   .O.                       =^       O.                                                        \r\n"
							+ "**************......................................................................................................O`.............................                                                 .@.                      .O`       =^                           ....,]]]]]]]]]]....          \r\n"
							+ "*****************.................................................................................................,O................................. .           ..             .                  .@.                      =\\....... .@`             ..]]]O/[[[......                          \r\n"
							+ "*******************..............................................................................................,O..............................=^....         . ..             .                 .=/                       /^          ,\\\\.  ..]//[[...          .,[[[[[[OOOOOO\\]]]...         \r\n"
							+ "*******************.............................................................................................=/...............................=O.....     ..../`.                              ..=^                      ,O             .@/`....   ................                ...,[[\\O]].\r\n"
							+ "**********************........................................................................................./`................................=O......... ..,/..                              ...@^.                     =^              .O...]]OOOOOOOOOOOO/[`.              .....           \r\n"
							+ "***********************.****..................................................................................@@@@@@@@@@OO]]]`....................@..........,O`...                                .@.                     .O                =@OOOOOOOOOO[[..        ..]]/OOOOOOOOOOOOOOOOOOO]]]`\r\n"
							+ "******************************...............................................................................O^...............,[\\@\\`..............@........./@.....                              ..=^.                     =^                 \\@OOOO/`..        .,/OoO/[[[[[[[[[[[[[[[[[....     \r\n"
							+ "***************************.****.............................................................................O@@@@@@@@@@]]`........,\\O`...........@......./@O^....  ..                          . .O.                     .O                   OO`.                                              \r\n"
							+ "***********************************..........................................................................O@@@@@@@@@@@@@@@@@]`.....,\\\\.........@....,/@OO/.........          .               ..=^.                     =`                   =@.                                               \r\n"
							+ "************************************........................................................................@@@@OOOOO@@@@@@@@@@@@@@@]....=@`......@..]@@OOOO.........  .        .    ...        ../^.                    ,^                ,]/[.=^.                                              \r\n"
							+ "****************************************.................................................................../@oooooooooooOO@@@@@@@@@@@@@`...,@\\...=@/@@OOOOO`............        .    ...        ..@^.                   .O.            ,//`..  ..=^.                                             \r\n"
							+ "**********************************..*****.................................................................=@oooooooooooooooO@@@@@@@@@@@@@`...,O\\./@OOOOOOO^...............                      .=@^.                   =^          ,/`.. .,/@@@O^=.                                             \r\n"
							+ "*****************************************................................................................=@OoooooooooooooooooO@@@@@@@@@@@@O.....\\@OOOOOOO`................               ..     ./=O....               .^.       ,/[..../@@OO@O@\\.,.                                             \r\n"
							+ "********************************************............................................................,@O\\oooooooooooooooooooO@@@@@@@@@@@@`...=@OOOOO/....................                  ...^.@...               .o.     .]/....]@@@@O@OO[[\\\\..                                             \r\n"
							+ "*********************************************...........................................................@Oooooooooooooooooooooooo@@@@@@@@@@O@\\..@@@OO/......................                 . .=..=^ ..              =^   .]O`...,/@@@O@O[*.....,^.                                             \r\n"
							+ "*******************************************.****......................................................*=@ooooooooooooooooooooooooo@@@@@@@@@OO@\\/@OOOO^..........................            ..../..=\\..             ../..,/[..../@@@@O@O[*........=\\. .                                          \r\n"
							+ "************************************************.....................*..*****..*........*...*]]]]OO]]]]@OoooooooooooooooooooooooooO@@@@O@OOO@O@@@OOOOO..............................        .. =....O`.            . /]O/....,/@@@@@@O`***.........=\\.                                           \r\n"
							+ "************************************************......................*,[[[[[[[[[[[[OOOOO@@@@@@@@@@@@@@@oooooooooooooooooooooooooO@@@@@@@@@@O@@@O.,OOO^.............................         ../....=^...        ...=^.  ../@@@@@@@O`**.............=\\                                           \r\n"
							+ "********************************************.................................................*.,[O@@@@@OooooooooooooooooooooooooO@@@@@@@@@@@@@@O`..,O@O`............................        ..,`....,O...        ..,@...]@@@@@@@@O`*.**..............=\\                               ....[[[[[[[\r\n"
							+ "***************************************************................................................*,O@oooooooooooooooooooooooO@@@@@@@@@@@@@@@@`....,OOO..............................      ....  ...O..       . ../@]/@OOO@@@@O/****.................\\^.                                        \r\n"
							+ "***************************************************.**...............................................OOOoooooooooooooooooooooO@@@@@@@@@@@OOO@@^......,OO`.............................            ...=^.....   ...,/@@OO@@@@@@@^*****..................\\^                                        \r\n"
							+ "***************************************************.*...............................................,@OoooooooooooooooooooooO@@@@@@@@@@@O,O@@@^........=\\...............................            .,O...........o.@@@@OOOOOO@\\****...................*@.                                       \r\n"
							+ "*******************************************************............................................./OoooooooooooooooooooooO@@@@@@@@@@@O*.=@@@^.........=^..................................        ..O..........o..@OOOOOOOOOO@@^******................=\\..                                     \r\n"
							+ "*******************************************************.*..........................................*@Oooooooooooooooooooooo@@@@@@@@@@@@^...O@@@.............................................      ... =^ .      ...=@OOOOOOOOOOOO@@`*******............../^.                                     \r\n"
							+ "************************************************************.......................................O@oOoooooooooooooooooooO@@@@@@@@@@@^....,O@@................................................   .....O...      ..=@OOOOOOOOOOOOOO@@^*.***...............\\^.                                    \r\n"
							+ "***********************************************************..***..****............................,@OooooOoooooooooooooooo@@@@@@@@@@@@......=@@^..................................................  ...... .    ...=@OOOOOOOOOOOOOOOO@\\*****....*.........*O^.                                   \r\n"
							+ "****************************************************************]]]]]]]]]/OOO]***................*@OoOoooOoooooooooooooooo@@@@@@@@@@@^.......\\@^....................................................  ...       ...=@OOOOOOOOOOOOOOOOO@@`***...............*\\\\.                                  \r\n"
							+ "**********************************]]********`,]]]]OOO@@@@OOOOO[[[`********......................*/@OoooooooooooooooooooooooO@@@@@@@@@.........=@.....................................................         .. ..=@OOOOOOOOOOOOOOOOOO@@******.....*.......*\\\\.                                 \r\n"
							+ "**********************,]]`*****]oOOOO@@@OOO[[[[************************.........................*@OooooooooooooooooooooooooooO@@@@@@^..........@^....................................O...............         .....@@OOOOOOOOOOOOOOOOO@/*******...............\\^..                               \r\n"
							+ "`**][*,]******,o`*****[o[\\OOO[[[[`**[`**`*******************************....*..*.................\\@OooOooooooooooooooooooooooooO@@@@*..........=O...................................O`..................       ....@OOOOOOOOOOOOOOOOO@O**********.*..........*,@. .                              \r\n"
							+ "=o\\oooOOOOOOOOOOOOOOOOOOOOOOOOO]oo\\]]]oo]]]*********************************..*.................**O@OoOoooooooooooooooooooooooooO@@@...........,@................................../^...................       ...=@OOOOOOOOOOOOOOOO@@***********.*...........,@.                                \r\n"
							+ "@O[\\oooooooooooooooooooooo/[[[[[[[[[[[[[[[[[[[[[OOOOOOOO[OOOOO@@OOOOOO/[OO[[^...................**O@@@OOOoOoOooooooooooooooooooooO@@............\\^................................//......................... ....=@@@@@@@@@@@@@@@@@@^************......***...O^.                                \r\n"
							+ "o\\]]o***************************************************`*****************..*....................=@@@@@@@OOOOooooooooooooooooooooOO@`...........=O.............................../@.......................... ....@@OOOOOOOOOOOOOOO@^*************.........*,O`...                               \r\n"
							+ "**************************************************************************......................*\\@@@@@@@@OoOOoooooooooooooooooooooO\\............=^.............................=@^..............................=@OOOOOOOOOOOOOOO@O****************.......,O`..                                 \r\n"
							+ "**************************************************************************......................*,O@@@@@@@@OOOOooooooooooooooooooooo@^............@............................/@^................................@@OOOOOOOOOOOOO@@*****************.***.*//.. .                                 \r\n"
							+ "o^*********************************************************************....**....................**\\@@@@@@@@OOOOooooooooooooooooooooO@`...........=^........................../@/.......................    .......@@O@OOOOOOOOOO@^****************.*.***O/..                                    \r\n"
							+ "o^*************************************************************************.*......................**O@@@@@@@OOooooooooooooooooooooooOO............@.........................O@O....................................@OO@OOOOOOOO@O********************.*O^...                                    \r\n"
							+ "o*****************************************************************************.*.....................*,O@@@@@@OOOOoOOooooooooooooooooo@\\...........=\\.......................@@O^....................................\\@OOOOOOOOO@@^********************=@`...         .                           \r\n"
							+ "o*****************************************************************************.*.......................*O@@@@@@@OOOOOooooooooooooooooOo@\\...........O`.....................@@O^.....................................=@OOOOOOOO@@O********************=@`.......      .                           \r\n"
							+ "o^***oo^*************************************************************************.......................*O@@@@@@@@OOOOooOoooooOOoooooooo@^..........=O...................,@@O/.......................................@@OOOOOOO@O*****************,.*//..........                                 \r\n"
							+ "o^***oo^*******************************************************************************..................*O@@@@@@@@@@OoOoooooooOooooooooo@^..........=\\.................,@@@O........................................@@OOOOOO@@^*******************/^. ..   ....                                 \r\n"
							+ "o^***oo^******************************************************************************.....................\\@@@@@@@@@@@OOOOOOooooOoooooooo@^..........O^...............,@@OO*,]OOO\\]]].........]]]]]]]`..............O@OOOOO@@/******************=O`............ ..                              \r\n"
							+ "o^***oo^****************************************************************************.*.*...................*[O@@@@@@@@@@@@OOOooooOOoooooooo@\\..........O..............O@@@@O@OO@@O@@@@@@O/[[.........................=@OOOO@@^``****************//..................                .            \r\n"
							+ "o^*********************************************************************************.*****....................*\\O@@@@@@@@@@@@@OoOoOooooooooooOO.........,^...........,@@@@@@@@@@@@[[..................................=@@@O@@o*****************/@`.......... .........       .                    \r\n"
							+ "o^******************************************************************************************....................*\\@@@@@@@@@@@@@OOoOooooooooo\\O@`........=\\........,/@@@@@@@@[...........................................@@@\\****************]@/......................       .                    \r\n"
							+ "oo^**********************************************************************************..*****......................*,O@@@@@@@@@^\\@OoOoOooooooooO@\\.......]O@OOOOO[[[[[[[[[[[[[OOO@OOO\\]]]`.............................../O/***************,O/.......................    .                        \r\n"
							+ "oo^*************]********************************************************************..*****........................**=@@@@@@@*..,@OoOooooooooooO@\\.=O[`.................................,[[@@@OO]]`..................,@/=*`*************O@^.............................                        \r\n"
							+ "ooo^************]\\**********************************************************************..............................*O@@@@@@.....\\@Ooooooooooo\\O@\\.........................................]]]OOOOOO[[[[[[[[[....../O\\*************[*O@@^.......................  ......                  .    \r\n"
							+ "ooo^************oo**********************************************************************..............................*O@@@@@O..../O@@@@@@OooooooooO@`................................]]O@O[[`.....................,@/,`************`=@@O/................................                       \r\n"
							+ "o^=o************oo**************************************************************************....*....................*.O@@@@@O../O`.OOOooooOooooooooo@^.........................]/@@/`............................/O***************=/@@O^...................................                     \r\n"
							+ "o^=o************oo**************************************************************************....*....................**=@@@@@^,O`..*@OOOoooooooooooooOO......................,[.................................,O^,`*************,@@O@/........................................................ \r\n"
							+ "ooooo^**********ooo****************************************************=O@OO]`*,*************.......................*..=@@@@@O/...*@OoOooooooooooooooo@^....................................]]]O@@@@@@@@@@@@@@@@O[^=**********=`,/@@O@O..................................................,]]]]]]]\r\n"
							+ "ooooo^**********ooo********************************************]]OOOOOOO]o`]/[OO@OOO\\`************..................**.=@@/*......OOoOOoOoOOoooooooooo@^...........................,]/@@@@@@@@@@@@@@@@@@@@@@@@@o\\^,`**********,O@@@O@O`..............,]]]O@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOO\r\n"
							+ "ooooo*****=o****ooo********************************,`*,*\\]O@@@@@@@@@@@@@@@@@@@OO\\]`[/oOO@O]`********..................*//......../@oOOooOO@OoOOoooooooO\\.....................]/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O=\\o/******,^,]/\\@@@@OOO.....]]/OOO@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "ooooo*****=o****ooo******************************=O@@@@OOOO/[[[OOO@@@@@@@@@@@@@@@@@@@@@O\\]],[O@OO]*,*****............*=\\,]`.....*@OoooOOO@@ooOOoooooooO@O]]]]`...*]]]]]O@@@@@@@@@@@@@@@OOOOOOoooooooooooOO@@o/\\]********^*o,O@@OOOOOOOOOOO@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "oooo****o**o****************************************************************************ooOOOOO`*,\\O@O\\]`********..***OO..,O@\\/]/@OOOOOOo@@OoOooooooOoo@OoooOOOOOOoooooooooooooooooooooooooooooooooooo/o\\OOooo[`[\\]***o^,/o@@@OOO@OOOO@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@\r\n"
							+ "ooooo\\`*o**o*******************************************************************************************[O@@@@\\]]]*****=@`../O[O@O@@OOooOoO@OOOOooooooOO@Oooooooooooooooooooooooooooooooooooooooooooooo\\O@/o=o\\OO]o[\\\\*o^\\@@@@@@O@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "ooo/oo\\]o**o**********************************************************************************************\\O@@@@@@@@@@@@\\*....=@O@@OOOOOOO@@@@OOoooooOO@OOOOOOOOOOOOOOoOOoooOOOOOOOOOOooooooooo/oooo^o/@o/[`[[ooOOOooOOOO@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "@@OO/[ooo**o**************************************************************************************..*********[O@@@@@@@@@@^****,@O@@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOO@OOOOOO@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "ooooooo^**=o************]\\*************************************************************************.....*********,\\O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "ooooooo^**=o************oo*************************************************************************.....****..******/O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "ooooooo^**=o************oo*****************************************************************************.****..****/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOO@@OOOO@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "ooooooo^**=o************oo*****************************************************************************.****.****O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O@OOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@OO@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "ooooooo^**=o*******************************************************************************************......***=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "ooooooo^**=o****************************************************************************************************/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO@@@O@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "ooooooo/********************************************************************************************************/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "oooo*******************************************************************************************************]]O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO@@@@OOOOOOOOOO@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "ooooooo^*****oooooo******************************************************************************]]O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "ooooo********oooooo***************************************************,[[[]]]]]]]]]]oooO]]O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "ooooo**o*****oooooo************************************,[\\]/`]oOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOO@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "ooooo**o*****oooooo*****************************]/o\\[]OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOO@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "ooo^********************o^,`********\\]`,[]]o]/oO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOO@O@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "ooo^**********************,]]]]]o`**/[o]O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOO@O@OOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "oo^***********=o****,[]]]*,[[\\o\\oO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "oo^***********=o**]]*/o[[oOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "oo****o\\=[*=//[\\]/ooOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "oo`*]]oooooooo/O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "oooo]oo\\\\/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "");
			System.out.println(author.getName() + "：“哦吼！没想到你竟然遇到我了！不知道你是乱走还是认真探索了每一个坐标呢。”");
			System.out.println(author.getName() + "：“既然你遇到了我，说明你离通关已经不远了。顺便说一下，假如你没有遇到我，你是永远都无法通关的！”");
			System.out.println(author.getName() + "：“截止目前，你已经打败了 3/4 的怪物！恭喜你！”");
			System.out.println(
					author.getName() + "：“所以，现在你必须和我玩一个游戏，只要你赢了，我就会给你直通终点的'钥匙'，但是你会付出一点(无关痛痒的？)代价。如果输了，游戏直接结束！”");
			System.out.println(author.getName() + "：“听好了！——”");
			System.out.println("已知 x-y=(D-1)-z，求 x.y,z。（提示：有两组答案，回答出其中一组即可。）输入的格式示例：x,y,z");
//			答案应该有两组
//			x=5，y=5、z=1
//			x=1，y=5、z=5
//			因为在问题中没有规定x=v y=f z=e
//			x-y=(D-1)-z    变形得   x+z-y=(D-1) 或   z+x-y（D-1）
//			而尤拉公式是 v+f-e=2
//			所以才这种情况下x,z可互换
			Scanner getAnswer = new Scanner(System.in);
			String theAnswer = getAnswer.next();
			if (theAnswer.equals("5,5,1") || theAnswer.equals("1,5,5")) {
				System.out.println(author.getName() + "：“什么！你竟然答对了！不过应该是蒙的或者百度来的吧 :)”");
				System.out.println(author.getName() + "：“其实我也完全不懂这题是什么鬼，这题是出自....禁止事项”"); // 出自轻小说《凉宫春日》系列的《凉宫春日的暴走》，强烈推荐！！！
				System.out.println(author.getName() + "：“我是一个诚信的人，'钥匙'已经给你了，你也付出了代价。不过问题不大，快去打 BOSS 吧！”");
				ylzz.setAttack(0);
				System.out.println("[System] Monster " + ylzz.getName() + "'s attack has been set to 0.");
				bag.clear();
				System.out.println("[System] Player " + you.getName() + "'s bag has been cleared.");
				System.out.println(author.getName() + "：“等等！形式上还是给你一个好东西吧，这是我祖传的武器，希望对你有用23333~”");
				bag.add(gfsStick.getName());
				System.out.println("获得新武器：" + gfsStick.getName() + "，攻击力：" + gfsStick.getAttack() + "，介绍："
						+ gfsStick.getIntroduction());
				System.out.println(author.getName() + "：“不多说了，拜拜~ 有缘再会 :)”");
				author.setAlive(false);
			} else {
				System.err.println("回答错误！游戏结束！");
				System.exit(0);
			}
		} else if (x == guideAngel1.getAtX() && y == guideAngel1.getAtY() && guideAngel1.isAlive()) {
//			System.out.println("  く__,.ヘヽ.        /  ,ー? 〉\r\n" + "           ＼ ', !-─‐-i  /  /′\r\n"
//					+ "           ／｀?'       L/／｀ヽ?\r\n" + "         /   ／,   /|   ,   ,       ',\r\n"
//					+ "                    ?  / /-‐/  ｉ  L_ ? ヽ!   i\r\n"
//					+ "                        ? ? 7?｀?   ?'?-??!ハ|   |\r\n" + "          !,/7 '0'     ′0iソ|    |\r\n"
//					+ "          |.从\"    _     ,,,, / |./    |\r\n"
//					+ "                            ?'| i＞.?,,__  _,.イ /   .i   |\r\n"
//					+ "                                  ?'| | / k_７_/?'ヽ,  ?.  |\r\n"
//					+ "              | |/i 〈|/   i  ,.? |  i  |\r\n" + "             .|/ /  ｉ：    ?!    ＼  |\r\n"
//					+ "              kヽ>??    _,.??    /?!\r\n" + "              !'〈//｀Ｔ′', ＼ ｀'7'?r'\r\n"
//					+ "                                     ?'ヽL__|___i,___,ン?|ノ\r\n"
//					+ "                                                ?-,/  |___./\r\n"
//					+ "                  '?'    !_,.:");
// ↑OLD WAY
			System.out.println("..く__,.ヘヽ.        /  ,ー､ 〉..............\r\n"
					+ "...........＼ ', !-─‐-i  /  /´.............\r\n" + "...........／｀ｰ'       L/／｀ヽ､.............\r\n"
					+ "........./   ／,   /|   ,   ,       ',.......\r\n"
					+ ".......ｲ   / /-‐/  ｉ  L_ ﾊ ヽ!   i..........\r\n"
					+ "........ﾚ ﾍ 7ｲ｀ﾄ   ﾚ'ｧ-ﾄ､!ハ|   |...............\r\n"
					+ "..........!,/7 '0'     ´0iソ|    |............\r\n"
					+ "..........|.从\"    _     ,,,, / |./    |.....\r\n"
					+ "..........ﾚ'| i＞.､,,__  _,.イ /   .i   |.....\r\n"
					+ "............ﾚ'| | / k_７_/ﾚ'ヽ,  ﾊ.  |.........\r\n"
					+ "..............| |/i 〈|/   i  ,.ﾍ |  i  |.....\r\n"
					+ "..............|/ /  ｉ：    ﾍ!    ＼  |.......\r\n"
					+ "..............kヽ>､ﾊ    _,.ﾍ､    /､!............\r\n"
					+ "..............!'〈//｀Ｔ´', ＼ ｀'7'ｰr'.......\r\n"
					+ "..............ﾚ'ヽL__|___i,___,ンﾚ|ノ.........\r\n"
					+ "..................ﾄ-,/  |___./................\r\n"
					+ "..................'ｰ'    !_,.:................");
			System.out.println("你遇到了一个 NPC，它是 " + guideAngel1.getName());
			System.out.println(guideAngel1.getName() + "：“你就是新来的冒险者？告诉你一点东西，这个游戏地图的范围是 x,y=±5”");
			System.out.println(
					guideAngel1.getName() + "：“同时，请自行脑补地图，本游戏难易度从小到大分别是第一、四、三、二象限！请好好探索每一个坐标！说不定会有什么神奇的事情发生呢...”");
			System.out.println(guideAngel1.getName() + "：“一步登天的后果只有死路一条”");
			System.out.println(guideAngel1.getName() + "：“听我一句劝，你最好不要跑出地图范围，否则到时候会很麻烦的~”");
			System.out.println(guideAngel1.getName() + "：“总之，先收下这些吧！”");
			bag.add(dagger.getName());
			you.setAttack(you.getAttack() + 10);
			System.out.println(
					"获得新武器：" + dagger.getName() + "，攻击力：" + dagger.getAttack() + "，介绍：" + dagger.getIntroduction());
			System.out.println(guideAngel1.getName() + "：“另外告诉你，我在地图上还有三个好姐妹，但是这个该死的作者设定了，你遇到我们当中其中一个后，剩下的都会瞬间去世。”");
			System.out.println(guideAngel1.getName() + "：“虽然遇到你的时间只有几秒不到，但是我真的很开心！”");
			System.out.println(guideAngel1.getName() + "：“我们永远在这里等着你，后会有期！”");
			System.out.println("话音未落， " + guideAngel1.getName() + " 瞬间消失了");
			guideAngel1.setAlive(false);
			guideAngel2.setAlive(false);
			guideAngel3.setAlive(false);
			guideAngel4.setAlive(false);
			System.out.println("你的泪，拉了出来。");
		} else if (x == guideAngel2.getAtX() && y == guideAngel2.getAtY() && guideAngel2.isAlive()) {
			System.out.println(
					"OOOOOOOOOO........................................................................................................................................\r\n"
							+ "OOOOOOOOOo.......................................................]]/OOOOOOOOOOOOOOO]`.............................................................\r\n"
							+ "OOOOOOOOOo.................................................,/\\/OOOOOOOOOOOOOOOOOOOOOO^............................................................\r\n"
							+ "OOOOOOOOOO\\.............................................,/.=OOOOOOOOOOOOOOOOOOOOOOOOO^,]]]`.......................................................\r\n"
							+ "OOOOOOOOOOOO\\`..........................................^,]]]]OOOOOOOOOOOOOOOO@OOOOOO@OOOOO\\,`...................................................,\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooo\\]]OOOOOOOOOOOOOOOOOOOO/[ .@@//@@OoOOOO^.=................................................./O\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/OOOOOOOOO`     =@@   ,OO@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoooo]]]]]]]]]]]/OOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@OOOOO/O/  ,OOO@`/\\^.//OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`,OOOOOO@OOOO@^ =@@OOO@O@\\@OOOOOOOO/[OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/*/OOOOOOOOOOOO@\\ ,@OOO^@@@OOOOOO/[].....[OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^OOOOOO  ,O@@OO@@@@@@O]/OOOOOO`.... .      ,OOOOOOOOOOOOO/[=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\\`  ,\\OOOOO] =OOOOOOOOOOOO[`    =              OOO/..].... ,OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^,        =[`...,...  /^           O             .O\\]OO[...,OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/         .`          / .^          ^\\             .OOO]]].,OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO          ,          ,   ,          . `             =OOO/`   /OOo^,\\OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO         =^         /     , =       .  `           . ^       , `,OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO        =O`        .   ]     ^      .  .   ^         .         =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO        OO                  ...       `..  ^`        =         =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO       /O^    . ,                            `      `=^        =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO      =OO^    ./   ]]]`        .   .          .     .`o        OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO      OOO`^   /,\\/\\\\OOOOO       . .    ]]]`    `    =O`       =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^     =OOO/^   \\ ^ ,..O..^        ..  /[[\\OOO\\O`=    OOO^      =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^     OO` `    ,     ]]`          ,   =.,/..^ ,^=    OOO^      OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^    ,OOOOO   , ^    ,[`                ,]`    ,=    OOO^     =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^    /OOOOO   ,`,                       ,[`    ``      /^     =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^   ,OOOOOO   =\\,`                            ^.`   OOOO^     OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO    /OOOOOO   OOO,                           /./   ,OOOO\\    =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^  .,OOOOOOO`  OOOO\\^        ,o]]            `=OO   =OOOOO    =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO . ==OOOOOOO^  OOOOOO]\\        ..          /,OOO^  ,OOOOOO    OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`.  =OOOOOOOOO  OOOOOOOOO/]             ,,,/OOOOO.  /OOOOOO .  OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^..  =OOOOOOOOO^ OOOOOOOOO/,^.\\`    ],`/\\OOOOOOOO/  /OOOOOOO.   OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/ .,  =OOOOOOOOO^ =OOOOOOO]OO^  ........OO`OOOOOOO^ =OOOOOOOO,   =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/   ^  =OOOOOOOOOO =OOOOOOOOOO^   .......OOOOOOOOOO .OOOOOOOOO^  .,OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/   /^  /OOOOOOOOOO^=OOOOOO` ][           ,\\/ \\OOOO` OOOOOOOOOO   . OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/   =O   OOOOOOOOOOOO=[[      ,             .      , \\OOOOOOOOO^  .. =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/   ,O^  =OOOOOOO/    O^                            . `.   \\OOOO^  O   OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO   .OO   =OOOOO/      O^`                          * /,      \\OO`  O`  =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^   =OO   OOOOOO       @@.` `..`   .,/`  ,\\`.   ``*/ ,.@^      OO   O^   OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO   .OO^  /OOOOO        OO,, ,`` ../.[O/.,\\/`,`*  `, ..\\@^      ,O   OO   =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^   =OO  ,OO  .,        OO.... ../     /OOO    ,`.. `.`=O^       ... OO`   OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^  ,OOO  OOO\\ =,        OO.  ../      `....\\      .....=O^       *^  OO^   =o\\OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`  =OO^ =OOOO@.   ,`  ..@`   ,       ^......,          =@^.  .     \\`OO^   =O\\],oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooo^  =OO^ OOOO@O@\\   `  .,            /. .... .\\          ,^   .   =@O^OO^   =@ooOOOOOoOOOOOOOOOOOOOOOOOOOOOOOOOO^\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOoOO@@OO^  OOO^=OOOO` =OO@\\]` /             \\]*.**.*.*           ,` ,]]@OO/  OOO   =OOOO@OO@@@OOOOOOOOOOOOOOOOOOOOOOOOO^\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOO@@@OO@OOOO  OOO^OOOOOO\\ @OO]`*\\`               \\oooo/              =[`,]/OO =OOOO   /OOOOOO@OOOOOO@@OOOOOOOOOOOOOOOOOOOO^\r\n"
							+ "OOOOOOOOOOOOOOO/\\/OO@@OOOOOOOOO@OOO^ O@O\\OOOOOOO^  ],OO@                  ,`                .@OO`]  ,@OOO^   OOOOOOOO@OOOOOOOOOOO@@O\\/[OOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOO[,/O@@OOOOOOOOOOOOO@OOOOO =OOOOOOOO,/@\\,/[=O@.                                 . =OO^[O`/@]`/O^  /OOO@OOOOOO@OOOOOOOOOOOOOO@OO],\\OOOOOO\r\n"
							+ "OOOOOOO\\]OO@OOOOOOOOOOOOOOOOOOOOOOOO^.O@OOOOO^=`\\OOOOOOO\\ .                                .@OOOO@OO/ O=O  =OOOO@OOOOOOO@OOOOOOOoooOOOOOOOO@OO][\\O\r\n"
							+ "OO/\\/O@@OOOOOOoooooooooOOOOOOOOOOOOOO`\\O@OOOOO@@^ [@@OO@O\\`.            .                .,/OOOOOO[`,@@O/ =OOOOOOOOOOOOOOOOOOOoooooooooooOOOOOO@OO\r\n"
							+ "oO@@OOOOOOOOOOOo/[[ooooOOOOOOOOOOOOOOO.OoOOOOO@@O@@@O\\/O@OOO\\]     ,]/...^`.=..,]`.    ,/OOO@O]]O@@OOO@O.,OOOOOOOOOOOOOOOOOOOOooo/**,/OOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOO`**\\oOOoOOOOOoooOOOO\\=OOOOOOO@OOOOOOOO@OOOOOOOOOOO]`   ,`,`  ,/OOOOOOOOOOO@OOOOOOOOOO^,OOOOOOOOOOooooOOOOOOOo/**/OOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOO`*=O\\OOoooooooOOOOOo\\OOOO@O@OOOOOOOO@OOO\\OOOOOOOOO`  .,`.  ,OOOOOOOO/OOOOOOOOOOOOOO@OOOOoooOOOOooooooooO\\Oo*=OOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOO`oOOOoooooooooOOO/*[ooOO@O@OOOOOOOOOOOO@\\\\OOOOOOO.,@   =@` /OOOOO//OOOOOOOOOOOOOOOOOOo/`*,oOOoooooooooOOoO\\OOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOoOoo[[[[[\\ooOOO*****\\OOO@OOOOOOO@@OOO@@/\\OOOO@OOOO . =OOOO@OOO/\\O@OOO@OOOOOOOOOOOO/*****\\oO/[`,]]`*,oOO/OOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOO^OO\\OOOOOOOO]=Oo*....,@O@@OOOOOOO@OOOOOO^OOOOO@OOOO`  .OOOO@OO/O=@OOOOO@OOOOOOOOOO@`.....=O^/OOOOOOOOOOO/OOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOO\\@OOOOOOOOOOOOO^,/OOO@O@@@OOOOOOO@OOOO\\@OO@@@O@OOOO^``,OOOO@O@@OO@@\\OOO@OOOOOOOOO@@@OOOO\\,OOOOOOOOOOOOOOO\\OOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@O@@OOOOOOO@OOO` =@@@@@@OOOOO.  =@@@@@@@@@@/ .\\OO@OOOOOOOO@O@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@OOOOOOOOOOOO/  ,  [OOOOOOOO@^ .=@OOOO@O@/  ,.  ,OOOOOOOOOO@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/ ]`=@OOOOOOOOO@O\\    .``  \\OOOOO\\    /OOOOO/     .  /O@OOOOOOOOOO/ ] ,OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^,@@O.OOOOOOOOOO@OOO\\ .   ,\\   [OOO^.` OOO[   `.    =@OOOOOOOOOOOOO^\\OO^,OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@ =@@@@OOOOOOO@O@OOO@OO@]] ..   `          ,.   .,]]OOO@OO@OOOOOOOOOOOOOO =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@`=@@@OOOOOO@@@@@O@@OOOOO@@O    .  ,]`]],       ,@OOOOOO@@O@@@OOOOOOOOOO/ OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@OO  \\@OOO@@@@@@@@@@OOOOO@@@@OO]]        .     ,O@@OOOOOOO@@@@@@@OOOOOOO` /@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\\  ,O@@@@@@@@@@OOOOOO@@OOOOOO@@@\\    ,@@@OOOOO@OOOOOOOO@@@@@@@OOO[  ,OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "");
			System.out.println("你遇到了一个 NPC，它是 " + guideAngel2.getName());
			System.out.println(guideAngel2.getName() + "：“你就是新来的冒险者？告诉你一点东西，这个游戏地图的范围是 x,y=±5”");
			System.out.println(
					guideAngel2.getName() + "：“同时，请自行脑补地图，本游戏难易度从小到大分别是第一、四、三、二象限！请好好探索每一个坐标！说不定会有什么神奇的事情发生呢...”");
			System.out.println(guideAngel2.getName() + "：“一步登天的后果只有死路一条”");
			System.out.println(guideAngel2.getName() + "：“听我一句劝，你最好不要跑出地图范围，否则到时候会很麻烦的~”");
			System.out.println(guideAngel2.getName() + "：“总之，先收下这些吧！”");
			bag.add(dagger.getName());
			you.setAttack(you.getAttack() + 10);
			System.out.println(
					"获得新武器：" + dagger.getName() + "，攻击力：" + dagger.getAttack() + "，介绍：" + dagger.getIntroduction());
			System.out.println(guideAngel2.getName() + "：“另外告诉你，我在地图上还有三个好姐妹，但是这个该死的作者设定了，你遇到我们当中其中一个后，剩下的都会瞬间去世。”");
			System.out.println(guideAngel2.getName() + "：“虽然遇到你的时间只有几秒不到，但是我真的很开心！”");
			System.out.println(guideAngel2.getName() + "：“我们永远在这里等着你，后会有期！”");
			System.out.println("话音未落， " + guideAngel2.getName() + " 瞬间消失了");
			guideAngel1.setAlive(false);
			guideAngel2.setAlive(false);
			guideAngel3.setAlive(false);
			guideAngel4.setAlive(false);
			System.out.println("你的泪，拉了出来。");
		} else if (x == guideAngel3.getAtX() && y == guideAngel3.getAtY() && guideAngel3.isAlive()) {
			System.out.println(
					"oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooOOOOOOOooooooooooooooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooOOOOOOOOOOOOOOOOooooooooooooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooOOOO@OOOOOOOOOOOOOOOOOOOOoooooooooooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooooooooooooooooooooooooooooooooooooo\r\n"
							+ "oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooO[/OOOOOOOOOOOOOOOOOOOOOOOOOOOO@OOOOoOoooooooooooooooooooooooooooooooooooo\r\n"
							+ "oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooO`/OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@OOOoOOoooooooooooooooooooooooooooooooooo\r\n"
							+ "oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooO`,OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@OOOOOoOOooooooooooooooooooooooooooooooooo\r\n"
							+ "oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooO/.=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@OOOOOOoOOoooooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooO..=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@OOOOOOOoOOooooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooO^..O/,OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@OOOOOOOOoOOoooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooooooooooooO\\OOoooooO`./`     /OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@OOOOOOOOoo/Oooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooooooooooooO  OoooO/,OOO`    @OOOOOOOOOOOOOOOO    \\OOOOOOOOOO@@@OOOOOOOO\\**Ooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooooooooooooO =OooO^=OOOOO   \\OOOOOOOOOOOOOOOOO`   ,/OOOOOOOOOOOO@OOOOOOOO^**\\Ooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooooooooooooO` \\oO/,OOOO^ =O` \\OOOOOOOOOOOOOOOO`  \\=OOOOOOOOOOOooooO@OOOOOO\\..\\Oooooooooooooooooooooooooo\r\n"
							+ "oooooooooooooooooooooooooooooooooooooooooooooooooO .O/   \\O  `=O   \\OOO/` OOOOOO/`  ]  @OOOOOOOOOoOoooooooOOOOOOO..\\Ooooooooooooooooooooooooo\r\n"
							+ "oooooooooooooooooooooooooooooooooooooooooooooooooOO   =O`  ,@O  O@` \\/  ,` \\OO/   =@O^ =@OOOOOOOOOOoooOOoOOooOOOOO`.=Oooooooooooooooooooooooo\r\n"
							+ "oooooooooooooooooooooooooooooooooooooooooooooooooO .            [[[   ,OOO` ,  ,@@ O /  \\OOOO[OOOOOooO[  /oooooooOO^.=Ooooooooooooooooooooooo\r\n"
							+ "oooooooooooooooooooooooooooooooOOOO[[[[`...****,oooooooooOOOOO]]]]`           \\OO/  OO^ ,/... =OOOOOO[  /oooooooooooOO/oooooooooooooooooooooo\r\n"
							+ "oooooooooooOOOOOOOO[[[[[`.   ....*******..******=ooooooooooOOOOOOOOOOOOO@@@@\\]]      ,[  ,O@O` O[`    ]Oooooooooooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooOOOOOOOOOOOO@@OOOOOOOOOOO]]]]]]]]*****=ooooooooooOOOOOOOOOOOOOOOOOOO@@@@OO@O]]    [\\  ,]OO/=Ooooooooooooooooooooooooooooooooooooooo\r\n"
							+ "oooooooooooooOOO/OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/[[[\\]]`.,[\\OOOOOOOOOOOOOOOOOOOOOOOOOOOOO]`   [\\\\.Oooooooooooooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO****^****O`/o\\]/[OOOOOOOOOOOOOOOOOOOOOOOOOOOOO] ,Ooooooooooooooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooOOOOOOOOOOOOOOOOOoo[[[oooo^***=^***=oO/.*^=`**,[OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooooooooooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooOO\\]`..............=`***O****OO`...,^\\`...**...\\/OO\\OOOOOOOOOOOOOOOOOOOOOOOOOOOOooooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooOOO]`........=****O...*O[......`[`   ....=^^....[O]/\\OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoooooooooooooooooooo\r\n"
							+ "oooooooooooooooooooooooooooooooooooooooooooOOO\\]`^                                   .\\O*.*[\\]O[[OOOOOOOOOOOOOOOOOOOOOoooooOOOOOOOOoooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooooooooooooo^            ,]]]               ,]]        ,..**OOOO@OO]]OOOOOOOOOOOOOoOOOOOOOOOO[[[OOoooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooooooooooooo^    ^     /@OOOOO^           ,/\\OOO@\\          =OOOOOOOOOOOOOOOOOOOOOOOOOOooOOOOOOooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooooooooooooo^    ^    =    /` ^              /`.O,^    ,    =..,[OOOOOOOOOOOOOO@OOOOOOOooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooooooooooooo^ ,` ^       ^                 ^   ,`  `   = /  =]]]/OOOOOOOOooooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooooooooooooo^   ,`   =                             ^   ,    =Ooooooooooooooooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooooooooooooo^         `                           =^        =Ooooooooooooooooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooooooooooooo^         ,                           /    `    =Ooooooooooooooooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooooooooooooo^    .    ^\\                         /*    ^    =ooooooooooooooooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooooooooooooo^    ,    ...,                     ,`.=    .    =ooooooooooooooooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooooooooooooo^         .....,.               `[....,   ,.    =ooooooooooooooooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooooooooooooo^            ....../^]     ,=\\........`   ,.    =ooooooooooooooooooooooooooooooooooooooooooo\r\n"
							+ "oooooooooooooooooooooooooooooooooooooooooooooooooo     .    ^  ...\\  ^ ......]. /......    ^.    =ooooooooooooooooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooooOOOoooooo^     .    .  .,^o^      .    =/[`...=    ..    =oooooo\\,OOooooooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooOO[..=ooooooo^       `  =/..,O`..         /../*.*\\`    ..    =ooooooO*..*\\OOooooooooooooooooooooooooooooo\r\n"
							+ "oooooooooooooooooooooooooooooooOO/[*,]`*]Oooooooo^     .,.   [ .[[[.].,    ,.,]`[,[[[[`   \\.     =ooooooO\\^,o]]],[OOooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooOOOooOO@@OO@OOOOooooooo^    /      =         ./ .,         =  `   \\    =OooooooOOo@@oO@@OOooOOOOooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooO/\\OOOOOOOOOOOO@OOOOoOooooooo\\   `        ^      .*,^.,/[`.      ^       =   ,OoooooOOOoOOOOOOOOOOOOOOO/[OOoooooooooooooo\r\n"
							+ "oooooooooooooOO[\\]OO@OOOOOOOOOOOOOOOOOO@OOOoooooo^  /          .       .=[[\\.       = , ^     =  ,OOooooOo@OOOOOOoOOOOOOOOOO@OO\\`[\\Oooooooooo\r\n"
							+ "ooooooooO/[,/OOOOOoooooooooOOOOOOOOOOOOOOOOOoooOO@@/       ,,] ,      .//  =\\.      ^,[]       \\@@OOOooOoO@OOOOOOOOOOOOOoooooooOOOOO]`[OOoooo\r\n"
							+ "ooooO/\\/OOOOooooooo[*****\\ooOOOOoooooOOOOOOOOoO@OOOOO]   `    = .   ./\\o\\` ]O/\\.   ,,^    / ,]/OOOOOOOOOOOOOOOooooOOOOooo/[[[[\\ooooOOOOOO],\\O\r\n"
							+ "oO[,O@OOOOOOooooOOO]...***=oOOOooooooOOOOOOOOOOOOOOOOOOOOO    .,..][..,[    \\. .[`.,`.  ,OOOOOOOOOOOOO@OOOOOOooooooOOOoo********/OOOOOOOOOO@O\r\n"
							+ "OOoooooooooooooooooooO`..*o//OoooooooOOOoOOOOOoOOOOOOOOOOO\\...        .^   ,=.  ...    =OOOOOOOOOOOOOOOOOoOOOoooooooOo\\o**..,/Ooooooooooooooo\r\n"
							+ "oooooooooooooooooooooooO.,o,Oo[***oooOoOooOOooOOOOOOOOOOOOOO`/O  .    =`` ,,,.   ..   =^\\OOOOOOOOOOOOOOOOOoOOoooo[[ooO^O^.,Oooooooooooooooooo\r\n"
							+ "oooooooooooooooooooooooo\\/\\Oo`*****,ooo/,/O],\\OOOOOOOOOOOOO`//,\\.    .^      ^      /\\,O`\\OOOOOOOOOOOOO`,//OOoo*****=oO/o.Ooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooO^Oo`..]]`..*=\\[OO],\\O],\\OOOOOOOOO`=O OO@O]  =       ,   ]@O@O\\,O`\\OOOOOOOOO/./O`,OO[[]***..*=oO=Oooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooO=OOOooooooO`    ,OOO`,OO\\`,\\OOOO^=O /O@@O@^,`  .     / .@@O@@O^,O \\OOOO[.]OO/`/O/`   ]OOooOO\\/O^Oooooooooooooooooooo\r\n"
							+ "oooooooooooooooooooooooooOoooooooooOO]      \\OOO],\\OOO]..,O`/O@]  ,\\`       ``  ,[ .,@OO^=O `,/OOO/`/OO[    ,/OooooooooOO=ooooooooooooooooooo\r\n"
							+ "oooooooooooooooooooooooooooooooooooooO\\       ,\\OOO\\`.[OOO`=OOO@OO\\`, ,`  =@\\.,  ,@OO@OOO`=OO[`,]OOO[    ,]/oooooooooooooOooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooooO`        ,\\OOOOO]/OOO@@o@/ ^ O.]/@\\/^ ^.\\@O\\@OOOO]OOOOO[      \\@@O@Ooooooooooooooooooooooooooooooooo\r\n"
							+ "oooooooooooooooooooooooooooooooooooooooOO@O@\\/`        ,\\OO@OO@^=@^   @@@O`**`   =@@*OO*@O/[         /@OOOOO@Oooooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooooOOOOOOOOOO\\`         =`OO`O@`  ..,\\\\``     =OO^\\O^=      \\OOOOOOOOOOOOO@Ooooooooooooooooooooooooooooooo\r\n"
							+ "oooooooooooooooooooooooooooooooooooooOOOOOOOOOOOOOOOOO\\`,` /,OO,O@    =\\^ .O@    .@O^,O^= ,O]OOOOOOOOOOOOOOOOO@Oooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooooOOOOOOOOOOOOOOOOOOOOOOO^=O^=O^    =.,]^.=     OOO.OO.OOOOOOOOOOOOOOOOOOOOOO@Ooooooooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooooooooO@OOOOOOOOOOOOOOOOOOOOOOO.OO.OO^    =..@...^    =OO`=O^=OOOOOOOOOOOOOOOOOOOOOO@Oooooooooooooooooooooooooooo\r\n"
							+ "oooooooooooooooooooooooooooooooooO@OOOOOOOOOOOOOOOOOOOOOOO^=O^=O@`    `..@...^    =OO^,O^,OOOOOOOOOOOOOOOOOOOOOOO@@Oooooooooooooooooooooooooo\r\n"
							+ "oooooooooooooooooooooooooooooooOOOOOOOOOOOOOOOOOOOOOOOOOOO.OO.=O@.    ^..[...^    .@OO OO.OOOOOOOOOOOOOOOOOOOOOOOOOOOoooooooooooooooooooooooo\r\n"
							+ "ooooooooooooooooooooooooooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^=OO OO/.    `......,    .=OO.=O^=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOooooooooooooooooooooo\r\n"
							+ "");
			System.out.println("你遇到了一个 NPC，它是 " + guideAngel3.getName());
			System.out.println(guideAngel3.getName() + "：“你就是新来的冒险者？告诉你一点东西，这个游戏地图的范围是 x,y=±5”");
			System.out.println(guideAngel3.getName() + "：“听我一句劝，你最好不要跑出地图范围，否则到时候会很麻烦的~”");
			System.out.println(
					guideAngel3.getName() + "：“同时，请自行脑补地图，本游戏难易度从小到大分别是第一、四、三、二象限！请好好探索每一个坐标！说不定会有什么神奇的事情发生呢...”");
			System.out.println(guideAngel3.getName() + "：“一步登天的后果只有死路一条”");
			System.out.println(guideAngel3.getName() + "：“总之，先收下这些吧！”");
			bag.add(dagger.getName());
			you.setAttack(you.getAttack() + 10);
			System.out.println(
					"获得新武器：" + dagger.getName() + "，攻击力：" + dagger.getAttack() + "，介绍：" + dagger.getIntroduction());
			System.out.println(guideAngel3.getName() + "：“另外告诉你，我在地图上还有三个好姐妹，但是这个该死的作者设定了，你遇到我们当中其中一个后，剩下的都会瞬间去世。”");
			System.out.println(guideAngel3.getName() + "：“虽然遇到你的时间只有几秒不到，但是我真的很开心！”");
			System.out.println(guideAngel3.getName() + "：“我们永远在这里等着你，后会有期！”");
			System.out.println("话音未落， " + guideAngel3.getName() + " 瞬间消失了");
			guideAngel1.setAlive(false);
			guideAngel2.setAlive(false);
			guideAngel3.setAlive(false);
			guideAngel4.setAlive(false);
			System.out.println("你的泪，拉了出来。");
		} else if (x == guideAngel4.getAtX() && y == guideAngel4.getAtY() && guideAngel4.isAlive()) {
			System.out.println(
					"                                                                                                                     \r\n"
							+ "                                                                                                                     \r\n"
							+ "                                                                                                                     \r\n"
							+ "                                                                                                                     \r\n"
							+ "                                                                                                                     \r\n"
							+ "                                                                                                                     \r\n"
							+ "                                        ..  ........                                                                 \r\n"
							+ "                                         ...,,/@@\\..                                                                 \r\n"
							+ "                                ..........,/@@@@@^......            ........                                         \r\n"
							+ "                                ...*,@@../@@@@@@@...........        ...\\....                                         \r\n"
							+ "                             .....,@@@@^/[[/@\\[@^.../@@@@]...........,@@@...                                         \r\n"
							+ "                            . .../@@@\\@\\@O\\@@@^.^.,/@@@/[[`,\\`......//\\@@@..                                         \r\n"
							+ "                         .. ....@@@^....\\/OO]..*=`@@@`.*]...*,\\..../^*.@@@@.....                                     \r\n"
							+ "                        .. ....@.[......///\\O]]@@@@\\]]]`[/\\].=O^./@..*^O=@@@....                                     \r\n"
							+ "                        .^=``.=^...`/@@/[..]]]]]........,[\\@\\/`=@`.../.=/O.@^`..                                     \r\n"
							+ "                        .^\\.\\]\\,/O@[......,.=/`... .    ....,//`....=^*=^...=*..                                     \r\n"
							+ "                        .\\,^..=[@\\.... .*........       .....,]/.../@o`=^=O^=^..    ........                         \r\n"
							+ "                        ..\\=\\.=[`........../*.,..        ..,`*..o`,@@`*/^....@..    ..../`..                         \r\n"
							+ "                ...`......=^@,....  ......,..,..*...      ..O.......=/*\\^,...=.......*/`.=^.                         \r\n"
							+ "                ../@.......@/...............=..^.`..      ..,\\..  ..*^.@^....=^...*`/`...*^.                         \r\n"
							+ "            .....@@@@,...../.......=.. ....,^./.=^......    .O..    ..=^\\...*=^..,/`......^.                         \r\n"
							+ "             .../@@@@/@]..=`..../..^......./.=.,@`@`....    .=*.    ..*.=^..*=^O^`.......=^.                         \r\n"
							+ "            ...=@@@/\\*[.[\\/....=`.=^......,^.^.@@=O@`....  ..,........=^.@`.,/\\.........,=`.....                     \r\n"
							+ "            ..,@@@\\*=@`..@.....O../^@.....=.=.=@@@*\\@`=^.........*....*^.=/`...........,\\/......                     \r\n"
							+ "            ..=@@^/.....=`,...=^.=@^@.....=./.=O@\\*.,@*@`.....=./.........@...........=O/]/[.,^..   ....             \r\n"
							+ "            ..=@OO@\\...=^O....=^.@O\\@@`...=,^.@,/**,*=/@@`....@,^.=....*..@..........]/`..../...  ......             \r\n"
							+ "            ..,@O^....,\\/=....=./@O@^,^..*==^*^.@*****@@\\@`..=^/..^...=...@......../`......O......,`^...             \r\n"
							+ "            ...\\OO^..,@*.O...\\O.@O`*@*=^***=^=^*`*****,^*,@..@`^.=...,^..=/......*/......../. ..,/..O...             \r\n"
							+ "            .. ,@Oo\\*^..=^..=@@=O`**=^*/\\/=OO=^`**********=\\/@=.....,^...=^....*=^*........^..//....O...             \r\n"
							+ "            ....=^oO^`..O..,@@@O*****```*\\/@O/^`O@@@@@\\`***\\@^`....,@....=^...*/`.*........@,.......O^..             \r\n"
							+ "        .........,OOOo\\=``=@@@@^,]]/@@@@^,,@\\\\\\*,[[[[[\\@@@\\*@^.O.*=@@.../@^..,/............=^^.....=O^..             \r\n"
							+ "        .*.@`......OOOO\\/[``@@@@,]Oo]*`***,,=^``******=OOooO`/@^^=@@@..@..@./\\*.............\\,o`...=O...             \r\n"
							+ "        ..=@,O`.....=OOOOOO/@@@@\\ooo`******************[/[\\@``@`=@@@@.=^...,.................@\\oo=o\\@.......         \r\n"
							+ "        ...@....,[O/[[[[[[\\O@O//^*`**********************`]`*,//@@@@@^@.....................=//OO/O/\\]]].. ....      \r\n"
							+ "        ...@*`...\\...........=@O^`**************************,@@@@@@.=@^......................O@/[.........,\\\\....... \r\n"
							+ "        ...=^O]/^.*..........=@@,\\,*****`,*@@OO@^********,/O@`,^\\/*,.,`.....................*O@*..............\\`.... \r\n"
							+ "        ...,@@\\^.............=@@,`.\\\\].***,`****,**`*,,/@[,]o*........*`....................,O@^...........,]]`.\\`.. \r\n"
							+ "        ....\\/OO`............=/@]....=]O@[@@@@@@@@@@@OOO@OOoo......,O^*.....................OO@*.......*/\\@[...,\\@.. \r\n"
							+ "            .@/OOOO`......../\\,^/*,\\...,/@\\@\\@@^,@@@^``*O@/O^..../O/......................./OO/*.....=o//...    .... \r\n"
							+ "            ..\\/OOOO].......,@@\\@/**\\`../,\\`=^@@/@@`//*`*O\\o^..,O@/......................,OOO/......,o/,....    ..   \r\n"
							+ "        . .....=\\\\oOOOO]`....@@@@***,\\`@.*/^/\\@@@@@O/`***.@/`*]OO^...................]]OOOO/......../@..             \r\n"
							+ "        ......*..[\\/o`\\OOOoo@@/`*****O=`**=@^=O@@@@@^*****=@OOO@^............,]]OOOOOOOO/..........,O^..             \r\n"
							+ "        . ...,O@@@@@@@\\/\\OO@`@@.****=/O*,*O@@,=@@@^@.*****.@=@O@.....]]/OOOOOOOOOOOo//.............O@*..             \r\n"
							+ "        ...../@@@@@@@@@@@@@/*,``****`****,O@O@`@O@/^******\\=/,,@/OOOOOOOOOOOOooO@/`...............=O/...             \r\n"
							+ "          ../@@@@@@@@@@@@@@\\@^`*,O@@@@@@@]@OO@,@*=/*******,^@\\/@@OOOOOOO@@@[[....................=O@............     \r\n"
							+ "          .....=@@@@@@@@@@@,[`/@@`,,/]]]@@@@@\\]]/@`********=@/=@@@OOOOOOOOOOOOOOOO]]...........*/O@`....             \r\n"
							+ "            .......@@@@@@@/=@O@@\\.,/@@@@@@@@@@\\=\\^***********`[@@/OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\\@]]]]]]............ \r\n"
							+ "               ......@@@@@^[`=@@@@@@@@@@@@@[,*=/@.*`**********`\\@@@@@@@@@@@@OOOOOOOOOOOOOOOOOO\\@@@@@@@@@@@OOOO]..... \r\n"
							+ "               .......,@@@@@@@@@@@@@@@@@@^****/O^*******.*]]O`***,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOO/[[`... \r\n"
							+ "               ......@@@@@@@@@@@@@@@@@/*\\]``**@@***]@@@@OO@OO@O]],`**********[[\\@@@@@@@@@@@@@@@@@@@@@@@/[........... \r\n"
							+ "                .   ..,[@@@@@@@@@@@@`,]]O@@OOO,`**/@@@@@@@@OO[[`*[[***************@@@^......................         \r\n"
							+ "                ............,\\@@@@@@@@@@@@@@@^=/@\\`******************************=@@@^......    ...                  \r\n"
							+ "                            .`]]]/@@@O\\@@@@@@@@@\\/\\@/*/]]*.*`*`,*****************@@@O^...                            \r\n"
							+ "                            .`\\/@@/[[[[[[`\\/@@@@@@@@@@@@\\@@`,@@@@.*=@]]]********/@^.....                             \r\n"
							+ "                            ...@@@@@@@@@@@@@@*]....,[[@@@@@@@@@@@@@@@@@@@@@@@\\]@@@/..                                \r\n"
							+ "                            ......,[[[[[[[[`............................,[[@/[[*.*...                                \r\n"
							+ "                                ....... ..     ........                         ..                                   \r\n"
							+ "                                                                                                                     \r\n"
							+ "                                                                                                                     \r\n"
							+ "");
			System.out.println("你遇到了一个 NPC，它是 " + guideAngel4.getName());
			System.out.println(guideAngel4.getName() + "：“你就是新来的冒险者？告诉你一点东西，这个游戏地图的范围是 x,y=±5”");
			System.out.println(guideAngel4.getName() + "：“听我一句劝，你最好不要跑出地图范围，否则到时候会很麻烦的~”");
			System.out.println(
					guideAngel4.getName() + "：“同时，请自行脑补地图，本游戏难易度从小到大分别是第一、四、三、二象限！请好好探索每一个坐标！说不定会有什么神奇的事情发生呢...”");
			System.out.println(guideAngel4.getName() + "：“一步登天的后果只有死路一条”");
			System.out.println(guideAngel4.getName() + "：“总之，先收下这些吧！”");
			bag.add(dagger.getName());
			you.setAttack(you.getAttack() + 10);
			System.out.println(
					"获得新武器：" + dagger.getName() + "，攻击力：" + dagger.getAttack() + "，介绍：" + dagger.getIntroduction());
			System.out.println(guideAngel4.getName() + "：“另外告诉你，我在地图上还有三个好姐妹，但是这个该死的作者设定了，你遇到我们当中其中一个后，剩下的都会瞬间去世。”");
			System.out.println(guideAngel4.getName() + "：“虽然遇到你的时间只有几秒不到，但是我真的很开心！”");
			System.out.println(guideAngel4.getName() + "：“我们永远在这里等着你，后会有期！”");
			System.out.println("话音未落， " + guideAngel4.getName() + " 瞬间消失了");
			guideAngel1.setAlive(false);
			guideAngel2.setAlive(false);
			guideAngel3.setAlive(false);
			guideAngel4.setAlive(false);
			System.out.println("你的泪，拉了出来。");
		} else if (x == caixukun.getAtX() && y == caixukun.getAtY() && caixukun.isAlive()) {
			// Caixukun is so CAI so death is impossible~ This part is called "CAI-Attack"~
			System.out.println(
					"..........................................................................................*/oooOOOo]]]\\]]]]....................................................................................................\r\n"
							+ "......................................................................................*]oOOOOOOOOOOOOOOOOOOOOo]................................................................................................\r\n"
							+ "...................................................................................*oOOOOOOOOOOOOOOOOOOO@@@OOOOOOo`............................................................................................\r\n"
							+ ".................................................................................,oOOOOOOOOOOOOOOOOOOO@@@@@@@OOOOOOo`..........................................................................................\r\n"
							+ "...............................................................................*oOOOOOOOOOOOOOOO@OO@@@@@@@@@@@OOOOOOO^.........................................................................................\r\n"
							+ "............................................................................../oOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@O@@@OOO`........................................................................................\r\n"
							+ "............................................................................./oOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@OO@@@OOO*.......................................................................................\r\n"
							+ "............................................................................=OOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@OOOOOO@@@OO^.......................................................................................\r\n"
							+ ".........................................................................../OOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@OO@Oo=O@@@Oo.......................................................................................\r\n"
							+ "........................................................................../OOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOO@Oo*O@@@O^.......................................................................................\r\n"
							+ "..........................................................................oOO@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOO`*oOO@O^.......................................................................................\r\n"
							+ "..........................................................................\\O@@@@@@@@@@@@@@@@@@@@@@OOOOOo[[\\OOO/**.=OO@/........................................................................................\r\n"
							+ "...........................................................................O@@@@@@@@@OooOOoOOO@@@Oo`****........**..[`.........................................................................................\r\n"
							+ "............................................................................O@@@@@@@Oo^*/OOo\\/oOOO^,*****......****............................................................................................\r\n"
							+ ".............................................................................O@@@@@@@O`,ooOo\\*=ooo/********...*oo]Oo`..........................................................................................\r\n"
							+ "..............................................................................O@@@@@@@O\\^,[*\\o\\/oooo********....[[\\[...........................................................................................\r\n"
							+ "...............................................................................O@@@@@@@OOo]/oOoooooo^********....*]oO`.........................................................................................\r\n"
							+ "................................................................................,@@@@@@OoooOOOoooooo\\,**********\\OO@@^.........................................................................................\r\n"
							+ "..................................................................................@@@@@o[*oooooooooooo\\`********=oOo^..........................................................***.**..........................\r\n"
							+ ".................................................................................=@@@@Oo**=ooooOOOOOOOooooo]*****\\Oo........................................................*****]..=O^........................\r\n"
							+ ".................................................................................O@@@@@@OooooooOOOOOOOOOOOOoo`**..........................................................******o/[`...*...........*****.......\r\n"
							+ "................................................................................/@@@@@@@@@OooooooOOOOOOOOOOOOOOOo]]*.....................................................*,Oo.****************************.....\r\n"
							+ "........................................................................,]/O@@@@@@@@@@@@@@@@@OooooooOOOO@@@@^...[[.....................................................*.....***`oooooo,**********\\o\\ooo]]*....\r\n"
							+ "..................................................................,O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOO@@@@@@O........................................................*,*****,]ooooooooooo\\`*********`*****[\\[*.\r\n"
							+ "...........................................................]`....=@@@@@@@@@@@@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.......................................................*ooooooooooooooooooooooo`**\\oooo^*********.\r\n"
							+ "......................................................,/@@@@^..../@@@@@@@@@@@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O......................................................./ooooooooooooooo[*******....**[\\o\\`********\r\n"
							+ "....................................................,@@@@@@@^....O@@@@@@@@@@@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O......................................................*ooooooooooOo`*****...****....**ooooooooooo\\\r\n"
							+ "..................................................,@@@@@@@@@`...=@@@@@@@@@@@@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\\...................................................=oOOOOooo^*,\\oo**.....****]ooooooooooooooooo\r\n"
							+ "...............................................,@@@@@@@@@@@O....=@@@@@@@@@@@@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\\.................................................=OOOOOOOoo`******`=oo]**,ooooooooOoooooooooo\r\n"
							+ "..............................................=@@@@@@@@@@@@^....O@@@@@@@@@@@@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O...............................................=OOOOOOOOOoo][=ooooooOOOOOOOOOOOOOOOOOOooooo\r\n"
							+ "..............................................@@@@@@@@@@@@@^...=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`............................................=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoooo\r\n"
							+ "............................................./@@@@@@@@@@@@@....=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O............................................oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooo\r\n"
							+ "............................................=@@@@@@@@@@@@@O....@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O.............................................=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoo\r\n"
							+ "...........................................=@@@@@@@@@@@@@@O...=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@`....,O].......................................,OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`\r\n"
							+ "..........................................,@@@@@@@@@@@@@@@/...=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O.....,@@@@\\......................................=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo*.\r\n"
							+ "..........................................@@@@@@@@@@@@@@@@^...O@@@@@@@@@@@@@@@@@@@@@@@@@@@@O@@@@@@@@@@@@@@@@@@@@@@@@`....,@@@@@@@\\.....................................,OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/...\r\n"
							+ ".........................................O@@@@@@@@@@@@@@@@^...@@@@@@@@@@@@@@@@@@@@@@@@@@@O@@@@@@@@@@@@@@@@@@@@@@@@@`....=@@@@@@@@@@^.....................................=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`....\r\n"
							+ "......................................../@@@@@@@@@@@@@@@@@^..=@@@@@@@@@@@@@@@@@@@@@@@@@O@@@@@@@@@@@@@@@@@@@@@@@@@@^....,@@@@@@@@@@@@`......................................\\O@@OOOOOOOOOOOOOOOOOOOOOOOOo`......\r\n"
							+ "......................................./@@@@@@@@@@@@@@@@@@^..=@@@@@@@@@@@@@@@@@@@@@@@O@@@@@@@@@@@@@@@@@@@@@@@@@@@^....,O@@@@@@@@@@@@O.......................................oO@@OOOOOOOOOOOOOOOOOOOOo`.........\r\n"
							+ "....................................../@@@@@@@@@@@@@@@@@@@^..=@@@@@@@@@@@@@@@@@@@O[\\@@@@@@@@@@@@@@@@@@@@@@@@@@@@O.....O@@@@@@@@@@@@@@^.....................................*]oO..[\\OO@OOOOOOOOOO/[*............\r\n"
							+ "...................................../@@@@@@@@@@@@@@@@@@@@^..=@@@@@@@@@@@@@@@@@@@`..,@@@@@@@@@@@@@@@@@@@@@@@@@@O....,O@@@@@@@@@@@@@@@O...................................***\\oo*...............................\r\n"
							+ "..................................../@@@@@@@@@@@@@@@@@@@@@^..=@@@@@@@@@@@@@@@@@@^.../@@@@@@@@@@@@@@@@@@@@@@@@@@`...*O@@@@@@@@@@@@@@@@@^..................................*****o*...............................\r\n"
							+ "...................................O@@@@@@@@@@@@@@@@@@@@@@^..=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^....O@@@@@@@@@@@@@@@@@@^..................................***=oo*...............................\r\n"
							+ "..................................=@@@@@@@@@@@@@@@@@@@@@@@^..=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^..../@@@@@@@@@@@@@@@@@@@^.................................***oooo*...............................\r\n"
							+ "..................................O@@@@@@@@@@@@@@@@@@@@@@@^..=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O...*/@@@@@@@@@@@@@@@@@@@@...............................******/****...............................\r\n"
							+ "..................................@@@@@@@@@@@@@@@@@@@@@@@@^..=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`*.*=@@@@@@@@@@@@@@@@@@@@^............................***/oo//\\o`***...............................\r\n"
							+ ".................................=@@@@@@@@@@@@@@@@@@@@@@@@^...@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`*.*=O@@@@@@@@@@@@@@@@@@@@^..........................**]oooooooooooo*...............................\r\n"
							+ ".................................=@@@@@@@@@@@@@@@@@@@@@@@@^...@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^...,O@@@@@@@@@@@@@@@@@@@@@^.........................*=oooooooooOoo[*................................\r\n"
							+ "................................./@@@@@@@@@@@@@@@@@@@@@@@@^...@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^...*O@@@@@@@@@@@@@@@@@@@@@@^........................**=ooooooo/[.....................................\r\n"
							+ ".............................,/@@@@@@@@@@@@@@@@@@@@@@@/@@@^...@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O****O@@@@@@@@@@@@@@@@@@@@@@@O*....................****oooooo/`........................................\r\n"
							+ "..........................,O@@@@@@@@@@@@@@@@@@@@@@@@@/=@@@^...@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@`.**/@@@@@@@@@@@@@@@@@@@@@@@@@\\...............,OOo]]`oooooo`...........................................\r\n"
							+ ".........................=@@@@@@@@@@@@@@@@@@@@@@@@@@@.@@@@^...@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^.../@@@@@@@@@@@@@@@@@@@@@@@@@@O............,O@@@OOooooooo^.............................................\r\n"
							+ ".....................,]]@@@@@@@@@@@@@@@@@@@@@@@@@@@@O=@@@@^...@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.***O@@@@@@@@@@@@@@@@@@@@@@@@@@O..........,O@@@@@OOOOOOO@@^.............................................\r\n"
							+ "..................,O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO@@@@^...@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/.../@@@@@@@@@@@@@@@@@@@@@@@@@@@O.....]]/O@@@@@@@@@@@@@@@@O`.............................................\r\n"
							+ "................/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O@@@@@^...@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*..=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@o*.............................................\r\n"
							+ ".............,O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O[.=@@@@@@^...O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/.*./@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`..............................................\r\n"
							+ "............/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@`...,@@@@@@@^...@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^..*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O/................................................\r\n"
							+ "...........O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^.....O@@@@@@@^...@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O...=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`..................................................\r\n"
							+ ".........,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@`......=@@@@@@@@^...@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^...=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/`....................................................\r\n"
							+ "........=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O[.........@@@@@@@@@^...@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@`...@@@@@@@@@O=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@[.......................................................\r\n"
							+ "......./@@@@/[.,\\@@@@@@@@@@@@@@@@@/[............=@@@@@@@@@....@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O....@@@@@@@@@@..O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/..........................................................\r\n"
							+ "......O@O`.....**o@@@@@@@@@@@@[.................O@@@@@@@@@/[[[O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/....@@@@@@@@@@`..,O@@@@@@@@@@@@@@@@@@@@@@@@@@O`............................................................\r\n"
							+ ".....O@/...*\\*.*=O@@@@@O[`......................@@@@@@@@@@....=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^....@@@@@@@@@@@`...\\O@@@@@@@@@@@@@@@@@@@@@O`...............................................................\r\n"
							+ ".....=@^..*oo***O@@@@/.........................=@@@@@@@@@@....=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*.]]/@@@@@@@@@@@@^....,O@@@@@@@@@@@@@@@@/`..................................................................\r\n"
							+ ".........*oO\\../@@@/.........................../@@@@@@@@@@*..=/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O....=@@@@@@@@@@@@@\\......[O@@@@@@@@@O[......................................................................\r\n"
							+ "......***,o@@@@O[..............................@@@@@@@@@@@O]],@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^....=@@@@@@@@@@@@@@@`.......,[\\O[...........................................................................\r\n"
							+ ".....=o**=o`..................................=@@@@@@@@@@@@OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O.....O@@@@@@@@@@@@@@@O.......................................................................................\r\n"
							+ "......=^*=^...................................=@@@@@@@@@@@@OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^....=@@@@@@@@@@@@@@@@O.......................................................................................\r\n"
							+ ".......]***....................................O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^*.../@@@@@@@@@@@@@@@@@.......................................................................................\r\n"
							+ ".......\\oo^.....................................,@@@@@@@@@@/O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O@@@@@@@@@@@@@@@@@@@@@@^......................................................................................\r\n"
							+ ".......=ooooO].......................................=@@@OOoO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOO@@@@@@@@@@@@@@@@@@@@^......................................................................................\r\n"
							+ "........,Oooo/`......................................O@OOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO@@@@@@@@@@@@@@@@@@@@@.......................................................................................\r\n"
							+ "..........=ooOO^.....................................OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@OO@O\\,OOOOOOO@@@@@@@@@@@O`........................................................................................\r\n"
							+ "...............,`...................................=OOOOOOOOOO@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/..............................................................................................\r\n"
							+ ".................................................../OOOOOOOOOOO@OOOOOOOOOOOOO@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@O..............................................................................................\r\n"
							+ "..................................................OOOOOOOOOOOOOOOOOOOOOOOOOOO@OOOOOOOOOOOOOO@OOOOOOOOOOOOOOOOOOO^..............................................................................................\r\n"
							+ ".................................................OOOOOOOOOOOOOOOOOOOOOOOOOOOO@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO..............................................................................................\r\n"
							+ "................................................OOOOOOOOOOOOOOOOOOOOOOOOOOOOO@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`.............................................................................................\r\n"
							+ "...............................................=OOOOOOOOOOOOOOOOOOOOOOOOOOOO@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^.............................................................................................\r\n"
							+ "...............................................OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/.............................................................................................\r\n"
							+ "...............................................OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooOOOOOOOOOOOO*............................................................................................\r\n"
							+ "...............................................OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo............................................................................................\r\n"
							+ "...............................................OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`...........................................................................................\r\n"
							+ "...............................................oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoOOOOOOO...........................................................................................\r\n"
							+ "...............................................OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoOOOOOOOOOOOOOOOO...........................................................................................\r\n"
							+ "...............................................=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooOOOOOOOOOOOOOOOOOOOOOooOOOOOOOO^..........................................................................................\r\n"
							+ "...............................................=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooOoooooOOOOOOOOOOOOOOOOOOOOOOOO^............................................................*.............................\r\n"
							+ "...............................................OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`..........................................................................................\r\n"
							+ "...............................................OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^...........................................................................................\r\n"
							+ "...............................................OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooooOOOOooOOOOOOOOo^...........................................................................................\r\n"
							+ "..............................................=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@OOOOOOOOOOOOOOOOOOOOOOOOOOOoooooooooo...........................................................................................\r\n"
							+ "..............................................=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoooooo^..........................................................................................\r\n"
							+ "..............................................OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOoooooo^..........................................................................................\r\n"
							+ ".............................................=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOoooo^..........................................................................................\r\n"
							+ ".............................................=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@O[OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooo^..........................................................................................\r\n"
							+ "............................................./OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@O..=@OOOOOOOOOOOOOOOOOOOOOOOOOOOOoooOOo......................................................................*...................\r\n"
							+ ".............................................OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@O..,@@OOOOOOOOOOOOOooooOOOOOOOOOOOOOOOo...........................................................................*..............\r\n"
							+ ".............................................OOOOOOOOOOOOOOOOOOOOOOOO@@@OOO@@@O...O@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOooOo..........................................................................................\r\n"
							+ ".............................................OOOOOOOOOOOOOOOOOOOOOOOOO@@OO@@@O^...O@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^.........................................................................................\r\n"
							+ "............................................=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@O`....\\@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^.............................................................***.........................\r\n"
							+ "............................................=OOOOOOOOOOOOOOOOOOOOOOOO@@OOOO@O.....=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^..............................................................**.........................\r\n"
							+ "............................................=OOOOOOOOOOOOOOOOOOOOOOO@@@@@O@@^......OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^.........................................................................................\r\n"
							+ "............................................oOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@O.......OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^.........................................................................................\r\n"
							+ "............................................OOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@^.......OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^.........................................................................................\r\n"
							+ "............................................OOOOOOOOOOOOOOOOOOOOOOOO@@@@@@O........=@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^.........................................................................................\r\n"
							+ "............................................OOOOOOOOOOOOOOOOOOOOOOO@@@@@@@O........=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^.........................................................................................\r\n"
							+ "............................................OOOOOOOOOOOOOOOOOOOOOO@@@@@@@@^.........=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^.........................................................................................\r\n"
							+ "............................................OOOOOOOOOOOOOOOOOOOOOO@@@@@@@@^..........OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`.........................................................................................\r\n"
							+ "............................................OOOOOOOOOOOOOOOOOOOOOOOO@@@@@@...........OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO*...........................................*.............................................\r\n"
							+ "");
			System.out.println("你遇到了一个怪物！它叫 " + caixukun.getName());
			System.out.println("你当前的血量是 " + you.getHp() + "，" + caixukun.getName() + " 的血量是 " + caixukun.getHp());
			System.out.println("你选择——");
			System.out.println("1.攻击 2.逃跑");
			Scanner toCXK = new Scanner(System.in);
			String toCXKR = toCXK.next();
			if (toCXKR.equals("1")) {
				getBag(bag, hasCount);
				Scanner atcxk = new Scanner(System.in);
				String atcxkR = atcxk.next();
				if (atcxkR.equals("1")) {
					System.out.println("你选择了匕首！");
					System.out.println("战斗开始！");
					while (caixukun.getHp() > 0) {
						System.out.println(
								you.getName() + " 使用 匕首，对 " + caixukun.getName() + " 造成了 " + you.getAttack() + " 点伤害！");
						caixukun.setHp(caixukun.getHp() - you.getAttack());
						System.out.println(caixukun.getName() + " 现在的血量是 " + caixukun.getHp());
						System.out.println(caixukun.getName() + " 被打败了！");
						caixukun.setAlive(false);
						System.out.println(caixukun.getName() + " 掉落了武器： " + basketball.getName() + "，攻击力："
								+ basketball.getAttack() + "，介绍：" + basketball.getIntroduction());
						bag.add("篮球");
						you.setAttack(you.getAttack() + 30);
						System.out.println("已加入背包！");
						System.out.println("小贴士：本游戏的'格挡'与其字面意思略有差异，若战斗时格挡成功，则让对象的 HP 提升其格挡值，但不会超过其 HP 最大值。");
					}
				} else {
					x++;
					System.out.println("你没有做出正确的选择，你逃跑了。");
				}
			} else {
				x++;
				System.out.println("你逃跑了。");
			}
		} else if (x == guolaoshi.getAtX() && y == guolaoshi.getAtY() && guolaoshi.isAlive()) {
			System.out.println(
					"oooooooooooooooooooooooooooooooo/oooooooo][`/*ooo^=oooo^[[[[/*,[[,[********************\\[**`*******,***........,**.,...*..... ..........................`.*....,OoO@@O. .O@@O,O\\=OOOO@@@O..=OO\\*..**.***************************`***.*******[***`*,[^**,*,o^*********`**`*******[[oo]^*\\\\*o`o**o,oooooo\\]oo[o\\o*/o^**.***......\r\n"
							+ "oooooooooooooooooooooooooooooooooooo\\o\\o*[oooooooooo\\=oo[ooo**o[**o/****`***********[]`*******,*****......*.....**.]/\\*]`.............................**.......*/o/\\OO\\**.OOOO]\\OO@@O@@@OOOO/\\,\\***************************************************,*****************]*******,***\\\\]*o*=^*=o*,`***,oo*=ooooo*oo*oooo\\*.........\r\n"
							+ "ooOoOoooooooooooooooooooooooooooooooooooo\\],*o`/oooo[[oo[oooo*o/^,o^*****^******[***]*[`*[**..*........*.........,`.^....,*.**..............**\\**`*****.,*.*...=,ooOOO^*`.=OOOo.=O=@@@@@OO@Oo`,O^*******************[*************************************,*,o******`**/****,oooo^*,/*,][[o^o\\]``**oooo]=o\\oooo\\ooooo/\\]`......\r\n"
							+ "OooooooooOooooooooooooooooooooooooooooooo\\`\\ooo^[\\oo[ooo/ooo**oo=oo*,/o[*****^*,*\\`*********..*.*...............*........*............*......*o^,`.**.=\\oo/..o**\\/\\OOo/....=OO^....\\O/OO@@OOOOOO`*.......***.***..****..******************************[[**,`********o]**oo]],/**=`*o/**/[[oo*\\oo*\\/`*/oooooo,/oooooo/o/oooo\\]*.\r\n"
							+ "OOooooooooooooooooooooooooooooooooooooooooooooooo[o^/`oooooo[`/oo[[`******[[[`***,**`*`*****.**..*....................  .....................,oo^,\\/\\/OOOOooOOoOo....*......^=/.,` .[..\\o`[*`*************.*`*****.**.,..*,`,`.*************,[[[^********************,**]*,[*,`*o[[*/[ooooooo`oo,oo\\ooooooooooooooooooooooooooo\r\n"
							+ "ooooOOOOooooooooooooooooooooooooooooooooooooooooo\\oo\\oo]oooooo^**,/[/**,****,o`]*[***`**`,*****...    .. ....................***.........*]=]/OOOOOOOOOOOOOOOOOO\\/^*`,..`**........*...    .......**..*...***********,`...**.**.***********,**,]****^***********`*****^*,o*,o[[o[[[[o`*,oo*]o/ooooo[*oo\\oooo/ooooooooooo]oooooo\r\n"
							+ "OOooooooooooooooooooooooooooooooooooooooo\\*ooooooooooooo[ooo*/\\**oo*]oo`***[/*,*******=`*****...     .... ......***.=\\`*.*]`,]o`.....=^*/ooOOOOOOOOOOOOOOO@OOOOOOOOOOOOOOOO`./*.o,o^........O`.\\*****..**..**.***..****...*...******************`******\\********^****oo*,o/\\]]]]^**=oooo/o*o/*oo\\]oo,ooooooooooo/oooooooooooooo\r\n"
							+ "oOOOoOooooooooooooooooooooooooooooooooooooooooooo]/oo[oo]o/[*oo\\oooooooo[`**=^.*]***^*`****.....   ,........\\]/,oOOo\\oo^***.......*,OOOOO@@O@@@@@@@@@@@@@@@@@@@@@OO@O@@@@@@\\OOOOOOOO,o.],^.*...[`...*.......*****..*..***.****`****[***********=*************\\\\`*=oooo/**,*=,]]`o**o[[[oo\\*oooo\\*=oooooo/oooooooooooooooooooooo\r\n"
							+ "OOoooOoooooooooOooooooooooooooooooooooooooooooooo\\ooooooo]o/oo]/*ooo\\*o`o]/`****][[\\o***........ ..=o`,*,^]O\\=OOOO\\oO/**..**..**/]OOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O@@@O@@@OOOoOOOoO^*..............****,`......***,*.**..******************^*,*`**,*,]]*]oooo`]o^**/oo/oo*/oooooooo]ooo`\\o/ooooooooooooooooooooooooooo\r\n"
							+ "OOOOoOoooOOoooooooooooooooooooooooooooooooooooooooooooooo[oo,oooooo[*oo****,`*/**,\\]****...........OOOO\\]*oOOOOOOO//]/O/**...*/OOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOO^**.**,]*,***]oo*]],]...`....******..*****,**]*=^*`****`***,*.,*,[***oo[*ooooo]=ooo=oooo\\ooooooooo/\\oo\\ooooooooooooooooooooooooooo\r\n"
							+ "oOOOooOooOOOooooooooooooooooooooooooooo\\oooooooooooooooooooo\\,o/\\oooooo`***oo`*,^,`,...............,oOOOoOooOOOO/*=oO\\,o**..,OOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOo]oo\\/`*[[[o\\/\\OOO`[`......,,]**.....*.*********=`***]`*..*/`[`,o,o***=o`o^=ooo,]oooooo/ooooooooo=oooooo^*oooooooooooooooooooooo\r\n"
							+ "OOOoOoooOOOoooOooooooooooooooooooooooooooooooooo\\oooooooooooooo*=ooooo`*/o^,=ooo/***.........*/^oOOOOO@OO@O@O\\\\,OOooOO/`^*oOOOO@@@@@@@@@@@@@@@@@@@@@@@@@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOO^..***.**``.**.*.....................................********.*o*=o*=ooooo\\=/ooooooooooooooooooooooooooooooooooooooooooooooo\r\n"
							+ "OOOoOOOoOooOOoOooooooooooooooooooooooooooooooooooooooooooooooooo=ooo,\\`oo\\oo/,`,]`*.....`....o\\/oOO@@@@@@OOOO\\OO\\OOoOooooOOOOOOO@@@@@@@@@@@@@@@OO@@@@@@@@@@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Oo^*/o\\]/`**.........    . .  ..........*......******`**.********,o`/*ooo`*/ooooooooo]oooo\\ooo/\\ooooo/oooooooooooooooooooOooooOO\r\n"
							+ "OOOOOOOOoOOooOooooOoooooooooooooooooooooooooooooooooooooooooooo//o^*=ooo[`***,^*..*.......*.*/OoOOO@@@@OO@OOOOoOOOOO\\OooO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOO[.*...........   .   ..................***..*...*.*******`**]oo/*ooooo*o/o^*ooo*oooooooooooooooooooooooooooooooooooooooooooo\r\n"
							+ "OoOOOooooooOoOoooooooooooooo/ooooooooooooooooooooo/oooooooo\\oo\\]oo]ooo\\**,/[`******......*O^=OOOOO@@@@@@@@OOOOOoo/\\OOOOO@@@@@@@@@@@@@O@@@OOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO[*....   .. . ... .      ....................***,********`*[[*****\\/,^*o`\\]oooooo,ooooooooooooooooooooooooooooooooooooooOoOoOOoo\r\n"
							+ "OOOOOOooOooooooOoooooooooooooooooooooooooooo]ooo\\oooooooooooooo/,ooo/**,******\\`.......,,/oooOOO@@@@@@O@@@OOOOOOOOOOOOO@@@@@@@@@@OOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O[[..*.**,]`.**.,/Oo/o\\].,`.*.*.]]]]]].*...........**.***,^`*=/[[,*]****o**\\o[oooooooooooooooooooooooooooooooooooooooooooooOoooOOooOOo\r\n"
							+ "OOOOOOOoOOOoOoooooOooooooooooooooooooooooooooooo/oo/ooooooooooooooo/****/`...*..[..*..,oOOOOOO@@@@@@@@@@@O@@@@@OOOO@@@@@@@@@@OOO@@@@@@@@@@@OoooOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOO@@OOOOOOOo/`]Oo]OOOO\\/`.=oo\\/`**/*.]]Ooo`........,[***.*****=\\*,`*]/^,*=oo]o\\]o[\\ooooooooooooooo\\ooooooooooooooooooooooooooOooOOooOOO\r\n"
							+ "@@@@@OOOOOOOOOOOOOoOooOOoOOooooOoOoooooooooooooo/ooo[[ooooooooooo`..**o`****.*...****,oOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOO@@/[/\\/OOOOO@@O@@O@O@@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOO@@@@@OOoO/*OOOOOOOOOOOoo[o/*..........**[oo]*****,]]`\\/o`oooo/oooo]]ooooooooooooooooooooooooooooooooooooooOoooooOOooooOO\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOooOo]oooooooooooooo//]/.*..,`**`..*...,`.***oOOOOOOOO@O@@@@OO@@O@O@@@@@@@@OO@@@OOOO\\/.,/OOOOOO@@@@@@OOO@OO@O@O/OO@@@@OOOO@@@O@OO@@@@@@@@@@@@@@@@@@@@@@@@@OO@@@@@@@OOOO@OOOOo/OOO\\**=`,*]oo`..*` . ..,o]*\\OOO\\]*`/\\*,`=o[\\oo=ooooooo\\,oo]oooooooooooooooooooooooooooooooOoooOooOOooOooO\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOo]O^]]/=`,/**.*..`..*o\\/OOOO@@OO@@@@@@@@@@@@@@@@@@OOO@@@@OOoo`]O[*\\OOOOOOOOOOOOOOOOOOOO@OOOO@OOOOoOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOO/OoooOO@@O]^./o. ... .**,oOOOOoo^,[]o/\\ooooooooooo\\ooooooooooooooooooooooooooooooooooooOOOooOoOOOOOOOO\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOO@@\\/@OOOOo]^*****..,OoOOOO@@@@@@@@@@@@@@@@@@@@@O@@@@OOOOo,]]/`]OOo\\ooo[OOOOOOO@OOOOOO@@OO@@OO^,``.,*,`,/OOOOO@@OO@@@@@@@@@@@@@@@@@@@@OOOOo/oOo^***.....[***o\\oOOOOOOOOOOOOO*.........[*/oOOOOOO\\*/oooooooOoooooooo]ooooooooooooooooooooooooooooooooOooooOoOOOooOOOO\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOO@OOOOOOO^/^*.****=OOOoOOO@@@@@@@@@@@@@@@@@@OO@@@@@@OOOO/oOO\\]/[\\o]\\/O\\OOOOo/OO@@@OO@@OO@@@OOo//...`......[*OOOOo`*.*ooOO@@@@@@@@@@@@@OOooo**..**...........,[[oOOOOOOOOOOOO\\*.*`.......ooo]/\\OOOoooooooooooooooooooo^ooooooooooooooooooOOoooooOoooooOoooOOOOOOOOOOOO\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O@@OOOOOo=`*/o`/\\OOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@OOOOOo\\,[\\/]/OOOOOOOOOooOOOOO@@@OOOOO@@@/OOoO\\*.*......*..........**[OO@@@@@@@@@@@@@@@OOOOOo]`=ooooO/[`.............[\\OOOO*,[^*******...=OOoOoOOOooooooo]oooooooooooooooooooooooooooooooooOOooooooooOoOOOOOoOOOOOOOO\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O@Oo@OO\\o]\\=O\\OOOO@@OOOOO@@@@@@@@@@@@@@@@@@@@@@@OOOO@/***,[]/@OOOOOOoo*oOOO@@@@@OOO\\/@OO/=oo[.,]o*...`*.... .    .*..*OO@@@@@@@@@@@@@@@@@@@@OOOOOOOOO/[[[[**...... ..............*.**\\o]***oOOOoOOOOOo],/ooooooooooooooooooooooooooooooooOooooOOOOoOooooOoooOoOOOOOOOO\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOO/Oo^=o\\/\\OO@@O@@@@@@@@@@@@@@@@@@@@@@@@@@OOoo/o]/o//OOO@OOOO[,oOooOO@@@@@@@OOOOooOo[*]]oO*..***/............*`.,O@@@@@@@@@@@@@@@@@@@@@@@OOOO]]OOoOOOOOOOO/]..]]]`...........*...***.,\\ooOOOOOOOOOoo/\\oooooooooooooooooooooooooooooooooooooOoOOoooOOoOOOOOOOOoOOOOO\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOO@@@@@@@@@@@@O@@@@@@@OOOOo[o`ooOOO@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOoo\\o]oOOOO/[o\\`/OoOOOOO@@@@@@@@@OOoOOO[\\]OOO*[.*,**`*.........*,/..]@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOO/[\\oOOOO@@@@OOOO@@@OO]`.*.....**\\O^*=`]^.,OOoO@OOOOOOOOooOoooooooooooooooooooooooooooooooooOOOoOOOOOoooOOOOOOOOOOO\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O@OOOOOOOO@@@@@@@@@@@@O@@@OOOOOOOOooO@@@@OO@@@@@OOO@@@@@@@OO@@OOOOOOo]`*/OOOOO/**]/OOOOOOOOO@@@@@@@@@@@OO@OOOoOO[,/`*[**...........*...,/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOo`]]]]]*..`*`=oOoOOOOOO\\`***.**\\o/\\*oO\\..]\\/OOOOOOO@@@@@@@@@@@@@@@@@@@@@@OO@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOO@@@@@@@@@@@@@@@O@@OOoOOOOOOO@OO@@@@@@@@@@@@@@@@@@OOOOoOOOoOOOOoOOo]oOOOOOO@OOOOO@@@@@O@@@@OO@OOOOOOOOOO/,**`................/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOO\\****`=OOOOOO@OO\\oOoOOOOooOoo,oo`*\\*oOOo[O]oO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOO@O@OOOO@@@@@OOOO@@@OO@@@@@@@@OOOOOOOOoooOOoOOo]OOOO@@@@@@OOOO@@@O@@@@@@@@O@O@@OOOOoO`][.................,O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOooooo`**,[OOO@@@@@@@OOOOOOOOoo`,o[OOoO\\/o/[OoO@@@@@@OOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOO@@@OOO@@OO@@@@@@@O@@@OOOOOOOO@@@O@OOOOOOOooOOOOOOOO@@@@@@@@@@@OOOOOO@@@@@@@OO@@@@@@OOO//.*.....**`..**...***/OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOo\\`*`**,\\OO@@@@@@@@@@OOOOOOOO\\,o`OOOOOOO/OOOOO@OOO@OOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOO@O@O@OO@@@@OOO@@@@O@@@@@@O@@OOOOO@@OOOOOOOOOO@@@@O@OOO@@@@@@@@@@@O@OOOO@@@@@@OOOOOo/*....,o/[*.****,^.*oOOOO@@@@@@@@OOO@@OOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOO\\ooo]****\\OO@@@@@@@@@@OOOOOOOooOOOO\\OOOOOOOOO@OOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O@OO@OOOOO@@@@@@@@@OO@@@@OOO@OOOOOOOOOOOOOOO@@@@@OOOOO@@@@@@@@@@@@@@@@@@@@O@OO@@@OOOo^...*/o/*.*,***=oo/=oOOOOOOO@@OOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOoO\\`*,\\O@@@@@@@@@@OOOOoOOOOOO@O@@@@@@OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO@@OO@OOO@@@@OO@OOOOOOOOOOOOO@OOOOOOOOOOOOOOO@@OOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O/[*...//****,**]ooOo]oOOOOOOOOOOOOOOOOOOOOOOooOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOoooooO@@@@@@OOOOOOo,OO@@@OO@@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "OOO@@@O@@OOO@OOOO@@@OOOOOOOOOOOOOOOO@OOOO@OOOOOOOOOOOOOO@@OOOOOOOOoOOOOO@@@@@@@OO@@@OOOOOOOOOOOOOO@OOOO@@@@@@@@@@@@@@@@@@@OO@@@@@OOOOo..*******,`*/OOOOooooOOOOOOOOOOOOOOOOOOOOOoooooooooOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOoOOOOOOOOOOOOO@@@@@OOOOOOOOOO@@OO@@O@@@@@@OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@OOOOOOOOO@@@@@@@@@@@@@@@OOOOOOOOOOOOOO@OOO@@@@@@@@@@@@@@@@@@@@OOO@@@O@@@O`*.*..**=]o^oOOOOOooOOOOOOOOOOOOOOOOOOooooooooo\\ooo\\*/oooOOoOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOO@O@@@OOOOOOOOOO@@@OOO@@@@@@@@@OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo/\\OOOOOOOOOOO@@@@@O@@@@@@@@@OOOOOOOOOOOOOOO@@OOOO@@@@@@@@@@@@@@@@@@@@@@OOOOO@@@@O^**,`*=oooOoOoOOOOo`=oOOOOOOOOOOooOoOoooOooooooo***`*oo/ooooOooOOO@@@@@@@@@@@@@@@@@@@@@OO@@@@@OOOOOOOOOOOOOOOOOOOOOOOOO@@OO@OOOOoOOOOOOOOOOOOOOOOOOOO@OOOO@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "oOOoooooooOoooOOooOooOOooooooooooooooooooooooo`=\\oOoOOOOOOOO@@@@@@@@@@@@@OOOOOO@@@@OOOOOO@OOOO@@@@@@@@@@@@@@@@@@@@@@@OOOOO@@OOO/,/o^`]OOOOOOOOOOOo*/ooOOOOOOOOOOooooOoooooooo^,o[`***=o*]]/o/`=oOOOO@@@@@@@@@@@@@@@@@@@@@@OOO@@OO@OOOOOOOOOOOOOOOOOOOOOO@O@O@@@O@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@OOOOOOOOO@O@O\r\n"
							+ "oOooooooooooooooooooooooooooooooooooooooooooooo,oo/OOOO@@O@@@@@@@@@@@@@OO@@@@OO@@OOOOO@@@@@@@@@@@@@@@@@OOO@@@@@@@@@OOOOOOO@OO`,ooo/OOO@OOOOOOOoo],oooooooOOOOoooooooooooo**,,[**************[[\\oOOOO@@@@@@@@@@@@@@@@@@@@@@@OOOOO@@@OOOOOO@@OOOOOOOOO@OO@@@O@@@@@@OOOOOOooOOoooo^oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "oooooooooooooooooooooooo*oooo\\o[oo`[/^=o*oo*\\/`,/oOOOOO@@@@@@@@@@@@O@@@@@@@@@@OOOOOO@@@@@@@@@@@@@@@@@OOOO@@@OO@@@@OOOOOOOOO\\oooOO@@@OOOOOOOOOoooooOooooooooooooooooo\\oo[*********************[\\oooOOO@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOO@@OOOO@@OOOOO@O@@@O@O@@@@@@@@OOooooooo/oo^o^=oooooooooooooooooooooooOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "ooooo/oooooooooooooo]**,*oo*,o*****o/,o*`*]oo*=/oOOOOOO@@@@@@@OOO@@@@@@@@@@@@OOOOOO@O@@@@@@@@@@@@@OOOOOOOOOOO@@@@OOOOOOOooooOO@@@O@OOOOOOOOOooOooooooooooooo[[[[************..*.....**..********\\oOOOOO@@@@@@@@@@@@@@@@@@@@@@OOOO@OOOO@@@@OO@@OOOOO@O@OO@@@@@@@@@@Oooooo*,`*^\\\\=^=o=oooooooooooooooooooooooooooooooOOOOOOOOOOOO\r\n"
							+ "ooooooooooooooooo`,]/o],^/oo^=o*\\o**,/o[**,*,/=oooOOOOO@@@@@@@@O@@@@@@@@@OOOOOOOOO@@@@@@@@@@@@@@OOOOOOOOOOOOO@@@OOOOOO/\\OOOO@@@@@@OOOOOOOOoooOooooooo***[`**,[******************..*****.*********ooOOOOOOOO@@@@@@@@@@@@@@@@@@@@OOOOOOOO@@@@@O@@OOOOOOOO@@@@@@@@@@@OOoo^***,`**`,^****,o^/**o*\\ooooooooooooooooooooooooooooooOoo\r\n"
							+ "ooooooooo]/o]],[o/[\\=o*,/[[*o*,**[**,o****,*o,oooOOooO@OO@@@@O@@@@@OOOOOOO@OO@OO@@@@@@@@@OOOO@@@@OOOOOOOOOOOOOOOOOOOoOOOO@@@@@@@@OOOOOOOOOoooooooo\\*=o**]`***********.****......******...*********=oooOOOOOOOOOO@@@@@@@@@@@@@@@@OO@OOOOO@@@@@OOOOOOOOOO@@@@@@@@@@OOooo`******o**/***`**=****`**oo^\\o`ooo*=ooooo/\\oooooooooooooo\r\n"
							+ "oo/oo]]]o\\/o^*oo/oo`oooo*****`*,*,`****,***oooooOOOoOO@@@@@@@@@@O@@@@@@OOOO@OOO@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOooOOOO@@@@@@@@OOO@@OOOOOooooo`*\\ooo*\\o/**********...*******...*...*.*...**..****,[ooOOOOOOOOOOOOOO@@@@@@@@@@@@@@@OO@@O@OOOOO@OOOOOOOOOOO@@@@@@@@OO[/o***********]*,****o^*=**,[**\\**,[\\,o`*\\oo\\oo/oooooooooooo\r\n"
							+ "oooooooooooo^*oo****`*]**,`******,`*****,`/oo^oOoOOOOOO@@@@@@@@@@@@@@@@OO@@@O@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@OOOOOOOOoOoooo`,o`*/*******************.......*...........*.*.****\\ooooooOOOOOOOOOOO@@@@@@@@@@@@@@OO@OO@@O@@@@@OOOOOOOOOO@@@@@@@O/ooo********,]********^*********o`*,o^***ooo`*/oo*ooooooooooo\r\n"
							+ "]ooooo]]oooo=o^*`*,\\^****************=`**=oo\\oOOOOOOO@@@@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOoOOOOOOOO@@@@@@@@@@OOOOOOOOoOoooooo]****[`*********...**..*.......*.....*.......********\\`=oooOoOOOOOOOOO@@@@@@@@OO@@OOO@OOO@@@@O@@@O@OOOOO@@@@@@@@OO/oo/**********`*,*****************`*o`*=o^****ooooooooooooooo\r\n"
							+ "\\ooooooooo^*=o^*\\`*******************=^=oo/*oOOoOOOO@@@@@@@OO@@@@@@@@@O@@@@@O@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOoOOOO@@@@@@@@O@OOOOOOoooooo/***=[*[`****..*..................*....**...*..***********/ooooooOOOOOOOO@@@@@@@@@@OO@@@O@@OO@@@@@@@@@@OOOO@@@@@@@@OOooo^*.***************************oo**,/****oo`*\\oooo/oo[ooooo\r\n"
							+ "oo*=ooooo/[*^*********\\`************.,**/o]/OoOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOoOOOOoOOOooOOOO@O@@@@@@@OOOOOOOOOooooo`o^o`********..........*.........**..*.......*.************[[oooooOOOOOOOO@@@@@@@@@@@OO@OO@@O@@O@@@@@@@@OO@@@@@@@@@Ooo\\**.***.*********************^*^*********,o\\]*/`*ooooooooooo\r\n"
							+ "oo*=o`[oo`*o***=****`**************=*=*=ooooOoOOOOO@@@@@@@@@@@@@@@@@@@@@@@OO@@@@@@@@@@@@@@OOOOOOOOOOOooooOOoOOOooOOOOOO@@@@@@@@OOOOOOOOoooo/oo\\]]`********.................**.*****....**....*..*********,oooooOoOOOOOOO@@@@@@@@@@@OOOOO@@O@O@@@@@@@@OOO@@@@@@@@OOo`**.***.*********************************/o/\\ooo*ooooo^=oooo\r\n"
							+ "oooo\\o*=*********`******************\\.=o\\oooOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOooooOOoOoooOOOOO@@@@@@@@@OOOOOOooooooo[[ooo*oo*******................***.**..*.............**..*******\\o^=oooOOOOOOO@@@@@@@@@@@@@@@@@@@@OO@@@@@@@@@@@@@@@@@OOo`***.***************************************=o/**=o**o*ooo\\o\r\n"
							+ "****/o*o****************************[,o^,]ooOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOooOoooooOOo/\\oooOOOO@@@@@@@@@OOOOOOOooooo^[`,oo[ooo`*`*****........***.*****.**...................**********`=ooooOOOOOOO@@@@@@@@@@@@O@@@@O@@@OO@@@@@@@@O@@@@@@@O`,`***.**.*****.*.********************************[=o*oo*ooooo\r\n"
							+ "*oo*/`*o****\\***o`*,***************,=oo^,ooOOoOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOoOooo[oOOooo[ooOOOOO@@@@@@@@@OOOOooooo/***\\`*,]]ooo^********....*************.....................***.*********ooooOOOOOOOO@@@@@@@@@@@O@@@O@@@@@O@@@@@@@@@@@@@@O/*************.***.******************************=^,o`ooooooo\\=\r\n"
							+ "o**o********/**********************=\\`\\^oooOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOoooooo*^*oo^***^\\oooOOO@@@@@@@@@OOOooooo/*******=o[oooo/********..*`*****..****....................***....********oooooooOOOOO@@@@@@@@@@O@@@@@@@@@OOOO@@@@O@@@@@@Oo^****...*******..******.*********************,******=\\*\\o^**o*,\r\n"
							+ "*********************************`**[,^*ooOOOO@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOoooo/[[***[`*..*`=ooOOO@@@@@@@@@OOooOoooo[[*.*.**o`*,o`****.**...*.*^****...**...................*****************=^]]oooooOOOOOO@@@@@@@@@OOOO@@@@@@@OOO@@@@OO@@@OO/^*`*****...******..*************************,`*\\^**,,[*,/^*o*o`\r\n"
							+ "****`**,*****************************=^ooOOOOOOO@@@@@@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOoo^*******o`****ooooOOO@@@@@@@@@OOoooo/[[o`******.*****[`**.......*****......................*****]]]**********=o^=^=oooooooOOOOOO@@@@@@@@@@O@OOO@@@@@@@OO@@@O@@@@Ooo^****.*..**.....******.***.****************************,^**\\,o*\r\n"
							+ "]******,******************************,ooOOOoOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOoooo*******=\\\\*]oOoOOOOO@@@@@@@@@@Ooo*******...**************.*.....*..........................***.*=[[ooooo\\*****,[***`*oo/oooOOOOOOO@@@@@@@@@@OOOO@@@@@@OO@@@@@@@@OOO[**...*........***.*****.***************************,/***\\/****\r\n"
							+ "OOOOOOOoOOOooooo]]]]]]]]`]`,]**]*,****oooOOOoOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOooo`*****/oOoOOOOOOO@@@@@@@@@@@@@OOo]^****....**.***.......*.......................................*********/o\\]***********[ooooOOOOOOO@@@@@@@@@OOO@@@@@@@@OOO@@@@@@OOoo.........*......**.*..********************************,`**,*`*\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO,oOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOooo`o^=o]`ooOOOOOOOOOOO@@@@@@@@@@@@OOOOOOo]`***/*.**..*..**...*....................................*.*]`*oOOOOOOOO\\ooo*********/oooOOOOO@@@@@@@@@@@OOO@@@@O@@O@@@@@@@@OOo***............*.....*..*..************.********************[**\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooOOOoOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOooooo`]*`****[\\ooOOOOOOOOOO@@@@@@@@@@@OO@OOOOOOOooOoo`********.............................,****.*`**=oooOOOOOOOOOOOOOOOOOOo**.*****=ooOOOOO@@@@@@@@@@@OO@@@@O@@OOO@@@@@@@OOo/*],]]]]]`*,`**`**,`*`*******]]*,`,]]]]]*,`]]]]]]]*]o]]]]]]oo]\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOoo`******..***ooooOoooOOOOOOOOOO@@@@@@OO@@@@@OOOOOOo`********.......................***..=oooooo`=ooooOOOOOOOOOOOOOOOOOOOOOooo*,`**ooooOOOOOO@@@@@@@OOOO@@@@OO@@OO@@@@@@OOOOOoOOOOoooOOoOOooOooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOo/\\o`****..******=o`***[[*=oooOOOOOOOOOOOOOOOOOOOOOooo/**,ooo*......................*`****,ooOooOoO\\OOOOOOOOOOOOOOOOOOOOOOOOOOOo/*=**,ooOOOOOOO@@@@@@OOO@@@@@@@@@@@@@@@@@@OOOoOoooOoooOoOOOOOOOOOooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOoo\\********......**********,[*oooooooOOOOOOOOOOOOOOOOooo^***,oo^*....................*]***..**\\o/\\ooOoOOOOOOOOOOOoooooooooooooOooOo\\*****oooOOOOO@@@@@@OOOO@@@@O@@@@@@@@@@@OOOOooooooooooOooooOOOOoooOOOoOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OOOOOOOOOOOOOOOooOOOooOOOOOOOOooooooooOOOoOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOooo/*********..........*.**.******,[[[oooooOOOOOOOOOOOooo/o]]ooo^.....................*`.....****,]ooooOOooOOOOooo\\/**********\\[o\\*****.**=*/oOOOO@@@@@@OO@O@@@@O@@@@@@@@@@OOO/oooooOooOOOOoOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "OoOOOoOooooooooooooooooooooOOOo^\\]oo/ooOOOoOOOoOO@O@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOo/[************.........................***[[[oOOOOOOOOOOoooooOOo^**..................**]*=]]oooooooOOOOOooooo[o****.*....*****************=o\\ooOO@@@@@OOOOO@@@@@@@@@@@@@@@@OOoo]oOoooooOoooOOOOOoOOOOOOOOOOOOOoOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "oooOOOOooooooooooooooooooooooOOOoo`\\=oooOOoooOOOOO@O@@@@@@@@@@@@@@@@@@@@@@@@@@Ooo****..***************...*.*]]]]]/\\/Oo]/\\]/,\\,oooOOOOOOOOOOooOOOOoo**................**\\\\oooooooooOOOOOooo[*****.****............*****.******,o/oOOO@@@@@OOOO@@@@@@@@@@@@@@@@OOOoo`ooooo,ooooOOOOOooooooooOOOooOoOooOoOOooOoOOOOOOOOOOOOOOOOOOO\r\n"
							+ "ooooOOoooooooooooooooooooo\\oooOOoo^*/ooooooOOOOOOOO@OO@@@@@@@@@@@@@@@@@@@@@@@Oooo/****.*********]]]]**ooOOOOOOOOOOOOOOOoOOOOOOOOOOOOoOOOOOOOOOOOOoo^*.................***oooOOOOoooooo\\]`*.................................****\\oooO@@@@@OOOO@@@@@@@@@@@@@@@@OOo[\\oo*****oooOOooo**o/^*[=o\\oOOoooooooooooooo/ooooOoOOOOOOOOOOOO\r\n"
							+ "OoooOOoooooooooOOOOOOoOOOOOOooOOOo/o*=ooooooOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@Oo*****.*************=OOOOO@OO@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOO@@OOOOOOO],*................,oooOOOOOoooooooo***.............................*.******,oooO@@@@@OOOO@@@@@@@@@@@@@@@@OO\\***[o****/\\OOo*,***,**[\\o^**,OOoooo/[=oooooooooooooooOOOOOoooOO\r\n"
							+ "ooooOOoooooOOOOOOOOOoOoooOooooooOOooo^*Ooo^oOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@Oo^**..***`******...***=[\\/[ooooOOOOOoo=O/\\oooOOOOOOOO@@@O@OOO@@OOOooooooOOooo]o\\]]]]]oOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@OO\\/]]*.....*****.**..***=/oO@@@@@OOOO@@@@@@@@@@@@@@@OOOO/.***`*`=^OOo`,]**[**,*/***o\\oO^o]`/oooooooOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "oooOOOooooOOOOoooooo=oooo]o/oooooOoooOooo*ooOOoOOOoOOOOOOOO@@@@@@@@@@@@@@@@@@O\\***..************.***.]/\\*,oOo\\`[*****.*******/\\oOoOOOOOOOOOO@OOOooo**]*******......***[[[ooOOOO@@@@@@@@@@@@@@@@O@OO@@@@@@O@@@@OO\\]oo\\***....**..**oO@@@@@@@OO@@@@@@@@@@@@@@@OOOOo^****,^ooO^***********=o/o\\o/\\O\\,ooooOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
							+ "ooOOOOoooOOOooooooo]oooo,oo*=o\\*oo^oOOOOooooOooOOOooOOOOOOOO@@@@@@@@@@@@@@@@OO**.......**..*....*.*********]/o/[oOOOO]]]o**/ooooOOOOOOOOOOOOOO@OOo\\***...............**,,/OOOOOOOOOOooo[***........****,[[`**,ooOOOOoo`**......**=^O@@@@@@@@@@@@@@@@@@@@@@OOOoOoooo\\`***\\Oo****=`******=*\\*o^o=o=ooooOOOOOoooooooooooOOOOOOOOOO\r\n"
							+ "OOOOOooooOOoooooooooooo/ooo`[*,oOo*[/ooooo/[OO/ooOOOOOOOOOOO@@@@@@@@@@@@@@@@OO`*.*.....*...*...*..........*****.*****o[\\[o/*oooooOOOOOOOOOOOOOOOOOOOo*.............**/oOOOOOOOOOOOOOoo\\**.................,*,`**...*....*.*....***^O@@@@@@@@@@@@@@@@@@@@@@OOo/o`\\oooo^***ooo*****[`*****//*,^/OOooooOOOo]oooooooooooooooooOOOOO\r\n"
							+ "OOOOooooOOooooooooooooo/\\/o^,ooOo*=[=o*ooOooOOOooooOOOOOOOO@@@@@@@@@@@@@@@@@Oo^*....*.**.*.......................******/ooooooOOOOOOOOOooooooooo\\oOOOo**.=*...****/OOOOOOooooOOOOOOOOOOoo]****.....*,]`\\o]o^*****................*=O@@@@@@@@@@@@@@@@@@@@@OOooOO/[`**ooo**oooo`*******,*,*ooooOOo^=]oOooooooooooooooooOoooOOOOOO\r\n"
							+ "OoOoooooOOOooooooooo*=oo[**/OOOo***]o`*,\\oooooooOOOOOOOoOOOO@@@@@@@@@@O@@@@@Oo^....***.......................**..***,oooooooooOOOOOOoo/o*,****.****,\\o*..*\\...***.*......****\\o\\oOoOOOOooOooooooo[\\[***,*.*.......................,O@@@@@@@@@@@@@@@@@@@OOOOoO\\oOo,`**ooo*,ooo\\*,`****,o`*/oOoOo\\o]]ooooooooo\\ooooooooOoOOOOOOOo\r\n"
							+ "oooooooooOOOooooo\\/o\\o*oooOOOO^*o`*o***,*oOoo]ooOOOOOOOOOOO@OOOOO@OOOOO@@@@@Oo**.****..................**........*..*[[[=o/ooooOooo`**,/**..**...***.*`...,...*..............****,o*oooooooooo`*******...*........................*O@@@@@@@@@@@@@@@@@@OOOOOOOo**oo*,**=\\o^,oooOooo]]/ooooooOo/oooooooo=oooooooo\\\\ooooooOOOOOOOO\r\n"
							+ "oooooooooOOOOooooooooOOOoOOo^,^*,/\\/[****oooo^**/\\OoOoooOOOOOOOOOOOOOO@@@@@OOo**..**................................*..*********`********.........**..**..*...*....*..............***,`*[`*********................................=@@@@@@@@@@@@@@@@@@OOOOOoo***/******\\oo**,ooooooo\\oooooo[^*oo,oo`oOo`oo\\ooooooooooOOOOOoooOO\r\n"
							+ "oooooooooooOOOOOooOoOOOooooo`=^,,o`*******oo\\^*ooooo[o\\/OOOOOO@OOOOOO@@OOOOOO^*...*....................................*********................*..*.**.......*.......................*********.**.................................=@@@@@@@@@@@@@@@@@@OOOOo^*=^,o***,*^*=o^=`**\\^**[/[****,[**,]`*=ooOOOoooooooooOOOOOOOOoooooO\r\n"
							+ "OOOOOOOoo\\=ooooOooooooo\\*****=o**,oo,o***=oo`\\**/**oo*ooOOOOOOOOOOOO@@OOOOOOO^****......................................................................................................****.......................................,O@@@@@@@@@@@@@@@OOOOoo`************=`,o****=*************,]o**,/o=OOOOOOOOOOOOOOOOooOoooOOo\r\n"
							+ "OOOOOOOOOOOooooo\\/oo]]]]o/[[\\/*,=o**=^****,``,^**]]*[**\\OOOOOOOOOOO@@OOooOOOO/****.......................................................*..***..........................................*..........................................\\@@@@@@@@@@@@@@OOOoOO^**=^**********o*,^***^o^******`***`*/ooo\\]]/`o\\OOOOOOoOOOoooooooooOOO\r\n"
							+ "OooooooooOOOoooooo**[[\\],[[**o**\\`*,=^*****o*****,*,[*,OOOOOOOOOOo@@OO^=oooOO\\****....................................................*,*ooo****..........................****.....................................................*\\@@@@@@@@@@@@@@OOOooo**,o************,/****=oo********,ooooooooooOOo,ooooo^oooooooooOOOOOOO\r\n"
							+ "ooooooooooOOooooooo/oo]*,oo`,oo`*[`*******,\\^*******[[\\oOOOOOoOO=O@@OO,\\Oooooo***....................................................,ooOOOO*****.......................***ooo`*..................................................`.=@@@@@@@@@@@@@@OOOoo^**o`****[*.******[****,oo******ooooooooooooooooO]o*/o\\]oooooooooooooOO\r\n"
							+ "oooooooooooOo/[`ooo]o/ooooooooooooo//^****/`**********=oOO@OoooO=O@OOO/=ooooo^****.................................................*,oOOOOOo^****.......................***ooOO\\]*................................................*.o@@@@@@@@@@@@@OOOOo\\*o*=*******..*****=/***=o\\]****oooo`**\\`*=/[oo[oOO\\oo/oooooooooooooOOoO\r\n"
							+ "ooooooooooooo^=o[ooo]ooooooooooooooooo]//o^**,`******=OoOOOOOooO/@@OOOO****\\o^***...*............................................*,oOOOOOOO\\oo**.*.....................*****oOOOo`**................................................=O@@@@@@@@@@@OOOO\\]=o**=`,\\*******o*=^=^**=*******=oo[*******,\\**=oooOo^oo\\,oooooOoOOOOOOOO\r\n"
							+ "ooooooooooOoooo/,oooooOoo^]/`*******[oo/ooo*[oo*`*/`**ooOOOOOooOO@OOOOo****=o^****..............................................*,oOOOOOOooo\\`***...................*********oOOOo\\*................................................=O@@@@@@@@@@@OOOOoo*****`=/********,*ooo^***o^**`*oo**********[*,\\/ooOo/=oooooooOOOOOOOOOOO\r\n"
							+ "ooooooOOOOoooooo/,oOOoo`**=^****`***`,\\/o*,ooo[\\o^*oooooOOOO=OOO@@OO\\Oo,`.]]o^***..............................................**/OOOOOOo/[*****.........................**..*=OOoo**...............................................=O@O@@@@@@@@@OOOOoo=**********.*****=oo^ooo*\\]**[`o^*************,o*OOoo=o^]oooOOOOOOOOOOOO\r\n"
							+ "OOOOOoOOOoooo/**oooo[`**********`***=ooo`**/,`*[[*=^oo/oOOOOooOO@@OOooo^\\]********............................................*,oOOOOOO^***...................................*OOOoo**..............................................O@@@@@@@@@@@OOOO/o*,[[`****..*******oo**\\o^*ooo``oo**************oooooo*ooooooOOOooooOooOOO\r\n"
							+ "OOOoOooo]]ooo`***oo\\*,o[*********=^ooo[*`*****[***,`,[ooooooOOOOOOOOoo^,**oo***.*............................................./oOOoOOOO^*........*............................./Oooo]**.............................................O@@@@@@@@@@@OOOOo^****.*********.*/**********o^*`*ooo**=ooooooooOoooo*/o*o^o/OOo/ooooooOOOO\r\n"
							+ "oooooo]/oo`*/[`]`=ooooo`******]]/ooo[`**************=oOoooOooOOOOOOOo/o\\oo]]***....**.......................................*oooooooOOO\\*.....................................=ooooooo`............................................=O@@@@@@@@@@@OOOo,\\****..**.***.*,oo*******.*^***`*\\\\o/^=o`*\\ooooooo`ooo`/ooooOOooooooooOOOO\r\n"
							+ "OOOOoOOoooOOo\\]/o\\oooooooo\\`/o\\/o]/[**************[[^**\\oo\\/OOOOOOOOOoooo`/o*.*............................................,ooo\\**=ooooOOo`*oo\\]`....*................,]`*..*ooo***[\\oo]*..........................................O@@@@@@@@@@OOOO^*/***`.***=^.*,[**,[*****..*********=o`**[[**[[[[****`*]]]]]]]oOO]oOooooOOOO\r\n"
							+ "OOOOOooooooooooooo*[ooooooo**oo*******************,\\[*/oooooOOOO\\OOOoooOOOOO\\............**...............................]oo`**`**,*oooOOOOOooooo\\**..............*/o/***/OOo/`*[***,ooo*.......... .............................=@@@@@@@@@@@OOO\\o^*=^/^*,]^*,***,****.***..***********\\o^*`*,]oooo\\]o/ooo\\oo/*ooOOOOOOOOOOOOO\r\n"
							+ "ooo\\oooo/ooooooooooo]***********`***********.***..*``**,/,o=OooO\\=OOoo^*[o/\\^..........**................................=o/*********/ooooOOOOOOOOooo^**..........[***=oOOOOo/**.*******oo*...........  ..... ..................../@@@@@@@@@OOOo/[o*`*/`*[**,`.****...*.**..**************ooo,oo[oooooooooooOOOOo\\/oOOOOOOOOOOO\r\n"
							+ "oo*=o^*oo*,/***[\\ooooo\\*********.**......                 . .,[[[ooOo\\Ooooooo`........**................................=o`*****`******\\oooooooOOOOOOoo]****..*,/oooOOOOoo`[******...**.*,/*.....................................,O@@@@@@@@OOO/`,/o`****....**..**.*..***...**.***..****\\*oooo`,******[[o[\\ooOOooooooooOOOOOOOO\r\n"
							+ "o^**\\***o******=***=oooo*****...                                       .,*,,...........................................,o`**.****************,\\o,[[**[**o[******,o[[*[/********.**.......****......... ......... ................o@@@@O@@@@OO^,[/^**.********.**....*.**..****.**..***=*`,**ooo**********,^****oooOoOOOoooooOOO\r\n"
							+ "`*=o[*******`****,****o^`**...                                        ..  ... ........................................***.***..*********************.*...***...*[*.........*.*.*...........***.......  .....   . ...............*O@@@@@@@OOOO**=o\\`**.********.....***...**..***********.*****o]*************[*****\\OOOOOooooOO\r\n"
							+ "o\\******o**[`********`**/.                                                 .o`...........**...........................***..********************..**.........................................*........... .......................=@@@@@@OOOOo^****`..***..*,****.......*****..**=[*******..***.*\\o*********`**o**[^*,/ooOOOOOOOO\r\n"
							+ "***,/*********]]^*/^**.                                                      ............*..........................**.**.************`*.******...........***..................*...............................................,O@@@@@OOOOO^**o`..**...***.****.......*....**.*/`****...******.,o\\*******************=ooOOOOOOO\r\n"
							+ "[`,]***]]=ooooo^o\\*,.                                                          ...................................*o**..*=\\*****..****.*....**..........*****.*.................................*..............................=@@@@@@OOOOOOOo`**********.***.,`.............*=o**.**..*..*******o\\**********,`,]]o]ooooooOOOOO\r\n"
							+ "[***^,ooo\\/ooo[***`                                                             ....................................********.......*...........................................................................................O@@@@@@@@@OOOOOo^o*,`]***...******...........**[*........**********[********]=ooooOoooOOOOOoOOOO\r\n"
							+ "[/**/oooo^*=****.                                                                ...................................*.***......**....**..**..**.**............................................................................=@@@@@@@@@@@@@OOOOo`******..******............**.*....***.*..**.************oooooooooooooOOoOOOOO\r\n"
							+ "***oOo[********                                                                 ...............................................***,ooo\\*o]o\\\\*=o]\\***]`]/^*****,/***`********`*]*.............................................O@@@@@@@@@@@@@@@@@OOo/`***.,^*****.**.........**..*..**.....********=`****=ooooo/******\\oooooOOOO\r\n"
							+ "`,[oo********.                                                                ...*.........................................*]\\/O@@@@@OOOOOOOOOOOOoOooo\\oooooooo^,o*/]/\\/\\oo/oOoOOOO\\`*......................................./@@@@@@@@@@@@@@@@@@OOO@Oo*/****....*...*****...**.........*...**..**,o^***=Oo^***********,]ooooOOO\r\n"
							+ "\\`\\/`******.                                                                   ..o......................................*,ooOO@@@@@@@OOOo^*ooOOOOOO/oOOOOOOOOOOOOOOOOoo/[oOO^,o@@@@@@O]*,`..................................=@@@@@@@@@@@@@@@OOOO`   \\oO]`           ..*.**...**............*****/o\\****,o/*********]***oooooooO\r\n"
							+ "=^=o^*****.                                                                     ......................................***ooooOO@@@@@@@@@@OOOOOOOOOoOOOOOOOOOOOOOOOOOo/[`=*\\\\*=O@@@@@@@OO^**................................,@@@@@@@@@@OOOOOO^.  ..   ...`   .           .**.****...........*************`*************,ooooooOO\r\n"
							+ "/*=oo`**.                                                                      .......................................***,ooooooOOOO@@@@@@@@O@OOO@OOO@OOOOOOOOOOO@OOOOOOOOOOOO@@@@@@OOOo\\***..............................,@@@@@@@@OOOOOOo*..         .    . ...           ...**************************/`*************/ooooOOO\r\n"
							+ "o*=ooo\\.                                                                     ........................................**=ooooo***oOoOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo/*,[****............................,O@@@@@@@@OOOOO\\**...       ,.                        ......*************..****\\o`***********,o/ooOOOO\r\n"
							+ "***,o*.                                                                       ....*.*...............................***=^*o^]`**`[[[,ooOOOOOOOOOooooooOooOOOOoooOOOooOOOOOOOOOOOOOoo`********...........................,@@@@@@@@@@@OOOO*...        ..                                ..****.****.******,ooo*****,[***/oOOOOOoo\r\n"
							+ "[****.                                                                         .***``.**...........................*****//****************,`^,ooooooooooooooooooooooooooOoooOooooo*...*.*.***........................../@@@@@@@@@@@@OOoo*.        ...                                    ....*************=ooooo\\ooooooOOOooooo\r\n"
							+ "oo**.                                                                           .`.*,`**..........................**]**,o`**********........*,[*****[**\\oo,\\oo[[`*\\o/`*[[o[*=/*,*....**..*****......................../@@@@@@@@@@@@@Oo[`.       .....                                        ..*..**********\\ooo/oooooooOo\\oooo\r\n"
							+ "\\`].                                                                            .*,\\,o/**........................*.*/*\\**************................*.....*........*.**.***...........**.****......................./@@@@@@@@@@@@@OO[*..      .                                                 ..****,,]**********[**\\o*o^=oo\r\n"
							+ "                                                                                 .**ooo/***.....................********************........................................................**.....................,O@@@@@@@@@@@@OO/....     .                                                      ..**[\\oo\\**************/ooo\r\n"
							+ ".........ooOOOO\\]]].                                                             .*,oooo****.....................********************.................................................,*...*......................=@@@@@@@@@@@@@OO,`...    ..                                                           .*[*o^**********o/oo/oo\r\n"
							+ "........*=oOOOO@@@@@OOOOO]`                                                       ..*oooo\\****...................*.***[[]`************.*.......*********.***.*..........................*........................O@@@@@@@@@@@@@@Oo....   ..                                                               .*\\^***\\^******/o]ooo\r\n"
							+ "    ....,oOOOO@@@@@@O@@@@OOOO`                              .                     ..*\\ooo\\**..**....................***\\o`*/\\**************o,/oooOOOOOO/\\]]]\\]]]].**...........................................=@@@@@@@@@@@@@@@Oo`.......                                                                    **********,\\]oOOOO\r\n"
							+ "       ./oOOOOO@@@@@@@@@@@OO@O                                                     ...=ooo\\****,*....................*********,o`****oo`*,[`*ooooooOOOOOOOOOOOOOOooooo`***..........*.**.....................*O@@@@@@@@@@@@@@@@O`......  .                                                                     .******\\]ooOOOoO\r\n"
							+ "..........*=oOOOOOOOOO@@@@@OO/                               .                      ..*,oo`=****\\O]*..................****************.**********`***[[`,\\,[[[oo`*\\o`*,`***....*****.**...................../@@@@@@@@@@@@@@@@@O^...... ..                                                                        .**/`*oooooooo\r\n"
							+ "........*ooOOOOOOOOOOOOOOOOO@^                                                       ..*/oo^`****OOO`..................********************.****************..****************.*...*......................=@@@@@@@@@@@@@@@@@@@O*.... ..       .                                                                    .**oOOo**]oo\r\n"
							+ ".......*oOOOO@@@@@@OOOOOOOOOOO                                                        ..*\\ooo^.*ooOO^**..*..............**..************...**.*...********.*....*......*..*.......**.......*...........*,O@@@@@@@@@@@@@@@@@@@O*.......        .                                                                      .[[[*=/],o\r\n"
							+ ".......,oOOO@@@@@@@OOOO@@@@@O^                                                         ..*=o/o`**=OO\\*\\\\*....*.............*.*...**.****...*......*..................................................**O@@@@@@@@@@@@@@@@@@O@OO...  ..        ..                                                                          .*,*oo\r\n"
							+ "......*/oOOOO@@@@@@OOO@@O@@@O.                                 .                        ..*\\ooo`*ooOO\\**`,`.**.**.................*..*....*........................................................*,/@@@@@@@@@@@@@@@@@@@@@@O^....*.           .                                                                           .[*=\r\n"
							+ ".  .....*\\oOOO@@@@@OO@@@@@@@O                                                            ..*[oo^*/OOOo*..,oO\\******...............**............................................................**,oO@@@@O@@@@@@@@@@@@@@@@@@O*...*.          ...                                                                             =/\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@^                                                            ...**=[\\*OOoo^...*=OOO`******...........,`.............................................................*/O@@@@OO@@@@@@@@@@@@@@@@@@OO\\/`..           .                                                                                 \r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\\`                                                           ...*=oo/OOo\\,....*\\OO@O\\*****...................................................................*..*,oOO@@@@OoO@@@@@@@@@@@@@@@@@@OOO/*..            ..                                                                               \r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\\                                                         ...*[]oOOo^**.....,oO@@O^]****................................................................***/OOOO@@@O`=O@@@@@@@@@@@@@@@@@@OOOO`..             ..                                                                               \r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.                                                         ...*oOOO^****.....,oOO@OoOO]**.*...........................................................***/OOOO@@@@/*=OOOOO@@@@@@@@@@@@@@OOOOO*..                                                                                              \r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.                                                         ...*oOOo***..*. ...,\\OO@OooOOO\\*.**.....................................................***]OOOO@@@@@/.../OOOO@@@@@@@@@@@@@@@OOOOO*..                                                                                              \r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.                                                         ...*,OO[***..*......*\\oO@OO/,o\\@@O\\`....*...........................................***,/OOOOO@@@@@/....,OOOOO@@@@@@@@@@@@@@OOOOOo^.                                                                                               \r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.                 .                                       ....,o^****....... ...*oOOOOo/***\\O@OOO]`.*.......................................,oOOOOOO@O@@@@@[.....*OOOoOOO@@@@@@@@@@@@OOOOOOO`                                                                                                \r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.                                                         ...*/^****.....*..   ...*\\OOOo\\*....,O@@@@OOOo]`**........................**]/OOOOOOOOO@@@@@@@/`......*o=OoOOOOO@@@@@@@@@@@OOOOOOO.                                                                                                \r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O                                                           ..**....**........   ...*=OoOoo*......,\\O@@@@@@OOOOOOOO]]]]]]]]]]]/OOOOOOOOOOOOO@@@@@@@@@@/..  .....`**oOoooOOO@@@@@@@@@@OOooOOOo.                                                                                                \r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^                                                           .=`.............*.     ...,\\oooo^..........[\\@@@@@@@@@@@@@@@@@OO@@O@@@@@@@@@@@@@@@@@@@/`.      .....**,o`*=oOOO@@@@@@@@@@Oo\\OOOOo.                                                                                                \r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O                                                            ............*......      ..,oo/,oo].............[O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O[`.         ......./\\^..=OOOO@@@@@@@@@@O=*\\OOOo.                                                                                                \r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^                        .                                 ..... ........*.......      ...,oo***,^.................[[O@@@@@@@@@@@@@@@@OOO[`..             ......****...,OOOO@@@@@@@@@@o**ooOo\\.                 .                                                                              \r\n"
							+ "OOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@/                         ..                                 .   ...........*....*.       ..*\\^,*****........... .....  ...........                       .. ...*[.....*oOOOO@@@@@@@@@Oo`[oo/[o.                                                                                                \r\n"
							+ "OOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@\\                          ..                              ..  ..   ................      ....*o^,*.*..*...........  .                                ...  ...*`*.....*/ooOOO@@@@@@@@@O^**=oO/o.                                                                                                \r\n"
							+ "OOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@O.                          .                                         ..............        ....,o^**...............    .                            .   ....,`.......=O/oOOOO@@@@@@@@O`***o^oo.                                                                                                \r\n"
							+ "OOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@O.                                                                       .............        ...*oo***................. ..  .                         ....**.... ...*o`,ooOOO@@@@@@@@^***[*/***.                                                                                               \r\n"
							+ "");
			System.out.println("你遇到了一个怪物！它叫 " + guolaoshi.getName());
			System.out.println("你当前的血量是 " + you.getHp());
			System.out.println("你选择——");
			System.out.println("1.攻击 2.逃跑");
			Scanner toGLS = new Scanner(System.in);
			String toGLSR = toGLS.next();
			if (toGLSR.equals("1")) {
				getBag(bag, hasCount);
				Scanner atcxk = new Scanner(System.in);
				String atglsR = atcxk.next();
				if (atglsR.equals("1")) {
					x++;
					System.out.println("用匕首肯定是打不过的！你逃跑了！");
				} else if (atglsR.equals("2") && bag.contains(basketball.getName())) {
					System.out.println("你选择了篮球！");
					System.out.println("战斗开始！");
					try {
						while (guolaoshi.getHp() > 0) {
							if (you.getHp() <= 0) {
								you.setAlive(false);
							}
							if (you.isAlive()) {
								int isBlock = (int) (Math.random() * 8);
								if (isBlock % 2 == 0) {
									System.out.println(you.getName() + " 使用 篮球，对 " + guolaoshi.getName() + " 造成了 "
											+ you.getAttack() + " 点伤害！但 " + guolaoshi.getName() + " 格挡了一部分伤害！");
									guolaoshi.setHp(guolaoshi.getHp() - you.getAttack() + guolaoshi.getDefense());
									if (guolaoshi.getHp() > guolaoshi.getMaxHP()) {
										guolaoshi.setHp(guolaoshi.getMaxHP());
									}
								} else {
									System.out.println(you.getName() + " 使用 篮球，对 " + guolaoshi.getName() + " 造成了 "
											+ you.getAttack() + " 点伤害！");
									guolaoshi.setHp(guolaoshi.getHp() - you.getAttack());
								}
							} else if (!you.isAlive()) {
								System.err.println("你死了！GAME OVER！");
								System.exit(0);
							}
							if (guolaoshi.getHp() > 0) {
								int isBlock = (int) (Math.random() * 8);
								if (isBlock % 2 == 0) {
									System.out.println(guolaoshi.getName() + " 现在的血量是 " + guolaoshi.getHp());
									System.out.println(guolaoshi.getName() + " 对 " + you.getName() + " 造成了 "
											+ guolaoshi.getAttack() + " 点伤害！但 " + you.getName() + " 格挡了一部分伤害！");
									you.setHp(you.getHp() - guolaoshi.getAttack() + you.getDefense());
									if (you.getHp() > you.getMaxHP()) {
										you.setHp(you.getMaxHP());
									}
								} else {
									System.out.println(guolaoshi.getName() + " 现在的血量是 " + guolaoshi.getHp());
									System.out.println(guolaoshi.getName() + " 对 " + you.getName() + " 造成了 "
											+ guolaoshi.getAttack() + " 点伤害！");
									you.setHp(you.getHp() - guolaoshi.getAttack());
								}
							}
							System.out.println(you.getName() + " 当前的血量是 " + you.getHp());
						}
					} finally {
						System.out.println(guolaoshi.getName() + " 被打败了！");
						guolaoshi.setAlive(false);
						System.out.println(guolaoshi.getName() + " 掉落了武器：" + miHotel.getName() + "，攻击力："
								+ miHotel.getAttack() + "，介绍：" + miHotel.getIntroduction());
						bag.add(miHotel.getName());
						you.setAttack(you.getAttack() - dagger.getAttack() + miHotel.getAttack());
					}
				} else {
					x++;
					System.out.println("你没有做出正确的选择，你逃跑了。");
				}
			} else {
				x++;
				System.out.println("你逃跑了。");
			}
		} else if (x == dygg.getAtX() && y == dygg.getAtY() && dygg.isAlive()) {
			System.out.println(
					"....................***....................=O...........=OO^..............................................=OO^.....=OO............................=@....  .................................*...............................\r\n"
							+ "....................***....................=O..*........=O@`..............................................OO@^.....=OO............................=@....  .................................*...............................\r\n"
							+ "....................**.....................=O..*........=O@^..............................................OO@^.....=OO............................=@..... ............ ....................................................\r\n"
							+ "....................**.....................,O..`........=O@`.............................................*OOO^.....=OO........................... .@..... ............ ....................................................\r\n"
							+ "...................****....................*O^.^........=O@^.............................................=OOO^.....=OO..........................  =@..... ...........  ....................................................\r\n"
							+ "....................*......................*O^.^........=O@..............................................=OOO......=OO..........................  =@....  ...........   ...................................................\r\n"
							+ "...........................................*O^.^........=OO..............................................=OO^......=OO........................... =@....   .........    ...................................................\r\n"
							+ "...........................................*O^.^........=OO..............................................=OO^......=OO........................... =@....   ..........   ...................................................\r\n"
							+ "...........................................*O^.^........=OO..............................................=OO^......=OO........................... =@....   .........    ...................................................\r\n"
							+ "...........................................*O^.^........=@O..............................................oOO^......=OO..........................  =@....   .........    ...................................................\r\n"
							+ "...........................................*OO.^........=@O.............................................*OOO^...*..=OO..........................  =@....   .........    ...................................................\r\n"
							+ "............................................OO=^........=@O.............................................=OOO^.=ooo*oOO\\]]]`..^.................   =@....   ........     ...................................................\r\n"
							+ "............................................OO=^........=OO............................................*oOOOOOOOOOOOOOOOOOOOOO.,*..............   =@...    ........     ...................................................\r\n"
							+ "...........................................*OOo^........=@O.......................................*..=OOOOOO@@@@@@@@@@@@@OOOOOOOOo]OOO^........   =@....   .......      ...................................................\r\n"
							+ "............................................OOO^........=@O..................................*./`*OO]O@@@@@@@@@@@@@@@@@@@@@@@@OOOO@@O@Oo\\**.....  =@....   .......      ...................................................\r\n"
							+ "............................................OOO^........=@O................................*/oOOOo@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOO\\o\\**.. =@....   ......       ....   ............................................\r\n"
							+ "............................................OOOO........=@O.........................*/`=oo*=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOO...=@....   ......       ....     ........................... ..............\r\n"
							+ "............................................OOOO........=@/.........................*=O\\/OOO@@@@@@@@@@@@@@@@@@@@@@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO*=@....   ......       ....     ........................... ..............\r\n"
							+ "............................................OOOO........=@^ ......................]]OOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOO\\*... .......       ....     ........................... ..............\r\n"
							+ "............................................OO@O........=@^ ...................*oOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O]..........        ....     .......................     ..............\r\n"
							+ "............................................O@@O. ......*O^ ..................,/O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO`.......        ....     ........................   . .............\r\n"
							+ "............................................O@O@.........O^ ................,/O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO@@@@@@@@@O^......        ....     .......................      .............\r\n"
							+ "............................................OO@@........*O. .............../O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOO\\`..       .....      .....................       .............\r\n"
							+ "............................................OO@@........*O. ............./@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO.      ......      ..   ...............        .............\r\n"
							+ "............................................O@@@........*O. ..........*=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO\\...........           ................        ............\r\n"
							+ "............................................O@@@. ......*O. .........,OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO`.........            ...............        ............\r\n"
							+ "............................................OO@@........*O. ........./@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO\\].......            ..............        .............\r\n"
							+ "............................................OO@@.  ......O. ......../O@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O*......            .............         .............\r\n"
							+ "............................................O@@@.  ......O. .......O@@@@@@@@OOOOOOOOOOoo[[[[[[[[[[[[[oooOOOOOOOOOOOOOO@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^.....            .............        ..............\r\n"
							+ "............................................O@@@.  ......O. .....,O@@@@@OOOOooo/[[`**********...********...**[[[[ooOOOOOOOOOOOOOOOOOO@@OOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@OO`...            ............          .............\r\n"
							+ "............................................OO@@.  ......O. ....,O@@@OOoooooo^********..**.......................*****,ooooooooOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@O...            ............          .............\r\n"
							+ ".....................................  .....=@@@.  ......O. ...=@@@Oo/**************..................................************[[[[[[[[\\ooooooooOOOOOOOOOO@@@@@@@@@@@@O..            ...........           .............\r\n"
							+ "....................................  ......=@@@.   .....O. ..,O@OOooo************................................................******************oooooooOOOOOO@@@@@@@@@\\.            ...........            ............\r\n"
							+ "....................................   .....=@@@.   .....o. .=@OOooooo^***********................................................******************ooooooooooooOOOO@@@@@@@\\.           ............           ............\r\n"
							+ "......................  ............   .....=@@@.   .....o .O@Oooooooo***********.....................................................***************=ooooooooooooOOOO@@@@@@^.          .............          ............\r\n"
							+ ".....................   ............     ...=@@@.   .....^./@Oooooooo`***********......................................................**************=oooooooooooooOOOOOO@@@@^          ............           ............\r\n"
							+ ".....................  .............     ...=@@@.    ....,OOOooooooo***********.................... .....................................*************oooooooooooooooOOOOOO@@O.          ............         .............\r\n"
							+ ".....................   ...........      ...=@O@`   .....OOoooooo\\**************.........................................................*************ooooooooooooooooOOOOOO@@\\.         ............         .............\r\n"
							+ ".....................  . ..........   ......=@@@^   ....oOooooooooo***,]*,]*]*******............     ....................................************=ooooooooooooooooOOOOOOOO[.        ............         ..............\r\n"
							+ ".....................   ...........      ...=@@@^   ..,OOooooooooo`***[[[[`*******................  .....................................************=ooooooooooooooooOOOOOOO@@O`       ............         ..............\r\n"
							+ ".....................   ...........      ...=@O@^    ./Oooooooooo***********.*..........................................................*************,oooooooooooooooOOOOOOOOOO@O.       ...........        ...............\r\n"
							+ ".....................   ...........      .../@O@^   .OOooooooooo*/oo]]]oooooooo]]]]**.............  ...................................**************]oooooooooooooooOOOOOOOOOOOO\\.      ...........        ...............\r\n"
							+ ".....................   ..........       ...O@O@^  .=Oo=oooooo/****,*******...*****............****...................................**********[[[*[[`*oo[[ooooooooOOOOOOOOOOOOOO^      ...........       ................\r\n"
							+ "....................   ...........       ...=@O@^  *oooooooo**********.....****................    .............................................***]]]ooooooooooooooOOOOOOOOOOOOOOO.     .............     ................\r\n"
							+ "...................    ...........       ...=@O@^ .ooooooooo*********.......*******.................  .....   ...........................*]]/OOOoo[[[[**[[[[ooooooooOOOOOOOOOOOOOOOo.    .............    .................\r\n"
							+ "...................    ...........       ...=@@@O .Oooooo/************....................................     .....................****[`.............******,ooooooOOOOOOOOOOOOOOOO^    .............    .................\r\n"
							+ "...................    ...........       ...=@@@O.=o,ooooo*****=/OOOOOOo^*.................     .........      .....................................***********ooooooOOOOOOOOOOOOOOOO.   ............     .................\r\n"
							+ ".................      ...........       ...=@@@O.=oooooo`***]ooOOOOOOooo`***.........            ........     ....................................*****]]]]]/oooooooOOOOOOOOOOOOOOOO^   ............     .................\r\n"
							+ ".................      ...........       ...=@@@O.=oooooo***oooOOOOOoooOOOoo\\***.....              ........    ........      ..................****,/oOOOOOOOOOOOoooooOoOOOOOOOOOOOOO\\   ...............   ................\r\n"
							+ ".................      ...........       ...=@@@O*,\\oooo`***ooooooooooOOOOOOOOoo`*....              .... .      ......           ...........*,/oOOOOOOOOOOOOOOOOOooooooOOOOOOOOOOOOOOO.  ............... ..................\r\n"
							+ ".................      ...........       ...=@@@O*=^ooo`******[[,[[[[[[[[\\ooOOOOo/*.....                         .              ........**,/oOOOOOOOOOOOOOOOOOOOOOoooooooOOOOOOOOOOOOO^ ...................................\r\n"
							+ ".................      ...........        ..=@@@O,oooo[***************.......**[[[*.......                                     .....***ooooOOOOOOOOOOOOOOOOOOOOooooooooooOOOOOOOOOOOOO^ ...................................\r\n"
							+ "................      ............         .=@O@O=[o***********...................................                           .......*,oooOOOOOOOOOOOOOOOOOooooooooooooooOOOOOOOOOOOOOO^....................................\r\n"
							+ "................      ............        ..=@O@O***********......................................                           ......****\\oooooooooooo[[[[[[ooo[[[[[[oooooooOOOOOOOOOOOO^....................................\r\n"
							+ "................      ............         .=@O@O***********.....................................                   ................*****....**........************[[[oooOOOOOOOOOOOOO^....................................\r\n"
							+ "...............      .............       ...OOO@o**********......................................               . ..................***......................*********=oooOOOOOOOOOOOO. ...................................\r\n"
							+ "................     .............      .../@@@@^,*`***,**.......................................................................................................*****oooooOOOOOOOOOOO..      .............................\r\n"
							+ "...............      .............     .......   .....****..................................................................................*....................****,oooOOOOOOOOOOOOO^.    ...............................\r\n"
							+ "...............      .............   ,/..........,[[[`***.................................................................................**......................***ooooOOOOOOOOOOOOO^ ..,]]..............................\r\n"
							+ "..............       ............ .]/...,/O[`.  ......***..........................................................................................................*,ooooOOOOOOOOOOOOO^ =@@`...............................\r\n"
							+ "...**.........       ............/[   .......  .......***.............  ..........................................................................................**,oooooooOOOOOOOOOO.O@^  ...............................\r\n"
							+ "..***.........       ............=\\`.......    ......****........              ........................................................      ....................****ooo*[ooo`\\OOOOOOO@@`  ................................\r\n"
							+ "..***.........       ............     .......  ....=O****..................................    .....**...............................      ......................**=\\]]*****...=OOOOOOO./@OoO^.............................\r\n"
							+ "..*...........       ............     .......  ....,o**.*............]/OOOOOOO\\`..................*.................................  ...........................**/o[*........,Oo[[[[O@OOOOOOO^ ..........................\r\n"
							+ "..**..........      .............     ......   ....*`*...........]OOO/`.../`=OOOOO\\*.............*o..............=^*.....................]/OOOOOOOOOO\\..........**=ooo*..*......,oOOOOOOOOOOOOOO. .........................\r\n"
							+ "..**.........      ..............      ......  .....*......**/oOOOOO^...=O@@@@@O@@Oooo\\`*........=O**\\*.........****................]/oOOOO`.OO]`..[,o\\..]`....*,oooo\\*****...**OOOOOOOOOOOOOOOO. .........................\r\n"
							+ ".**..........     ...............      ......  .....**.....*****,ooo^...O@@@@@^=OOO`*oOoo**.....=Oo*=o\\*...........*.............*/oOOo``OO@@@@@OO\\...*oOOO*.*,oooooo*********..OOOOOOOOOOOOOOO^  .........................\r\n"
							+ "**...........     ...............       .....  .............***********=O@@@@@@@@OO..=OOO*......=oo*oOoo/*...*****...........****oOOO`..OOO@@O`=OOO..**\\oOOOOOOooooo**********.=OOOOOOOOOOOOOOO^ ..........................\r\n"
							+ "**...........      ..............       .....   ...........*****.............**[*[****=o/*.......**oOo*........**o\\**.....******oOOOo*..OO/@@@@@@@O^****,[[oooooooo***********,oOOOOOOOOOOOOOOO............................\r\n"
							+ "**..........       ..............       .....    ........****=****.................*****.........,oo/............**\\o\\`....*****,[[[*........[[[`***....***=ooo/`***********../OOOOOOOOOOOOOOO^ ...........................\r\n"
							+ "**.........        .............       .....*..    ...../o*...**ooo]*.**************.............**...............***........................**..........**ooo^**********....,OOOOOOOOOOOOOOOO^ ...........................\r\n"
							+ "**.........       ..............        .......*,`*.......  ...,[ooOOOOOOOOoo[[[`....,[........****.............*****...............*******............***ooo**********.....,oOOOOOOOOOOOOOOOO^ ...........................\r\n"
							+ "*..........       ..............        .......******=Oo*.................******...............**...............*****..................**,\\ooo]**********[o[`*****[**.....,oOOOOOOOOOOOOOOOOOO^ ...........................\r\n"
							+ "*..........       ..............        .......******=o^*\\**...................................................*********.....................*******************.*..***]oooooooOOOOOOOOOOOOOOO^ ...........................\r\n"
							+ "*.........        ..............        .......*********`**.....................................................********......................................******=oooooooooOOOOOOOOOOOOOOOO. ...........................\r\n"
							+ "*.........       ...............        .......***..*****.............................  ...............  .......*********......................................*****\\oooooooOOOOOOOOOOOOOOOOO^ ............................\r\n"
							+ "..........       ...............        .......**...................... .               ..............    ......************..............................***********=oooooOOOOOOOOOOOOOOOOOO^ ............................\r\n"
							+ "*..........      ...............        .....................                         ................    .......*************..............................***=oooooooooooOOOOOOOOOOOOOOOOOO^.............................\r\n"
							+ "*.........       ...............        ....*.*.............                         ................     .......**************..............................***,\\oooooooooOOOOOOOOOOOOOOOOOO^.............................\r\n"
							+ "*.........       ...............        ....=`*.............                     ....................     ......****************...............................***=oooooooOOOOOOOOOOOOOOOOOOO..............................\r\n"
							+ "..........       ...............        ....O^**...........                      ....................    .......****************...................................*,oooOOOOOOOOOOOOOOOOOOOOO..............................\r\n"
							+ "..........       ...............        ....**.**..........................   .. ....................    ........***************.....................................**[oOOOOOOOOOOOOOOOOOOO. .............................\r\n"
							+ "..........       ...............       .....**.**............................ .......................    ........***************......................................**=ooOOOOOOOOOOOOOOOO`  .............................\r\n"
							+ ".........        ...............       ......**......................................................    ........*********,/]]`**......................................*=oOOOOOOOOOOOOOOOO^. ..............................\r\n"
							+ ".........       ................       .....**...............................***.....................    .......********]ooooo^**.....................................**=ooOOOOOOOOOOOOOOO. ...............................\r\n"
							+ ".........       ................       .....**..........................*,/ooo^**....................    .......*****oooooooo`**********..............................***oooOOOOOOOOOOOOOO.................................\r\n"
							+ "........        ...............        .....***....................**]oooOOooo^*.....................    .......****ooooooo[****************..........................***oooOOOOOOOOOOOO/..................................\r\n"
							+ "........        ...............        .....****.................*=oOOOOOOooo[**.....................    ......***/oooo//****.*****/]`****]]]]**......................***ooOOOOOOOOOOO[. ..................................\r\n"
							+ "........        ...............       ......****...............*=oOOOOOOo/`*****.....................    ......**oooo^****......***ooo\\***\\ooooo^*....................***oooOOOOOO^  ... ..................................\r\n"
							+ "........        ...............       ......*****..........**]ooOOOOOOoo`*******....................     .....**=oo/**..........**=oooo`*****,\\ooo\\*.................***=oooOOOOOO^  ......................................\r\n"
							+ ".......        ................        .....*^***........**/oOOOOOOOoo`***....................................**/oo`*..........**=ooooo**********ooo\\`***...........***,oooOOOOOOO^  ......................................\r\n"
							+ "........       .................       .....*^**`*******,/oOOOOoooooo`******....****..........................*\\oo***.......****,oooOoo^**********=oooo]***.......****,ooOOOOOOOOO^  ......................................\r\n"
							+ ".........      ................       ......***,*****,ooOOOOOoooOOOOoo\\********.**/oOOO\\`*....................*=oo\\***]]]]]]/oooooOOOOoo]*****]oooooooooo^***.*****/oooOOOOOOOOOOO^  ......................................\r\n"
							+ ".........      ................       ......******,]ooOOOOOoooOOOOOOOoooo*********\\OOOOOOOOo]**...............*,ooooooooooOOOOOOOOOOOOOOoooooooooooooo[[ooo]`*****/ooooOOOOOOOOOOO.  ......................................\r\n"
							+ ".........      ................        .....******oooOOOOOoooOOOOOOOoooo/**********\\OOOOOOOOOOOo\\**......***,/ooooOOOOOOOOOOOOOOOOOOOOOOOoooooooooOOOOOo/ooOo]/]/oooOOOOOOOOOOOOOO.  ......................................\r\n"
							+ "..........    .................       ......***,ooooOOOOo\\ooOOOOOOOoooo**************,oOOOOOOOOOOOoo]]]]]ooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoooooooOOOOOOOoooOOOOoooOOOOOOOOOOOOOOO.  ......................................\r\n"
							+ "..........    ...................   .......****oooooOOOO^=oOOOOOOOoooo`********........*,[[OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooooooooOOOOOOOOOoooOOOOOOOOOOOOOOOOOOOO. .......................................\r\n"
							+ "..........    ...................   .......****o*=ooOOOO^=oOOOOOOooooo\\**********............*,[OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooooooooOOOOOOOOOOOooOOOOOOOOOOOOOOOOOOO^.........................................\r\n"
							+ "..........    ..................    .......****o**ooOOOOoooOOOOOooOoooooo]*******................,\\OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoooooooOOOOOOOOOOOOOooooOOOOOOOOOOOOOOOOO^   ......................................\r\n"
							+ "..........    ..................    .......****o*,ooooOOooooOOOOOOOOOOOooooo]****...................\\OOOOOOOOOOOOOOOOOOOOOOooooooooooooooooooooOOOOOOOOOOOOOoooooOOOOOOOOOOOOOOOO. ........................................\r\n"
							+ "..........   ....................   .......******oooooOOOooooOOOOOOOOOOOOOOOOOo]****.................*\\OOOOOOOOOOOOOOOOOOOooo*=/oooo\\oooooOOOOOOOOOOOOoooOO/**\\ooOOOOOOOOOOOOOOO^ .........................................\r\n"
							+ "..........   .......*......................,\\*=^*ooooooOOooo\\ooooooooOOOOOOOOOOOOOOOo\\]`***............,oOOOOOOOOOOOOOOOOOooooooooooooOooOOOOOOOOOOOOooooo/*.**ooOOOOOOOOOOOOOOO...........................................\r\n"
							+ "..........   ......**........... ..........=O*^\\=oooooooOooo****,oo[[[\\OOOOOO@@@OOOOOOOOOoOooo\\]`*****..*=ooOOOOOOOOOOOOOOOoooOoOoo/**..OO@@@@@@@OOOoooooo`..**oOOOOOOOOOOOOOOO`  . .......................................\r\n"
							+ "........... .......***.......... ..........=@\\*^=ooooooooooo***********...,\\OO@@@OOOOOOOo/*=Ooooooooooo]ooooooooooOOOo[\\oooo/********..=@@@@@@@@@Oo,oooo/*...*/OOOOOOOOOOOOOOO^  ..........................................\r\n"
							+ "........... ......****.....................=@Ooooooooooooooo**********.......\\O@@@@@OoOOo^*=o`***\\o`****=oooo**...=o/*.*=oo******..**.=@@@@@@@@@Oo`****,*....,OOOOOOOOOOOOOOO/.  ..........................................\r\n"
							+ "..................****.....................=OOOOooooooooo*[***********........*O@@@@@@@Oo[**/*...**.....*oO^*.....***...*o\\*****..*O@@@@@@@@@OOO/*******....*oOOOOOOOOOOOOOOO.    .........................................\r\n"
							+ ".................*****.....................=@OOOOOOooooo\\**\\*******...........**O@O@@@@OOOoOo....*.......,o`......***...**o^]ooOOO@@@@@@@@@OOOo^*******....=oOOOOOOOOOOOOOOO^    ...............................,]]]]].....\r\n"
							+ ".................*****.....................=@OOOOOOOOoooo^*********.............,Oo**oOOOOOOOOOo/O`................o^..*=OOOOOOO@@@@@@@@@@OOo^*********...,oOOOOOOOOOOOOOOO`     ........................,]O@@@@@@@@@@@@@@\\\r\n"
							+ ".................*****.....................O@@OOOOOOOOoooo^********.............*,o\\....*,[\\OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@OOOo[`*********.../oOOOOOOOOOOOOOOO`.    ...................,]O@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ ".................*****.....................O@@@@@@@ooOOOooo^********.............**oo... ...*OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@OO/`**************.*=OOOOOOOOOOOOOOOO...    ...............,/@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ ".................*****.....................O@OO@@@Oo@@OOOOoo/^*******.............**,*.. ....\\OOOOOOO@@O@O@@@@@@@OOO@O@@@@@@@@@@@OO[**************\\****/OOOOOOOOOOOOOOO/....    ............/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ ".................**/`*.....................O@OO@@@OO@@@@OOooooo`*****...............**,]........,\\OO@@@@@@@@@@@@@@@@@@@@@@@@@Oo/***[**********,ooo`***=OOOOOOOOOOOOOO/......    ..........,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ ".................*=o^*.....................O@OO@@@O@@@@@@@OOooo*******..............*****`*.....   ....[[...,@O...  . ...***********,]]`****,ooooo**.,oOOOOOOOOOOOOO^.......        ...../@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "................**oo^*.....................O@OO@@@@@@@@@@@@OOooo`*****..............*.....***......           .   ....************/oooooo***/oooo/***oOOOOOOOOOOOO`.........      ....  =@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "................*=oo^*.....................O@OO@@@@@@@@@@@@@@Oooo^****..........*******............................**************=ooooooo*,ooooOo**`oOOOOOOOOOOO@@@O\\`......     ......O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "................*/oo**.....................O@@@@@@@@@@@@@@@@@@Oooo^****.*...*..*********....      ..........  ........**********/oooooooooooooOo***oOOOOOOOOOO@@@@@@@@@@\\`..         .O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "................*ooo**.....................O@@@@@@@@@@@@@@@@@@@Ooo\\****.......************............................*******=oooooooooooooooOO`*,oOOOOOOOOO@@@@@@@@@@@@@@@O]]]]O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "................*ooo**.....................O@@@@@@@@@@@@@@@@@@@Oooo`*.*....***..******************............***********]]]ooooooooooooooooOO`**oOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "................*ooo`*..................../@@@@@@@@@@@@@@@@@@@@ooooo***..........*************************************,//ooooooooooooooooooOo**,oOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "................*ooo*.................]O@@@@@@@@@@@@@@@@@@@@@@Oooooo`*******.....**********//oo\\oooo\\oo]******,[]]]/*,ooooooooooooooooooooOo`.*/OOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "................=oo^`...........]O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OoOooo****........***********[\\oooooooooooooooooooooooooooooooooooooooooooOo*.*\\oOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "...............*/oo`*..,]]/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOoooo\\********...********************[ooooooooooooooooooooooooooooooooooOO`**=oOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "...............,oOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OooOOo]*****..*******************************,\\********,[[[[[[[oooooooOO`.*,oOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "..............,O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOO\\o******...************************************************=oooOO^**=oOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ ".......... ./@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Oo]^*****...********....***....**.***************************ooo^*=ooOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "...]]]]]OOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Ooo****......****.....................**......*************ooo/**/OOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Oo\\]*******...*...................................*****,/ooo\\]/OOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Ooo\\*********....................................*****=oOooooOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOo]*********...................................*****oOOoOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOoo\\*********..............................******]oOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOo``********......*...*****.****.*.********,oooOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/   =@@@@@@@@@@@@@@@@@@OOOooo*o]*******************************/ooooOO/...O@@@@@@@@@@@@@@@@^ ,\\@@@@@@@@@@@@@@@/[[[@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@    @@@@@@@@@@@@@@@@@@@@@OOOOooooo\\]*****,`****...     .*,/]\\=]ooOOOOO\\   =@@@@@@@@@@@@@@@^   @@@@@@@@@@@@@@@^    ,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O                                   \\@@@@@@OOOo[[`..                 .//o^...=OOOOO@O   /@@@@@@@@@@@@@@^   @@@@@@@@@@@@@@@`  ,`   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^   ...`...`..,].....,.`....`.]..   =@@@@@@@@@O`            ,]OOOOOOOOOOO^   =OOO@@@O   O@@@@@@@@@@@@@^  ,@@@@@@@@@@@@@@O   =@@\\   \\@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^  .@@@@[[@@@@@@@`..@@@@@@@[\\@@@@^  =@@@@@@@@@@@@@@@@@@@O   =@@@@@OOOOOOO^   =OOOO@@O   \\@@@@@@@@@@@@`  ,@@@@@@  ,@@@@@`   /@@@@O   ,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^  .@@@@`  ,O@@@@   @@@@@`  ,O@@@^  =@@@@@@@@@@@@@@@@@@@@   =@@@@@@@@@@O@^   =OOOO@@O   O@@@@@@@@@@@.  =@@@@@/   =@@@/   ,@@@@@@@@\\   ,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^  .@@@@@@`  ,\\@@   @@@`  ,@@@@@@^  =@@@@@@@@@@@@@@@@@@@@   =@@@@@@@@@@@@^   =OOOO@@O   O@@@@@@@@@/   /@@@@@^   /@@/    /@@@@@@@@@@@`   ,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^  .@@@@@@@@`,@@@   @@@`,@@@@@@@@^  =@@@@@@@@@/..........    .........,OO/   =OOOO@@O   O@@@@@@@[    ,[[[[[.   @@[    /@@@@@@@@@@@@@@@`   ,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^  .@@                         @@^  =@@@@@@@@@^                       .OO^   =OOOO@@O   O@@@@@@@^            ,O`    /^                =@`    ,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^  =@@\\]]]]]]]]`    ]]]]]]]]]]]@@^  =@@@@@@@@@@@@@@@@@@@.   =@@@@@@@@@@@O^   =OOOO@@O   O@@@@@@@@@@@@@@@/   =@@\\ ,@@@\\]]]]]]]]]]]]]]]`/@@@\\  /@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^  =@@@@@@@@@/      O^ ,@@@@@@@@@^  =@@@@@@@@@@@@@@@@@@`    =@@@@@@@@@@@O^   =OOOO@@O   O@@@@@@@@@@@@@@/   /@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^  =@@@@@@/.   /@   @\\`   ,O@@@@@^  =@@@@@@@@@@@@@@@@@      ,` \\@@@@@@@@O^   =OOO@@@O   O@@@@@@@@@@@@@`   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^  =@@/`    ]@@@@   @@@@O`   ,O@@^  =@@@@@@@@@@@@@@@/   ]   ,    \\@@@@@@O^   =OO@@@@O   O@@@@@@@@@@@/   ,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/  =@O  ./@@@@@@@\\]/@@@@@@@\\` /@@^  =@@@@@@@@@@@@@@`   /@   =@\\    ,@@@@O^   =O@@@@@@   O@@@@@@@@@@`   =O/[[`.  =@@^                    =@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOO@@@@@@@@@@@@O   =@@@@@@@@@@@@O]/@@@@@@@@@@@@@/    O@@   =@@@\\    ,O@O^   /@@@@@@@   O@@@@@@@@              .O@@^  ,]]]]]]]]]]]]]]`  =@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@[[[[[[[[[[[[[[[[[[[[`   .[[[[[[[[[[[[[[[[[[[[@@@@@@@`   =@@@@   =@@@@@\\  ,OO@/   O@@@@@@@   O@@@@@@@@^ ,]]/@@@@@@@@@@@@^  =@@@@@@@@@@@@@@^  =@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.                                           .@@@@@`    O@@@@@   =@@@@@@@@@@@@O   O@@@@@@@   O@@@@@@@@@@@@@@@@@@@@@@@@@@^  =@@@@@@@@@@@@@@^  =@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@`     ,@@@@@@@@@@@@@@@@@@@@@@@\\   /@@@@@@@   =@@@@@@@@@@@@@@@@@@@@@@@@   O@@@@@@@@@@@@@@@@@@@@@@@@@@^  =@@@@@@@@@@@@@@^  =@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/.   ,O`    [@@@@@@@@@@@@@@@@@@@@@@/@@@@@@@@@   =@@@@@@@@@@@@@@@@@@@@@@@@   =@@@@@@@@@@@@@@/[[.      O@^  =@@@@@@@@@@@@@@^  =@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O[.     ]@@@@@\\`    ,[\\@@@@@@@@@@@@@@@@@@@@@@@@@@@   =@@@@@@@@@@@@@@@@@@@@@@@^   O@@@@@@^              ,]]@@^  ,[[[[[[[[[[[[[[`  =@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O[[`        ]O@@@@@@@@@@@O]        ,[[O@@@@@@@@@@@@@@@@@@@   =@@@@@@@@@@@@@/[[[[[[[`    ,@@@@@@@O   ,]]/@@@@@@@@@@@@^                    =@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@`       .]/@@@@@@@@@@@@@@@@@@@@@@O]]        ,@@@@@@@@@@@@@@@@   =@@@@@@@@@@@@@^           /@@@@@@@@@@@@@@@@@@@@@@@@@@@@^  =@@@@@@@@@@@@@@^  =@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@]]@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O\\]@@@@@@@@@@@@@@@@@]]]O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\\]]/@@@@@@@@@@@@@@\\]]/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
							+ "");
			System.out.println("你遇到了一个怪物！它叫 " + dygg.getName());
			System.out.println("你当前的血量是 " + you.getHp());
			System.out.println("你选择——");
			System.out.println("1.攻击 2.逃跑");
			Scanner toGLS = new Scanner(System.in);
			String toGLSR = toGLS.next();
			if (toGLSR.equals("1")) {
				getBag(bag, hasCount);
				Scanner atcxk = new Scanner(System.in);
				String atglsR = atcxk.next();
				if (atglsR.equals("1") || atglsR.equals("2")) {
					x++;
					System.out.println("用这些武器肯定是打不过的！所以你逃跑了！");
				} else if (atglsR.equals("3") && bag.contains(miHotel.getName())) {
					System.out.println("你选择了迷吼tiao！");
					System.out.println("战斗开始！");
					try {
						while (dygg.getHp() > 0) {
							if (you.getHp() <= 0) {
								you.setAlive(false);
							}
							if (you.isAlive()) {
								int isBlock = (int) (Math.random() * 8);
								if (isBlock % 2 == 0) {
									System.out.println(you.getName() + " 使用 迷吼tiao，对 " + dygg.getName() + " 造成了 "
											+ you.getAttack() + " 点伤害！但 " + dygg.getName() + " 格挡了一部分伤害！");
									dygg.setHp(dygg.getHp() - you.getAttack() + dygg.getDefense());
									if (dygg.getHp() > dygg.getMaxHP()) {
										dygg.setHp(dygg.getMaxHP());
									}
								} else {
									System.out.println(you.getName() + " 使用 迷吼tiao，对 " + dygg.getName() + " 造成了 "
											+ you.getAttack() + " 点伤害！");
									dygg.setHp(dygg.getHp() - you.getAttack());
								}
							} else if (!you.isAlive()) {
								System.err.println("你死了！GAME OVER！");
								System.exit(0);
							}
							if (dygg.getHp() > 0) {
								int isBlock = (int) (Math.random() * 8);
								if (isBlock % 2 == 0) {
									System.out.println(dygg.getName() + " 现在的血量是 " + dygg.getHp());
									System.out.println(dygg.getName() + " 对 " + you.getName() + " 造成了 "
											+ dygg.getAttack() + " 点伤害！但 " + you.getName() + " 格挡了一部分伤害！");
									you.setHp(you.getHp() - dygg.getAttack() + you.getDefense());
									if (you.getHp() > you.getMaxHP()) {
										you.setHp(you.getMaxHP());
									}
								} else {
									System.out.println(dygg.getName() + " 现在的血量是 " + dygg.getHp());
									System.out.println(dygg.getName() + " 对 " + you.getName() + " 造成了 "
											+ dygg.getAttack() + " 点伤害！");
									you.setHp(you.getHp() - dygg.getAttack());
								}
							}
							System.out.println(you.getName() + " 当前的血量是 " + you.getHp());
						}
					} finally {
						System.out.println(dygg.getName() + " 被打败了！");
						dygg.setAlive(false);
						System.out.println(dygg.getName() + " 掉落了武器：" + bigSword.getName() + "，攻击力："
								+ bigSword.getAttack() + "，介绍：" + bigSword.getIntroduction());
						bag.add(bigSword.getName());
						you.setAttack(you.getAttack() - miHotel.getAttack() + bigSword.getAttack());
					}
				} else {
					x++;
					System.out.println("你没有做出正确的选择，你逃跑了。");
				}
			} else {
				x++;
				System.out.println("你逃跑了。");
			}
		} else if (x == ylzz.getAtX() && y == ylzz.getAtY() && ylzz.isAlive()) {
			System.out.println("你遇到了一个怪物！它叫 " + ylzz.getName());
			System.out.println("你当前的血量是 " + you.getHp());
			if (author.isAlive()) {
				System.out.println(
						"  .......    .*.  . .. ,.   ..... ....**.................*....   .          ...           ...... ..,]o/OOo\\`****......*=^              .                                 ...............             \r\n"
								+ "*...      ..**. ..  .*]o....*. .*. .....^***...........*`. ...            .   ...............,/OOOOOOOOOOOOOO\\`........=\\.                                                                           \r\n"
								+ ".[[******.*.  ..  ...=OO... .**...........*****.....***.. .....            ..   .......... ,OOOOOOOOOOOOOOOOOOOO......*oo.                                                                           \r\n"
								+ "..  .....   .*. .....=OO.......*......,]/oOOooo]*........*...*..       .    ...           ,OOOOOOOOOOOOOOOOOOOOOO`.....*o.                                     ...,]/\\]*..                           \r\n"
								+ ".[\\]*...**,.  .......oO/.. .....*...=OOOOOOOOOOOOo\\`...,*........*.... ..  . ......    .*OOOOOOOOOOOOOOOOOOOOOOOO^......*^                                  .*/OOOOOOOOOOo\\....                      \r\n"
								+ "*..         ...*.*...=o^.   ....,OOOOOOOOOOOOOOOOOOO`**........... . ..... ..  .........,OOOOOOOOOOOOOOOOOOOOOOOOO......*`                                 .oOOOOOOOOOOOOOO\\....                     \r\n"
								+ "oOOOOOoo^......*.*...=o*.      .=OOOOOOOOOOOOOOOOOOO..................,`..,....   ..... .OOOOOOOOOOOOOOOOOo[[oOOOO`........                              .=OOOOOOOOOOOOOOOOO*......                  \r\n"
								+ "=OOOOOOO^.....*o=o..****.      .=OOOOOOOOOOOOOOOOOOO*.................,\\**.\\......     .=Oo**,[oOOOOOOo[**...*oOOO^..*...*.                              *oOOOOOOOOOOOOOOOOO`......                  \r\n"
								+ "=OOOOOOO......*o=^..*`**.      .oOOOOOOOOOOOoooo[[o[*..................*OO`*,o....***.*..,*...**********......=oOO`......*..                            .*\\OOOOOOOOOoo^*[[[o`......                  \r\n"
								+ ".OOOOOOO.......*=^. .^**.      .***\\[[[[******.*,[oo[**..... ..........=Ooo^*.,*..     ..**..*,]*****,]]]]]*..*,o^.../`..=\\..                           ...**,[[*******.**[[`*......                 \r\n"
								+ ".\\OOOOOO.... ....^. .***.      .********************...................=\\.*]oo\\**.**........***[/***********....***.....*O^.                            ..**********]`**..**.........                \r\n"
								+ " =OOOOO/.      ..^  ..**.      .*,[[*****[[[`**......*.............*...=o**.*/[[**.****.....*=OOo`****oOOo`.......*.....*o*                             ..*,***********...............               \r\n"
								+ " =OOOOO^.       =^  .....      .*,ooo`**ooOOo`*...........*............=*..**......=Oo........****....****............*..*..    .                       ..,oo\\**]ooOo^*..............                \r\n"
								+ " ,OOOOO^.       =^  .....      .*****....****............... ...............*...  .=O^..........*.....................=^......  .                       ..***.....****..............                 \r\n"
								+ " .OOOOO^.      .=.  .....      .............................  .. ...   .......     .,`..  ....***********.......... .=o^.....*.                         .............................                \r\n"
								+ "  OOOOO^.      .=.  .....      ....********................   .  ...   ......      ....    ...*************......   .=o`......                          ...*********...............                  \r\n"
								+ "  =OOOO^.     ..o.  .....      ...****,]******......  ....       ..    .....      ....     ...***oooOoo*****....    ..*.. ....                          ...**********.......                         \r\n"
								+ "  =OOOO^.     ..o.   ....      ...**[ooo[`******...    ...       ..    .....      ....     ...***************...    .....  ....                         ...**[oooo/****....                          \r\n"
								+ "  ,OOOO.   . ..,o.   ....      ......**********....    ...       ..    .....      .... .........************....    .....    ..*.                        .....*********....                          \r\n"
								+ ". .OOOO.   ....=o.   ....   ........*********......    ...       ..    ......    .]/OOOOO^......************....    ....    ......                  ........*********......                          \r\n"
								+ "   OOOO. .....*oo.  .*]/OOo]]]*....*********..............    . ....   ...]OOOOOOOOOOOOOOO......************.....   =OO`.    ....   .        ...]/oo]]......********.........                        \r\n"
								+ "   =OOO. .....*oo]]]OOOOOOOOOOO.....*********.........,OO\\.   .......  .OOOOOOOOOOOOOOOOOO^......**********......  ,OOOOO\\`. ... ....    ..]OOOOOOOOOOO`.....********.........*]]]`.                 \r\n"
								+ "   =OOO.  ....=OOOOOOOOOOOOOOOOo....................]OOOOOOOO]*...... .OOOOOOOOOOOOOOOOOOOO^......................=OOOOOOOOOOO\\.*..    .OOOOOOOOOOOOOOOO*..................../OOOOOOO\\`.             \r\n"
								+ "   =OO^.    .,OOOOOOOOOOOOOOOOOo.................../OOOOOOOOOOO\\`....,OOOOOOOOOOOOOOOOOOOOOO...................../OOOOOOOOOOOOOOO\\`.  ./OOOOOOOOOOOOOOOO^..................,OOOOOOOOOOOO`.           \r\n"
								+ "   =OO^.    ,OOOOOOOOOOOOOOOOOOOO^...............*OOOOOOOOOOOOOOOOO./OOOOOOOOOOOOOOOOOOOOOOOO`.................,OOOOOOOOOOOOOOOOOOO\\./OOOOOOOOOOOOOOOOOOOo*...............=OOOOOOOOOOOOOOOO`         \r\n"
								+ "   .OO^.   .OOOOOOOOOOOOOOOOOOOOOO\\`............[oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`...............=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO]............*oOOOOOOOOOOOOOOOOOO`.       \r\n"
								+ "    OO^.  .OOOOOOOOOOOOOOOOOOOOOOOOO`.........]OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO............OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\\.........]OOOOOOOOOOOOOOOOOOOOOO\\.      \r\n"
								+ "    \\O*. .=OOOOOOOOOOOOOOOOOOOOOOOOOOO\\....]OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\\`......,/OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`...,OOOOOOOOOOOOOOOOOOOOOOOOOO^      \r\n"
								+ "    =O.. .OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo/OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.     \r\n"
								+ ".   .^.../OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^     \r\n"
								+ "..  .*..*OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.    \r\n"
								+ ".... ...OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.    \r\n"
								+ "...  ..=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\\.    \r\n"
								+ "....  .=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`    \r\n"
								+ "...   .=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`    \r\n"
								+ "...   .\\OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.    \r\n"
								+ "..   .=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^.   \r\n"
								+ "..   .OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^.   \r\n"
								+ ".    =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/..oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/.=OOOOOOOOOOO^.   \r\n"
								+ ".    =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\\..OOOOOOOOOOOOO^=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`.\\OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo`..oOOOOOOOOOO`    \r\n"
								+ "..   .OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO[*.*oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.  =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo`...*oOOOOOOOOO`.   \r\n"
								+ "*.   .\\OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`...*oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO. ..OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`.....,oOOOOOOOOO`.   \r\n"
								+ ".......=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^....\\OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^.....OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo......*OOOOOOOOO\\.   \r\n"
								+ "o]]]^. .OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/..    .\\OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/......OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^.      .OOOOOOOOOO.   \r\n"
								+ "......  .,oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^....   .=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/.\\^. ..=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/..      .*oOOOOOOOO.   \r\n"
								+ "..........\\OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO*... .   .=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO..      ,OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\\..       .=OOOOOOOO`.   \r\n"
								+ "..........=OOOOOOOOOOOOOOOO[........[OOOOOOOOOOOOOOO......   .=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^..      .OOOOOOOOOOOOOOOOOOOOOO/*......*[OOOOOOOOOOOOOOO`..       .OOOOOOOOO^.   \r\n"
								+ "..........=OOOOOOOOOOOOOO/*......*.***[OOOOOOOOOOOO^........ .=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO*..       =OOOOOOOOOOOOOOOOOOOo*......*****[\\OOOOOOOOOOOO..       ..OOOOOOOOO.    \r\n"
								+ "..........oOOOOOOOOOOOOOO/**************,oOOOOOOOO^...........oOOOOOOOOOOOOOOOOOOOOOOOO/.......,\\OOOOOOOOOOOOOOOOO*..       .OOOOOOOOOOOOOOOOOOOOo**************,]oOOOOOOOO`        ..=OOOOOOOO^.    \r\n"
								+ ".........=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO*..........=OOOOOOOOOOOOOOOOOOOOOOO*........***=OOOOOOOOOOOOOOO^...       .OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^.        ..\\OOOOOOOO^     \r\n"
								+ "........oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/.........../OOOOOOOOOOOOOOOOOOOOOO..*****************=OOOOOOOOo*.         /OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO[.        ../OOOOOOOO`     \r\n"
								+ ".......,OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOoo^*..........*]oOOOOOOOOOOOOOOOOOOOO*****]`*****]]/oOOOOOOOOOOOO/.          OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/\\*.        ..,oOOOO]]*..    \r\n"
								+ ".......\\OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^.........*oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo.           OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`.        .OOOOOOOOo...    \r\n"
								+ ".......OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`.    ..**,OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo.          =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo..        .OOOOOOO\\.....   \r\n"
								+ ".......oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`.      ..oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo.         .OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^..       .OOOOOOOO`......  \r\n"
								+ ".......oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\\`..    .*OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo.         ,OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO]*..    .*OOOOOOO\\........ \r\n"
								+ "       .OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^......=oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO..        =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo*.....,oOOOOOOOO^.........\r\n"
								+ "       .OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`...     .*oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.        .OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/....     .,OOOOOOOo*.........\r\n"
								+ "       =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo..        .=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\\.. ..=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^.        ..OOOOOOo**.........\r\n"
								+ "       =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^.           .*,[OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\\]OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO..          ..,[[[*...........\r\n"
								+ "       =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^.         ......=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO[[/[....  .OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.          .........,**.......\r\n"
								+ "       .OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^..        ......=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\\           OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo.          ......             \r\n"
								+ "       =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`.....     ......=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^..         .\\OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo.          ......             \r\n"
								+ "      .=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^......    ......=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO. .         ......,\\OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^.           ......            \r\n"
								+ "      .=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO[[.........   .....=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.  .     ...........=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/[..            .....            \r\n"
								+ "       .OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo^..........  ....=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.  ..   .............\\OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo`.            .....            \r\n"
								+ "       =OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO/..     ...      .OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.  ..   .............*OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`.                             \r\n"
								+ "       ,OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO*..              ,OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.  .    .............=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo..        .                    \r\n"
								+ "...   .=OOOOOOOOOOOOOOO/[[\\OOOOOOOOOOOOOOOOOO^.               =OOOOOOOOOOOOOOOOOOOOO/OOOOOOOOOOOOOOOOOOOOOOO^...   .............*oOOOOOOOOOOOOOO[.*[OOOOOOOOOOOOOOOOOO*.  ......                     \r\n"
								+ ".......,OOOOOOOOOOOOO]]....,oOOOOOOOOOOOOOOO`.                .OOOOOOOOOOOOOOOOOOOOO*=OOOOOOOOOOOOOOOOOOOOOO`.     .............*oOOOOOOOOOOO\\].....,OOOOOOOOOOOOOOO]..                              \r\n"
								+ "...     *OOOOOOOOOOOOOO[..   .,]OOOOOOOOOOOOO`                .OOOOOOOOOOOOOOOOOOOOO],OOOOOOOOOOOOOOOOOOOOO^..   ................/OOOOOOOOOOOOO/[****]]]OOOOOOOOOOOOO/.                              \r\n"
								+ ".       =OOOOOOOOOOOOOo.     .=OOOOOOOOOOOOO\\.                .OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^..     .............=OOOOOOOOOOOOOO`.....*oOOOOOOOOOOOOOO.                          ... .\r\n"
								+ ".       .OOOOOOOOOOOOO`.     .\\OOOOOOOOOOOOO`..               .OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO..          ........=OOOOOOOOOOOOO[.......oOOOOOOOOOOOOOo.                              .\r\n"
								+ "...     .OOOOOOOOOOOOO].     .=OOOOOOOOOOOOOO`...             .OOOOOOOOOOOOOOOOOOOO. ..\\OOOOOOOOOOOOOOOOOOO/.            .  ....=OOOOOOOOOOOOO\\`......,OOOOOOOOOOOOOO`                              .\r\n"
								+ "....    .OOOOOOOOOOOOO]].     .=OOOOOOOOOOOOO]*.              .OOOOOOOOOOOOOOOOOOOO.    \\OOOOOOOOOOOOOOOOOO.                     ,OOOOOOOOOOOO]`.      .oOOOOOOOOOOOO]`                             .\r\n"
								+ "*... ...,OOOOOOOOOOOOOo.      .=OOOOOOOOOOOOO^.               .OOOOOOOOOOOOOOOOOOO^.. ..=OOOOOOOOOOOOOOOOOO.                     .OOOOOOOOOOOOO`       .OOOOOOOOOOOOO^.                             .\r\n"
								+ "^.......*OOOOOOOOOOOOO/..     .=OOOOOOOOOOOOO^.               .=OOOOOOOOOOOOOOOOOO`......OOOOOOOOOOOOOOOOOO.                     =OOOOOOOOOOOOO`       .OOOOOOOOOOOOO/.                              \r\n"
								+ "^.......=OOOOOOOOOOOOOO.....  ..OOOOOOOOOOOOO^.              ..=OOOOOOOOOOOOOOOOOO.......OOOOOOOOOOOOOOOOOO`.                   .=OOOOOOOOOOOOO^.       \\OOOOOOOOOOOOO.                              \r\n"
								+ "........=OOOOOOOOOOOOOO*........,OOOOOOOOOOOOO*..     .........=OOOOOOOOOOOOOOOOOO...   .=OOOOOOOOOOOOOOOOO^..............      ./OOOOOOOOOOOOO^        .=OOOOOOOOOOOO^.                             \r\n"
								+ "........=OOOOOOOOOOOOOO*........=OOOOOOOOOOOOO`................,OOOOOOOOOOOOOOOOOO..     .OOOOOOOOOOOOOOOOO^.....................=OOOOOOOOOOOOO^        .oOOOOOOOOOOOO^.                             \r\n"
								+ "`.*.....=OOOOOOOOOOOOO[.........=OOOOOOOOOOOOO`.................OOOOOOOOOOOOOOOOOO...    .OOOOOOOOOOOOOOOOO^....                .=OOOOOOOOOOOO/.         .OOOOOOOOOOOO`.                            *\r\n"
								+ "O\\*.....=OOOOOOOOOOOO/[.......=ooOOOOOOOOOOOOO^..............  ,OOOOOOOOOOOOOOOOOO^..    .OOOOOOOOOOOOOOOOO\\.                   .=OOOOOOOOOOOo`.          =OOOOOOOOOOO^.                           ..\r\n"
								+ "o\\*.....=OOOOOOOOOOOO]......*/oOO`=OOOOOOOOOOOo`................OOOOOOOOOOOOOOOOOOO..     ,OOOOOOOOOOOOOOOOO.                   .=OOOOOOOOOOOO\\.           =OOOOOOOOOOo*.                           .\r\n"
								+ "ooooooo/oOOOOOOOOOOOOO\\*...,oOOOO^.\\OOOOOOOOOOOO]..............=OOOOOOOOOOOOOOOOOOO*      .OOOOOOOOOOOOOOOOO.                   .,OOOOOOOOOOOOO`           .\\OOOOOOOOOOO`                            \r\n"
								+ ".....***oOOOOOOOOOOOOOO\\*,ooooOOO^.*OOOOOOOOOOOOO`]]`./OOOOO`..,OOOOOOOOOOOOOOOOOOO.      .OOOOOOOOOOOOOOOOO.                   .=OOOOOOOOOOOOO^            .OOOOOOOOOOOO.                   .       \r\n"
								+ "*.....**OOOOOOOOOOOOOOO\\/ooooooOO^..OOOOOOOOOOOOOoOOOOOOOOOOo\\ooOOOOOOOOOOOOOOOOOOO.      .OOOOOOOOOOOOOOOOO`.                   =OOOOOOOOOOOOO^.           .OOOOOOOOOOOO`.                 ..       \r\n"
								+ "......**[oOOOOOOOOOOOOOOooooOOOOOOoOOOOOOOOOOOOOOooo[[*........oOOOOOOOOOOOOOOOOOO^.       =OOOOOOOOOOOOOOOOO.                   .\\OOOOOOOOOOOO/..           .\\OOOOOOOOOOo*.              .....      \r\n"
								+ ".......**=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^.......    .\\OOOOOOOOOOOOOOOOOO^.       .OOOOOOOOOOOOOOOOO\\.                   ,OOOOOOOOOOOOO]..          .=OOOOOOOOOOO^.           ........      \r\n"
								+ "....*.**]/OOOOOOOOOOOOOOOOOOOOOoo[[[*\\OOOOOOOOOOOO^.           =OOOOOOOOOOOOOOOOOO^         =OOOOOOOOOOOOOOOOO.                   .=OOOOOOOOOOOOO*.           =OOOOOOOOOOOO.         ...........     \r\n"
								+ "]/OOOOOOOOOOOOOOOOOOOOOO/`*..........=OOOOOOOOOOOOOO\\.         ,OOOOOOOOOOOOOOOOOO^         .\\OOOOOOOOOOOOOOOO^                    .OOOOOOOOOOOOo..           .OOOOOOOOOOOOO\\.      .**.*.........   \r\n"
								+ "OOOOOOOOOOOOOOOOOOOOOOOO/*........   .=OOOOOOOOOOOOOOo.        .OOOOOOOOOOOOOOOOOO^           ,OOOOOOOOOOOOOOOO^                    .oOOOOOOOOOO/*.           .OOOOOOOOOOOOOOO\\...*****............  \r\n"
								+ "OOOOOo/[*...=OOOOOOOOOOO*..           .\\OOOOOOOOOOOOOO^.       .oOOOOOOOOOOOOOOOOOO...        .OOOOOOOOOOOOOOOO^                     =OOOOOOOOOO*.             =OOOOOOOOOOOOOOOOo/***............    \r\n"
								+ ".............=OOOOOOOOOO*.             =OOOOOOOOOOOOOO*        .oOOOOOOOOOOOOOOOOOO^...        .OOOOOOOOOOOOOOOO.                    .OOOOOOOOOO^.     .]]]]`..,OOOOOOOOOOOOOOOO`*.............      \r\n"
								+ "..........   .=OOOOOOOOO^.             .OOOOOOOOOOOOO^.        .oOOOOOOOOOOOOOOOOOO^..*.       .OOOOOOOOOOOOOOOO`                     .oOOOOOOOOO\\..]/OOOOOOOOOOOOOOOOOOOOOOOOo`............         \r\n"
								+ "              .\\OOOOOOOO*.              \\OOOOOOOOOOo..         .,oOOOOOOOOOOOOOOOOOO...**.....  =OOOOOOOOOOOOOOO^             .,]OOOO..=OOOOOOOO\\`*=OOOOOOOOOOOOOOOOOOOOOOOO/*...........            \r\n"
								+ "               =OOOOOOOo..              .OOOOOOOOOo..            ..,OOOOOOOOOOOOOOOO....**.....*=OOOOOOOOOOOOOOOO]]]]...   .]OOOOOOOO. .OOOOOOOO^..=OOOOOOOOOOOOOOOOOOOOOOO`.........                \r\n"
								+ "               .OOOOOOOO*.               =OOOOOOOOO*            ....OOOOOOOOOOOOOOO^...........*oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO. .oOOOOOOOO..=OOOOOOOOOOoo\\OOOOOOOOOO^....                     \r\n"
								+ "                =OOOOOOO^.               ,OOOOOOOOO.                ,OOOOOOOOOOOOOO^...........*oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO`..OOOOOOOO*.,OOOoo/[[***..,OOOOOOOOO^...                      \r\n"
								+ "                .=OOOOOOO*.               ,OOOOOOOO`.               .=OOOOOOOOOOOOO^...........*=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO^.,OOOOOOO\\*****...........,OOOOOOOO^..                       \r\n"
								+ "                 .OOOOOO`.                .=OOOOOO].                 .OOOOOOOOOOOOOO...........**\\OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo^..=OOOOOo`.................\\OOOOOOO`.                        \r\n"
								+ "                  .OOOOOO..                ,OOOOOOO.                  ,OOOOOOOOOOOOO.   .........*\\OOOOOOOOOOOOOOOOOooooooooooooooooo***...OOOOOO^........         .OOOOOOO^                         \r\n"
								+ "                   =OOOOO.                  \\OOOOOO.                   =OOOOOOOOOOOO.      ........\\OOOOOOOOOOOOOO^**********************..,OOOOO^...               =OOOOOO^                         \r\n"
								+ "                    .[[[..                  .OOOOO^.                   .OOOOOOOOOOOO^           ....OOOOOOOOOOOOOO..........................*[`**..                 .=OOOOO.                         \r\n"
								+ "                    .......                  .*`....                    OOOOOOOOOOOO^              .OOOOOOOOOOOOO^.................         .......                  .,[.....                        \r\n"
								+ "");
				System.out.println("你正打算做决定的时候，影流之主分身成了三个！并且朝你冲了过来！");
				int attack = ylzz.getAttack() * 3;
				System.out.println("你受到了 " + attack + " 点伤害！你被秒杀了！");
				System.err.println("GAME OVER");
				System.out.println(you.getName() + " 好像没有探索完全部内容就来螳臂当车了呢...");
				System.exit(0);
			} else {
				System.out.println("你选择——");
				System.out.println("1.攻击 2.逃跑(被禁用)");
				System.out.println("毫无疑问，你被迫进攻。");
				getBag(bag, hasCount);
				System.out.println("你选择了爷爷的拐杖！");
				System.out.println("战斗开始！");
				long shadow = 2;
				System.out.println("影流之主当前分身：" + shadow);
				System.out.println(
						".o`..\\*...oooo\\***..              ..,`   ...        ...........*[oo\\\\`**..................,oo[.. ...                     .......*******\\o`**[[[oooooooooo]***********.....................................................................********..................................                     \r\n"
								+ "`,OO..=o*..,ooooooo]*..     .....*o/.  ...             ...*.**...*,oooooo]*************,/o`..,*. ....      ...             .........****`*..******\\oooooooooooooo]******......................    ...........................***...................................................             ..       \r\n"
								+ "o*.\\o`.,oo*..,\\ooooooo^********=o/.  ..*. .        ...........***...*[oooooooo[[***,ooo/*...,O^.  ...       ....            .........****.....****,ooOooooooooooooooooooo\\**..............            ...........................................................................         ...     ... .  \r\n"
								+ "Oo].,O\\..\\Oo^...*,\\ooo/,****,`\\[.  ../^ ....*.     ....*.......*o***...***********ooo`...../o`*.  ...       . .....          ..........**.......*,]oOOOOoooooooooooooooooo*................        .............................................................................        ..           ... \r\n"
								+ "OOoo*.\\O].,oOoo`....*********..  ../o. ....=`.O].. ...**\\\\`.....*\\oo\\**.......**[**......=O`..,.   ...      ... .......         ......***...]/OO@@@@@@@@OOOOOoooo/****=oOO............................               ..........................................................                        ..\r\n"
								+ "OOoo/\\.,Oo*.,OOOO\\`...       ...]oO`. .*...*`.OO....,O...\\\\...**..,OOooo]*............*/Oo*..*^.   ..        ...  ...............*****..*]OO@@@@@@@@@@@@@@@@@OOo/*****,oOO^..             ........                          ...................................................                     .....\r\n"
								+ "oooOooo^.,Oo`..\\OOOOooo*`*,],]ooo^. ./`...*=OOO/ ....ooo^.=\\*..*`*..\\Oooooooo\\]]]]]/ooOO`...**...  ....     ......  ..........****`,.../O@@@@@@@@@@@@@@@@@@@@@@OO\\`***=OOOO..               ...                              .................................................                        ..*\r\n"
								+ "*..=Ooo^`*.,O\\]...,\\OOoooooooo/.. .,/. .`.*=OOOO^....oOOO\\.,\\`*.=OOOoOOOOoooooo\\oooooo`....o`.,/..     .  .  ....*..  ........***....,O@@@@@@@@@@@@@@@@@@@@@@@@@@O^..*oOOOO..                                            .....................................................                          *\r\n"
								+ "...*[oo\\``**.*\\Oo^*........... ..//...*=*.*oOOOO^...**.*oo^..,oOOOO@OOOOOOOOOoo]*,`**..../o*..o^=^...   ..........***..    ..... ..,OO@@@@@@@@@@@@@@@@@OO@@@@@@@@@O**=`=oOO.                                          ..,/OOOOOOOOOO\\`........................................                         .*\r\n"
								+ "......,OOo^=/o`..\\OOO\\]]]]]]]]oo[...=^....*oOOOo*.......=oooOOOO@@@@@@@@@@OOOOO\\*.....*/O/...*ooo/.Oo/`.*..........*`***..     ..]/OO@@@@@@@@@@@OOOOOOoo[[\\OO@@@@@O^***,[oO.                                        .,oOOOOOOOOOOOOOOOOo*.....................................                         ,O\r\n"
								+ ".......\\OoO\\]*\\`\\]...,[[[[[[... ..*oO^*o..*oOOOo*.........\\O@@@@@@@@@@@@@@@@@OOOO]]]/oo/`....******=/*^.,`=^.=..*`..*********,]]o/[..OOOOOOOOOOOooo/o`*****=OOOOOOO\\...*/OO^.                                      .=OOOOOOOO@@@OOOOOOOOOo*...................................                       . .[\r\n"
								+ ".......=O[\\OOoooooooo]]*.......****.***o*.*oOOOo*........,O@@@@O@@@OOOOOOOOOOOOOOOOOo`.....,*...***[*.]*.*o/*.=^.**....***`*,****...,O\\/oooooo/o[***********oOOOOOOO*.`*=/*^.                                     .=OOOOOOOOOOOOOOOOOOOOOOO\\.................................                        .o*.\r\n"
								+ ".......oO]OOOo**[o@@@@@@@@OOo^***.*=O\\=o..*oOOOo*........,OOOOOOOOOOOooo[[**ooooOOOOo***.*oo....*****o^..,OOo\\*O\\.,\\*........... .*/OO*****=\\oooooOOOOOo`***,\\OOOOOO*..**.,^.                                     ..*,oOOOOooo[[*******=oOOOo^..............................                         .\\O\\\r\n"
								+ ".......,OOOOo..**=@@@@@@@@@Oo*....*oOoOO*.*oOOOo*........*Oo`=oooooo`**********=oOOOOo*.*o\\^.....******.../Ooo\\/OO..ooo`........,oo`.*^=oOOOooooooooooo\\*******OOOOO`/O[..oo..                                    .....*****************oooOOO`.............................                          .[`\r\n"
								+ "........\\OOOOoo**=@@@@@@@@@O......=oOOO/*..OOOOo*........*Ooo^*,=oooooOOOoo]****oOOOOO`*=*.*........***...OOOOooooO\\..\\oooo`]ooo/`...]=oooooooooooOOOOOOo^******\\o\\oo^..]**o...                                   ....******\\ooOoo]`****[[oOOO\\*.............................                         ...\r\n"
								+ "........*oOooo`*..O@@@@@@@@O`.....*oOoOO*..OOOO^.........=OOOooooooooooooooo=o=ooooooo\\**[o^.........***..=oOOOOOoooO\\...*.........****ooOOOOo*,ooOOOOo/`*********=oo,OO`.=O\\...                                  ...,]oooooooooo********,o]*[[**.............................                     ...***\r\n"
								+ ".........=ooo[*...\\@@@@@@@@O*......*ooOO...\\ooOo.........=oooooooooooOOOOOoo`************`*,*..**...******=OOOOOOOOooooo]`.....********,oOOOo*****=oo/`***********,o/****.=OO/...                                 ...**,=oooooOOOOoo**************.............................                 .........\r\n"
								+ ".........*ooo*....=@@@@@@@@^........**\\^...=ooo^.. ......=OooOOOOo[[[\\oooo^***********ooo`*,`.*/`...*o^*,o=OOO]oOOOOOOoo`***[\\*...*****************]*****************...*=OO^...                                   ..*oOoo**,ooooo/***************.*............................               ..........\r\n"
								+ ".........*=oo*.....@@@@@@@O^..........=^...*ooo^..    ...=oo^********************************.=o`...*oo^**=OOOoo`*oOOoooo\\o\\ooo****.*******/oo]]/ooooo\\*************`*.*/oOO*.... ...                              ....**************************...**...........................         ...............\r\n"
								+ ".........**oo*.....O@@@@@@O^..........=^...*=oo^..   ....=o/***********,]]`***********...**oo../*...*=oo*.=OOo/ooOoo*.*..*OOOO/**....**/ooooOOOOOOoooooooo`********.../^*,o\\..... ........                         ....************************....*o*............................      .................\r\n"
								+ ".........**oo*... .O@@@@@@O^..........O^...*=oo*..  .....*o****=ooOOOOoooooo`********..***oo*..*....*,\\/*.*oo[*=oOo**.....oOOO*......**/ooOOooooOoOOOOooooooo`*****.**OOOoo/,/............                         ...**ooOOOOOooo\\**********....****..............................   ...............*...\r\n"
								+ "..........=oo*..  .O@@@@@@O*.........*O^...**\\o*..   ....*/****ooooooooooooooo`*****..*=ooo^*........****..*****\\o^*......=OOO^......**\\oooooOOOOOOOooooooooooo]**.../OOOo[\\o^,o`..... ...   ..                     ..*=oooooooooooooo]****...**]o^***...................................................\r\n"
								+ "..........*=o*..   =@@@@@@O*.........=O^...*****.    ....***..*ooOOOOOOooooooooo****.*]oooo***.............******[*.......*oOo*......***/ooooooooooooooooooooooo**...oOOO/.*o]/O^....       ...                     ..*oooOOOOOOoooooooo^**...*/ooo****..................................  ..............\r\n"
								+ "...........***..   =@@@@@@O*.........=O^....****..   ....***...*,ooooooooooooooo***..*\\oo^*****............*******........****........***[[ooooooo\\oooooOOooooooo*...\\OOO^..=O/*......      ....                    ...**[\\ooooooooooooo^**...*,o\\`****..................................  ..............\r\n"
								+ "*.........*o\\*...  =@@@@@@O*.........=O^....****..   ....=^.....***ooooooooooooo`*...*,[*****..............******.........****.........***..\\OOOOOOOOOOOOOoooooo`*...=OOo*..*[*.......      ........                ....****o/oooooooooo/*....*****..................................... ................\r\n"
								+ "*.........=oo^...  .O@@@@@O.........,OO^....****..   ....**..   .*,oooOOOOOOoooo^**.....***.................**..*.........****.........***...=OOOOOOOOOOOOoooo`***...*o/*..............     ..........            ... ....*=oOOOOOOoooooo^**.............................................................\r\n"
								+ "*.........=ooo\\*.. .O@@@@@o.........oOO^....****..   ....**..    ..*ooOOOOOooooo**..........................*.............***..........***....\\oOOOOOOOOOoooo/***.....**........*....... ...............        .  .........=ooOOOOOooo^`*.........................................................**..**\r\n"
								+ "`......***=oooo^*...=@@@@O^........,oOO^....****..   ....**..    ...*oOoOOooooo`***.............................*.........***..........***....=ooOOOOOooooo[*****...............*o^.............................. ...........=ooOOooooo****...........................................................[\\o\r\n"
								+ "^*......o^=OOOoo*...=@@@@O^....**..=OOO^....****..   ...****.  .....*,oooooo[****........*,.....................*.........***...........**....**\\ooooooo/`*****......]OO@OO\\]..*]oo**........................................*oooooo/`***................................................................\r\n"
								+ "o*.......*oooOoo*...=@@@@O`.....=o*oOOO^....****..   ...*oo*.  ......*********.....]]/OOOOOOOOOOo\\`.........*****.........****..........*.....***************...../O@@@@@@@@@@@@@@OOO]........................................**********....]]OOO@OOOOOOOO\\`.............................................\r\n"
								+ "o*.......*=ooooo*.. .@@@@O*.....**=oOOO^....****..   ...=oo*.  ......******.*....*oOO@@@@@@@@@@@@@@@OO\\....*******........****..........**......************...,O@@@@@@@@@@@@@@@@@@@@@@O\\.......................................*........*/OO@@@@@@@@@@@@@@@OO\\..........................................\r\n"
								+ "`*......**=Oooo^... .O@@@O*.....***oOOO^....****..   ...=oo*. ..................,OO@@@@@@@@@@@@@@@@@@@@@O`.******`.......**/]*........*]o`...........***.....,O@@@@@@@@@@@@@@@@@@@@@@@@@@O`.............................................,OO@@@@@@@@@@@@@@@@@@@@@O\\.......................................\r\n"
								+ "***.......*oooo*..  .=@@@O*.....**=oOOO^....****..   ..*=o\\]]`................,OO@@@@@@@@@@@@@@@@@@@@@@@@OO*****=^.......**oO\\....]OOOO@O^.................,O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`........................***...............*/OO@@@@@@@@@@@@@@@@@@@@@@@@O*.....................................\r\n"
								+ "***........*o/**..  .=@@@O*......*,ooOo*....*****..,]/OOOOOOOo*............*OOO@@@@@@@@@@@@@@@@@@@@@@@@@@OO\\****=^.......]OOO@@@@@@@@@@@@O*..............,O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`..............],]]OOOOOOO\\*...........*/OO@@@@@@@@@@@@@@@@@@@@@@@@@@@O^.....................................\r\n"
								+ "***.........****..  .=@@@O*.......*,ooo*.....*,OOOO@@@@@@@@@OO^.........]]OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O/****.....]]O@@@@@@@@@@@@@@@@O\\*...........,O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^..........]OOO@@@@@@@@@OO^........*]]O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^....................................\r\n"
								+ "***.........****..  .=O@Oo*........*ooo.....=OO@@@@@@@@@@@@@@@O\\*.....,OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Oo**,*....OO@@@@@@@@@@@@@@@@@@@O\\*.......,/O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O........OO@@@@@@@@@@@@@@O^*......]OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`..............................    .\r\n"
								+ "***.........****...  .O@Oo.........*oo/....=O@@@@@@@@@@@@@@@@@@@OO\\]]]OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Oo^*../@@@@@@@@@@@@@@@@@@@@@@@@O\\*.../O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\\.....oO@@@@@@@@@@@@@@@@@@@O\\**]OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O...................................\r\n"
								+ "***.........****...  .O@Oo.........=oo^...OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO**.*O@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O...=O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O............  .....................\r\n"
								+ "****........****...   =@Oo.........,oo^*/O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O\\/O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^*O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^...........  ....        ..       \r\n"
								+ "*****........****...  =OO^........*=/ooO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O*..........  ....    ......       \r\n"
								+ "*****........****.... .OO^........,oOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O*.. ........      ........        \r\n"
								+ "*]***........*****.....OO*.......=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^..........      .........        \r\n"
								+ ",oo`**.......******....=o*.....*OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`...........     ..........       \r\n"
								+ "]/OO\\**......oo\\*****..,o*...]OO@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOO[\\O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOO[[[\\O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O............     ..........       \r\n"
								+ "ooooo**.....*oOOo**.....***/O@@@@@@@@@@@@@@@@@@@@@@@@@O[*............=O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO/[[[[[[OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO`............,OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O/............     .........        \r\n"
								+ "oOOoo^*.....*oOOo^*.....,OO@@@@@@@@@@@@@@@@@@@@@@@@@@O\\`..............[OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O/*..............=O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O\\**.............,OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O......  .....     .......      ...\r\n"
								+ "ooOoo\\**.....=OOo**....*OO@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOo\\**..........*=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O*.................,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOo\\**...........*OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O......   ....    . ......      ...\r\n"
								+ "[oooo/**.....*oo/***..,OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOoo`*******/oOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`=oo**.............=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOO\\]******]]ooOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O......              .           ..\r\n"
								+ "**\\o*****......*****.*oO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOo]********..***/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^......                            \r\n"
								+ "*********.......**...=O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOooooooooOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O......                            \r\n"
								+ ".*********.......*...=O@@@@@@@@@@@@@@@@@@@@@@@@O[..,\\O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^...,O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O\\.....                             \r\n"
								+ "...*******...........*O@@@@@@@@@@@@@@@@@@@OOO[*.....,O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/`,O@@@@@@@@@@@@OOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO`.......=O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^.......                           \r\n"
								+ "....******............*O@@@@@@@@@@@@@O/[`....****...]O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO/`....O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O/[............/OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@o........  .                        \r\n"
								+ "....*******.............,[O/OOOOOO/[.......*******..,O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O/[..***....=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O..............=O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`........  .          ..            \r\n"
								+ "....*********...................*..........***`*...\\OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O[`......**.....O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`.............\\OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O\\.............      ......            \r\n"
								+ "...***************`..........]..............*]o*...oO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOO/OO/..........***.....,O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O.............*oO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO[..............       .....    ......  \r\n"
								+ "********]],]ooooooo.........=O^..............,...*=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOO*......=O`..........***.....=O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^.............=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO/................  ...........  ........ \r\n"
								+ "*****,]]oooo[[[**........... .....................*oOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOo\\]....`..................,O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`...............=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO^................  .......  .     ...... \r\n"
								+ "****/o`*....]]]oOOo`**,`.. .... ..................*,[o[[[`**,OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O*......................=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Oo`................*[\\o/[`***\\O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO....................... .         .......\r\n"
								+ "**,]`.*ooOOOOOO/[OOOOOo`......../OOO.....=OO\\`...............=O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O......................*=O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`**.........................*=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O.................................   ......\r\n"
								+ "*,`..*]oooOOOOOoo***,]*........,OO@O^....oOOO^...............*oOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O..........*************=O@@OOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O*............................*=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO............................. ...    .....\r\n"
								+ "******............********......oOOO.....\\/[..............***oOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^...****************..******......*O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O*..........................****oOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O*.................. ...............   .....\r\n"
								+ "...............**********.........................*******]/ooOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O/***************.......*****........,O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@o**...................*******]oOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`..........................................\r\n"
								+ ".............*.************.................*.....***/oooOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Oo************.........*****.........*o@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O/***.............*******/oOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O...........................................\r\n"
								+ "............******************..................**,oOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^*******......   ...******.....****=oO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`***.........*******]OOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O..............................    .........\r\n"
								+ "....****....*********************...............*=oO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^**..**.....      ...***********,]//OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^....*........*=`..*=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^...........................................\r\n"
								+ "...********************************.............*=OOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Oo........         ..********,ooOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@^............**=`...=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^...........................................\r\n"
								+ "....*********************************............=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^.........       ....****,oOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^............***\\..,OOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^...........................................\r\n"
								+ "****.**********************************......**=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^.........      ......**\\O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O*............**=oooOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`.........................................,@\r\n"
								+ "***********************************************oO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^.........     ........=ooOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O...  ..........**=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO*........................................,O@\r\n"
								+ "**********************************************oO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^....**.........    ...=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O..   ...........*OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO*...*....................................O@@\r\n"
								+ "*********************************************/O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O\\****.******..........=O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^.  ............,OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Oo*.*****................................./@@@\r\n"
								+ "*****..*************************************=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Ooo\\*********,`.......=O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O..  ...........,oO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Oo**...**................................/@@@@\r\n"
								+ "..*......*********************************,oOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O/`*\\ooo]]]]ooo/[*.....=O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O..............,OOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOO[***................................../O@@@@@\r\n"
								+ ".....       .............*****************/OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Oo`**=oooooooo*.....*./O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O..............=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Oo`**....***..........................,OO[.=OO[\r\n"
								+ "..       .............     ..............*OOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO^=o]]o[o[[********=ooO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O..............=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Oo/****....****...........................[\\o`. \r\n"
								+ ".        ...........         ....  ......=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO*...*ooo[****oooo****=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O*...........*oOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O/...******..*****..............................  \r\n"
								+ ".             .            ...       ...*oOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^...*oOOO^**,oo/`**]oOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O/*..........,OOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Oo*..******..*`****........       ............... \r\n"
								+ "..                                    ..oOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^....*oOO@OO`***,\\ooOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O/*...........=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^...*,*o^**..******...... ..................**.. \r\n"
								+ "......    .                            .=O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`...**oOO@@@@O^*...*oOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O*............=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O/*...**ooo\\`********..........***..............*..\r\n"
								+ "............  .                        .=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O\\....*ooOO@@@@@@O\\`**oO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^.. ...........=OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`****ooooooooo******...............*].............\r\n"
								+ "............                           .=O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O*..*/ooOO@@@@@@@@@OoO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O..             .OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O/..  ....... .      .....***....*.....\\...........\r\n"
								+ "...                                    .oO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O*..OOOoOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O..   ....      .=O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^.                   ....**.*..*..***..=`....***.*\r\n"
								+ "........           .........         ..*O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^..=OOooooOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^.    .........../O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO.                    .....*=`..**,`.....=`....****\r\n"
								+ ".........     ...............        ..oO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^..................O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O*.     .........OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O\\.                     .......*\\oo`..*`*.*O....****\r\n"
								+ ".............  ..... .......         .,O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`...              .O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O*..   .........,O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO.                     .....*,[[[[[oo*****o\\....*=o\r\n"
								+ "***********..    .   ....           ..OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O\\..              .,O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O*..... ...*....=O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`.                     ...********]]/]`**=o....*=o\r\n"
								+ "*************..   ........        ...,O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O.               .O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO*.....  ...  ..OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^.                      ...\\OOOOOOoooo`***o....**o\r\n"
								+ "......********..               ......O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/.              ..O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^... .....    .=O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O`.                      ....*OOO@Oooo^****o....**o\r\n"
								+ ".*.......******....... ............*O@@@@@@@@@@@@@@@@@@@@@@@@@OOOOO@@@@@@@@@@@@@@@@@@@@@@@@@OO`.              .O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^..          .=O@@@@@@@@@@@@@@@@@@@@@@@@@OOOOO@@@@@@@@@@@@@@@@@@@@@@@@@O`.                      .....*oOOOOoooo****o....**o\r\n"
								+ "....************..................*OO@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@OO..              .,O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^...     .....oO@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@O^.                      .....****oooooo*\\o`*o....*,o\r\n"
								+ "**......*********.............**,,OO@@@@@@@@@@@@@@@@@@@@@@@OO/[*..,OO@@@@@@@@@@@@@@@@@@@@@@O^.              ..O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Oo*..........=O@@@@@@@@@@@@@@@@@@@@@@OO[*....\\OO@@@@@@@@@@@@@@@@@@@@@O^.                     .....***..,OOoooo^***=o....*,^\r\n"
								+ "***..**************\\`..............\\O@@@@@@@@@@@@@@@@@@@OO`..       .,\\O@@@@@@@@@@@@@@@@@@@O^.       . .    .=@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Oo^........../O@@@@@@@@@@@@@@@@@@@@@/..       ..,O@@@@@@@@@@@@@@@@@@@O^.              ...........,\\oooOOOOoooooo**O^....**.\r\n"
								+ ".***..************................OOO@@@@@@@@@@@@@@@@@@O*.           .oO@@@@@@@@@@@@@@@@@@O^.      ..........O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO^........*OO@@@@@@@@@@@@@@@@@@@@O^...        .*O@@@@@@@@@@@@@@@@@@@O.            ..............**ooOOOOoo`*.*o\\oO^....*..\r\n"
								+ "Oo****************... ........  ..O@@@@@@@@@@@@@@@@@@@O^.            .\\@@@@@@@@@@@@@@@@@@@O^.     .........*]O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOO`.......=O@@@@@@@@@@@@@@@@@@@@O^....         .O@@@@@@@@@@@@@@@@@@O^.         .......*..........**oOOooooo\\`,o^oO........\r\n"
								+ "OO*****=o^********...           ./O@@@@@@@@@@@@@@@@@@OO.              =O@@@@@@@@@@@@@@@@@@O^.    ........*,oO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^..\\Oo\\]]]]/OO@@@@@@@@@@@@@@@@@@@OO*...          .O@@@@@@@@@@@@@@@@@@O^.. .........................**OOoooooooooo^o^........\r\n"
								+ "OO^****oo^*********....        .=O@@@@@@@@@@@@@@@@@@OO*..            .=O@@@@@@@@@@@@@@@@@@O^.............=OOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O*....*,,oooO@@@@@@@@@@@@@@@@@@@@Oo*..            .O@@@@@@@@@@@@@@@@@@O^...........  .............**/oOoOOOoo/\\ooo*o^........\r\n"
								+ "OO^***=oo`*,****=^/*...........,OO@@@@@@@@@@@@@@@@@@O^.....          ..O@@@@@@@@@@@@@@@@@@OO.............,oOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O.... .....,OO@@@@@@@@@@@@@@@@@@O/....            .=O@@@@@@@@@@@@@@@@@@O.....................*..*]/OOOOOOOOOoo]/oo*o^........\r\n"
								+ "OO/**=^o`.*/***=oo^*...........=O@@@@@@@@@@@@@@@@@@OO*......          .=O@@@@@@@@@@@@@@@@@OO..............=O@@@@@@@@@@@@@@@@@@@@@@@@@O/[`........\\@@@@@@@@@@@@@@@@@@@@@@@@@O^.         .oO@@@@@@@@@@@@@@@@@O^.                .=O@@@@@@@@@@@@@@@@@@o.....................,ooooOOOOOoOooo^=/,oo^*o....*...\r\n"
								+ "O^****o*.,oo**,ooO^...........,OO@@@@@@@@@@@@@@@@@Oo*........   ....  .=O@@@@@@@@@@@@@@@@@O^.............=O@@@@@@@@@@@@@@@@@@@@@@@@@O^.         ..O@@@@@@@@@@@@@@@@@@@@@@@@O`.        .=O@@@@@@@@@@@@@@@@@@/..   ...............=O@@@@@@@@@@@@@@@@O^..      ..... ..     .**oOOOOOooOoooo/\\],o`*=O`***...\r\n"
								+ "o`***o*.,oo/*,ooOO^..........=O@@@@@@@@@@@@@@@@@@Oo*....................O@@@@@@@@@@@@@@@@OO*.............OO@@@@@@@@@@@@@@@@@@@@@@@@Oo*...........=O@@@@@@@@@@@@@@@@@@@@@@@@O..        .O@@@@@@@@@@@@@@@@@@O........ ............=O@@@@@@@@@@@@@@@Oo...       ..           .*ooOOOOooOOooo]]o`ooo`,O\\`*.*/\r\n"
								+ "*****...*,oo*=oOOO^..........=O@@@@@@@@@@@@@@@@@@O^*....................O@@@@@@@@@@@@@@@@O\\.............=O@@@@@@@@@@@@@@@@@@@@@@@@Oo*............=O@@@@@@@@@@@@@@@@@@@@@@@@O*........./O@@@@@@@@@@@@@@@@@O\\..................  .=O@@@@@@@@@@@@@@@O\\...     ...........    .*^.=OOOOoOoooooooooo`,o`]`*\\oO\r\n"
								+ "****.****`,`,ooOOO***.......*oO@@@@@@@@@@@@@@@@@@O*.....................O@@@@@@@@@@@@@@@@Oo...........*=OO@@@@@@@@@@@@@@@@@@@@@@@OO`*............=O@@@@@@@@@@@@@@@@@@@@@@@@O*........,O@@@@@@@@@@@@@@@@@@O^.............       .*O@@@@@@@@@@@@@@@@o...    ..............  .*oooOOOOO/oooooooooooooooooooo\r\n"
								+ "**..**]oo\\`*\\oOOO[****......,OO@@@@@@@@@@@@@@@@@@O^*....................O@@@@@@@@@@@@@@@@@O^....***.*.*=O@@@@@@@@@@@@@@@@@@@@@@@@O`..............=O@@@@@@@@@@@@@@@@@@@@@@@@O^........=O@@@@@@@@@@@@@@@@@@O`.                   ..O@@@@@@@@@@@@@@@@o...     ................*oOOOOOOOO*\\ooo,ooooOOOooooooo\r\n"
								+ ".....*=ooO^*/oOO^***/`....../O@@@@@@@@@@@@@@@@@@O/*.....*...............O@@@@@@@@@@@@@@@@@O^*....**..**oO@@@@@@@@@@@@@@@@@@@@@@OO`...............*\\O@@@@@@@@@@@@@@@@@@@@@@OO*......../O@@@@@@@@@@@@@@@@@O^..                    .O@@@@@@@@@@@@@@@@O*..     ................*oOOOO@@@@O\\*[oooo`,\\ooooooooO\r\n"
								+ "..*****ooo*,ooOOO\\*=o^*....*oO@@@@@@@@@@@@@@@@@@O**....*/*.............*O@@@@@@@@@@@@@@@@@O^*........*,oO@@@@@@@@@@@@@@@@@@@@@@O`.................*O@@@@@@@@@@@@@@@@@@@@@@O/*........O@@@@@@@@@@@@@@@@@@O*.                     .O@@@@@@@@@@@@@@@@O*..       .............,/[oO@@@@@@@@@@OOOOOO]**\\ooooO/\r\n"
								+ "***,]oooo^**oOOOOOOo/****..*oO@@@@@@@@@@@@@@@@OO`***..*oOOO*...........*O@@@@@@@@@@@@@@@@Oo**....,oOOOOO@@@@@@@@@@@@@@@@@@@@@@O^...................=O@@@@@@@@@@@@@@@@@@@@@O*.........O@@@@@@@@@@@@@@@@@O`..                 .....O@@@@@@@@@@@@@@@OO..        ............*[[[oO@@@@@@@@@@@OOOOO@@@@@@@@@O\r\n"
								+ "*/*oooooo^*=oOOOOOOOoo`*****O@@@@@@@@@@@@@@@@@@O***.*=OOOOO^*..........*O@@@@@@@@@@@@@@@@OO`....*OOOO@@@@@@@@@@@@@@@@@@@@@@@@O/....................=O@@@@@@@@@@@@@@@@@@@@OO*.........O@@@@@@@@@@@@@@@@@O..                 ......O@@@@@@@@@@@@@@@OO..      ..............`]*]oOOOOOOOOOOOOOo*=O@@@@@@@@@@\r\n"
								+ "*`*,\\oooo**oooOOOOOOoo]]o\\]oO@@@@@@@@@@@@@@@@@OOo`**,oOOOOO^*.........**O@@@@@@@@@@@@@@@@O^*......**oOO@@@@@@@@@@@@@@@@@@@@@@O*....................=O@@@@@@@@@@@@@@@@@@@@Oo..........OO@@@@@@@@@@@@@@@@O\\..               .......O@@@@@@@@@@@@@@@Oo..   . .....................********[\\oooooOOOOOO@@@@@\r\n"
								+ "*.***oooo^/oooOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@Oo]/OOOOOOO\\*..........*O@@@@@@@@@@@@@@@@@O`...*oOOOOO@@@@@@@@@@@@@@@@@@@@@@Oo.....................=O@@@@@@@@@@@@@@@@@@@@O^..........OO@@@@@@@@@@@@@@@@@O`.                     .O@@@@@@@@@@@@@@@OO*.. ................................*****.*****[[\\ooOO\r\n"
								+ "*****=[oooooOOOO[[[ooooooooOOO@@@@@@@@@@@@@@@@@@@OOOOOOOOOOO*.....******O@@@@@@@@@@@@@@@@@@OO^.*oOOOOO@@@@@@@@@@@@@@@@@@@@@@Oo.....................=O@@@@@@@@@@@@@@@@@@@@O^..........OO@@@@@@@@@@@@@@@@@O^.                     .oO@@@@@@@@@@@@@@@O^.................................................****\r\n"
								+ "..*******,oOOO`.,o\\*,ooooooOOO@@@@@@@@@@@@@@@@@@@OOOOOOOOOOO*....****]]oO@@@@@@@@@@@@@@@@@@@@OO\\`,oOOO@@@@@@@@@@@@@@@@@@@@@@Oo.....................=O@@@@@@@@@@@@@@@@@@@@O^..........=O@@@@@@@@@@@@@@@@@@o..                    .=O@@@@@@@@@@@@@@@O^.....................................................\r\n"
								+ "*`*******=o/\\^**.\\o`*=ooooooOOO@@@@@@@@@@@@@@@@@@OOOOOOOOOOO*****/OO@@@@@@@@@@@@@@@@@@@@@@OOOOooOOOOOO@@@@@@@@@@@@@@@@@@@@@@O`.....................=O@@@@@@@@@@@@@@@@@@@@Oo..........*OO@@@@@@@@@@@@@@@@@O\\..                   .=O@@@@@@@@@@@@@@@O^.....................................................\r\n"
								+ "/************=^=ooo**=oooooooOO@@@@@@@@@@@@@@@@@@OOOOOO@@@@OOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@O^*..*oOOOOO@@@@@@@@@@@@@@@@@@@@@O`......................=O@@@@@@@@@@@@@@@@@@@@Oo*..........oO@@@@@@@@@@@@@@@@@@O`.                .....=O@@@@@@@@@@@@@@O^.....................................................\r\n"
								+ "****************\\`**,oooooooooOO@@@@@@@@@@@@@@@@@@OO@@@@@@@@@@@@@@@@@@@OOOO@@@@@@@@@@@@@@OO`....=OOOOOO@@@@@@@@@@@@@@@@@@@@O.......................=O@@@@@@@@@@@@@@@@@@@@Oo*...........\\O@@@@@@@@@@@@@@@@@O\\..        .............O@@@@@@@@@@@@@@O^.....................................................\r\n"
								+ "****************`**=oooooooooooO@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOooo[[[*\\O@@@@@@@@@@@@@@O^.....OOO@OO@@@@@@@@@@@@@@@@@@@@O.......................=O@@@@@@@@@@@@@@@@@@@@Oo*............oO@@@@@@@@@@@@@@@@OO`......................=O@@@@@@@@@@@@@O\\....******...........................................\r\n"
								+ "]`***********]**.*/ooooOOOOOO@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOoo[***.........=O@@@@@@@@@@@@@@@\\.....oOOOOO@@@@@@@@@@@@@@@@@@@@O*......................=O@@@@@@@@@@@@@@@@@@@@O^..............OO@@@@@@@@@@@@@@@OO^......................=O@@@@@@@@@@@@@@O].,oooo******.**.....................................\r\n"
								+ "\\^.*********...]OOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOo`*****................O@@@@@@@@@@@@@@@OO`...=OOOOO@@@@@@@@@@@@@@@@@@@@@^......................=O@@@@@@@@@@@@@@@@@@@@O^...............O@@@@@@@@@@@@@@@OO^......................=O@@@@@@@@@@@@@@@@OOOOoooo/*******............................        \r\n"
								+ "`,*..*]]\\]]OO@@@@@@@@@@@@@@@@@OOOOOO@@@@@@@@@@@@@@OO`......................oO@@@@@@@@@@@@@@@OO*..=OOOOO@@@@@@@@@@@@@@@@@@@@@O......................=O@@@@@@@@@@@@@@@@@@@@O^...............=O@@@@@@@@@@@@@@OO^......................=O@@@@@@@@@@@@@@@@@OOOooo[[\\o\\/[[^...................... ...          \r\n"
								+ "..,OO@@@@@@@@@@@@@@@@@OOOOOOoo[[`*oO@@@@@@@@@@@@@@Oo*......................=O@@@@@@@@@@@@@@@OO^..*OOOOO@@@@@@@@@@@@@@@@@@@@@@^.....................,O@@@@@@@@@@@@@@@@@@@@@O*...............oO@@@@@@@@@@@@@OO.......................=O@@@@@@@@@@@@@@@@@OOo/******.*.......................                \r\n"
								+ "ooOO@@@@@@@@@@@OOOOOoooo[******...*OO@@@@@@@@@@@@@O^............          ..O@@@@@@@@@@@@@@@OO^..*OOOOO@@@@@@@@@@@@@@@@@@@@@@\\.....................*OO@@@@@@@@@@@@@@@@@@@@O^...............,O@@@@@@@@@@@@@O/........................O@@@@@@@@@@@@@@@@@OOo****.........................                   \r\n"
								+ "oOO@@@@@OOOOOOo/[`******...........*OO@@@@@@@@@@@@O^.....                  .=O@@@@@@@@@@@@@@OO*...OOOOOO@@@@@@@@@@@@@@@@@@@@@@\\.....................*OO@@@@@@@@@@@@@@@@@@@O^................=O@@@@@@@@@@@@Oo...,/O@@@@OOO\\]/]]]]\\/OOO@@@@@@@@@@@@@@@@OOo^**..........................                    \r\n"
								+ ".\\OOOOOOoo[******...................*OO@@@@@@@@@@@O^.....                  ..O@@@@@@@@@@@@@@O^....OOOOOO@@@@@@@@@@@@@@@@@@@@@@Oo`....................*OO@@@@@@@@@@@@@@@@@@O^.................=O@@@@@@@@@@@@OOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOo`**...........................                    \r\n"
								+ "[`***********........................\\O@@@@@@@@@@O\\......                  ..\\@@@@@@@@@@@@@Oo*....=OOOOO@@@@@@@@@@@@@@@@@@@@@@Oooo\\*..................,O@@@@@@@@@@@@@@@@@@O^.......]OOOOOO....OO@@@@@@@@@@OOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO/*..................                                \r\n"
								+ "................................... ..\\O@@@@@@@@@O/......                   .=O@@@@@@@@@@@OO/.......,[OOO@@@@@@@@@@@@@@@@@@@@@Oooooo******.............,O@@@@@@@@@@@@@@@@@@O..,/O@@@@@@@@O....=O@@@@@@@@@@OOOO@@@@@@@@@@@@@@@@@OOOOOOOO@@@@@@@@@@@@Oo`*................             .                    \r\n"
								+ "......................................,O@@@@@@@@@OO`.....                   .=O@@@@@@@@@@@O/........***..=@@@@@@@@@@@@@@@@@@@@Oooooo^****=oooooo\\]/ooOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O.....O@@@@@@@@@@OoOO@@@@@@@@@@@@OOOOOOOooooOO@@@@@@@@@@@@O`*...............                                    \r\n"
								+ "...............................     ...\\O@@@@@@@@OO*....                    .=O@@@@@@@@@@@O^..............O@@@@@@@@@@@@@@@@@@@Ooo/`*`****=oooOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^....=O@@@@@@@@@OooOO@@OOOOOOOOOOOoooo[`***O@@@@@@@@@@@@O^...............                                      \r\n"
								+ ".............................        ..=O@@@@@@@@@O*....                    ..O@@@@@@@@@@@O^..............,O@@@@@@@@@@@@@@@@@@O[*********=oOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\\...,O@@@@@@@@@@O/=OOOOOOOooooo/``********,O@@@@@@@@@@@@o*.............                                       \r\n"
								+ "....               ............      ...\\O@@@@@@@@\\]...                      .\\O@@@@@@@@@OO*...............\\@@@@@@@@@@@@@@@@@@O**********=ooOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O^...=O@@@@@@@@O\\]*ooooooo[`******.........*O@@@@@@@@@@O\\..........                                           \r\n"
								+ "...                 .............  . . ..O@@@@@@@OO/..                       .=O@@@@@@@@@O[.. .............*O@@@@@@@@@@@@@@@@@O**********=oooOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOO....OO@@@@@@@OO[************.............*OO@@@@@@@@@O`.......                                              \r\n"
								+ ".....  .            ..............      .=O@@@@@@@@O...                      ..O@@@@@@@@@@O*..  ............=@@@@@@@@@@@@@@@@@O*....******,ooooOOOOOOOOOO@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOO^...*O@@@@@@@@O^..........................=O@@@@@@@@@O^...                                                  \r\n"
								+ ".....  .            ..............      .=O@@@@@@OO^..                       ..\\O@@@@@@@@@O*..  .............O@@@@@@@@@@@@@@@@O.........******[[oooooOOOOO@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOooo`...O@@@@@@@Oo*..........................=O@@@@@@@@@O^..                                                   \r\n"
								+ "....... ............................   ...oOOOOOOoo/`..                       .=O@@@@@@@@@O...  .............,O@@@@@@@@@@@@@@@O...............******,[\\ooO@@@@@@@@@@@@@@@@@OOooooooooooooooooo^...\\OOOOOOOOo^...........................O@@@@@@@@@O^..                                                   \r\n"
								+ "....... ............... ......          ....*,oo/,***...                      ..OO@@@@@@@OO*.   ..............,O@@@@@@@@@@@@@@O......................****\\O@@@@@@@@@@@@@@@@Oo****************,`*..******[[[[**.......................  .=O@@@@@@@@O^..                                                   \r\n"
								+ ".......  .........  .                   ....*********...                       .=OOO@OOO/[*....................=@@@@@@@@@@@@@@O..........................*O@@@@@@@@@@@@@@@@O`.......................**********................. .       .\\O@@OOoo/*....                                                  \r\n"
								+ ".......  .............. ..             ....*********....                       ...**,[****...........  .     ..=O@@@@@@@@@@@@@O...........................=@@@@@@@@@@@@@@@@^........................**********.............             ..,[[`****......                                                 \r\n"
								+ "...... .....................           ...*********....                        ...*******..........           ..O@@@@@@@@@@@@@O...........................=@@@@@@@@@@@@@@@@^.......................*********.............              ..*********.......                                                \r\n"
								+ "...... ...............                 ...*********....                       ...*******.............         ..\\@@@@@@@@@@@@@O...........................=@@@@@@@@@@@@@@@O^.................. ....*********......                     ...*******...........                                             \r\n"
								+ "........                              ...**********....                       ...******************....         .O@@@@@@@@@@@@@^.   .......................O@@@@@@@@@@@@@@O................    ...**********....                       ....***************....                                           \r\n"
								+ "                                      ...**********.....                       ..******]]ooOOOOOOo*...          .=@@@@@@@@@@@@@^        .......         . .=@@@@@@@@@@@@@@^.......            ....***,]]]***....                        ...******/ooOOOOOO^*..                                           \r\n"
								+ "                                      ..*/ooOOOOOoo\\`...                        ..****ooOOOOOOOOOOOO\\...         .\\@@@@@@@@@@@O`.                         ..O@@@@@@@@@@@@@O...                ...*oooOOOOOOo*...                        ...**/oooOOOOOOOOOOO\\...                                         \r\n"
								+ "                                     ..*,oooOOOOOOOOo*..                        ...**=oOOOOOOOOOOOOOO\\...         ,O@@@@@@@@@OO^..                         .\\@@@@@@@@@@@@@O.                  ..*\\ooOOOOOOOOo*..                         ...*\\ooOOOOOOOOOOOOOO*.                                         \r\n"
								+ "                                     ..,ooOOOOOOOOOOo*..                          ..*=oOOOOOOOOOOOOOO\\...         .=OOOOOOOOOOoo*..                         .O@@@@@@@@@@@@^.                 ..*,ooOOOOOOOOOO^..                          ...**ooOOOOOOOOOOOOO`..                                        \r\n"
								+ "                                    ..*ooOOOOOOOOOOO`...                            ..**\\OOOOOOOOOOO[[..          .***=oooooooo***.                         .\\@@@@@@@@@@@O.                  ..=ooOOOOOOOOOo/`..                            ...*,ooOOOOOOOOOO/`..                                        \r\n"
								+ "                                     .*=oOOOOOOOOOOo*...                              ...*oOOOOOOOOOo`.          ...*************..                          .\\@@@@@Oo[[*.....               ..*/oOOOOOOOOOO/..                                ...*\\oOOOOOOOo/..                                         \r\n"
								+ "                                     ..*,oOOOOOOOOO/..                                   .............         ....**************...                          .\\Oooo***........              ...*\\oOOOOOOOOo*.                                     ...........                                           \r\n"
								+ "                                       ...*ooooo[*...                                                          ...***************...                       ...*,o^*****..........              ...*\\ooooo`...                                                                                            \r\n"
								+ "                                          ........                                                            ....***************....                   ...****]o^*******...........              ........                                                                                               \r\n"
								+ "                                                                                                             ....****************........  ......  ........***,\\o^********...........                                                                                                                    \r\n"
								+ ".                                                                                                          ......****************................  .........***==o\\*******,]]/OOOoo\\..                                                                                                                   \r\n"
								+ ".                                                                                                        .......***,,oooooooo\\***............................****oooooooOOOOOOOOOOOo]..                                                                                                                  \r\n"
								+ "..                                                                                                  ..........**/ooOOOOOOOOOOOOo`*............................**,ooooOOOOOOOOOOOOOOOo^..                                                                                                                 \r\n"
								+ "....    .                                                                                           ..........**ooOooOOOOOOOOOOOO*.............................**oooOOOOOOOOOOOOOOOOOO\\.                                                                                                                 \r\n"
								+ ".. .    .                                                                                               ......*oOOOOOOOOOOOOOOOOO^..............................**\\oOOOOOOOOOOOOOOOOOOO^.                                                                                                                \r\n"
								+ "..........      ...                                                                                  .........*ooOOOOOOOOOOOOOOOO`................................*[oOOOOOOOOOOOOOOOOOO^.                                                                                                                \r\n"
								+ "..........      .                                                                                      ......*=ooOOOOOOOOOOOOOOOo....................................*,oOOOOOOOOOOOOOO/..                                                                                                                \r\n"
								+ "...                                                                                                     .....=/ooOOOOOOOOOOOOOOO`........................................*,[[OOOOo[[*...                                                                                                                 \r\n"
								+ "..                                                                                                      .....**\\ooOOOOOOOOOOOO/*......................................................                                                                                                                   \r\n"
								+ "                                                                                                         .......***,[[[[[[[**.................. ...        .  ..    ...........                                                                                                                          \r\n"
								+ "                                                                                                           .........******................  .                           ..                                                                                                                               \r\n"
								+ "                                                                                                                .........................  .                                                                                                                                                             \r\n"
								+ "                                                                                                               ....         .........                                                                                                                                                                    \r\n"
								+ "                                                                                                                                                                                                                                                                                                         \r\n"
								+ "                                                                                                                                                                                                                                                                                                         \r\n"
								+ "");
				try {
					while (ylzz.getHp() > 0) {
						if (you.getHp() <= 0) {
							you.setAlive(false);
						}
						if (you.isAlive()) {
							int isBlock = (int) (Math.random() * 8);
							if (isBlock % 2 == 0) {
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								System.out.println(you.getName() + " 使用 爷爷的拐杖，对 " + ylzz.getName() + " 造成了 "
										+ you.getAttack() + " 点伤害！但 " + ylzz.getName() + " 格挡了一部分伤害！");
								ylzz.setHp(ylzz.getHp() - you.getAttack() + ylzz.getDefense());
								if (ylzz.getHp() > ylzz.getMaxHP()) {
									ylzz.setHp(ylzz.getMaxHP());
								}
							} else {
								System.out.println(you.getName() + " 使用 爷爷的拐杖，对 " + ylzz.getName() + " 造成了 "
										+ you.getAttack() + " 点伤害！");
								ylzz.setHp(ylzz.getHp() - you.getAttack());
							}
						} else if (!you.isAlive()) {
							System.err.println("你死了！GAME OVER！");
							System.exit(0);
						}
						if (ylzz.getHp() > 0) {
							int isBlock = (int) (Math.random() * 8);
							if (isBlock % 2 == 0) {
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								System.out.println(ylzz.getName() + " 现在的血量是 " + ylzz.getHp());
								System.out.println(ylzz.getName() + " 使用 " + shadow + " 个分身对 " + you.getName() + " 造成了 "
										+ ylzz.getAttack() + " 点伤害！但 " + you.getName() + " 格挡了一部分伤害！");
								you.setHp(you.getHp() - ylzz.getAttack() + you.getDefense());
								if (you.getHp() > you.getMaxHP()) {
									you.setHp(you.getMaxHP());
								}
							} else {
								System.out.println(ylzz.getName() + " 现在的血量是 " + ylzz.getHp());
								System.out.println(ylzz.getName() + " 使用 " + shadow + " 个分身对 " + you.getName() + " 造成了 "
										+ ylzz.getAttack() + " 点伤害！");
								you.setHp(you.getHp() - ylzz.getAttack());
								System.out.println("影流之主：“草！怎么打不动你这个逼崽子！MD，逼爷继续分身！”");
								shadow = shadow * 2;
								System.out.println("影流之主当前分身：" + shadow);
							}
						}
						System.out.println(you.getName() + " 当前的血量是 " + you.getHp());
					}
				} finally {
					System.out.println(ylzz.getName() + " 被打败了！");
					ylzz.setAlive(false);
					System.out.println("影流之主 灰飞烟灭了！");
					System.out.println("影流之主 掉落了一封信，你捡了起来，并准备打开时，天旋地转！\n");
					System.out.println("这里是一个无法用言语描述的空间。");
					System.out.println(
							"*****************`*\\,[`,************^ooO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOooOOOOO[*.*=oOO/``.....*,/ooOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@Ooo\\/oooooooooooo[[[[oooooooooooo[[[*ooooooooooooooooo\\/ooooooooooooooooooooooooooooooooooooo\r\n"
									+ "****************=o**,[`,`********=ooooO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOO[`...*]***........***,]*ooooOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@Oo\\]oooooooooooooooooooooooo]/oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo\r\n"
									+ "[[[[***=************oo***********,ooooO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOo[`**..]]/`......************oooooOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@Oooooo\\oooooooooooooooo*,oooooooooooooo=ooooooooooooooooooooooooooooooooooooooooooooooooooo\r\n"
									+ "]]]]***=oooo********oo*******,ooooooO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOo[`..........*.....********oooOoOOO@@@@@@@OOO@@@@@@@@@@@@@@@@@@@@@@@OooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooOoOooOOooooOOOO\r\n"
									+ "****oooooooo**/ooooo****=o*ooooooooOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOooo[`..........*..*..........***]]OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooOOoOoooOOoOooooo\r\n"
									+ "****ooooooooooooooooooooooooooooooOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOO/[`*************..*.*.**.*....*=ooOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooOOoOoooOOoOoOOOO\r\n"
									+ "************ooooooo^ooooooooooooooO@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOo..*]]oo^**]]]`]]]********.....**/ooOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@OoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooOoooOOOOOOOOOOOO\r\n"
									+ "************ooooooo^oooooooooooooOO@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOoo*/OOOoo`*****oo/o\\******.......***[[\\oo/[[[,[*......***,[\\oooOOO@@@@@@@@@@@@@@@@@@@@OooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooOOOOOOOOOOOOOOOOOO\r\n"
									+ "********oooooooooooooooo**=ooooooO@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOooo******************..............***.................****[ooOOO@@@@@@@@@@@@@@@@@@@OooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooOOooOOOOoOOOOOOOOOOoOOOOO\r\n"
									+ "********oooooooooooooooooooooooooOO@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOoOoooo`****........**.........................................*,\\oOOO@@@@@@@@@@@@@@@@@OoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "oooooooooooooooooooooooo*,ooooooOO@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOo/`******.................   ..........   ...................**,ooOO@@@@@@@@@@@@@@@@@OooooooooooooooooooooooooooooooooooooooooooooooooooOOOOOOOOOOOOooOooOOOOOOOOOOOOOOOOO\r\n"
									+ "**********,\\ooooooooooooooooooooOO@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOooo`*........                            .................***ooOOO@@@@@@@@@@@@@@@OooooooooooooooooooooooooooooooooooooooooooooooooOOOoOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "*************=ooooooooooooooooooO@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOO@@@@@@@OOOOo^**.....                              .. ....*,/\\]]]]*****`oOOOO@@@@@@@@@@@@@@OoooooooooooooooooooooooooooooooooOooooOOOOOooOOOOOOoOOOOOOoOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "************[***ooooooooooooooooO@@@@@@@@@@@@@@@@@@@@@@@@@OOOO@@@@@@@@OOOOOoo**.....                                ...**]oOO@OOOOOOo]]],ooOOOO@@@@@@@@@@@@@OOoooooooooooooooooooooooooooooooOooOOooOOoooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "****************ooooooooooooooooO@@@@@@@@@@@@@@@@@@@@@@@@@O@@@@@@@@@OOOoo/**......                                .*****..     .[O@@@OOoooooOOOO@@@@@@@@@@@@OOooooooooooooOooooooooooooooOOoOOOOooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "****************ooooooooooooooooO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOo`**.........                               .*.*.  ]@@@\\`  ..,OOOOOOOOoOOOOO@@@@@@@@@@@OOOOOOooOoooOOOoOOOOOOooooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ ".....***********ooooooooooooooooO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOo`**.......                                   ,oo*  =@@@@@@@^.,OOO@OOOOOOOOOOOOO@@@@@@@@@@OOOOOOOoOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "......*************\\ooooooooooooO@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOO^**.........                           .       .=O.   =@@@@@@@@OOOOOOOOOOOOOOOOOOOO@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "....***************ooooooooooooooO@@@@@@@@@@@@@@@@@@@@@@@@@@OOOo*............           .          .............=\\...,O@@@OOO/\\oOOoOOOOOOOOOOOOOOOOO@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "********************=ooooooooooooO@@@@@@@@@@@@@@@@@@@@@@@@OOOO^**..........               ............***,\\]**...*,[\\ooo[`********.***\\oooooOOOOOOOO@@@@@@@OoOooooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "********]]]]*****,o*o]/ooooooooooO@@@@@@@@@@@@@@@@@@@@@@@@OOOo*.........                  ............*,/ooooo\\**..*.*.................***,ooooOOOOOO@@@@@OOOooo\\***=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "**************]]o*]oo]/ooooooooooOO@@@@@@@@@@@@@@@@@@@@@@@OOo^*.................         ..******.....**=oooooo\\`**.......................***[oOOOOOO@@@@@OOooo**`**,oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "*************ooooo[\\o/ooooooooooooOO@@@@@@@@@@@@@@@@@@@@@@OOo*......**^**.***..*]/`.    ..**ooo***.......******[`*...........................**\\ooOOOO@@@@@OOOo**,oo/oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "*************ooooo^\\\\oooooooooooooOO@@@@@@@@@@@@@@@@@@@@@@Oo^***.*,oOOO/`..]]    ..\\\\...*=/ooooo**............................................*,[ooOOO@@@@@OOo*...,[oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "**************ooooooooooooooooooooOO@@@@@@@@@@@@@@@@@@@@@@OO/`**oOOO@O` ,@@@@@@`   =O*..*\\ooooo[*..............................................***\\oOOO@@@@OOo..  ..=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "**************ooOOOOooooooooooooooOOO@@@@@@@@@@@@@@@@@@@@@OOoo/oOO@@`. .@@@^ @@@OOO^...****[oo^**................................................**ooOOO@@@O`*.     .oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "*************,oooOOOOOooooooooooooooOO@@@@@@@@@@@@@@@@@@@@OOOOOOO@Oo`*..\\@@@@OO[`......***********........****............  .        .............*,oOOO@@@O....    .*OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "**************/oooOOOOOOooooooooooooOO@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOo`..........*******,o^*.........*ooo]*..........             ..........*,=OOOO@@^..........=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "**************=oooOOOOOOOoooooooOOOoOOO@@@@@@@@@@@@@@@@@@@OOOOOO@@@@@OOOo/...................*oooo*........*oOOOOo^*...                  ..........**ooOO@@O^*....**`*,OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "**************=oooOOOOOOOOOOoooOOOOOOOOO@@@@@@@@@@@@@@@@@@OOOOO@@@@OOOo`*....................=OOo^*....  ...,oOOOOOOo\\...                 ...........*\\oOO@@O]]/oooo*=ooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "********[[****oooOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@OOOOOO@OOOOo`......................=OOO^...      ...,[[*....\\o*.                ...........**oOOO@@OO`*,ooooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "o`**,o*=oo[*,/oooOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@OOOOOOOOOo`*......................OOO^..          ......   ...*..              ............**=OOO@O`.****ooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "oooooo\\oooooooooooOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@OOOOOOOOoo^*.....................=OO/...          ....,]O]]....o*...           ...........***=oOO@O....**,oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOooooooooooooooooOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@OOOOOOOo^*..............    ....OOo....           ,@@@@@OO`...o^......      ..............***ooOO@^.....*/OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOoOOOoooooooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@OOOOOOo**............        .o/........       .,O@@@@@O^....**............................*/oOOO^.....=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOooooooOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@OOOOo^*...........          =O...../OO\\].......=Ooooooo*****..............................*,/OOOO`..]oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@OOOOo^*.........            =/...=ooO@@@OOo`......*...****................................**=oOOO@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@O@@@@@@@@OOOo`*........             *o*....[oOO/[[[*..............................................*=ooOoOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@OOOOOOOOOOOOO@@@@@@@OOOOOO@@@@@OOOo*.......              ..=o^....*****..................................................*=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@O@@@@@@@@@@@@\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@OOOOOOOOOOOOO@@@@@@@@o[`OO@@@@@OOOo**.....              ....*********....*.*]o^*.........................................*=oOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@O@@@@@@@@@@@@@\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@OOOOOOOOOOOOO@@@@@@@@O*.=OO@@@@@OOo/*......             ....******....******............................................**=OOOO@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOO@O@@@OOOOOOOOOOOOOO@@@@@@@@\\.*O/\\O@@@OOOo**....             ......***.....****.......*****,/ooooo]***........................**=OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@\r\n"
									+ "OOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@OOOOOOOOOOOOOOOO@@@@@@@@^*O/..=@@@OOO\\*.....           .......***.....********]]]\\\\oooooOOOOOOOOOOOOOo...................*=oOOOO@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@\r\n"
									+ "OOOOOOOOOOOOOOOOOOO@OOOOOO@@@@@OOOOOOOOOOOOOOOO@@@@@@@@@\\=O.  \\@@OOOO^*.....        .......**oo*...*]/ooooooOOOOOOOOOOOOOOOOOOO@OOOOO\\*................**ooOOO@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@\r\n"
									+ "OOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@OOOOOOOOOOOOOOOOO@@@@@@@@@@O\\.  \\@@OOOo^*.....       .....********.*=oooOOOOOOOOOOOOoOOOOOOOOOOOOOOOOOo`*..............**=oOOO@@O@@@@@OOO@OO@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@\r\n"
									+ "OOOOOOOOOOOOOOOOO@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOO@@@@@@@@@@O^...@@@OOO^**....      ......********/oOOOOOOOOOOOOooo[[[****[[ooooooo[[[*...............**=/OOO@@@@@OOO@@@OOOOOOO@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@\r\n"
									+ "OOOOOOOOOOOO@OO@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOO@@@@@@@@@@@@O**o@@@@OO\\***....     ........**\\,]OOOOOOOOOOOOOo/*....  ...****,****...................**,oOO@@@@@@@@@@@@@@@@@@@@@OOOO@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@\r\n"
									+ "OOO@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOO@@@O@@@@@@@@@@O]oOO@@OOo***..................**oOOOOOOOOOOOoo/**.....................................**/OOO@@@@@@@O@O@@@@@@@@@@@@OOOO@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OO@OOOOOOOOOOOO@@@@@@@@@@@@@@@@@OOOO@@@OOo`**....................,[oooOo[[[`*****.....................................*oOOO@@@@@@@@@@O@@@@@@@@@@@@OOOO@@OO@@@@@@OO@@@@@OOO@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@O@@OO@@@@@OOOOOOOO@@@@@@@@@@@@@@@@@@@OOOO@@OOo^*......................*.................             ...................,oOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O@@@@@@@O@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "@@@@@@@@@@@@@@@@@@@@@@@@@@OO@@@@@@@@OOOO@@@@@@@@@@@@@@@@@@@@@@@@@O^=O@@OOoo*..................................                 ..................*=OOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "@@@@@@@@@@@@@@@@@@@@@@@@@@O@@@@@@@O@@OO@@@@@@@@@@@@@@@@@@@@@@@@@@@@\\O@@@OOOo*.................................                    .............,oOOOOOOOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\r\n"
									+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOo^*................................                    ...........,OOOOOOoooOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOOOOOOOOOOOO@@OOOOOOOOO@@OOOO\r\n"
									+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOo`*..............................                   ..........,OOOOOOooooooOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O@OOO@@@@@@@O@@@@@@@@\r\n"
									+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOo]`*...........................                   ........*]oOOOOOOooo[\\o\\OOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
									+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOoo]`**.......................               ..........*,OOOOOOOOo^*****ooOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
									+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOoo`***..........................  ............**,ooOOOOOOOOOoo*...**,oOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
									+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@O@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOOOoo\\]]***...........................******/ooooooOOOOOOOOoo^*...***oOO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
									+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@OOOOooo]/]\\*******.............**]oooooooo[[[**[ooOOOOOOOoo*......*\\oO@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n");
					System.out.println("↑这是作者的一位朋友");
					System.out.println(author.getName() + "：“没想到吧，你竟然还会在这种地方见到我~”");
					System.out.println(author.getName() + "：“我在设计这款屑作之初，就没打算在开头阐明详细内容，现在你通关了，自然而然地可以知道了。”");
					System.out.println("在遥远的鬼畜大陆，每一个鬼畜素材对象都在这里快乐的生活着。");
					System.out.println("但是有一天，谜之森林外出现了令人叹为观止的一幕——天空正在逐渐被黑暗所侵蚀着！");
					System.out.println("就在这时，有一名年轻勇敢的冒险家，他决定只身一人，拯救鬼畜大陆，他的名字叫——" + you.getName());
					System.out.println(you.getName() + " 历经千辛万苦，终于打败了谜之森林里的所有怪物。");
					System.out.println("影流之主 灰飞烟灭后。转眼间，世界恢复了光明！你凯旋了，成为了鬼畜大陆永远传世的神话！");
					System.out.println("恭喜获得成就：用一根爷爷祖传的的拐杖打败 BOSS 的" + you.getGender() + "人。（草");
					System.out.println("游戏的内容到此结束，感谢您的游玩！");
					// 草，其实我在 2020.6.19 就完成了这款游戏的前作，但是后来的要求与前作所有的大相径庭，被迫重写。
					// 前作：https://github.com/Emilelu/StrangeAdventure
					// 但是有一位朋友给了我些想法，前作就因此合并入本游戏了！可以通过地图越界传送过去！
					System.out.println(author.getName()
							+ "：“现在你将会被传送回地图，但是地图上已经没有任何怪物以及NPC了。\n你只有三种选择：1.在地图上漫无目的地游荡 2.输入 exit 退出游戏 3.探索地图范围之外（四个方向有不同的彩蛋）”");

				}
			}
		} else {
			System.out.println("好像没啥发生(*^_^*)");
		}
	}

}
