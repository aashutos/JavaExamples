package com.ntak.examples.JuniferMaze.trees.impl;

import java.util.Map;
import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ntak.examples.JuniferMaze.enums.DirEnum;
import com.ntak.examples.JuniferMaze.trees.Treeable;

/**
 * Bean represents a specific traversal point on the grid. Position of this node is represented by X and Y co-ordinates.
 * 
 * @author akakshepati
 *
 */
public class RoutePoint extends Observable implements Treeable {

	private AtomicInteger degOfFreedom;
	private Map<DirEnum,RoutePoint> nextNodes;
	private final int posX;
	private final int posY;
	private static final Logger log = LogManager.getLogger(RoutePoint.class);
	
	public RoutePoint(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
		nextNodes = new ConcurrentHashMap<>();
		degOfFreedom = new AtomicInteger(DirEnum.values().length);
	}

	@Override
	public Map<DirEnum, RoutePoint> getNextNodes() {
		return nextNodes;
	}

	/**
	 * Thread-safe method for returning the number of moves available to this particular node (via Atomic Integers). 
	 * 
	 * @see com.ntak.examples.JuniferMaze.trees.Treeable
	 */
	@Override
	public int getDegOfFreedom() {
		return degOfFreedom.get();
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	
	public void setNextNodeForDir(DirEnum dir, RoutePoint dirPoint) {
		if (nextNodes.containsKey(dir)) {
			log.warn("Routing Node is being replaced.");
		}
		nextNodes.put(dir, dirPoint);
	}

	public void decDegOfFreedom() {
		this.degOfFreedom.decrementAndGet();
		if (degOfFreedom.get() < 0)
			degOfFreedom.set(0);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + posX;
		result = prime * result + posY;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoutePoint other = (RoutePoint) obj;
		if (posX != other.posX)
			return false;
		if (posY != other.posY)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RoutePoint [posX=" + posX + ", posY="
				+ posY + "]";
	}
	
}
