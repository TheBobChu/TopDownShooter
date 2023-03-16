
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class BoundsManager {

	private Handler handler;
	private boolean inRoom2Bounds, inRoom3Bounds, inRoom4From2Bounds, inRoom4From3Bounds;
	private boolean inRifleBounds, pickedUpRifle;
	private boolean inBossFightBounds, inBossFight;
	
	public BoundsManager(Handler handler) {
		this.handler = handler;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		Font font = new Font("arial", 0, 10);
		g.setFont(font);
		//FontMetrics metrics = g.getFontMetrics(font);
		/*if (inRoom2Bounds && !handler.getCamera().isRoom2()) {
			Rectangle room2box = new Rectangle((int)(0 + handler.getCamera().getxOffset()), (int)(768 + handler.getCamera().getyOffset()), 800, 32);
			int room2boxX1 = room2box.x + (room2box.width - metrics.stringWidth("Press ENTER to unlock room 2 for 500 gold")) / 2;
			int room2boxY1 = room2box.y + (room2box.height - metrics.getHeight() / 2) + metrics.getAscent();
			g.drawString("Press ENTER to unlock room 2 for 500 gold", room2boxX1, room2boxY1);
		}*/
		if (inRoom2Bounds && !handler.isRoom2())
			g.drawString("Press ENTER to unlock room 2 for 500 gold", (int)(218 + handler.getCamera().getxOffset()), (int)(432 + handler.getCamera().getyOffset()));
		if (inRoom3Bounds && !handler.isRoom3())
			g.drawString("Press ENTER to unlock room 3 for 500 gold", (int)(218 + handler.getCamera().getxOffset()), (int)(432 + handler.getCamera().getyOffset()));
		if (inRoom4From2Bounds && !handler.isRoom4()) {
			g.drawString("Press ENTER to unlock room 4 for 1000 gold", (int)(217 + handler.getCamera().getxOffset()), (int)(417 + handler.getCamera().getyOffset()));
			g.drawString("Must have rooms 2 & 3 unlocked", (int)(239 + handler.getCamera().getxOffset()), (int)(432 + handler.getCamera().getyOffset()));
		}
		if (inRoom4From3Bounds && !handler.isRoom4()) {
			g.drawString("Press ENTER to unlock room 4 for 1000 gold", (int)(217 + handler.getCamera().getxOffset()), (int)(417 + handler.getCamera().getyOffset()));
			g.drawString("Must have rooms 2 & 3 unlocked", (int)(239 + handler.getCamera().getxOffset()), (int)(432 + handler.getCamera().getyOffset()));
		}
		if (inRifleBounds && !pickedUpRifle)
			g.drawString("Press ENTER to unlock rifle for 1000 gold", (int)(218 + handler.getCamera().getxOffset()), (int)(432 + handler.getCamera().getyOffset()));
		if (inBossFightBounds && !inBossFight)
			g.drawString("Press ENTER to start boss fight", (int)(239 + handler.getCamera().getxOffset()), (int)(432 + handler.getCamera().getyOffset()));
	}

	public boolean isInRoom2Bounds() {
		return inRoom2Bounds;
	}

	public void setInRoom2Bounds(boolean inRoom2Bounds) {
		this.inRoom2Bounds = inRoom2Bounds;
	}

	public boolean isInRifleBounds() {
		return inRifleBounds;
	}

	public void setInRifleBounds(boolean inRifleBounds) {
		this.inRifleBounds = inRifleBounds;
	}

	public boolean isPickedUpRifle() {
		return pickedUpRifle;
	}

	public void setPickedUpRifle(boolean pickedUpRifle) {
		this.pickedUpRifle = pickedUpRifle;
	}

	public boolean isInRoom3Bounds() {
		return inRoom3Bounds;
	}

	public void setInRoom3Bounds(boolean inRoom3Bounds) {
		this.inRoom3Bounds = inRoom3Bounds;
	}

	public boolean isInRoom4From2Bounds() {
		return inRoom4From2Bounds;
	}

	public void setInRoom4From2Bounds(boolean inRoom4From2Bounds) {
		this.inRoom4From2Bounds = inRoom4From2Bounds;
	}

	public boolean isInRoom4From3Bounds() {
		return inRoom4From3Bounds;
	}

	public void setInRoom4From3Bounds(boolean inRoom4From3Bounds) {
		this.inRoom4From3Bounds = inRoom4From3Bounds;
	}

	public boolean isInBossFightBounds() {
		return inBossFightBounds;
	}

	public void setInBossFightBounds(boolean inBossFightBounds) {
		this.inBossFightBounds = inBossFightBounds;
	}

	public boolean isInBossFight() {
		return inBossFight;
	}

	public void setInBossFight(boolean inBossFight) {
		this.inBossFight = inBossFight;
	}
	
}
