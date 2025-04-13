LOAD DATA
INFILE tracks.csv
REPLACE INTO TABLE track
FIELDS TERMINATED BY ',' 
TRAILING NULLCOLS
(track_id, track_name, track_length)
