package game.patterns;

import game.Hazard;
import game.instructions.ForwardInstruction;
import game.instructions.Instruction;
import game.instructions.RadialSpreadInstruction;
import misc.GenGet;

public final class TestEnemyPattern extends Pattern{

	public static TestEnemyPattern instance;
	
	Instruction inst;
	Instruction radialPat;
	
	double lastShot = 0;
	double cooldown = 0.4;
	
	private TestEnemyPattern() {
		setup();
		inst = ForwardInstruction.getInstance();
		radialPat = RadialSpreadInstruction.getInstance();
	}

	public static TestEnemyPattern getInstance() {
		if (instance == null) {
			instance = new TestEnemyPattern();
		}
		return instance;
	}
	
	
	@Override
	public void cast(Hazard entity, double startTime, double currentTime, double delta) {
		double time = currentTime - startTime;
		int t = new GenGet<Integer>(Integer.class).get(readArgument("T"), 0);
		int wait = new GenGet<Integer>(Integer.class).get(readArgument("Sleep"), 1);
		if (t%wait == wait - 1) {
			radialPat.setArgument("Amount", readArgument("Amount"));
			radialPat.setArgument("Spread", readArgument("Spread"));
			radialPat.setArgument("Direction", readArgument("Direction"));
			radialPat.setArgument("Transform", readArgument("Transform"));
			radialPat.setArgument("Speed", readArgument("Speed"));
			radialPat.setArgument("InstParams", readArgument("InstParams"));
			radialPat.invoke(entity, delta);
		}
		inst.invoke(entity, delta);
	}

}
