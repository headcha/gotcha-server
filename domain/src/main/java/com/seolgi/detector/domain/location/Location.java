package com.seolgi.detector.domain.location;

import com.seolgi.detector.domain.camera.Camera;
import com.seolgi.detector.domain.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "camera_location")
@Getter
@NoArgsConstructor
public class Location extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private double latitude;

    @OneToOne(optional = false , fetch = FetchType.LAZY)
    @JoinColumn(name = "cameraId" , nullable = false)
    private Camera camera;

    @Builder
    public Location(double longitude, double latitude, Camera camera) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.camera = camera;
    }
}
