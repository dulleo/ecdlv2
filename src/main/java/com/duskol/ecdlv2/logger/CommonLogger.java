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
public class CommonLogger {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(CommonLogger.class);
	private String methodName;
	
	@Before("@annotation(org.springframework.web.bind.annotation.RequestMapping) && @annotation(mapping)")
	public void advice(JoinPoint jp, RequestMapping mapping) {
		methodName = jp.getSignature().getName();
		LOGGER.info("[CONTROLLER]: {}", jp.getSignature().getDeclaringType());
		LOGGER.info("[METHOD]: {}", this.methodName);
		LOGGER.info("[METHOD TYPE]: {}", mapping.method()[0]);
		LOGGER.info("[URL]: {}", mapping.value()[0]);
		if(jp.getArgs().length != 0) {
			LOGGER.info("[Args]: {}",Arrays.toString(jp.getArgs()));
		}
	}

	public String getMethodName() {
		return methodName;
	}	
}
