package worldreadiness;

import com.ibm.icu.lang.UCharacter;

import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.function.Consumer;

public class UnicodeTest {

    static Consumer<String> print = s -> {
        for (int i = 0; i < s.length(); i++) {
            System.out.print(Integer.toHexString(s.codePointAt(i)));
            System.out.print(" : ");
            System.out.println(UCharacter.getName(s.codePointAt(i)));
        }
        System.out.println();
    };

    public static void main(String[] args) {
        String text = "Hello";
//        print.accept(text);

        byte[] bytes = text.getBytes(StandardCharsets.UTF_16LE);
        for (byte b : bytes) {
            System.out.print(Integer.toHexString(b));
            System.out.print(" ");
        }

        System.out.println();

//        String nfdString = Normalizer.normalize("\u01df", Normalizer.Form.NFD);
//        print.accept(nfdString);

//        String nfcString = Normalizer.normalize(nfdString, Normalizer.Form.NFC);
//        print.accept(nfcString);
//
        String nfkdString = Normalizer.normalize("\ufb06", Normalizer.Form.NFKD);
        print.accept(nfkdString);
    }
}
