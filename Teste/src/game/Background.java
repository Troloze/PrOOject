package game;

import engine.ImageBufferHandler;
import engine.SpriteHolder;
import misc.Transform;

public class Background extends Entity implements SpriteHolder {

	
	private static final int size = 27;
	private Sprite[][] tiles;
	
	public Background() {
		transform = new Transform();
		transform.defaultScale = null;
		tiles = new Sprite[size * 2][100];
		Transform trans = new Transform();
		trans.scale = 1.01;
		trans.defaultScale.setLocation(50, 43);
		trans.zPosition = 5;
		for (int i = -size; i < size; i++) {
			for (int j = -50; j < 50; j++) {
				trans.position.setLocation(i * 25, (j * 43) + ((i%2 == 0 ^ j%2 == 0) ? 0 : -43.0/3.0));
				trans.rotation = (i%2 == 0 ^ j%2 == 0) ? 0 : 180;
				tiles[size + i][50 + j] = new Sprite(this);
				tiles[size + i][50 + j].setOffset(trans);
				tiles[size + i][50 + j].set(ImageBufferHandler.TRIANGLE, (i%2 == 0 ^ j%2 == 0) ? ImageBufferHandler.T_GRAY1 : ImageBufferHandler.T_GRAY2);
			}
		}
	}
	
	@Override
	public Transform getTransform() {
		// TODO Auto-generated method stub
		return transform;
	}

	@Override
	public void update(double delta) {
		transform.position.setLocation(transform.position.getX(), transform.position.getY() + 100 * delta);
		//transform.rotation += 10 * delta;

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
