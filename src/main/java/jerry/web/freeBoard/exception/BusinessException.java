package jerry.web.freeBoard.exception;

public class BusinessException extends RuntimeException{
	private final ExceptionCode exceptionCode;
	
	public BusinessException(ExceptionCode exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	
    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}
