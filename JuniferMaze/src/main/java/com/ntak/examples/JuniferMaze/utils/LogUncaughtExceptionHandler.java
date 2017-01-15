package com.ntak.examples.JuniferMaze.utils;

import java.lang.Thread.UncaughtExceptionHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Concrete implementation of UncaughtExceptionHandler, which logs thread uncaught exception output using the log4j package.
 * 
 * @see java.lang.Thread.UncaughtExceptionHandler
 * 
 * @author akakshepati
 *
 */
public class LogUncaughtExceptionHandler implements UncaughtExceptionHandler {

	private static Logger log = LogManager.getLogger(LogUncaughtExceptionHandler.class);
	
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		log.error("Thread: " + t.getName() + ". Exception occured: " + e.getMessage());
		log.info("Exception details: ",e);
	}

}
