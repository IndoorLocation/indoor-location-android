package io.indoorlocation.core;

import android.location.Location;

public class IndoorLocation extends Location {

    private Double floor;

    public IndoorLocation(Location location, Double floor) {
        super(location);
        this.floor = floor;
    }

    public IndoorLocation(String provider, double latitude, double longitude, Double floor, long timeStamp) {
        super(provider);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setTime(timeStamp);
        this.floor = floor;
    }

    public Double getFloor() {
        return floor;
    }

    public void setFloor(Double floor) {
        this.floor = floor;
    }

}
