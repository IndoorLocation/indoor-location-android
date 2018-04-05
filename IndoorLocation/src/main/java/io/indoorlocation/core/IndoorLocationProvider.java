package io.indoorlocation.core;

import java.util.ArrayList;
import java.util.List;

public abstract class IndoorLocationProvider {

    private List<IndoorLocationProviderListener> listeners;
    private IndoorLocation lastIndoorLocation;

    public IndoorLocationProvider() {
        listeners = new ArrayList<>();
    }

    public void addListener(IndoorLocationProviderListener listener) {
        listeners.add(listener);
    }

    public void removeListener(IndoorLocationProviderListener listener) {
        listeners.remove(listener);
    }

    public List<IndoorLocationProviderListener> getListeners() {
        return listeners;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    public abstract boolean supportsFloor();

    public abstract void start();

    public abstract void stop();

    public abstract boolean isStarted();

    public void dispatchOnProviderStarted() {
        for (IndoorLocationProviderListener listener : listeners) {
            listener.onProviderStarted();
        }
    }

    public void dispatchOnProviderStopped() {
        for (IndoorLocationProviderListener listener : listeners) {
            listener.onProviderStopped();
        }
    }

    public void dispatchOnProviderError(Error error) {
        for (IndoorLocationProviderListener listener : listeners) {
            listener.onProviderError(error);
        }
    }

    public void dispatchIndoorLocationChange(IndoorLocation indoorLocation) {
        for (IndoorLocationProviderListener listener : listeners) {
            listener.onIndoorLocationChange(indoorLocation);
        }
        setLastLocation(indoorLocation);
    }

    private void setLastLocation(IndoorLocation indoorLocation) {
        lastIndoorLocation = indoorLocation;
    }

    public IndoorLocation getLastLocation() {
        return lastIndoorLocation;
    }

}
