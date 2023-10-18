package com.ust.controller;

import com.ust.dto.ITRCDto;
import com.ust.entity.ITRC;
import com.ust.entity.LOB;
import com.ust.service.ITRCService;
import com.ust.service.LOBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/itrc")
public class ITRCController {
    @Autowired
    private ITRCService itrcService;

    @Autowired
    private LOBService lobService;

    @PostMapping
    public ResponseEntity<ITRCDto> saveItrc(@RequestBody ITRCDto itrcDto){
        final var itrc = itrcService.getItrc(itrcDto.itrcId());
        final var lob = lobService.getLob(itrcDto.lobId());
        if(itrc.isPresent() || lob.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(entityToDto(itrcService.saveITRC(dtoToEntity(itrcDto,lob.get()))));
    }

    @GetMapping
    public ResponseEntity<List<ITRCDto>> getAllItrc(){
        final var itrcs = itrcService.getItrcs();
        if(itrcs.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(itrcs.stream().map(this::entityToDto).toList());
    }

    public ITRC dtoToEntity(ITRCDto itrcDto, LOB lob){
        return new ITRC(itrcDto.itrcId(), itrcDto.itrcName(), lob);
    }

    public ITRCDto entityToDto(ITRC itrc){
        return new ITRCDto(itrc.getItrcId(), itrc.getItrcName(), itrc.getLob().getLobId());
    }
}
