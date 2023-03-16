
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
	
	private Handler handler;
	private int hurtTime = 0;
	private long regenTime, lastRegenTime;
	private final Rectangle room2Bounds = new Rectangle(352, 704, 96, 33);
	private final Rectangle room3Bounds = new Rectangle(704, 352, 33, 96);
	private final Rectangle room4From2Bounds = new Rectangle(704, 1120, 33, 96);
	private final Rectangle room4From3Bounds = new Rectangle(1120, 704, 96, 33);
	private final Rectangle rifleBounds = new Rectangle(384, 1152, 32, 32);
	private final Rectangle bossFightBounds = new Rectangle(1152, 1152, 32, 32);

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.health = 5;
		handler.setHurt(false);
		
		handler.setCurrWeapon(handler.getPistol());
		handler.getMouseInput().setAttackTime(handler.getCurrWeapon().attackTime);
	}
	
	private void regenerate() {
		// this gets the time paused and then subtracts it so that
		// when in pause state the game time also pauses
		long pausedTime = handler.getEndPause() - handler.getStartPause();
		if (!handler.isYet3()) {
			regenTime -= pausedTime;
			handler.setYet3(true);
		}
		regenTime += System.currentTimeMillis() - lastRegenTime;
		lastRegenTime = System.currentTimeMillis();
		if (regenTime < 15000)
			return;
		
		if (this.health < 5 && this.health > 0)
			this.health++;
		regenTime = 0;
	}

	public void tick() {
		regenerate();
		collision();
		handler.getHud().setPlayerHealth(this.health);
		
		x += velX;
		y += velY;
		
		if (handler.isUp()) velY = -handler.getSpeed();
		else if (!handler.isDown()) velY = 0;
		
		if (handler.isDown()) velY = handler.getSpeed();
		else if (!handler.isUp()) velY = 0;
		
		if (handler.isLeft()) velX = -handler.getSpeed();
		else if (!handler.isRight()) velX = 0;
		
		if (handler.isRight()) velX = handler.getSpeed();
		else if (!handler.isLeft()) velX = 0;
		
		handler.getCamera().center(this);
		
		if (this.health <= 0) {
			handler.setCurrentState(handler.getEndState());
		}
	}
	
	public void render(Graphics g) {
		if (!handler.isHurt() && !handler.isShielded())
			g.drawImage(Gfx.player, x, y, null);
		else if (!handler.isHurt() && handler.isShielded())
			g.drawImage(Gfx.playerShielded, x - 12, y - 12, 56, 56, null);
		else if (handler.isHurt() && !handler.isShielded()) {
			g.drawImage(Gfx.playerHurt, x, y, null);
			if (hurtTime < 10) {
				hurtTime++;
				return;
			}
			hurtTime = 0;
			handler.setHurt(false);
		}
	}
	
	public boolean placeFree(int x, int y, Rectangle myRect, Rectangle otherRect) {
		myRect.x = x;
		myRect.y = y;
		if (myRect.intersects(otherRect)) {
			return false;
		}
		return true;
	}
	
	public boolean placeFree(Rectangle myRect, Rectangle otherRect) {
		if (myRect.intersects(otherRect))
			return false;
		return true;
	}
	
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.WallTile || tempObject.getId() == ID.BlockTile 
					|| tempObject.getId() == ID.CrateTile || tempObject.getId() == ID.EnemySpawnTile
					|| tempObject.getId() == ID.Room2 || tempObject.getId() == ID.Room3
					|| tempObject.getId() == ID.Room4From2 || tempObject.getId() == ID.Room4From3) {
				if (!placeFree((int)(x + velX), y, getBounds(), tempObject.getBounds())) {
					velX = 0;
				}
				if (!placeFree(x, (int)(y + velY), getBounds(), tempObject.getBounds())) {
					velY = 0;
				}
			}
		}
		if (!handler.isRoom2()) {
			if (!placeFree(getBounds(), room2Bounds))
				handler.getBoundsManager().setInRoom2Bounds(true);
			else
				handler.getBoundsManager().setInRoom2Bounds(false);
		}
		if (!handler.isRoom3()) {
			if (!placeFree(getBounds(), room3Bounds))
				handler.getBoundsManager().setInRoom3Bounds(true);
			else
				handler.getBoundsManager().setInRoom3Bounds(false);
		}
		if (!handler.isRoom4()) {
			// Room 4 From 2
			if (!placeFree(getBounds(), room4From2Bounds))
				handler.getBoundsManager().setInRoom4From2Bounds(true);
			else
				handler.getBoundsManager().setInRoom4From2Bounds(false);
			// Room 4 From 3
			if (!placeFree(getBounds(), room4From3Bounds))
				handler.getBoundsManager().setInRoom4From3Bounds(true);
			else
				handler.getBoundsManager().setInRoom4From3Bounds(false);
		}
		if (!handler.getBoundsManager().isPickedUpRifle()) {
			if (!placeFree(getBounds(), rifleBounds))
				handler.getBoundsManager().setInRifleBounds(true);
			else
				handler.getBoundsManager().setInRifleBounds(false);
		}
		if (!handler.getBoundsManager().isInBossFight()) {
			if (!placeFree(getBounds(), bossFightBounds))
				handler.getBoundsManager().setInBossFightBounds(true);
			else
				handler.getBoundsManager().setInBossFightBounds(false);
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
