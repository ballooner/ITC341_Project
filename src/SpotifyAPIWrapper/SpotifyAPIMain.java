package SpotifyAPIWrapper;

import CSV.CSVBuilder;
import Parsing.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpotifyAPIMain {

    public static void main(String[] args) throws JSONException, Exception {

        SpotifyAPI spotifyAPI = new SpotifyAPI("45e695c1b26945f78769608ddad62be6");

        spotifyAPI.requestToken(System.getenv("SpotifySecret"));

        /*
        JSONObject request = spotifyAPI.getArtist("0nmQIMXWTXfhgOBdNzhGOs");

        JSONParser parser = new JSONParser();
        String artistInfo = parser.parseArtistInfo(request);
        CSVBuilder.appendToArtists(artistInfo);

        JSONObject albumRequest = spotifyAPI.getArtistAlbums("0nmQIMXWTXfhgOBdNzhGOs", 20, 0);
        String albums = parser.parseArtistAlbums(albumRequest);
        CSVBuilder.appendToAlbums(albums);

        JSONObject trackRequest = spotifyAPI.getAlbumTracks("50YNY0xy9uJ0U9eFQBdLJa", 20, 0);
        String tracks = parser.parseAlbumTracks("50YNY0xy9uJ0U9eFQBdLJa", trackRequest);
        CSVBuilder.appendToTracks(tracks);
*/
        List<String> artistsID = new ArrayList<String>(Arrays.asList(
                "0nmQIMXWTXfhgOBdNzhGOs",
                "2yEwvVSSSUkcLeSTNyHKh8",
                "3RNrq3jvMZxD9ZyoOZbQOD",
                "05fG473iIaoy82BF1aGhL8",
                "6XyY86QOPPrYVGvF9ch6wz",
                "5eAWCfyUhZtHHtBdNk56l1",
                "6B5c4sch27tWHAGdarpPaW"
        ));

        for (int i = 0; i < artistsID.size(); i++) {
            JSONObject request = spotifyAPI.getArtist(artistsID.get(i));

            JSONParser parser = new JSONParser();
            String artistInfo = parser.parseArtistInfo(request);
            CSVBuilder.appendToArtists(artistInfo);

            JSONObject albumRequest = spotifyAPI.getArtistAlbums(artistsID.get(i), 20, 0);
            String albums = parser.parseArtistAlbums(albumRequest);
            CSVBuilder.appendToAlbums(albums);
            String[] albumIDs = albums.replace("\n", "").split(",");

            JSONObject trackRequest;
            for (int j = 0; j < albumIDs.length; j += 3) {
                System.out.println(albumIDs[j]);
                trackRequest = spotifyAPI.getAlbumTracks(albumIDs[j], 20, 0);
                String tracks = parser.parseAlbumTracks(albumIDs[j], trackRequest);
                CSVBuilder.appendToTracks(tracks);
            }
        }
    }
}
