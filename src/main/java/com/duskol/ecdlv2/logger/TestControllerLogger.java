package com.duskol.ecdlv2.logger;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.JDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.duskol.ecdlv2.dto.TestDTO;
import com.duskol.ecdlv2.error.ErrorCodes;
import com.duskol.ecdlv2.exception.DataIntegrityException;
import com.duskol.ecdlv2.exception.InternalException;
import com.duskol.ecdlv2.exception.NotFoundException;
import com.duskol.ecdlv2.exception.ResourceNotFoundException;

@Aspect
@Component
public class TestControllerLogger {
	
	@Autowired
	private CommonLogger commonLogger;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestControllerLogger.class);
	private static final String MESSAGE_FORMAT_ERROR = "Method \"{}\" unsuccessfully finished. ERROR: {}";
	
	@Pointcut("execution(java.util.List<com.duskol.ecdlv2.dto.TestDTO> com.duskol.ecdlv2.controller.TestController.getAllTests())")
	public void getAllTestPointcut() { }
	
	@SuppressWarnings("unchecked")
	@Around("getAllTestPointcut()")
	public List<TestDTO> getTest(ProceedingJoinPoint jp) throws Throwable {

		List<TestDTO> result = null;
		
		try {
			result = (List<TestDTO>) jp.proceed();
			LOGGER.info("Result: " + result.toString());
		} catch (Exception e) {
			getInternalError(e, ErrorCodes.TESTS_CAN_NOT_BE_PROVIDED, commonLogger.getMethodName());
		}
		return result;
	}
	
	@Pointcut("execution(com.duskol.ecdlv2.dto.TestDTO com.duskol.ecdlv2.controller.TestController.getTest(Long)) "
			+ "&& args(id)")
	public void getTestPointcut(Long id) { }
	
	@Around("getTestPointcut(id)")
	public TestDTO getTest(ProceedingJoinPoint jp, Long id) throws Throwable {

		TestDTO result = null;
		
		try {
			result = (TestDTO) jp.proceed();
			LOGGER.info("Result: " + result.toString());
		} catch (ResourceNotFoundException e) {
			getNotFoundError(e, ErrorCodes.TEST_CAN_NOT_BE_PROVIDED, commonLogger.getMethodName());
		} catch (DataIntegrityViolationException e) {
			getDataIntegrityViolationError(e, ErrorCodes.TEST_CAN_NOT_BE_PROVIDED, commonLogger.getMethodName());
		} catch (DataAccessException e) {
			getDataAccessError(e, ErrorCodes.TEST_CAN_NOT_BE_PROVIDED, commonLogger.getMethodName());
		} catch (Exception e) {
			getInternalError(e, ErrorCodes.TEST_CAN_NOT_BE_PROVIDED, commonLogger.getMethodName());
		}
		return result;
	}
	
	/**
	 * 
	 * @param e
	 * @param errorCodes
	 * @param methodName
	 * @throws NotFoundException
	 */
	private void getNotFoundError(Exception e, ErrorCodes errorCodes, String methodName) throws NotFoundException {
		LOGGER.error(MESSAGE_FORMAT_ERROR, methodName, e.getMessage(),e);
		throw new NotFoundException(e.getMessage(), errorCodes);
	}
	
	/**
	 * 
	 * @param e
	 * @param errorCodes
	 * @param methodName
	 * @throws DataIntegrityException
	 */
	private void getDataIntegrityViolationError(DataIntegrityViolationException e, ErrorCodes errorCodes, String methodName) throws DataIntegrityException {
		generateError(e, errorCodes, methodName);    
	}
	
	/**
	 * 
	 * @param e
	 * @param errorCodes
	 * @param methodName
	 * @throws DataIntegrityException
	 */
	private void getDataAccessError(DataAccessException e, ErrorCodes errorCodes, String methodName) throws DataIntegrityException {
		generateError(e, errorCodes, methodName); 
	}
	
	/**
	 * 
	 * @param e
	 * @param errorCodes
	 * @param methodName
	 * @throws DataIntegrityException
	 */
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
	
	/**
	 * 
	 * @param e
	 * @param errorCodes
	 * @param methodName
	 * @throws InternalException
	 */
	private void getInternalError(Exception e, ErrorCodes errorCodes, String methodName) throws InternalException {
		LOGGER.error(MESSAGE_FORMAT_ERROR, methodName, e.getMessage(), e);
		throw new InternalException(e.getMessage(), errorCodes);
	}
}
