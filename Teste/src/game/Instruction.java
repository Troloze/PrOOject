package game;

public abstract class Instruction {
	
	boolean stopOthers;
	boolean isLoop;
	double loopsAt;
	
	public void invoke() {
		invoke(null, 0.0, 0.0, 0.0);
	}
	
	public void invoke (Hazard entity) {
		invoke(entity, 0.0, 0.0, 0.0);
	}
	
	public void invoke(Hazard entity, double delta) {
		invoke(entity, 0.0, 0.0, delta);
	}

	public void invoke(double startTime, double currentTime, double delta) {
		invoke(null, startTime, currentTime, delta);
	}

	public abstract void invoke(Hazard entity, double startTime, double currentTime, double delta);
}
