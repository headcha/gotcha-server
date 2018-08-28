package com.seolgi.detector.camera.web.common.auth;

import com.seolgi.detector.domain.member.Member;
import com.seolgi.detector.domain.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AuthSecurityService implements UserDetailsService {

	@Autowired
	private MemberService memberService;
	
	@Override
	public UserDetails loadUserByUsername(final String id) throws UsernameNotFoundException {

		Member member = memberService.findOneByLoginId(id);
		if (member == null)
			throw new UsernameNotFoundException("아이디를 찾을 수 없습니다.");

		return createUser(member.getLoginId() , member.getPassword());
	}

	private User createUser(String id, String password) {
		Collection<SimpleGrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(Role.USER));

		return new User(id, password , roles);
	}

}
