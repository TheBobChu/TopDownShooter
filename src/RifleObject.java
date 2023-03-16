
import java.awt.Graphics;
import java.awt.Rectangle;

public class RifleObject extends GameObject {

	public RifleObject(int x, int y, ID id) {
		super(x, y, id);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(Gfx.rifle, x, y, 32, 32, null);
	}

	public Rectangle getBounds() {
		return null;
	}

}
