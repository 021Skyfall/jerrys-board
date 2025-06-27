package jerry.web.freeBoard.exception;

public enum ExceptionCode {
    GENERAL_EXCEPTION(500, "er1", "에러 발생");
	
    private final int status;
    private final String code;
    private final String message;

    ExceptionCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
