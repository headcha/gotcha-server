package com.seolgi.detector.domain.member;

import com.seolgi.detector.domain.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@Entity(name = "member")
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(length = 100 , nullable = false)
    private String loginId;
    @Column(length = 100 , nullable = false)
    private String password;
    @Column(length = 20)
    private String phoneNumber;
    @Column(length = 100)
    private String email;
    @Column(length = 30)
    private String name;
    @Column
    private boolean termsOfServiceConfirm;
    @Column
    private boolean privacyPolicyConfirm;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date latestEditPasswordAt;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date latestLoggedAt;


    public void updatePassword(String encodePassword) {
        this.password = encodePassword;
        this.latestEditPasswordAt = new Date();
    }

    public void updateLatestLoggedAt() {
        this.latestLoggedAt = new Date();
    }
}
