package com.seolgi.detector.domain.member.auth.one_time;

import com.seolgi.detector.domain.base.BaseEntity;
import com.seolgi.detector.domain.member.Member;
import com.seolgi.detector.domain.utils.UniqueUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "auth_one_time")
@Getter
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
public class AuthOneTime extends BaseEntity {

    private static final int EXPIRE_MINUTE = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date tokenExpiredAt;

    @Column
    private boolean used;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false)
    private Date usedAt;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "memberId" , nullable = false)
    private Member member;

    @Builder
    public AuthOneTime(Member member) {
        this.token = UniqueUtil.generate();
        this.tokenExpiredAt = DateUtils.addMinutes(new Date() , EXPIRE_MINUTE);
        this.used = false;
        this.member = member;
    }

    public void used() {
        this.used = true;
        this.usedAt = new Date();
    }
}
