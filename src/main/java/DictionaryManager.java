import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryManager {
    private File sourceDictionaryFile;
    private Dictionary dictionary;

    public DictionaryManager(File sourceDictionaryFile) {
        this.sourceDictionaryFile = sourceDictionaryFile;
        loadDictionary();
    }

    public DictionaryManager() {
    }

    public File getSourceDictionaryFile() {
        return sourceDictionaryFile;
    }

    public void viewDictionary() {
        System.out.println(dictionary);
    }

    public void setSourceDictionaryFile(File sourceDictionaryFile) {
        this.sourceDictionaryFile = sourceDictionaryFile;
        loadDictionary();
    }

    private void loadDictionary() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(sourceDictionaryFile));
            dictionary = getDictionaryTemplate(bufferedReader.readLine());
            while (bufferedReader.ready()) {
                addWordToDictionary(bufferedReader.readLine());
            }

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    private Dictionary getDictionaryTemplate(String fromToLanguages) {
        String[] fromToLanguage = fromToLanguages.split(";");
        String fromLanguage = fromToLanguage[0];
        String toLanguage = fromToLanguage[1];
        Map<String, List<String>> emptyDictionary = new HashMap<>();
        Dictionary dictionary = new Dictionary(emptyDictionary, fromLanguage, toLanguage);
        return dictionary;
    }

    private void addWordToDictionary(String wordWithTranslations) {
        String[] separatedWordWithTranslation = wordWithTranslations.split(";");
        String wordForTranslation = separatedWordWithTranslation[0];
        List<String> translations = new ArrayList<String>();
        for (int i = 1; i < separatedWordWithTranslation.length; i++) {
            translations.add(separatedWordWithTranslation[i]);
        }
        dictionary.addWordTranslation(wordForTranslation, translations);
    }

    public void addNewWordTest() {
        String newWord = "Power";
        List<String> translation = new ArrayList<>();
        translation.add("Влада");
        translation.add("Потужність");
        dictionary.addWordTranslation(newWord, translation);
        System.out.println(dictionary);

    }

    public void addNewTranslationToExistWordTest() {
        String newWord = "Power";
        List<String> translation = new ArrayList<>();
        translation.add("Міць");
        dictionary.addWordTranslation(newWord, translation);
        System.out.println(dictionary);
    }

    public void saveDictionaryToFile() {
        String sourceDictionaryFileName = "./src/main/resources/" + dictionary.getSourceLanguage() + "To" + dictionary.getTargetLanguage() + ".txt";
        if (sourceDictionaryFile == null) {
            sourceDictionaryFile = new File(sourceDictionaryFileName);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(sourceDictionaryFile))) {
            String dictionaryFromToLanguages = dictionary.getSourceLanguage() + dictionary.getTargetLanguage();
            bufferedWriter.write(dictionaryFromToLanguages);
            bufferedWriter.newLine();
            Map<String, List<String>> dictionaryMap = dictionary.getDictionary();
            for (String word : dictionaryMap.keySet()) {
                StringBuffer line = new StringBuffer(word);
                for (String translation : dictionaryMap.get(word)) {
                    line.append(";" + translation);
                }
                bufferedWriter.write(line.toString());
                bufferedWriter.newLine();
            }

        } catch (IOException e) {

        }
    }

    public void newDictionaryCreator() {
        String fromLanguage = "English";
        String toLanguage = "Ukrainian";
        List<String> translations = new ArrayList<String>();
        dictionary = getDictionaryTemplate(fromLanguage + ";" + toLanguage);
        String word = "Set";
        translations.add("Набор");
        translations.add("Комплект");
        translations.add("Встановлювати");
        dictionary.addWordTranslation(word, translations);
        word = "Get";
        translations = new ArrayList<String>();
        translations.add("Получати");
        translations.add("Попасти");
        translations.add("Мати");
        dictionary.addWordTranslation(word, translations);
        word = "Put";
        translations = new ArrayList<String>();
        translations.add("Класти");
        translations.add("Ставити");
        translations.add("Саджати");
        translations.add("ПОМИЛКА");

        dictionary.addWordTranslation(word, translations);
        word = "Son";
        translations = new ArrayList<String>();
        translations.add("Син");
        dictionary.addWordTranslation(word, translations);
        saveDictionaryToFile();
        dictionary.toString();
    }

    public void getTranslationAndRemoveTranslationTest() {
        System.out.println("Word : Put. Translation : " + dictionary.getTranslation("Put"));
        dictionary.deleteTranslationFromWord("Power","ПОМИЛКА");
        dictionary.toString();
        dictionary.deleteWordFromDictionary("Son");
        dictionary.toString();
    }
}
