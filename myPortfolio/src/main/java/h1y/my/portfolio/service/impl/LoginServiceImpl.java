package h1y.my.portfolio.service.impl;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import h1y.my.portfolio.config.jwt.JwtUtils;
import h1y.my.portfolio.dto.LoginRequestDto;
import h1y.my.portfolio.dto.MemberInfoDto;
import h1y.my.portfolio.entity.Member;
import h1y.my.portfolio.repository.MemberRepositoy;
import h1y.my.portfolio.service.LoginService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final MemberRepositoy memberRepositoy;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtils jwtUtils;
	
	@Override
	public String login(LoginRequestDto loginRequestDto) {
		
		String loginId = loginRequestDto.getLoginId();
		String password = loginRequestDto.getPassword();
		
		Member member = memberRepositoy.findByLoginId(loginId);
		
		if ( member == null ) throw new UsernameNotFoundException("아이디 또는 비밀번호가 잘못되었습니다.");
		if ( !passwordEncoder.matches(password, member.getPassword()) ) 
			throw new UsernameNotFoundException("아이디 또는 비밀번호가 잘못되었습니다.");
		
		MemberInfoDto infoDto = member.toDto();		
		String accessToken = jwtUtils.createAccessToken(infoDto);
		
		System.out.println("LoginServiceImpl accessToken ================= " + accessToken);
		
		return accessToken;
	}
	
}
