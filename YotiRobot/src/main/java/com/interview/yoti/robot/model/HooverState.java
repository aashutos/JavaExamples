package com.interview.yoti.robot.model;

import java.util.Collections;
import java.util.List;

/**
 *  Unmodifiable POJO Representing the current state of the model.
 *  
 *  @author Aashutos Kakshepati
 */
public class HooverState {

	private final Point2D hooverPosition;
	private final Point2D dimensions;
	private final List<Point2D> dirtPatches;
	private final int noPatchesRemoved;
	
	public HooverState(Point2D hooverPosition, Point2D dimensions, List<Point2D> dirtPatches) {
		this(hooverPosition, dimensions, dirtPatches, 0);
	}
	
	public HooverState(Point2D hooverPosition, Point2D dimensions, List<Point2D> dirtPatches, int noPatchesRemoved) {
		super();
		this.hooverPosition = hooverPosition;
		this.dimensions = dimensions;
		this.dirtPatches = dirtPatches;
		this.noPatchesRemoved = noPatchesRemoved;
	}

	public Point2D getHooverPosition() {
		return hooverPosition;
	}

	public Point2D getDimensions() {
		return dimensions;
	}

	public List<Point2D> getDirtPatches() {
		return Collections.unmodifiableList(dirtPatches);
	}

	public int getNoPatchesRemoved() {
		return noPatchesRemoved;
	}

	@Override
	public String toString() {
		return "[hooverPosition=" + hooverPosition + ", dimensions=" + dimensions + ", dirtPatches="
				+ dirtPatches + ", noPatchesRemoved=" + noPatchesRemoved + "]";
	}	
	
}
