package Data;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DictionaryAPIAdapterTest {

    @Test
    public void isWordFalseTest() {
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
    public void isWordTrueTest() {
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
    public void isNounFalseNotWordTest() {
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
    public void isNounFalseNotNounTest() {
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
    public void isNounTrueTest() {
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
    public void isVerbFalseNotWordTest() {
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
    public void isVerbFalseNotVerbTest() {
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
    public void isVerbTrueTest() {
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
