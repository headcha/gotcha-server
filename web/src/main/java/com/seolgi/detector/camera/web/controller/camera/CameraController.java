package com.seolgi.detector.camera.web.controller.camera;

import com.querydsl.core.types.Order;
import com.seolgi.detector.camera.web.api.v1.camera.comment.CommentDto;
import com.seolgi.detector.domain.base.search.PageableFactory;
import com.seolgi.detector.domain.camera.Camera;
import com.seolgi.detector.domain.camera.CameraService;
import com.seolgi.detector.domain.camera.comment.CameraComment;
import com.seolgi.detector.domain.camera.comment.CameraCommentService;
import com.seolgi.detector.domain.camera.vote.CameraVoteService;
import com.seolgi.detector.domain.member.Member;
import com.seolgi.detector.domain.utils.converter.PageConverter;
import com.seolgi.detector.domain.utils.converter.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cameras/{id}")
public class CameraController {

    @Autowired
    private CameraService cameraService;

    @Autowired
    private CameraCommentService cameraCommentService;

    @Autowired
    private CameraVoteService cameraVoteService;

    @GetMapping
    public String index(@PathVariable long id , Model model , Member member) {

        Camera one = cameraService.findOne(id);
        Page<CameraComment> commentPage = cameraCommentService.get(id, PageableFactory.create(1, 20));


        boolean voteRight = false;

        if (member != null)
            voteRight = cameraVoteService.existsRightByMember(member.getId());

        boolean voteWrong = false;

        if (member != null)
            voteRight = cameraVoteService.existsWrongByMember(member.getId());

        PageDto<CommentDto> commentDtoPage = PageConverter.convert(commentPage, CommentDto.class);

        model.addAttribute("camera" , new DisplayCamera(one , commentDtoPage , voteRight , voteWrong));
        return "camera/index";
    }
}
