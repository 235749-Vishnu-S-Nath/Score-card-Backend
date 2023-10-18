package com.ust.service;

import com.ust.entity.LOB;
import com.ust.repository.LOBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class LOBService {
    @Autowired
    private LOBRepository lobRepository;

    public Optional<LOB> getLob(long l) {
        return lobRepository.findById(l);
    }

    public LOB saveLOB(LOB lob) {
        return lobRepository.save(lob);
    }

    public List<LOB> getLobs() {
        return lobRepository.findAll();
    }
}
