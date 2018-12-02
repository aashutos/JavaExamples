package com.interview.yoti.robot.annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.interview.yoti.robot.validators.Point2DArrayRangeValidator;
import com.interview.yoti.robot.validators.Point2DRangeValidator;

/**
 *  @author Aashutos Kakshepati
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = {Point2DRangeValidator.class, Point2DArrayRangeValidator.class})
public @interface Point2DRangeCheck {

	int min() default 0;
	int max() default Integer.MAX_VALUE;
	boolean strict() default false;

	/**
	 * @return the error message template
	 */
	String message() default "{javax.validation.constraints.Point2DRangeCheck.message}";

	Class<?>[] groups() default { };
	
	Class<? extends Payload>[] payload() default { };
}
