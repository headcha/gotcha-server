package com.seolgi.detector.domain.camera.image;

import com.seolgi.detector.domain.camera.Camera;
import com.seolgi.detector.domain.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "camera_image")
@Getter
@NoArgsConstructor
public class CameraImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String url;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cameraId", nullable = false)
    private Camera camera;

    @Builder
    public CameraImage(String url, Camera camera) {
        this.url = url;
        this.camera = camera;
    }
}
