package abilities;

import java.awt.image.BufferedImage;

import framework.SpriteLoader;
import window.Game;

public abstract class Ability {

	protected String name;
	protected BufferedImage icon;
	protected SpriteLoader sprites = Game.getSprites();
	public abstract String getDesc();
	public abstract boolean isPassive();
	
	public String getName() {
		return name;
	}
	
	public BufferedImage getIcon() {
		return icon;
	}
}
