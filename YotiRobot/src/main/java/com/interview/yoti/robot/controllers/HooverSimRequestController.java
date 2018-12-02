package com.interview.yoti.robot.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.interview.yoti.robot.model.payloads.HooverRequestPayload;
import com.interview.yoti.robot.model.payloads.HooverResponsePayload;
import com.interview.yoti.robot.operations.HooverOperation;

/**
 *  Hoover Simulation REST Endpoint.
 * 
 *  @author Aashutos Kakshepati
 */
@RestController
@RequestMapping(path="/hoover")
public class HooverSimRequestController {

	private static final Logger LOG = LoggerFactory.getLogger(HooverSimRequestController.class);
	@Autowired
	private Gson gson;
	protected Validator requestValidator;
	
	@Transactional
	@RequestMapping(path="/request-sim", method=RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> requestHooverSimulation(@RequestBody @Valid HooverRequestPayload payload, Errors errors) {
		long requestTimestamp = new Date().getTime();
				
		requestValidator.validate(payload, errors);
		if (errors.hasErrors())
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors.getAllErrors());
		
		LOG.info(String.format("Request Received: %s", payload));
		HooverResponsePayload response = HooverOperation.executeSimulation(payload,requestTimestamp);
		LOG.info(String.format("Response Generated: %s", response));
		
		return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(response));
	}	
	
	@Autowired	
	public void setRequestValidators(@Qualifier("hooverValidator") Validator hooverValidator) {
		this.requestValidator = hooverValidator;
	}
}
