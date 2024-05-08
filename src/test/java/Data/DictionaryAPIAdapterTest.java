package Data;

import org.easymock.EasyMock;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.junit.Assert.assertEquals;

public class DictionaryAPIAdapterTest {

    @Test
    public void isWordFalseTest() throws IOException {
        // Record
        HttpURLConnection connection = EasyMock.mock(HttpURLConnection.class);
        InputReader reader = EasyMock.mock(InputReader.class);
        DictionaryAPIAdapter dictionaryAPIAdapter = new DictionaryAPIAdapter(connection, reader);
        String word = "cvned";
        boolean expectedResult = false;

        connection.setRequestMethod("GET");
        EasyMock.expectLastCall();
        connection.addRequestProperty("User-Agent", "Mozilla/4.0");
        EasyMock.expectLastCall();
        connection.setUseCaches(false);
        EasyMock.expectLastCall();
        connection.setDoOutput(true);
        EasyMock.expectLastCall();
        EasyMock.expect(connection.getResponseCode()).andReturn(200);
        EasyMock.expect(reader.nextLine()).andReturn("");
        EasyMock.expect(reader.nextLine()).andReturn(null);
        reader.close();
        EasyMock.expectLastCall();
        connection.disconnect();
        EasyMock.expectLastCall();

        // Replay
        EasyMock.replay(connection, reader);
        boolean isWord = dictionaryAPIAdapter.isWord(word);

        // Verify
        EasyMock.verify(connection, reader);
        assertEquals(expectedResult, isWord);
    }

    @Test
    public void isWordTrueTest() throws IOException {
        // Record
        HttpURLConnection connection = EasyMock.mock(HttpURLConnection.class);
        InputReader reader = EasyMock.mock(InputReader.class);
        DictionaryAPIAdapter dictionaryAPIAdapter = new DictionaryAPIAdapter(connection, reader);
        String word = "run";
        boolean expectedResult = true;

        connection.setRequestMethod("GET");
        EasyMock.expectLastCall();
        connection.addRequestProperty("User-Agent", "Mozilla/4.0");
        EasyMock.expectLastCall();
        connection.setUseCaches(false);
        EasyMock.expectLastCall();
        connection.setDoOutput(true);
        EasyMock.expectLastCall();
        EasyMock.expect(connection.getResponseCode()).andReturn(200);
        EasyMock.expect(reader.nextLine()).andReturn("\"partOfSpeech\":\"verb\"");
        EasyMock.expect(reader.nextLine()).andReturn(null);
        reader.close();
        EasyMock.expectLastCall();
        connection.disconnect();
        EasyMock.expectLastCall();

        // Replay
        EasyMock.replay(connection, reader);
        boolean isWord = dictionaryAPIAdapter.isWord(word);

        // Verify
        EasyMock.verify(connection, reader);
        assertEquals(expectedResult, isWord);
    }

    @Test
    public void isNounFalseTest() throws IOException {
        // Record
        HttpURLConnection connection = EasyMock.mock(HttpURLConnection.class);
        InputReader reader = EasyMock.mock(InputReader.class);
        DictionaryAPIAdapter dictionaryAPIAdapter = new DictionaryAPIAdapter(connection, reader);
        String word = "run";
        boolean expectedResult = false;

        connection.setRequestMethod("GET");
        EasyMock.expectLastCall();
        connection.addRequestProperty("User-Agent", "Mozilla/4.0");
        EasyMock.expectLastCall();
        connection.setUseCaches(false);
        EasyMock.expectLastCall();
        connection.setDoOutput(true);
        EasyMock.expectLastCall();
        EasyMock.expect(connection.getResponseCode()).andReturn(200);
        EasyMock.expect(reader.nextLine()).andReturn("\"partOfSpeech\":\"verb\"");
        EasyMock.expect(reader.nextLine()).andReturn(null);
        reader.close();
        EasyMock.expectLastCall();
        connection.disconnect();
        EasyMock.expectLastCall();

        // Replay
        EasyMock.replay(connection, reader);
        boolean isWord = dictionaryAPIAdapter.isNoun(word);

        // Verify
        EasyMock.verify(connection, reader);
        assertEquals(expectedResult, isWord);
    }

    @Test
    public void isNounTrueTest() throws IOException {
        // Record
        HttpURLConnection connection = EasyMock.mock(HttpURLConnection.class);
        InputReader reader = EasyMock.mock(InputReader.class);
        DictionaryAPIAdapter dictionaryAPIAdapter = new DictionaryAPIAdapter(connection, reader);
        String word = "man";
        boolean expectedResult = true;

        connection.setRequestMethod("GET");
        EasyMock.expectLastCall();
        connection.addRequestProperty("User-Agent", "Mozilla/4.0");
        EasyMock.expectLastCall();
        connection.setUseCaches(false);
        EasyMock.expectLastCall();
        connection.setDoOutput(true);
        EasyMock.expectLastCall();
        EasyMock.expect(connection.getResponseCode()).andReturn(200);
        EasyMock.expect(reader.nextLine()).andReturn("\"partOfSpeech\":\"noun\"");
        EasyMock.expect(reader.nextLine()).andReturn(null);
        reader.close();
        EasyMock.expectLastCall();
        connection.disconnect();
        EasyMock.expectLastCall();

        // Replay
        EasyMock.replay(connection, reader);
        boolean isWord = dictionaryAPIAdapter.isNoun(word);

        // Verify
        EasyMock.verify(connection, reader);
        assertEquals(expectedResult, isWord);
    }

