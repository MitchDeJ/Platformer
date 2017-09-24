package window;

import framework.GameObject;

public class Camera {
	
	private float x, y;
	
	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

	public void tick(GameObject follow) {
		//TODO Hardcoded numbers to center player, change up if following something else
		float xTarget = -follow.getX()-50 + Game.WIDTH/2;
		float yTarget = -follow.getY() + Game.HEIGHT/2;
		x+= (xTarget-x)*0.1F;
		y+= (yTarget-y)*0.1F;
	}

}
