package com.interview.yoti.robot;

import java.text.DateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.interview.yoti.robot.adapters.Point2DAdapter;
import com.interview.yoti.robot.model.Point2D;
import com.interview.yoti.robot.validators.HooverSimRequestValidator;

/**
 * Configuration of Spring Beans will be initiated within this class.
 * 
 * @author Aashutos Kakshepati
 */
@EnableWebMvc
@EnableTransactionManagement
@Configuration
@ComponentScan({ "com.interview.yoti.*" })
public class MainConfiguration {
	
	private Gson generateGsonParser() {
		return new GsonBuilder().registerTypeAdapter(Point2D.class, new Point2DAdapter())
				.enableComplexMapKeySerialization().serializeNulls().setDateFormat(DateFormat.LONG)
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).setPrettyPrinting().setVersion(1.0).create();
	}

	@Bean(name = "gsonHttpMessageConverter")
	public GsonHttpMessageConverter gsonHttpMessageConverter() {
		GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
		converter.setGson(generateGsonParser());
		return converter;
	}
	
	@Bean(name = "hooverValidator")
	public Validator hooverValidator() {
		return new HooverSimRequestValidator();
	}
}
