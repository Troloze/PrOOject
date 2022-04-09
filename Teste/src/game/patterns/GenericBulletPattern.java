package game.patterns;

import game.Hazard;
import game.instructions.FadeInInstruction;
import game.instructions.ForwardInstruction;
import game.instructions.Instruction;
import misc.GenGet;

public class GenericBulletPattern extends Pattern {
	
	private static GenericBulletPattern instance;
	private Instruction forward;
	private Instruction fadeIn;
	
	private GenericBulletPattern() {
		setup();
		forward = ForwardInstruction.getInstance();
		fadeIn = FadeInInstruction.getInstance();
	}
	
	public static GenericBulletPattern getInstance() {
		if (instance == null) {
			instance = new GenericBulletPattern();
		}
		return instance;
	}
	
	@Override
	public void cast(Hazard entity, double startTime, double currentTime, double delta) {
		double scale, startS;
		forward.invoke(entity, delta);
		scale = new GenGet<Double>(Double.class).get(readArgument("Scale"), 1.0);
		fadeIn.setArgument("Scale", scale);
		startS = new GenGet<Double>(Double.class).get(readArgument("StartSize"), 1.0);
		fadeIn.setArgument("Start", startS);
		fadeIn.invoke(entity, startTime, currentTime, delta);
		resetArguments();
	}
}
