import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebRequest {
    public static String hostSite = "http://localhost/NewmanSchedules/Website/";

    // This took forever. Why does Java make something so simple so complex?!?!
    public static String GET(String url) throws IOException {
        URL fUrl = new URL(hostSite + url);
        HttpURLConnection connection = (HttpURLConnection) fUrl.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder webContents = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            webContents.append(inputLine);
        }
        in.close();
        connection.disconnect();
        return webContents.toString();
    }
}
