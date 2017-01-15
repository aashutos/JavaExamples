package com.ntak.examples.JuniferMaze.trees;

import java.util.Map;

import com.ntak.examples.JuniferMaze.enums.DirEnum;

/**
 * Beans that inherit this interface are able to be traversed by the TreeSearch class.
 * 
 * @see com.ntak.examples.JuniferMaze.trees.impl.TreeSearch
 * 
 * @author akakshepati
 *
 */
public interface Treeable{

	/**
	 * Returns the next possible nodes for each move. If no possible move is available for a particular direction, null is expected to be returned.
	 * 
	 * @return List&lt;Treeable&gt; - minimal path to the point as of reading
	 */
	public Map<DirEnum,? extends Treeable> getNextNodes();
	
	/**
	 * 
	 * Returns the number of possible moves that can be made from the current position. If no further moves are available, the value 0 is returned.
	 * 
	 * @return int - number of moves possible. Default value 0.
	 */
	public default int getDegOfFreedom(){
		return 0;
	};
}
