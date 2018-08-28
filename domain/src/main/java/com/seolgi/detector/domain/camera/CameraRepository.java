package com.seolgi.detector.domain.camera;

import com.seolgi.detector.domain.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CameraRepository extends BaseRepository<Camera, Long>, CameraCustomRepository {

}
