
import java.awt.Graphics;

public class HelpState extends State {
	
	public HelpState(Handler handler) {
		super(handler);
		this.helpHoverNone = true;
		this.helpHoverOk = false;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		if (helpHoverNone && !helpHoverOk)
			g.drawImage(Gfx.helpHoverNone, -7, -18, 640, 480, null);
		else if (!helpHoverNone && helpHoverOk)
			g.drawImage(Gfx.helpHoverOk, -7, -18, 640, 480, null);
	}

}
