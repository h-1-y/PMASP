package h1y.my.portfolio.config.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import h1y.my.portfolio.common.dto.ErrorResponseDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

	private final CustomUserDetailsService customUserDetailsService;
	private final JwtUtils jwtUtils;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorizationHeader = request.getHeader("Authorization");
		
		System.out.println("authorizationHeader ============ " + authorizationHeader);
		
		// JWT가 헤더에 있는 경우
		if ( authorizationHeader != null && authorizationHeader.startsWith("Bearer ") ) {
			
			String token = authorizationHeader.substring(7);
			System.out.println("token ================= " + token);
			
			// JWT 유효성 검증
			if ( jwtUtils.validateToken(token) ) {
				
				Long id = jwtUtils.getMemberId(token);
				
				System.out.println("Long id = jwtUtils.getMemberId(token); ===== " + id);
				
				// 유저와 토큰 일치시 userDetails 생성
				UserDetails userDetails = customUserDetailsService.loadUserByUsername(id.toString());
				
				if ( userDetails != null ) {
					
					// UserDetails, Password, Role -> 접근권한 인증 Token 생성
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
							new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					
					// 현재 Request의 Security에 접근권한 설정
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					
				}
				
			} else {
				
				System.out.println("토큰이 유효하지 않음 !!!!!!!!!!!!!!!!!!!!!!!!!");
				
				// 에러 응답 DTO 생성
				ErrorResponseDto errorDto = new ErrorResponseDto(401, "UNAUTHORIZED", "사용자가 인증되지 않거나, 토큰이 유효하지 않음");
				
				// 상태 코드와 에러 메시지를 JSON 형식으로 응답 본문에 설정
				response.setCharacterEncoding("UTF-8");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 401 상태 코드
				response.setContentType("application/json");  // 응답 타입: JSON
				response.getWriter().write(new ObjectMapper().writeValueAsString(errorDto));  // JSON 형식으로 응답 본문 작성
				return;
				
			}
			
		}
		
		filterChain.doFilter(request, response);
		
	}
	
}
