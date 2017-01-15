package com.ntak.examples.JuniferMaze.parsers.impl;

import java.util.ArrayList;
import java.util.List;

import com.ntak.examples.JuniferMaze.trees.impl.RoutePoint;

/**
 * Contains factory methods for generating a Grid for traversal. 
 * 
 * @author akakshepati
 *
 */
public class GridFactory {

	private static List<List<String>> gridValues;
	
	public static ImmutableGrid create(List<List<String>> gridValues, int width, int height) {
		GridFactory.gridValues = gridValues;
		return new ImmutableGrid(generateRootPoints(gridValues),width,height);
	}

	public static MutableGrid create() {
		return new MutableGrid(gridValues);
	}
	
	/**
	 * Flattens the matrix of Strings into a single List of RoutePoints to represent the grid.
	 * 
	 * @param gridValues - matrix of String values, which expects 1 = a non-traversable wall and other values to be traversable fields.
	 * @return List&lt;RoutePoint&gt; - flattened matrix to a list of RoutePoints
	 */
	private static List<RoutePoint> generateRootPoints(List<List<String>> gridValues) {
		List<RoutePoint> flattenedGrid = new ArrayList<>();
		
		for (int y = 0; y < gridValues.size(); y++){
			List<String> row = gridValues.get(y);
			for (int x = 0; x < row.size(); x++) {
				if (gridValues.get(y).get(x).equals("1"))
					flattenedGrid.add(null);
				else
					flattenedGrid.add(new RoutePoint(x,y));
			}
		}
		return flattenedGrid;
	}
	
}
