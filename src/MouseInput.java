
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	
	private Handler handler;
	private long lastTimer, attackTime = 500, timer = attackTime;
	
	public MouseInput(Handler handler) {
		this.handler = handler;
	}
	
	public void mouseMoved(MouseEvent e) {
		int hoverMX = e.getX();
		int hoverMY = e.getY();
		// Menu State
		// Hover Over Start Button
		if (mouseOver(hoverMX, hoverMY, 240-7, 62-18, 160, 100)) {
			handler.getMenuState().menuHoverNone = false; 
			handler.getMenuState().menuHoverStart = true;
			handler.getMenuState().menuHoverHelp = false; 
			handler.getMenuState().menuHoverQuit = false;
		}
		// Hover Over Help Button
		else if (mouseOver(hoverMX, hoverMY, 240-7, 190-18, 160, 100)) {
			handler.getMenuState().menuHoverNone = false; 
			handler.getMenuState().menuHoverStart = false;
			handler.getMenuState().menuHoverHelp = true; 
			handler.getMenuState().menuHoverQuit = false;
		}
		// Hover Over Quit Button
		else if (mouseOver(hoverMX, hoverMY, 240-7, 318-18, 160, 100)) {
			handler.getMenuState().menuHoverNone = false; 
			handler.getMenuState().menuHoverStart = false;
			handler.getMenuState().menuHoverHelp = false; 
			handler.getMenuState().menuHoverQuit = true;
		}
		// Hover Over No Buttons
		else {
			handler.getMenuState().menuHoverNone = true; 
			handler.getMenuState().menuHoverStart = false;
			handler.getMenuState().menuHoverHelp = false; 
			handler.getMenuState().menuHoverQuit = false;
		}
		// Pause State
		// Hover Over Resume Button
		if (mouseOver(hoverMX, hoverMY, 238-7, 82-18, 164, 60)) {
			handler.getPauseState().pauseHoverNone = false; 
			handler.getPauseState().pauseHoverResume = true;
			handler.getPauseState().pauseHoverShop = false; 
			handler.getPauseState().pauseHoverMainMenu = false;
		}
		// Hover Over Upgrades Button
		else if (mouseOver(hoverMX, hoverMY, 238-7, 210-18, 164, 60)) {
			handler.getPauseState().pauseHoverNone = false; 
			handler.getPauseState().pauseHoverResume = false;
			handler.getPauseState().pauseHoverShop = true; 
			handler.getPauseState().pauseHoverMainMenu = false;
		}
		// Hover Over Main Menu Button
		else if (mouseOver(hoverMX, hoverMY, 238-7, 338-18, 164, 60)) {
			handler.getPauseState().pauseHoverNone = false; 
			handler.getPauseState().pauseHoverResume = false;
			handler.getPauseState().pauseHoverShop = false; 
			handler.getPauseState().pauseHoverMainMenu = true;
		}
		// Hover Over No Buttons
		else {
			handler.getPauseState().pauseHoverNone = true; 
			handler.getPauseState().pauseHoverResume = false;
			handler.getPauseState().pauseHoverShop = false; 
			handler.getPauseState().pauseHoverMainMenu = false;
		}
		// Help State
		// Hover Over Ok Button
		if (mouseOver(hoverMX, hoverMY, 240-7, 318-18, 160, 100)) {
			handler.getHelpState().helpHoverNone = false;
			handler.getHelpState().helpHoverOk = true;
		}
		// Hover Over No Buttons
		else {
			handler.getHelpState().helpHoverNone = true;
			handler.getHelpState().helpHoverOk = false;
		}
		// Shop State
		// Hover Over Shield Button
		if (mouseOver(hoverMX, hoverMY, 78-7, 236-18, 100, 56)) {
			handler.getShopState().shopHoverNone = false;
			handler.getShopState().shopHoverShield = true;
			handler.getShopState().shopHoverAmmo = false;
			handler.getShopState().shopHoverDamage = false;
			handler.getShopState().shopHoverBack = false;
		}
		// Hover Over Ammo Button
		else if (mouseOver(hoverMX, hoverMY, 270-7, 236-18, 100, 56)) {
			handler.getShopState().shopHoverNone = false;
			handler.getShopState().shopHoverShield = false;
			handler.getShopState().shopHoverAmmo = true;
			handler.getShopState().shopHoverDamage = false;
			handler.getShopState().shopHoverBack = false;
		}
		// Hover Over Damage Button
		else if (mouseOver(hoverMX, hoverMY, 462-7, 236-18, 100, 56)) {
			handler.getShopState().shopHoverNone = false;
			handler.getShopState().shopHoverShield = false;
			handler.getShopState().shopHoverAmmo = false;
			handler.getShopState().shopHoverDamage = true;
			handler.getShopState().shopHoverBack = false;
		}
		// Hover Over Back Button
		else if (mouseOver(hoverMX, hoverMY, 254-7, 328-18, 132, 72)) {
			handler.getShopState().shopHoverNone = false;
			handler.getShopState().shopHoverShield = false;
			handler.getShopState().shopHoverAmmo = false;
			handler.getShopState().shopHoverDamage = false;
			handler.getShopState().shopHoverBack = true;
		}
		// Hover Over No Buttons
		else {
			handler.getShopState().shopHoverNone = true;
			handler.getShopState().shopHoverShield = false;
			handler.getShopState().shopHoverAmmo = false;
			handler.getShopState().shopHoverDamage = false;
			handler.getShopState().shopHoverBack = false;
		}
		// End State
		// Hover Over Back Button
		if (mouseOver(hoverMX, hoverMY, 240-7, 318-18, 160, 100)) {
			handler.getEndState().endHoverNone = false;
			handler.getEndState().endHoverBack = true;
		}
		else {
			handler.getEndState().endHoverNone = true;
			handler.getEndState().endHoverBack = false;
		}
		// Win State
		// Hover Over Back Button
		if (mouseOver(hoverMX, hoverMY, 240-7, 318-18, 160, 100)) {
			handler.getWinState().winHoverNone = false;
			handler.getWinState().winHoverBack = true;
		}
		else {
			handler.getWinState().winHoverNone = true;
			handler.getWinState().winHoverBack = false;
		}
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		// Menu State
		if (handler.getCurrentState() == handler.getMenuState()) {
			// Press Start Button
			if (mouseOver(mx, my, 240-7, 62-18, 160, 100)) {
				handler.getGame().reset();
				handler.setCurrentState(handler.getGameState());
			}
			// Press Help Button
			if (mouseOver(mx, my, 240-7, 190-18, 160, 100)) {
				handler.setCurrentState(handler.getHelpState());
			}
			// Press Quit Button
			if (mouseOver(mx, my, 240-7, 318-18, 160, 100)) {
				System.exit(1);
			}
		}
		// Pause State
		else if (handler.getCurrentState() == handler.getPauseState()) {
			// Press Resume Button
			if (mouseOver(mx, my, 238-7, 82-18, 164, 60)) {
				handler.setCurrentState(handler.getGameState());
				handler.setPaused(false);
			}
			// Press Shop Button
			if (mouseOver(mx, my, 238-7, 210-18, 164, 60)) {
				handler.setCurrentState(handler.getShopState());
			}
			// Press Main Menu Button
			if (mouseOver(mx, my, 238-7, 338-18, 164, 60)) {
				handler.setCurrentState(handler.getMenuState());
				handler.setPaused(false);
			}
		}
		// Help State
		else if (handler.getCurrentState() == handler.getHelpState()) {
			// Press Ok Button
			if (mouseOver(mx, my, 240-7, 318-18, 160, 100)) {
				handler.setCurrentState(handler.getMenuState());
			}
		}
		// Shop State
		else if (handler.getCurrentState() == handler.getShopState()) {
			// Press Shield Button
			if (mouseOver(mx, my, 78-7, 236-18, 100, 56)) {
				if (handler.getHud().getGold() >= 200 && !handler.isShielded()) {
					handler.setShielded(true);
					handler.getHud().setGold(handler.getHud().getGold() - 200);
				}
			}
			// Press Ammo Button
			if (mouseOver(mx, my, 270-7, 236-18, 100, 56)) {
				if (handler.getHud().getGold() >= 200) {
					if (handler.getCurrWeapon() == handler.getPistol())
						handler.getHud().setPistolAmmo(handler.getHud().getPistolAmmo() + (handler.getPistol().ammo / 4));
					else if (handler.getCurrWeapon() == handler.getRifle())
						handler.getHud().setRifleAmmo(handler.getHud().getRifleAmmo() + (handler.getRifle().ammo / 4));
					handler.getHud().setGold(handler.getHud().getGold() - 200);
				}
			}
			// Press Damage Button
			if (mouseOver(mx, my, 462-7, 236-18, 100, 56)) {
				if (handler.getHud().getGold() >= handler.getShopState().getDamageCost()) {
					handler.setBonusDmg(handler.getBonusDmg() + 1);
					handler.getHud().setGold(handler.getHud().getGold() - handler.getShopState().getDamageCost());
					handler.getShopState().setDamageCost(handler.getShopState().getDamageCost() * 2);
				}
			}
			// Press Back Button
			if (mouseOver(mx, my, 254-7, 328-18, 132, 72)) {
				handler.setCurrentState(handler.getPauseState());
			}
		}
		// End State
		else if (handler.getCurrentState() == handler.getEndState()) {
			// Press Back Button
			if (mouseOver(mx, my, 240-7, 318-18, 160, 100)) {
				handler.setCurrentState(handler.getMenuState());
			}
		}
		// Win State
		else if (handler.getCurrentState() == handler.getWinState()) {
			// Press Back Button
			if (mouseOver(mx, my, 240-7, 318-18, 160, 100)) {
				handler.setCurrentState(handler.getMenuState());
			}
		}
		// Game State
		else if (handler.getCurrentState() == handler.getGameState()) {
			// Press To Shoot The Gun
			timer += System.currentTimeMillis() - lastTimer;
			lastTimer = System.currentTimeMillis();
			if (timer < attackTime)
				return;
			handler.setPlayerAttacked(true);
			int mxGame = (int) (e.getX() + handler.getCamera().getxOffset());
			int myGame = (int) (e.getY() + handler.getCamera().getyOffset());
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if (tempObject.getId() == ID.Player) {
					if (handler.getCurrWeapon() == handler.getPistol()) {
						if (handler.getHud().getPistolAmmo() > 0) {
							handler.addObject(new Bullet(tempObject.getX() + 16, tempObject.getY() + 16, ID.Bullet, handler, mxGame, myGame));
							handler.getHud().setPistolAmmo(handler.getHud().getPistolAmmo() - 1);
						}
					}
					else if (handler.getCurrWeapon() == handler.getRifle()) {
						if (handler.getHud().getRifleAmmo() > 0) {
							handler.addObject(new Bullet(tempObject.getX() + 16, tempObject.getY() + 16, ID.Bullet, handler, mxGame, myGame));
							handler.getHud().setRifleAmmo(handler.getHud().getRifleAmmo() - 1);
						}
					}
				}
			}
			timer = 0;
		}
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width && my > y && my < y + height)
			return true;
		return false;
	}

	public long getAttackTime() {
		return attackTime;
	}

	public void setAttackTime(long attackTime) {
		this.attackTime = attackTime;
	}

}
