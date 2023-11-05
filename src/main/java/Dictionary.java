import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionary {
    private Map<String, List<String>> dictionary;
    private String sourceLanguage;
    private String targetLanguage;

    public Dictionary(Map<String, List<String>> dictionary, String sourceLanguage, String targetLanguage) {
        this.dictionary = dictionary;
        this.sourceLanguage = sourceLanguage;
        this.targetLanguage = targetLanguage;
    }

    public Map<String, List<String>> getDictionary() {
        return dictionary;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public List<String> getTranslation(String wordForTranslation) {
        return dictionary.get(wordForTranslation);
    }
    public void deleteWordFromDictionary(String wordToDelete){
        dictionary.remove(wordToDelete);
    }
    public void deleteTranslationFromWord(String word, String translationToDelete){
        dictionary.get(word).remove(translationToDelete);
    }

    @Override
    public String toString() {
        System.out.println("Dictionary from : " + sourceLanguage + " to : " + targetLanguage);
        for (String element : dictionary.keySet()) {
            StringBuilder line = new StringBuilder("Word : ");
            line.append(element + " Translations :");
            List<String> translations = dictionary.get(element);
            for (String translationElement : translations) {
                line.append(translationElement + ";");
            }
            System.out.println(line);
        }
        return "";
    }

    public void addWordTranslation(String wordForTranslation, List<String> translation) {
        if (dictionary.containsKey(wordForTranslation)) {
            AddToExistingTranslation(wordForTranslation, translation);
        } else {
            AddNewWordToDictionary(wordForTranslation, translation);
        }
    }

    private void AddToExistingTranslation(String wordForTranslation, List<String> translations) {
        List<String> existTranslations = dictionary.get(wordForTranslation);
        for (String element : translations) {
            if (!existTranslations.contains(element)) {
                existTranslations.add(element);
            }
        }
    }

    private void AddNewWordToDictionary(String wordForTranslation, List<String> translations) {
        dictionary.put(wordForTranslation, translations);
    }

}
