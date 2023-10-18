package com.ust.service;

import com.ust.entity.ITRC;
import com.ust.repository.ITRCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ITRCService {
    @Autowired
    private ITRCRepository itrcRepository;

    public Optional<ITRC> getItrc(long l) {
        return itrcRepository.findById(l);
    }

    public ITRC saveITRC(ITRC itrc) {
        return itrcRepository.save(itrc);
    }

    public List<ITRC> getItrcs() {
        return itrcRepository.findAll();
    }
}
