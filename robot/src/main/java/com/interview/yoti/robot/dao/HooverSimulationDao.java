package com.interview.yoti.robot.dao;

import static com.interview.yoti.robot.dao.jooq.Tables.HOOVER_REQUESTS;
import static com.interview.yoti.robot.dao.jooq.Tables.HOOVER_RESPONSE;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.InsertSetMoreStep;
import org.springframework.stereotype.Repository;

import com.interview.yoti.robot.model.payloads.HooverRequestPayload;
import com.interview.yoti.robot.model.payloads.HooverResponsePayload;
import static com.interview.yoti.robot.operations.Point2DOperation.*;
/**
 *  @author Aashutos Kakshepati
 */
@Repository
public class HooverSimulationDao {

	private static Logger LOG = Logger.getLogger(HooverSimulationDao.class);
	
	private static DSLContext dsl;

	public static final int persistRequest(HooverRequestPayload request, long reqTs) {
		String dirtPatches = generatePersistableArrayValues(request.getDirtPatches());
		InsertSetMoreStep<?> ins = dsl.insertInto(HOOVER_REQUESTS)
		   .set(HOOVER_REQUESTS.REQUEST_TIMESTAMP, reqTs)
		   .set(HOOVER_REQUESTS.DIMENSION_ROOM, request.getDimensions().toString())
		   .set(HOOVER_REQUESTS.POSITION_HOOVER, request.getHooverPosition().toString())
		   .set(HOOVER_REQUESTS.INSTRUCTIONS, request.getInstructions().toString())
		   .set(HOOVER_REQUESTS.DIRT_PATCHES, dirtPatches);
		  
		   LOG.info("SQL Query generated: " + ins.getSQL());
		   
		   return ins.execute();
	}

	public static final int persistResponse(HooverResponsePayload response, long reqTs) {
		InsertSetMoreStep<?> ins = dsl.insertInto(HOOVER_RESPONSE)
		   .set(HOOVER_RESPONSE.REQUEST_TIMESTAMP, reqTs)
		   .set(HOOVER_RESPONSE.POSITION_HOOVER, response.getFinalPosition().toString())
		   .set(HOOVER_RESPONSE.PATCHES_CLEANED, response.getPatches());
		  
		   LOG.info("SQL Query generated: " + ins.getSQL());
		   
		   return ins.execute();
	}

	public static void setDsl(DSLContext dsl) {
		HooverSimulationDao.dsl = dsl;
	}
}
