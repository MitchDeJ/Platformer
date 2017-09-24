package abilities;

import framework.SpriteLoader;
import window.Game;

public class AbilitySelection {
	
	private Ability[] abilities = new Ability[10];
	
	SpriteLoader sprites = Game.getSprites();
	
	public AbilitySelection() {
			abilities[0] = new Arc();
			abilities[1] = new Bombardment();
			abilities[2] = new Dash();
			abilities[3] = new Fortify();
			abilities[4] = new RapidFire();
			abilities[5] = new Arc();
			abilities[6] = new Bombardment();
			abilities[7] = new Dash();
			abilities[8] = new Fortify();
			abilities[9] = new RapidFire();
	}
	
	public Ability[] getAbilities() {
		return abilities;
	}
}
