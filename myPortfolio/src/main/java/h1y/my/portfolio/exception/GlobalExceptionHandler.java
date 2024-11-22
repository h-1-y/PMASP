package h1y.my.portfolio.exception;

import java.util.HashMap;
import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	// Global
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGlobalExceptions(Exception ex) {
		printLog(500, "INTERNAL_SERVER_ERROR", ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponseDto(500, "INTERNAL_SERVER_ERROR", ex.getMessage()));
	}
	
	// STATUS CODE : 400
	// Validation
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		
		Map<String, String> errors = new HashMap<>();
		
		ex.getBindingResult().getFieldErrors().forEach(error -> {
		
			System.out.println("error.getField() ====== " + error.getField());
			System.out.println("error.getDefaultMessage() ======= " + error.getDefaultMessage());
			
			errors.put(error.getField(), error.getDefaultMessage());
			
		});
		
		return ResponseEntity.badRequest().body(errors);
		
	}
	
	// STATUS CODE : 400
	// BadRequest
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponseDto> handleBadRequestExceptions(BadRequestException ex) {
		printLog(400, "BAD_REQUEST", ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(400, "BAD_REQUEST", ex.getMessage()));
	}
	
	// STATUS CODE : 401
	// Unauthorized
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseEntity<ErrorResponseDto> handleUnauthorizedExceptions() {
		printLog(401, "UNAUTHORIZED", "사용자가 인증되지 않거나, 토큰이 유효하지 않음");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDto(401, "UNAUTHORIZED", "사용자가 인증되지 않거나, 토큰이 유효하지 않음"));
	}
	
	// STATUS CODE : 403
	// Forbidden
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseEntity<ErrorResponseDto> handleForbiddenExceptions() {
		printLog(403, "FORBIDDEN", "접근 권한이 없음");
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponseDto(403, "FORBIDDEN", "접근 권한이 없음"));
	}
	
	// STATUS CODE : 404
	// Page Not Found
//	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleNotFoundExceptions(NoResourceFoundException ex) {
		printLog(404, "NOT_FOUND", ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(404, "NOT_FOUND", ex.getMessage()));
	}
	
	// STATUS CODE : 405
	// Method Not Allowed
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public ResponseEntity<ErrorResponseDto> handleMethodNotAllowedExceptions() {
		printLog(405, "METHOD_NOT_ALLOWED", "지원되지 않는 HTTP 메서드");
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ErrorResponseDto(405, "METHOD_NOT_ALLOWED", "지원되지 않는 HTTP 메서드"));
	}
	
	// STATUS CODE : 500
	// Server Error
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponseDto> handleInternalServerErrorExceptions() {
		printLog(500, "INTERNAL_SERVER_ERROR", "서버 에러");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponseDto(500, "INTERNAL_SERVER_ERROR", "서버 에러"));
	}
	
	// STATUS CODE : 503
	// Service Unavailable
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public ResponseEntity<ErrorResponseDto> handleServiceUnavailableExceptions() {
		printLog(503, "SERVICE_UNAVAILABLE", "서버 서비스 실패");
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ErrorResponseDto(503, "SERVICE_UNAVAILABLE", "서버 서비스 실패"));
	}
	
	public void printLog(int code, String errorName, String message) {
		
		log.error("■ ERROR > STATUS 	: " + code);
		log.error("■ ERROR > ERROR NAME   : " + errorName);
		log.error("■ ERROR > MESSAGE 	: " + message);
		
	}
	
}
