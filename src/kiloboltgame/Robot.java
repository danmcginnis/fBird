package kiloboltgame;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Robot {

	// Constants are Here
	final int JUMPSPEED = -15;
	final int MOVESPEED = 5;

	private int centerX = 100;
	private int centerY = 377;

	private int speedY = 0;
	public static Rectangle rect = new Rectangle(0, 0, 0, 0);
	public static Rectangle rect2 = new Rectangle(0, 0, 0, 0);
	public static Rectangle rect3 = new Rectangle(0, 0, 0, 0);
	public static Rectangle rect4 = new Rectangle(0, 0, 0, 0);
	
	private Background bg1 = StartingClass.getBg1();
	private Background bg2 = StartingClass.getBg2();

	public void update() {
		// Moves Character or Scrolls Background accordingly.


		// Updates Y Position
		centerY += speedY;
		if (centerY<30)
			centerY=30;
		// Handles Jumping
		if (speedY<=15)
		speedY += 1;
		


		// Prevents going beyond X coordinate of 0

		rect.setRect(centerX - 34, centerY - 63, 68, 63);
		rect2.setRect(rect.getX(), rect.getY() + 63, 68, 64);
		rect3.setRect(rect.getX() - 26, rect.getY() + 32, 26, 20);
		rect4.setRect(rect.getX() + 68, rect.getY() + 32, 26, 20);
	}



	private void stop() {
		

	}

	public void jump() {
		speedY=-12;
		
	}


	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}



	public int getSpeedY() {
		return speedY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}


	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}




}