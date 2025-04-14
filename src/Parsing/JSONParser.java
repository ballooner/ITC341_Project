package Parsing;

import CSV.CSVBuilder;
import SpotifyAPIWrapper.SpotifyAPI;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

public class JSONParser {

    public String parseArtistInfo(JSONObject artistObject) {
        String artistName, artistID;

        artistName = artistObject.getString("name");
        artistID = artistObject.getString("id");

        parseAndAppendArtistGenre(artistObject);

        return String.join(",", artistID, artistName);
    }

    public void parseAndAppendArtistGenre(JSONObject artistObject) {
        String artistID = artistObject.getString("id");

        JSONArray genreArr = artistObject.getJSONArray("genres");

        for (int i = 0; i < genreArr.length(); i++) {
            CSVBuilder.appendToGenre(String.join(",", artistID, genreArr.getString(i)));
        }
    }

    public String parseArtistAlbums(JSONObject albumObject) {
        StringBuilder sb = new StringBuilder();
        JSONArray albums = albumObject.getJSONArray("items");

        JSONObject album;
        String rel_date = "";

        for (int i = 0; i < albums.length(); i++) {
            album = albums.getJSONObject(i);
            parseAndAppendAlbumByArtist(album);

            switch (album.getString("release_date_precision")) {
                case "year":
                    rel_date = album.getString("release_date") + "-" + "00" + "-" + "00";
                    break;
                case "month":
                    rel_date = album.getString("release_date") + "-" + "00";
                    break;
                case "day":
                    rel_date = album.getString("release_date");
            }

            sb.append(String.join(",", album.getString("id"), album.getString("name"),
                    Integer.toString(album.getInt("total_tracks")), rel_date));
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    public void parseAndAppendAlbumByArtist(JSONObject albumObject) {
        JSONArray artistsOnAlbum = albumObject.getJSONArray("artists");

        for (int i = 0; i < artistsOnAlbum.length(); i++) {
            String album_artist = String.join(",", albumObject.getString("id"),
                    artistsOnAlbum.getJSONObject(i).getString("id"));
            CSVBuilder.appendToAlbumArtist(album_artist);
        }
    }

    public String parseAlbumTracks(String albumID, JSONObject albumTrackObject) {
        StringBuilder sb = new StringBuilder();
        JSONArray tracks = albumTrackObject.getJSONArray("items");

        JSONObject track;
        for (int i = 0; i < tracks.length(); i++) {
            track = tracks.getJSONObject(i);
            parseAndAppendTrackByArtist(track);
            parseAndAppendTrackOnAlbum(albumID, track);

            sb.append(String.join(",", track.getString("id"), track.getString("name"),
                    Integer.toString(track.getInt("duration_ms"))));
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    public void parseAndAppendTrackByArtist(JSONObject track) {
        JSONArray artistsOnTrack = track.getJSONArray("artists");

        for (int i = 0; i < artistsOnTrack.length(); i++) {
            String track_artist = String.join(",", track.getString("id"),
                    artistsOnTrack.getJSONObject(i).getString("id"));
            CSVBuilder.appendToTrackArtist(track_artist);
        }
    }

    public void parseAndAppendTrackOnAlbum(String albumID, JSONObject track) {
        String track_album = String.join(",", track.getString("id"), albumID);

        CSVBuilder.appendToTrackOnAlbum(track_album);
    }
}
