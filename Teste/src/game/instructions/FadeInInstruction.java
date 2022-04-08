package game.instructions;

import game.Hazard;
import game.Instruction;
import misc.Misc;

public final class FadeInInstruction extends Instruction {

	public static FadeInInstruction instance;
	
	public static FadeInInstruction getInstance() {
		if (instance == null) {
			instance = new FadeInInstruction();
		}
		return instance;
	}
	
	@Override
	public void invoke(Hazard entity, double startTime, double currentTime, double delta) {
		if (entity == null) return;
		if (currentTime - startTime <= 0.2) {
			entity.setAlpha((currentTime - startTime) * 5);
			entity.getTransform().setScale((1 + 1.5 * Misc.Transform.smoothStart3(1 - (currentTime - startTime) * 5)));
		}
		else {
			entity.setAlpha(1.0f);
			entity.getTransform().setScale(1);
		}
			
	}

}
