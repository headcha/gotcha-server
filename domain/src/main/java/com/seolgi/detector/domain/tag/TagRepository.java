package com.seolgi.detector.domain.tag;

import com.seolgi.detector.domain.base.BaseRepository;

public interface TagRepository extends BaseRepository<Tag , Long> {
    Tag findOneByTag(String tag);
}
