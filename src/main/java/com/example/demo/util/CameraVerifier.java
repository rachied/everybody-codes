package com.example.demo.util;

import com.example.demo.entity.Camera;
import com.opencsv.bean.BeanVerifier;

import static com.example.demo.entity.Camera.NUMBER_PATTERN;

public class CameraVerifier implements BeanVerifier<Camera> {

    @Override
    public boolean verifyBean(Camera c) {
        return c.getLatitude() != null && c.getLongitude() != null
                && c.getName() != null && NUMBER_PATTERN.matcher(c.getName()).find();
    }

}
