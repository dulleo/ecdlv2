package com.duskol.ecdlv2.logger;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.JDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duskol.ecdlv2.dto.QuestionDTO;
import com.duskol.ecdlv2.error.ErrorCodes;
import com.duskol.ecdlv2.exception.DataIntegrityException;
import com.duskol.ecdlv2.exception.InternalException;
import com.duskol.ecdlv2.exception.NotFoundException;
import com.duskol.ecdlv2.exception.ResourceNotFoundException;


@Aspect
@Component
public class QuestionControllerLogger {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(QuestionControllerLogger.class);
	
	//private static final String MESSAGE_FORMAT_START = "Method \"{}\" has been started";
	//private static final String MESSAGE_FORMAT_FINISH = "Method \"{}\" successfully finished.......";
	private static final String MESSAGE_FORMAT_ERROR = "Method \"{}\" unsuccessfully finished. ERROR: {}";
	//private static String METHOD_NAME;
	/*
	 * @Before("@annotation(org.springframework.web.bind.annotation.RequestMapping) && @annotation(mapping)"
	 * ) public void advice(JoinPoint jp, RequestMapping mapping) { METHOD_NAME =
	 * jp.getSignature().getName(); LOGGER.info(MESSAGE_FORMAT_START, METHOD_NAME);
	 * LOGGER.info("Method Type: {}", mapping.method()[0]); LOGGER.info("URL: {}",
	 * mapping.value()[0]); LOGGER.info("Args: {}",Arrays.toString(jp.getArgs())); }
	 */
	
	@Pointcut("execution(void com.duskol.ecdlv2.controller.QuestionController.createQuestion(Long, com.duskol.ecdlv2.dto.QuestionDTO)) "
			+ "&& args(testId, questionDTO)")
	public void createQuestionPointcut(Long testId, QuestionDTO questionDTO) {
		//
	}
	
	@Around("createQuestionPointcut(testId, questionDTO)")
	public void createQuestion(ProceedingJoinPoint jp, Long testId, QuestionDTO questionDTO) throws Throwable {
		
		try {
			jp.proceed();
			//LOGGER.info(MESSAGE_FORMAT_FINISH, METHOD_NAME);
		} catch (ResourceNotFoundException e) {
			getNotFoundError(e, ErrorCodes.QUESTION_CAN_NOT_BE_CREATED, jp.getSignature().getName());
		} catch (DataIntegrityViolationException e) {
			getDataIntegrityViolationError(e, ErrorCodes.QUESTION_CAN_NOT_BE_CREATED, jp.getSignature().getName());
		} catch (DataAccessException e) {
			getDataAccessError(e, ErrorCodes.QUESTION_CAN_NOT_BE_CREATED, jp.getSignature().getName());
		} catch (Exception e) {
			getInternalError(e, ErrorCodes.QUESTION_CAN_NOT_BE_CREATED, jp.getSignature().getName());
		}
	}
	
	private void getNotFoundError(Exception e, ErrorCodes errorCodes, String methodName) throws NotFoundException {
		LOGGER.error(MESSAGE_FORMAT_ERROR, methodName, e.getMessage(),e);
		throw new NotFoundException(e.getMessage(), errorCodes);
	}
	
	private void getDataIntegrityViolationError(DataIntegrityViolationException e, ErrorCodes errorCodes, String methodName) throws DataIntegrityException {
		generateError(e, errorCodes, methodName);    
	}
	
	private void getDataAccessError(DataAccessException e, ErrorCodes errorCodes, String methodName) throws DataIntegrityException {
		generateError(e, errorCodes, methodName); 
	}
	
	private void generateError(Exception e, ErrorCodes errorCodes, String methodName) throws DataIntegrityException {
		if(e.getCause() instanceof JDBCException && ((JDBCException)e.getCause()).getSQLException() != null)
        {                    
			String message = ((JDBCException)e.getCause()).getSQLException().getMessage();
			LOGGER.error(MESSAGE_FORMAT_ERROR,methodName,message,e);
            throw new DataIntegrityException(message, errorCodes);
        } else {
        	LOGGER.error(MESSAGE_FORMAT_ERROR,methodName,e.getMessage(), e);
            throw new DataIntegrityException(e.getMessage(), errorCodes); 
        }
	}
	
	private void getInternalError(Exception e, ErrorCodes errorCodes, String methodName) throws InternalException {
		LOGGER.error(MESSAGE_FORMAT_ERROR, methodName, e.getMessage(), e);
		throw new InternalException(e.getMessage(), errorCodes);
	}

}
