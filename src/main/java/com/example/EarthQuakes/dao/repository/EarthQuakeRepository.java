package com.example.EarthQuakes.dao.repository;

import com.example.EarthQuakes.model.EarthQuake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EarthQuakeRepository extends JpaRepository<EarthQuake, Long> {
    List<EarthQuake> findByTitleContaining(String title);
 }
