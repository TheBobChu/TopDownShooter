
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BossMinion extends GameObject {

	private Handler handler;
	private GameObject player;
	private long lastAttackTimer, attackCooldown = 2000, attackTimer = attackCooldown;
	
	public BossMinion(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.health = 50;
		
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player) {
				player = handler.object.get(i);
				break;
			}
		}
		
		handler.getHud().setEnemiesLeft(handler.getHud().getEnemiesLeft() + 1);
	}

	public void tick() {
		collision();
		move();
		
		if (this.health <= 0) {
			handler.removeObject(this);
			handler.getHud().setEnemiesLeft(handler.getHud().getEnemiesLeft() - 1);
			handler.getHud().setGold(handler.getHud().getGold() + 200);
		}
	}
	
	public void attack(GameObject tempObject) {
		// this gets the time paused and then subtracts it so that
		// when in pause state the game time also pauses
		long pausedTime = handler.getEndPause() - handler.getStartPause();
		if (!handler.isYet2()) {
			attackTimer -= pausedTime;
			handler.setYet2(true);
		}
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if (attackTimer < attackCooldown)
			return;
		
		if (handler.isShielded())
			handler.setShielded(false);
		else {
			tempObject.health--;
			handler.setHurt(true);
		}
		attackTimer = 0;
	}
	
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			//Enemy Collision With Player, Enemy Attacks With A 2 Second Cooldown
			if (tempObject.getId() == ID.Player) {
				if (!placeFree(getBounds(), tempObject.getBounds())) {
					attack(tempObject);
				}
			}
		}
	}
	
	public boolean placeFree(Rectangle myRect, Rectangle otherRect) {
		if (myRect.intersects(otherRect))
			return false;
		return true;
	}
	
	public void move() {
		int dx = player.getX() - x;
		int dy = player.getY() - y;
		float norm = (float) Math.sqrt(dx * dx + dy * dy);
		dx *= 3 / norm;
		dy *= 3 / norm;
		x += dx;
		y += dy;
	}

	public void render(Graphics g) {
		g.drawImage(Gfx.basicEnemy, x, y, null);
		g.setColor(Color.white);
		g.setFont(new Font("arial", 0, 10));
		g.drawString(this.health + "/50", x + 5, y - 5);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
