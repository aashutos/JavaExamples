package com.interview.yoti.robot.validators;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.Errors;

import com.interview.yoti.robot.model.Point2D;
import com.interview.yoti.robot.model.payloads.HooverRequestPayload;

/**
 *  @author Aashutos Kakshepati
 */
public class HooverSimRequestValidatorTest {

	private static final String ERR_CODE = "ERRO1";
	private HooverSimRequestValidator validator;
	private HooverRequestPayload payload;
	@Mock
	private Errors mockErrors;
	private static String INST = "NNNNNEEEEESSSSSWWWWW";
	private static final Point2D SIZE = new Point2D(100,100);
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		validator = new HooverSimRequestValidator();
		payload = new HooverRequestPayload();
		payload.setDimensions(SIZE);
	}

	@Test
	public void testValid() {
		int maxNoPatches = 16;
		Point2D[] patches = setRandomUniquePatches(SIZE.getX(),SIZE.getY(), maxNoPatches).toArray(new Point2D[maxNoPatches]);

		patches = new HashSet<Point2D>(Arrays.asList(patches)).toArray(new Point2D[8]);
		payload.setHooverPosition(
				new Point2D(
						(int)(Math.random()*100),
						(int)(Math.random()*100)
						)
		);
		payload.setDirtPatches(patches);
		payload.setInstructions(INST);
		
		assertTrue("Not expected type for validator.", validator.supports(payload.getClass()));
		validator.validate(payload, mockErrors);
		verify(mockErrors, never()).reject(anyString(), anyObject(), anyString());
	}

	@Test
	public void testInvalidHooverPositionNegative() {
		int maxNoPatches = 16;
		Point2D[] patches = setRandomUniquePatches(SIZE.getX(),SIZE.getY(), maxNoPatches).toArray(new Point2D[maxNoPatches]);

		patches = new HashSet<Point2D>(Arrays.asList(patches)).toArray(new Point2D[8]);
		payload.setHooverPosition(
				new Point2D(
						(int)(Math.random()*100),
						-5
						)
		);
		payload.setDirtPatches(patches);
		payload.setInstructions(INST);
		
		assertTrue("Not expected type for validator.", validator.supports(payload.getClass()));
		validator.validate(payload, mockErrors);
		verify(mockErrors).reject(eq(ERR_CODE), anyObject(), anyString());
	}

	@Test
	public void testInvalidHooverPositionPositive() {
		int maxNoPatches = 16;
		Point2D[] patches = setRandomUniquePatches(SIZE.getX(),SIZE.getY(), maxNoPatches).toArray(new Point2D[maxNoPatches]);

		patches = new HashSet<Point2D>(Arrays.asList(patches)).toArray(new Point2D[8]);
		payload.setHooverPosition(
				new Point2D(
						(int)(Math.random()*100),
						SIZE.getY()
						)
		);
		payload.setDirtPatches(patches);
		payload.setInstructions(INST);
		
		assertTrue("Not expected type for validator.", validator.supports(payload.getClass()));
		validator.validate(payload, mockErrors);
		verify(mockErrors).reject(eq(ERR_CODE), anyObject(), anyString());
	}
	
	@Test
	public void testInvalidDirtPatchNegative() {
		int maxNoPatches = 15;
		
		Set<Point2D> setPatches = setRandomUniquePatches(SIZE.getX(),SIZE.getY(), maxNoPatches);
		setPatches.add(new Point2D(-1,-1));
		
		Point2D[] patches = setPatches.toArray(new Point2D[setPatches.size()]);
		
		patches = new HashSet<Point2D>(Arrays.asList(patches)).toArray(new Point2D[8]);
		payload.setHooverPosition(
				new Point2D(
						(int)(Math.random()*100),
						(int)(Math.random()*100)
						)
		);
		payload.setDirtPatches(patches);
		payload.setInstructions(INST);
		
		assertTrue("Not expected type for validator.", validator.supports(payload.getClass()));
		validator.validate(payload, mockErrors);
		verify(mockErrors).reject(eq(ERR_CODE), anyObject(), anyString());
	}
	

	@Test
	public void testInvalidDirtPatch() {
		int maxNoPatches = 15;
		
		Set<Point2D> setPatches = setRandomUniquePatches(SIZE.getX(),SIZE.getY(), maxNoPatches);
		setPatches.add(new Point2D(SIZE.getX()+1000,SIZE.getY()+1000));
		
		Point2D[] patches = setPatches.toArray(new Point2D[setPatches.size()]);
		
		patches = new HashSet<Point2D>(Arrays.asList(patches)).toArray(new Point2D[8]);
		payload.setHooverPosition(
				new Point2D(
						(int)(Math.random()*100),
						(int)(Math.random()*100)
						)
		);
		payload.setDirtPatches(patches);
		payload.setInstructions(INST);
		
		assertTrue("Not expected type for validator.", validator.supports(payload.getClass()));
		validator.validate(payload, mockErrors);
		verify(mockErrors).reject(eq(ERR_CODE), anyObject(), anyString());
	}
	
	public Set<Point2D> setRandomUniquePatches(int maxX, int maxY, int maxNoPatches) {
		Set<Point2D> points = new HashSet<>();
		
		for (int i = 0; i < maxNoPatches; i++) {
			Point2D curPoint = genRandomPointWithinBounds(maxX, maxY);
			points.add(curPoint);
		}
		
		return points;
	}
	
	public Point2D genRandomPointWithinBounds(int maxX, int maxY) {
		return new Point2D(
				(int)(Math.random()*maxX),
				(int)(Math.random()*maxY)
				);
	}
}
