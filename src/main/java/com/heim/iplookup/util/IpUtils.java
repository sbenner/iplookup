package com.heim.iplookup.util;

import com.heim.iplookup.model.CityBlock;
import com.heim.iplookup.model.CityLocations;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;

public class IpUtils {

    private static final String IPADDRESS_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    private static Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);

    public static boolean testIp(String ip){
        return pattern.matcher(ip).matches();
    }

    private static int ipToInt(String ipAddress) {

        int result = 0;

        String[] ipAddressInArray = ipAddress.split("\\.");
        for (int i = 3; i >= 0; i--) {
            long ip = Integer.parseInt(ipAddressInArray[3 - i]);
            result |= ip << (i * 8);

        }

        return result;
    }

    public static Map<Integer, CityBlock> readIpCityBlockFile(Path file) {

        Map<Integer, CityBlock> cityBlocks = new TreeMap<>();
        try (
                BufferedReader reader
                        = new BufferedReader(new InputStreamReader(
                        Files.newInputStream(file)))
        ) {
            try {
                Iterable<CSVRecord> records = CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .parse(reader);

                for (CSVRecord record : records) {
                    CityBlock cb = new CityBlock();
                    String network = record.get("network");
                    String[] nw = network.split("/");
                    cb.setIp(ipToInt(nw[0]));
                    cb.setBitmask(Integer.valueOf(nw[1]));
                    String accuracyRadius = record.get("accuracy_radius");
                    if (accuracyRadius != null && accuracyRadius.length() > 0)
                        cb.setAccuracyRadius(record.get("accuracy_radius"));
                    String geoNameId = record.get("geoname_id");
                    if (geoNameId != null && geoNameId.length() > 0)
                        cb.setGeonameId(Long.valueOf(geoNameId));
                    cityBlocks.put(cb.getIp(), cb);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cityBlocks;
    }

    public static Map<Long, CityLocations> readCityLocationsFile(Path file) {

        Map<Long, CityLocations> cityLocationsMap = new HashMap<>();
        try (
                BufferedReader reader
                        = new BufferedReader(new InputStreamReader(
                        Files.newInputStream(file)))
        ) {
            try {
                Iterable<CSVRecord> records = CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .parse(reader);

                for (CSVRecord record : records) {
                    CityLocations cb = new CityLocations();
                    cb.setGeonameId(Long.valueOf(record.get("geoname_id")));
                    cb.setLocaleCode(record.get("locale_code"));
                    cb.setContinentCode(record.get("continent_code"));
                    cb.setContinentName(record.get("continent_name"));
                    cb.setCountryIsoCode(record.get("country_iso_code"));
                    cb.setCountryName(record.get("country_name"));
                    cb.setSubdivision1IsoCode(record.get("subdivision_1_iso_code"));
                    cb.setSubdivision1Name(record.get("subdivision_1_name"));
                    cb.setSubdivision2IsoCode(record.get("subdivision_2_iso_code"));
                    cb.setSubdivision2Name(record.get("subdivision_2_name"));
                    cb.setCityName(record.get("city_name"));
                    cb.setMetroCode(record.get("metro_code"));
                    cb.setTimeZone(record.get("time_zone"));
                    cityLocationsMap.put(cb.getGeonameId(), cb);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cityLocationsMap;
    }


    private static boolean compare(int ip, int subnet, int bits) {
        int mask = -1 << (32 - bits);
        return (subnet & mask) == (ip & mask);
    }


    public static String intToIp(int ip) {
        return ((ip >> 24) & 0xFF) + "."
                + ((ip >> 16) & 0xFF) + "."
                + ((ip >> 8) & 0xFF) + "."
                + (ip & 0xFF);
    }

    public static void lookupIpAddress(String ip, Map<Integer, CityBlock> cityBlocks,
                                        Map<Long, CityLocations> cityLocationsMap) {
        long ms = System.currentTimeMillis();
        Set<CityBlock> set = new HashSet<>();
        for (int i = 16; i < 30; i++) {
            int mask = -1 << (32 - i);
            int ipAddress = ipToInt(ip) & mask;
            CityBlock cb = cityBlocks.get(ipAddress);
            if (cb != null) set.add(cb);
        }
        set.forEach(cb ->
        {
            if (compare(ipToInt(ip), cb.getIp(), cb.getBitmask())) {
                System.out.println(ip);
                System.out.println(cityLocationsMap.get(cb.getGeonameId()));
            }
        });
        System.out.println("\nLookup Took : " + (System.currentTimeMillis() - ms) + " MS\n");
    }

}
