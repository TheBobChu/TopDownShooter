
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 7534332931447218814L;
	
	private boolean running = false;
	private Thread thread;
	
	private Handler handler;
	private Camera camera;
	private HUD hud;
	private LevelManager levelManager;
	private BoundsManager boundsManager;
	private BufferedImage level;
	private KeyInput keyInput;
	private MouseInput mouseInput;
	private State currentState;
	private State menuState;
	private State gameState;
	private State pauseState;
	private State helpState;
	private State shopState;
	private State endState;
	private State winState;
	private Weapon currWeapon;
	private Pistol pistol;
	private Rifle rifle;
	private char[][] bfsGraph;
	
	public Game() {
		new Window("Game", 640, 480, this);
		start();
		
		handler = new Handler(this);
		bfsGraph = new char[784][784];
		pistol = new Pistol();
		rifle = new Rifle();
		hud = new HUD(handler);
		camera = new Camera(0, 0);
		levelManager = new LevelManager(handler);
		boundsManager = new BoundsManager(handler);
		menuState = new MenuState(handler);
		gameState = new GameState(handler);
		pauseState = new PauseState(handler);
		helpState = new HelpState(handler);
		shopState = new ShopState(handler);
		endState = new EndState(handler);
		winState = new WinState(handler);
		currentState = menuState;
		keyInput = new KeyInput(handler);
		mouseInput = new MouseInput(handler);
		this.addKeyListener(keyInput);
		this.addMouseListener(mouseInput);
		this.addMouseMotionListener(mouseInput);
		level = ImageLoader.loadImage("/level.png");
		
		Gfx.init();
		
		loadLevel(level);
		
		handler.addObject(new Player(384, 368, ID.Player, handler));
	}
	
	public void reset() {
		handler.clearAll();
		handler.setTime(0);
		handler.setRenderDmg(false);
		handler.setRoom2(false);
		handler.setRoom3(false);
		handler.setRoom4(false);
		handler.setBonusDmg(0);
		handler.setShielded(false);
		handler.setTimeDelay(50);	
		levelManager.setAlternate(false);
		shopState.damageCost = 100;
		pistol.damage = 5;
		rifle.damage = 2;
		hud.setLevel(0);
		hud.setRoom2Level(0);
		hud.setRoom3Level(0);
		hud.setRoom4Level(0);
		hud.setPlayerHealth(5);
		hud.setGold(0);
		hud.setPistolAmmo(handler.getPistol().ammo);
		hud.setRifleAmmo(handler.getRifle().ammo);
		hud.setEnemiesLeft(0);
		hud.setHotbar2(false);
		hud.setHotbar3(false);
		hud.setOnPistol(true);
		hud.setOnRifle(false);
		boundsManager.setPickedUpRifle(false);
		boundsManager.setInBossFight(false);
		loadLevel(level);
		handler.addObject(new Player(384, 368, ID.Player, handler));
	}
	
	public void startBossFight() {
		for (int j = 0; j < 20; j++) {
			for (int i = 0; i < handler.object.size(); i++) {
				if (handler.object.get(i).getId() == ID.WallTile ||
						handler.object.get(i).getId() == ID.CrateTile ||
						handler.object.get(i).getId() == ID.MidTile ||
						handler.object.get(i).getId() == ID.BasicEnemy ||
						handler.object.get(i).getId() == ID.BlockTile ||
						handler.object.get(i).getId() == ID.BossFightTile ||
						handler.object.get(i).getId() == ID.Rifle ||
						handler.object.get(i).getId() == ID.EnemySpawnTile) {
					if (handler.object.get(i).getId() == ID.BasicEnemy) 
						hud.setEnemiesLeft(hud.getEnemiesLeft() - 1);
					handler.removeObject(handler.object.get(i));
				}
			}
		}
		for (int i = 0; i < 49; i++) {
			handler.addObject(new WallTile(i * 32, 0, ID.WallTile));
			handler.addObject(new WallTile(0, i * 32, ID.WallTile));
			handler.addObject(new WallTile(i * 32, 48 * 32, ID.WallTile));
			handler.addObject(new WallTile(48 * 32, i * 32, ID.WallTile));
		}
		handler.addObject(new BossEnemy(752, 752, ID.BossEnemy, handler));
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}
		}
		stop();
	}
	
	private void tick() {
		if (getCurrentState() == menuState) {
			
		}
		else if (getCurrentState() == pauseState) {
			
		}
		else if (getCurrentState() == helpState) {
			
		}
		else if (getCurrentState() == shopState) {
			
		}
		else if (getCurrentState() == endState) {
			
		}
		else if (getCurrentState() == winState) {
			
		}
		else {
			handler.tick();
			if (!boundsManager.isInBossFight()) levelManager.tick();
			hud.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D)g;
		
		if (getCurrentState() == menuState) {
			menuState.render(g);
		}
		else if (getCurrentState() == helpState) {
			helpState.render(g);
		}
		else if (getCurrentState() == shopState) {
			shopState.render(g);
		}
		else if (getCurrentState() == endState) {
			endState.render(g);
		}
		else if (getCurrentState() == winState) {
			winState.render(g);
		}
		else {
			g2d.translate(-camera.getxOffset(), -camera.getyOffset());
			handler.render(g);
			hud.render(g);
			boundsManager.render(g);
			g2d.translate(camera.getxOffset(), camera.getyOffset());
			if (getCurrentState() == pauseState) {
				pauseState.render(g);
			}
		}
		g.dispose();
		bs.show();
	}
	
	private void loadLevel(BufferedImage level) {
		for (int x = 0; x < level.getWidth(); x++) {
			for (int y = 0; y < level.getHeight(); y++) {
				int pixel = level.getRGB(x, y);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if (red == 255 && green == 255 && blue == 255) {
					handler.addObject(new FloorTile(x * 32, y * 32, ID.FloorTile));
					for (int i = x * 16; i < x * 16 + 16; i++) {
						for (int j = y * 16; j < y * 16 + 16; j++) {
							bfsGraph[j][i] = '1';
						}
					}
				}
				if (red == 255 && green == 0 && blue == 0) {
					handler.addObject(new FloorTile(x * 32, y * 32, ID.FloorTile));
					if ((x == 11 && y == 24) || (x == 12 && y == 24) || (x == 13 && y == 24))
						handler.addObject(new WallTile(x * 32, y * 32, ID.Room2));
					else if ((x == 24 && y == 11) || (x == 24 && y == 12) || (x == 24 && y == 13))
						handler.addObject(new WallTile(x * 32, y * 32, ID.Room3));
					else if ((x == 24 && y == 35) || (x == 24 && y == 36) || (x == 24 && y == 37))
						handler.addObject(new WallTile(x * 32, y * 32, ID.Room4From2));
					else if ((x == 35 && y == 24) || (x == 36 && y == 24) || (x == 37 && y == 24))
						handler.addObject(new WallTile(x * 32, y * 32, ID.Room4From3));
					else handler.addObject(new WallTile(x * 32, y * 32, ID.WallTile));
					for (int i = x * 16; i < x * 16 + 16; i++) {
						for (int j = y * 16; j < y * 16 + 16; j++) {
							bfsGraph[j][i] = '0';
						}
					}
				}
				if (red == 0 && green == 0 && blue == 255) {
					handler.addObject(new FloorTile(x * 32, y * 32, ID.FloorTile));
					handler.addObject(new MidTile(x * 32, y * 32, ID.MidTile));
					for (int i = x * 16; i < x * 16 + 16; i++) {
						for (int j = y * 16; j < y * 16 + 16; j++) {
							bfsGraph[j][i] = '1';
						}
					}
				}
				if (red == 0 && green == 255 && blue == 0) {
					handler.addObject(new FloorTile(x * 32, y * 32, ID.FloorTile));
					handler.addObject(new CrateTile(x * 32, y * 32, ID.CrateTile, handler));
					for (int i = x * 16; i < x * 16 + 16; i++) {
						for (int j = y * 16; j < y * 16 + 16; j++) {
							bfsGraph[j][i] = '2';
						}
					}
				}
				if (red == 0 && green == 0 && blue == 0) {
					handler.addObject(new FloorTile(x * 32, y * 32, ID.FloorTile));
					handler.addObject(new BlockTile(x * 32, y * 32, ID.BlockTile));
					for (int i = x * 16; i < x * 16 + 16; i++) {
						for (int j = y * 16; j < y * 16 + 16; j++) {
							bfsGraph[j][i] = '0';
						}
					}
				}
				if (red == 255 && green == 0 && blue == 255) {
					handler.addObject(new FloorTile(x * 32, y * 32, ID.FloorTile));
					handler.addObject(new EnemySpawnTile(x * 32, y * 32, ID.EnemySpawnTile));
					for (int i = x * 16; i < x * 16 + 16; i++) {
						for (int j = y * 16; j < y * 16 + 16; j++) {
							bfsGraph[j][i] = '1';
						}
					}
				}
				if (red == 255 && green == 255 && blue == 0) {
					handler.addObject(new FloorTile(x * 32, y * 32, ID.FloorTile));
					for (int i = x * 16; i < x * 16 + 16; i++) {
						for (int j = y * 16; j < y * 16 + 16; j++) {
							bfsGraph[j][i] = '0';
						}
					}
				}
				if (red == 0 && green == 255 && blue == 255) {
					handler.addObject(new FloorTile(x * 32, y * 32, ID.FloorTile));
					for (int i = x * 16; i < x * 16 + 16; i++) {
						for (int j = y * 16; j < y * 16 + 16; j++) {
							bfsGraph[j][i] = '3';
						}
					}
				}
			}
		}
		handler.addObject(new RifleObject(384, 1152, ID.Rifle));
		handler.addObject(new BossFightTile(37 * 32, 37 * 32, ID.BossFightTile));
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
	public Camera getCamera() {
		return camera;
	}

	public Pistol getPistol() {
		return pistol;
	}

	public Rifle getRifle() {
		return rifle;
	}

	public MouseInput getMouseInput() {
		return mouseInput;
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public BoundsManager getBoundsManager() {
		return boundsManager;
	}

	public HUD getHud() {
		return hud;
	}

	public char[][] getBfsGraph() {
		return bfsGraph;
	}

	public State getMenuState() {
		return menuState;
	}

	public State getGameState() {
		return gameState;
	}

	public State getPauseState() {
		return pauseState;
	}
	
	public State getHelpState() {
		return helpState;
	}
	
	public State getShopState() {
		return shopState;
	}
	
	public State getEndState() {
		return endState;
	}
	
	public State getWinState() {
		return winState;
	}

	public Weapon getCurrWeapon() {
		return currWeapon;
	}

	public void setCurrWeapon(Weapon currWeapon) {
		this.currWeapon = currWeapon;
	}

}
