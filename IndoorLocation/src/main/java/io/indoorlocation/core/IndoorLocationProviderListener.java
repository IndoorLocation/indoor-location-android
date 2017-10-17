package io.indoorlocation.core;

public interface IndoorLocationProviderListener {

    void onProviderStarted();

    void onProviderStopped();

    void onProviderError(Error error);

    void onIndoorLocationChange(IndoorLocation indoorLocation);

}