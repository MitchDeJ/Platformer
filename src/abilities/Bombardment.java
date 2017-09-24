package abilities;

public class Bombardment extends Ability {

	public Bombardment() {
		name = "Bombardment";
		icon = sprites.GUI_ICON_BOMBARDMENT;
	}

	@Override
	public String getDesc() {
		return "Bombard the area in front of you with arrows.";
	}

	@Override
	public boolean isPassive() {
		return false;
	}

}
