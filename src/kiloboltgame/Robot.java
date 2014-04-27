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
	public static Rectangle boundingBox = new Rectangle(0, 0, 0, 0);
	
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

		boundingBox.setRect(centerX - 61, centerY - 62, 51, 34);
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

	public Rectangle getBoundingBox(){
		return boundingBox;
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