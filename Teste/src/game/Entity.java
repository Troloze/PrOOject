package game;

import engine.SpriteHolder;
import misc.Transform;

public abstract class Entity implements SpriteHolder {
	
	public Transform transform;
	protected boolean destroyed;
	
	public boolean isDestroyed() {
		return destroyed;
	}
		
	public abstract void update(double delta);
	
	/**
	 * REMOVER TODAS AS REFERENCIAS AO OBJETO PARA PERMITIR O COLETOR DE LIXO REALIZAR A COLETA DO OBJETO
	 */
	public abstract void destroy();
}
