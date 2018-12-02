package com.interview.yoti.robot.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.interview.yoti.robot.model.Point2D;
import com.interview.yoti.robot.model.payloads.HooverRequestPayload;

/**
 *  Performs Semantic Spring Validation on the Hoover Simulation Request Object. 
 * 
 *  @author Aashutos Kakshepati
 */
public class HooverSimRequestValidator implements Validator {

	private static final String ERRO1 = "ERRO1";

	@Override
	public boolean supports(Class<?> clazz) {
		return HooverRequestPayload.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		HooverRequestPayload payload = (HooverRequestPayload) target;
		
		int maxX = payload.getDimensions().getX()-1;
		int maxY = payload.getDimensions().getY()-1;
		
		boolean res = true;
		
		res &= Point2DRangeValidator.singleDirectionValidation(true, payload.getHooverPosition(), 0, maxX);
		res &= Point2DRangeValidator.singleDirectionValidation(false, payload.getHooverPosition(), 0, maxY);
		if (!res) {
			errors.reject(ERRO1, new Object[]{payload.getHooverPosition()}, "Hoover Position was found to be out of bounds of the dimension of the room.");
			return;
		}
		
		for (Point2D patch : payload.getDirtPatches()) {
			res &= Point2DRangeValidator.singleDirectionValidation(true, patch, 0, maxX);
			res &= Point2DRangeValidator.singleDirectionValidation(false, patch, 0, maxY);
			if (!res) {
				errors.reject(ERRO1, new Object[]{patch}, "Dirt patch was found to be out of bounds of the dimension of the room.");
				return;
			}
		}
	}


	
}
