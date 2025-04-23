--creating the database schema for the music database 

set echo on linesize 150 pagesize 100

drop table artist cascade constraints;
create table artist (
	artist_id varchar2(25),
	artist_name varchar2(30),
	constraint artist_pk primary key(artist_id)
);

drop table artistGenre cascade constraints;
create table artistGenre (
	artist_id varchar2(25),
	genre varchar2(30),
	constraint artistGenre_fk foreign key(artist_id) references artist(artist_id)
);

drop table album cascade constraints;
create table album (
	album_id varchar2(25),
	album_name varchar2(100),
	total_tracks number,
	album_reldate date,
	constraint album_pk primary key(album_id)
);

drop table track cascade constraints;
create table track (
	track_id varchar2(25),
	track_name varchar2(100),
	track_length number,
	constraint track_pk primary key(track_id)
);


drop table album_by_artist cascade constraints;
create table album_by_artist (
	album_id varchar2(25),
	artist_id varchar2(25)
);

drop table track_by_artist cascade constraints;
create table track_by_artist (
	track_id varchar2(25),
	artist_id varchar2(25)
);

drop table track_on_album cascade constraints;
create table track_on_album (
	track_id varchar2(25),
	album_id varchar2(25)
);

