package abilities;

public class Dash extends Ability {

	public Dash() {
		name = "Dash";
		icon = sprites.GUI_ICON_DASH;
	}

	@Override
	public String getDesc() {
		return "Dash over a short distance, dealing damage to every opponent in your way.";
	}

	@Override
	public boolean isPassive() {
		return false;
	}

}
