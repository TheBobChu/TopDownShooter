
import java.awt.Graphics;
import java.awt.Rectangle;

public class MidTile extends GameObject {

	public MidTile(int x, int y, ID id) {
		super(x, y, id);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(Gfx.mid, x + 32, y + 32, -96, -96, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 96, 96);
	}

}
