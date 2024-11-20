package h1y.my.portfolio.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

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
