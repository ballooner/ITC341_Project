@ECHO ON

sqlldr userid=user/pass control=artist.ctl
sqlldr userid=user/pass control=artistGenre.ctl
sqlldr userid=user/pass control=album.ctl
sqlldr userid=user/pass control=track.ctl
sqlldr userid=user/pass control=album_by_artist.ctl
sqlldr userid=user/pass control=track_by_artist.ctl
sqlldr userid=user/pass control=track_on_album.ctl

pause
exit