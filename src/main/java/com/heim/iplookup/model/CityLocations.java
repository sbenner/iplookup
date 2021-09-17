package com.heim.iplookup.model;


public class CityLocations {
    private long geonameId;
    private String localeCode;
    private String continentCode;
    private String continentName;
    private String countryIsoCode;
    private String countryName;
    private String subdivision1IsoCode;
    private String subdivision1Name;
    private String subdivision2IsoCode;
    private String subdivision2Name;
    private String cityName;
    private String metroCode;
    private String timeZone;

    public String toString() {
        return "geonameId=" + getGeonameId() + "\n" +
                "continentCode=" + getContinentCode() + "\n" +
                "continentName=" + getContinentName() + "\n" +
                "countryIsoCode=" + getCountryIsoCode() + "\n" +
                "countryName=" + getCountryName() + "\n" +
                "subdivision1IsoCode=" + getSubdivision1IsoCode() + "\n" +
                "subdivision1Name=" + getSubdivision1Name() + "\n" +
                "subdivision2IsoCode=" + getSubdivision2IsoCode() + "\n" +
                "subdivision2Name=" + getSubdivision2Name() + "\n" +
                "cityName=" + getCityName() + "\n" +
                "metroCode=" + getMetroCode() + "\n" +
                "timeZone=" + getTimeZone() + "\n";
    }

    public long getGeonameId() {
        return geonameId;
    }

    public void setGeonameId(long geonameId) {
        this.geonameId = geonameId;
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    public String getContinentCode() {
        return continentCode;
    }

    public void setContinentCode(String continentCode) {
        this.continentCode = continentCode;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public void setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getSubdivision1IsoCode() {
        return subdivision1IsoCode;
    }

    public void setSubdivision1IsoCode(String subdivision1IsoCode) {
        this.subdivision1IsoCode = subdivision1IsoCode;
    }

    public String getSubdivision1Name() {
        return subdivision1Name;
    }

    public void setSubdivision1Name(String subdivision1Name) {
        this.subdivision1Name = subdivision1Name;
    }

    public String getSubdivision2IsoCode() {
        return subdivision2IsoCode;
    }

    public void setSubdivision2IsoCode(String subdivision2IsoCode) {
        this.subdivision2IsoCode = subdivision2IsoCode;
    }

    public String getSubdivision2Name() {
        return subdivision2Name;
    }

    public void setSubdivision2Name(String subdivision2Name) {
        this.subdivision2Name = subdivision2Name;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getMetroCode() {
        return metroCode;
    }

    public void setMetroCode(String metroCode) {
        this.metroCode = metroCode;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
