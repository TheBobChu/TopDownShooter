
import java.awt.Graphics;
import java.awt.Rectangle;

public class BlockTile extends GameObject {

	public BlockTile(int x, int y, ID id) {
		super(x, y, id);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(Gfx.block, x, y, 32, 32, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

	
	
}
