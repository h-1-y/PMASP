package h1y.my.portfolio.config.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import h1y.my.portfolio.dto.MemberInfoDto;
import h1y.my.portfolio.entity.Member;
import h1y.my.portfolio.repository.MemberRepositoy;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberRepositoy memberRepositoy;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		
		Member member = memberRepositoy.findById(Long.parseLong(id))
				.orElseThrow(() -> new IllegalArgumentException("회원 객체 없음"));

		MemberInfoDto infoDto = member.toDto();
		
		return new CustomUserDetails(infoDto);
		
	}
	
}
