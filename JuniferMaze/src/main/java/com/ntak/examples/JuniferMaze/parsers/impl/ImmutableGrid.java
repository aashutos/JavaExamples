package com.ntak.examples.JuniferMaze.parsers.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.ntak.examples.JuniferMaze.enums.DirEnum;
import com.ntak.examples.JuniferMaze.trees.impl.RoutePoint;

/**
 * Bean, which encapsulates descriptive data regarding the incremental solution
 * to the grid.
 * 
 * @author akakshepati
 *
 */
public class ImmutableGrid{

	private final List<RoutePoint> grid;
	private final int width;
	private final int height;

	/**
	 * 
	 * Constructs an immutable grid based on the data and dimensions specified as arguments. 
	 * If the grid parameters were not sufficient a default empty grid will be returned.
	 * 
	 * @param grid - List&lt;RoutePoint&gt; containing all values in grid
	 * @param width - RoutePointhe number of columns in grid
	 * @param height - RoutePointhe number of rows in grid
	 */
	public ImmutableGrid(List<RoutePoint> grid, int width, int height) {
		super();
		if (grid != null && (width * height == grid.size())) {
			this.grid = Collections.unmodifiableList(new ArrayList<RoutePoint>(grid));
			this.width = width;
			this.height = height;
		} else {
			this.grid = new ArrayList<RoutePoint>(grid);
			this.width = 0;
			this.height = 0;
		}
	};

	/**
	 * Returns RoutePoint at a particular co-ordinate specified (if valid). Otherwise returns an Optional.empty().
	 * 
	 * @param x - integer x-coordinate (expected value starts from 0 to Width - 1)
	 * @param y - integer y-coordinate (expected value starts from 0 to Height - 1)
	 * @return Optional&lt;RoutePoint&gt; representing the data about a particular point in the grid or empty() if not a valid position on grid.
	 */
	public Optional<RoutePoint> getAtIndex(int x, int y) {
		return (x < width && y < height && x >= 0 && y >= 0)?Optional.ofNullable(grid.get(flattenCoordinates(x,y))):Optional.empty();
	};

	public Optional<RoutePoint> getNeighbouringToIndex(int x, int y, DirEnum dir) {
		if (x >= width || y >= height)
			return Optional.empty();
		
		switch (dir) {	
			case NORTH:		return getAtIndex(x,y-1);
			case SOUTH:		return getAtIndex(x,y+1);
			case EAST:		return getAtIndex(x-1,y);
			case WEST:		return getAtIndex(x+1,y);
			default: 		return Optional.empty();
		}
	};

	/**
	 * Internal conversion method from a second order co-ordinate system to a single order co-ordinate system.
	 * No checks are made by this method to ensure validity of co-ordinates that have been passed in.
	 * 
	 * @param x - x co-ordinate of grid
	 * @param y - y co-ordinate of grid
	 * @return int - list index value
	 */
	private int flattenCoordinates(int x, int y) {
		return x + width * y;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public String toString() {
		return "ImmutableGrid [grid=" + grid + "]";
	}
	
}
