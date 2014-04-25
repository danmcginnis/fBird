package kiloboltgame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import kiloboltgame.framework.Animation;

public class StartingClass extends Applet implements Runnable, KeyListener {

	enum GameState {
		Running, Dead, Paused
	}

	GameState state = GameState.Paused;

	static Robot robot;
	public static int score = 0;
	private Font font = new Font(null, Font.BOLD, 30);

	private Image image, bird, background, upPipe, downPipe;


	private Graphics second;
	private URL base;
	private static Background bg1, bg2;
	private Animation anim, hanim;

	@Override
	public void init() {

		setSize(480, 800);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Flappy Bird");
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}

		// Image Setups
		bird = getImage(base, "data/flappybird.png");
		bird=bird.getScaledInstance(51, 36, Image.SCALE_SMOOTH);
		background = getImage(base, "data/background.jpg");
		background = background.getScaledInstance(480,800,Image.SCALE_SMOOTH);
		anim = new Animation();
		anim.addFrame(bird, 1250);

	}

	@Override
	public void start() {
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		robot = new Robot();

		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		if (state == GameState.Running||state==GameState.Paused) {

			while (true) {
				
				bg1.update();
				if (state == GameState.Running)
					robot.update();
				animate();
				repaint();
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
	}

	public void animate() {
		anim.update(10);
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);

	}

	@Override
	public void paint(Graphics g) {

		if (state == GameState.Running||state==GameState.Paused) {

			g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
			g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);


			g.drawImage(bird, robot.getCenterX() - 61,
					robot.getCenterY() - 63, this);

			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(score), 400, 30);

		} else if (state == GameState.Dead) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 480);
			g.setColor(Color.WHITE);
			g.drawString("Dead", 360, 240);

		}
	}


	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
			
		case KeyEvent.VK_SPACE:
			if (state == GameState.Running) {
				robot.jump();
				break;
			}else if (state == GameState.Paused){
				state=GameState.Running;
				robot.jump();
				break;
			}else if (state == GameState.Dead){
				state=GameState.Paused;
				reset();
			}
			

		}

	}
	public void reset(){
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {


		case KeyEvent.VK_SPACE:
			break;


		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static Background getBg1() {
		return bg1;
	}

	public static Background getBg2() {
		return bg2;
	}

	public static Robot getRobot() {
		return robot;
	}

}