package com.example.demo.repo;

import com.example.demo.entity.Camera;
import com.example.demo.util.CameraReader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CameraRepository {

    private final CameraReader reader;

    public CameraRepository() throws IOException {
        this.reader = new CameraReader("cameras.csv");
    }

    public List<Camera> fetchAll()
    {
        return reader.getCameras();
    }

    public List<Camera> search(String name, boolean exactSearch)
    {
        Predicate<Camera> exactMatch = c -> c.getName().equalsIgnoreCase(name);
        Predicate<Camera> partialMatch = c -> c.getName().toLowerCase().contains(name.toLowerCase());

        var condition = exactSearch ? exactMatch : partialMatch;

        return reader.getCameras()
                .stream()
                .filter(condition)
                .collect(Collectors.toList());
    }

}
