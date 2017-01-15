package com.ntak.examples.JuniferMaze.utils;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class contains static methods used with Apache Commons CLI to build command line options passed into the system.
 * 
 * @see org.apache.commons.cli
 * 
 * @author akakshepati
 *
 */
public class CLIUtils {

	private static Logger log = LogManager.getLogger(CLIUtils.class);
	
	/**
	 * Builds options for the system and returns the set.
	 * 
	 * @return Options - Encapsulates the set of Option classes which represent individual command line parameters for this system.
	 */
	public static Options buildOptions() {
		Options opts = new Options();
		
		Option fnameOpts = 		Option.builder("f")
								.longOpt("filename")
								.hasArg()
								.numberOfArgs(1)
								.desc("Text file location, which holds information regarding the grid that is to be solved.")
								.required()
								.build();
		opts.addOption(fnameOpts);
		
		Option custDelOpts = 	Option.builder("d")
								.longOpt("delimiter")
								.hasArg()
								.numberOfArgs(1)
								.desc("The delimiter used within the file specified. If not specified the default space character will be used")
								.required(false)
								.build();
		opts.addOption(custDelOpts);
		
		return opts;
	}
	
	/**
	 * Parses the command line options passed in by the user and encapsulates the information in a CommandLine structure for use.
	 * 
	 * @param args - Parameters passed in to the main method of the program.
	 * @return CommandLine - Encapsulates parameter options and values attributed to them.
	 */
	public static CommandLine parseCLIOptions(String[] args) {
		
		CommandLineParser cliParser = new DefaultParser();
		
		try {
			return cliParser.parse(CLIUtils.buildOptions(), args);
		} catch (ParseException e) {
			log.error("Could not parse command line arguments. See exception for details: ",e);
			log.info("Returning null...");
		}
		return null;
	}
}
