package h1y.my.portfolio.config.jwt;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import h1y.my.portfolio.common.dto.ErrorResponseDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(400, "BAD_REQUEST", "토큰이 유효하지 않습니다.");
		ObjectMapper objectMapper = new ObjectMapper();
		
		String jsonErrorResponse = objectMapper.writeValueAsString(errorResponseDto);
		
		response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); // application/json
        response.getWriter().write(jsonErrorResponse);
		
	}
	
}
