import java.io.File;

public class Main {
    public static void main(String[] args){
        DictionaryManager dictionaryManager = new DictionaryManager();
        dictionaryManager.newDictionaryCreator();
        dictionaryManager.addNewWordTest();
        dictionaryManager.addNewTranslationToExistWordTest();
        dictionaryManager.getTranslationAndRemoveTranslationTest();
        dictionaryManager.saveDictionaryToFile();
    }
}
