package game;

import misc.WaveParams;

public final class Wave {

	private static Wave instance;
	
	private WaveParams currentWave;
	private int waveCount;
	private double start;
	private double timeCount;
	private double waveCooldown;
	
	private Wave() {}
	
	public static Wave getInstance() {
		if (instance == null) {
			instance = new Wave();
		}
		return instance;
	}
	
	public void update(double delta) {
		double current = System.nanoTime() / 1000000000.0;
		if (currentWave == null && waveCooldown < current) {
			currentWave = new WaveParams();
			start = current;
			waveCount = 0;
			timeCount = currentWave.tInterval + start;
		}
		if (currentWave != null && timeCount < current) {
			if (waveCount == currentWave.number) {
				currentWave = null;
				waveCooldown = current + 2;
				return;
			}
			timeCount += currentWave.tInterval;
			waveCount += 1;
			EntityInstancer.instance(EntityInstancer.ENT_ENEMY, currentWave.instPar);
		}
	}
	
	public void reset() {
		currentWave = null;
		waveCount = 0;
		timeCount = 0;
	}
}
