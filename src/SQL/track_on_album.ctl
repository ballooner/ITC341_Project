LOAD DATA
INFILE track_album.csv
REPLACE INTO TABLE track_on_album
FIELDS TERMINATED BY ',' 
TRAILING NULLCOLS
(track_id, album_id)