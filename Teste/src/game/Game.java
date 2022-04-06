package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import engine.InputHandler;
import misc.InstanceParams;
import misc.Transform;

public final class Game {
	private static Game instance;
	private static EntityInstancer eI;
	private List<Entity> entities;
	private List<Collisionable> colDetect;
	private List<Point> colPairs;
	private List<Entity> destroyQueue;
		
	private boolean test = true;
	
	private Game() {
		entities = new ArrayList<>();
		destroyQueue = new ArrayList<>();
		colDetect = new ArrayList<>();
		colPairs = new ArrayList<>();
		//mainCharacter = MainCharacter.getInstance();
	}
	
	public static Game getInstance() {
		if(instance == null) {
			instance = new Game();
			eI = EntityInstancer.getInstance();
			
		}
		
		return instance;
	}
	
	public void gameUpdate(double delta) {
		InstanceParams iP = new InstanceParams();
		if (test) {
			eI.instance(EntityInstancer.ENT_PLAYER, iP);
			test = false;
			entities.add(new Background());
		}
		
		updateEntities(delta);
		updateCollision();
		destroy();
		
	}

	public void updateEntities(double delta) {
		colDetect.clear();
		for (Entity ent : entities) {
			if (ent.isDestroyed()) {
				addToDestroy(ent);
				continue;
			}
			
			ent.update(delta);	
			
			if (ent instanceof Collisionable) {
				if (((Collisionable) ent).getCollider() != null)
					if (((Collisionable) ent).getCollider().isTarget() || ((Collisionable) ent).getCollider().isHazard())
						colDetect.add((Collisionable) ent);
		
			}
		}
	}
	
	public void updateCollision() {
		int j, size;
		colPairs.clear();
		Collisionable current, test;
		Transform currentTrans, testTrans;
		double distT, sumBoxes, boxOne, boxTwo;
		size = colDetect.size();
		colDetect.sort(new Comp());
		for (int i = 0; i < size; i++) {
			j = i;
			current = colDetect.get(i);
			currentTrans = current.getTransform();
			while (true) {
				j++;
				if (j >= size) break;
				test = colDetect.get(j);
				testTrans = test.getTransform();
				if(Math.abs(currentTrans.zPosition - testTrans.zPosition) > 2) continue;
				if (currentTrans.position.getX() + current.getCollider().getHitbox() > testTrans.position.getX()) {
					if ((current.getCollider().getHitFlags() & test.getCollider().getDamageFlags()) != 0 || (current.getCollider().getDamageFlags() & test.getCollider().getHitFlags()) != 0) 
						colPairs.add(new Point(i, j));
				}
				else break;
			}
		}
		
		for (Point pt : colPairs) {
			current = colDetect.get(pt.x);
			test = colDetect.get(pt.y);
			distT = current.getTransform().position.distanceSq(test.getTransform().position);
			
			if ((current.getCollider().getHitFlags() & test.getCollider().getDamageFlags()) != 0) {
				boxOne = (current.getCollider().getDamagebox() * current.getTransform().scale);
				boxTwo = (test.getCollider().getHitbox() * test.getTransform().scale);
				sumBoxes = boxOne * boxOne + boxTwo * boxTwo;
				
				if (sumBoxes >= distT) {
					test.onCollision(current);
				}
			}
			
			if ((current.getCollider().getDamageFlags() & test.getCollider().getHitFlags()) != 0) {
				boxOne = (current.getCollider().getHitbox() * current.getTransform().scale);
				boxTwo = (test.getCollider().getDamagebox() * test.getTransform().scale);
				sumBoxes = boxOne * boxOne + boxTwo * boxTwo;
				
				if (sumBoxes >= distT) {
					current.onCollision(test);
				}
			}
		}
	}
	
	public void addEntity(Entity ent) {
		if (ent == null) return;
		entities.add(ent);
	}
	
	public void addToDestroy(Entity ent) {
		destroyQueue.add(ent);
	}
	
	public void destroy() {
		for (Entity ent : destroyQueue) {
			if (ent == null) continue;
			entities.remove(ent);
		}
		destroyQueue.clear();
	}
	
	public void debugDraw(Graphics2D g2) {
		for (Entity ent : entities) {
			if (ent.isDestroyed()) {
				addToDestroy(ent);
				continue;
			}
			g2.setColor(Color.red);
			g2.drawOval((int) ent.getTransform().position.getX() + 640, (int) ent.getTransform().position.getY() + 480, 25, 25);
		}
	}
	
	private class Comp implements Comparator<Collisionable> {

		@Override
		public int compare(Collisionable arg0, Collisionable arg1) {
			double x0 = arg0.getTransform().position.getX();
			double x1 = arg1.getTransform().position.getX();

			return (x0 > x1) ? 1 : ((x1 > x0) ? -1 : 0);
		}

		
		
	}
}
