package Parsing;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONParser {

    public String parseArtistInfo(JSONObject artistObject) {
        String artistName, artistID;

        artistName = artistObject.getString("name");
        artistID = artistObject.getString("id");

        return String.join(",", artistID, artistName);
    }

    public String parseArtistGenre(JSONObject artistObject) {
        String artistID = artistObject.getString("id");

        JSONArray genreArr = artistObject.getJSONArray("genres");

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < genreArr.length(); i++) {
            sb.append(String.join(",", artistID, genreArr.getString(i)));
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }
}
