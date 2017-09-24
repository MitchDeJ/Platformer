package framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import objects.Player;
import window.Handler;

public class KeyInput extends KeyAdapter {
	
	Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i=0; i < handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.PLAYER) {
				
				if (key == KeyEvent.VK_D) {
					tempObject.setVelX(5);
					((Player) tempObject).setDirection(1);
				}
				if (key == KeyEvent.VK_A) {
					tempObject.setVelX(-5);
					((Player) tempObject).setDirection(0);
				}
				if (key == KeyEvent.VK_W && !tempObject.isJumping()) {
					if (tempObject.isFalling()) {
					tempObject.setJumping(true);
					((Player) tempObject).setFlip(true);
					}
					tempObject.setVelY(-8);
					
				}
				
			}
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i=0; i < handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.PLAYER) {
				
				 if(key == KeyEvent.VK_D && tempObject.getVelX() > 0)
				    tempObject.setVelX(0);
				   if(key == KeyEvent.VK_A && tempObject.getVelX() < 0)
				    tempObject.setVelX(0);
			}
			
		}
	}

}
