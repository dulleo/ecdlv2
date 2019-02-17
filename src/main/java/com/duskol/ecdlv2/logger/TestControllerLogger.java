package com.duskol.ecdlv2.logger;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Aspect
@Component
public class TestControllerLogger {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestControllerLogger.class);
	private static String METHOD_NAME;
	private static final String MESSAGE_FORMAT_START = "Method \"{}\" has been started";
	
	@Before("@annotation(org.springframework.web.bind.annotation.RequestMapping) && @annotation(mapping)")
	public void advice(JoinPoint jp, RequestMapping mapping) {
		METHOD_NAME = jp.getSignature().getName();
		LOGGER.info(MESSAGE_FORMAT_START, METHOD_NAME);
		LOGGER.info("Method Type: {}", mapping.method()[0]);
		LOGGER.info("URL: {}", mapping.value()[0]);
		LOGGER.info("Args: {}",Arrays.toString(jp.getArgs()));
	}

}
