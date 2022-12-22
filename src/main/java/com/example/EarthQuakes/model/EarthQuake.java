package com.example.EarthQuakes.model;

import javax.persistence.*;

@Entity
@Table(name = "Earthquakes")
public class EarthQuake {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long earthQuakeId;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "title")
    private String title;
    @Column(name = "depth")
    private double depth;
    @Column(name = "magnitude")
    private double magnitude;
    @Column(name = "dataset")
    private String dataset;

    public EarthQuake() {

    }

    public EarthQuake(double latitude, double longitude, String title, double depth, double magnitude, String dataset) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.depth = depth;
        this.magnitude = magnitude;
        this.dataset = dataset;
    }

    public long getId() {
        return earthQuakeId;
    }

    public double getLatitude(){
        return latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public double getMagnitude(){
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public String toString(){
        return String.format("Quake(%3.2f, %3.2f), mag = %3.2f, depth = %3.2f, title = %s, dataset = %s", latitude, longitude,magnitude,depth,title, dataset);
    }
}
