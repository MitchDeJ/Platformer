package abilities;

public class RapidFire extends Ability {

	public RapidFire() {
		name = "Rapid Fire";
		icon = sprites.GUI_ICON_RAPIDFIRE;
	}

	@Override
	public String getDesc() {
		return "Fire 3 shots in a quick succession.";
	}

	@Override
	public boolean isPassive() {
		return false;
	}

}
