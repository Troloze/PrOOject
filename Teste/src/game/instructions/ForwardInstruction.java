package game.instructions;

import java.awt.geom.Point2D;

import game.Hazard;

public final class ForwardInstruction extends Instruction {

	private static ForwardInstruction instance;
	
	private ForwardInstruction() {
		setup();
	}
	
	public static ForwardInstruction getInstance() {
		if (instance == null) {
			instance = new ForwardInstruction();
		}
		return instance;
	}
	
	@Override
	public void invoke(Hazard entity, double startTime, double currentTime, double delta) {
		if (entity == null) return;
		Point2D dirRate = entity.getDirectionRate();

		entity.getTransform().getPosition().setLocation(
				entity.getTransform().getPosition().getX() + dirRate.getX() * entity.getSpeed() * delta, 
				entity.getTransform().getPosition().getY() +dirRate.getY() * entity.getSpeed() * delta);

	}

}
