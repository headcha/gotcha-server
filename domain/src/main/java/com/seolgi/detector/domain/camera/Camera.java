package com.seolgi.detector.domain.camera;

import com.seolgi.detector.domain.camera.comment.CameraComment;
import com.seolgi.detector.domain.camera.image.CameraImage;
import com.seolgi.detector.domain.camera.tag.CameraTagMap;
import com.seolgi.detector.domain.camera.vote.CameraVote;
import com.seolgi.detector.domain.location.Location;
import com.seolgi.detector.domain.base.BaseEntity;
import com.seolgi.detector.domain.member.Member;
import com.seolgi.detector.domain.tag.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "camera")
@Getter
@NoArgsConstructor
public class Camera extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 1000)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    @OneToOne(mappedBy = "camera", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Location location;

    @OneToOne(mappedBy = "camera", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CameraImage image;

    @OneToMany(mappedBy = "camera")
    private List<CameraComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "camera")
    private List<CameraVote> votes = new ArrayList<>();

    @OneToMany(mappedBy = "camera" , cascade = CascadeType.ALL)
    private List<CameraTagMap> tagMaps = new ArrayList<>();

    @Builder
    public Camera(String imageUrl, String message, double longitude, double latitude, Member member) {
        this.image = CameraImage.builder().url(imageUrl).camera(this).build();
        this.location = Location.builder().latitude(latitude).longitude(longitude).camera(this).build();
        this.message = message;
        this.member = member;
        this.comments = new ArrayList<>();
        this.votes = new ArrayList<>();
        this.tagMaps = new ArrayList<>();
    }

    public void addTags(List<Tag> tagList) {
        for (Tag tag : tagList)
            this.tagMaps.add(CameraTagMap.builder().camera(this).tag(tag).build());
    }
}
