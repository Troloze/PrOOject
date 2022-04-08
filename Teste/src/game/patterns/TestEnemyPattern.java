package game.patterns;

import game.Hazard;
import game.instructions.ForwardInstruction;
import game.instructions.Instruction;
import game.instructions.RadialSpreadInstruction;

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

		radialPat.setArgument("Amount", 60);
		radialPat.setArgument("Spread", 180.0);
		radialPat.setArgument("Direction", time * 180);
		radialPat.setArgument("Transform", null);
		radialPat.setArgument("Speed", time * 5 + 10);
		radialPat.invoke(entity, delta);
		
		inst.invoke(entity, delta);
	}

}
