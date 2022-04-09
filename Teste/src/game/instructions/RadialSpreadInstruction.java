package game.instructions;

import engine.ImageBufferHandler;
import game.EntityInstancer;
import game.Game;
import game.Hazard;
import misc.GenGet;
import misc.InstanceParams;
import misc.Misc;
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
		int amount;
		double spread, direction, thisDir, invAmount, speed, dirOff;
		boolean follow, random;
		Transform offset;
		InstanceParams par;
		
		follow = new GenGet<Boolean>(Boolean.class).get(readArgument("FollowPlayer"), false);
		random = new GenGet<Boolean>(Boolean.class).get(readArgument("RandomDir"), false);
		par = new GenGet<InstanceParams>(InstanceParams.class).get(readArgument("InstParams"), null);	
		amount = new GenGet<Integer>(Integer.class).get(readArgument("Amount"), 0);	
		spread = new GenGet<Double>(Double.class).get(readArgument("Spread"), 0.0);
		dirOff = new GenGet<Double>(Double.class).get(readArgument("RandomRate"), 0.0);
		if (!follow && !random) direction = new GenGet<Double>(Double.class).get(readArgument("Direction"), 0.0);	
		else if (follow) direction = Misc.Other.getAngleBetween(entity.getTransform().getPosition(), Game.getInstance().getPlayerPosition());
		else direction = 360 + (Math.random() - 1/2) * dirOff;
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
		if (amount == 0) {
			resetArguments();
			return;
		}
		
		for (int i = 0; i < amount; i++) {
			if (amount != 1) invAmount = 1.0/(amount - 1);
			else invAmount = 1.0;
			thisDir = (i * spread * invAmount) + (direction - ((amount != 1) ? spread * 0.5 : 0));
			if (par == null) {
				par = new InstanceParams();
				par.transform = offset;
				par.direction = 360 + thisDir;
				par.speed = speed;
				par.spriteData.type = ImageBufferHandler.ARROW;
				par.spriteData.color = ImageBufferHandler.B_WHITE;
			} else {
				par.direction = 360 + thisDir;
				par.transform.getPosition().setLocation(offset.getPosition());;
				par.transform.setZPosition(10.01);
			}
			EntityInstancer.instance(EntityInstancer.ENT_GENERIC_BULLET, par);
		}
		resetArguments();
	}

}
