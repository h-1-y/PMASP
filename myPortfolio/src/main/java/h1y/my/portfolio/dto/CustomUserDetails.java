package h1y.my.portfolio.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

	private final MemberInfoDto memberInfoDto;
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
	
    @Override
    public String getPassword() {
        return memberInfoDto.getPassword();
    }

    @Override
    public String getUsername() {
        return memberInfoDto.getLoginId().toString();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
	
	@Override
    public boolean isEnabled() {
        return true;
    }
	
}
