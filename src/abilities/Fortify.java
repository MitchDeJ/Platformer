package abilities;

public class Fortify extends Ability {

	public Fortify() {
		name = "Fortify";
		icon = sprites.GUI_ICON_FORTIFY;
	}

	@Override
	public String getDesc() {
		return "Reduce damage taken for a short period of time.";
	}

	@Override
	public boolean isPassive() {
		return false;
	}

}
