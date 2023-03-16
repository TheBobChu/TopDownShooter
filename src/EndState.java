
import java.awt.Graphics;

public class EndState extends State {

	public EndState(Handler handler) {
		super(handler);
		this.endHoverNone = true;
		this.endHoverBack = false;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		if (endHoverNone && !endHoverBack)
			g.drawImage(Gfx.endHoverNone, -7, -18, 640, 480, null);
		else if (!endHoverNone && endHoverBack)
			g.drawImage(Gfx.endHoverBack, -7, -18, 640, 480, null);
	}

}
