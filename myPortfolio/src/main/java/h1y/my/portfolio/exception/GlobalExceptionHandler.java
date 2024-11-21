package h1y.my.portfolio.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import h1y.my.portfolio.dto.ErrorResponseDto;
import h1y.my.portfolio.dto.ResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorResponseDto> handleExceptions(Exception ex) {
		
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(0, ex.getMessage());
		System.out.println("asdasdasdasdasdasdasd");
		ResponseDto.of(false, 0, "error test");
		
		return ResponseEntity.badRequest().body(errorResponseDto);
		
	}
	
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
	
}
