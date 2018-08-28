package com.seolgi.detector.domain.tag;

import com.seolgi.detector.domain.base.BaseService;
import com.seolgi.detector.domain.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class TagService extends BaseService<Tag , Long> {

    private TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository repository) {
        super(repository);
        this.tagRepository = repository;
    }

    public List<Tag> findOrCreate(List<String> tags , Member member) {

        List<Tag> resultList = new ArrayList<>();

        for (String tag : tags) {
            Tag findOne = tagRepository.findOneByTag(tag);

            if (findOne == null)
                findOne = super.save(Tag.builder().tag(tag).member(member).build());

            resultList.add(findOne);
        }

        return resultList;

    }
}
