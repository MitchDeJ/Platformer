package window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {
	
	private int speed;
	private int frames;
	
	private int index = 0;
	private int count = 0;
	
	private BufferedImage[] images;
	private BufferedImage currentImg;
	
	
	public Animation(int speed, BufferedImage[] images) {
		this.speed = speed;
		this.images = images;
		this.frames = images.length;
		this.currentImg = images[0];
	}
	
	public void runAnimation() {
		index++;
		if (index > speed) {
			index = 0;
			nextFrame();
		}
	}
	
	private void nextFrame() {
		if (count < frames) {
			currentImg = images[count];
			count++;
		} else {
			count = 0;
		}
	}
	
	public int getCurrentFrame() {
		return count;
	}
	
	public void setCurrentFrame(int i) {
		this.count = i;
	}
	
	public void drawAnimation(Graphics g, int x, int y) {
		g.drawImage(currentImg, x, y, null);
	}
	public void drawFlippedAnimation(Graphics g, int x, int y) {
		g.drawImage(currentImg, x+currentImg.getWidth(), y,-currentImg.getWidth(), currentImg.getHeight(), null);
	}
	
}
