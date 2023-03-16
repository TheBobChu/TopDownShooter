
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
			if (key == KeyEvent.VK_W) handler.setUp(true);
			if (key == KeyEvent.VK_A) handler.setLeft(true);
			if (key == KeyEvent.VK_S) handler.setDown(true);
			if (key == KeyEvent.VK_D) handler.setRight(true);
			if (key == KeyEvent.VK_ESCAPE) {
				// Game To Pause
				if (handler.getCurrentState() == handler.getGameState()) {
					handler.setCurrentState(handler.getPauseState());
					handler.setPaused(true);
					handler.setStartPause(System.currentTimeMillis());
					handler.setYet(false);
					handler.setYet2(false);
					handler.setYet3(false);
					handler.setYet4(false);
				}
				// Pause To Resume
				else if (handler.getCurrentState() == handler.getPauseState()) {
					handler.setCurrentState(handler.getGameState());
					handler.setPaused(false);
					handler.setEndPause(System.currentTimeMillis());
				}
				// Shop To Pause
				else if (handler.getCurrentState() == handler.getShopState()) {
					handler.setCurrentState(handler.getPauseState());
				}
			}
			if (!handler.isPaused()) {
				// Unlock Room 2
				if (!handler.isRoom2()) {
					if (handler.getBoundsManager().isInRoom2Bounds()) {
						if (key == KeyEvent.VK_ENTER) {
							if (handler.getHud().getGold() >= 500) {
								for (int i = 0; i < handler.object.size(); i++) {
									if (handler.object.get(i).getId() == ID.Room2) {
										int tempX = handler.object.get(i).getX();
										int tempY = handler.object.get(i).getY();
										handler.removeObject(handler.object.get(i));
										for (int x = tempX/2; x < tempX/2 + 16; x++) {
											for (int y = tempY/2; y < tempY/2 + 16; y++) {
												handler.getBfsGraph()[y][x] = '1';
											}
										}
									}
								}
								handler.setRoom2(true);
								handler.getHud().setGold(handler.getHud().getGold() - 500);
							}
							
						}
					}
				}
				// Unlock Room 3
				if (!handler.isRoom3()) {
					if (handler.getBoundsManager().isInRoom3Bounds()) {
						if (key == KeyEvent.VK_ENTER) {
							if (handler.getHud().getGold() >= 500) {
								for (int i = 0; i < handler.object.size(); i++) {
									if (handler.object.get(i).getId() == ID.Room3) {
										int tempX = handler.object.get(i).getX();
										int tempY = handler.object.get(i).getY();
										handler.removeObject(handler.object.get(i));
										for (int x = tempX/2; x < tempX/2 + 16; x++) {
											for (int y = tempY/2; y < tempY/2 + 16; y++) {
												handler.getBfsGraph()[y][x] = '1';
											}
										}
									}
								}
								handler.setRoom3(true);
								handler.getHud().setGold(handler.getHud().getGold() - 500);
							}
							
						}
					}
				}
				// Unlock Room 4 From 2
				if (!handler.isRoom4() && handler.isRoom2() && handler.isRoom3()) {
					if (handler.getBoundsManager().isInRoom4From2Bounds()) {
						if (key == KeyEvent.VK_ENTER) {
							if (handler.getHud().getGold() >= 1000) {
								for (int i = 0; i < handler.object.size(); i++) {
									if (handler.object.get(i).getId() == ID.Room4From2
											|| handler.object.get(i).getId() == ID.Room4From3) {
										int tempX = handler.object.get(i).getX();
										int tempY = handler.object.get(i).getY();
										handler.removeObject(handler.object.get(i));
										for (int x = tempX/2; x < tempX/2 + 16; x++) {
											for (int y = tempY/2; y < tempY/2 + 16; y++) {
												handler.getBfsGraph()[y][x] = '1';
											}
										}
									}
								}
								handler.setRoom4(true);
								handler.getHud().setGold(handler.getHud().getGold() - 1000);
							}
							
						}
					}
				}
				// Unlock Room 4 From 3
				if (!handler.isRoom4() && handler.isRoom2() && handler.isRoom3()) {
					if (handler.getBoundsManager().isInRoom4From3Bounds()) {
						if (key == KeyEvent.VK_ENTER) {
							if (handler.getHud().getGold() >= 1000) {
								for (int i = 0; i < handler.object.size(); i++) {
									if (handler.object.get(i).getId() == ID.Room4From3
											|| handler.object.get(i).getId() == ID.Room4From2) {
										int tempX = handler.object.get(i).getX();
										int tempY = handler.object.get(i).getY();
										handler.removeObject(handler.object.get(i));
										for (int x = tempX/2; x < tempX/2 + 16; x++) {
											for (int y = tempY/2; y < tempY/2 + 16; y++) {
												handler.getBfsGraph()[y][x] = '1';
											}
										}
									}
								}
								handler.setRoom4(true);
								handler.getHud().setGold(handler.getHud().getGold() - 1000);
							}
							
						}
					}
				}
				// Purchase Rifle
				if (!handler.getBoundsManager().isPickedUpRifle()) {
					if (handler.getBoundsManager().isInRifleBounds()) {
						if (key == KeyEvent.VK_ENTER) {
							if (handler.getHud().getGold() >= 1000) {
								handler.setTimeDelay(10);
								handler.getBoundsManager().setPickedUpRifle(true);
								handler.setCurrWeapon(handler.getRifle());
								handler.getHud().setHotbar2(true);
								handler.getHud().setOnPistol(false);
								handler.getHud().setOnRifle(true);
								for (int i = 0; i < handler.object.size(); i++) {
									if (handler.object.get(i).getId() == ID.Rifle)
										handler.removeObject(handler.object.get(i));
								}
								handler.getMouseInput().setAttackTime(handler.getCurrWeapon().attackTime);
								handler.getHud().setGold(handler.getHud().getGold() - 1000);
							}
						}
					}
				}
				// Start Boss Fight
				if (!handler.getBoundsManager().isInBossFight()) {
					if (handler.getBoundsManager().isInBossFightBounds()) {
						if (key == KeyEvent.VK_ENTER) {
							handler.getBoundsManager().setInBossFight(true);
							handler.getGame().startBossFight();
						}
					}
				}
				if (handler.getBoundsManager().isPickedUpRifle()) {
					if (key == KeyEvent.VK_1) {
						handler.setTimeDelay(50);
						handler.getHud().setOnPistol(true);
						handler.getHud().setOnRifle(false);
						handler.setCurrWeapon(handler.getPistol());
						handler.getMouseInput().setAttackTime(handler.getCurrWeapon().attackTime);
					}
					else if (key == KeyEvent.VK_2) {
						handler.setTimeDelay(10);
						handler.getHud().setOnPistol(false);
						handler.getHud().setOnRifle(true);
						handler.setCurrWeapon(handler.getRifle());
						handler.getMouseInput().setAttackTime(handler.getCurrWeapon().attackTime);
					}
				}
			}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
			if (key == KeyEvent.VK_W) handler.setUp(false);
			if (key == KeyEvent.VK_A) handler.setLeft(false);
			if (key == KeyEvent.VK_S) handler.setDown(false);
			if (key == KeyEvent.VK_D) handler.setRight(false);
	}
	
}
