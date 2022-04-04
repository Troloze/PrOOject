package game;

import java.awt.geom.Point2D;

import engine.ImageBufferHandler;
import misc.Misc;

public class TestEntity extends Entity{

	private double rotation;
	private Point2D position;
	private Point2D scale;
	private Sprite sprt;
	private double i;
	
	private TestEntity(double rotation, Point2D position, Point2D scale) {
		this.rotation = rotation;
		this.position = new Point2D.Double();
		this.position.setLocation(position);
		this.scale = new Point2D.Double();
		this.scale.setLocation(scale);
		this.sprt = new Sprite(this);
		this.sprt.set(ImageBufferHandler.TRIANGLE, ImageBufferHandler.T_BLUE1);
		this.zPosition = 10.0;
		this.sprt.setAlpha(0.1f + (float) Math.random() * 0.8f);
		this.i = Math.random() * 2 * Math.PI;
	}
	
	public static TestEntity newInstance(double rotation, Point2D position, Point2D scale) {
		return new TestEntity(rotation, position, scale);
	}
	
	@Override
	public Point2D getScale() {
		return scale;
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public double getZPos() {
		return zPosition;
	}
	
	@Override
	public double getRotation() {
		return rotation;
	}

	@Override
	public void update(double delta) {
		zPosition = 0;
	}

	@Override
	public void destroy() {}
}
