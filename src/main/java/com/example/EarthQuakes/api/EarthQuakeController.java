package com.example.EarthQuakes.api;

import com.example.EarthQuakes.model.EarthQuake;
import com.example.EarthQuakes.service.intf.EarthQuakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/EarthQuakes")
public class EarthQuakeController {
    @Autowired
    private EarthQuakeService earthQuakeService;
    @GetMapping("/Quakes")
    public List<EarthQuake> getAllQuakes(@RequestParam(required = false) String title) {
        return earthQuakeService.getAllQuake();
    }

    @GetMapping("/Quakes/{id}")
    public EarthQuake getQuakesById(@PathVariable("id") long id) {
        return earthQuakeService.getQuake(id);
    }

    @PostMapping("/Quakes")
    public EarthQuake createQuakes(@RequestBody EarthQuake quake) {
       return earthQuakeService.addQuake(quake);
    }


    @DeleteMapping("/Quakes/{id}")
    public boolean deleteQuakesById(@PathVariable("id") long id) {
        return earthQuakeService.deleteQuake(id);
    }

    @DeleteMapping("/Quakes")
    public boolean deleteAllQuakes() {
       return earthQuakeService.deleteAllQuakes();
    }
}