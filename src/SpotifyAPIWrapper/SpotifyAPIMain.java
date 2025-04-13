package SpotifyAPIWrapper;

import CSV.CSVBuilder;
import Parsing.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;

public class SpotifyAPIMain {

    public static void main(String[] args) throws JSONException, Exception {
        SpotifyAPI spotifyAPI = new SpotifyAPI("45e695c1b26945f78769608ddad62be6");

        spotifyAPI.requestToken(System.getenv("SpotifySecret"));

        JSONObject request = spotifyAPI.getArtist("0nmQIMXWTXfhgOBdNzhGOs");

        JSONParser parser = new JSONParser();
        String artistInfo = parser.parseArtistInfo(request);
        CSVBuilder.appendToArtists(artistInfo);

        String artistGenre = parser.parseArtistGenre(request);
        CSVBuilder.appendToGenre(artistGenre);

        JSONObject albumRequest = spotifyAPI.getArtistAlbums("0nmQIMXWTXfhgOBdNzhGOs", 20, 0);
        String albums = parser.parseArtistAlbums(albumRequest);
        CSVBuilder.appendToAlbums(albums);

    }
}
