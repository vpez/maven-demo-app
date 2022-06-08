package worldreadiness;

import com.ibm.icu.text.Collator;

import java.util.Arrays;
import java.util.Locale;

public class CollationTest {
    public static void main(String[] args) {
        var collator = Collator.getInstance(Locale.forLanguageTag("et-EE"));
        var str = new String[] {"Ängelholm", "Asmara", "Xanadu", "Zamboni"};
        Arrays.sort(str, collator::compare);
        System.out.println(String.join(", ", str));

        collator = Collator.getInstance(Locale.forLanguageTag("uz-UZ"));
        str = new String[] {"Bangui", "Chicago", "Shanghai", "Zagreb"};
        Arrays.sort(str, collator::compare);
        System.out.println(String.join(", ", str));
    }
}
