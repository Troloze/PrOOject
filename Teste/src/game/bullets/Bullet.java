package game.bullets;

import engine.SpriteHolder;
import game.Hazard;
import game.patterns.Pattern;

public abstract class Bullet extends Hazard implements SpriteHolder {

	Bullet() {
		super();
	}
	
	Bullet(Pattern pat) {
		super(pat);
	}
	
}
