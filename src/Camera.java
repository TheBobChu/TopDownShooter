
public class Camera {
	
	private float xOffset, yOffset;
	
	public Camera(float xOffset, float yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void center(GameObject object) {
		xOffset = object.getX() - 640/2 + 23;
		yOffset = object.getY() - 480/2 + 34;
		checkBlankSpace();
	}
	
	public void checkBlankSpace() {
		if (xOffset <= 0) xOffset = 0;
		else if (xOffset >= 941) xOffset = 941;
		if (yOffset <= 0) yOffset = 0;
		else if (yOffset >= 1126) yOffset = 1126;
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

}
