
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BossEnemy extends GameObject {

	private Handler handler;
	private GameObject player;
	private long lastAttackTimer, attackCooldown = 3000, attackTimer = attackCooldown;
	private long lastSpawnTimer, spawnCooldown = 5000, spawnTimer = spawnCooldown;
	private int strongerTimer = 0;
	
	public BossEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.health = 1000;
		attackCooldown = 3000;
		spawnCooldown = 5000;
		strongerTimer = 0;
		
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
		bossMovements();
		spawnMinion();
		
		strongerTimer++;
		if (strongerTimer == 1000) {
			attackCooldown = 1500;
			spawnCooldown = 2500;
		}
		if (strongerTimer == 1500) {
			attackCooldown = 5000;
			spawnCooldown = 3000;
		}
		
		if (this.health <= 0) {
			handler.setCurrentState(handler.getWinState());
		}
	}
	
	public void spawnMinion() {
		long pausedTime = handler.getEndPause() - handler.getStartPause();
		if (!handler.isYet4()) {
			attackTimer -= pausedTime;
			handler.setYet4(true);
		}
		spawnTimer += System.currentTimeMillis() - lastSpawnTimer;
		lastSpawnTimer = System.currentTimeMillis();
		
		if (spawnTimer < spawnCooldown)
			return;
		
		handler.addObject(new BossMinion(x + 16, y + 16, ID.BossMinion, handler));
		
		spawnTimer = 0;
	}
	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player) {
				if (!placeFree(getBounds(), handler.object.get(i).getBounds())) {
					handler.object.get(i).health -= 10;
				}
			}
		}
	}
	
	private boolean placeFree(Rectangle myRect, Rectangle otherRect) {
		if (myRect.intersects(otherRect))
			return false;
		return true;
	}
	
	private void bossMovements() {
		int dx = player.getX() - x;
		int dy = player.getY() - y;
		float norm = (float) Math.sqrt(dx * dx + dy * dy);
		dx *= 2 / norm;
		dy *= 2 / norm;
		x += dx;
		y += dy;
		
		long pausedTime = handler.getEndPause() - handler.getStartPause();
		if (!handler.isYet()) {
			attackTimer -= pausedTime;
			handler.setYet(true);
		}
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if (attackTimer < attackCooldown)
			return;
		
		handler.addObject(new BossBullet(x + 32, y + 32, ID.BossBullet, handler, player.getX(), player.getY()));
	
		attackTimer = 0;
	}

	public void render(Graphics g) {
		g.drawImage(Gfx.bossEnemy, x, y, null);
		
		g.setColor(Color.gray);
		g.fillRect((int)(263 + handler.getCamera().getxOffset()), (int)(30 + handler.getCamera().getyOffset()), 100, 32);
		g.setColor(Color.getHSBColor( (1f * health / 10) / 360, 1f, 1f));
		g.fillRect((int)(263 + handler.getCamera().getxOffset()), (int)(30 + handler.getCamera().getyOffset()), health / 10, 32);
		g.setColor(Color.white);
		g.drawRect((int)(263 + handler.getCamera().getxOffset()), (int)(30 + handler.getCamera().getyOffset()), 100, 32);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 64, 64);
	}

}
