package com.interview.yoti.robot.model.payloads;

import java.util.Arrays;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.interview.yoti.robot.adapters.Point2DAdapter;
import com.interview.yoti.robot.adapters.Point2DArrayAdapter;
import com.interview.yoti.robot.annotations.Point2DRangeCheck;
import com.interview.yoti.robot.model.Point2D;

/**
 *  POJO Specifying the Hoover Simulation Request.
 *  
 *  @author Aashutos Kakshepati
 */
public class HooverRequestPayload {

	@SerializedName("roomSize")
	@NotNull
	@Point2DRangeCheck(min=0,strict=true)
	@JsonAdapter(Point2DAdapter.class)
	private Point2D dimensions;
	@SerializedName("coords")
	@NotNull
	@Point2DRangeCheck(min=0,strict=true)
	@JsonAdapter(Point2DAdapter.class)
	private Point2D hooverPosition;
	@SerializedName("patches")
	@NotNull
	@Point2DRangeCheck(min=0,strict=true)
	@JsonAdapter(Point2DArrayAdapter.class)
	private Point2D[] dirtPatches;
	@NotNull
	@Pattern(regexp="[NSEW]*", message="Instructions must contain N,S,E,W only. Can be empty string, however.")
	private String instructions;
	
	public Point2D getDimensions() {
		return dimensions;
	}
	
	public void setDimensions(Point2D dimensions) {
		this.dimensions = dimensions;
	}
	
	public Point2D getHooverPosition() {
		return hooverPosition;
	}
	
	public void setHooverPosition(Point2D hooverPosition) {
		this.hooverPosition = hooverPosition;
	}
	
	public Point2D[] getDirtPatches() {
		return dirtPatches;
	}
	
	public void setDirtPatches(Point2D[] dirtPatches) {
		this.dirtPatches = dirtPatches;
	}
	
	public String getInstructions() {
		return instructions;
	}
	
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	@Override
	public String toString() {
		return "HooverRequestPayload [dimensions=" + dimensions + ", hooverPosition=" + hooverPosition
				+ ", dirtPatches=" + Arrays.toString(dirtPatches) + ", instructions=" + instructions + "]";
	}
	
}
