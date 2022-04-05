package game;

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
	private List<Hazard> colDetect;
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
		}
		
		if (InputHandler.getInstance().getInput(InputHandler.KEY_FOCUS) == 0) {
			for (int i = 0; i < 2; i++) {
				iP.rotation = 0;
				iP.position = new Point2D.Double(-300 + Math.random() * 600, -200 + Math.random() * 400);
				iP.scale = new Point2D.Double(50 , 43);

				eI.instance(EntityInstancer.ENT_TEST, iP);
			}
		}
		
		updateEntities(delta);
		updateCollision();
		
		
	}

	public void updateEntities(double delta) {
		colDetect.clear();
		for (Entity ent : entities) {
			if (ent.isDestroyed()) {
				addToDestroy(ent);
				continue;
			}
			
			ent.update(delta);	
			
			if (ent instanceof Hazard) 
				if (((Hazard) ent).getCollider().isTarget() || ((Hazard) ent).getCollider().isHazard())
					colDetect.add((Hazard) ent);
		}
	}
	
	public void updateCollision() {
		int j;
		colPairs.clear();
		Hazard current, test;
		Transform currentTrans, testTrans;
		double distT, sumBoxes, boxOne, boxTwo;
		for (int i = 0; i < colDetect.size(); i++) {
			j = i;
			current = colDetect.get(i);
			currentTrans = current.getTransform();
			while (true) {
				j++;
				test = colDetect.get(j);
				testTrans = test.getTransform();
				
				if (currentTrans.position.getX() + 2 * current.getCollider().getHitbox() > testTrans.position.getX()) 
					if ((current.getCollider().getHazardFlags() & test.getCollider().getTargetFlags()) != 0 || (current.getCollider().getTargetFlags() & test.getCollider().getHazardFlags()) != 0) 
						colPairs.add(new Point(i, j));
				else 
					break;
				
			}
		}
		
		for (Point pt : colPairs) {
			current = colDetect.get(pt.x);
			test = colDetect.get(pt.y);
			distT = current.getTransform().position.distanceSq(test.getTransform().position);
			
			if ((current.getCollider().getHazardFlags() & test.getCollider().getTargetFlags()) != 0) {
				boxOne = (current.getCollider().getDamagebox() * current.getTransform().scale);
				boxTwo = (test.getCollider().getHitbox() * test.getTransform().scale);
				sumBoxes = boxOne * boxOne + boxTwo * boxTwo;
				
				if (sumBoxes >= distT) {
					current.onCollision(test);
				}
			}
			
			if ((current.getCollider().getTargetFlags() & test.getCollider().getHazardFlags()) != 0) {
				boxOne = (current.getCollider().getHitbox() * current.getTransform().scale);
				boxTwo = (test.getCollider().getDamagebox() * test.getTransform().scale);
				sumBoxes = boxOne * boxOne + boxTwo * boxTwo;
				
				if (sumBoxes >= distT) {
					test.onCollision(current);
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
	
	private class Comp implements Comparator<Hazard> {

		@Override
		public int compare(Hazard arg0, Hazard arg1) {
			double x0 = arg0.getTransform().position.getX();
			double x1 = arg1.getTransform().position.getX();

			return (x0 > x1) ? 1 : ((x1 > x0) ? -1 : 0);
		}

		
		
	}
}
