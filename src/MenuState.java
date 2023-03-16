
import java.awt.Graphics;

public class MenuState extends State {
	
	public MenuState(Handler handler) {
		super(handler);
		this.menuHoverNone = true;
		this.menuHoverStart = false;
		this.menuHoverHelp = false;
		this.menuHoverQuit = false;
	}
	
	public void tick() {
		
	}

	public void render(Graphics g) {
		if (menuHoverNone && !menuHoverStart && !menuHoverHelp && !menuHoverQuit) {
			g.drawImage(Gfx.menuHoverNone, -7, -18, 640, 480, null);
		}
		else if (!menuHoverNone && menuHoverStart && !menuHoverHelp && !menuHoverQuit) {
			g.drawImage(Gfx.menuHoverStart, -7, -18, 640, 480, null);
		}
		else if (!menuHoverNone && !menuHoverStart && menuHoverHelp && !menuHoverQuit) {
			g.drawImage(Gfx.menuHoverHelp, -7, -18, 640, 480, null);
		}
		else if (!menuHoverNone && !menuHoverStart && !menuHoverHelp && menuHoverQuit) {
			g.drawImage(Gfx.menuHoverQuit, -7, -18, 640, 480, null);
		}
	}

}
