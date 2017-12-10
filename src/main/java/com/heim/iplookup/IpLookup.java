package com.heim.iplookup;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class IpLookup {

    private static int ipToInt(String ipAddress) {

        int result = 0;

        String[] ipAddressInArray = ipAddress.split("\\.");
        for (int i = 3; i >= 0; i--) {
            long ip = Integer.parseInt(ipAddressInArray[3 - i]);
            result |= ip << (i * 8);

        }

        return result;
    }

    private static Map<Integer, CityBlock> readIpCityBlockFile(String file) {

        Map<Integer, CityBlock> cityBlocks = new TreeMap<>();
        try (
                BufferedReader reader
                        = new BufferedReader(new InputStreamReader(
                        IpLookup.class.getResourceAsStream(file)))
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

    private static Map<Long, CityLocations> readCityLocationsFile(String file) {

        Map<Long, CityLocations> cityLocationsMap = new HashMap<>();
        try (
                BufferedReader reader
                        = new BufferedReader(new InputStreamReader(
                        IpLookup.class.getResourceAsStream(file)))
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

    private static void lookupIpAddress(String ip, Map<Integer, CityBlock> cityBlocks,
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
                System.out.println(cityLocationsMap.get(cb.getGeonameId()));
            }
        });
        System.out.println("Took : " + (System.currentTimeMillis() - ms) + " MS");
    }

    public static void main(String[] args) {
        long ms = System.currentTimeMillis();
        Map<Integer, CityBlock> cityBlocks = readIpCityBlockFile("/GeoLite2-City-Blocks-IPv4.csv");
        System.out.println("IP City Map " + cityBlocks.size() + " took : " + (System.currentTimeMillis() - ms) + " MS");
        ms = System.currentTimeMillis();
        Map<Long, CityLocations> cityLocations = readCityLocationsFile("/GeoLite2-City-Locations-en.csv");
        System.out.println("City Locations Map " + cityLocations.size() + " took : " + (System.currentTimeMillis() - ms) + " MS");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            if (line.equals("quit")) {
                System.exit(0);
            }
            if (line.length() >= 7 && line.contains(".")) {
                lookupIpAddress(line, cityBlocks, cityLocations);
            }
        }

    }


}
