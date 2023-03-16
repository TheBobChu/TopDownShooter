
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ShopState extends State {
	
	public ShopState(Handler handler) {
		super(handler);
		this.shopHoverNone = true;
		this.shopHoverShield = false;
		this.shopHoverAmmo = false;
		this.shopHoverDamage = false;
		this.shopHoverBack = false;
		this.damageCost = 100;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		if (shopHoverNone && !shopHoverShield && !shopHoverAmmo && !shopHoverDamage && !shopHoverBack)
			g.drawImage(Gfx.shopHoverNone, -7, -18, 640, 480, null);
		else if (!shopHoverNone && shopHoverShield && !shopHoverAmmo && !shopHoverDamage && !shopHoverBack)
			g.drawImage(Gfx.shopHoverShield, -7, -18, 640, 480, null);
		else if (!shopHoverNone && !shopHoverShield && shopHoverAmmo && !shopHoverDamage && !shopHoverBack)
			g.drawImage(Gfx.shopHoverAmmo, -7, -18, 640, 480, null);
		else if (!shopHoverNone && !shopHoverShield && !shopHoverAmmo && shopHoverDamage && !shopHoverBack)
			g.drawImage(Gfx.shopHoverDamage, -7, -18, 640, 480, null);
		else if (!shopHoverNone && !shopHoverShield && !shopHoverAmmo && !shopHoverDamage && shopHoverBack)
			g.drawImage(Gfx.shopHoverBack, -7, -18, 640, 480, null);
		
			g.setColor(Color.white);
			Font font = new Font("arial", 0, 15);
			Font font2 = new Font("arial", 0, 8);
			g.setFont(font);
			g.drawString("Gold: " + handler.getHud().getGold(), 15, 30);
			/*FontMetrics metrics = g.getFontMetrics(font);
			Rectangle shieldRect = new Rectangle(68, 84, 120, 20);
			int shieldRectX1 = shieldRect.x + (shieldRect.width - metrics.stringWidth("Shield")) / 2;
			int shieldRectY1 = shieldRect.y + ((shieldRect.height - metrics.getHeight()) / 2);
			g.drawString("Shield", shieldRectX1, shieldRectY1);*/
			g.drawString("Shield", 101, 82);
			g.setFont(font2);
			g.drawString("Immune to next enemy attack", 68, 158);
			g.drawString("Cost: 200 Gold", 93, 173);
			g.setFont(font);
			if (handler.getCurrWeapon() == handler.getPistol())
				g.drawString("25 Ammo", 282, 85);
			else if (handler.getCurrWeapon() == handler.getRifle())
				g.drawString("125 Ammo", 277, 85);
			g.setFont(font2);
			g.drawString("25% of gun's original ammo", 262, 153);
			g.drawString("Cost: 200 Gold", 285, 172);
			g.setFont(font);
			g.drawString("Strength", 476, 86);
			g.setFont(font2);
			g.drawString("Gain 1 extra damage", 466, 153);
			g.drawString("Cost: " + Integer.toString(this.damageCost) + " Gold", 477, 172);
	}

}
