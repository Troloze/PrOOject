package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import engine.InputHandler;
import misc.Transform;

public final class Game {
	private static Game instance;
	
	private Point2D playerPos;
	
	private GameStateHandler stateHandler;
	private InputHandler input;
	
	private static double score = 0;
	private boolean delet = false;
		
	private static List<Entity> entities;
	private static List<Entity> waitEntities;
	private static List<Collisionable> colDetect;
	private static List<Point> colPairs;
	private static List<Entity> destroyQueue;
		
	private static Entity background;
	
	private Game() {
		input = InputHandler.getInstance();
		stateHandler = GameStateHandler.getInstance();
		
		entities = new ArrayList<>();
		destroyQueue = new ArrayList<>();
		colDetect = new ArrayList<>();
		colPairs = new ArrayList<>();
		waitEntities = new ArrayList<>();
		
		background = new Background();
		entities.add(background);
		
		playerPos = new Point2D.Double();
	}
	
	public void setPPos(Point2D pos) {
		playerPos.setLocation(pos);
	}
	
	public Point2D getPlayerPosition() {
		return playerPos;
	}
	
	public static Game getInstance() {
		if(instance == null) {
			instance = new Game();
		}
		
		return instance;
	}
	
	public void startGame() {
		score = 0;
		RankInfo.getInstance().setScore(0);
		EntityInstancer.instance(EntityInstancer.ENT_PLAYER, null);
	}
	
	public void destroyAll() {
		delet = true;
		for(Entity ent : entities) {
			if (background == ent) continue;
			ent.destroy();
		}
	}
	
	public void gameUpdate(double delta) {
		stateHandler.updateState();
		if (delet) {
			delet = false;
			//destroy();
			updateEntities(delta);
		}
		if(GameStateHandler.getState() == GameStateHandler.STATE_PLAYING || GameStateHandler.getState() == GameStateHandler.STATE_LOSE) {
			if(input.getInput(InputHandler.KEY_PAUSE) == 0) GameStateHandler.setState(GameStateHandler.STATE_PAUSED);
			score += (5 * delta);
			RankInfo.getInstance().setScore((int) (score) * 100);
			updateEntities(delta);
			updateCollision();
			updateWave(delta);
			destroy();
			addNewEntities();
		} 
		else if (GameStateHandler.getState() != GameStateHandler.STATE_PAUSED) {
			background.update(delta); 
			return;
		}
	}

	public void updateWave(double delta) {
		Wave.getInstance().update(delta);
	}
	
	public void updateEntities(double delta) {
		Collider col;
		colDetect.clear();
		for (Entity ent : entities) {
			if (ent.isDestroyed()) {
				addToDestroy(ent);
				continue;
			}
			
			ent.update(delta);	

			if (ent instanceof Collisionable) {
				col = ((Collisionable) ent).getCollider();
				if (col != null)
					if (col.isTarget() || col.isHazard())
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
				if(Math.abs(currentTrans.getZPosition() - testTrans.getZPosition()) > 2) continue;
				if (currentTrans.getPosition().getX() + current.getCollider().getHitbox() > testTrans.getPosition().getX()) {
					if ((current.getCollider().getHitFlags() & test.getCollider().getDamageFlags()) != 0 || (current.getCollider().getDamageFlags() & test.getCollider().getHitFlags()) != 0) 
						colPairs.add(new Point(i, j));
				}
				else break;
			}
		}
		
		for (Point pt : colPairs) {
			current = colDetect.get(pt.x);
			test = colDetect.get(pt.y);
			distT = current.getTransform().getPosition().distanceSq(test.getTransform().getPosition());
			
			if ((current.getCollider().getHitFlags() & test.getCollider().getDamageFlags()) != 0) {
				boxOne = (current.getCollider().getDamagebox() * current.getTransform().getScale());
				boxTwo = (test.getCollider().getHitbox() * test.getTransform().getScale());
				sumBoxes = boxOne * boxOne + boxTwo * boxTwo;
				
				if (sumBoxes >= distT) {
					test.onCollision(current);
				}
			}
			
			if ((current.getCollider().getDamageFlags() & test.getCollider().getHitFlags()) != 0) {
				boxOne = (current.getCollider().getHitbox() * current.getTransform().getScale());
				boxTwo = (test.getCollider().getDamagebox() * test.getTransform().getScale());
				sumBoxes = boxOne * boxOne + boxTwo * boxTwo;
				
				if (sumBoxes >= distT) {
					current.onCollision(test);
				}
			}
		}
	}
	
	public void addEntity(Entity ent) {
		if (ent == null) return;
		waitEntities.add(ent);
	}
	
	public void addNewEntities() {
		for(Entity i : waitEntities) {
			entities.add(i);
		}
		waitEntities.clear();
	}
	
	public void addToDestroy(Entity ent) {
		destroyQueue.add(ent);
	}
	
	public void destroy() {
		int size = destroyQueue.size();
		
		for (int i = 0; i < size; i++) {
			Entity ent = destroyQueue.get(i);
			if (ent == null) continue;
			entities.remove(ent);
		}
		destroyQueue.clear();
	}
	
	public static double getScore() {
		return score;
	}
	
	public static void addScore(double i) {
		score += i;
	}
	
	public void debugDraw(Graphics2D g2) {
		for (Entity ent : entities) {
			if (ent.isDestroyed()) {
				addToDestroy(ent);
				continue;
			}
			g2.setColor(Color.red);
			g2.drawOval((int) ent.getTransform().getPosition().getX() + 640, (int) ent.getTransform().getPosition().getY() + 480, 25, 25);
		}
	}
	
	private class Comp implements Comparator<Collisionable> {

		@Override
		public int compare(Collisionable arg0, Collisionable arg1) {
			if (arg0 == null || arg1 == null) return 0;
			double x0 = Math.round(arg0.getTransform().getPosition().getX());
			double x1 = Math.round(arg1.getTransform().getPosition().getX());

			return (x0 > x1) ? 1 : ((x1 > x0) ? -1 : 0);
		}
	}
}
