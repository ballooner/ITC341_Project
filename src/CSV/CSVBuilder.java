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
}
