package com.seolgi.detector.camera.web.api.v1.member.camera.vote;

import lombok.Getter;

@Getter
public class VoteDto {

    private long totalCount;

    public VoteDto(long totalCount) {
        this.totalCount = totalCount;
    }
}
