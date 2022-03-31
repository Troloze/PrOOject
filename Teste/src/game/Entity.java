package game;

import java.awt.geom.Point2D;

public abstract class Entity {
	public Point2D position;
	public double zPosition;
	
	
	public abstract Entity newInstance();
	
	public abstract void update();
	
	/**
	 * REMOVER TODAS AS REFERENCIAS AO OBJETO PARA PERMITIR O COLETOR DE LIXO REALIZAR A COLETA DO OBJETO
	 */
	public abstract void destroy();
}
