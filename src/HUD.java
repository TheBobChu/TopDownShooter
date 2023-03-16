
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HUD {
	
	private Handler handler;
	private int gold = 0, level = 0, pistolAmmo, rifleAmmo;
	private int playerHealth, enemiesLeft;
	private boolean hotbar2, hotbar3, onPistol, onRifle;
	private int room2Level = 0, room3Level = 0, room4Level = 0;
	
	public HUD(Handler handler) {
		this.handler = handler;
		this.pistolAmmo = handler.getPistol().ammo;
		this.rifleAmmo = handler.getRifle().ammo;
	}

	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if (playerHealth == 5)
			g.drawImage(Gfx.hp5, (int)(20 + handler.getCamera().getxOffset()), (int)(20 + handler.getCamera().getyOffset()), 102, 18, null);
		if (playerHealth == 4)
			g.drawImage(Gfx.hp4, (int)(20 + handler.getCamera().getxOffset()), (int)(20 + handler.getCamera().getyOffset()), 102, 18, null);
		if (playerHealth == 3)
			g.drawImage(Gfx.hp3, (int)(20 + handler.getCamera().getxOffset()), (int)(20 + handler.getCamera().getyOffset()), 102, 18, null);
		if (playerHealth == 2)
			g.drawImage(Gfx.hp2, (int)(20 + handler.getCamera().getxOffset()), (int)(20 + handler.getCamera().getyOffset()), 102, 18, null);
		if (playerHealth == 1)
			g.drawImage(Gfx.hp1, (int)(20 + handler.getCamera().getxOffset()), (int)(20 + handler.getCamera().getyOffset()), 102, 18, null);
		//hp0 for now but of course player will be dead
		if (playerHealth <= 0)
			g.drawImage(Gfx.hp0, (int)(20 + handler.getCamera().getxOffset()), (int)(20 + handler.getCamera().getyOffset()), 102, 18, null);
		
		// hotbar code
		if (onPistol && !onRifle && !hotbar2 && !hotbar3)
			g.drawImage(Gfx.hbOnPistol, (int)(264 + handler.getCamera().getxOffset()), (int)(377 + handler.getCamera().getyOffset()), 97, 34, null);
		else if (onPistol && !onRifle && hotbar2 && !hotbar3)
			g.drawImage(Gfx.hbOnPistolNoRifle, (int)(264 + handler.getCamera().getxOffset()), (int)(377 + handler.getCamera().getyOffset()), 97, 34, null);
		else if (!onPistol && onRifle && hotbar2 && !hotbar3)
			g.drawImage(Gfx.hbOnRifle, (int)(264 + handler.getCamera().getxOffset()), (int)(377 + handler.getCamera().getyOffset()), 97, 34, null);
		
		// color and font for ammo
		g.setColor(Color.white);
		Font font = new Font("arial", 0, 8);
		g.setFont(font);
		// center the ammo count for first slot
		FontMetrics metrics = g.getFontMetrics(font);
		Rectangle ammoRect1 = new Rectangle((int)(264 + handler.getCamera().getxOffset()), (int)(400 + handler.getCamera().getyOffset()), 34, 3);
		int ammoRectX1 = ammoRect1.x + (ammoRect1.width - metrics.stringWidth(Integer.toString(pistolAmmo))) / 2;
		int ammoRectY1 = ammoRect1.y + (ammoRect1.height - metrics.getHeight() / 2) + metrics.getAscent();
		g.drawString(Integer.toString(pistolAmmo), ammoRectX1, ammoRectY1);
		// center the ammo count for second slot
		if (hotbar2) {
			Rectangle ammoRect2 = new Rectangle((int)(296 + handler.getCamera().getxOffset()), (int)(400 + handler.getCamera().getyOffset()), 34, 3);
			int ammoRectX2 = ammoRect2.x + (ammoRect2.width - metrics.stringWidth(Integer.toString(rifleAmmo))) / 2;
			int ammoRectY2 = ammoRect2.y + (ammoRect2.height - metrics.getHeight() / 2) + metrics.getAscent();
			g.drawString(Integer.toString(rifleAmmo), ammoRectX2, ammoRectY2);
		}
		if (hotbar3) {
			
		}
		
		//temporary level code
		g.setColor(Color.white);
		Font font2 = new Font("arial", 0, 15);
		g.setFont(font2);
		if (!handler.getBoundsManager().isInBossFight())
			g.drawString("Level: " + level, (int)(23 + handler.getCamera().getxOffset()), (int)(50 + handler.getCamera().getyOffset()));
		else
			g.drawString("Level: BOSS", (int)(23 + handler.getCamera().getxOffset()), (int)(50 + handler.getCamera().getyOffset()));
		
		//temporary enemies left code
		g.drawString("Enemies Left: " + enemiesLeft, (int)(23 + handler.getCamera().getxOffset()), (int)(65 + handler.getCamera().getyOffset()));
		
		//temporary gold code
		g.drawString("Gold: " + gold, (int)(23 + handler.getCamera().getxOffset()), (int)(80 + handler.getCamera().getyOffset()));
	
		//temporary gun stats code
		g.drawString("Damage: " + (handler.getCurrWeapon().damage + handler.getBonusDmg()), (int)(23 + handler.getCamera().getxOffset()), (int)(95 + handler.getCamera().getyOffset()));
		g.drawString("Fire Rate: " + handler.getCurrWeapon().fireRate + "/s", (int)(23 + handler.getCamera().getxOffset()), (int)(110 + handler.getCamera().getyOffset()));
	}

	public int getPlayerHealth() {
		return playerHealth;
	}

	public void setPlayerHealth(int playerHealth) {
		this.playerHealth = playerHealth;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getEnemiesLeft() {
		return enemiesLeft;
	}

	public void setEnemiesLeft(int enemiesLeft) {
		this.enemiesLeft = enemiesLeft;
	}

	public boolean isHotbar2() {
		return hotbar2;
	}

	public void setHotbar2(boolean hotbar2) {
		this.hotbar2 = hotbar2;
	}

	public boolean isHotbar3() {
		return hotbar3;
	}

	public void setHotbar3(boolean hotbar3) {
		this.hotbar3 = hotbar3;
	}

	public int getPistolAmmo() {
		return pistolAmmo;
	}

	public void setPistolAmmo(int pistolAmmo) {
		this.pistolAmmo = pistolAmmo;
	}

	public int getRifleAmmo() {
		return rifleAmmo;
	}

	public void setRifleAmmo(int rifleAmmo) {
		this.rifleAmmo = rifleAmmo;
	}

	public boolean isOnPistol() {
		return onPistol;
	}

	public void setOnPistol(boolean onPistol) {
		this.onPistol = onPistol;
	}

	public boolean isOnRifle() {
		return onRifle;
	}

	public void setOnRifle(boolean onRifle) {
		this.onRifle = onRifle;
	}

	public int getRoom2Level() {
		return room2Level;
	}

	public void setRoom2Level(int room2Level) {
		this.room2Level = room2Level;
	}

	public int getRoom3Level() {
		return room3Level;
	}

	public void setRoom3Level(int room3Level) {
		this.room3Level = room3Level;
	}

	public int getRoom4Level() {
		return room4Level;
	}

	public void setRoom4Level(int room4Level) {
		this.room4Level = room4Level;
	}
	
}
