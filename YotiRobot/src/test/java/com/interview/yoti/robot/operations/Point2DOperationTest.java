package com.interview.yoti.robot.operations;

import static org.junit.Assert.*;

import org.junit.Test;

import com.interview.yoti.robot.model.Point2D;

/**
 *  @author Aashutos Kakshepati
 */
public class Point2DOperationTest {

	@Test
	public void testAdd() {
		Point2D one = new Point2D(1,1);
		Point2D two = new Point2D(2,9);
		Point2D res = Point2DOperation.add(one, two);
		
		assertNotNull("Add operation failed unexpectedly.", res);
		assertEquals("X Dimension added wrong.", 3, res.getX());
		assertEquals("Y Dimension added wrong.", 10, res.getY());
	}
	
	@Test
	public void testArrayStringGeneration() {
		Point2D one = new Point2D(1,1);
		Point2D two = new Point2D(2,9);
		Point2D three = new Point2D(8,5);
		Point2D[] points= new Point2D[]{one,two,three};
		String res = Point2DOperation.generatePersistableArrayValues(points);
		
		System.out.println(res);
		
		assertNotNull("Add operation failed unexpectedly.", res);
		assertEquals("Output generated incorrectedly.", String.format("%s,%s,%s", one,two,three), res);
	}

}
