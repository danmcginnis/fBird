package flappyBirdGame;

import java.awt.Rectangle;

public class Pipe {

	private char orientation;
	private int x = 500;
	private int y;
	private int speedX = -5;

	private Rectangle boundingBox = new Rectangle(0, 0, 0, 0);

	public Pipe(char ori, int yVal) {
		orientation = ori;
		y = yVal;
		if (orientation == 'd') {
			boundingBox.setRect(x, y, 120, 800);
		} 
		else {
			boundingBox.setRect(x, y + 1000, 120, 800);
		}
	}

	public Pipe(char ori, int yVal, int xVal) {
		orientation = ori;
		y = yVal;
		x = xVal;
		if (orientation == 'd') {
			boundingBox.setRect(x, y, 120, 800);
		} 
		else {
			boundingBox.setRect(x, y + 1000, 120, 800);
		}
	}

	public Rectangle getBoundingBox() {
		return boundingBox;
	}

	public void update() {
		x += speedX;
		if (orientation == 'd') {
			boundingBox.setRect(x, y, 120, 800);
		} 
		else {
			boundingBox.setRect(x, y + 1000, 120, 800);
		}
	}

	public char getOrientation() {
		return orientation;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}