package game;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import engine.ImageBufferHandler;
import engine.InputHandler;
import misc.Transform;

public class TestEntity extends Entity{

	private Sprite sprt;
	
	private TestEntity(double rotation, Point2D position, Point2D scale) {
		
		this.transform = new Transform(position, new Point2D.Double(50, 50), 1, rotation, 10.0);
		
		this.sprt = new Sprite(this);
		this.sprt.set(ImageBufferHandler.TRIANGLE, ImageBufferHandler.T_BLUE1);
		this.sprt.setAlpha(0.1f + (float) Math.random() * 0.8f);
	}
	
	public static TestEntity newInstance(double rotation, Point2D position, Point2D scale) {
		return new TestEntity(rotation, position, scale);
	}
	
	@Override
	public Transform getTransform() {
		return transform;
	}

	@Override
	public void update(double delta) {
		transform.zPosition = 0;
		if (InputHandler.getInstance().getInput(InputHandler.KEY_BOMB) == 1) {
			destroy();
		}
	}

	@Override
	public void destroy() {
		if (!sprt.isDestroyed()) sprt.destroy();
		sprt = null;
		this.destroyed = true;
	}
}
