import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BossBullet extends GameObject {

	private Handler handler;
	
	public BossBullet(int x, int y, ID id, Handler handler, int px, int py) {
		super(x, y, id);
		this.handler = handler;
		
		calculateVelocity(x, y, px, py);
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
	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Player || tempObject.getId() == ID.WallTile) {
				if (!placeFree((int)(x + velX), y, getBounds(), tempObject.getBounds())) {
					if (tempObject.getId() == ID.Player) {
						if (handler.isShielded())
							handler.setShielded(false);
						else {
							tempObject.health--;
							handler.setHurt(true);
						}
					}
					handler.removeObject(this);
				}
				else if (!placeFree(x, (int)(y + velY), getBounds(), tempObject.getBounds())) {
					if (tempObject.getId() == ID.Player) {
						if (handler.isShielded())
							handler.setShielded(false);
						else {
							tempObject.health--;
							handler.setHurt(true);
						}
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
		g.setColor(Color.red);
		g.fillOval(x, y, 16, 16);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

}
