
import java.awt.image.BufferedImage;

public class Gfx {
	
	public static BufferedImage floor1, floor2, floor3, floor4, floor5, 
								floor6, floor7, floor8, floor9;
	public static BufferedImage wall, crate, block, mid, boss;
	public static BufferedImage hp5, hp4, hp3, hp2, hp1, hp0;
	public static BufferedImage hbPistol, hbPistolRifle, hbOnPistol, hbOnPistolNoRifle, hbOnRifle;
	public static BufferedImage player, playerHurt, playerShielded, basicEnemy, bossEnemy;
	public static BufferedImage menuHoverNone, menuHoverStart, menuHoverHelp, menuHoverQuit;
	public static BufferedImage pauseHoverNone, pauseHoverResume, pauseHoverShop, pauseHoverMainMenu;
	public static BufferedImage helpHoverNone, helpHoverOk;
	public static BufferedImage shopHoverNone, shopHoverShield, shopHoverAmmo, shopHoverDamage, shopHoverBack;
	public static BufferedImage endHoverNone, endHoverBack;
	public static BufferedImage winHoverNone, winHoverBack;
	public static BufferedImage rifle;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/SpriteSheet.png"));
		SpriteSheet sheet2 = new SpriteSheet(ImageLoader.loadImage("/SpriteSheet2.png"));
		SpriteSheet menuSheet = new SpriteSheet(ImageLoader.loadImage("/MenuScreen.png"));
		SpriteSheet pauseSheet = new SpriteSheet(ImageLoader.loadImage("/PauseScreen.png"));
		SpriteSheet helpSheet = new SpriteSheet(ImageLoader.loadImage("/HelpScreen.png"));
		SpriteSheet shopSheet = new SpriteSheet(ImageLoader.loadImage("/ShopScreen.png"));
		SpriteSheet endSheet = new SpriteSheet(ImageLoader.loadImage("/endScreen.png"));
		SpriteSheet winSheet = new SpriteSheet(ImageLoader.loadImage("/winScreen.png"));
		
		floor1 = sheet.crop(0, 0, 32, 32);
		floor2 = sheet.crop(32, 0, 32, 32);
		floor3 = sheet.crop(64, 0, 32, 32);
		floor4 = sheet.crop(0, 32, 32, 32);
		floor5 = sheet.crop(32, 32, 32, 32);
		floor6 = sheet.crop(64, 32, 32, 32);
		floor7 = sheet.crop(0, 64, 32, 32);
		floor8 = sheet.crop(32, 64, 32, 32);
		floor9 = sheet.crop(64, 64, 32, 32);
		
		wall = sheet.crop(96, 0, 32, 32);
		crate = sheet.crop(96, 32, 32, 32);
		block = sheet.crop(96, 64, 32, 32);
		mid = sheet.crop(0, 96, 96, 96);
		boss = sheet.crop(128, 0, 96, 96);
		
		hp5 = sheet2.crop(0, 23, 51, 9);
		hp4 = sheet2.crop(0, 55, 51, 9);
		hp3 = sheet2.crop(0, 87, 51, 9);
		hp2 = sheet2.crop(0, 119, 51, 9);
		hp1 = sheet2.crop(0, 151, 51, 9);
		hp0 = sheet2.crop(0, 183, 51, 9);
		
		hbPistol = sheet2.crop(0, 192, 96, 32);
		hbPistolRifle = sheet2.crop(0, 224, 96, 32);
		hbOnPistol = sheet2.crop(96, 154, 97, 34);
		hbOnPistolNoRifle = sheet2.crop(96, 188, 97, 34);
		hbOnRifle = sheet2.crop(96, 222, 96, 34);
		
		player = sheet2.crop(64, 0, 32, 32);
		playerHurt = sheet2.crop(64, 64, 32, 32);
		playerShielded = sheet2.crop(200, 0, 56, 56);
		basicEnemy = sheet2.crop(64, 32, 32, 32);
		bossEnemy = sheet2.crop(96, 32, 64, 64);
		
		menuHoverNone = menuSheet.crop(0, 0, 640, 480);
		menuHoverStart = menuSheet.crop(640, 0, 640, 480);
		menuHoverHelp = menuSheet.crop(0, 480, 640, 480);
		menuHoverQuit = menuSheet.crop(640, 480, 640, 480);
		
		pauseHoverNone = pauseSheet.crop(0, 0, 640, 480);
		pauseHoverResume = pauseSheet.crop(640, 0, 640, 480);
		pauseHoverShop = pauseSheet.crop(0, 480, 640, 480);
		pauseHoverMainMenu = pauseSheet.crop(640, 480, 640, 480);
		
		helpHoverNone = helpSheet.crop(0, 0, 640, 480);
		helpHoverOk = helpSheet.crop(640, 0, 640, 480);
		
		shopHoverNone = shopSheet.crop(0, 0, 640, 480);
		shopHoverShield = shopSheet.crop(640, 0, 640, 480);
		shopHoverAmmo = shopSheet.crop(1280, 0, 640, 480);
		shopHoverDamage = shopSheet.crop(0, 480, 640, 480);
		shopHoverBack = shopSheet.crop(640, 480, 640, 480);
		
		endHoverNone = endSheet.crop(0, 0, 640, 480);
		endHoverBack = endSheet.crop(640, 0, 640, 480);
		
		winHoverNone = winSheet.crop(0, 0, 640, 480);
		winHoverBack = winSheet.crop(640, 0, 640, 480);
		
		rifle = sheet2.crop(96, 0, 32, 32);
	}
	
}
