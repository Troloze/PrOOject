package game.bullets;

import engine.SpriteHolder;
import game.Hazard;
import game.Pattern;

public abstract class Bullet extends Hazard implements SpriteHolder {

	Bullet() {
		super();
	}
	
	Bullet(Pattern pat) {
		super(pat);
	}
	
}
