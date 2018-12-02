package com.interview.yoti.robot.utils;

import com.interview.yoti.robot.model.Point2D;

/**
 *  @author Aashutos Kakshepati
 */
public final class Constants {

	public static final Point2D UP_ONE = new Point2D(0,1);
	public static final Point2D DOWN_ONE = new Point2D(0,-1);
	public static final Point2D LEFT_ONE = new Point2D(-1,0);
	public static final Point2D RIGHT_ONE = new Point2D(1,0);
	
	public static final String EXC_ANN_P2D_RANGE = "Point(s): %s is not within expected range: [min=%d; max=%d].";
	public static final String EXC_PRST_HOOVER_REQ_RESP = "Error Persisting Data into DB: Request=%s; Response=%s.";
}
