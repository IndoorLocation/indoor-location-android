package io.indoorlocation.core;

import java.util.ArrayList;
import java.util.List;

public abstract class IndoorLocationProvider {

    private String name;
    private List<IndoorLocationProviderListener> listeners;

    public IndoorLocationProvider(String name) {
        this.name = name;
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
        return name;
    }

    public abstract boolean supportsFloor();

    public abstract void start();

    public abstract void stop();

    public abstract boolean isStarted();

}
