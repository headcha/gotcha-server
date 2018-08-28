package com.seolgi.detector.domain.member.auth.one_time.password;

import com.seolgi.detector.domain.base.BaseEntity;
import com.seolgi.detector.domain.member.Member;
import com.seolgi.detector.domain.utils.UniqueUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "auth_one_time_password")
@Getter
@NoArgsConstructor
public class AuthPassword extends BaseEntity {
    private static final int ACCESS_EXPIRE_MINUTE = 30;
    private static final int CONFIRM_EXPIRE_MINUTE = 20;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 200 , nullable = false)
    private String email;

    @Column(nullable = false , unique = true)
    private String accessToken;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date accessExpiredAt;

    @Column(unique = true)
    private String resetToken;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false)
    private Date resetExpiredAt;

    @Column
    private boolean access;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false)
    private Date accessAt;

    @Column
    private boolean reset;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false)
    private Date resetAt;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "memberId")
    private Member member;

    @Builder
    public AuthPassword(Member member) {
        this.email = member.getEmail();
        this.accessToken = UniqueUtil.generate();
        this.accessExpiredAt = DateUtils.addMinutes(new Date() , ACCESS_EXPIRE_MINUTE);
        this.reset = false;
        this.access = false;
        this.member = member;
    }

    private boolean isExpiredAccess() {
        return accessExpiredAt.before(new Date());
    }

    private boolean isExpiredReset() {
        return resetExpiredAt.before(new Date());
    }

    public void access() {
        this.access = true;
        this.accessAt = new Date();
        this.resetToken = UniqueUtil.generate();
        this.resetExpiredAt = DateUtils.addMinutes(new Date() , CONFIRM_EXPIRE_MINUTE);
    }

    public void reset() {
        this.reset = true;
        this.resetAt = new Date();
    }

    public boolean isAccessNotValid() {
        return isExpiredAccess() || isAccess();
    }

    public boolean isResetNotValid() {
        return  isExpiredReset() || isReset();
    }
}
