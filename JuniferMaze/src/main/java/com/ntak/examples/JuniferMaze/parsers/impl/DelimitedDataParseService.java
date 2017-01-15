package com.ntak.examples.JuniferMaze.parsers.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ntak.examples.JuniferMaze.application.GlobalValues;
import com.ntak.examples.JuniferMaze.enums.HeaderEnum;
import com.ntak.examples.JuniferMaze.parsers.HeaderBean;

/**
 * Reads in input file with a specified delimiter and converts to a grid of beans T representing the input grid of fields.
 * 
 * @author akakshepati
 *
 */
public class DelimitedDataParseService {

	private static Logger log = LogManager.getLogger(DelimitedDataParseService.class);
	private static BufferedReader reader;
	private static Pattern pattern;
	
	/**
	 * Initialises internal BufferedReader and delimiter pattern (default delimiter " "). Then processes header as specified in HeaderEnum and outputs a Map of Type T which represents each header.
	 * 
	 * @param file - Grid file
	 * @param pattern - Delimiter pattern regular expression
	 * @param headerVals - Header values for a grid file
	 * @param <T> - HeaderBean definition interface
	 * 
	 * @return T Header information for grid file specified
	 */
	public static <T extends HeaderBean> T getHeader(File file, String pattern, T headerVals) {
		
		if (file == null || !file.exists() || !file.isFile()) {
			log.warn("File: " + file + " does not exist...");
			return null;
		}
		
		if (pattern != null && !pattern.trim().isEmpty())
			DelimitedDataParseService.pattern = Pattern.compile(pattern);
		else
			DelimitedDataParseService.pattern = GlobalValues.spaceDelPattern;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			
			for (HeaderEnum header : HeaderEnum.values()) {
				String line = reader.readLine();
				
				if (line == null) {
					throw new IOException("Malformed file. Could not parse headers. Check file format. Trying to reader header value: " + header);
				}
				
				String[] vals = DelimitedDataParseService.pattern.split(line);
				headerVals.setHeaderValue(header, vals);
			}
		} catch (FileNotFoundException e) {
			log.error("File was not found. See exception for details", e);
			log.info("Returning null...");
			return null;
		} catch (IOException e) {
			log.error("Issue reading file", e);
			return null;
		}

		return headerVals;
	}
	
	public static <T extends HeaderBean> T getHeader(String fileName, String pattern, T headerVals) {
		return getHeader(new File(fileName),pattern,headerVals);
	}
	
	/**
	 * 
	 * Reads contents of file in a line by line basis and splits each line by the delimiter. This is returned as a a matrix of String values.
	 * 
	 * @return List&lt;List&lt;String&gt;&gt; - The matrix of String values read from the main portion of the file (split by specified delimiter).
	 */
	public static List<List<String>> parseData() {

		List<List<String>> strGrid = new LinkedList<>();
		
		if (reader == null) {
			log.warn("Parser not initialised. Returning null...");
			return null;
		}
		
		try {
			String line = "";
			while ((line = reader.readLine()) != null) {
				String[] gridRowRaw = pattern.split(line);
				List<String> gridRow = Arrays.asList(gridRowRaw);
				strGrid.add(gridRow);
			}
		} catch (IOException ioe) {
			log.info("Issue processing file. See exception for details:", ioe);
		}
		try {
			close();
		} catch (Exception e) {
			log.debug("Failed to close the access to the file being read in an expected manner. See exception for details:", e);
		}
		return strGrid;
	}

	public static void close() throws Exception {
		if (reader != null) {
			reader.close();
			reader = null;
			pattern = null;
		}
		
	}
	
}
