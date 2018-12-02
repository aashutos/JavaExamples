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
public class Point2DRangeValidatorTest {

	private static final int MIN = 0;
	private static final int MAX = 100;
	private Point2DRangeValidator validator;
	@Mock
	private Point2DRangeCheck mockAnnotation;
	@Mock
	private ConstraintValidatorContext mockContext;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(mockAnnotation.max()).thenReturn(MAX);
		when(mockAnnotation.min()).thenReturn(MIN);
		when(mockAnnotation.strict()).thenReturn(true);
		
		validator = new Point2DRangeValidator();		
	}

	@Test
	public void testNotStrictModeNullTrue() {
		when(mockAnnotation.strict()).thenReturn(false);
		validator.initialize(mockAnnotation);
		assertTrue("Unexpectedly invalid.",validator.isValid(null, mockContext));
	}

	@Test
	public void testStrictModeNullFalse() {
		validator.initialize(mockAnnotation);
		assertFalse("Unexpectedly valid.",validator.isValid(null, mockContext));
	}

	@Test
	public void testStrictModeValidTrue() {
		validator.initialize(mockAnnotation);
		assertTrue("Unexpectedly invalid.",validator.isValid(new Point2D(1,1), mockContext));
	}

	@Test
	public void testLowerBoundFalse() {
		validator.initialize(mockAnnotation);
		assertFalse("Unexpectedly valid.",validator.isValid(new Point2D(-1,1), mockContext));
	}

	@Test
	public void testUpperBoundFalse() {
		validator.initialize(mockAnnotation);
		assertFalse("Unexpectedly valid.",validator.isValid(new Point2D(1,1000), mockContext));
	}
	
	@Test
	public void testLowerBoundaryInclusive() {
		validator.initialize(mockAnnotation);
		assertTrue("Unexpectedly invalid.",validator.isValid(new Point2D(MIN,MIN), mockContext));
	}

	@Test
	public void testUpperBoundaryInclusive() {
		validator.initialize(mockAnnotation);
		assertTrue("Unexpectedly invalid.",validator.isValid(new Point2D(MAX,MAX), mockContext));
	}
}
