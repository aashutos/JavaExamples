package com.ntak.examples.JuniferMaze.parsers.impl;

import java.util.Optional;

import com.ntak.examples.JuniferMaze.application.GlobalValues;
import com.ntak.examples.JuniferMaze.trees.impl.RoutePoint;

import junit.framework.TestCase;

/**
 * Rudimentary positive tests to test the ImmutableGrid and ImmutableGridFactory
 * 
 * @author akakshepati
 *
 */
public class ImmutableGridTest extends TestCase {

	private ImmutableGrid grid;
	
	protected void setUp() throws Exception {
		DelimitedDataParseService.getHeader("./src/test/resources/input.txt",null, GlobalValues.HeaderValues);
		GlobalValues.updateHeaderConstantInformation();
		System.out.println(GlobalValues.height);
		System.out.println(GlobalValues.width);
		grid = GridFactory.create(DelimitedDataParseService.parseData(), GlobalValues.width, GlobalValues.height);
		super.setUp();
	}

	public void test() {
		assertNotNull(grid);
		assertEquals(5,grid.getWidth());
		assertEquals(5,grid.getHeight());
		
		assertEquals(Optional.empty(),grid.getAtIndex(0,0));
		assertEquals(Optional.of(new RoutePoint(1,1)),grid.getAtIndex(1,1));
		assertEquals(Optional.of(new RoutePoint(2,3)),grid.getAtIndex(2,3));
		assertEquals(Optional.empty(),grid.getAtIndex(4,4));
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
