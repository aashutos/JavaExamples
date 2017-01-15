package com.ntak.examples.JuniferMaze.parsers.impl;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ntak.examples.JuniferMaze.enums.HeaderEnum;
import com.ntak.examples.JuniferMaze.parsers.HeaderBean;
import com.ntak.examples.JuniferMaze.trees.impl.RoutePoint;

/**
 * Implementation of the HeaderBean for the grid file.
 * 
 * @author akakshepati
 *
 */
public class GridHeaderBean implements HeaderBean {
	private static Logger log = LogManager.getLogger(GridHeaderBean.class);
	private RoutePoint start;
	private RoutePoint goal;
	private Integer[] dims;

	/**
	 * Set header values for each element of the header, if input values can be parsed to integer type.
	 * 
	 */
	@Override
	public <T> void setHeaderValue(HeaderEnum key, T[] value) {
		try {
		switch (key) {
			
			case GRID_SIZE:			{
										if (value.length >= 2) {
											dims = new Integer[]{Integer.parseInt(value[0].toString()),Integer.parseInt(value[1].toString())};
										}
									}
		
			case START_POINT:		{
										if (value.length >= 2) {
											start = new RoutePoint(Integer.parseInt(value[0].toString()),Integer.parseInt(value[1].toString()));
										}
									}
			
			case GOAL_POINT:		{
										if (value.length >= 2) {
											goal = new RoutePoint(Integer.parseInt(value[0].toString()),Integer.parseInt(value[1].toString()));
									}
			}
			
			default: 				return;
		}
		} catch (NumberFormatException nfe) {
			log.error("The supplied String values for header were not numeric values as expected.", nfe);
			return;
		}
	}

	/**
	 * Returns beans associated with the HeaderEnum identifier specified. If the type specified is not expected null will be returned.
	 * Expected headers and return types are specified below:
	 * 
	 * <ul>
	 * 	<li>GRID_SIZE - The Size of the grid: Width and Height. The expected return type is Integer[]</li>
	 *  <li>START_POINT - The Starting point of the solution: The expected type is RoutePoint</li>
	 *  <li>GOAL_POINT - The Goal point of the solution: The expected type is RoutePoint</li>
	 * </ul>
	 * 
	 * @see com.ntak.examples.JuniferMaze.enums.HeaderEnum
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getHeaderValue(HeaderEnum key) {
		try {
			switch (key) {

			case GRID_SIZE:
				return (T) dims;

			case START_POINT:
				return (T) start;

			case GOAL_POINT:
				return (T) goal;

			default:
				return null;

			}
		} catch (ClassCastException cce) {
			log.error("The supplied class type was not the expected type for the parameter: " + key, cce);
			return null;
		}
	}

	public RoutePoint getStart() {
		return start;
	}

	public RoutePoint getGoal() {
		return goal;
	}

	public Integer[] getDims() {
		return dims;
	}

	@Override
	public String toString() {
		return "GridHeaderBean [start=" + start + ", goal=" + goal + ", dims=" + Arrays.toString(dims) + "]";
	}
	
}
