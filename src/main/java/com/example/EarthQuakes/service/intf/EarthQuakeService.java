package com.example.EarthQuakes.service.intf;

import com.example.EarthQuakes.model.EarthQuake;

import java.util.List;

public interface EarthQuakeService {
    List<EarthQuake> getAllQuake();
    EarthQuake getQuake(long earthQuakeId);
    EarthQuake addQuake(EarthQuake quake);
    boolean updateQuake(long earthQuakeId, EarthQuake quake);
    boolean deleteQuake(long earthQuakeId);
    boolean deleteAllQuakes();
}
