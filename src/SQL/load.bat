@ECHO ON

sqlldr userid=user/pass control=artist.ctl
sqlldr userid=user/pass control=artistGenre.ctl
sqlldr userid=user/pass control=album.ctl
sqlldr userid=user/pass control=track.ctl

pause
exit