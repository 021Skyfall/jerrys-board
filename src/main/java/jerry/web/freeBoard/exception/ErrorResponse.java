package jerry.web.freeBoard.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String code;
    private String message;
    private LocalDateTime occurAt;
    
	public ErrorResponse(String code, String message, LocalDateTime occurAt) {
		super();
		this.code = code;
		this.message = message;
		this.occurAt = occurAt;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getOccurAt() {
		return occurAt;
	}
	public void setOccurAt(LocalDateTime occurAt) {
		this.occurAt = occurAt;
	}
}
