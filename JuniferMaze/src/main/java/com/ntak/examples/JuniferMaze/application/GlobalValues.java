package com.ntak.examples.JuniferMaze.application;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import com.ntak.examples.JuniferMaze.enums.HeaderEnum;
import com.ntak.examples.JuniferMaze.parsers.HeaderBean;
import com.ntak.examples.JuniferMaze.parsers.impl.GridHeaderBean;
import com.ntak.examples.JuniferMaze.parsers.impl.ImmutableGrid;
import com.ntak.examples.JuniferMaze.trees.impl.RoutePoint;
import com.ntak.examples.JuniferMaze.trees.impl.TreeSearch;

/**
 * Class consists of global constants and variables that are used throughout this solution.
 * 
 * @author akakshepati
 *
 */
public class GlobalValues {

	public static final Pattern spaceDelPattern = Pattern.compile(" ");
	public static int width;
	public static int height;
	public static final Map<RoutePoint,TreeSearch> processingThreadMap = new ConcurrentHashMap<>(); 
	public static final Map<RoutePoint,GridState<RoutePoint>> globalState = new ConcurrentHashMap<>();
	public static final HeaderBean HeaderValues = new GridHeaderBean();
	public static RoutePoint start;
	public static RoutePoint goal;
	public static ImmutableGrid immutableGrid;
	public static volatile AtomicBoolean isEnded = new AtomicBoolean();
	public static PeriodFormatter frmtSeconds = new PeriodFormatterBuilder()
		     .appendSeconds()
		     .appendSuffix("s")
		     .appendMillis3Digit()
		     .appendSuffix("ms")
		     .toFormatter();
	
	/**
	 * Refreshes header constant information with values stored within the HeaderBean.
	 * 
	 */
	public static void updateHeaderConstantInformation() {
		start = HeaderValues.<RoutePoint>getHeaderValue(HeaderEnum.START_POINT);
		goal = HeaderValues.<RoutePoint>getHeaderValue(HeaderEnum.GOAL_POINT);
		Integer[] dims = HeaderValues.<Integer[]>getHeaderValue(HeaderEnum.GRID_SIZE);
		if (dims.length >= 2) {
			width = dims[0];
			height = dims[1];
		} else {
			width = 0;
			height = 0;
		}
	}
	
}
