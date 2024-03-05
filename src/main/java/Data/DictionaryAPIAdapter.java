package Data;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DictionaryAPIAdapter implements Dictionary {
    private final String urlPath = "https://api.dictionaryapi.dev/api/v2/entries/en/";
    private HttpURLConnection con;

    @Override
    public boolean isNoun(String word) {
        String wordResults = makeRequest(word);
        return wordResults.indexOf("\"partOfSpeech\":\"noun\"") != -1;
    }

    @Override
    public boolean isVerb(String word) {
        String wordResults = makeRequest(word);
        return wordResults.indexOf("\"partOfSpeech\":\"verb\"") != -1;
    }

    @Override
    public boolean isWord(String word) {
        String wordResults = makeRequest(word);
        return wordResults.length() != 0;
    }

    private String makeRequest(String word) {
        //https://www.baeldung.com/java-http-request
        String result = "";
        try {
            URL url = new URL(urlPath + word);
            this.con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setUseCaches(false);
            con.setDoOutput(true);

            // send request
            int status = con.getResponseCode();

            //read response
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            result = content.toString();
        } catch (Exception e) {
        } finally {
            if (con != null) {
                con.disconnect();
            }
            return result;
        }
    }
}
