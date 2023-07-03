package pl.akademiaqa.utils;

public class StringUtils {

    // prywatny konstruktor

    public static String removeRoundBrackets(String text) {
        return text.replaceAll("[()]", "");
    }
}
