# IPv4 Lookup

IPv4 lookup in maxmind's CSVs.

1. Build with 

``mvn clean compile assembly:single``

1. Download CSVs from 
http://dev.maxmind.com/geoip/legacy/geolite/
2. Pass 
GeoLite2-City-Blocks-IPv4.csv
GeoLite2-City-Locations-en.csv
files' paths as the application cmd parameters.

e.g.
assuming you're located in the project's folder:
 
``cd target && java -jar iplookup-1.0.jar ~/tools/GeoLite2-City-CSV_20171205/GeoLite2-City-Blocks-IPv4.csv ~/tools/GeoLite2-City-CSV_20171205/GeoLite2-City-Locations-en.csv``


The sample files are provided in the `resources` folder.

3. Supply the needed IP addresses
into the STDIN. 


