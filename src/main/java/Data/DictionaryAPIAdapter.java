package Data;

import java.net.HttpURLConnection;
import java.net.URL;

public class DictionaryAPIAdapter implements Dictionary {
    private final String urlPath = "https://api.dictionaryapi.dev/api/v2/entries/en/";
    private HttpURLConnection connection;
    private InputReader reader;

    public DictionaryAPIAdapter() {
        this.connection = null;
        this.reader = null;
    }

    public DictionaryAPIAdapter(HttpURLConnection con, InputReader reader) {
        this.connection = con;
        this.reader = reader;
    }

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
        HttpURLConnection con = null;
        try {
            if (this.connection == null) {
                URL url = new URL(urlPath + word);
                con = (HttpURLConnection) url.openConnection();
            } else {
                con = this.connection;
            }

            con.setRequestMethod("GET");
            con.addRequestProperty("User-Agent", "Mozilla/4.0");
            con.setUseCaches(false);
            con.setDoOutput(true);

            // send request
            int status = con.getResponseCode();

            //read response
            InputReader inputReader = null;
            if (this.reader == null) {
                inputReader = new InputReader(con.getInputStream());
            } else {
                inputReader = this.reader;
            }

            String inputLine;
            while ((inputLine = inputReader.nextLine()) != null) {
                result = result + inputLine;
            }
            inputReader.close();
        } catch (Exception e) {
        } finally {
            if (con != null) {
                con.disconnect();
            }
            return result;
        }
    }
}
