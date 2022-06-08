package worldreadiness;

import com.ibm.icu.text.Collator;
import com.ibm.icu.text.RuleBasedCollator;
import com.ibm.icu.text.StringSearch;

import java.text.StringCharacterIterator;
import java.util.Locale;

public class SearchingTest {
    public static void main(String[] args) {
        var text = "Thomas Müller and Bennie Muller are accomplished association football players.";
        var search = "muller";

        Collator collator = Collator.getInstance(Locale.forLanguageTag("de-DE"));
        collator.setStrength(Collator.PRIMARY);

        StringCharacterIterator iterator = new StringCharacterIterator(text);
        StringSearch stringSearch = new StringSearch(search, iterator, (RuleBasedCollator) collator);

        int pos = stringSearch.first();
        System.out.println("Found " + pos);
        while (pos != StringSearch.DONE) {
            pos = stringSearch.next();
            System.out.println("Found " + pos);
        }

        System.out.println("***");

        text = "Topkapı Palace is a famous landmark of Turkey.";
        search = Character.toString('\u0069');
        collator = Collator.getInstance(Locale.forLanguageTag("tr-TR"));
        collator.setStrength(Collator.PRIMARY);
        iterator = new StringCharacterIterator(text);
        stringSearch = new StringSearch(search, iterator, (RuleBasedCollator) collator);
        pos = stringSearch.first();
        System.out.println("Found " + pos);
        while (pos != StringSearch.DONE) {
            pos = stringSearch.next();
            System.out.println("Found " + pos);
        }

        System.out.println("***");

        text = "Saint Petersburg is written as Санкт-Петербург in Cyrillic.";
        search = Character.toString('\u0430');
        collator = Collator.getInstance(Locale.forLanguageTag("ru-RU"));
        collator.setStrength(Collator.PRIMARY);
        iterator = new StringCharacterIterator(text);
        stringSearch = new StringSearch(search, iterator, (RuleBasedCollator) collator);
        pos = stringSearch.first();
        System.out.println("Found " + pos);
        while (pos != StringSearch.DONE) {
            pos = stringSearch.next();
            System.out.println("Found " + pos);
        }
    }
}
