# IndoorLocation core classes for Android

## Core classes

### IndoorLocation

IndoorLocation extends the standard android.location.Location class to add support for floor. Floor is a Double and can be null.

Two constructors are defined:

```
public IndoorLocation(Location location, Double floor)

public IndoorLocation(String provider, double latitude, double longitude, Double floor, long timeStamp)

```

### IndoorLocationProvider

Abstract class to serve as a base for any provider. A provider is basically a class that emits IndoorLocations.

### IndoorLocationProviderListener

Interface that you must implement to receive events from IndoorLocation providers.

## Support

For any support with this provider, please do not hesitate to contact [support@mapwize.io](mailto:support@mapwize.io)

## License

MIT
