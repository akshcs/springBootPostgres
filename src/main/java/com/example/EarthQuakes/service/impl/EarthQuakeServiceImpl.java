package com.example.EarthQuakes.service.impl;

import com.example.EarthQuakes.dao.repository.EarthQuakeRepository;
import com.example.EarthQuakes.model.EarthQuake;
import com.example.EarthQuakes.service.intf.EarthQuakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EarthQuakeServiceImpl implements EarthQuakeService {
    @Autowired
    private EarthQuakeRepository earthQuakeRepository;
    @Override
    public List<EarthQuake> getAllQuake() {
        return new ArrayList<>(earthQuakeRepository.findAll());
    }

    @Override
    public EarthQuake getQuake(long earthQuakeId) {
        Optional<EarthQuake> earthQuakeData = earthQuakeRepository.findById(earthQuakeId);
        return earthQuakeData.orElse(null);
    }

    @Override
    public EarthQuake addQuake(EarthQuake quake) {
        try {
            return earthQuakeRepository
                    .save(new EarthQuake(quake.getLatitude(), quake.getLongitude(), quake.getTitle(), quake.getDepth(),
                            quake.getMagnitude(), quake.getDataset()));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateQuake(long earthQuakeId, EarthQuake quake) {
        return false;
    }

    @Override
    public boolean deleteQuake(long earthQuakeId) {
        try {
            earthQuakeRepository.deleteById(earthQuakeId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteAllQuakes() {
        try {
            earthQuakeRepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
