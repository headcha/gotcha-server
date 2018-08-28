package com.seolgi.detector.domain.member;

import com.seolgi.detector.domain.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService extends BaseService<Member, Long> {

    private MemberRepository memberRepository;


    @Autowired
    public MemberService(MemberRepository repository) {
        super(repository);
        this.memberRepository = repository;
    }


    public boolean existById(String loginId) {
        Member one = findOneByLoginId(loginId);
        return one != null ? true : false;
    }

    @Transactional
    public void drop(String loginId) {
        Member member = findOneByLoginId(loginId);
        delete(member);
    }

    public long count() {
        return memberRepository.count();
    }

    public Member findOneByLoginId(String loginId) {
        return memberRepository.findOneByLoginId(loginId);
    }

    @Transactional
    public void updatePassword(long memberId, String encodePassword) {

        Member one = findOne(memberId);
        one.updatePassword(encodePassword);
    }

    public Member findOneBy(String email, String name) {
        return this.memberRepository.findOneByEmailAndName(email , name);
    }

    @Transactional
    public void updateLatestLoggedAtBy(String loginId) {
        Member member = findOneByLoginId(loginId);
        member.updateLatestLoggedAt();
    }
}
