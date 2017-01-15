package com.ntak.examples.JuniferMaze.trees;

/**
 * Interface, which when implemented will provide algorithm to search specified Treeable Node structure.
 * 
 * @author akakshepati
 *
 * @param <T> - Treeable Node type that is to be traversed
 */
public interface Search<T extends Treeable> {
	
	public void determinePath();
	
	public T getPoint();
	
}
