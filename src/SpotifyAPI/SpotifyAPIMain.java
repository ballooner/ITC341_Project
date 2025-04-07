package SpotifyAPI;

import org.json.JSONException;
import org.json.JSONObject;

public class SpotifyAPIMain {

    public static void main(String[] args) throws JSONException, Exception {
        SpotifyAPI spotifyAPI = new SpotifyAPI("45e695c1b26945f78769608ddad62be6");

        spotifyAPI.requestToken(System.getenv("SpotifySecret"));


        JSONObject request = spotifyAPI.getArtist("0nmQIMXWTXfhgOBdNzhGOs");
        System.out.println(request.get("name"));
    }
}
