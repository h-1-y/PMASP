package h1y.my.portfolio.exception;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import h1y.my.portfolio.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	// STATUS CODE : 400
	// Validation
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
		
		StringBuilder sb = new StringBuilder();
		
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			sb.append("(" + error.getField() + ") " + error.getDefaultMessage() + "\n");
		});
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(printLogAndReturn(400, "BAD_REQUEST", sb.toString()));
		
	}
	
	// STATUS CODE : 400
	// BadRequest
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponseDto> handleBadRequestExceptions(BadRequestException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(printLogAndReturn(400, "BAD_REQUEST", ex.getMessage()));
	}
	
	// STATUS CODE : 401
	// Unauthorized
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseEntity<ErrorResponseDto> handleUnauthorizedExceptions() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(printLogAndReturn(401, "UNAUTHORIZED", "사용자가 인증되지 않거나, 토큰이 유효하지 않음"));
	}
	
	// STATUS CODE : 403
	// Forbidden
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseEntity<ErrorResponseDto> handleForbiddenExceptions() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(printLogAndReturn(403, "FORBIDDEN", "접근 권한이 없음"));
	}
	
	// STATUS CODE : 404
	// Page Not Found
//	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleNotFoundExceptions(NoResourceFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(printLogAndReturn(404, "NOT_FOUND", ex.getMessage()));
	}
	
	// STATUS CODE : 405
	// Method Not Allowed
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorResponseDto> handleMethodNotAllowedExceptions(HttpRequestMethodNotSupportedException ex) {
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(printLogAndReturn(405, "METHOD_NOT_ALLOWED", ex.getMessage()));
	}
	
	// STATUS CODE : 500
	// Server Error
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponseDto> handleInternalServerErrorExceptions() {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(printLogAndReturn(500, "INTERNAL_SERVER_ERROR", "서버 에러"));
	}
	
	// STATUS CODE : 503
	// Service Unavailable
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public ResponseEntity<ErrorResponseDto> handleServiceUnavailableExceptions() {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(printLogAndReturn(503, "SERVICE_UNAVAILABLE", "서버 서비스 실패"));
	}
	
	// Global
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGlobalExceptions(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(printLogAndReturn(500, "INTERNAL_SERVER_ERROR", ex.getMessage()));
	}
	
	public ErrorResponseDto printLogAndReturn(int code, String errorName, String message) {
		
		ErrorResponseDto errorDto = new ErrorResponseDto(code, errorName, message);
		
		log.error("-------------------------------------------------------------------");
		log.error("| ■ ERROR > STATUS 	: " + code);
		log.error("| ■ ERROR > ERROR NAME   : " + errorName);
		log.error("| ■ ERROR > MESSAGE 	: " + message);
		log.error("-------------------------------------------------------------------");
		
		return errorDto;
		
	}
	
}
