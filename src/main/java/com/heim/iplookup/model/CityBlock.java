package com.heim.iplookup.model;

public class CityBlock {
    private int ip;
    private int bitmask;
    private long geonameId;
    private long registeredCountryGeonameId;
    private long representedCountryGeonameId;
    private String network;
    private String isAnonymousProxy;
    private String isSatelliteProvider;
    private String postalCode;
    private String latitude;
    private String longitude;
    private String accuracyRadius;

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }

    public int getBitmask() {
        return bitmask;
    }

    public void setBitmask(int bitmask) {
        this.bitmask = bitmask;
    }

    public long getGeonameId() {
        return geonameId;
    }

    public void setGeonameId(long geonameId) {
        this.geonameId = geonameId;
    }

    public long getRegisteredCountryGeonameId() {
        return registeredCountryGeonameId;
    }

    public void setRegisteredCountryGeonameId(long registeredCountryGeonameId) {
        this.registeredCountryGeonameId = registeredCountryGeonameId;
    }

    public long getRepresentedCountryGeonameId() {
        return representedCountryGeonameId;
    }

    public void setRepresentedCountryGeonameId(long representedCountryGeonameId) {
        this.representedCountryGeonameId = representedCountryGeonameId;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getIsAnonymousProxy() {
        return isAnonymousProxy;
    }

    public void setIsAnonymousProxy(String isAnonymousProxy) {
        this.isAnonymousProxy = isAnonymousProxy;
    }

    public String getIsSatelliteProvider() {
        return isSatelliteProvider;
    }

    public void setIsSatelliteProvider(String isSatelliteProvider) {
        this.isSatelliteProvider = isSatelliteProvider;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAccuracyRadius() {
        return accuracyRadius;
    }

    public void setAccuracyRadius(String accuracyRadius) {
        this.accuracyRadius = accuracyRadius;
    }
}
