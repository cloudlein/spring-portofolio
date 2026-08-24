package com.my.portofolio.controller.api;

import com.my.portofolio.service.CvProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cv-profiles")
@RequiredArgsConstructor
public class CvProfileController {

    private final CvProfileService cvProfileService;
    
}
