# IPv4 Lookup

IPv4 lookup in maxmind's CSVs.
1. Download CSVs from 
http://dev.maxmind.com/geoip/legacy/geolite/
2. Pass 
GeoLite2-City-Blocks-IPv4.csv
GeoLite2-City-Locations-en.csv
files' paths as the application cmd parameters.
`app /path/city_blocks.csv /path/city_locations.csv` 
3. Supply the needed IP addresses
into the STDIN. 
