package com.seolgi.detector.camera.web.controller.camera;

import com.seolgi.detector.domain.camera.Camera;
import com.seolgi.detector.domain.camera.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public String index(@PathVariable long id , Model model) {

        Camera one = cameraService.findOne(id);
        model.addAttribute("camera" , new DisplayCamera(one));
        return "camera/index";
    }
}
