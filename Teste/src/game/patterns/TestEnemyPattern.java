package game.patterns;

import game.Hazard;
import game.Instruction;
import game.Pattern;
import game.instructions.ForwardInstruction;

public final class TestEnemyPattern extends Pattern{

	public static TestEnemyPattern instance;
	
	Instruction inst;
	
	private TestEnemyPattern() {
		inst = ForwardInstruction.getInstance();
	}

	public static TestEnemyPattern getInstance() {
		if (instance == null) {
			instance = new TestEnemyPattern();
		}
		return instance;
	}
	
	
	@Override
	public void cast(Hazard entity, double startTime, double currentTime, double delta) {
		inst.invoke(entity, delta);
	}

}