    @Test
    public void isVerbFalseTest() throws IOException {
        // Record
        HttpURLConnection connection = EasyMock.mock(HttpURLConnection.class);
        InputReader reader = EasyMock.mock(InputReader.class);
        DictionaryAPIAdapter dictionaryAPIAdapter = new DictionaryAPIAdapter(connection, reader);
        String word = "man";
        boolean expectedResult = false;

        connection.setRequestMethod("GET");
        EasyMock.expectLastCall();
        connection.addRequestProperty("User-Agent", "Mozilla/4.0");
        EasyMock.expectLastCall();
        connection.setUseCaches(false);
        EasyMock.expectLastCall();
        connection.setDoOutput(true);
        EasyMock.expectLastCall();
        EasyMock.expect(connection.getResponseCode()).andReturn(200);
        EasyMock.expect(reader.nextLine()).andReturn("\"partOfSpeech\":\"noun\"");
        EasyMock.expect(reader.nextLine()).andReturn(null);
        reader.close();
        EasyMock.expectLastCall();
        connection.disconnect();
        EasyMock.expectLastCall();

        // Replay
        EasyMock.replay(connection, reader);
        boolean isWord = dictionaryAPIAdapter.isVerb(word);

        // Verify
        EasyMock.verify(connection, reader);
        assertEquals(expectedResult, isWord);
    }

    @Test
    public void isVerbTrueTest() throws IOException {
        // Record
        HttpURLConnection connection = EasyMock.mock(HttpURLConnection.class);
        InputReader reader = EasyMock.mock(InputReader.class);
        DictionaryAPIAdapter dictionaryAPIAdapter = new DictionaryAPIAdapter(connection, reader);
        String word = "run";
        boolean expectedResult = true;

        connection.setRequestMethod("GET");
        EasyMock.expectLastCall();
        connection.addRequestProperty("User-Agent", "Mozilla/4.0");
        EasyMock.expectLastCall();
        connection.setUseCaches(false);
        EasyMock.expectLastCall();
        connection.setDoOutput(true);
        EasyMock.expectLastCall();
        EasyMock.expect(connection.getResponseCode()).andReturn(200);
        EasyMock.expect(reader.nextLine()).andReturn("\"partOfSpeech\":\"verb\"");
        EasyMock.expect(reader.nextLine()).andReturn(null);
        reader.close();
        EasyMock.expectLastCall();
        connection.disconnect();
        EasyMock.expectLastCall();

        // Replay
        EasyMock.replay(connection, reader);
        boolean isWord = dictionaryAPIAdapter.isVerb(word);

        // Verify
        EasyMock.verify(connection, reader);
        assertEquals(expectedResult, isWord);
    }

    @Test
    public void isWordFalseIntegrationTest() {
        // Record
        DictionaryAPIAdapter dictionaryAPIAdapter = new DictionaryAPIAdapter();
        String word = "cvned";
        boolean expectedResult = false;

        // Replay
        boolean isWord = dictionaryAPIAdapter.isWord(word);

        // Verify
        assertEquals(expectedResult, isWord);
    }

    @Test
    public void isWordTrueIntegrationTest() {
        // Record
        DictionaryAPIAdapter dictionaryAPIAdapter = new DictionaryAPIAdapter();
        String word = "mouth";
        boolean expectedResult = true;

        // Replay
        boolean isWord = dictionaryAPIAdapter.isWord(word);

        // Verify
        assertEquals(expectedResult, isWord);
    }

    @Test
    public void isNounFalseNotWordIntegrationTest() {
        // Record
        DictionaryAPIAdapter dictionaryAPIAdapter = new DictionaryAPIAdapter();
        String word = "cvned";
        boolean expectedResult = false;

        // Replay
        boolean isNoun = dictionaryAPIAdapter.isNoun(word);

        // Verify
        assertEquals(expectedResult, isNoun);
    }

    @Test
    public void isNounFalseNotNounIntegrationTest() {
        // Record
        DictionaryAPIAdapter dictionaryAPIAdapter = new DictionaryAPIAdapter();
        String word = "forget";
        boolean expectedResult = false;

        // Replay
        boolean isNoun = dictionaryAPIAdapter.isNoun(word);

        // Verify
        assertEquals(expectedResult, isNoun);
    }

    @Test
    public void isNounTrueIntegrationTest() {
        // Record
        DictionaryAPIAdapter dictionaryAPIAdapter = new DictionaryAPIAdapter();
        String word = "Person";
        boolean expectedResult = true;

        // Replay
        boolean isNoun = dictionaryAPIAdapter.isNoun(word);

        // Verify
        assertEquals(expectedResult, isNoun);
    }

    @Test
    public void isVerbFalseNotWordIntegrationTest() {
        // Record
        DictionaryAPIAdapter dictionaryAPIAdapter = new DictionaryAPIAdapter();
        String word = "cvned";
        boolean expectedResult = false;

        // Replay
        boolean isVerb = dictionaryAPIAdapter.isVerb(word);

        // Verify
        assertEquals(expectedResult, isVerb);
    }

    @Test
    public void isVerbFalseNotVerbIntegrationTest() {
        // Record
        DictionaryAPIAdapter dictionaryAPIAdapter = new DictionaryAPIAdapter();
        String word = "Symbolism";
        boolean expectedResult = false;

        // Replay
        boolean isVerb = dictionaryAPIAdapter.isVerb(word);

        // Verify
        assertEquals(expectedResult, isVerb);
    }

    @Test
    public void isVerbTrueIntegrationTest() {
        // Record
        DictionaryAPIAdapter dictionaryAPIAdapter = new DictionaryAPIAdapter();
        String word = "run";
        boolean expectedResult = true;

        // Replay
        boolean isVerb = dictionaryAPIAdapter.isVerb(word);

        // Verify
        assertEquals(expectedResult, isVerb);
    }
}
