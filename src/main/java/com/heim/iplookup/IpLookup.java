package com.heim.iplookup;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class IpLookup {

    static   Map<Integer, CityBlock> cityBlocks;
    static     Map<Long, CityLocations> cityLocations;


    static void initMaps(String[] args) throws Exception{
        long ms = System.currentTimeMillis();

        Path cityBlocksFile  = Paths.get(args[0]);
        if(Files.exists(cityBlocksFile))
            cityBlocks = IpUtils.readIpCityBlockFile(cityBlocksFile);
        else
            throw new FileNotFoundException("GeoLite City Blocks file not found");

        System.out.println("IP City Map " + cityBlocks.size() + " took : " + (System.currentTimeMillis() - ms) + " MS");
        ms = System.currentTimeMillis();
        Path cityLocationsFile  = Paths.get(args[1]);
        if(Files.exists(cityLocationsFile))
            cityLocations = IpUtils.readCityLocationsFile(cityLocationsFile);
        else
            throw new FileNotFoundException("GeoLite City Locations file not found");

        System.out.println("City Locations Map " + cityLocations.size() + " took : " + (System.currentTimeMillis() - ms) + " MS");

    }


    public static void main(String[] args) throws Exception{
        if(args.length<2){
            System.out.println("Please enter paths for input GeoLoc files");
            System.out.println("IpLookup <geolite_city_file> <geolite_locations_file>");
            System.exit(1);
        }

        initMaps(args);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            if (line.equals("quit")) {
                System.exit(0);
            }
            if (line.length() >= 7 && line.contains(".")&&
                    IpUtils.testIp(line)) {
                IpUtils.lookupIpAddress(line, cityBlocks, cityLocations);
            }else{
                System.out.println("wrong IP supplied");
            }
        }

    }


}
