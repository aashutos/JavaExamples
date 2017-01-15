package com.ntak.examples.JuniferMaze.application;

import java.util.List;

import com.ntak.examples.JuniferMaze.trees.Treeable;

/**
 * Class encapsulates the state of a particular element of the solution.
 * 
 * @author akakshepati
 *
 */
public class GridState<T extends Treeable> implements Comparable<GridState<T>> {
	
	/**
	 * Minimum number of steps to a particular point.
	 * 
	 */
	private final int steps;
	
	/**
	 * The number of Treeable Nodes that lead up to this particular point. 
	 * 
	 * 
	 */
	private final List<T> breadcrumbs;

	public GridState(int steps, List<T> breadcrumbs) {
		super();
		this.steps = steps;
		this.breadcrumbs = breadcrumbs;
	}

	public int getSteps() {
		return steps;
	}

	public List<T> getBreadcrumbs() {
		return breadcrumbs;
	}

	public int compareTo(int newSteps) {
		return Integer.compare(steps, newSteps);
	}

	@Override
	public int compareTo(GridState<T> newState) {
		return compareTo(newState.steps);
	}

	@Override
	public String toString() {
		return "[steps=" + steps + ", breadcrumbs=" + breadcrumbs + "]";
	}
	
	
}
