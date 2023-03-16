
import java.awt.Graphics;

public class PauseState extends State {

	public PauseState(Handler handler) {
		super(handler);
		this.pauseHoverNone = true;
		this.pauseHoverResume = false;
		this.pauseHoverShop = false;
		this.pauseHoverMainMenu = false;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		if (pauseHoverNone && !pauseHoverResume && !pauseHoverShop && !pauseHoverMainMenu)
			g.drawImage(Gfx.pauseHoverNone, -7, -18, 640, 480, null);
		else if (!pauseHoverNone && pauseHoverResume && !pauseHoverShop && !pauseHoverMainMenu)
			g.drawImage(Gfx.pauseHoverResume, -7, -18, 640, 480, null);
		else if (!pauseHoverNone && !pauseHoverResume && pauseHoverShop && !pauseHoverMainMenu)
			g.drawImage(Gfx.pauseHoverShop, -7, -18, 640, 480, null);
		else if (!pauseHoverNone && !pauseHoverResume && !pauseHoverShop && pauseHoverMainMenu)
			g.drawImage(Gfx.pauseHoverMainMenu, -7, -18, 640, 480, null);
	}

}
