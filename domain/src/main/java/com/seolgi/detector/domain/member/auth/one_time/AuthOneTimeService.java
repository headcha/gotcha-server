package com.seolgi.detector.domain.member.auth.one_time;

import com.seolgi.detector.domain.base.BaseService;
import com.seolgi.detector.domain.exception.ApplicationException;
import com.seolgi.detector.domain.member.Member;
import com.seolgi.detector.domain.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthOneTimeService extends BaseService<AuthOneTime, Long> {

    private final AuthOneTimeRepository authOneTimeRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    public AuthOneTimeService(AuthOneTimeRepository repository) {
        super(repository);
        this.authOneTimeRepository = repository;
    }

    @Transactional
    public AuthOneTime create(long memberId) {
        Member member = memberRepository.findOne(memberId);

        if (member == null)
            throw new ApplicationException("정보를 찾을 수 없습니다.");

        AuthOneTime authOneTime = AuthOneTime.builder()
                .member(member)
                .build();

        return save(authOneTime);
    }

    public AuthOneTime findOneBy(String accessToken) {
        return authOneTimeRepository.findOneByToken(accessToken);
    }


    @Transactional
    public void used(String token) {
        AuthOneTime one = findOneBy(token);
        one.used();
    }
}
