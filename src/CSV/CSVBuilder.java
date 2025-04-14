package CSV;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVBuilder {

    public static void appendToArtists(String artistInfo) {
        String path = new File("csv files/artists.csv").getAbsolutePath();

        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(artistInfo + System.lineSeparator());
            System.out.println("Data appended");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void appendToGenre(String id_genre) {
        String path = new File("csv files/artist_genre.csv").getAbsolutePath();

        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(id_genre + System.lineSeparator());
            System.out.println("Data appended");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void appendToAlbums(String albums) {
        String path = new File("csv files/albums.csv").getAbsolutePath();
        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(albums + System.lineSeparator());
            System.out.println("Data appended");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void appendToTracks(String tracks) {
        String path = new File("csv files/tracks.csv").getAbsolutePath();
        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(tracks + System.lineSeparator());
            System.out.println("Data appended");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void appendToAlbumArtist(String album_artist) {
        String path = new File("csv files/album_artist.csv").getAbsolutePath();
        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(album_artist + System.lineSeparator());
            System.out.println("Data appended");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void appendToTrackArtist(String track_artist) {
        String path = new File("csv files/track_artist.csv").getAbsolutePath();
        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(track_artist + System.lineSeparator());
            System.out.println("Data appended");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void appendToTrackOnAlbum(String track_album) {
        String path = new File("csv files/track_album.csv").getAbsolutePath();
        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(track_album + System.lineSeparator());
            System.out.println("Data appended");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
