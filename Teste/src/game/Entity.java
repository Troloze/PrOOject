package game;

import java.awt.geom.Point2D;

import engine.SpriteHolder;

public abstract class Entity implements SpriteHolder {
	public Point2D position;
	public double zPosition;
	
		
	public abstract void update(double delta);
	
	/**
	 * REMOVER TODAS AS REFERENCIAS AO OBJETO PARA PERMITIR O COLETOR DE LIXO REALIZAR A COLETA DO OBJETO
	 */
	public abstract void destroy();
}
