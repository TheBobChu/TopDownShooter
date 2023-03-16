
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Handler {

	ArrayList<GameObject> object = new ArrayList<GameObject>();
	
	private Game game;
	private int x, y, time = 0, time2 = 0;;
	private String dmg;
	private boolean renderDmg, critted, paused;
	private boolean hurt, shielded;
	private long startPause, endPause;
	private boolean yet = false, yet2 = false, yet3 = false, yet4 = false;
	private int bonusDmg = 0;
	private int speed = 2;
	private boolean playerAttacked = false;
	private int timeDelay;

	private boolean up = false, down = false, left = false, right = false;
	private boolean room2 = false, room3 = false, room4 = false;
	
	public Handler(Game game) {
		this.game = game;
	}
	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
		
		if (playerAttacked) {
			speed = 0;
			if (time2 < timeDelay) {
				time2++;
				return;
			}
			speed = 2;
			time2 = 0;
			playerAttacked = false;
		}
		
		if (renderDmg) {
			g.setColor(Color.white);
			Font font = new Font("arial", 0, 15);
			g.setFont(font);
			FontMetrics metrics = g.getFontMetrics(font);
			Rectangle dmgRect = new Rectangle(x, y, 32, 32);
			int dmgRectX1 = dmgRect.x + (dmgRect.width - metrics.stringWidth(dmg)) / 2;
			int dmgRectY1 = dmgRect.y + (dmgRect.height - metrics.getHeight() / 2);
			g.drawString(dmg, dmgRectX1, dmgRectY1);
			if (critted) {
				int dmgRectX2 = dmgRect.x + (dmgRect.width - metrics.stringWidth("Critical Strike!")) / 2;
				int dmgRectY2 = dmgRect.y + (dmgRect.height - metrics.getHeight() / 2);
				g.drawString("Critical Strike!", dmgRectX2, dmgRectY2 + 20);
			}
			if (time < 50) {
				if (!paused)
				time++;
				return;
			}
			time = 0;
			renderDmg = false;
			critted = false;
		}
	}
	
	public Game getGame() {
		return game;
	}
	
	public Weapon getCurrWeapon() {
		return game.getCurrWeapon();
	}
	
	public void setCurrWeapon(Weapon currWeapon) {
		game.setCurrWeapon(currWeapon);
	}
	
	public State getMenuState() {
		return game.getMenuState();
	}

	public State getGameState() {
		return game.getGameState();
	}

	public State getPauseState() {
		return game.getPauseState();
	}
	
	public State getHelpState() {
		return game.getHelpState();
	}
	
	public State getShopState() {
		return game.getShopState();
	}
	
	public State getEndState() {
		return game.getEndState();
	}
	
	public State getWinState() {
		return game.getWinState();
	}
	
	public HUD getHud() {
		return game.getHud();
	}
	
	public char[][] getBfsGraph() {
		return game.getBfsGraph();
	}
	
	public Camera getCamera() {
		return game.getCamera();
	}

	public Pistol getPistol() {
		return game.getPistol();
	}

	public Rifle getRifle() {
		return game.getRifle();
	}

	public MouseInput getMouseInput() {
		return game.getMouseInput();
	}

	public State getCurrentState() {
		return game.getCurrentState();
	}

	public void setCurrentState(State currentState) {
		game.setCurrentState(currentState);
	}

	public BoundsManager getBoundsManager() {
		return game.getBoundsManager();
	}
	
	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}
	
	public void addObject(GameObject tempObject) {
		object.add(tempObject);
	}
	
	public void removeObject(GameObject tempObject) {
		object.remove(tempObject);
	}
	
	public void clearAll() {
		object.clear();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getDmg() {
		return dmg;
	}

	public void setDmg(String dmg) {
		this.dmg = dmg;
	}

	public boolean isRenderDmg() {
		return renderDmg;
	}

	public void setRenderDmg(boolean renderDmg) {
		this.renderDmg = renderDmg;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public boolean isCritted() {
		return critted;
	}

	public void setCritted(boolean critted) {
		this.critted = critted;
	}

	public boolean isHurt() {
		return hurt;
	}

	public void setHurt(boolean hurt) {
		this.hurt = hurt;
	}

	public boolean isShielded() {
		return shielded;
	}

	public void setShielded(boolean shielded) {
		this.shielded = shielded;
	}

	public long getStartPause() {
		return startPause;
	}

	public void setStartPause(long startPause) {
		this.startPause = startPause;
	}

	public long getEndPause() {
		return endPause;
	}

	public void setEndPause(long endPause) {
		this.endPause = endPause;
	}

	public boolean isYet() {
		return yet;
	}

	public void setYet(boolean yet) {
		this.yet = yet;
	}

	public boolean isRoom2() {
		return room2;
	}

	public void setRoom2(boolean room2) {
		this.room2 = room2;
	}

	public boolean isRoom3() {
		return room3;
	}

	public void setRoom3(boolean room3) {
		this.room3 = room3;
	}

	public boolean isRoom4() {
		return room4;
	}

	public void setRoom4(boolean room4) {
		this.room4 = room4;
	}

	public int getBonusDmg() {
		return bonusDmg;
	}

	public void setBonusDmg(int bonusDmg) {
		this.bonusDmg = bonusDmg;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isPlayerAttacked() {
		return playerAttacked;
	}

	public void setPlayerAttacked(boolean playerAttacked) {
		this.playerAttacked = playerAttacked;
	}

	public int getTimeDelay() {
		return timeDelay;
	}

	public void setTimeDelay(int timeDelay) {
		this.timeDelay = timeDelay;
	}

	public boolean isYet2() {
		return yet2;
	}

	public void setYet2(boolean yet2) {
		this.yet2 = yet2;
	}

	public boolean isYet3() {
		return yet3;
	}

	public void setYet3(boolean yet3) {
		this.yet3 = yet3;
	}

	public boolean isYet4() {
		return yet4;
	}

	public void setYet4(boolean yet4) {
		this.yet4 = yet4;
	}
	
}
