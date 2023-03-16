
import java.awt.Graphics;

public class WinState extends State {

	public WinState(Handler handler) {
		super(handler);
		this.winHoverNone = true;
		this.winHoverBack = false;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		if (winHoverNone && !winHoverBack)
			g.drawImage(Gfx.winHoverNone, -7, -18, 640, 480, null);
		else if (!winHoverNone && winHoverBack)
			g.drawImage(Gfx.winHoverBack, -7, -18, 640, 480, null);
	}

}
