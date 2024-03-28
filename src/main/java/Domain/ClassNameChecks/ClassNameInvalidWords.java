package Domain.ClassNameChecks;

import Data.Dictionary;
import Data.Options;
import Domain.Issue;

import java.util.ArrayList;
import java.util.List;

public class ClassNameInvalidWords implements ClassNameCheck {

    private Dictionary dictionary;
    private final String ALLOW_ANYTHING = "allowAnything";
    private final String ALLOW_ANY_WORD = "allowAnyWord";
    private final String ALLOW_NOUN = "allowNoun";
    private final String ALLOW_VERB = "allowVerb";
    private final String ALLOW_COMPOUND_NAME = "allowCompoundName";
    private final String TRUE = "true";

    public ClassNameInvalidWords(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
    @Override
    public List<Issue> doClassNameCheck(String className, Options options) {
        ArrayList<Issue> issues = new ArrayList<>();
        boolean anythingAllowed = options.hasKey(ALLOW_ANYTHING) && options.get(ALLOW_ANYTHING).equals(TRUE);
        boolean anyWordAllowed = options.hasKey(ALLOW_ANY_WORD) && options.get(ALLOW_ANY_WORD).equals(TRUE);
        boolean nounAllowed = options.hasKey(ALLOW_NOUN) && options.get(ALLOW_NOUN).equals(TRUE);
        boolean verbAllowed = options.hasKey(ALLOW_VERB) && options.get(ALLOW_VERB).equals(TRUE);
        boolean compoundNameAllowed = options.hasKey(ALLOW_COMPOUND_NAME) && options.get(ALLOW_COMPOUND_NAME).equals(TRUE);
        ArrayList<String> words = new ArrayList<>();
        if (compoundNameAllowed) {
            int startIndex = 0;
            for (int i = 1; i < className.length(); i++) {
                if (Character.isUpperCase(className.charAt(i))) {
                    words.add(className.substring(startIndex, i));
                    startIndex = i;
                }
                if (i == className.length() - 1) {
                    words.add(className.substring(startIndex));
                }
            }
        } else {
            words.add(className);
        }
        for (String word: words) {
            if (anythingAllowed) {
            } else if (anyWordAllowed) {
                if (!dictionary.isWord(word)) {
                    Issue issue = new Issue();
                    issue.setMessage("class name contains invalid word: " + word + " is not a word");
                    issues.add(issue);
                }
            } else if (nounAllowed && verbAllowed) {
                if (!(dictionary.isNoun(word) || dictionary.isVerb(word))) {
                    Issue issue = new Issue();
                    issue.setMessage("class name contains invalid word: " + word + " is not a noun or verb");
                    issues.add(issue);
                }
            } else if (nounAllowed) {
                if (!dictionary.isNoun(word)) {
                    Issue issue = new Issue();
                    issue.setMessage("class name contains invalid word: " + word + " is not a noun");
                    issues.add(issue);
                }
            } else if (verbAllowed) {
                if (!dictionary.isVerb(word)) {
                    Issue issue = new Issue();
                    issue.setMessage("class name contains invalid word: " + word + " is not a verb");
                    issues.add(issue);
                }
            } else {
                Issue issue = new Issue();
                issue.setMessage("class name contains invalid word: " + word);
                issues.add(issue);
            }
        }

        return issues;
    }

    @Override
    public Options getDefaultOptions() {
        ArrayList<String> optionsKeys = new ArrayList<>();
        ArrayList<String> optionsValues = new ArrayList<>();
        optionsKeys.add("allowCompoundName");
        optionsValues.add("true");
        optionsKeys.add("allowNoun");
        optionsValues.add("true");
        optionsKeys.add("allowVerb");
        optionsValues.add("true");
        optionsKeys.add("allowAnyWord");
        optionsValues.add("false");
        optionsKeys.add("allowAnything");
        optionsValues.add("false");
        return new Options(optionsKeys, optionsValues);
    }
}
