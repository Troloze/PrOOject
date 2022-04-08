package game.instructions;

import java.awt.geom.Point2D;

import engine.ImageBufferHandler;
import game.EntityInstancer;
import game.Hazard;
import misc.GenGet;
import misc.InstanceParams;
import misc.Transform;

public final class RadialSpreadInstruction extends Instruction {

	private static RadialSpreadInstruction instance;
	
	private RadialSpreadInstruction() {
		setup();
	}
	
	public static RadialSpreadInstruction getInstance() {
		if (instance == null) {
			instance = new RadialSpreadInstruction();
		}
		return instance;
	}
	
	@Override
	public void invoke(Hazard entity, double startTime, double currentTime, double delta) {
		Object arg;
		int amount;
		double spread, direction, thisDir, invAmount, speed;
		Transform offset;
		InstanceParams par;
			
		amount = new GenGet<Integer>(Integer.class).get(readArgument("Amount"), 0);	
		spread = new GenGet<Double>(Double.class).get(readArgument("Spread"), 0.0);
		direction = new GenGet<Double>(Double.class).get(readArgument("Direction"), 0.0);	
		speed = new GenGet<Double>(Double.class).get(readArgument("Speed"), 10.0);
		offset = new GenGet<Transform>(Transform.class).get(readArgument("Direction"), new Transform());
		
		offset.getPosition().setLocation(
				entity.getTransform().getPosition().getX() + offset.getPosition().getX(),
				entity.getTransform().getPosition().getY() + offset.getPosition().getY()
				);
		
		offset.setZPosition(entity.getTransform().getZPosition() + offset.getZPosition() + 0.01);
		
		if (spread < 0) spread = 0.0;
		if (spread > 360) spread = 360.0;
		while (direction < 360) direction += 360.0;
		while (direction >= 720) direction -= 360.0;
		
		for (int i = 0; i < amount; i++) {
			invAmount = 1.0/amount;
			thisDir = (i * spread * invAmount) + (direction - spread * 0.5);
			par = new InstanceParams();
			par.transform = offset;
			par.direction = 360 + thisDir;
			par.speed = speed;
			par.spriteData.type = ImageBufferHandler.ARROW;
			par.spriteData.color = ImageBufferHandler.B_RED;
			EntityInstancer.instance(EntityInstancer.ENT_GENERIC_BULLET, par);
		}
		
	}

}
