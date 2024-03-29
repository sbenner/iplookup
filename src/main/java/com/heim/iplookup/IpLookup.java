package com.heim.iplookup;

import com.heim.iplookup.model.CityBlock;
import com.heim.iplookup.model.CityLocations;
import com.heim.iplookup.util.IpUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
public class IpLookup {

    private static Map<Integer, CityBlock> cityBlocks;
    private static Map<Long, CityLocations> cityLocations;


    private static void initMaps(String[] args) throws Exception{
        long ms = System.currentTimeMillis();
        Path cityBlocksFile  = Paths.get(args[0]);
        if(Files.exists(cityBlocksFile))
            cityBlocks = IpUtils.readIpCityBlockFile(cityBlocksFile);
        else
            throw new FileNotFoundException("GeoLite City Blocks file not found");

        log.info("IP City Map " + cityBlocks.size() + " took : " + (System.currentTimeMillis() - ms) + " MS");
        ms = System.currentTimeMillis();
        Path cityLocationsFile  = Paths.get(args[1]);
        if(Files.exists(cityLocationsFile))
            cityLocations = IpUtils.readCityLocationsFile(cityLocationsFile);
        else
            throw new FileNotFoundException("GeoLite City Locations file not found");

        log.info("City Locations Map {} took {} MS ",cityLocations.size() ,
                (System.currentTimeMillis() - ms));

    }


    public static void main(String[] args) throws Exception{
        if(args.length<2){
            log.info("Please enter paths for input GeoLoc files\n" +
                                "IpLookup <geolite_city_file> <geolite_locations_file>");
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
            }
        }

    }


}
