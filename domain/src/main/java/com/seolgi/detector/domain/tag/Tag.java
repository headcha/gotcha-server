package com.seolgi.detector.domain.tag;

import com.seolgi.detector.domain.base.BaseEntity;
import com.seolgi.detector.domain.camera.tag.CameraTagMap;
import com.seolgi.detector.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tag")
@Getter
@NoArgsConstructor
public class Tag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String tag;


    @OneToMany(mappedBy = "camera")
    private List<CameraTagMap> tagMaps = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    @Builder
    public Tag(String tag, Member member) {
        this.tag = tag;
        this.member = member;
    }
}
