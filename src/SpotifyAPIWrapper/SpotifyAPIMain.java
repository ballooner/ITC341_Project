package SpotifyAPIWrapper;

import CSV.CSVBuilder;
import Parsing.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpotifyAPIMain {

    public static void main(String[] args) throws JSONException, Exception {

        SpotifyAPI spotifyAPI = new SpotifyAPI("45e695c1b26945f78769608ddad62be6");

        spotifyAPI.requestToken(System.getenv("SpotifySecret"));

        String[] artistsID = {
                "0nmQIMXWTXfhgOBdNzhGOs",
                "2yEwvVSSSUkcLeSTNyHKh8",
                "3RNrq3jvMZxD9ZyoOZbQOD",
                "05fG473iIaoy82BF1aGhL8",
                "6XyY86QOPPrYVGvF9ch6wz",
                "5eAWCfyUhZtHHtBdNk56l1",
                "6B5c4sch27tWHAGdarpPaW",
                "6P7H3ai06vU1sGvdpBwDmE",
                "1aSxMhuvixZ8h9dK9jIDwL",
                "0WwSkZ7LtFUFjGjMZBMt6T",
                "2hO4YtXUFJiUYS2uYFvHNK",
                "3Uqu1mEdkUJxPe7s31n1M9",
                "59dGdCSieplkG6HWFQbyYB",
                "25uiPmTg16RbhZWAqwLBy5",
                "5a2w2tgpLwv26BYJf2qYwu",
                "5Wabl1lPdNOeIn0SQ5A1mp",
                "5wAYxVWrOqP8vGrsSmfNnk",
                "3U2U4TR03ZuStsizrv0EJB",
                "1G1mX30GpUJqOr1QU2eBSs",
                "3LtBdgNHdH0Ix8hCFZ4NJG"
        };

        for (String id : artistsID) {
            JSONObject request = spotifyAPI.getArtist(id);

            JSONParser parser = new JSONParser();
            String artistInfo = parser.parseArtistInfo(request);
            CSVBuilder.appendToArtists(artistInfo);

            JSONObject albumRequest = spotifyAPI.getArtistAlbums(id, 10, 0);
            String albums = parser.parseArtistAlbums(albumRequest);
            CSVBuilder.appendToAlbums(albums);

            JSONObject trackRequest;

            JSONArray albumsArr = albumRequest.getJSONArray("items");
            String albumID;
            for (int j = 0; j < albumsArr.length(); j++) {
                albumID = albumsArr.getJSONObject(j).getString("id");
                trackRequest = spotifyAPI.getAlbumTracks(albumID, 20, 0);
                String tracks = parser.parseAlbumTracks(albumID, trackRequest);
                CSVBuilder.appendToTracks(tracks);
            }
        }
    }
}
