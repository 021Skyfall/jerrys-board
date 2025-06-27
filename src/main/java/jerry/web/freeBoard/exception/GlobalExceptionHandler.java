package jerry.web.freeBoard.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
	String errorCode;
	String errorMessage;
	HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleAllException(Exception e) {
    	errorCode = e.getClass().getSimpleName();
    	errorMessage = e.getMessage();
    	
    	ErrorResponse errorResponse = new ErrorResponse(
    			errorCode,
    			errorMessage,
    			LocalDateTime.now()
    	);
    	
    	return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleCustomException(BusinessException e) {
    	errorCode = e.getExceptionCode().getCode();
    	errorMessage = e.getExceptionCode().getMessage();
    	
    	ErrorResponse errorResponse = new ErrorResponse(
    			errorCode,
    			errorMessage,
    			LocalDateTime.now()
    	);
    	
    	return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
