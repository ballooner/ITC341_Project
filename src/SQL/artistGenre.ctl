LOAD DATA
INFILE artist_genre.csv
REPLACE INTO TABLE artistGenre
FIELDS TERMINATED BY ',' 
TRAILING NULLCOLS
(artist_id, genre)
