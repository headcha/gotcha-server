package com.seolgi.detector.domain.camera;

import com.querydsl.jpa.JPQLQuery;
import com.seolgi.detector.domain.base.BaseQueryDslSupport;
import com.seolgi.detector.domain.camera.vote.QCameraVote;
import com.seolgi.detector.domain.location.Location;
import com.seolgi.detector.domain.member.QMember;
import com.seolgi.detector.domain.utils.DateCalculateUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class CameraRepositoryImpl extends BaseQueryDslSupport<Camera> implements CameraCustomRepository {
    public CameraRepositoryImpl() {
        super(Camera.class);
    }

    @Override
    public Page<Camera> findByMember(long memberId, PageRequest pageable) {
        QCamera camera = QCamera.camera;
        QMember member = QMember.member;
        JPQLQuery<Camera> query = from(camera).innerJoin(camera.member, member).where(member.id.eq(memberId));

        return super.sortAndPagination(query, pageable);
    }

    @Override
    public List<Camera> search(CameraSearchCondition condition) {

        final String geoLocationQuery = "SELECT camera.* , (6371*acos(cos(radians(:latitude))*cos(radians(latitude))*cos(radians(longitude)-radians(:longitude))+sin(radians(:latitude))*sin(radians(latitude)))) AS distance " +
                "FROM camera_location inner join camera on camera_location.cameraId = camera.id HAVING distance <= :distance ORDER BY distance";


        Query nativeQuery = getEntityManager().createNativeQuery(geoLocationQuery, Camera.class);

        nativeQuery.setParameter("latitude", condition.getLatitude());
        nativeQuery.setParameter("longitude", condition.getLongitude());
        nativeQuery.setParameter("distance", condition.getDistanceKm());

        List<Camera> resultList = nativeQuery.getResultList();

        return resultList;
    }

    @Override
    public Page<Camera> findToday(PageRequest pageable) {
        QCamera camera = QCamera.camera;

        Date firstTime = DateCalculateUtil.getFirstTime(new Date());

        JPQLQuery<Camera> query = from(camera)
                .where(camera.createdAt.after(firstTime));
        Page<Camera> cameras = sortAndPagination(query, pageable);
        return cameras;
    }
}
