package Praktikum11;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelSearchServer implements CommandExecutor{

    private static final String REGEX = "(\\+41)\\d{9}";

    @Override
    public String execute(String command) throws Exception {
        URL url = buildURL(command);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        Pattern pattern = Pattern.compile(REGEX);
        System.out.println("****** Search Content of URL for tel. number ********");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
            String input;
            while ((input = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(input);
                while(matcher.find()) {
                    System.out.println("found: " + matcher.group());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "DONE";
    }

    private URL buildURL(String https_url) {
        URL url;
        try {
            url = new URL(https_url);
        } catch(MalformedURLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return url;
    }
}
