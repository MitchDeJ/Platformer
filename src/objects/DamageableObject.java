package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;

public abstract class DamageableObject extends GameObject {

	public DamageableObject(float x, float y, String name, ObjectId id) {
		super(x, y, id);
		this.name = name;
	}
	
	public String name;
	public int maxHealth = 100;
	public int health = maxHealth;

	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	
	public void renderName(Graphics g, float x, float y) {
		g.drawString(name, (int)x, (int)y);
	}
	
	public void renderHealthBar(Graphics g, float x, float y, int width, int height) {
		g.setColor(Color.BLACK);
		g.drawRect((int)x-1, (int)y-1, width+1, height+1);
		g.setColor(Color.RED);	
		g.fillRect((int)x, (int)y, width, height);
		g.setColor(Color.GREEN);
		double greenMultiplier = ((double)health)/((double)maxHealth);
		g.fillRect((int)x, (int)y, (int) Math.round(greenMultiplier*width), height);
	}

	public abstract Rectangle getBounds();

}
