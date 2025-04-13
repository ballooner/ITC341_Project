package SpotifyAPIWrapper;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Wrapper class for Spotify's Web API.
 *
 * @author duley1jj
 */

public class SpotifyAPI {
    private String clientID;
    private String accessToken;

    public SpotifyAPI(String clientID) {
        this.clientID = clientID;
    }

    /**
     * Requests an access token from the Spotify API and stores it in a class variable
     */
    public void requestToken(String clientSecret) throws URISyntaxException, IOException, InterruptedException{
        String postBody = String.format("grant_type=client_credentials&client_id=%s&client_secret=%s", clientID, System.getenv("SpotifySecret"));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://accounts.spotify.com/api/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(postBody))
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .proxy(ProxySelector.getDefault())
                .build()
                .send(request, BodyHandlers.ofString());

        String responseBody = response.body();
        JSONObject obj = new JSONObject(responseBody);

        accessToken = obj.get("access_token").toString();
    }

    public String getToken()
    {
        return accessToken;
    }

    /**
     * Call and return Spotify's Get Artist endpoint response
     * @param artistID		The Spotify ID of the Artist
     * @return				A JSONObject containing a Spotify API response
     */
    public JSONObject getArtist(String artistID) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.spotify.com/v1/artists/" + artistID))
                .header("Authorization", "Bearer " + accessToken)
                .GET()
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .proxy(ProxySelector.getDefault())
                .build()
                .send(request, BodyHandlers.ofString());

        return new JSONObject(response.body());
    }


    /**
     * Call and return Spotify's Get Several Artists endpoint response
     * @param artistIDs		A String array containing the Spotify artist IDs to request
     * @return				A JSONObject containing a Spotify API response
     */
    public JSONObject getSeveralArtist(String[] artistIDs) throws URISyntaxException, IOException, InterruptedException {
        StringBuilder idListBuilder = new StringBuilder();
        idListBuilder.append(artistIDs[0]);

        for (int i = 1; i < artistIDs.length; i++)
        {
            idListBuilder.append(",").append(artistIDs[i]);
        }

        String ids = idListBuilder.toString();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.spotify.com/v1/artists?ids=" + ids))
                .header("Authorization", "Bearer " + accessToken)
                .GET()
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .proxy(ProxySelector.getDefault())
                .build()
                .send(request, BodyHandlers.ofString());

        return new JSONObject(response.body());
    }


    /**
     * Call and return Spotify's Get Artists Albums endpoint response
     * @param artistID		The Spotify ID of the Artist
     * @param limit			The max number of items to return
     * @param offset		The index of the first item, where 0 is the first item
     * @return				A JSONObject containing a Spotify API response
     */
    public JSONObject getArtistAlbums(String artistID, int limit, int offset) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.spotify.com/v1/artists/" + artistID + "/albums?offset="
                + offset + "&" + "limit=" + limit))
                .header("Authorization", "Bearer " + accessToken)
                .GET()
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .proxy(ProxySelector.getDefault())
                .build()
                .send(request, BodyHandlers.ofString());

        String responseBody = response.body();
        JSONObject obj = new JSONObject(responseBody);

        return obj;
    }


    /**
     * Call and return Spotify's Get Album Tracks endpoint response
     * @param albumID 		The Spotify ID of the album
     * @param limit 		The max number of items to return
     * @param offset 		The index of the first item where 0 is the first
     * @return 				A JSONObject containing a Spotify API response
     */
    public JSONObject getAlbumTracks(String albumID, int limit, int offset) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.spotify.com/v1/albums/" + albumID + "/tracks?offset="
                        + offset + "&" + "limit=" + limit))
                .header("Authorization", "Bearer " + accessToken)
                .GET()
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .proxy(ProxySelector.getDefault())
                .build()
                .send(request, BodyHandlers.ofString());

        return new JSONObject(response.body());
    }


    /**
     * Call and return Spotify's Get Track endpoint response
     * @param trackID 		The Spotify ID of the track
     * @return 				A JSONObject containing a Spotify API response
     */
    public JSONObject getTrack(String trackID) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.spotify.com/v1/tracks/" + trackID))
                .header("Authorization", "Bearer " + accessToken)
                .GET()
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .proxy(ProxySelector.getDefault())
                .build()
                .send(request, BodyHandlers.ofString());

        return new JSONObject(response.body());
    }


    /**
     * Call and return Spotify's Get Several Tracks endpoint response
     * @param trackIDs 		A String array containing the Spotify track IDs to request
     * @return 				A JSONObject containing a Spotify API response
     */
    public JSONObject getSeveralTracks(String[] trackIDs) throws URISyntaxException, IOException, InterruptedException {
        StringBuilder idListBuilder = new StringBuilder();
        idListBuilder.append(trackIDs[0]);

        for (int i = 1; i < trackIDs.length; i++)
        {
            idListBuilder.append(",").append(trackIDs[i]);
        }

        String ids = idListBuilder.toString();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.spotify.com/v1/tracks?ids=" + ids))
                .header("Authorization", "Bearer " + accessToken)
                .GET()
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .proxy(ProxySelector.getDefault())
                .build()
                .send(request, BodyHandlers.ofString());

        return new JSONObject(response.body());
    }
}
