package com.example.EarthQuakes.service.intf;

import com.example.EarthQuakes.model.EarthQuake;

import java.util.List;

public interface EarthQuakeParser {
    List<EarthQuake> getEarthQuakes(String source);
}
