LOAD DATA
INFILE album_artist.csv
REPLACE INTO TABLE album_by_artist
FIELDS TERMINATED BY ',' 
TRAILING NULLCOLS
(album_id, artist_id)