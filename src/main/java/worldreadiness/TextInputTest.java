package worldreadiness;

import com.ibm.icu.text.BreakIterator;

import java.util.Locale;

public class TextInputTest {
    public static void main(String[] args) {
        String text = "人口密度も日本の都道府県のなかで最も大きい。";
        BreakIterator iterator = BreakIterator.getWordInstance(Locale.JAPANESE);
        iterator.setText(text);

        int start = iterator.first();
        int next;
        while (true) {
            next = iterator.next();
            if (next == BreakIterator.DONE) break;
            System.out.print(text.substring(start, next) + " ");
            start = next;
        }

        System.out.println();
        String arabic = "\u0644\u0628\u0646\u0627\u0646";
        System.out.println(arabic);

        String x = "ส";
        System.out.println(x.codePointAt(0));
    }
}
