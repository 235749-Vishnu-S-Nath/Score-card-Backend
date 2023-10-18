package com.ust.controller;

import com.ust.dto.LOBDto;
import com.ust.entity.LOB;
import com.ust.service.LOBService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/lob")
@Slf4j
public class LOBController {

    @Autowired
    private LOBService lobService;

    @PostMapping
    public ResponseEntity<LOBDto> saveLob(@RequestBody LOBDto lobDto){
        log.info("{}",lobDto.toString());
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
