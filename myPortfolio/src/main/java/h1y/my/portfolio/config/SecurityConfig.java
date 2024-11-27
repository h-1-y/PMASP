package h1y.my.portfolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import h1y.my.portfolio.config.jwt.CustomUserDetailsService;
import h1y.my.portfolio.config.jwt.JwtAccessDenyHandler;
import h1y.my.portfolio.config.jwt.JwtAuthFilter;
import h1y.my.portfolio.config.jwt.JwtAuthenticationEntryPoint;
import h1y.my.portfolio.config.jwt.JwtUtils;
import io.jsonwebtoken.io.Decoders;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig {

	private final CustomUserDetailsService customUserDetailsService;
	private final JwtUtils jwtUtils;
	
	private static final String [] AUTH_WHITE_LIST = { 
					  "/api/v1/member/**", "/api/v1/auth/**", "/swagger-ui/**" 
					, "/swagger-ui-custom.html", "/swagger-ui.html", "/api-docs/**"
					, "/api-docs", "/v3/api-docs/**"
				};
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		// CSRF, CORS
		http.csrf((csrf) -> csrf.disable()); // CSRF 비활성화
		http.cors(Customizer.withDefaults());
		
		// 세션 관리 상태 없음으로 구성 , Spring security가 세션생성이나 사용 X
		http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
					SessionCreationPolicy.STATELESS
				));
		
		// FormLogin, BasicHttp 비활성화
		http.formLogin((form) -> form.disable());
		http.httpBasic((basic) -> basic.disable());
		
		// JwtAuthFilter를 UsernamePasswordAuthenticationFilter 앞에 추가 
		http.addFilterBefore(new JwtAuthFilter(customUserDetailsService, jwtUtils), UsernamePasswordAuthenticationFilter.class);
		
		http.exceptionHandling(
				exceptionHandling -> exceptionHandling
									.authenticationEntryPoint(new JwtAuthenticationEntryPoint())
									.accessDeniedHandler(new JwtAccessDenyHandler()) // 권한이 없는 경우
		);
		
		http.authorizeHttpRequests(
				authorize -> authorize
							.requestMatchers(AUTH_WHITE_LIST).permitAll() // 특정 경로는 인증 없이 허용
//							.requestMatchers("/admin/**").hasAuthority("ADMIN")
							.anyRequest().authenticated() // 나머지는 인증 필요
		);
		
		return http.build();
		
//        http.csrf().disable() // CSRF 비활성화
//            .authorizeRequests()
//            .requestMatchers("/api/v1/members/**").permitAll() // 특정 경로는 인증 없이 허용
//            .anyRequest().authenticated() // 나머지는 인증 필요
//            .and()
//            .formLogin().disable() // 기본 로그인 폼 비활성화
//            .httpBasic().disable(); // HTTP Basic 인증 비활성화
//        return http.build();
        
    }
	
}
