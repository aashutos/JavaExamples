package com.interview.yoti.robot.enums;

import static com.interview.yoti.robot.utils.Constants.*;

import com.interview.yoti.robot.model.Point2D;

/**
 *  Enumeration specifying the direction of movement.
 *  
 *  @author Aashutos Kakshepati
 */
public enum CardinalDirection {
	
	N("North",UP_ONE),
	S("South",DOWN_ONE),
	E("East",RIGHT_ONE),
	W("West",LEFT_ONE);

	private final String direction;
	private final Point2D movement;
	
	CardinalDirection(String direction, Point2D movement) {
		this.direction = direction;
		this.movement = movement;
	}

	public String getDirection() {
		return direction;
	}
	
	public Point2D getMovement() {
		return movement;
	}
}
