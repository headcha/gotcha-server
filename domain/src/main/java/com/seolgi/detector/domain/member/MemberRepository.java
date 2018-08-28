package com.seolgi.detector.domain.member;


import com.seolgi.detector.domain.base.BaseRepository;

public interface MemberRepository extends BaseRepository<Member, Long> {
    Member findOneByLoginId(String loginId);

    Member findOneByLoginIdAndName(String loginId, String name);

    Member findOneByEmailAndName(String email, String name);

}
