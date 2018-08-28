package com.seolgi.detector.domain.member.auth.one_time;

import com.seolgi.detector.domain.base.BaseRepository;

public interface AuthOneTimeRepository extends BaseRepository<AuthOneTime, Long> {
    AuthOneTime findOneByToken(String token);
}
