package com.ntak.examples.JuniferMaze.parsers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ntak.examples.JuniferMaze.application.GlobalValues;
import com.ntak.examples.JuniferMaze.trees.impl.RoutePoint;

/**
 * MutableGrid which assumes input grid is well formed as Immutable Grid needs to be created first.
 * 
 * @author akakshepati
 *
 */
public class MutableGrid {
	
	private static Logger log = LogManager.getLogger(MutableGrid.class);
	private List<List<String>> grid;
	
	public MutableGrid(List<List<String>> grid) {
		if (grid != null) {
			this.grid = grid;
		} else {
			this.grid = new ArrayList<List<String>>();
		}
	}
	
	/**
	 * Writes solution path to the Mutable grid. S for start position, E for the goal position and X for the interim position in solution path.
	 * 
	 * @param path - List of path ROutePoints to be marked
	 */
	public void markPath(List<RoutePoint> path) {
		for (RoutePoint step : path) {
			if (step != null) {
				ArrayList<String> row = new ArrayList<String>(grid.get(step.getPosY()));
				if (isStartingPoint(step)) {
					row.set(step.getPosX(), "S");
					grid.set(step.getPosY(), row);
					continue;
				}
				if (isGoalPoint(step)) {
					row.set(step.getPosX(), "E");
					grid.set(step.getPosY(), row);
					continue;
				}
				if (row.get(step.getPosX()).equals("0")) {
					row.set(step.getPosX(), "X");
					grid.set(step.getPosY(), row);
				} else
					log.warn("Route Point is not traversable. Point information: " + step);
			}
		}
		
	}
	
	private static boolean isGoalPoint(RoutePoint step) {
		return GlobalValues.goal.equals(step);
	}

	private static boolean isStartingPoint(RoutePoint step) {
		return GlobalValues.start.equals(step);
	}

	public void displayGrid() {
		StringBuilder strBuilder = new StringBuilder();
		for (int y = 0; y < grid.size(); y++) {
			List<String> row = grid.get(y);
			for (int x = 0; x < row.size(); x++) {
				strBuilder.append(row.get(x)).append(' ');
			}
			strBuilder.append("\n");
		}
		log.info("Output: ");
		log.info("\n" + strBuilder.toString());
	}

}
