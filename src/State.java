
import java.awt.Graphics;

public abstract class State {
	
	protected Handler handler;
	protected boolean menuHoverNone, menuHoverStart, menuHoverHelp, menuHoverQuit;
	protected boolean pauseHoverNone, pauseHoverResume, pauseHoverShop, pauseHoverMainMenu;
	protected boolean helpHoverNone, helpHoverOk;
	protected boolean shopHoverNone, shopHoverShield, shopHoverAmmo, shopHoverDamage, shopHoverBack;
	protected boolean endHoverNone, endHoverBack;
	protected boolean winHoverNone, winHoverBack;
	protected int damageCost;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);

	public int getDamageCost() {
		return damageCost;
	}

	public void setDamageCost(int damageCost) {
		this.damageCost = damageCost;
	}
	
}
