
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject {
	
	private Handler handler;
	private int critBonus = 2;
	
	public Bullet(int x, int y, ID id, Handler handler, int mx, int my) {
		super(x, y, id);
		this.handler = handler;
		
		calculateVelocity(x, y, mx, my);
	}
	
	private int calculateCrit() {
		// 20% chance to critical strike and deal double damage
		int chance = (int)(Math.random() * 100) + 1;
		if (chance <= 20)
			return critBonus;
		return 1;
	}
	
	public void tick() {
		collision();
		
		x += velX;
		y += velY;
	}
	
	public boolean placeFree(int x, int y, Rectangle myRect, Rectangle otherRect) {
		myRect.x = x;
		myRect.y = y;
		if (myRect.intersects(otherRect))
			return false;
		return true;
	}
	
	public void collision() {
		boolean critted = false;
		int crit = calculateCrit();
		if (crit == critBonus) critted = true;
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.WallTile || tempObject.getId() == ID.BlockTile 
					|| tempObject.getId() == ID.EnemySpawnTile || tempObject.getId() == ID.CrateTile
					|| tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.Room2
					|| tempObject.getId() == ID.Room3 || tempObject.getId() == ID.Room4From2
					|| tempObject.getId() == ID.Room4From3 || tempObject.getId() == ID.BossMinion
					|| tempObject.getId() == ID.BossEnemy) {
				if (!placeFree((int)(x + velX), y, getBounds(), tempObject.getBounds())) {
					tempObject.health -= (handler.getCurrWeapon().damage + handler.getBonusDmg()) * crit;
					if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.BossMinion
							|| tempObject.getId() == ID.BossEnemy) {
						if (tempObject.getId() != ID.BossEnemy)
							handler.getHud().setGold(handler.getHud().getGold() + (10 * crit));
						handler.setRenderDmg(true);
						handler.setX(tempObject.getX());
						handler.setY(tempObject.getY());
						handler.setDmg("-" + ((handler.getCurrWeapon().damage + handler.getBonusDmg()) * crit));
						if (critted) handler.setCritted(true);
					}
					handler.removeObject(this);
				}
				else if (!placeFree(x, (int)(y + velY), getBounds(), tempObject.getBounds())) {
					tempObject.health -= (handler.getCurrWeapon().damage + handler.getBonusDmg()) * crit;
					if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.BossMinion
							|| tempObject.getId() == ID.BossEnemy) {
						if (tempObject.getId() != ID.BossEnemy)
							handler.getHud().setGold(handler.getHud().getGold() + (10 * crit));
						handler.setRenderDmg(true);
						handler.setX(tempObject.getX());
						handler.setY(tempObject.getY());
						handler.setDmg("-" + ((handler.getCurrWeapon().damage + handler.getBonusDmg()) * crit));
						if (critted) handler.setCritted(true);
					}
					handler.removeObject(this);
				}
			}
		}
	}
	
	public void calculateVelocity(int fromX, int fromY, int toX, int toY) {
		double distance = Math.sqrt(Math.pow((toX - fromX), 2) + Math.pow((toY - fromY), 2));
		float speed = 10f;
		velX = (float)((toX - fromX) * speed / distance);
		velY = (float)((toY - fromY) * speed / distance);
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillOval(x, y, 8, 8);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 8, 8);
	}
	
}
