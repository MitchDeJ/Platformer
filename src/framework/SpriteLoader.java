package framework;


import java.awt.image.BufferedImage;
import window.BufferedImageLoader;

public class SpriteLoader {
	
	BufferedImageLoader loader = new BufferedImageLoader();
	
	// PLAYER SPRITES //
	public BufferedImage[] PLAYER_WALK = new BufferedImage[8];
	public BufferedImage[] PLAYER_STAND = new BufferedImage[4];
	public BufferedImage[] PLAYER_FLIP = new BufferedImage[6];
	public BufferedImage PLAYER_JUMPING;
	public BufferedImage PLAYER_LANDING;
	////////////////////
	
	// BACKGROUNDS //
	public BufferedImage BG_CLOUDS;
	////////////////
	
	// FLOORS //
	public BufferedImage FLOOR_GRASS0;
	///////////
	
	// GUI //
	public BufferedImage GUI_ABILITYBAR;
	
	//ABILITIES//
	public BufferedImage GUI_ICON_ARC;
	public BufferedImage GUI_ICON_DASH;
	public BufferedImage GUI_ICON_RAPIDFIRE;
	public BufferedImage GUI_ICON_BOMBARDMENT;
	public BufferedImage GUI_ICON_FORTIFY;
	/////////////
	
	/////////

	
	public SpriteLoader() {
		init();
	}
	
	public void init() {
		PLAYER_WALK = loadSpriteArray("player/walk", 8);
		PLAYER_STAND = loadSpriteArray("player/stand", 4);
		PLAYER_FLIP = loadSpriteArray("player/flip", 6);
		PLAYER_JUMPING = loader.loadImage("/sprites/player/jump/0.png");
		PLAYER_LANDING = loader.loadImage("/sprites/player/jump/1.png");
		
		BG_CLOUDS = loader.loadImage("/sprites/background/clouds.png");
		
		FLOOR_GRASS0 = loader.loadImage("/sprites/floor/grass0.png");
		
		GUI_ABILITYBAR = loader.loadImage("/sprites/gui/abilitybar.png");
		
		GUI_ICON_ARC = loader.loadImage("/sprites/gui/abilities/6.png");
		GUI_ICON_DASH = loader.loadImage("/sprites/gui/abilities/17.png");
		GUI_ICON_RAPIDFIRE = loader.loadImage("/sprites/gui/abilities/8.png");
		GUI_ICON_BOMBARDMENT = loader.loadImage("/sprites/gui/abilities/10.png");
		GUI_ICON_FORTIFY = loader.loadImage("/sprites/gui/abilities/12.png");
	}
	
	public BufferedImage[] loadSpriteArray(String path, int size) {
		BufferedImage[] image = new BufferedImage[size];
		for (int i=0;i<size;i++) {
			image[i] = loader.loadImage("/sprites/"+path+"/"+i+".png");
		}
		return image;
	}
	
	public BufferedImage[] flippedSpriteArray(BufferedImage[] sprite) {
		BufferedImage[] image = sprite;
		for(int i=0;i<sprite.length;i++) {
			    for (int w=0;w<image[i].getWidth();w++)
			        for (int h=0;h<image[i].getHeight()/2;h++)
			        {
			            int tmp = image[i].getRGB(w, h);
			            image[i].setRGB(w, h, image[i].getRGB(w, image[i].getHeight()-h-1));
			            image[i].setRGB(w, image[i].getHeight()-h-1, tmp);
			        }
		}
		return image;
	}

}
