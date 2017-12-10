package com.heim.iplookup;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CityLocations {
    long geonameId;
    String localeCode;
    String continentCode;
    String continentName;
    String countryIsoCode;
    String countryName;
    String subdivision1IsoCode;
    String subdivision1Name;
    String subdivision2IsoCode;
    String subdivision2Name;
    String cityName;
    String metroCode;
    String timeZone;

    public String toString() {
        return "geonameId=" + geonameId + "\n" +
                "continentCode=" + continentCode + "\n" +
                "continentName=" + continentName + "\n" +
                "countryIsoCode=" + countryIsoCode + "\n" +
                "countryName=" + countryName + "\n" +
                "subdivision1IsoCode=" + subdivision1IsoCode + "\n" +
                "subdivision1Name=" + subdivision1Name + "\n" +
                "subdivision2IsoCode=" + subdivision2IsoCode + "\n" +
                "subdivision2Name=" + subdivision2Name + "\n" +
                "cityName=" + cityName + "\n" +
                "metroCode=" + metroCode + "\n" +
                "timeZone=" + timeZone + "\n";
    }

}
