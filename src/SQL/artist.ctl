LOAD DATA
INFILE artists.csv
REPLACE INTO TABLE artist
FIELDS TERMINATED BY ',' 
TRAILING NULLCOLS
(artist_id, artist_name)
