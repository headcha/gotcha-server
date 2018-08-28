package com.seolgi.detector.domain.camera.tag;

import com.seolgi.detector.domain.base.BaseEntity;
import com.seolgi.detector.domain.camera.Camera;
import com.seolgi.detector.domain.tag.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "camera_tag_map")
@NoArgsConstructor
@Getter
public class CameraTagMap extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tagId", nullable = false)
    private Tag tag;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cameraId", nullable = false)
    private Camera camera;

    @Builder
    public CameraTagMap(Tag tag, Camera camera) {
        this.tag = tag;
        this.camera = camera;
    }
}
