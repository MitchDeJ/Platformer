package abilities;

public class Arc extends Ability {

	public Arc() {
		name = "Arc";
		icon = sprites.GUI_ICON_ARC;
	}

	@Override
	public String getDesc() {
		return "Fire an arc of projectiles.";
	}

	@Override
	public boolean isPassive() {
		return false;
	}

}
