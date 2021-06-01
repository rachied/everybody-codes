package com.example.demo.controller;

import com.example.demo.entity.Camera;
import com.example.demo.repo.CameraRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cameras")
public class CameraController {

    private final CameraRepository camRepo;

    public CameraController(CameraRepository camRepo) {
        this.camRepo = camRepo;
    }

    @GetMapping("/all")
    public List<Camera> getAllCameras() {
        return camRepo.fetchAll();
    }

    @GetMapping("/search")
    public List<Camera> search(@RequestParam String name,
                               @RequestParam(required= false, defaultValue = "true") boolean exact) {
        return camRepo.search(name, exact);
    }

}
