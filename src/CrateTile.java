
import java.awt.Graphics;
import java.awt.Rectangle;

public class CrateTile extends GameObject {
	
	private Handler handler;

	public CrateTile(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.health = 50;
	}

	public void tick() {	
		if (this.health <= 0) {
			handler.removeObject(this);
			for (int i = x/2; i < x/2 + 16; i++) {
				for (int j = y/2; j < y/2 + 16; j++) {
					// tile above crate
					if (handler.getBfsGraph()[j][i - 16] == '3') {
						handler.getBfsGraph()[j][i - 16] = '1';
						handler.getBfsGraph()[j][i] = '3';
					}
					// tile to the left of crate
					if (handler.getBfsGraph()[j - 16][i] == '3') {
						handler.getBfsGraph()[j - 16][i] = '1';
						handler.getBfsGraph()[j][i] = '3';
					}
					// isolated crate
					handler.getBfsGraph()[j][i] = '1';
				}
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(Gfx.crate, x, y, 32, 32, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
