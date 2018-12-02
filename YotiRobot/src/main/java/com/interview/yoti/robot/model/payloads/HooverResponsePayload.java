package com.interview.yoti.robot.model.payloads;

import javax.validation.constraints.NotNull;

import com.google.gson.annotations.SerializedName;
import com.interview.yoti.robot.model.Point2D;

/**
 *  POJO representing the final state of a Hoover Simulation.
 * 
 *  @author Aashutos Kakshepati
 */
public class HooverResponsePayload {

	@NotNull
	@SerializedName("coords")
	private Point2D finalPosition;
	@NotNull
	@SerializedName("patches")
	private int patchesCleaned;
	
	public Point2D getFinalPosition() {
		return finalPosition;
	}
	
	public HooverResponsePayload(Point2D finalPosition, int patches) {
		super();
		this.finalPosition = finalPosition;
		this.patchesCleaned = patches;
	}

	public void setFinalPosition(Point2D finalPosition) {
		this.finalPosition = finalPosition;
	}
	
	public int getPatches() {
		return patchesCleaned;
	}
	
	public void setPatches(int patches) {
		this.patchesCleaned = patches;
	}

	@Override
	public String toString() {
		return "[finalPosition=" + finalPosition + ", patches=" + patchesCleaned + "]";
	}
}
