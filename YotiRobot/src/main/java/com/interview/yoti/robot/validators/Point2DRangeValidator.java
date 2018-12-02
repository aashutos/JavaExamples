package com.interview.yoti.robot.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.interview.yoti.robot.annotations.Point2DRangeCheck;
import com.interview.yoti.robot.model.Point2D;
import static com.interview.yoti.robot.utils.Constants.EXC_ANN_P2D_RANGE;

/**
 *  @author Aashutos Kakshepati
 */
public class Point2DRangeValidator implements ConstraintValidator<Point2DRangeCheck, Point2D>{

	private int minimum;
	private int maximum;
	private boolean strict;
	
	@Override
	public void initialize(Point2DRangeCheck constraintAnnotation) {
		minimum = constraintAnnotation.min();
		maximum = constraintAnnotation.max();
		strict = constraintAnnotation.strict();
	}

	@Override
	public boolean isValid(Point2D value, ConstraintValidatorContext context) {
		if (!validatePoint2D(value)) {
			context.buildConstraintViolationWithTemplate(String.format(EXC_ANN_P2D_RANGE, value, minimum, maximum));
			return false;
		}
		return true;
	}

	public boolean validatePoint2D(Point2D value) {
		if (value == null && strict)
			return false;
		
		if (value == null && !strict)
			return true;
		
		if (singleDirectionValidation(true,value,minimum,maximum) && singleDirectionValidation(false,value,minimum,maximum)) {
			return true;
		}
		
		return false;
	}

	public static boolean singleDirectionValidation(boolean wantXCheck, Point2D point, int min, int max) {		
		if (wantXCheck) {
			if (point.getX() >= min && point.getX() <= max) {
				return true;
			} else {
				return false;
			}
		} else {
			if (point.getY() >= min && point.getY() <= max) {
				return true;
			} else {
				return false;
			}		
		}
	}
	
	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	public boolean isStrict() {
		return strict;
	}

	public void setStrict(boolean strict) {
		this.strict = strict;
	}
}
