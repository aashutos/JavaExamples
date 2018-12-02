package com.interview.yoti.robot.operations;

import javax.validation.constraints.NotNull;

import com.interview.yoti.robot.model.Point2D;

/**
 *  Common Point2D Operations.
 * 
 *  @author Aashutos Kakshepati
 */
public class Point2DOperation {
	
	private static final String COMMA = ",";
	
	public static final Point2D add(@NotNull(message="First Point2D is null.") Point2D first, 
									@NotNull(message="Second Point2D is null.") Point2D second) {
		return new Point2D(first.getX() + second.getX(), first.getY() + second.getY());
	}
	
	public static String generatePersistableArrayValues(Point2D[] points) {
		StringBuilder stBuild = new StringBuilder();
		
		for (Point2D point : points) {
			stBuild.append(COMMA).append(point.toString());
		}
		
		return stBuild.substring(1);
	}
	
}
