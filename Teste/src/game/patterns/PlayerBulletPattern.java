package game.patterns;

import game.Hazard;
import game.Instruction;
import game.Pattern;
import game.instructions.FadeInInstruction;
import game.instructions.ForwardInstruction;

public final class PlayerBulletPattern extends Pattern {

	private static PlayerBulletPattern instance;
	private Instruction forward;
	private Instruction fadeIn;
	
	private PlayerBulletPattern() {
		forward = ForwardInstruction.getInstance();
		fadeIn = FadeInInstruction.getInstance();
	}
	
	public static PlayerBulletPattern getInstance() {
		if (instance == null) {
			instance = new PlayerBulletPattern();
		}
		return instance;
	}
	
	
	@Override
	public void cast(Hazard entity, double startTime, double currentTime, double delta) {
		forward.invoke(entity, delta);
		fadeIn.invoke(entity, startTime, currentTime, delta);
	}
}
