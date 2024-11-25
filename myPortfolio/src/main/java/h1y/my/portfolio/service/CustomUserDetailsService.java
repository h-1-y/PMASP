package h1y.my.portfolio.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import h1y.my.portfolio.dto.CustomUserDetails;
import h1y.my.portfolio.dto.MemberInfoDto;
import h1y.my.portfolio.entity.Member;
import h1y.my.portfolio.repository.MemberJpaRepositoy;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberJpaRepositoy memberJpaRepositoy;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		
		Member member = memberJpaRepositoy.findById(Long.parseLong(id)).get();
		
		if ( member == null ) throw new UsernameNotFoundException("잘못된 접근입니다.");

		MemberInfoDto infoDto = member.toDto();
		
		return new CustomUserDetails(infoDto);
	}
	
}
