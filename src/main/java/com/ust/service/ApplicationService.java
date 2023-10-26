package com.ust.service;

import com.ust.entity.Application;
import com.ust.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    public Optional<Application> getApplication(long l) {
        return applicationRepository.findById(l);
    }

    public Application saveApplication(Application application) {
        return applicationRepository.save(application);
    }

    public List<Application> getApplications() {
        return applicationRepository.findAll();
    }
}
