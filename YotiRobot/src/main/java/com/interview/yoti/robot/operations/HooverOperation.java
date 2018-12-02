package com.interview.yoti.robot.operations;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.RecoverableDataAccessException;

import com.interview.yoti.robot.dao.HooverSimulationDao;
import com.interview.yoti.robot.enums.CardinalDirection;
import com.interview.yoti.robot.model.HooverState;
import com.interview.yoti.robot.model.Point2D;
import com.interview.yoti.robot.model.payloads.HooverRequestPayload;
import com.interview.yoti.robot.model.payloads.HooverResponsePayload;

import static com.interview.yoti.robot.utils.Constants.EXC_PRST_HOOVER_REQ_RESP;

/**
 *  Business Logic around the simulation of a Hoover Cleaning process.
 * 
 *  @author Aashutos Kakshepati
 */
public class HooverOperation {
	
	
	private static final Logger LOG = LoggerFactory.getLogger(HooverOperation.class);
			
	public static HooverResponsePayload executeSimulation(HooverRequestPayload request, Long timestamp) {
		HooverState currentState = initialiseState(request);
		List<CardinalDirection> instructions = initialiseInstructions(request);
		
		for (CardinalDirection instruction : instructions) {
			LOG.info("CURRENT STATE >>>>>" + currentState);
			currentState = incrementHooverMovement(currentState, instruction);			
		}
		LOG.info("CURRENT STATE >>>>>" + currentState);
		HooverResponsePayload response = new HooverResponsePayload(currentState.getHooverPosition(),currentState.getNoPatchesRemoved());
		persistSimulation(request,response,timestamp);
		
		return response;
	}

	private static void persistSimulation(HooverRequestPayload request, HooverResponsePayload response, long timestamp) {
		int res = HooverSimulationDao.persistRequest(request, timestamp) + HooverSimulationDao.persistResponse(response, timestamp);
		if (res != 2)
			throw new RecoverableDataAccessException(String.format(EXC_PRST_HOOVER_REQ_RESP,request,response));
	}

	private static HooverState incrementHooverMovement(HooverState currentState, CardinalDirection instruction) {
		Point2D newPosition = Point2DOperation.add(currentState.getHooverPosition(), instruction.getMovement());
		
		// Out of Bounds Validation Check
		if (newPosition.getX() >= currentState.getDimensions().getX() || newPosition.getY() >= currentState.getDimensions().getY()
			|| newPosition.getX() < 0 || newPosition.getY() < 0) {
			return currentState;
		}
		
		// Patch Removal Check
		List<Point2D> newDirtPatches = new LinkedList<>(currentState.getDirtPatches());
		int noPatchesRemoved = currentState.getNoPatchesRemoved();
		if (newDirtPatches.contains(newPosition)) {
			newDirtPatches.remove(newPosition);
			noPatchesRemoved++;
		}
		
		currentState = new HooverState(newPosition, currentState.getDimensions(), newDirtPatches, noPatchesRemoved);
		
		return currentState;
	}

	private static List<CardinalDirection> initialiseInstructions(HooverRequestPayload request) {
		List<CardinalDirection> instructions = new LinkedList<>();
		for (char dir : request.getInstructions().toCharArray()) {
			instructions.add(CardinalDirection.valueOf(String.valueOf(dir)));
		}
		return instructions;
	}

	private static HooverState initialiseState(HooverRequestPayload request) {
		HooverState currentState = new HooverState(request.getHooverPosition(), request.getDimensions(), Arrays.asList(request.getDirtPatches()));
		return currentState;
	}
}
