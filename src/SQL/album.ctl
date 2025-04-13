LOAD DATA
INFILE albums.csv
REPLACE INTO TABLE album
FIELDS TERMINATED BY ',' 
TRAILING NULLCOLS
(album_id, album_name, total_tracks, album_reldate DATE 'yyyy-mm-dd')
