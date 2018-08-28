package com.seolgi.detector.domain.member.auth.one_time.password;

import com.seolgi.detector.domain.base.BaseService;
import com.seolgi.detector.domain.exception.ApplicationException;
import com.seolgi.detector.domain.member.Member;
import com.seolgi.detector.domain.member.MemberRepository;
import com.seolgi.detector.domain.utils.notification.mail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthPasswordService extends BaseService<AuthPassword, Long> {

    private final AuthPasswordRepository authPasswordRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    public AuthPasswordService(AuthPasswordRepository repository) {
        super(repository);
        this.authPasswordRepository = repository;
    }

    @Transactional
    public void request(String memberLoginId , String memberName) {
        Member member = memberRepository.findOneByLoginIdAndName(memberLoginId , memberName);

        if (member == null)
            throw new ApplicationException("정보를 찾을 수 없습니다.");

        AuthPassword authPassword = AuthPassword.builder()
                .member(member)
                .build();

        save(authPassword);
        MailSender.sendPasswordReset(authPassword.getEmail() , authPassword.getAccessToken());
    }

    public AuthPassword findOneBy(String accessToken , String resetToken) {
        AuthPassword one = authPasswordRepository.findOneByAccessTokenAndResetToken(accessToken , resetToken);
        return one;
    }

    public AuthPassword findOneBy(String accessToken) {
        AuthPassword one = authPasswordRepository.findOneByAccessToken(accessToken);
        return one;
    }


    @Transactional
    public void access(long id) {
        AuthPassword one = findOne(id);
        one.access();

    }
}
