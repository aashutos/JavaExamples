package com.interview.yoti.robot;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.interview.yoti.robot.operations.Point2DOperationTest;
import com.interview.yoti.robot.validators.HooverSimRequestValidatorTest;
import com.interview.yoti.robot.validators.Point2DArrayRangeValidatorTest;
import com.interview.yoti.robot.validators.Point2DRangeValidatorTest;

/**
 *  @author Aashutos Kakshepati
 */
@RunWith(Suite.class)
@SuiteClasses({	HooverSimRequestValidatorTest.class, 
				Point2DArrayRangeValidatorTest.class, 
				Point2DRangeValidatorTest.class, 
				Point2DOperationTest.class
})
public class TestSuite {

}
