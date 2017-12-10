package com.heim.iplookup;

import lombok.Data;

@Data
class CityBlock {
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
