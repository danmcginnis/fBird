package kiloboltgame;

public class Background {

	private int backgroundX;
	private int backgroundY;
	private int speedX;

	public Background(int x, int y) {
		backgroundX = x;
		backgroundY = y;
		speedX = 0;
	}

	public void update() {
		backgroundX += speedX;

		if (backgroundX <= -2160) {
			backgroundX += 4320;
		}
	}

	public int getBackGroundX() {
		return backgroundX;
	}

	public void setBackGroundX(int backgroundX) {
		this.backgroundX = backgroundX;
	}

	public int getBackGroundY() {
		return backgroundY;
	}

	public void setBackGroundY(int backgroundY) {
		this.backgroundY = backgroundY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
}