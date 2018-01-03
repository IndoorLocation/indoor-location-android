package io.indoorlocation.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndoorLocationProviderSelector extends IndoorLocationProvider implements IndoorLocationProviderListener{

    private List<IndoorLocationProvider> indoorLocationProviderList;
    private Map<String, IndoorLocation> indoorLocationMap;
    private double indoorLocationValidity;
    private boolean isStarted = false;

    public IndoorLocationProviderSelector(String name, double indoorLocationValidity) {
        super(name);
        this.indoorLocationProviderList = new ArrayList<>();
        this.indoorLocationMap = new HashMap<>();
        this.indoorLocationValidity = indoorLocationValidity;
    }

    public void addIndoorLocationProvider(IndoorLocationProvider indoorLocationProvider) {
        this.indoorLocationProviderList.add(indoorLocationProvider);
    }

    public void removeIndoorLocationProvider(IndoorLocationProvider indoorLocationProvider) {
        this.indoorLocationProviderList.remove(indoorLocationProvider);
        indoorLocationProvider.stop();
    }

    @Override
    public boolean supportsFloor() {
        for (IndoorLocationProvider indoorLocationProvider : this.indoorLocationProviderList) {
            if (indoorLocationProvider.supportsFloor()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void start() {
        for (IndoorLocationProvider indoorLocationProvider : this.indoorLocationProviderList) {
            indoorLocationProvider.start();
        }
        this.isStarted = true;
    }

    @Override
    public void stop() {
        for (IndoorLocationProvider indoorLocationProvider : this.indoorLocationProviderList) {
            indoorLocationProvider.stop();
        }
        this.isStarted = false;
    }

    @Override
    public boolean isStarted() {
        return this.isStarted;
    }

    private IndoorLocation selectIndoorLocation(List<IndoorLocation> indoorLocations) {
        IndoorLocation selectedIndoorLocation = indoorLocations.get(0);

        for (IndoorLocation indoorLocation : indoorLocations) {
            if (indoorLocation.getTime() > selectedIndoorLocation.getTime() && (System.currentTimeMillis() - indoorLocation.getTime()) < this.indoorLocationValidity) {
                selectedIndoorLocation = indoorLocation;
            }
        }

        return selectedIndoorLocation;
    }

    /*
        Indoor location listener
     */
    @Override
    public void onProviderStarted() {
        this.dispatchOnProviderStarted();
    }

    @Override
    public void onProviderStopped() {
        this.dispatchOnProviderStopped();
    }

    @Override
    public void onProviderError(Error error) {
        this.dispatchOnProviderError(error);
    }

    @Override
    public void onIndoorLocationChange(IndoorLocation indoorLocation) {
        this.indoorLocationMap.put(indoorLocation.getProvider(), indoorLocation);
        IndoorLocation selectedIndoorLocation = this.selectIndoorLocation(new ArrayList<>(this.indoorLocationMap.values()));
        this.dispatchIndoorLocationChange(selectedIndoorLocation);
    }
}
