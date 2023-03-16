
public class LevelManager {
	
	private Handler handler;
	private boolean alternate;
	
	public LevelManager(Handler handler) {
		this.handler = handler;
	}
	
	public void tick() {
		//check if there are enemies in map, if not then next level starts
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.BasicEnemy) {
				return;
			}
		}
		
		alternate = !alternate;
		// Room 1 Spawns
		if (!handler.isRoom4()) {
			handler.addObject(new BasicEnemy(0, 352 + (int)((Math.random() * 64) + 1), ID.BasicEnemy, handler));
			handler.getHud().setLevel(handler.getHud().getLevel() + 1);
			if (handler.getHud().getLevel() >= 3)
				handler.addObject(new BasicEnemy(0, 352 + (int)((Math.random() * 64) + 1), ID.BasicEnemy, handler));
			if (handler.getHud().getLevel() == 5) {
				handler.addObject(new BasicEnemy(0, 352 + (int)((Math.random() * 64) + 1), ID.BasicEnemy, handler));
				handler.addObject(new BasicEnemy(0, 352 + (int)((Math.random() * 64) + 1), ID.BasicEnemy, handler));
				handler.addObject(new BasicEnemy(0, 352 + (int)((Math.random() * 64) + 1), ID.BasicEnemy, handler));
			}
		}
		// Room 2 Spawns, Starts When You Unlock Room 2
		if (handler.isRoom2()) {
			if (!alternate) {
				handler.addObject(new BasicEnemy(0, 1120 + (int)((Math.random() * 64) + 1), ID.BasicEnemy, handler));
				if (handler.getHud().getRoom2Level() >= 2)
					handler.addObject(new BasicEnemy(0, 1120 + (int)((Math.random() * 64) + 1), ID.BasicEnemy, handler));
				if (handler.getHud().getRoom2Level() >= 5)
					handler.addObject(new BasicEnemy(0, 1120 + (int)((Math.random() * 64) + 1), ID.BasicEnemy, handler));
			}
			else if (alternate) {
				handler.addObject(new BasicEnemy(352 + (int)((Math.random() * 64) + 1), 1536, ID.BasicEnemy, handler));
				if (handler.getHud().getRoom2Level() >= 2)
					handler.addObject(new BasicEnemy(352 + (int)((Math.random() * 64) + 1), 1536, ID.BasicEnemy, handler));
				if (handler.getHud().getRoom2Level() >= 5)
					handler.addObject(new BasicEnemy(352 + (int)((Math.random() * 64) + 1), 1536, ID.BasicEnemy, handler));
			}
			handler.getHud().setRoom2Level(handler.getHud().getRoom2Level() + 1);
		}
		// Room 3 Spawns, Starts When You Unlock Room 3
		if (handler.isRoom3()) {
			if (!alternate) {
				handler.addObject(new BasicEnemy(1120 + (int)((Math.random() * 64) + 1), 0, ID.BasicEnemy, handler));
				if (handler.getHud().getRoom3Level() >= 2)
					handler.addObject(new BasicEnemy(1120 + (int)((Math.random() * 64) + 1), 0, ID.BasicEnemy, handler));
				if (handler.getHud().getRoom3Level() >= 4)
					handler.addObject(new BasicEnemy(1120 + (int)((Math.random() * 64) + 1), 0, ID.BasicEnemy, handler));
				if (handler.getHud().getRoom3Level() == 5) {
					handler.addObject(new BasicEnemy(1120 + (int)((Math.random() * 64) + 1), 0, ID.BasicEnemy, handler));
					handler.addObject(new BasicEnemy(1120 + (int)((Math.random() * 64) + 1), 0, ID.BasicEnemy, handler));
				}
			}
			else if (alternate) {
				handler.addObject(new BasicEnemy(1536, 352 + (int)((Math.random() * 64) + 1), ID.BasicEnemy, handler));
				if (handler.getHud().getRoom3Level() >= 2)
					handler.addObject(new BasicEnemy(1536, 352 + (int)((Math.random() * 64) + 1), ID.BasicEnemy, handler));
				if (handler.getHud().getRoom3Level() >= 4)
					handler.addObject(new BasicEnemy(1536, 352 + (int)((Math.random() * 64) + 1), ID.BasicEnemy, handler));
				if (handler.getHud().getRoom3Level() >= 4) {
					handler.addObject(new BasicEnemy(1536, 352 + (int)((Math.random() * 64) + 1), ID.BasicEnemy, handler));
					handler.addObject(new BasicEnemy(1536, 352 + (int)((Math.random() * 64) + 1), ID.BasicEnemy, handler));
				}
			}
			handler.getHud().setRoom3Level(handler.getHud().getRoom3Level() + 1);
		}
		// Room 4 Spawns, Starts When You Unlock Room 4
		if (handler.isRoom4()) {
			if (!alternate) {
				handler.addObject(new BasicEnemy(1536, 1120 + (int)((Math.random() * 64) + 1), ID.BasicEnemy, handler));
				if (handler.getHud().getRoom4Level() >= 2)
					handler.addObject(new BasicEnemy(1536, 1120 + (int)((Math.random() * 64) + 1), ID.BasicEnemy, handler));
				if (handler.getHud().getRoom4Level() == 4)
					handler.addObject(new BasicEnemy(1536, 1120 + (int)((Math.random() * 64) + 1), ID.BasicEnemy, handler));
					handler.addObject(new BasicEnemy(1536, 1120 + (int)((Math.random() * 64) + 1), ID.BasicEnemy, handler));
					handler.addObject(new BasicEnemy(1536, 1120 + (int)((Math.random() * 64) + 1), ID.BasicEnemy, handler));
			}
			else if (alternate) {
				handler.addObject(new BasicEnemy(1120 + (int)((Math.random() * 64) + 1), 1536, ID.BasicEnemy, handler));
				if (handler.getHud().getRoom4Level() >= 2)
					handler.addObject(new BasicEnemy(1120 + (int)((Math.random() * 64) + 1), 1536, ID.BasicEnemy, handler));
				if (handler.getHud().getRoom4Level() == 4) {
					handler.addObject(new BasicEnemy(1120 + (int)((Math.random() * 64) + 1), 1536, ID.BasicEnemy, handler));
					handler.addObject(new BasicEnemy(1120 + (int)((Math.random() * 64) + 1), 1536, ID.BasicEnemy, handler));
					handler.addObject(new BasicEnemy(1120 + (int)((Math.random() * 64) + 1), 1536, ID.BasicEnemy, handler));
				}
			}
			handler.getHud().setRoom4Level(handler.getHud().getRoom4Level() + 1);
		}
	}

	public boolean isAlternate() {
		return alternate;
	}

	public void setAlternate(boolean alternate) {
		this.alternate = alternate;
	}

}
