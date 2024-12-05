package h1y.my.portfolio.config.jwt;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import h1y.my.portfolio.common.dto.ErrorResponseDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAccessDenyHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) 
			throws IOException, ServletException {

		ErrorResponseDto errorResponseDto = new ErrorResponseDto(403, "FORBIDDEN", "접근 권한이 없음");
		ObjectMapper objectMapper = new ObjectMapper();
		
		String jsonErrorResponse = objectMapper.writeValueAsString(errorResponseDto);
		
		response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); // application/json
        response.getWriter().write(jsonErrorResponse);
		
	}
	
}
