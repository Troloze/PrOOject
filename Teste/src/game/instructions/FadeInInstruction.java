package game.instructions;

import game.Hazard;
import misc.GenGet;
import misc.Misc;

public final class FadeInInstruction extends Instruction {

	public static FadeInInstruction instance;
	
	private FadeInInstruction() {
		setup();
	}
	
	public static FadeInInstruction getInstance() {
		if (instance == null) {
			instance = new FadeInInstruction();
		}
		return instance;
	}
	
	@Override
	public void invoke(Hazard entity, double startTime, double currentTime, double delta) {
		Object arg;
		double scale, start;
		
		
		arg = readArgument("Start");
		start = new GenGet<Double>(Double.class).get(arg, 1.0);
				
		arg = readArgument("Scale");
		scale = new GenGet<Double>(Double.class).get(arg, 1.0);
		
		if (entity == null) return;
		if (currentTime - startTime <= 0.2) {
			entity.setAlpha(Misc.Transform.smoothStop2((currentTime - startTime) * 5));
			entity.getTransform().setScale((scale + start *  Misc.Transform.smoothStart3(1 - (currentTime - startTime) * 5)));
		}
		else {
			entity.setAlpha(1.0f);
			entity.getTransform().setScale(scale);
		}
		resetArguments();
	}

}
