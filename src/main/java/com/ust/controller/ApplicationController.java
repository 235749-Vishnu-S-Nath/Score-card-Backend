package com.ust.controller;

import com.ust.dto.ApplicationDto;
import com.ust.entity.Application;
import com.ust.entity.ITRC;
import com.ust.service.ApplicationService;
import com.ust.service.ITRCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/application")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ITRCService itrcService;

    @PostMapping
    public ResponseEntity<ApplicationDto> saveApplication(@RequestBody ApplicationDto applicationDto){
        final var application = applicationService.getApplication(applicationDto.applicationId());
        final var itrc = itrcService.getItrc(applicationDto.itrcId());
        if(application.isPresent() || itrc.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(entityToDto(applicationService.saveApplication(dtoToEntity(applicationDto,itrc.get()))));
    }

    @GetMapping
    public ResponseEntity<List<ApplicationDto>> getAllItrc(){
        final var applications = applicationService.getApplications();
        if(applications.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(applications.stream().map(this::entityToDto).toList());
    }

    public Application dtoToEntity(ApplicationDto applicationDto, ITRC itrc){
        return new Application(applicationDto.applicationId(), applicationDto.applicationName(), itrc);
    }

    public ApplicationDto entityToDto(Application application){
        return new ApplicationDto(application.getApplicationId(), application.getApplicationName(), application.getItrc().getItrcId());
    }
}
