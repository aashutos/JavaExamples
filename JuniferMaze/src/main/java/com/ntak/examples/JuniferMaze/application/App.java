package com.ntak.examples.JuniferMaze.application;

import java.util.Date;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.commons.cli.CommandLine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.Duration;

import com.ntak.examples.JuniferMaze.parsers.impl.DelimitedDataParseService;
import com.ntak.examples.JuniferMaze.parsers.impl.GridFactory;
import com.ntak.examples.JuniferMaze.parsers.impl.MutableGrid;
import com.ntak.examples.JuniferMaze.trees.impl.RoutePoint;
import com.ntak.examples.JuniferMaze.trees.impl.TreeSearch;
import com.ntak.examples.JuniferMaze.utils.CLIUtils;

/**
 * Main entry-point for the application.
 * 
 * Parameters that can be passed through CLI:
 * 
 * <ul>
 * 	<li> --filename/-f: The filename for the grid that is to be solved (required).</li>
 *  <li> --delimiter/-d: The delimiter utilised within the file specified. This is expected 
 *  to be consistent within the file. Default value is: " " (hence, not required).</li>
 * </ul>
 * 
 * @author akakshepati
 *
 */
public class App 
{
	
	private static Logger log = LogManager.getLogger(App.class);
	
    public static void main( String[] args ) throws InterruptedException
    {
    	long startTime = new Date().getTime();
        System.out.println( "Hello World! Time started: " + new Date(startTime));
        CommandLine usrCmds = CLIUtils.parseCLIOptions(args);
        
        if (usrCmds == null) {
        	log.error("Invalid command line values passed in. Shutting down...");
        	return;
        }
        
        if (DelimitedDataParseService.getHeader(usrCmds.getOptionValue("f"),usrCmds.getOptionValue("d"), GlobalValues.HeaderValues) == null) {
        	log.error("File could not be parsed as expected. Shutting down...");
        	return;
        }
        
        GlobalValues.updateHeaderConstantInformation();
        GlobalValues.immutableGrid = GridFactory.create(DelimitedDataParseService.parseData(), GlobalValues.width, GlobalValues.height);
        
        if (GlobalValues.immutableGrid.getAtIndex(GlobalValues.start.getPosX(), GlobalValues.start.getPosY()).equals(Optional.empty())
        	|| GlobalValues.immutableGrid.getAtIndex(GlobalValues.goal.getPosX(), GlobalValues.goal.getPosY()).equals(Optional.empty())
        ) {
        	log.error("Start or destination is not traversable. No solution possible...");
        	return;
        }
        
        GlobalValues.globalState.put(GlobalValues.start, new GridState<RoutePoint>(0,new LinkedList<>()));
        //GlobalValues.globalState.put(GlobalValues.goal, new GridState<RoutePoint>(-1,new LinkedList<>()));
        TreeSearch startSearch = new TreeSearch(GlobalValues.start);
        startSearch.start();
        
        while(!GlobalValues.isEnded.get()) {
        	Thread.sleep(100);
        }
        
        for (Entry<RoutePoint, TreeSearch> entry : GlobalValues.processingThreadMap.entrySet()) {
        	if (entry.getValue() != null)
        		entry.getValue().interrupt();	
        }

        synchronized(GlobalValues.globalState) {
        	GlobalValues.globalState.get(GlobalValues.goal).getBreadcrumbs().add(GlobalValues.goal);
        	GridState<RoutePoint> state =  GlobalValues.globalState.get(GlobalValues.goal);
        	MutableGrid output = GridFactory.create();
            output.markPath(state.getBreadcrumbs());
            output.displayGrid();
            log.info("Number of steps: " + state.getSteps());
        }
        
        long endTime = new Date().getTime();
        
        log.info("Total time taken in second(s): " + GlobalValues.frmtSeconds.print(new Duration(endTime-startTime).toPeriod()));
        
        System.exit(0);
    }
}
