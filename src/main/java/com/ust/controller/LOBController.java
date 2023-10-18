package com.ust.controller;

import com.ust.dto.LOBDto;
import com.ust.entity.LOB;
import com.ust.service.LOBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/lob")
public class LOBController {

    @Autowired
    private LOBService lobService;

    @PostMapping
    public ResponseEntity<LOBDto> saveLob(LOBDto lobDto){
        final var lob = lobService.getLob(lobDto.lobId());
        if(lob.isPresent()){
            return ResponseEntity.badRequest().build();
        }
            return ResponseEntity.ok().body(entityToDto(lobService.saveLOB(dtoToEntity(lobDto))));
    }

    @GetMapping
    public ResponseEntity<List<LOBDto>> getAllLobs(){
        final var lobs = lobService.getLobs();
        if(lobs.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(lobs.stream().map(this::entityToDto).toList());
    }

    public LOB dtoToEntity(LOBDto lobDto){
        return new LOB(lobDto.lobId(),lobDto.lob());
    }

    public LOBDto entityToDto(LOB lob){
        return new LOBDto(lob.getLobId(), lob.getLob());
    }
}
