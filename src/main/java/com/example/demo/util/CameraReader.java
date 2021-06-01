package com.example.demo.util;

import com.example.demo.entity.Camera;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.ClassPathResource;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CameraReader {

    private final List<Camera> cameras;

    public CameraReader(String filePath) throws IOException {
        var resource = new ClassPathResource(filePath).getFile();

        this.cameras = new CsvToBeanBuilder<Camera>(new FileReader(resource))
                .withSeparator(';')
                .withIgnoreEmptyLine(true)
                .withType(Camera.class)
                .withSkipLines(1)
                .withVerifier(new CameraVerifier())
                .build()
                .parse();
    }

    public List<Camera> getCameras() {
        return cameras;
    }
}
