package io.indoorlocation.core;

import android.location.Location;

public class IndoorLocation extends Location {

    private Integer floor;

    public IndoorLocation(Location location, Integer floor) {
        super(location);
        this.floor = floor;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

}
