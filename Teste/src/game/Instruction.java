package game;

public abstract class Instruction {
	
	boolean stopOthers;
	boolean isLoop;
	double loopsAt;
	
	public void invoke() {
		invoke(null, 0.0, 0.0);
	}
	
	public void invoke(Hazard entity) {
		invoke(entity, 0.0, 0.0);
	}

	public void invoke(double startTime, double currentTime) {
		invoke(null, startTime, currentTime);
	}

	public abstract void invoke(Hazard entity, double startTime, double currentTime);
}
