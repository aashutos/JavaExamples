package com.ntak.examples.JuniferMaze.trees.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ntak.examples.JuniferMaze.application.GlobalValues;
import com.ntak.examples.JuniferMaze.application.GridState;
import com.ntak.examples.JuniferMaze.enums.DirEnum;
import com.ntak.examples.JuniferMaze.enums.StateEnum;
import com.ntak.examples.JuniferMaze.trees.Search;
import com.ntak.examples.JuniferMaze.utils.LogUncaughtExceptionHandler;

/**
 * Class contains logic attributed to the determination of the path of a particular RoutePoint node.
 * 
 * @author akakshepati
 * 
 * @see com.ntak.examples.JuniferMaze.trees.Treeable
 *
 */
public class TreeSearch extends Thread implements Observer,Search<RoutePoint> {

	private RoutePoint point;
	private GridState<RoutePoint> state;
	private static final Logger log = LogManager.getLogger(TreeSearch.class);
	
	public TreeSearch(RoutePoint point){
		super();
		this.point = point;
		synchronized(GlobalValues.globalState) {
			state = GlobalValues.globalState.get(point);
		}
		this.setUncaughtExceptionHandler(new LogUncaughtExceptionHandler());
		if (point != null) {
			this.setName("TreeSearch@(" + Optional.ofNullable(point.getPosX()) + "," + Optional.ofNullable(point.getPosY()) + ")");
			synchronized(GlobalValues.processingThreadMap) {
				GlobalValues.processingThreadMap.put(point, this);
			}
		}
	}
	
	@Override
	public void run() {
		log.debug("Thread: " + this.getName() + " started.");
		determinePath();
		log.debug("Thread: " + this.getName() + " ended.");
	}
	
	/**
	 * Determines the minimal path up to the given point
	 * 
	 */
	public void determinePath() {
		if (point == null) {
			log.info("Searching on unknown position.");
			return;
		}
		if (point.equals(GlobalValues.goal)) {
			log.info("Reached goal: " + GlobalValues.goal + ".");
			GlobalValues.isEnded.set(true);
			return;
		}
		
		if (blockedPoint()) {
			log.trace("No more moves available for position: " + point + ".");
			point.notifyObservers(StateEnum.ITS_OVER);
			return;
		}
		
		List<RoutePoint> newBreadcrumb = new LinkedList<>(state.getBreadcrumbs());
		newBreadcrumb.add(point);
		
		for (DirEnum dir : DirEnum.values()) {
			Optional<RoutePoint> nextNode = GlobalValues.immutableGrid.getNeighbouringToIndex(point.getPosX(), point.getPosY(), dir);
			if (!nextNode.isPresent()) { // Wall or off-grid position
				point.decDegOfFreedom();
				
				if (point.getDegOfFreedom() <= 0) {
					point.notifyObservers();
				}
			} else { // Valid position
				RoutePoint dirPoint = nextNode.get();
				point.setNextNodeForDir(dir, dirPoint);
				
				synchronized (GlobalValues.globalState) {
					GridState<RoutePoint> dirState = null;
					if ((dirState = GlobalValues.globalState.get(dirPoint)) == null) { // Node has not been reached yet
						GlobalValues.globalState.put(dirPoint, new GridState<RoutePoint>(state.getSteps()+1,newBreadcrumb));
						TreeSearch dirSearch = new TreeSearch(dirPoint);
						dirPoint.addObserver(this);
						dirSearch.start();
					} else {
						dirPoint.addObserver(this);
						if ((dirState.getSteps() > (state.getSteps() + 1)) || dirState.getSteps() < 0) { 
							GlobalValues.globalState.put(dirPoint, new GridState<RoutePoint>(state.getSteps()+1,newBreadcrumb));
							dirPoint.notifyObservers(StateEnum.I_HAVE_BETTER);
						} else {
							point.decDegOfFreedom();
						}
					}
				}
			}
		}

		while (point.getDegOfFreedom() > 0 || !GlobalValues.isEnded.get()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				log.debug("Thread: " + this.getName() + " interrupted");
			}
		}
		
		point.notifyObservers(StateEnum.ITS_OVER);
	}
	
	/**
	 * This method checks whether there are any further moves from the current position. 
	 * If three or more of the neighbouring tiles are Optional.empty(), this means that there are no further moves possible from the current position.
	 * 
	 * @return boolean - true if there are no further moves
	 */
	private boolean blockedPoint() {
		if (point.equals(GlobalValues.start)) {
			return false;
		}
		
		int count = 0;
		for (DirEnum dir : DirEnum.values()) {
			if (GlobalValues.immutableGrid.getNeighbouringToIndex(point.getPosX(), point.getPosY(), dir).equals(Optional.empty())) {
				count++;
			}
		}
		
		return (count >= 3);
	}

	@Override
	public RoutePoint getPoint() {
		return point;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg != null) {
			log.debug("Triggering object: " + o);
			switch (arg.toString()) {
			case "ITS_OVER":		log.debug("[ITS OVER]: Move has no further options. Degrees Of Freedom reduced by 1.");
									point.decDegOfFreedom();
									break;
									
			case "I_HAVE_BETTER":	log.debug("[I HAVE BETTER]: No longer best option for move. Degrees Of Freedom reduced by 1.");
									point.decDegOfFreedom();
									break;
			default:				log.trace("Unknown Command:" + arg.toString());
			}
		}
	}

}
