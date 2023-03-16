
import java.awt.Graphics;
import java.awt.Rectangle;

public class FloorTile extends GameObject {

	private int floorNum;
	
	public FloorTile(int x, int y, ID id) {
		super(x, y, id);
		
		this.floorNum = (int)((Math.random() * 9) + 1);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		if (floorNum == 1)
			g.drawImage(Gfx.floor1, x, y, 32, 32, null);
		else if (floorNum == 2)
			g.drawImage(Gfx.floor2, x, y, 32, 32, null);
		else if (floorNum == 3)
			g.drawImage(Gfx.floor3, x, y, 32, 32, null);
		else if (floorNum == 4)
			g.drawImage(Gfx.floor4, x, y, 32, 32, null);
		else if (floorNum == 5)
			g.drawImage(Gfx.floor5, x, y, 32, 32, null);
		else if (floorNum == 6)
			g.drawImage(Gfx.floor6, x, y, 32, 32, null);
		else if (floorNum == 7)
			g.drawImage(Gfx.floor7, x, y, 32, 32, null);
		else if (floorNum == 8)
			g.drawImage(Gfx.floor8, x, y, 32, 32, null);
		else if (floorNum == 9)
			g.drawImage(Gfx.floor9, x, y, 32, 32, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
