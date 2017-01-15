/**
 * 
 */
package com.ntak.examples.JuniferMaze.parsers.impl;

import junit.framework.TestCase;

/**
 * Rudimentary positive tests to check the file parser works as expected.
 * 
 * @author akakshepati
 *
 */
public class DelimitedDataParseServiceTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGetHeader(){
		System.out.println(DelimitedDataParseService.getHeader("./src/test/resources/input.txt", " ", new GridHeaderBean()));
	}
	
	public void testGetGrid(){
		DelimitedDataParseService.getHeader("./src/test/resources/input.txt", " ", new GridHeaderBean());
		System.out.println(DelimitedDataParseService.parseData());
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		DelimitedDataParseService.close();
	}

}
