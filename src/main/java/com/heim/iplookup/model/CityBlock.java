package com.heim.iplookup.model;

import lombok.Data;

@Data
public class CityBlock {
    int ip;
    int bitmask;
    long geonameId;
    long registeredCountryGeonameId;
    long representedCountryGeonameId;
    String network;
    String isAnonymousProxy;
    String isSatelliteProvider;
    String postalCode;
    String latitude;
    String longitude;
    String accuracyRadius;
}
