package com.interview.yoti.robot.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.interview.yoti.robot.annotations.Point2DRangeCheck;
import com.interview.yoti.robot.model.Point2D;
import static com.interview.yoti.robot.utils.Constants.EXC_ANN_P2D_RANGE;

import java.util.Arrays;

/**
 *  @author Aashutos Kakshepati
 */
public class Point2DArrayRangeValidator implements ConstraintValidator<Point2DRangeCheck, Point2D[]>{

	private int minimum;
	private int maximum;
	private boolean strict;
	
	private Point2DRangeValidator p2dValidator;
	
	@Override
	public void initialize(Point2DRangeCheck constraintAnnotation) {
		minimum = constraintAnnotation.min();
		maximum = constraintAnnotation.max();
		strict = constraintAnnotation.strict();
		p2dValidator = new Point2DRangeValidator();
		p2dValidator.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(Point2D[] values, ConstraintValidatorContext context) {
		if (values == null && strict) {
			context.buildConstraintViolationWithTemplate(String.format(EXC_ANN_P2D_RANGE, "[null]", minimum, maximum));
			return false;
		}
		if (values == null && !strict)
			return true;
		
		for (Point2D value : values) {
			if (!p2dValidator.isValid(value, context)) {
				context.buildConstraintViolationWithTemplate(String.format(EXC_ANN_P2D_RANGE, Arrays.asList(values).toString(), minimum, maximum));
				return false;
			}
		}
		return true;
	}

}
