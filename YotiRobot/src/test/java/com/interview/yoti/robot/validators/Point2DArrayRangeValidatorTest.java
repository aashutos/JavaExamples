package com.interview.yoti.robot.validators;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import javax.validation.ConstraintValidatorContext;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.interview.yoti.robot.annotations.Point2DRangeCheck;
import com.interview.yoti.robot.model.Point2D;

/**
 *  @author Aashutos Kakshepati
 */
public class Point2DArrayRangeValidatorTest {

	private Point2DArrayRangeValidator validator;
	@Mock
	private Point2DRangeCheck mockAnnotation;
	@Mock
	private ConstraintValidatorContext mockContext;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(mockAnnotation.max()).thenReturn(1000000);
		when(mockAnnotation.min()).thenReturn(0);
		when(mockAnnotation.strict()).thenReturn(true);
		
		validator = new Point2DArrayRangeValidator();	
		validator.initialize(mockAnnotation);
	}

	@Test
	public void testNotStrictModeNullTrue() {
		when(mockAnnotation.strict()).thenReturn(false);
		validator.initialize(mockAnnotation);
		assertTrue("Unexpectedly invalid.", validator.isValid(null, mockContext));
	}

	@Test
	public void testStrictModeNullFalse() {
		assertFalse("Unexpectedly valid.", validator.isValid(null, mockContext));
	}

	@Test
	public void testStrictModeTrue() {
		assertTrue("Unexpectedly invalid.", validator.isValid(new Point2D[]{new Point2D(100,100),new Point2D(1,54),new Point2D(15,60)}, mockContext));		
	}

	@Test
	public void testLowerBoundFalse() {
		assertFalse("Unexpectedly valid.", validator.isValid(new Point2D[]{new Point2D(100,100),new Point2D(1,54), new Point2D(-1,-1)}, mockContext));		
	}

	@Test
	public void testUpperBoundFalse() {
		assertFalse("Unexpectedly valid.", validator.isValid(new Point2D[]{new Point2D(100,100),new Point2D(1,54), new Point2D(999999999,999999999)}, mockContext));
	}

}
