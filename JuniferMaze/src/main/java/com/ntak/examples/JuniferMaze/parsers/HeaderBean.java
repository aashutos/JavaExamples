package com.ntak.examples.JuniferMaze.parsers;

import com.ntak.examples.JuniferMaze.enums.HeaderEnum;

/**
 * Interface specifying methods used to store information regarding the header contents of a file.
 * 
 * @author akakshepati
 *
 */
public interface HeaderBean {
	public <T> void setHeaderValue(HeaderEnum key, T[] value);
	public <T> T getHeaderValue(HeaderEnum key);
}
