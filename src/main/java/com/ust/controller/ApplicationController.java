package com.ust.controller;

import com.ust.dto.ApplicationDTO;
import com.ust.entity.Application;
import com.ust.entity.ITRC;
import com.ust.entity.LOB;
import com.ust.service.ITRCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ust.service.ApplicationService;

@RestController
@RequestMapping("api/v1/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ITRCService itrcService;

    @PostMapping
    private ResponseEntity<?> addApplication(@RequestBody ApplicationDTO appDto){
        final var app = applicationService.findApplication(appDto.applicationId());
        final var itrc = itrcService.getItrc(appDto.itrcId());
        if(app.isPresent() || itrc.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(entityToDto(applicationService.saveApplication(dtoToEntity(appDto,itrc.get()))));
    }

    @GetMapping
    private ResponseEntity<?> getAllApplications(){
        final var applications = applicationService.findAllApplication();
        if(applications.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(applications.stream().map(application -> entityToDto(application)).toList());
    }

    public Application dtoToEntity(ApplicationDTO appDto, ITRC itrc){
        return new Application(appDto.applicationId(), appDto.applicationName(), itrc);
    }

    public ApplicationDTO entityToDto(Application app){
        return new ApplicationDTO(app.getApplicationId(), app.getApplicationName(), app.getItrc().getItrcId());
    }


}
