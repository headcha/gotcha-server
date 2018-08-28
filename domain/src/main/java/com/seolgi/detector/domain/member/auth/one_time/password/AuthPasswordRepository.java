package com.seolgi.detector.domain.member.auth.one_time.password;

import com.seolgi.detector.domain.base.BaseRepository;

public interface AuthPasswordRepository extends BaseRepository<AuthPassword, Long> {

    AuthPassword findOneByAccessToken(String token);

    AuthPassword findOneByAccessTokenAndResetToken(String accessToken, String resetToken);
}
