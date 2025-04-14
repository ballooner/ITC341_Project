LOAD DATA
INFILE track_artist.csv
REPLACE INTO TABLE track_by_artist
FIELDS TERMINATED BY ',' 
TRAILING NULLCOLS
(track_id, artist_id)